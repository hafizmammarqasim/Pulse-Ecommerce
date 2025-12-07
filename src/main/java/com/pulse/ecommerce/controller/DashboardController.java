package com.pulse.ecommerce.controller;

import com.pulse.ecommerce.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
