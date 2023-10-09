package mmtr.web.controller;

import mmtr.web.common.AddEntryDto;
import mmtr.web.common.DictionaryDto;
import mmtr.web.db.entity.TypeEntity;
import mmtr.web.service.operation.OperationService;
import mmtr.web.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String addEntry(@ModelAttribute("entry") AddEntryDto addEntryDto, Model model) {

        boolean result = operationService.addEntry(addEntryDto);
        model.addAttribute("result", result ? "Success" : "Invalid input data");
        model.addAttribute("tableData", searchService.getDictionaryByTypeId(addEntryDto.getTypeId()));

        return "dict/dictShow";
    }

    @ModelAttribute("dictTypes")
    public List<TypeEntity> getDictTypes() {
        return searchService.getTypes();
    }

    @ModelAttribute("entry")
    public AddEntryDto getAddEntryDto() {
        return new AddEntryDto();
    }
}