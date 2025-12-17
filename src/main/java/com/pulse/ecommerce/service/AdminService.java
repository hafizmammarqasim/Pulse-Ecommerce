package com.pulse.ecommerce.service;

import com.pulse.ecommerce.dto.ChartData;
import com.pulse.ecommerce.model.*;
import com.pulse.ecommerce.repository.*;

import java.math.RoundingMode;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final OrderRepository orderRepo;
    private final OrderReturnRepo returnRepo;
    private final SupportTicketRepo ticketRepo;
    private final ProductVariantRepository variantRepo;
    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final UserRepo userRepo;

    @Autowired
    public AdminService(OrderRepository orderRepo, OrderReturnRepo returnRepo, SupportTicketRepo ticketRepo,
                        ProductVariantRepository variantRepo, ProductRepository productRepo, CategoryRepository categoryRepo, UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.returnRepo = returnRepo;
        this.ticketRepo = ticketRepo;
        this.variantRepo = variantRepo;
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    // This method gets the numbers for the Order Manager's KPI cards
    public Map<String, Long> getOrderManagerStats() {
        Map<String, Long> stats = new HashMap<>();

        stats.put("pendingOrders", orderRepo.countByStatus("PENDING"));
        stats.put("returnRequests", returnRepo.countByStatus(ReturnStatus.REQUESTED));
        stats.put("openTickets", ticketRepo.countByStatus(TicketStatus.OPEN));

        stats.put("totalOrders", orderRepo.count());

        return stats;
    }

    public List<Order> getRecentPendingOrders() {
        // We will fetch all pending orders, but you could limit it later with Pageable
        return orderRepo.findByStatusOrderByCreatedAtDesc("PENDING");
    }

    // --- Product Manager Stats ---
    public Map<String, Long> getProductManagerStats() {
        Map<String, Long> stats = new HashMap<>();

        //we defined 5 as our threshold
        stats.put("lowStockItems", variantRepo.countByStockQuantityLessThan(10));
        stats.put("totalProducts", productRepo.count());
        stats.put("totalCategories", categoryRepo.count());

        return stats;
    }

    // In AdminService.java

    public Map<String, Object> getSuperAdminStats() {
        Map<String, Object> stats = new HashMap<>();

        // --- KPI Card Calculations ---
        BigDecimal totalRevenue = orderRepo.sumTotalAmount();
        totalRevenue = (totalRevenue != null) ? totalRevenue : BigDecimal.ZERO;
        stats.put("totalRevenue", totalRevenue);

        // MODIFIED: Calculate Revenue for This Month
        BigDecimal revenueThisMonth = orderRepo.sumTotalAmountForCurrentMonth();
        stats.put("revenueThisMonth", (revenueThisMonth != null) ? revenueThisMonth : BigDecimal.ZERO);

        long totalOrders = orderRepo.count();
        stats.put("totalOrders", totalOrders);

        // Calculate Average Order Value
        BigDecimal avgOrderValue = BigDecimal.ZERO;
        if (totalOrders > 0) {
            avgOrderValue = totalRevenue.divide(BigDecimal.valueOf(totalOrders), 2, RoundingMode.HALF_UP);
        }
        stats.put("avgOrderValue", avgOrderValue);
        // --- End of KPI Calculations ---

        // Chart data remains the same
        stats.put("monthlyData", orderRepo.getRevenueByMonthSQL());
        stats.put("categoryData", orderRepo.getRevenueByCategorySQL());
        stats.put("statusData", orderRepo.getOrderStatusDistributionSQL());

        return stats;
    }

//        Map<String, Object> stats = new HashMap<>();
//
//        BigDecimal totalRevenue = orderRepo.sumTotalAmount();
//        // Handle case where there are no orders yet
//        stats.put("totalRevenue", totalRevenue != null ? totalRevenue : BigDecimal.ZERO);
//
//        stats.put("totalOrders", orderRepo.count());
//        stats.put("totalCustomers", userRepo.countByRole("USER")); // Or "CUSTOMER" depending on your Enum
//
//        // Calculate Average Order Value
//        long totalOrders = orderRepo.count();
//        if (totalOrders > 0 && totalRevenue != null) {
//            BigDecimal avgOrderValue = totalRevenue.divide(new BigDecimal(totalOrders), 2, BigDecimal.ROUND_HALF_UP);
//            stats.put("avgOrderValue", avgOrderValue);
//        } else {
//            stats.put("avgOrderValue", BigDecimal.ZERO);
//        }
//
//        return stats;

    public List<SupportTicket> getOpenSupportTickets() {
        return ticketRepo.findByTypeNotAndStatus(TicketType.RETURN, TicketStatus.OPEN);
        // We will need to add this method to the repository
    }

    // In AdminService.java

// ... inside the AdminService class

    // MODIFIED: This method now returns the full Map, not ChartData
    public List<Map<String, Object>> getRevenueByProductForCategory(String categoryName) {
        return orderRepo.getRevenueByProductForCategorySQL(categoryName);
    }

    // MODIFIED: This method now also returns the full Map
    public List<Map<String, Object>> getRevenueByProductForMonth(String month) {
        return orderRepo.getRevenueByProductForMonthSQL(month);
    }

    // In AdminService.java

    public List<Map<String, Object>> getVariantBreakdownForProduct(String productName) {
        return orderRepo.getVariantBreakdownForProductSQL(productName);
    }

    public List<ProductVariant> getLowStockVariantsForPM() {
        // threshold 10, same as in stats
        return variantRepo.findByStockQuantityLessThan(10);
    }


}