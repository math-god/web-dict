package mmtr.web.controller;

import mmtr.web.common.GetEntriesDto;
import mmtr.web.service.entry.EntryService;
import mmtr.web.service.type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/dictShow")
public class DictionaryShowingController {

    private EntryService entryService;
    private TypeService typeService;

    @Autowired
    public DictionaryShowingController(EntryService entryService, TypeService typeService) {
        this.entryService = entryService;
        this.typeService = typeService;
    }

    @GetMapping("/getDictionaryByType")
    public String getDictionaryByType(@RequestParam(value = "typeId") UUID typeID, Model model) {
        GetEntriesDto tableData = entryService.getEntriesByTypeId(typeID);

        model.addAttribute("tableData", tableData);

        return "dict/dictShow";
    }

    @GetMapping("/getEntriesByKeyAndByType")
    public String getEntriesByKeyAndByType(@RequestParam(value = "typeId") UUID typeID,
                                           @RequestParam(value = "keyId") UUID keyId, Model model) {

        GetEntriesDto tableData = entryService.getEntriesByKeyIdAndTypeId(keyId, typeID);

        model.addAttribute("tableData", tableData);

        return "dict/dictShow";
    }

    @GetMapping("/getTypes")
    public String showAllTypes(Model model) {
        List<String> typeList = new ArrayList<>();

        typeList.addAll(typeService.getTypes());

        model.addAttribute("dictTypes", typeList);

        return "dict/dictSelection";
    }
}