package hvdloc.vn.doan_t3h.controller.backend;

import hvdloc.vn.doan_t3h.config.paging.PagingParam;
import hvdloc.vn.doan_t3h.dto.BrandCategoryDto;
import hvdloc.vn.doan_t3h.dto.ResponseTableDto;
import hvdloc.vn.doan_t3h.service.BrandCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller
public class BrandCategoryController {
    @Autowired
    BrandCategoryService brandCategoryService;

    @GetMapping("/backend/{path}/list")
    public String list(@PathVariable String path, @PagingParam ResponseTableDto tableDto, Model model) {
        model.addAttribute("title", "brand".equalsIgnoreCase(path) ? " thương hiệu" : "thể loại");

        tableDto.setPath(path);
        brandCategoryService.list(tableDto, path);
        return "brand-category/list";
    }

    @GetMapping("/backend/{path}/create")
    public String create(Model model, @PathVariable String path,
                         HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("title", "brand".equalsIgnoreCase(path) ? " thương hiệu" : "thể loại");
        model.addAttribute("path", path);
        return "brand-category/create";
    }
    @GetMapping("/backend/{path}/edit/{id}")
    public String edit(Model model, @PathVariable String path, @PathVariable Long id,
                       HttpServletRequest request, HttpServletResponse response){
        model.addAttribute("title", "brand".equalsIgnoreCase(path) ? " thương hiệu": "thể loại");
        model.addAttribute("path", path);
        model.addAttribute("model", brandCategoryService.getById(id, path));
        return "brand-category/edit";
    }

    @PostMapping(value = "/backend/{path}/save", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createform(@PathVariable String path, BrandCategoryDto dto,
                             RedirectAttributes redirectAttributes, Model model) throws Exception {
        brandCategoryService.save(dto, path);
        return "redirect:/backend/" + path + "/list";
    }

    @DeleteMapping(value = "/backend/{path}/delete/{id}")
    @ResponseBody
    public String delete(Model model, @PathVariable String path, @PathVariable Long id)
            throws SQLException {
        model.addAttribute("path", path);
        return brandCategoryService.delete(path, id);
//        return "/jsp/user/create.jsp";
    }


}
