package hvdloc.vn.doan_t3h.controller;

import hvdloc.vn.doan_t3h.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;


    @RequestMapping("product/detail/{id}")
    public String detail(@PathVariable Long id, Model model){
        model.addAttribute("products", productService.getById(id));
        model.addAttribute("addToCart", productService.getOrderAddToCart());
        return "detail";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping("product/order/{id}")
    public String order(@PathVariable Long id, @RequestParam String color,
                        @RequestParam String size,
                        @RequestParam Integer number,
                        RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("message", productService.createOrder(id,size,color,number));
        return "redirect:/product/detail" + id;
    }

    @PreAuthorize(value = "isAuthenticated()")
    @RequestMapping("product/cart")
    public String cart(Model model){
        model.addAttribute("products", productService.findAllByUserId());
        return "/product/cart";
    }

    @PreAuthorize(value = "isAuthenticated()")
    @GetMapping("product/cart/{productId}/{id}")
    public String deleteCart(@PathVariable Long id, RedirectAttributes redirectAttributes){
        productService.deleteCart(id);
        return "redirect:/product/cart";
    }

    @RequestMapping("product/shop/{id}")
    public String shop(@PathVariable Long id, Model model){
        model.addAttribute("products", productService.getById(id));
//        model.addAttribute("addToCart", productService.getOrderAddToCart());
        return "/product/shop";
    }

}
