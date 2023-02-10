package hvdloc.vn.doan_t3h.controller;

import hvdloc.vn.doan_t3h.dto.ResponseDto;
import hvdloc.vn.doan_t3h.dto.UserDto;
import hvdloc.vn.doan_t3h.service.BrandCategoryService;
import hvdloc.vn.doan_t3h.service.ProductService;
import hvdloc.vn.doan_t3h.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    BrandCategoryService brandCategoryService;

    @GetMapping({"index","","/"})
    public String index(Model model){
        model.addAttribute("categories", brandCategoryService.findAll("category"));
        model.addAttribute("products", productService.findTop4());
        return "index";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("register")
    public String register(){
        return "register";
    }

    @PostMapping(value = "register/save",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String registerSave(UserDto userDto, RedirectAttributes redirectAttributes){
        ResponseDto responseDto = userService.register(userDto);
        redirectAttributes.addFlashAttribute("message", responseDto.getMessage());
        return "redirect:/register";
    }

    @GetMapping("verify/{id}")
    public String verify(@PathVariable Long id, RedirectAttributes redirectAttributes){
        String result = userService.verify(id);
        redirectAttributes.addFlashAttribute("message", result);
        return "redirect:/login";
    }




}
