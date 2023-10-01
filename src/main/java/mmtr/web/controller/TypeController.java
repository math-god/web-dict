package mmtr.web.controller;

import mmtr.web.service.type.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/type")
public class TypeController {

    private TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/getTypes")
    public String showAllTypes(Model model) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String type : typeService.getTypes()) {
            stringBuilder.append(type).append(" ");
        }

        model.addAttribute("types", stringBuilder.toString());

        return "type/type";
    }
}
