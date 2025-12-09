package com.pulse.ecommerce.service;

import com.pulse.ecommerce.model.*;
import com.pulse.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DashboardService {
    ProductRepository productRepo;
    EarbudProductRepository earbudRepo;
    SpeakerProductRepository speakerRepo;
    HeadPhoneProductRepository headPhoneRepo;
    WatchProductRepository watchRepo;
    PowerBankRepository powerBankRepo;

    @Autowired
    public DashboardService(
            ProductRepository productRepo,
            EarbudProductRepository earbudRepo,
            SpeakerProductRepository speakerRepo,
            HeadPhoneProductRepository headPhoneRepo,
            WatchProductRepository watchRepo,
            PowerBankRepository powerBankRepo){
        this.productRepo = productRepo;
        this.earbudRepo = earbudRepo;
        this.speakerRepo = speakerRepo;
        this.headPhoneRepo = headPhoneRepo;
        this.watchRepo = watchRepo;
        this.powerBankRepo = powerBankRepo;
    }

    public List<EarBudProduct> getEarbudsToDisplay(){
        return earbudRepo.findTop6ByOrderByPriceAsc();
    }

    public List<SpeakerProduct> getSpeakersToDisplay(){
        return speakerRepo.findTop6ByOrderByPriceAsc();
    }

    public List<HeadPhoneProduct> getHeadPhonesToDisplay(){
        return headPhoneRepo.findTop6ByOrderByPriceAsc();
    }

    public List<WatchProduct> getWatchesToDisplay(){
        return watchRepo.findTop6ByOrderByPriceAsc();
    }

    public List<PowerBankProduct> getPowerBanksToDisplay(){
        return powerBankRepo.findTop6ByOrderByPriceAsc();
    }

    public List<Product> searchProductByName(String keyword){
        return productRepo.findByNameContaining(keyword);
    }

    public Optional<Product> getProductDetails(Long id){
        return productRepo.findById(id);
    }
}
