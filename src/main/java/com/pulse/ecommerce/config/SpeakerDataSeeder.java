package com.pulse.ecommerce.config;

import com.pulse.ecommerce.model.Category;
import com.pulse.ecommerce.model.SpeakerProduct;
import com.pulse.ecommerce.repository.CategoryRepository;
import com.pulse.ecommerce.repository.ProductRepository;
import com.pulse.ecommerce.repository.ProductVariantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SpeakerDataSeeder extends BaseSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepo;
    private final ProductRepository productRepo;

    public SpeakerDataSeeder(CategoryRepository categoryRepo,
                             ProductRepository productRepo,
                             ProductVariantRepository variantRepo) {
        super(variantRepo);
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

    @Override
    public void run(String... args) {

        Category speakersCat = categoryRepo.findByName("Speakers").orElse(null);
        if (speakersCat == null) return;

        // Skip only if SPEAKERS already exist
      if (productRepo.existsByCategory_Name("Speakers")) return;

        // 1 - ThunderBox (Party)
        SpeakerProduct s1 = new SpeakerProduct();
        s1.setName("ThunderBox");
        s1.setCategory(speakersCat);
        s1.setLabel("Party");
        s1.setTrending(false);
        s1.setBestSeller(false);
        s1.setPrice(new BigDecimal("5999.00"));
        s1.setOutputPowerWatt(20);
        s1.setFrequencyResponse("80Hz-18kHz");
        s1.setBluetoothVersion("5.0");
        s1.setBatteryLifeHours(10);
        s1.setChargingPort("Type-C");
        s1.setWaterResistanceRating("IPX5");
        s1.setStereoPairingSupport(true);
        s1.setWeightGrams(800);
        s1.setDriverSizeMm(52);
        s1.setSizeMm(210);
        productRepo.save(s1);

        createVariant(s1, "Black", "Party", 22);
        createVariant(s1, "Blue", "Party", 18);
        createVariant(s1, "Neon Green", "Party", 15);

        // 2 - GlowBeat (Travel)
        SpeakerProduct s2 = new SpeakerProduct();
        s2.setName("GlowBeat");
        s2.setCategory(speakersCat);
        s2.setLabel("Travel");
        s2.setTrending(false);
        s2.setBestSeller(false);
        s2.setPrice(new BigDecimal("4499.00"));
        s2.setOutputPowerWatt(10);
        s2.setFrequencyResponse("90Hz-18kHz");
        s2.setBluetoothVersion("5.3");
        s2.setBatteryLifeHours(12);
        s2.setChargingPort("Type-C");
        s2.setWaterResistanceRating("IPX7");
        s2.setStereoPairingSupport(false);
        s2.setWeightGrams(500);
        s2.setDriverSizeMm(45);
        s2.setSizeMm(170);
        productRepo.save(s2);

        createVariant(s2, "Black", "Travel", 30);
        createVariant(s2, "Teal", "Travel", 22);
        createVariant(s2, "Purple", "Travel", 18);

        // 3 - BassBrick (Party)
        SpeakerProduct s3 = new SpeakerProduct();
        s3.setName("BassBrick");
        s3.setCategory(speakersCat);
        s3.setLabel("Party");
        s3.setTrending(false);
        s3.setBestSeller(false);
        s3.setPrice(new BigDecimal("6499.00"));
        s3.setOutputPowerWatt(24);
        s3.setFrequencyResponse("70Hz-18kHz");
        s3.setBluetoothVersion("5.0");
        s3.setBatteryLifeHours(9);
        s3.setChargingPort("Type-C");
        s3.setWaterResistanceRating("IPX5");
        s3.setStereoPairingSupport(true);
        s3.setWeightGrams(900);
        s3.setDriverSizeMm(55);
        s3.setSizeMm(230);
        productRepo.save(s3);

        createVariant(s3, "Black", "Party", 24);
        createVariant(s3, "Red", "Party", 18);
        createVariant(s3, "Orange", "Party", 16);

        // 4 - WaveLink (Travel)
        SpeakerProduct s4 = new SpeakerProduct();
        s4.setName("WaveLink");
        s4.setCategory(speakersCat);
        s4.setLabel("Travel");
        s4.setTrending(false);
        s4.setBestSeller(false);
        s4.setPrice(new BigDecimal("4999.00"));
        s4.setOutputPowerWatt(12);
        s4.setFrequencyResponse("90Hz-18kHz");
        s4.setBluetoothVersion("5.1");
        s4.setBatteryLifeHours(11);
        s4.setChargingPort("Type-C");
        s4.setWaterResistanceRating("IPX6");
        s4.setStereoPairingSupport(false);
        s4.setWeightGrams(550);
        s4.setDriverSizeMm(45);
        s4.setSizeMm(180);
        productRepo.save(s4);

        createVariant(s4, "Black", "Travel", 26);
        createVariant(s4, "Grey", "Travel", 20);
        createVariant(s4, "Navy", "Travel", 18);

        // 5 - EchoCube (Classic)
        SpeakerProduct s5 = new SpeakerProduct();
        s5.setName("EchoCube");
        s5.setCategory(speakersCat);
        s5.setLabel("Classic");
        s5.setTrending(false);
        s5.setBestSeller(false);
        s5.setPrice(new BigDecimal("3999.00"));
        s5.setOutputPowerWatt(8);
        s5.setFrequencyResponse("100Hz-18kHz");
        s5.setBluetoothVersion("5.0");
        s5.setBatteryLifeHours(8);
        s5.setChargingPort("Type-C");
        s5.setWaterResistanceRating("IPX4");
        s5.setStereoPairingSupport(false);
        s5.setWeightGrams(450);
        s5.setDriverSizeMm(40);
        s5.setSizeMm(160);
        productRepo.save(s5);

        createVariant(s5, "Black", "Desk", 20);
        createVariant(s5, "White", "Desk", 18);
        createVariant(s5, "Silver", "Desk", 15);

        // 6 - StreetBoom (Party)
        SpeakerProduct s6 = new SpeakerProduct();
        s6.setName("StreetBoom");
        s6.setCategory(speakersCat);
        s6.setLabel("Party");
        s6.setTrending(true);
        s6.setBestSeller(false);
        s6.setPrice(new BigDecimal("6999.00"));
        s6.setOutputPowerWatt(30);
        s6.setFrequencyResponse("70Hz-18kHz");
        s6.setBluetoothVersion("5.1");
        s6.setBatteryLifeHours(10);
        s6.setChargingPort("Type-C");
        s6.setWaterResistanceRating("IPX5");
        s6.setStereoPairingSupport(true);
        s6.setWeightGrams(1100);
        s6.setDriverSizeMm(60);
        s6.setSizeMm(250);
        productRepo.save(s6);

        createVariant(s6, "Black", "Outdoor Party", 24);
        createVariant(s6, "Camo Green", "Outdoor Party", 18);
        createVariant(s6, "Orange", "Outdoor Party", 16);

        // 7 - AquaBeat (Active)
        SpeakerProduct s7 = new SpeakerProduct();
        s7.setName("AquaBeat");
        s7.setCategory(speakersCat);
        s7.setLabel("Active");
        s7.setTrending(false);
        s7.setBestSeller(false);
        s7.setPrice(new BigDecimal("4299.00"));
        s7.setOutputPowerWatt(10);
        s7.setFrequencyResponse("90Hz-18kHz");
        s7.setBluetoothVersion("5.0");
        s7.setBatteryLifeHours(10);
        s7.setChargingPort("Type-C");
        s7.setWaterResistanceRating("IPX7");
        s7.setStereoPairingSupport(false);
        s7.setWeightGrams(480);
        s7.setDriverSizeMm(45);
        s7.setSizeMm(170);
        productRepo.save(s7);

        createVariant(s7, "Black", "Waterproof", 22);
        createVariant(s7, "Cyan", "Waterproof", 18);
        createVariant(s7, "Yellow", "Waterproof", 16);

        // 8 - VibeBar (Studio / soundbar)
        SpeakerProduct s8 = new SpeakerProduct();
        s8.setName("VibeBar");
        s8.setCategory(speakersCat);
        s8.setLabel("Studio");
        s8.setTrending(false);
        s8.setBestSeller(false);
        s8.setPrice(new BigDecimal("7499.00"));
        s8.setOutputPowerWatt(40);
        s8.setFrequencyResponse("60Hz-18kHz");
        s8.setBluetoothVersion("5.0");
        s8.setBatteryLifeHours(0); // plugged soundbar
        s8.setChargingPort("Type-C");
        s8.setWaterResistanceRating("NONE");
        s8.setStereoPairingSupport(false);
        s8.setWeightGrams(1500);
        s8.setDriverSizeMm(60);
        s8.setSizeMm(400);
        productRepo.save(s8);

        createVariant(s8, "Black", "Studio Bar", 15);
        createVariant(s8, "Grey", "Studio Bar", 12);
        createVariant(s8, "White", "Studio Bar", 10);

        // 9 - PocketPulse (Travel)
        SpeakerProduct s9 = new SpeakerProduct();
        s9.setName("PocketPulse");
        s9.setCategory(speakersCat);
        s9.setLabel("Travel");
        s9.setTrending(false);
        s9.setBestSeller(false);
        s9.setPrice(new BigDecimal("3499.00"));
        s9.setOutputPowerWatt(5);
        s9.setFrequencyResponse("110Hz-18kHz");
        s9.setBluetoothVersion("5.1");
        s9.setBatteryLifeHours(7);
        s9.setChargingPort("Type-C");
        s9.setWaterResistanceRating("IPX4");
        s9.setStereoPairingSupport(false);
        s9.setWeightGrams(300);
        s9.setDriverSizeMm(35);
        s9.setSizeMm(140);
        productRepo.save(s9);

        createVariant(s9, "Black", "Mini", 30);
        createVariant(s9, "Red", "Mini", 24);
        createVariant(s9, "Blue", "Mini", 20);

        // 10 - SonicDock (Classic / desk dock)
        SpeakerProduct s10 = new SpeakerProduct();
        s10.setName("SonicDock");
        s10.setCategory(speakersCat);
        s10.setLabel("Classic");
        s10.setTrending(false);
        s10.setBestSeller(false);
        s10.setPrice(new BigDecimal("7999.00"));
        s10.setOutputPowerWatt(25);
        s10.setFrequencyResponse("70Hz-18kHz");
        s10.setBluetoothVersion("5.1");
        s10.setBatteryLifeHours(0); // desk dock
        s10.setChargingPort("Type-C");
        s10.setWaterResistanceRating("NONE");
        s10.setStereoPairingSupport(false);
        s10.setWeightGrams(1200);
        s10.setDriverSizeMm(55);
        s10.setSizeMm(260);
        productRepo.save(s10);

        createVariant(s10, "Black", "Desk Dock", 18);
        createVariant(s10, "Silver", "Desk Dock", 14);
        createVariant(s10, "White", "Desk Dock", 12);
    }
}
