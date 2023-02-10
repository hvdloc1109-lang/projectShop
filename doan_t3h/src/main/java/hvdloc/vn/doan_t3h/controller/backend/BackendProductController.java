package hvdloc.vn.doan_t3h.controller.backend;

import hvdloc.vn.doan_t3h.config.paging.PagingParam;
import hvdloc.vn.doan_t3h.dto.ProductDto;
import hvdloc.vn.doan_t3h.dto.ResponseDto;
import hvdloc.vn.doan_t3h.dto.ResponseTableDto;
import hvdloc.vn.doan_t3h.service.BrandCategoryService;
import hvdloc.vn.doan_t3h.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/backend/product")
public class BackendProductController {

    @Autowired
    ProductService productService;
    @Autowired
    BrandCategoryService brandCategoryService;

    @GetMapping("/list")
    public String listProduct(@PagingParam(path = "product") ResponseTableDto responseTableDto) {
        productService.list(responseTableDto);
        return "product/list";
    }
    @GetMapping("/{id}")
    public String product(@PathVariable Long id, Model model) {
        model.addAttribute("productDto", productService.getById(id));
        model.addAttribute("brands", brandCategoryService.findAll("brand"));
        model.addAttribute("categories", brandCategoryService.findAll("category"));
        return "product/detail";
    }

    @GetMapping("/create")
    private String create(Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("brands", brandCategoryService.findAll("brand"));
        model.addAttribute("categories", brandCategoryService.findAll("category"));
        model.addAttribute("productDto", productDto);
        return "product/create";
    }

    @PostMapping(value = "/save",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String save(@Valid @ModelAttribute("productDto") ProductDto productDto, BindingResult bindingResult,
                       Model model, RedirectAttributes redirectAttributes) throws Exception {
        ResponseDto responseDto = productService.save(productDto);
        model.addAttribute("message", responseDto.getMessage());
        return "redirect:/backend/product/list";
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable Long id) {
        return productService.delete(id);
    }
}
