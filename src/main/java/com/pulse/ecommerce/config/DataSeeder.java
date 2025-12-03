package com.pulse.ecommerce.config;
import com.pulse.ecommerce.model.*;
import com.pulse.ecommerce.repository.CategoryRepository;
import com.pulse.ecommerce.repository.ProductRepository;
import com.pulse.ecommerce.repository.ProductVariantRepository;
import com.pulse.ecommerce.repository.EarBudCodecRepository;
import com.pulse.ecommerce.repository.SpeakerInputOptionRepository;
import com.pulse.ecommerce.repository.PowerBankOutputPortRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataSeeder implements CommandLineRunner {


    private final CategoryRepository categoryRepo;
    private final ProductRepository productRepo;
    private final ProductVariantRepository variantRepo;
    private final EarBudCodecRepository earbudCodecRepo;
    private final SpeakerInputOptionRepository speakerInputRepo;
    private final PowerBankOutputPortRepository powerbankPortRepo;

    public DataSeeder(CategoryRepository categoryRepo,
                      ProductRepository productRepo,
                      ProductVariantRepository variantRepo,
                      EarBudCodecRepository earbudCodecRepo,
                      SpeakerInputOptionRepository speakerInputRepo,
                      PowerBankOutputPortRepository powerbankPortRepo) {
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
        this.variantRepo = variantRepo;
        this.earbudCodecRepo = earbudCodecRepo;
        this.speakerInputRepo = speakerInputRepo;
        this.powerbankPortRepo = powerbankPortRepo;
    }

    @Override
    public void run(String... args) {

        // Base case: do not re-seed if products already exist
        if (productRepo.count() > 0) {
            return;
        }

        // =========================================
        // 1. Seed Categories
        // =========================================
        Category earbudsCat = new Category();
        earbudsCat.setName("Earbuds");
        earbudsCat.setDescription("True wireless earbuds for music, calls, and workouts.");
        categoryRepo.save(earbudsCat);

        Category headphonesCat = new Category();
        headphonesCat.setName("Headphones");
        headphonesCat.setDescription("On-ear and over-ear headphones for immersive sound.");
        categoryRepo.save(headphonesCat);

        Category speakersCat = new Category();
        speakersCat.setName("Speakers");
        speakersCat.setDescription("Portable and home speakers for music and parties.");
        categoryRepo.save(speakersCat);

        Category watchesCat = new Category();
        watchesCat.setName("Watches");
        watchesCat.setDescription("Smartwatches for fitness tracking and notifications.");
        categoryRepo.save(watchesCat);

        Category powerbanksCat = new Category();
        powerbanksCat.setName("Powerbanks");
        powerbanksCat.setDescription("Portable chargers to keep your devices powered.");
        categoryRepo.save(powerbanksCat);

        // =========================================
        // 2. Earbuds (JOINED inheritance + codecs)
        // =========================================

        // Product 1 - Pulse AirBeat X (earbud)
        EarBudProduct p1 = new EarBudProduct();
        p1.setName("Pulse AirBeat X");
        p1.setPrice(new BigDecimal("4999.00"));
        p1.setCategory(earbudsCat);
        p1.setLabel("Active");
        p1.setTrending(false);
        p1.setBestSeller(false);
        // earbud specs
        p1.setDriverSizeMm(10);
        p1.setFrequencyResponse("20Hz-20kHz");
        p1.setBluetoothVersion("5.3");
        p1.setMic(true);
        p1.setNoiseCancellation("ENC");
        p1.setBatteryLifeEarbudsHours(6);
        p1.setBatteryLifeCaseHours(24);
        p1.setChargingTimeHours(1);
        p1.setChargingPort("Type-C");
        p1.setWaterResistanceRating("IPX4");
        p1.setLowLatencyMode(true);
        p1.setSizeMm(30);
        p1.setWeightGrams(4);
        productRepo.save(p1); // saves product + earbud_product row

        // Variants for AirBeat X
        ProductVariant v1a = new ProductVariant();
        v1a.setProduct(p1);
        v1a.setColor("Black");
        v1a.setVariantType("Standard");
        v1a.setStockQuantity(50);
        variantRepo.save(v1a);

        ProductVariant v1b = new ProductVariant();
        v1b.setProduct(p1);
        v1b.setColor("White");
        v1b.setVariantType("Standard");
        v1b.setStockQuantity(30);
        variantRepo.save(v1b);

        // Normalized codecs for AirBeat X (AAC, SBC)
        EarbudCodec p1c1 = new EarbudCodec();
        p1c1.setEarbudProduct(p1);
        p1c1.setCodecName("AAC");
        earbudCodecRepo.save(p1c1);

        EarbudCodec p1c2 = new EarbudCodec();
        p1c2.setEarbudProduct(p1);
        p1c2.setCodecName("SBC");
        earbudCodecRepo.save(p1c2);

        // Product 2 - Pulse NeoBuds Pro (earbud)
        EarBudProduct p2 = new EarBudProduct();
        p2.setName("Pulse NeoBuds Pro");
        p2.setPrice(new BigDecimal("6999.00"));
        p2.setCategory(earbudsCat);
        p2.setLabel("Studio");
        p2.setTrending(false);
        p2.setBestSeller(false);
        // earbud specs
        p2.setDriverSizeMm(11);
        p2.setFrequencyResponse("20Hz-40kHz");
        p2.setBluetoothVersion("5.3");
        p2.setMic(true);
        p2.setNoiseCancellation("ANC");
        p2.setBatteryLifeEarbudsHours(7);
        p2.setBatteryLifeCaseHours(28);
        p2.setChargingTimeHours(2);
        p2.setChargingPort("Type-C");
        p2.setWaterResistanceRating("IPX5");
        p2.setLowLatencyMode(true);
        p2.setSizeMm(32);
        p2.setWeightGrams(5);
        productRepo.save(p2);

        // Variants for NeoBuds Pro
        ProductVariant v2a = new ProductVariant();
        v2a.setProduct(p2);
        v2a.setColor("Black");
        v2a.setVariantType("Pro");
        v2a.setStockQuantity(40);
        variantRepo.save(v2a);

        ProductVariant v2b = new ProductVariant();
        v2b.setProduct(p2);
        v2b.setColor("Blue");
        v2b.setVariantType("Pro");
        v2b.setStockQuantity(25);
        variantRepo.save(v2b);

        // Normalized codecs for NeoBuds Pro (AAC, SBC, LDAC)
        EarbudCodec p2c1 = new EarbudCodec();
        p2c1.setEarbudProduct(p2);
        p2c1.setCodecName("AAC");
        earbudCodecRepo.save(p2c1);

        EarbudCodec p2c2 = new EarbudCodec();
        p2c2.setEarbudProduct(p2);
        p2c2.setCodecName("SBC");
        earbudCodecRepo.save(p2c2);

        EarbudCodec p2c3 = new EarbudCodec();
        p2c3.setEarbudProduct(p2);
        p2c3.setCodecName("LDAC");
        earbudCodecRepo.save(p2c3);

        // =========================================
        // 3. Headphones (JOINED inheritance)
        // =========================================

        // Product 3 - Pulse HyperWave (headphone)
        HeadPhoneProduct p3 = new HeadPhoneProduct();
        p3.setName("Pulse HyperWave");
        p3.setPrice(new BigDecimal("8999.00"));
        p3.setCategory(headphonesCat);
        p3.setLabel("Gaming");
        p3.setTrending(false);
        p3.setBestSeller(false);
        // headphone specs
        p3.setDriverSizeMm(50);
        p3.setFrequencyResponse("20Hz-20kHz");
        p3.setImpedanceOhm(32);
        p3.setBluetoothVersion("5.2");
        p3.setWireless(true);
        p3.setNoiseCancellation("ANC");
        p3.setMic(true);
        p3.setBatteryLifeHours(30);
        p3.setChargingPort("Type-C");
        p3.setAudioInput("3.5mm");
        p3.setWeightGrams(260);
        p3.setSizeMm(180);
        productRepo.save(p3);

        ProductVariant v3a = new ProductVariant();
        v3a.setProduct(p3);
        v3a.setColor("Black");
        v3a.setVariantType("Gaming");
        v3a.setStockQuantity(20);
        variantRepo.save(v3a);

        ProductVariant v3b = new ProductVariant();
        v3b.setProduct(p3);
        v3b.setColor("Red");
        v3b.setVariantType("Gaming");
        v3b.setStockQuantity(15);
        variantRepo.save(v3b);

        // Product 4 - Pulse SonicCore (headphone)
        HeadPhoneProduct p4 = new HeadPhoneProduct();
        p4.setName("Pulse SonicCore");
        p4.setPrice(new BigDecimal("7499.00"));
        p4.setCategory(headphonesCat);
        p4.setLabel("Classic");
        p4.setTrending(false);
        p4.setBestSeller(false);
        // headphone specs
        p4.setDriverSizeMm(40);
        p4.setFrequencyResponse("20Hz-20kHz");
        p4.setImpedanceOhm(32);
        p4.setBluetoothVersion("5.0");
        p4.setWireless(true);
        p4.setNoiseCancellation("NONE");
        p4.setMic(true);
        p4.setBatteryLifeHours(20);
        p4.setChargingPort("Type-C");
        p4.setAudioInput("3.5mm");
        p4.setWeightGrams(240);
        p4.setSizeMm(175);
        productRepo.save(p4);

        ProductVariant v4a = new ProductVariant();
        v4a.setProduct(p4);
        v4a.setColor("Black");
        v4a.setVariantType("Wireless");
        v4a.setStockQuantity(35);
        variantRepo.save(v4a);

        ProductVariant v4b = new ProductVariant();
        v4b.setProduct(p4);
        v4b.setColor("Silver");
        v4b.setVariantType("Wireless");
        v4b.setStockQuantity(18);
        variantRepo.save(v4b);

        // =========================================
        // 4. Speakers (JOINED inheritance + inputs)
        // =========================================

        // Product 5 - Pulse ThunderBox (speaker)
        SpeakerProduct p5 = new SpeakerProduct();
        p5.setName("Pulse ThunderBox");
        p5.setPrice(new BigDecimal("5999.00"));
        p5.setCategory(speakersCat);
        p5.setLabel("Party");
        p5.setTrending(false);
        p5.setBestSeller(false);
        // speaker specs
        p5.setOutputPowerWatt(20);
        p5.setFrequencyResponse("80Hz-18kHz");
        p5.setBluetoothVersion("5.0");
        p5.setBatteryLifeHours(10);
        p5.setChargingPort("Type-C");
        p5.setWaterResistanceRating("IPX5");
        p5.setStereoPairingSupport(true);
        p5.setWeightGrams(800);
        p5.setDriverSizeMm(52);
        p5.setSizeMm(210);
        productRepo.save(p5);

        ProductVariant v5a = new ProductVariant();
        v5a.setProduct(p5);
        v5a.setColor("Black");
        v5a.setVariantType("Party");
        v5a.setStockQuantity(22);
        variantRepo.save(v5a);

        ProductVariant v5b = new ProductVariant();
        v5b.setProduct(p5);
        v5b.setColor("Blue");
        v5b.setVariantType("Party");
        v5b.setStockQuantity(14);
        variantRepo.save(v5b);

        // Normalized input options for ThunderBox (Bluetooth, AUX)
        SpeakerInputOption p5i1 = new SpeakerInputOption();
        p5i1.setSpeakerProduct(p5);
        p5i1.setInputName("Bluetooth");
        speakerInputRepo.save(p5i1);

        SpeakerInputOption p5i2 = new SpeakerInputOption();
        p5i2.setSpeakerProduct(p5);
        p5i2.setInputName("AUX");
        speakerInputRepo.save(p5i2);

        // Product 6 - Pulse GlowBeat (speaker)
        SpeakerProduct p6 = new SpeakerProduct();
        p6.setName("Pulse GlowBeat");
        p6.setPrice(new BigDecimal("4499.00"));
        p6.setCategory(speakersCat);
        p6.setLabel("Travel");
        p6.setTrending(false);
        p6.setBestSeller(false);
        // speaker specs
        p6.setOutputPowerWatt(10);
        p6.setFrequencyResponse("90Hz-18kHz");
        p6.setBluetoothVersion("5.3");
        p6.setBatteryLifeHours(12);
        p6.setChargingPort("Type-C");
        p6.setWaterResistanceRating("IPX7");
        p6.setStereoPairingSupport(false);
        p6.setWeightGrams(500);
        p6.setDriverSizeMm(45);
        p6.setSizeMm(170);
        productRepo.save(p6);

        ProductVariant v6a = new ProductVariant();
        v6a.setProduct(p6);
        v6a.setColor("Black");
        v6a.setVariantType("Travel");
        v6a.setStockQuantity(30);
        variantRepo.save(v6a);

        ProductVariant v6b = new ProductVariant();
        v6b.setProduct(p6);
        v6b.setColor("Green");
        v6b.setVariantType("Travel");
        v6b.setStockQuantity(18);
        variantRepo.save(v6b);

        // Normalized input options for GlowBeat (Bluetooth only)
        SpeakerInputOption p6i1 = new SpeakerInputOption();
        p6i1.setSpeakerProduct(p6);
        p6i1.setInputName("Bluetooth");
        speakerInputRepo.save(p6i1);

        // =========================================
        // 5. Watches (JOINED inheritance)
        // =========================================

        // Product 7 - Pulse TimeFlex (watch)
        WatchProduct p7 = new WatchProduct();
        p7.setName("Pulse TimeFlex");
        p7.setPrice(new BigDecimal("7999.00"));
        p7.setCategory(watchesCat);
        p7.setLabel("Active");
        p7.setTrending(false);
        p7.setBestSeller(false);
        // watch specs
        p7.setDisplayType("AMOLED");
        p7.setDisplaySizeInch(1.78);
        p7.setResolution("368x448");
        p7.setBatteryCapacityMah(300);
        p7.setBatteryLifeDays(7);
        p7.setWaterResistanceRating("5ATM");
        p7.setHeartRateSensor(true);
        p7.setSpo2Sensor(true);
        p7.setSportsModesCount(100);
        p7.setGps(true);
        p7.setOsCompatibility("Android,iOS");
        p7.setStrapMaterial("Silicone");
        p7.setWeightGrams(45);
        productRepo.save(p7);

        ProductVariant v7a = new ProductVariant();
        v7a.setProduct(p7);
        v7a.setColor("Black");
        v7a.setVariantType("Sport");
        v7a.setStockQuantity(25);
        variantRepo.save(v7a);

        ProductVariant v7b = new ProductVariant();
        v7b.setProduct(p7);
        v7b.setColor("Orange");
        v7b.setVariantType("Sport");
        v7b.setStockQuantity(15);
        variantRepo.save(v7b);

        // Product 8 - Pulse SyncWave (watch)
        WatchProduct p8 = new WatchProduct();
        p8.setName("Pulse SyncWave");
        p8.setPrice(new BigDecimal("8999.00"));
        p8.setCategory(watchesCat);
        p8.setLabel("Classic");
        p8.setTrending(false);
        p8.setBestSeller(false);
        // watch specs
        p8.setDisplayType("AMOLED");
        p8.setDisplaySizeInch(1.9);
        p8.setResolution("410x502");
        p8.setBatteryCapacityMah(320);
        p8.setBatteryLifeDays(10);
        p8.setWaterResistanceRating("3ATM");
        p8.setHeartRateSensor(true);
        p8.setSpo2Sensor(true);
        p8.setSportsModesCount(60);
        p8.setGps(false);
        p8.setOsCompatibility("Android,iOS");
        p8.setStrapMaterial("Leather");
        p8.setWeightGrams(50);
        productRepo.save(p8);

        ProductVariant v8a = new ProductVariant();
        v8a.setProduct(p8);
        v8a.setColor("Black");
        v8a.setVariantType("Classic");
        v8a.setStockQuantity(20);
        variantRepo.save(v8a);

        ProductVariant v8b = new ProductVariant();
        v8b.setProduct(p8);
        v8b.setColor("Brown");
        v8b.setVariantType("Classic");
        v8b.setStockQuantity(12);
        variantRepo.save(v8b);

        // =========================================
        // 6. Powerbanks (JOINED inheritance + ports)
        // =========================================

        // Product 9 - Pulse VoltEdge (powerbank)
        PowerBankProduct p9 = new PowerBankProduct();
        p9.setName("Pulse VoltEdge");
        p9.setPrice(new BigDecimal("3499.00"));
        p9.setCategory(powerbanksCat);
        p9.setLabel("Rugged");
        p9.setTrending(false);
        p9.setBestSeller(false);
        // powerbank specs
        p9.setCapacityMah(10000);
        p9.setMaxOutputWatt(22);
        p9.setInputPort("Type-C");
        p9.setFastChargingSupport(true);
        p9.setBatteryType("Li-Po");
        p9.setChargingTimeHours(3);
        p9.setWeightGrams(230);
        p9.setDimensions("140x70x15mm");
        productRepo.save(p9);

        ProductVariant v9a = new ProductVariant();
        v9a.setProduct(p9);
        v9a.setColor("Black");
        v9a.setVariantType("Rugged");
        v9a.setStockQuantity(40);
        variantRepo.save(v9a);

        ProductVariant v9b = new ProductVariant();
        v9b.setProduct(p9);
        v9b.setColor("Camouflage");
        v9b.setVariantType("Rugged");
        v9b.setStockQuantity(20);
        variantRepo.save(v9b);

        // Normalized output ports for VoltEdge (2x USB-A, 1x Type-C)
        PowerbankOutputPort p9p1 = new PowerbankOutputPort();
        p9p1.setPowerbankProduct(p9);
        p9p1.setPortName("USB-A");
        p9p1.setPortCount(2);
        powerbankPortRepo.save(p9p1);

        PowerbankOutputPort p9p2 = new PowerbankOutputPort();
        p9p2.setPowerbankProduct(p9);
        p9p2.setPortName("Type-C");
        p9p2.setPortCount(1);
        powerbankPortRepo.save(p9p2);

        // Product 10 - Pulse PowerSync (powerbank)
        PowerBankProduct p10 = new PowerBankProduct();
        p10.setName("Pulse PowerSync");
        p10.setPrice(new BigDecimal("2999.00"));
        p10.setCategory(powerbanksCat);
        p10.setLabel("Travel");
        p10.setTrending(false);
        p10.setBestSeller(false);
        // powerbank specs
        p10.setCapacityMah(20000);
        p10.setMaxOutputWatt(30);
        p10.setInputPort("Type-C");
        p10.setFastChargingSupport(true);
        p10.setBatteryType("Li-Po");
        p10.setChargingTimeHours(4);
        p10.setWeightGrams(350);
        p10.setDimensions("150x72x18mm");
        productRepo.save(p10);

        ProductVariant v10a = new ProductVariant();
        v10a.setProduct(p10);
        v10a.setColor("White");
        v10a.setVariantType("Slim");
        v10a.setStockQuantity(35);
        variantRepo.save(v10a);

        ProductVariant v10b = new ProductVariant();
        v10b.setProduct(p10);
        v10b.setColor("Black");
        v10b.setVariantType("Slim");
        v10b.setStockQuantity(25);
        variantRepo.save(v10b);

        // Normalized output ports for PowerSync (2x USB-A, 1x Type-C)
        PowerbankOutputPort p10p1 = new PowerbankOutputPort();
        p10p1.setPowerbankProduct(p10);
        p10p1.setPortName("USB-A");
        p10p1.setPortCount(2);
        powerbankPortRepo.save(p10p1);

        PowerbankOutputPort p10p2 = new PowerbankOutputPort();
        p10p2.setPowerbankProduct(p10);
        p10p2.setPortName("Type-C");
        p10p2.setPortCount(1);
        powerbankPortRepo.save(p10p2);
    }
}