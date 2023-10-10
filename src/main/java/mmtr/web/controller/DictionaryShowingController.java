package mmtr.web.controller;

import mmtr.web.common.*;
import mmtr.web.db.entity.TypeEntity;
import mmtr.web.service.operation.OperationService;
import mmtr.web.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/dictShow")
public class DictionaryShowingController {
    private SearchService searchService;
    private OperationService operationService;

    @Autowired
    public DictionaryShowingController(SearchService searchService, OperationService operationService) {
        this.searchService = searchService;
        this.operationService = operationService;
    }

    @GetMapping("")
    public String openPage(Model model) {
        return "dict/dictShow";
    }

    @GetMapping("/getDictionaryByType")
    public String getDictionaryByType(@RequestParam(value = "typeId") UUID typeID, Model model) {
        DictionaryDto tableData = searchService.getDictionaryByTypeId(typeID);

        model.addAttribute("tableData", tableData);

        return "dict/dictShow";
    }

    //TODO Исключить дублирование ключей в одном и том же словаре
    //TODO Исключить дублирование значений в одном и том же ключе
    @PostMapping("/addEntry")
    public String addEntry(@ModelAttribute("entry") AddEntryDto addEntryDto,
                           @RequestHeader(value = HttpHeaders.REFERER, required = false) final String ref,
                           Model model) {

        boolean result = operationService.addEntry(addEntryDto);
        model.addAttribute("result", result ? "Success" : "Invalid input data");

        return "redirect:" + ref;
    }

    @PostMapping("/addValue")
    public String addValue(@ModelAttribute("value") AddValueDto addValueDto,
                           @RequestHeader(value = HttpHeaders.REFERER, required = false) final String ref,
                           Model model) {
        boolean result = operationService.addValue(addValueDto);
        model.addAttribute("result", result ? "Success" : "Invalid input data");

        return "redirect:" + ref;
    }

    @GetMapping("/deleteValue/{id}")
    public String deleteValue(@PathVariable("id") UUID id,
                              @RequestHeader(value = HttpHeaders.REFERER, required = false) final String ref,
                              Model model) {

        boolean result = operationService.deleteValue(id);
        model.addAttribute("result", result ? "Success" : "Invalid input data");

        return "redirect:" + ref;
    }

    @PostMapping("/editValue/{id}")
    public String editValue(@PathVariable("id") UUID id,
                            @ModelAttribute("editValue") EditValueDto editValueDto,
                            @RequestHeader(value = HttpHeaders.REFERER, required = false) final String ref,
                            Model model) {

        boolean result = operationService.editValue(id, editValueDto.getValueName());
        model.addAttribute("result", result ? "Success" : "Invalid input data");

        return "redirect:" + ref;
    }

    @PostMapping("/editKey/{id}")
    public String editKey(@PathVariable("id") UUID id,
                          @ModelAttribute("editKey") EditKeyDto editKeyDto,
                          @RequestHeader(value = HttpHeaders.REFERER, required = false) final String ref,
                          Model model) {

        boolean result = operationService.editKey(id, editKeyDto.getKeyName());
        model.addAttribute("result", result ? "Success" : "Invalid input data");

        return "redirect:" + ref;
    }

    @ModelAttribute("dictTypes")
    public List<TypeEntity> getDictTypes() {
        return searchService.getTypes();
    }

    @ModelAttribute("entry")
    public AddEntryDto getAddEntryDto() {
        return new AddEntryDto();
    }

    @ModelAttribute("value")
    public AddValueDto getAddValueDto() {
        return new AddValueDto();
    }

    @ModelAttribute("editValue")
    public EditValueDto getEditValueDto() {
        return new EditValueDto();
    }

    @ModelAttribute("editKey")
    public EditKeyDto getEditKeyDto() {
        return new EditKeyDto();
    }
}