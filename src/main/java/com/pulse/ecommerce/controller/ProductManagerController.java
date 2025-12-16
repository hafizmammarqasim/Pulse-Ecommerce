package com.pulse.ecommerce.controller;

import com.pulse.ecommerce.model.Category;
import com.pulse.ecommerce.model.ProductVariant;
import com.pulse.ecommerce.service.ProductManagerService;
import org.springframework.web.bind.annotation.*;

import com.pulse.ecommerce.model.Product;
import com.pulse.ecommerce.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin/products")
public class ProductManagerController {

    private final ProductManagerService pmService;

    public ProductManagerController(ProductManagerService pmService) {
        this.pmService = pmService;
    }

    // List + filters
    @GetMapping
    public String listProducts(@RequestParam(required = false) String category,
                               @RequestParam(required = false) String q,
                               Model model) {
        model.addAttribute("products",
                pmService.listProducts(category, q));
        model.addAttribute("categories", pmService.getAllCategories());
        model.addAttribute("selectedCategory", category);
        model.addAttribute("query", q);
        return "admin/product-list";
    }

    // Show create form
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", pmService.getAllCategories());
        return "admin/product-form";
    }

    // Show edit form
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = pmService.getProduct(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product id"));
        model.addAttribute("product", product);
        model.addAttribute("categories", pmService.getAllCategories());
        model.addAttribute("variants", pmService.getVariantsForProduct(id));
        return "admin/product-form";
    }

    // Save create/update
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product,
                              @RequestParam Long categoryId) {
        Category cat = new Category();
        cat.setId(categoryId); // or load via repo if you prefer
        product.setCategory(cat);
        pmService.saveProduct(product);
        return "redirect:/admin/products";
    }

    // Delete product
    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        pmService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    // Update variant stock from inventory / low-stock table
    @PostMapping("/variant/{id}/stock")
    public String updateVariantStock(@PathVariable Long id,
                                     @RequestParam int stock,
                                     @RequestParam(required = false) Long productId) {
        ProductVariant variant = pmService.getVariantsForProduct(productId).stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid variant id"));

        variant.setStockQuantity(stock);
        pmService.saveVariant(variant);
        return "redirect:/admin/products/" + productId + "/edit";
    }
}

