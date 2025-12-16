package com.pulse.ecommerce.repository;

import com.pulse.ecommerce.model.Order;
import com.pulse.ecommerce.model.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // === Standard JPA Methods (No Changes Needed) ===
    long countByStatus(String status);
    List<Order> findByStatusOrderByCreatedAtDesc(String status);
    List<Order> findByCustomer(UserRecord user);

    @Query("SELECT SUM(o.totalAmount) FROM Order o")
    BigDecimal sumTotalAmount();


    // === NATIVE QUERIES FOR DASHBOARD ANALYTICS (FULLY CORRECTED) ===

    /**
     * [Super Admin Chart] Gets total revenue per category.
     * CORRECTED: Uses SUM(oi.total) and correct table names.
     */
    @Query(value = """
        SELECT
            c.name AS label,
            SUM(oi.total) AS value
        FROM order_items oi
        JOIN product_variants pv ON oi.variant_id = pv.id
        JOIN products p ON pv.product_id = p.id
        JOIN categories c ON p.category_id = c.id
        GROUP BY c.name
    """, nativeQuery = true)
    List<Map<String, Object>> getRevenueByCategorySQL();

    /**
     * [Super Admin Chart] Gets total revenue for each of the last 6 months.
     * CORRECTED: Uses DATE_FORMAT for MySQL and guarantees 6 months of data.
     */
    @Query(value = """
        SELECT
            months.label,
            COALESCE(SUM(o.total_amount), 0) as value
        FROM (
            SELECT DATE_FORMAT(NOW() - INTERVAL 5 MONTH, '%Y-%m') as label UNION ALL
            SELECT DATE_FORMAT(NOW() - INTERVAL 4 MONTH, '%Y-%m') UNION ALL
            SELECT DATE_FORMAT(NOW() - INTERVAL 3 MONTH, '%Y-%m') UNION ALL
            SELECT DATE_FORMAT(NOW() - INTERVAL 2 MONTH, '%Y-%m') UNION ALL
            SELECT DATE_FORMAT(NOW() - INTERVAL 1 MONTH, '%Y-%m') UNION ALL
            SELECT DATE_FORMAT(NOW(), '%Y-%m')
        ) as months
        LEFT JOIN orders o ON DATE_FORMAT(o.created_at, '%Y-%m') = months.label
        GROUP BY months.label
        ORDER BY months.label ASC
    """, nativeQuery = true)
    List<Map<String, Object>> getRevenueByMonthSQL();

    /**
     * [Analytics Drill-Down] Gets revenue of each product for a specific CATEGORY.
     * CORRECTED: Uses SUM(oi.total) and correct table names.
     */
    @Query(value = """
        SELECT
            p.name as label,
            SUM(oi.total) as totalRevenue,
            SUM(oi.quantity) as unitsSold
        FROM order_items oi
        JOIN product_variants pv ON oi.variant_id = pv.id
        JOIN products p ON pv.product_id = p.id
        JOIN categories c ON p.category_id = c.id
        WHERE c.name = :categoryName
        GROUP BY p.name
        ORDER BY totalRevenue DESC
    """, nativeQuery = true)
    List<Map<String, Object>> getRevenueByProductForCategorySQL(@Param("categoryName") String categoryName);

    /**
     * [Analytics Drill-Down] Gets revenue of each product for a specific MONTH.
     * CORRECTED: Uses SUM(oi.total), correct table names, and DATE_FORMAT for MySQL.
     */
    @Query(value = """
        SELECT
            p.name as label,
            SUM(oi.total) as totalRevenue,
            SUM(oi.quantity) as unitsSold
        FROM orders o
        JOIN order_items oi ON o.id = oi.order_id
        JOIN product_variants pv ON oi.variant_id = pv.id
        JOIN products p ON pv.product_id = p.id
        WHERE DATE_FORMAT(o.created_at, '%Y-%m') = :month
        GROUP BY p.name
        ORDER BY totalRevenue DESC
    """, nativeQuery = true)
    List<Map<String, Object>> getRevenueByProductForMonthSQL(@Param("month") String month);

    // In OrderRepository.java

    /**
     * NEW [Product Spotlight]: Gets the sales breakdown for each variant of a specific product.
     */
    @Query(value = """
        SELECT
            pv.color AS variantLabel,
            SUM(oi.quantity) AS unitsSold,
            SUM(oi.total) AS totalRevenue
        FROM order_items oi
        JOIN product_variants pv ON oi.variant_id = pv.id
        JOIN products p ON pv.product_id = p.id
        WHERE p.name = :productName
        GROUP BY pv.color
        ORDER BY totalRevenue DESC
    """, nativeQuery = true)
    List<Map<String, Object>> getVariantBreakdownForProductSQL(@Param("productName") String productName);

    /**
     * [Super Admin Chart - Not currently used, but corrected for future use]
     * Gets the count of orders for each status.
     */
    @Query(value = "SELECT status AS label, COUNT(*) AS value FROM orders GROUP BY status", nativeQuery = true)
    List<Map<String, Object>> getOrderStatusDistributionSQL();

    @Query(value = "SELECT SUM(total_amount) FROM orders WHERE MONTH(created_at) = MONTH(NOW()) AND YEAR(created_at) = YEAR(NOW())",
            nativeQuery = true)
    BigDecimal sumTotalAmountForCurrentMonth();
}