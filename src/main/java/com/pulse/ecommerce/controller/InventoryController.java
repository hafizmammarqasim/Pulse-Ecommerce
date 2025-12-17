package com.pulse.ecommerce.controller;


import com.pulse.ecommerce.model.ProductVariant;
import com.pulse.ecommerce.repository.ProductVariantRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/inventory")
public class InventoryController {

    private final ProductVariantRepository variantRepo;

    public InventoryController(ProductVariantRepository variantRepo) {
        this.variantRepo = variantRepo;
    }

    @GetMapping
    public String inventoryDashboard(@RequestParam(name = "threshold", required = false) Integer threshold,
                                     Model model) {
        int t = (threshold == null ? 5 : threshold);
        List<ProductVariant> lowStock = variantRepo.findByStockQuantityLessThan(t);
        model.addAttribute("lowStockVariants", lowStock);
        model.addAttribute("threshold", t);
        return "admin/inventory";
    }

    @PostMapping("/{id}/stock")
    public String updateStock(@PathVariable Long id,
                              @RequestParam Integer stock,
                              @RequestParam(required = false) String redirectTo) {
        ProductVariant v = variantRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid variant id"));
        v.setStockQuantity(stock);
        variantRepo.save(v);

        if ("products".equals(redirectTo) && v.getProduct() != null) {
            return "redirect:/admin/products/" + v.getProduct().getId() + "/edit";
        }
        return "redirect:/admin/inventory";
    }
}
