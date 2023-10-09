package mmtr.web.controller;

import mmtr.web.common.DictionaryDto;
import mmtr.web.db.entity.TypeEntity;
import mmtr.web.service.search.SearchService;
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
    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
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

        List<DictionaryDto> result;
        if (kindOfInput.equals("key")) {
            result = searchService.getEntriesByKey(typeId, input);
        } else {
            result = searchService.getEntriesByValue(typeId, input);
        }

            model.addAttribute("tableData", result);

        return "dict/dictSearch";
    }

    @ModelAttribute("dictTypes")
    public List<TypeEntity> getDictTypes() {
        return searchService.getTypes();
    }
}
