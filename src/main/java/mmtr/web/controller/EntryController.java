package mmtr.web.controller;

import mmtr.web.common.EntryDto;
import mmtr.web.service.entry.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/entry")
public class EntryController {

    private EntryService entryService;

    @Autowired
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @GetMapping("/getDictionaryByType")
    public String getDictionaryByType(@RequestParam(value = "type") String type, Model model){
       List<EntryDto> entryDtos =  entryService.getEntriesByType(type);

       model.addAttribute("dictionary", entryDtos);

       return "entry/entry";
    }
}
