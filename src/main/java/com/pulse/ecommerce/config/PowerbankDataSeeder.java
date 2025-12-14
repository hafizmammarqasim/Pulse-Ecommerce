package com.pulse.ecommerce.config;


import com.pulse.ecommerce.model.Category;
import com.pulse.ecommerce.model.PowerBankProduct;
import com.pulse.ecommerce.repository.CategoryRepository;
import com.pulse.ecommerce.repository.ProductRepository;
import com.pulse.ecommerce.repository.ProductVariantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PowerbankDataSeeder extends BaseSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepo;
    private final ProductRepository productRepo;

    public PowerbankDataSeeder(CategoryRepository categoryRepo,
                               ProductRepository productRepo,
                               ProductVariantRepository variantRepo) {
        super(variantRepo);
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

    @Override
    public void run(String... args) {

        Category powerbanksCat = categoryRepo.findByName("Powerbanks").orElse(null);
        if (powerbanksCat == null) return;

        // OLD:
        // if (productRepo.count() > 0) return;

        // NEW: skip only if powerbanks already exist
        if (productRepo.existsByCategory_Name("Powerbanks")) return;

        // ... rest of pb1..pb10 code unchanged ...


    // 1 - VoltEdge (Rugged)
        PowerBankProduct pb1 = new PowerBankProduct();
        pb1.setName("VoltEdge");
        pb1.setCategory(powerbanksCat);
        pb1.setLabel("Rugged");
        pb1.setTrending(false);
        pb1.setBestSeller(false);
        pb1.setPrice(new BigDecimal("3499.00"));
        pb1.setCapacityMah(10000);
        pb1.setMaxOutputWatt(22);
        pb1.setInputPort("Type-C");
        pb1.setFastChargingSupport(true);
        pb1.setBatteryType("Li-Po");
        pb1.setChargingTimeHours(3);
        pb1.setWeightGrams(230);
        pb1.setDimensions("140x70x15mm");
        productRepo.save(pb1);

        createVariant(pb1, "Black", "Rugged", 40);
        createVariant(pb1, "Camouflage Green", "Rugged", 30);
        createVariant(pb1, "Desert Sand", "Rugged", 25);

        // 2 - PowerSync (Travel)
        PowerBankProduct pb2 = new PowerBankProduct();
        pb2.setName("PowerSync");
        pb2.setCategory(powerbanksCat);
        pb2.setLabel("Travel");
        pb2.setTrending(false);
        pb2.setBestSeller(false);
        pb2.setPrice(new BigDecimal("2999.00"));
        pb2.setCapacityMah(20000);
        pb2.setMaxOutputWatt(30);
        pb2.setInputPort("Type-C");
        pb2.setFastChargingSupport(true);
        pb2.setBatteryType("Li-Po");
        pb2.setChargingTimeHours(4);
        pb2.setWeightGrams(350);
        pb2.setDimensions("150x72x18mm");
        productRepo.save(pb2);

        createVariant(pb2, "White", "Travel", 35);
        createVariant(pb2, "Black", "Travel", 30);
        createVariant(pb2, "Navy Blue", "Travel", 25);

        // 3 - ChargeCore (Classic)
        PowerBankProduct pb3 = new PowerBankProduct();
        pb3.setName("ChargeCore");
        pb3.setCategory(powerbanksCat);
        pb3.setLabel("Classic");
        pb3.setTrending(false);
        pb3.setBestSeller(false);
        pb3.setPrice(new BigDecimal("2499.00"));
        pb3.setCapacityMah(10000);
        pb3.setMaxOutputWatt(18);
        pb3.setInputPort("Type-C");
        pb3.setFastChargingSupport(true);
        pb3.setBatteryType("Li-ion");
        pb3.setChargingTimeHours(3);
        pb3.setWeightGrams(220);
        pb3.setDimensions("135x68x14mm");
        productRepo.save(pb3);

        createVariant(pb3, "Black", "Classic", 40);
        createVariant(pb3, "Grey", "Classic", 30);
        createVariant(pb3, "Blue", "Classic", 25);

        // 4 - NanoBoost (Slim)
        PowerBankProduct pb4 = new PowerBankProduct();
        pb4.setName("NanoBoost");
        pb4.setCategory(powerbanksCat);
        pb4.setLabel("Slim");
        pb4.setTrending(false);
        pb4.setBestSeller(false);
        pb4.setPrice(new BigDecimal("2299.00"));
        pb4.setCapacityMah(5000);
        pb4.setMaxOutputWatt(20);
        pb4.setInputPort("Type-C");
        pb4.setFastChargingSupport(true);
        pb4.setBatteryType("Li-Po");
        pb4.setChargingTimeHours(2);
        pb4.setWeightGrams(150);
        pb4.setDimensions("125x65x11mm");
        productRepo.save(pb4);

        createVariant(pb4, "Black", "Slim", 35);
        createVariant(pb4, "White", "Slim", 28);
        createVariant(pb4, "Rose Gold", "Slim", 20);

        // 5 - TurboFlow (Pro)
        PowerBankProduct pb5 = new PowerBankProduct();
        pb5.setName("TurboFlow");
        pb5.setCategory(powerbanksCat);
        pb5.setLabel("Pro");
        pb5.setTrending(false);
        pb5.setBestSeller(false);
        pb5.setPrice(new BigDecimal("3999.00"));
        pb5.setCapacityMah(15000);
        pb5.setMaxOutputWatt(45);
        pb5.setInputPort("Type-C");
        pb5.setFastChargingSupport(true);
        pb5.setBatteryType("Li-Po");
        pb5.setChargingTimeHours(3);
        pb5.setWeightGrams(260);
        pb5.setDimensions("145x70x15mm");
        productRepo.save(pb5);

        createVariant(pb5, "Black", "Pro", 30);
        createVariant(pb5, "Blue", "Pro", 24);
        createVariant(pb5, "Red", "Pro", 20);

        // 6 - DuoCharge (Classic)
        PowerBankProduct pb6 = new PowerBankProduct();
        pb6.setName("DuoCharge");
        pb6.setCategory(powerbanksCat);
        pb6.setLabel("Classic");
        pb6.setTrending(false);
        pb6.setBestSeller(false);
        pb6.setPrice(new BigDecimal("2699.00"));
        pb6.setCapacityMah(12000);
        pb6.setMaxOutputWatt(24);
        pb6.setInputPort("Type-C");
        pb6.setFastChargingSupport(true);
        pb6.setBatteryType("Li-ion");
        pb6.setChargingTimeHours(3);
        pb6.setWeightGrams(240);
        pb6.setDimensions("140x70x15mm");
        productRepo.save(pb6);

        createVariant(pb6, "Black", "Dual Port", 32);
        createVariant(pb6, "White", "Dual Port", 26);
        createVariant(pb6, "Navy", "Dual Port", 22);

        // 7 - TrailCharge (Rugged)
        PowerBankProduct pb7 = new PowerBankProduct();
        pb7.setName("TrailCharge");
        pb7.setCategory(powerbanksCat);
        pb7.setLabel("Rugged");
        pb7.setTrending(false);
        pb7.setBestSeller(false);
        pb7.setPrice(new BigDecimal("3799.00"));
        pb7.setCapacityMah(10000);
        pb7.setMaxOutputWatt(20);
        pb7.setInputPort("Type-C");
        pb7.setFastChargingSupport(true);
        pb7.setBatteryType("Li-Po");
        pb7.setChargingTimeHours(4);
        pb7.setWeightGrams(260);
        pb7.setDimensions("150x75x17mm");
        productRepo.save(pb7);

        createVariant(pb7, "Black", "Outdoor", 28);
        createVariant(pb7, "Army Green", "Outdoor", 22);
        createVariant(pb7, "Orange", "Outdoor", 20);

        // 8 - MetroPack (Travel)
        PowerBankProduct pb8 = new PowerBankProduct();
        pb8.setName("MetroPack");
        pb8.setCategory(powerbanksCat);
        pb8.setLabel("Travel");
        pb8.setTrending(false);
        pb8.setBestSeller(false);
        pb8.setPrice(new BigDecimal("2599.00"));
        pb8.setCapacityMah(10000);
        pb8.setMaxOutputWatt(22);
        pb8.setInputPort("Type-C");
        pb8.setFastChargingSupport(true);
        pb8.setBatteryType("Li-ion");
        pb8.setChargingTimeHours(3);
        pb8.setWeightGrams(210);
        pb8.setDimensions("135x68x14mm");
        productRepo.save(pb8);

        createVariant(pb8, "Black", "Travel", 34);
        createVariant(pb8, "Blue", "Travel", 26);
        createVariant(pb8, "Red", "Travel", 22);

        // 9 - HyperCell (Max)
        PowerBankProduct pb9 = new PowerBankProduct();
        pb9.setName("HyperCell");
        pb9.setCategory(powerbanksCat);
        pb9.setLabel("Max");
        pb9.setTrending(true);
        pb9.setBestSeller(false);
        pb9.setPrice(new BigDecimal("4499.00"));
        pb9.setCapacityMah(30000);
        pb9.setMaxOutputWatt(65);
        pb9.setInputPort("Type-C");
        pb9.setFastChargingSupport(true);
        pb9.setBatteryType("Li-Po");
        pb9.setChargingTimeHours(5);
        pb9.setWeightGrams(520);
        pb9.setDimensions("165x80x22mm");
        productRepo.save(pb9);

        createVariant(pb9, "Black", "Max", 24);
        createVariant(pb9, "Graphite", "Max", 20);
        createVariant(pb9, "Blue", "Max", 18);

        // 10 - SnapLink (Slim / Mag-style)
        PowerBankProduct pb10 = new PowerBankProduct();
        pb10.setName("SnapLink");
        pb10.setCategory(powerbanksCat);
        pb10.setLabel("Slim");
        pb10.setTrending(false);
        pb10.setBestSeller(false);
        pb10.setPrice(new BigDecimal("3299.00"));
        pb10.setCapacityMah(10000);
        pb10.setMaxOutputWatt(20);
        pb10.setInputPort("Type-C");
        pb10.setFastChargingSupport(true);
        pb10.setBatteryType("Li-Po");
        pb10.setChargingTimeHours(3);
        pb10.setWeightGrams(200);
        pb10.setDimensions("135x68x13mm");
        productRepo.save(pb10);

        createVariant(pb10, "White", "Mag", 30);
        createVariant(pb10, "Black", "Mag", 26);
        createVariant(pb10, "Purple", "Mag", 20);
    }
}
