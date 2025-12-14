package com.pulse.ecommerce.config;


import com.pulse.ecommerce.model.Category;
import com.pulse.ecommerce.model.WatchProduct;
import com.pulse.ecommerce.repository.CategoryRepository;
import com.pulse.ecommerce.repository.ProductRepository;
import com.pulse.ecommerce.repository.ProductVariantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class WatchDataSeeder extends BaseSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepo;
    private final ProductRepository productRepo;

    public WatchDataSeeder(CategoryRepository categoryRepo,
                           ProductRepository productRepo,
                           ProductVariantRepository variantRepo) {
        super(variantRepo);
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

    @Override
    public void run(String... args) {

        Category watchesCat = categoryRepo.findByName("Watches").orElse(null);
        if (watchesCat == null) return;

        if (productRepo.existsByCategory_Name("Watches")) return;





    // 1 - TimeFlex (Active)
        WatchProduct w1 = new WatchProduct();
        w1.setName("TimeFlex");
        w1.setCategory(watchesCat);
        w1.setLabel("Active");
        w1.setTrending(false);
        w1.setBestSeller(false);
        w1.setPrice(new BigDecimal("7999.00"));
        w1.setDisplayType("AMOLED");
        w1.setDisplaySizeInch(1.78);
        w1.setResolution("368x448");
        w1.setBatteryCapacityMah(300);
        w1.setBatteryLifeDays(7);
        w1.setWaterResistanceRating("5ATM");
        w1.setHeartRateSensor(true);
        w1.setSpo2Sensor(true);
        w1.setSportsModesCount(100);
        w1.setGps(true);
        w1.setOsCompatibility("Android,iOS");
        w1.setStrapMaterial("Silicone");
        w1.setWeightGrams(45);
        productRepo.save(w1);

        createVariant(w1, "Black", "Sport", 25);
        createVariant(w1, "Blue", "Sport", 20);
        createVariant(w1, "Orange", "Sport", 15);

        // 2 - SyncWave (Classic)
        WatchProduct w2 = new WatchProduct();
        w2.setName("SyncWave");
        w2.setCategory(watchesCat);
        w2.setLabel("Classic");
        w2.setTrending(false);
        w2.setBestSeller(false);
        w2.setPrice(new BigDecimal("8999.00"));
        w2.setDisplayType("AMOLED");
        w2.setDisplaySizeInch(1.9);
        w2.setResolution("410x502");
        w2.setBatteryCapacityMah(320);
        w2.setBatteryLifeDays(10);
        w2.setWaterResistanceRating("3ATM");
        w2.setHeartRateSensor(true);
        w2.setSpo2Sensor(true);
        w2.setSportsModesCount(60);
        w2.setGps(false);
        w2.setOsCompatibility("Android,iOS");
        w2.setStrapMaterial("Leather");
        w2.setWeightGrams(50);
        productRepo.save(w2);

        createVariant(w2, "Black", "Classic", 20);
        createVariant(w2, "Brown", "Classic", 16);
        createVariant(w2, "Blue", "Classic", 14);

        // 3 - CoreFit (Active)
        WatchProduct w3 = new WatchProduct();
        w3.setName("CoreFit");
        w3.setCategory(watchesCat);
        w3.setLabel("Active");
        w3.setTrending(false);
        w3.setBestSeller(false);
        w3.setPrice(new BigDecimal("5999.00"));
        w3.setDisplayType("TFT");
        w3.setDisplaySizeInch(1.6);
        w3.setResolution("240x280");
        w3.setBatteryCapacityMah(260);
        w3.setBatteryLifeDays(10);
        w3.setWaterResistanceRating("IP68");
        w3.setHeartRateSensor(true);
        w3.setSpo2Sensor(true);
        w3.setSportsModesCount(40);
        w3.setGps(false);
        w3.setOsCompatibility("Android,iOS");
        w3.setStrapMaterial("Silicone");
        w3.setWeightGrams(38);
        productRepo.save(w3);

        createVariant(w3, "Black", "Fitness", 28);
        createVariant(w3, "Green", "Fitness", 20);
        createVariant(w3, "Purple", "Fitness", 18);

        // 4 - AeroTrack (Active)
        WatchProduct w4 = new WatchProduct();
        w4.setName("AeroTrack");
        w4.setCategory(watchesCat);
        w4.setLabel("Active");
        w4.setTrending(false);
        w4.setBestSeller(false);
        w4.setPrice(new BigDecimal("6999.00"));
        w4.setDisplayType("AMOLED");
        w4.setDisplaySizeInch(1.7);
        w4.setResolution("320x390");
        w4.setBatteryCapacityMah(280);
        w4.setBatteryLifeDays(8);
        w4.setWaterResistanceRating("5ATM");
        w4.setHeartRateSensor(true);
        w4.setSpo2Sensor(true);
        w4.setSportsModesCount(70);
        w4.setGps(true);
        w4.setOsCompatibility("Android,iOS");
        w4.setStrapMaterial("Silicone");
        w4.setWeightGrams(42);
        productRepo.save(w4);

        createVariant(w4, "Black", "Runner", 22);
        createVariant(w4, "Red", "Runner", 18);
        createVariant(w4, "Lime", "Runner", 16);

        // 5 - UrbanSync (Classic)
        WatchProduct w5 = new WatchProduct();
        w5.setName("UrbanSync");
        w5.setCategory(watchesCat);
        w5.setLabel("Classic");
        w5.setTrending(false);
        w5.setBestSeller(false);
        w5.setPrice(new BigDecimal("8499.00"));
        w5.setDisplayType("AMOLED");
        w5.setDisplaySizeInch(1.78);
        w5.setResolution("368x448");
        w5.setBatteryCapacityMah(300);
        w5.setBatteryLifeDays(7);
        w5.setWaterResistanceRating("3ATM");
        w5.setHeartRateSensor(true);
        w5.setSpo2Sensor(true);
        w5.setSportsModesCount(50);
        w5.setGps(false);
        w5.setOsCompatibility("Android,iOS");
        w5.setStrapMaterial("Leather");
        w5.setWeightGrams(46);
        productRepo.save(w5);

        createVariant(w5, "Black", "City", 20);
        createVariant(w5, "Brown", "City", 16);
        createVariant(w5, "Navy", "City", 14);

        // 6 - NightPulse (Studio)
        WatchProduct w6 = new WatchProduct();
        w6.setName("NightPulse");
        w6.setCategory(watchesCat);
        w6.setLabel("Studio");
        w6.setTrending(false);
        w6.setBestSeller(false);
        w6.setPrice(new BigDecimal("8999.00"));
        w6.setDisplayType("AMOLED");
        w6.setDisplaySizeInch(1.7);
        w6.setResolution("320x390");
        w6.setBatteryCapacityMah(290);
        w6.setBatteryLifeDays(9);
        w6.setWaterResistanceRating("5ATM");
        w6.setHeartRateSensor(true);
        w6.setSpo2Sensor(true);
        w6.setSportsModesCount(80);
        w6.setGps(true);
        w6.setOsCompatibility("Android,iOS");
        w6.setStrapMaterial("Silicone");
        w6.setWeightGrams(43);
        productRepo.save(w6);

        createVariant(w6, "Black", "Studio", 18);
        createVariant(w6, "Midnight Blue", "Studio", 15);
        createVariant(w6, "Wine Red", "Studio", 12);

        // 7 - TrailGuard (Rugged)
        WatchProduct w7 = new WatchProduct();
        w7.setName("TrailGuard");
        w7.setCategory(watchesCat);
        w7.setLabel("Rugged");
        w7.setTrending(false);
        w7.setBestSeller(false);
        w7.setPrice(new BigDecimal("9999.00"));
        w7.setDisplayType("AMOLED");
        w7.setDisplaySizeInch(1.8);
        w7.setResolution("368x448");
        w7.setBatteryCapacityMah(400);
        w7.setBatteryLifeDays(14);
        w7.setWaterResistanceRating("10ATM");
        w7.setHeartRateSensor(true);
        w7.setSpo2Sensor(true);
        w7.setSportsModesCount(120);
        w7.setGps(true);
        w7.setOsCompatibility("Android,iOS");
        w7.setStrapMaterial("Silicone");
        w7.setWeightGrams(55);
        productRepo.save(w7);

        createVariant(w7, "Black", "Rugged", 18);
        createVariant(w7, "Army Green", "Rugged", 15);
        createVariant(w7, "Desert Sand", "Rugged", 12);

        // 8 - FlexiBand (Lite)
        WatchProduct w8 = new WatchProduct();
        w8.setName("FlexiBand");
        w8.setCategory(watchesCat);
        w8.setLabel("Lite");
        w8.setTrending(false);
        w8.setBestSeller(false);
        w8.setPrice(new BigDecimal("4499.00"));
        w8.setDisplayType("TFT");
        w8.setDisplaySizeInch(1.47);
        w8.setResolution("194x368");
        w8.setBatteryCapacityMah(200);
        w8.setBatteryLifeDays(10);
        w8.setWaterResistanceRating("5ATM");
        w8.setHeartRateSensor(true);
        w8.setSpo2Sensor(true);
        w8.setSportsModesCount(30);
        w8.setGps(false);
        w8.setOsCompatibility("Android,iOS");
        w8.setStrapMaterial("Silicone");
        w8.setWeightGrams(30);
        productRepo.save(w8);

        createVariant(w8, "Black", "Band", 28);
        createVariant(w8, "Pink", "Band", 22);
        createVariant(w8, "Sky Blue", "Band", 20);

        // 9 - HorizonView (Pro)
        WatchProduct w9 = new WatchProduct();
        w9.setName("HorizonView");
        w9.setCategory(watchesCat);
        w9.setLabel("Pro");
        w9.setTrending(true);
        w9.setBestSeller(true);
        w9.setPrice(new BigDecimal("11999.00"));
        w9.setDisplayType("AMOLED");
        w9.setDisplaySizeInch(1.92);
        w9.setResolution("410x502");
        w9.setBatteryCapacityMah(350);
        w9.setBatteryLifeDays(10);
        w9.setWaterResistanceRating("5ATM");
        w9.setHeartRateSensor(true);
        w9.setSpo2Sensor(true);
        w9.setSportsModesCount(100);
        w9.setGps(true);
        w9.setOsCompatibility("Android,iOS");
        w9.setStrapMaterial("Steel");
        w9.setWeightGrams(60);
        productRepo.save(w9);

        createVariant(w9, "Silver", "Pro", 16);
        createVariant(w9, "Black", "Pro", 14);
        createVariant(w9, "Gold", "Pro", 12);

        // 10 - ChronoLink (Classic)
        WatchProduct w10 = new WatchProduct();
        w10.setName("ChronoLink");
        w10.setCategory(watchesCat);
        w10.setLabel("Classic");
        w10.setTrending(false);
        w10.setBestSeller(false);
        w10.setPrice(new BigDecimal("9499.00"));
        w10.setDisplayType("AMOLED");
        w10.setDisplaySizeInch(1.78);
        w10.setResolution("368x448");
        w10.setBatteryCapacityMah(300);
        w10.setBatteryLifeDays(7);
        w10.setWaterResistanceRating("3ATM");
        w10.setHeartRateSensor(true);
        w10.setSpo2Sensor(true);
        w10.setSportsModesCount(60);
        w10.setGps(false);
        w10.setOsCompatibility("Android,iOS");
        w10.setStrapMaterial("Leather");
        w10.setWeightGrams(52);
        productRepo.save(w10);

        createVariant(w10, "Black", "Classic", 18);
        createVariant(w10, "Brown", "Classic", 14);
        createVariant(w10, "Blue", "Classic", 12);
    }
}

