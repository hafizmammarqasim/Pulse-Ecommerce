package com.pulse.ecommerce.service;

import com.pulse.ecommerce.model.*;
import com.pulse.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    EarbudProductRepository earbudRepo;
    SpeakerProductRepository speakerRepo;
    HeadPhoneProductRepository headPhoneRepo;
    WatchProductRepository watchRepo;
    PowerBankRepository powerBankRepo;

    @Autowired
    public DashboardService(
            EarbudProductRepository earbudRepo,
            SpeakerProductRepository speakerRepo,
            HeadPhoneProductRepository headPhoneRepo,
            WatchProductRepository watchRepo,
            PowerBankRepository powerBankRepo){
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
}
