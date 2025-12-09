package com.pulse.ecommerce.controller;

import com.pulse.ecommerce.model.Product;
import com.pulse.ecommerce.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController {
    DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("earbudsList",dashboardService.getEarbudsToDisplay());
        model.addAttribute("headPhonesList",dashboardService.getHeadPhonesToDisplay());
        model.addAttribute("speakersList",dashboardService.getSpeakersToDisplay());
        model.addAttribute("powerBankList",dashboardService.getPowerBanksToDisplay());
        model.addAttribute("watchList",dashboardService.getWatchesToDisplay());
        return "home";
    }

    @GetMapping("/search")
    public String searchItem(@RequestParam String query, Model model){
        model.addAttribute("productsList",dashboardService.searchProductByName(query));
        return "search-result";
    }

    @GetMapping("/product-details/{id}")
    public String productDetails(@PathVariable Long id, Model model){
        Product product = dashboardService.getProductDetails(id).
                orElseThrow(() -> new RuntimeException("No Product Found for this Id"));

        model.addAttribute("product",product);
        return "product-details.html";
    }


}
