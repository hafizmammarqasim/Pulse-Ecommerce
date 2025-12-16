package com.pulse.ecommerce.service;

import com.pulse.ecommerce.model.ProductVariant;
import com.pulse.ecommerce.model.ReturnStatus;
import com.pulse.ecommerce.model.TicketStatus;
import com.pulse.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                        ProductVariantRepository variantRepo, ProductRepository productRepo, CategoryRepository categoryRepo,UserRepo userRepo) {
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

    // --- Product Manager Stats ---


    public Map<String, Object> getSuperAdminStats() {
        Map<String, Object> stats = new HashMap<>();

        BigDecimal totalRevenue = orderRepo.sumTotalAmount();
        // Handle case where there are no orders yet
        stats.put("totalRevenue", totalRevenue != null ? totalRevenue : BigDecimal.ZERO);

        stats.put("totalOrders", orderRepo.count());
        stats.put("totalCustomers", userRepo.countByRole("USER")); // Or "CUSTOMER" depending on your Enum

        // Calculate Average Order Value
        long totalOrders = orderRepo.count();
        if (totalOrders > 0 && totalRevenue != null) {
            BigDecimal avgOrderValue = totalRevenue.divide(new BigDecimal(totalOrders), 2, BigDecimal.ROUND_HALF_UP);
            stats.put("avgOrderValue", avgOrderValue);
        } else {
            stats.put("avgOrderValue", BigDecimal.ZERO);
        }

        return stats;
    }

    public Map<String, Long> getProductManagerStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("lowStockItems", variantRepo.countByStockQuantityLessThan(10));
        stats.put("totalProducts", productRepo.count());
        stats.put("totalCategories", categoryRepo.count());
        return stats;
    }

    public List<ProductVariant> getLowStockVariantsForPM() {
        // threshold 10, same as in stats
        return variantRepo.findByStockQuantityLessThan(10);
    }
}