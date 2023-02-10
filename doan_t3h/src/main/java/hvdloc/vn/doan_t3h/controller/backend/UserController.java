package hvdloc.vn.doan_t3h.controller.backend;

import hvdloc.vn.doan_t3h.config.paging.PagingParam;
import hvdloc.vn.doan_t3h.dto.ResponseDto;
import hvdloc.vn.doan_t3h.dto.ResponseTableDto;
import hvdloc.vn.doan_t3h.dto.UserChangePassword;
import hvdloc.vn.doan_t3h.dto.UserDto;
import hvdloc.vn.doan_t3h.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.sql.SQLException;

@Controller
@RequestMapping("backend/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("create")
    public String create(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "user/create";
    }


    // phan quyen
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("list")
    public String list(@PagingParam(path = "user") ResponseTableDto responseTableDto) {
        userService.list(responseTableDto);
        return "user/list";
    }

    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable  Long id) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userService.detail(id));
        return "user/detail";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable  Long id) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userService.detail(id));
        return "user/edit";
    }

    @PostMapping(value = "save",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String save(@Valid  UserDto userDto, BindingResult bindingResult,
                       Model model, RedirectAttributes redirectAttributes)
            throws SQLException {
        if (bindingResult.hasErrors()) {
            return "user/create.jsp";
        } else if (!userDto.getPassword().equalsIgnoreCase(userDto.getRePassword())) {
            bindingResult.rejectValue("rePassword", "error.userDto", "Mật khẩu không khớp");
            return "user/create";
        }
        ResponseDto dto = userService.save(userDto);
        redirectAttributes.addFlashAttribute("message", dto.getMessage());
        return "redirect:/backend/user/list";
    }

    @PostMapping(value = "update",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String update( UserDto userDto,
                       Model model, RedirectAttributes redirectAttributes)
            throws SQLException {
        ResponseDto dto = userService.save(userDto);
        redirectAttributes.addFlashAttribute("message", dto.getMessage());
        return "redirect:/backend/user/list";
    }

    @DeleteMapping(value = "delete/{id}")
    @ResponseBody
    public String update(@PathVariable Long id)
            throws SQLException {
        return userService.delete(id);
    }

    @GetMapping("info")
    public String info() {
        return "user/info";
    }

    @GetMapping("changepass")
    public String changepass(Model model) {
        model.addAttribute("user",new UserChangePassword());
        return "user/changepass";
    }

    @PostMapping(value = "changepass",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String saveChangepass(@Valid @ModelAttribute("user") UserChangePassword userDto, BindingResult bindingResult,
                                 Model model, RedirectAttributes redirectAttributes)
            throws SQLException {
        if (bindingResult.hasErrors()) {
            return "user/changepass";
        } else if (!userDto.getPassword().equalsIgnoreCase(userDto.getRePassword())) {
            bindingResult.rejectValue("rePassword", "error.userDto", "Mật khẩu không khớp");
            return "user/changepass";
        }
        String dto = userService.changePass(userDto);
        redirectAttributes.addFlashAttribute("message", dto);
        return "redirect:/backend/user/info";
    }
}
