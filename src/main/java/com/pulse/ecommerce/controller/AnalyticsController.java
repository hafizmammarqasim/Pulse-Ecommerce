package com.pulse.ecommerce.controller;

import com.pulse.ecommerce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/analytics")
public class AnalyticsController {

    private final AdminService adminService;

    @Autowired
    public AnalyticsController(AdminService adminService) {
        this.adminService = adminService;
    }

    // This method remains unchanged, as the multi-product table is perfect for a monthly report.
    @GetMapping("/month")
    public String showMonthlyDetails(@RequestParam("date") String date, Model model) {
        List<Map<String, Object>> monthlyStats = adminService.getRevenueByProductForMonth(date);
        model.addAttribute("stats", monthlyStats);
        model.addAttribute("title", "Sales Report: " + date);
        return "admin/analytics-details";
    }

    // ⭐️ UPGRADED METHOD ⭐️
    @GetMapping("/category")
    public String showCategoryDetails(@RequestParam("name") String name, Model model) {
        // 1. Fetch the detailed breakdown by product (as before)
        List<Map<String, Object>> productStats = adminService.getRevenueByProductForCategory(name);

        // 2. Calculate the summary KPIs for the entire category
        BigDecimal categoryTotalRevenue = BigDecimal.ZERO;
        long categoryTotalUnitsSold = 0;

        for (Map<String, Object> product : productStats) {
            categoryTotalRevenue = categoryTotalRevenue.add((BigDecimal) product.get("totalRevenue"));
            // The result from SUM() is a BigDecimal, so we convert it to long
            categoryTotalUnitsSold += ((BigDecimal) product.get("unitsSold")).longValue();
        }

        BigDecimal categoryAvgItemPrice = BigDecimal.ZERO;
        if (categoryTotalUnitsSold > 0) {
            categoryAvgItemPrice = categoryTotalRevenue.divide(BigDecimal.valueOf(categoryTotalUnitsSold), 2, RoundingMode.HALF_UP);
        }

        // 3. Put the KPIs into a separate map for the view
        Map<String, Object> summaryKpis = new HashMap<>();
        summaryKpis.put("totalRevenue", categoryTotalRevenue);
        summaryKpis.put("totalUnitsSold", categoryTotalUnitsSold);
        summaryKpis.put("avgItemPrice", categoryAvgItemPrice);

        // 4. Add both the summary and the details to the model
        model.addAttribute("summaryKpis", summaryKpis);
        model.addAttribute("stats", productStats); // This is the detailed list for the table
        model.addAttribute("title", "Category Performance: " + name);

        return "admin/analytics-details";
    }
}