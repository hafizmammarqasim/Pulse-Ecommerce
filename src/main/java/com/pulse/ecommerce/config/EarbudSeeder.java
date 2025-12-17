package com.pulse.ecommerce.config;


import com.pulse.ecommerce.model.Category;
import com.pulse.ecommerce.model.EarBudProduct;
import com.pulse.ecommerce.model.EarbudCodec;
import com.pulse.ecommerce.model.ProductVariant;
import com.pulse.ecommerce.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class EarbudSeeder extends BaseSeeder implements CommandLineRunner {

 private final CategoryRepository categoryRepo;
 private final ProductRepository productRepo;
 private final EarBudCodecRepository earbudCodecRepo;

 public EarbudSeeder(CategoryRepository categoryRepo,
                     ProductRepository productRepo,
                     ProductVariantRepository variantRepo,
                     EarBudCodecRepository earbudCodecRepo,
                     ProductImageRepository imageRepo) {
  super(variantRepo, imageRepo);
  this.categoryRepo = categoryRepo;
  this.productRepo = productRepo;
  this.earbudCodecRepo = earbudCodecRepo;
 }
    @Override
    public void run(String... args) {
        Category earbudsCat = categoryRepo.findByName("Earbuds").orElse(null);
        if (earbudsCat == null) return;

        // skip if earbuds already exist
       if (productRepo.existsByCategory_Name("Earbuds")) return;

        // 1 - AirBeat X
        EarBudProduct e1 = new EarBudProduct();
        e1.setName("AirBeat X");
        e1.setCategory(earbudsCat);
        e1.setLabel("Active");
        e1.setTrending(false);
        e1.setBestSeller(false);
        e1.setPrice(new BigDecimal("4999.00"));
        e1.setDriverSizeMm(10);
        e1.setFrequencyResponse("20Hz-20kHz");
        e1.setBluetoothVersion("5.3");
        e1.setMic(true);
        e1.setNoiseCancellation("ENC");
        e1.setBatteryLifeEarbudsHours(6);
        e1.setBatteryLifeCaseHours(24);
        e1.setChargingTimeHours(1);
        e1.setChargingPort("Type-C");
        e1.setWaterResistanceRating("IPX4");
        e1.setLowLatencyMode(true);
        e1.setSizeMm(30);
        e1.setWeightGrams(4);
        productRepo.save(e1);

     // create variants and capture them
     ProductVariant e1Black = createVariant(e1, "Black", "Standard", 50);
     ProductVariant e1White = createVariant(e1, "White", "Standard", 40);
     ProductVariant e1Navy  = createVariant(e1, "Navy Blue", "Standard", 30);

// add images per color (use whatever filenames you’ll put in /static/images)
    // addVariantImage(e1Black, "/Images/img_1.png");
     //addVariantImage(e1White, "/Images/img.png");
     //addVariantImage(e1Navy,  "/Images/img_2.png");

     createCodec(e1, "AAC");
     createCodec(e1, "SBC");
        // 2 - NeoBuds Pro
        EarBudProduct e2 = new EarBudProduct();
        e2.setName("NeoBuds Pro");
        e2.setCategory(earbudsCat);
        e2.setLabel("Studio");
        e2.setTrending(false);
        e2.setBestSeller(false);
        e2.setPrice(new BigDecimal("7999.00"));
        e2.setDriverSizeMm(11);
        e2.setFrequencyResponse("20Hz-40kHz");
        e2.setBluetoothVersion("5.3");
        e2.setMic(true);
        e2.setNoiseCancellation("ANC");
        e2.setBatteryLifeEarbudsHours(7);
        e2.setBatteryLifeCaseHours(28);
        e2.setChargingTimeHours(2);
        e2.setChargingPort("Type-C");
        e2.setWaterResistanceRating("IPX5");
        e2.setLowLatencyMode(true);
        e2.setSizeMm(32);
        e2.setWeightGrams(5);
        productRepo.save(e2);

        createVariant(e2, "Black", "Pro", 40);
        createVariant(e2, "Blue", "Pro", 30);
        createVariant(e2, "White", "Pro", 25);
        createCodec(e2, "AAC");
        createCodec(e2, "SBC");
        createCodec(e2, "LDAC");

        // 3 - EchoBuds
        EarBudProduct e3 = new EarBudProduct();
        e3.setName("EchoBuds");
        e3.setCategory(earbudsCat);
        e3.setLabel("Classic");
        e3.setTrending(false);
        e3.setBestSeller(false);
        e3.setPrice(new BigDecimal("3999.00"));
        e3.setDriverSizeMm(10);
        e3.setFrequencyResponse("20Hz-20kHz");
        e3.setBluetoothVersion("5.2");
        e3.setMic(true);
        e3.setNoiseCancellation("NONE");
        e3.setBatteryLifeEarbudsHours(5);
        e3.setBatteryLifeCaseHours(20);
        e3.setChargingTimeHours(1);
        e3.setChargingPort("Type-C");
        e3.setWaterResistanceRating("IPX3");
        e3.setLowLatencyMode(false);
        e3.setSizeMm(30);
        e3.setWeightGrams(4);
        productRepo.save(e3);

        createVariant(e3, "Black", "Classic", 35);
        createVariant(e3, "White", "Classic", 30);
        createVariant(e3, "Rose Gold", "Classic", 20);
        createCodec(e3, "SBC");

        // 4 - VibePods
        EarBudProduct e4 = new EarBudProduct();
        e4.setName("VibePods");
        e4.setCategory(earbudsCat);
        e4.setLabel("Active");
        e4.setTrending(false);
        e4.setBestSeller(false);
        e4.setPrice(new BigDecimal("5499.00"));
        e4.setDriverSizeMm(11);
        e4.setFrequencyResponse("20Hz-20kHz");
        e4.setBluetoothVersion("5.3");
        e4.setMic(true);
        e4.setNoiseCancellation("ENC");
        e4.setBatteryLifeEarbudsHours(7);
        e4.setBatteryLifeCaseHours(26);
        e4.setChargingTimeHours(1);
        e4.setChargingPort("Type-C");
        e4.setWaterResistanceRating("IPX5");
        e4.setLowLatencyMode(true);
        e4.setSizeMm(31);
        e4.setWeightGrams(4);
        productRepo.save(e4);

        createVariant(e4, "Black", "Sport", 40);
        createVariant(e4, "Lime Green", "Sport", 25);
        createVariant(e4, "Orange", "Sport", 25);
        createCodec(e4, "AAC");
        createCodec(e4, "SBC");

        // 5 - FluxBuds
        EarBudProduct e5 = new EarBudProduct();
        e5.setName("FluxBuds");
        e5.setCategory(earbudsCat);
        e5.setLabel("Gaming");
        e5.setTrending(false);
        e5.setBestSeller(false);
        e5.setPrice(new BigDecimal("5999.00"));
        e5.setDriverSizeMm(12);
        e5.setFrequencyResponse("20Hz-20kHz");
        e5.setBluetoothVersion("5.3");
        e5.setMic(true);
        e5.setNoiseCancellation("ENC");
        e5.setBatteryLifeEarbudsHours(6);
        e5.setBatteryLifeCaseHours(24);
        e5.setChargingTimeHours(1);
        e5.setChargingPort("Type-C");
        e5.setWaterResistanceRating("IPX4");
        e5.setLowLatencyMode(true);
        e5.setSizeMm(32);
        e5.setWeightGrams(5);
        productRepo.save(e5);

        createVariant(e5, "Black", "Gaming", 35);
        createVariant(e5, "Red", "Gaming", 25);
        createVariant(e5, "Electric Blue", "Gaming", 20);
        createCodec(e5, "AAC");
        createCodec(e5, "SBC");

        // 6 - WaveBuds
        EarBudProduct e6 = new EarBudProduct();
        e6.setName("WaveBuds");
        e6.setCategory(earbudsCat);
        e6.setLabel("Travel");
        e6.setTrending(false);
        e6.setBestSeller(false);
        e6.setPrice(new BigDecimal("5299.00"));
        e6.setDriverSizeMm(10);
        e6.setFrequencyResponse("20Hz-20kHz");
        e6.setBluetoothVersion("5.2");
        e6.setMic(true);
        e6.setNoiseCancellation("ENC");
        e6.setBatteryLifeEarbudsHours(8);
        e6.setBatteryLifeCaseHours(30);
        e6.setChargingTimeHours(1);
        e6.setChargingPort("Type-C");
        e6.setWaterResistanceRating("IPX5");
        e6.setLowLatencyMode(false);
        e6.setSizeMm(30);
        e6.setWeightGrams(4);
        productRepo.save(e6);

        createVariant(e6, "Black", "Travel", 30);
        createVariant(e6, "Teal", "Travel", 25);
        createVariant(e6, "Grey", "Travel", 20);
        createCodec(e6, "AAC");
        createCodec(e6, "SBC");

        // 7 - AuraBuds
        EarBudProduct e7 = new EarBudProduct();
        e7.setName("AuraBuds");
        e7.setCategory(earbudsCat);
        e7.setLabel("Studio");
        e7.setTrending(false);
        e7.setBestSeller(false);
        e7.setPrice(new BigDecimal("6999.00"));
        e7.setDriverSizeMm(11);
        e7.setFrequencyResponse("20Hz-40kHz");
        e7.setBluetoothVersion("5.3");
        e7.setMic(true);
        e7.setNoiseCancellation("ANC");
        e7.setBatteryLifeEarbudsHours(7);
        e7.setBatteryLifeCaseHours(28);
        e7.setChargingTimeHours(2);
        e7.setChargingPort("Type-C");
        e7.setWaterResistanceRating("IPX4");
        e7.setLowLatencyMode(true);
        e7.setSizeMm(31);
        e7.setWeightGrams(5);
        productRepo.save(e7);

        createVariant(e7, "Black", "Studio", 30);
        createVariant(e7, "Silver", "Studio", 20);
        createVariant(e7, "Blue", "Studio", 20);
        createCodec(e7, "AAC");
        createCodec(e7, "SBC");
        createCodec(e7, "LDAC");

        // 8 - BeatSync Pods
        EarBudProduct e8 = new EarBudProduct();
        e8.setName("BeatSync Pods");
        e8.setCategory(earbudsCat);
        e8.setLabel("Active");
        e8.setTrending(false);
        e8.setBestSeller(false);
        e8.setPrice(new BigDecimal("5799.00"));
        e8.setDriverSizeMm(10);
        e8.setFrequencyResponse("20Hz-20kHz");
        e8.setBluetoothVersion("5.3");
        e8.setMic(true);
        e8.setNoiseCancellation("ENC");
        e8.setBatteryLifeEarbudsHours(6);
        e8.setBatteryLifeCaseHours(25);
        e8.setChargingTimeHours(1);
        e8.setChargingPort("Type-C");
        e8.setWaterResistanceRating("IPX4");
        e8.setLowLatencyMode(true);
        e8.setSizeMm(30);
        e8.setWeightGrams(4);
        productRepo.save(e8);

        createVariant(e8, "Black", "Sport", 35);
        createVariant(e8, "White", "Sport", 25);
        createVariant(e8, "Mint", "Sport", 20);
        createCodec(e8, "AAC");
        createCodec(e8, "SBC");

        // 9 - SonicLite Pods
        EarBudProduct e9 = new EarBudProduct();
        e9.setName("SonicLite Pods");
        e9.setCategory(earbudsCat);
        e9.setLabel("Lite");
        e9.setTrending(false);
        e9.setBestSeller(false);
        e9.setPrice(new BigDecimal("3499.00"));
        e9.setDriverSizeMm(8);
        e9.setFrequencyResponse("20Hz-20kHz");
        e9.setBluetoothVersion("5.1");
        e9.setMic(true);
        e9.setNoiseCancellation("NONE");
        e9.setBatteryLifeEarbudsHours(4);
        e9.setBatteryLifeCaseHours(16);
        e9.setChargingTimeHours(1);
        e9.setChargingPort("Type-C");
        e9.setWaterResistanceRating("IPX2");
        e9.setLowLatencyMode(false);
        e9.setSizeMm(28);
        e9.setWeightGrams(3);
        productRepo.save(e9);

        createVariant(e9, "Black", "Lite", 40);
        createVariant(e9, "White", "Lite", 30);
        createVariant(e9, "Pink", "Lite", 20);
        createCodec(e9, "SBC");

        // 10 - Pulse RhythmEdge
        EarBudProduct e10 = new EarBudProduct();
        e10.setName("Pulse RhythmEdge");
        e10.setCategory(earbudsCat);
        e10.setLabel("Pro");
        e10.setTrending(true);
        e10.setBestSeller(true);
        e10.setPrice(new BigDecimal("8999.00"));
        e10.setDriverSizeMm(12);
        e10.setFrequencyResponse("20Hz-40kHz");
        e10.setBluetoothVersion("5.3");
        e10.setMic(true);
        e10.setNoiseCancellation("ANC");
        e10.setBatteryLifeEarbudsHours(8);
        e10.setBatteryLifeCaseHours(32);
        e10.setChargingTimeHours(2);
        e10.setChargingPort("Type-C");
        e10.setWaterResistanceRating("IPX5");
        e10.setLowLatencyMode(true);
        e10.setSizeMm(32);
        e10.setWeightGrams(5);
        productRepo.save(e10);

        createVariant(e10, "Black", "Pro", 40);
        createVariant(e10, "Titanium", "Pro", 25);
        createVariant(e10, "Burgundy", "Pro", 20);
        createCodec(e10, "AAC");
        createCodec(e10, "SBC");
        createCodec(e10, "LDAC");
    }

    private void createCodec(EarBudProduct product, String codec) {
        EarbudCodec c = new EarbudCodec();
        c.setEarbudProduct(product);
        c.setCodecName(codec);
        earbudCodecRepo.save(c);
    }
}
