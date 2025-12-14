package com.pulse.ecommerce.config;




import com.pulse.ecommerce.model.Category;
import com.pulse.ecommerce.model.HeadPhoneProduct;
import com.pulse.ecommerce.repository.CategoryRepository;
import com.pulse.ecommerce.repository.ProductRepository;
import com.pulse.ecommerce.repository.ProductVariantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class HeadphoneDataSeeder extends BaseSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepo;
    private final ProductRepository productRepo;

    public HeadphoneDataSeeder(CategoryRepository categoryRepo,
                               ProductRepository productRepo,
                               ProductVariantRepository variantRepo) {
        super(variantRepo);
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

        public void run(String... args) {

            Category headphonesCat = categoryRepo.findByName("Headphones").orElse(null);
            if (headphonesCat == null) return;

            // OLD (bad for multiple seeders):
            // if (productRepo.count() > 0) return;

            // NEW: skip only if headphones already present
          if (productRepo.existsByCategory_Name("Headphones")) return;

            // ... rest of your h1..h10 code unchanged ...


        // 1 - HyperWave (Gaming)
        HeadPhoneProduct h1 = new HeadPhoneProduct();
        h1.setName("HyperWave");
        h1.setCategory(headphonesCat);
        h1.setLabel("Gaming");
        h1.setTrending(false);
        h1.setBestSeller(false);
        h1.setPrice(new BigDecimal("8999.00"));
        h1.setDriverSizeMm(50);
        h1.setFrequencyResponse("20Hz-20kHz");
        h1.setImpedanceOhm(32);
        h1.setBluetoothVersion("5.2");
        h1.setWireless(true);
        h1.setNoiseCancellation("ANC");
        h1.setMic(true);
        h1.setBatteryLifeHours(30);
        h1.setChargingPort("Type-C");
        h1.setAudioInput("3.5mm");
        h1.setWeightGrams(260);
        h1.setSizeMm(180);
        productRepo.save(h1);

        createVariant(h1, "Black", "Gaming", 20);
        createVariant(h1, "Red", "Gaming", 18);
        createVariant(h1, "Electric Blue", "Gaming", 15);

        // 2 - SonicCore (Classic)
        HeadPhoneProduct h2 = new HeadPhoneProduct();
        h2.setName("SonicCore");
        h2.setCategory(headphonesCat);
        h2.setLabel("Classic");
        h2.setTrending(false);
        h2.setBestSeller(false);
        h2.setPrice(new BigDecimal("7499.00"));
        h2.setDriverSizeMm(40);
        h2.setFrequencyResponse("20Hz-20kHz");
        h2.setImpedanceOhm(32);
        h2.setBluetoothVersion("5.0");
        h2.setWireless(true);
        h2.setNoiseCancellation("NONE");
        h2.setMic(true);
        h2.setBatteryLifeHours(20);
        h2.setChargingPort("Type-C");
        h2.setAudioInput("3.5mm");
        h2.setWeightGrams(240);
        h2.setSizeMm(175);
        productRepo.save(h2);

        createVariant(h2, "Black", "Classic", 25);
        createVariant(h2, "Silver", "Classic", 18);
        createVariant(h2, "Blue", "Classic", 15);

        // 3 - BassForge (Studio)
        HeadPhoneProduct h3 = new HeadPhoneProduct();
        h3.setName("BassForge");
        h3.setCategory(headphonesCat);
        h3.setLabel("Studio");
        h3.setTrending(false);
        h3.setBestSeller(false);
        h3.setPrice(new BigDecimal("7999.00"));
        h3.setDriverSizeMm(45);
        h3.setFrequencyResponse("20Hz-20kHz");
        h3.setImpedanceOhm(32);
        h3.setBluetoothVersion("5.2");
        h3.setWireless(true);
        h3.setNoiseCancellation("ENC");
        h3.setMic(true);
        h3.setBatteryLifeHours(25);
        h3.setChargingPort("Type-C");
        h3.setAudioInput("3.5mm");
        h3.setWeightGrams(255);
        h3.setSizeMm(180);
        productRepo.save(h3);

        createVariant(h3, "Black", "Studio", 20);
        createVariant(h3, "Gunmetal", "Studio", 15);
        createVariant(h3, "Copper", "Studio", 12);

        // 4 - GameStorm (Gaming)
        HeadPhoneProduct h4 = new HeadPhoneProduct();
        h4.setName("GameStorm");
        h4.setCategory(headphonesCat);
        h4.setLabel("Gaming");
        h4.setTrending(true);
        h4.setBestSeller(false);
        h4.setPrice(new BigDecimal("8499.00"));
        h4.setDriverSizeMm(50);
        h4.setFrequencyResponse("20Hz-20kHz");
        h4.setImpedanceOhm(32);
        h4.setBluetoothVersion("5.2");
        h4.setWireless(true);
        h4.setNoiseCancellation("ENC");
        h4.setMic(true);
        h4.setBatteryLifeHours(28);
        h4.setChargingPort("Type-C");
        h4.setAudioInput("3.5mm");
        h4.setWeightGrams(270);
        h4.setSizeMm(185);
        productRepo.save(h4);

        createVariant(h4, "Black", "Gaming RGB", 22);
        createVariant(h4, "Neon Green", "Gaming RGB", 18);
        createVariant(h4, "Purple", "Gaming RGB", 15);

        // 5 - StreetBeat (Active)
        HeadPhoneProduct h5 = new HeadPhoneProduct();
        h5.setName("StreetBeat");
        h5.setCategory(headphonesCat);
        h5.setLabel("Active");
        h5.setTrending(false);
        h5.setBestSeller(false);
        h5.setPrice(new BigDecimal("5999.00"));
        h5.setDriverSizeMm(40);
        h5.setFrequencyResponse("20Hz-20kHz");
        h5.setImpedanceOhm(32);
        h5.setBluetoothVersion("5.1");
        h5.setWireless(true);
        h5.setNoiseCancellation("NONE");
        h5.setMic(true);
        h5.setBatteryLifeHours(18);
        h5.setChargingPort("Type-C");
        h5.setAudioInput("3.5mm");
        h5.setWeightGrams(220);
        h5.setSizeMm(170);
        productRepo.save(h5);

        createVariant(h5, "Black", "Active", 25);
        createVariant(h5, "Yellow", "Active", 20);
        createVariant(h5, "Teal", "Active", 18);

        // 6 - CloudTone (Classic)
        HeadPhoneProduct h6 = new HeadPhoneProduct();
        h6.setName("CloudTone");
        h6.setCategory(headphonesCat);
        h6.setLabel("Classic");
        h6.setTrending(false);
        h6.setBestSeller(false);
        h6.setPrice(new BigDecimal("6499.00"));
        h6.setDriverSizeMm(40);
        h6.setFrequencyResponse("20Hz-20kHz");
        h6.setImpedanceOhm(32);
        h6.setBluetoothVersion("5.1");
        h6.setWireless(true);
        h6.setNoiseCancellation("ENC");
        h6.setMic(true);
        h6.setBatteryLifeHours(22);
        h6.setChargingPort("Type-C");
        h6.setAudioInput("3.5mm");
        h6.setWeightGrams(230);
        h6.setSizeMm(175);
        productRepo.save(h6);

        createVariant(h6, "Black", "Classic", 20);
        createVariant(h6, "White", "Classic", 18);
        createVariant(h6, "Champagne Gold", "Classic", 12);

        // 7 - FocusLine Pro (Studio)
        HeadPhoneProduct h7 = new HeadPhoneProduct();
        h7.setName("FocusLine Pro");
        h7.setCategory(headphonesCat);
        h7.setLabel("Studio");
        h7.setTrending(false);
        h7.setBestSeller(false);
        h7.setPrice(new BigDecimal("8999.00"));
        h7.setDriverSizeMm(45);
        h7.setFrequencyResponse("20Hz-20kHz");
        h7.setImpedanceOhm(32);
        h7.setBluetoothVersion("5.2");
        h7.setWireless(true);
        h7.setNoiseCancellation("ANC");
        h7.setMic(true);
        h7.setBatteryLifeHours(30);
        h7.setChargingPort("Type-C");
        h7.setAudioInput("3.5mm");
        h7.setWeightGrams(260);
        h7.setSizeMm(180);
        productRepo.save(h7);

        createVariant(h7, "Black", "Studio Pro", 18);
        createVariant(h7, "Silver", "Studio Pro", 15);
        createVariant(h7, "Navy", "Studio Pro", 12);

        // 8 - NoiseGuard ANC (Pro)
        HeadPhoneProduct h8 = new HeadPhoneProduct();
        h8.setName("NoiseGuard ANC");
        h8.setCategory(headphonesCat);
        h8.setLabel("Pro");
        h8.setTrending(true);
        h8.setBestSeller(true);
        h8.setPrice(new BigDecimal("10499.00"));
        h8.setDriverSizeMm(40);
        h8.setFrequencyResponse("20Hz-20kHz");
        h8.setImpedanceOhm(32);
        h8.setBluetoothVersion("5.3");
        h8.setWireless(true);
        h8.setNoiseCancellation("ANC");
        h8.setMic(true);
        h8.setBatteryLifeHours(35);
        h8.setChargingPort("Type-C");
        h8.setAudioInput("3.5mm");
        h8.setWeightGrams(250);
        h8.setSizeMm(180);
        productRepo.save(h8);

        createVariant(h8, "Black", "Pro ANC", 20);
        createVariant(h8, "Graphite", "Pro ANC", 15);
        createVariant(h8, "White", "Pro ANC", 15);

        // 9 - RhythmWave (Lite)
        HeadPhoneProduct h9 = new HeadPhoneProduct();
        h9.setName("RhythmWave");
        h9.setCategory(headphonesCat);
        h9.setLabel("Lite");
        h9.setTrending(false);
        h9.setBestSeller(false);
        h9.setPrice(new BigDecimal("5499.00"));
        h9.setDriverSizeMm(40);
        h9.setFrequencyResponse("20Hz-20kHz");
        h9.setImpedanceOhm(32);
        h9.setBluetoothVersion("5.0");
        h9.setWireless(true);
        h9.setNoiseCancellation("NONE");
        h9.setMic(true);
        h9.setBatteryLifeHours(16);
        h9.setChargingPort("Type-C");
        h9.setAudioInput("3.5mm");
        h9.setWeightGrams(210);
        h9.setSizeMm(170);
        productRepo.save(h9);

        createVariant(h9, "Black", "Lite", 25);
        createVariant(h9, "Blue", "Lite", 20);
        createVariant(h9, "Red", "Lite", 18);

        // 10 - Pulse AeroSound (Active)
        HeadPhoneProduct h10 = new HeadPhoneProduct();
        h10.setName("Pulse AeroSound");
        h10.setCategory(headphonesCat);
        h10.setLabel("Active");
        h10.setTrending(false);
        h10.setBestSeller(false);
        h10.setPrice(new BigDecimal("7999.00"));
        h10.setDriverSizeMm(40);
        h10.setFrequencyResponse("20Hz-20kHz");
        h10.setImpedanceOhm(32);
        h10.setBluetoothVersion("5.2");
        h10.setWireless(true);
        h10.setNoiseCancellation("ENC");
        h10.setMic(true);
        h10.setBatteryLifeHours(24);
        h10.setChargingPort("Type-C");
        h10.setAudioInput("3.5mm");
        h10.setWeightGrams(235);
        h10.setSizeMm(178);
        productRepo.save(h10);

        createVariant(h10, "Black", "Active", 22);
        createVariant(h10, "White", "Active", 18);
        createVariant(h10, "Green", "Active", 15);
    }
}
