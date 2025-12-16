package com.pulse.ecommerce.service;



import com.pulse.ecommerce.model.Product;
import com.pulse.ecommerce.repository.OrderItemRepo;
import com.pulse.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class ProductTrendService {

    private final ProductRepository productRepo;
    private final OrderItemRepo orderItemRepo;

    public ProductTrendService(ProductRepository productRepo,
                               OrderItemRepo orderItemRepo) {
        this.productRepo = productRepo;
        this.orderItemRepo = orderItemRepo;
    }

    // runs every night at 2 AM; adjust as you like
    @Scheduled(cron = "0 0 2 * * *")
    @Transactional
    public void recomputeProductFlags() {

        // 1) reset all flags
        productRepo.resetTrending();
        productRepo.resetBestSeller();

        // 2) get sales for last 30 days
        LocalDateTime since = LocalDateTime.now().minusDays(30);
        List<Object[]> rows = orderItemRepo.sumQtyByProductSince(since);

        // sort by quantity desc
        rows.sort(Comparator.comparingLong(r -> -((Long) r[1])));

        // define thresholds: e.g. top 10 trending, top 3 best sellers
        for (int i = 0; i < rows.size(); i++) {
            Long productId = (Long) rows.get(i)[0];
            long rank = i; // effectively final

            productRepo.findById(productId).ifPresent(p -> {
                if (rank < 10) {
                    p.setTrending(true);
                }
                if (rank < 3) {
                    p.setBestSeller(true);
                }
            });
        }

    }
    }
