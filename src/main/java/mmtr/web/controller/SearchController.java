package mmtr.web.controller;

import mmtr.web.common.GetEntriesDto;
import mmtr.web.common.SearchDto;
import mmtr.web.db.entity.TypeEntity;
import mmtr.web.service.entry.EntryService;
import mmtr.web.service.type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/search")
public class SearchController {

    private EntryService entryService;
    private TypeService typeService;

    @Autowired
    public SearchController(EntryService entryService, TypeService typeService) {
        this.entryService = entryService;
        this.typeService = typeService;
    }

    @GetMapping("")
    public String openPage(Model model) {
        return "dict/dictSearch";
    }

    @GetMapping("/filterBy")
    public String search(@RequestParam(value = "typeId", required = false) UUID typeId,
                         @RequestParam(value = "kindOfInput") String kindOfInput,
                         @RequestParam(value = "input") String input, Model model) {

        if (input.isEmpty()) {
            return "dict/dictSearch";
        }

        SearchDto searchDto = new SearchDto();

        searchDto.setTypeId(typeId);
        searchDto.setKindOfInput(kindOfInput);
        searchDto.setInput(input);

        List<GetEntriesDto> result = entryService.getEntriesByFilter(searchDto);

        model.addAttribute("tableData", result);

        return "dict/dictSearch";
    }

    @ModelAttribute("dictTypes")
    public List<TypeEntity> getDictTypes() {
        return typeService.getTypes();
    }
}
