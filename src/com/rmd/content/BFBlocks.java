package com.rmd.content;

import arc.graphics.Color;
import com.rmd.content.blocks.*;
import com.rmd.content.blocks.experiment.BaseComponent;
import com.rmd.content.blocks.experiment.ExperimentalPowerTurret;
import com.rmd.content.blocks.experiment.components.DamageImproveComponent;
import com.rmd.content.blocks.experiment.components.SpeedImproveComponent;
import com.rmd.content.blocks.heat.EnvironmentalHeatCrafter;
import com.rmd.content.blocks.heat.EnvironmentalHeatProducer;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.UnitSorts;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.LaserBulletType;
import mindustry.entities.bullet.PointBulletType;
import mindustry.entities.effect.ExplosionEffect;
import mindustry.entities.effect.MultiEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.defense.turrets.PowerTurret;
import mindustry.world.blocks.heat.HeatProducer;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.consumers.ConsumeItemFlammable;

import static com.rmd.content.BFItems.*;
import static com.rmd.content.BFLiquids.*;
import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;
import static mindustry.type.ItemStack.with;

public class BFBlocks {
    public static Block fractionalDistillationTower, catalyticCracker, steamCracker, largeSteamCracker, ethyleneHydrator,
            polyethylenePolymerizer, largePolyethylenePolymerizer, plastaniumInjector, largePlastaniumInjector, integratedRefinery;

    public static Block combustionHeater, powerHeater, environmentalHeater;

    public static Block voidDrillMarkI, voidDrillMarkII, voidDrillMarkIII, voidDrillMarkIV;

    public static Block ethanolPowerGenerator, voidPowerGenerator;

    public static Block doubleGunMarkIV, doubleGunMarkVIII, simpleLightJavelinLauncher, lightJavelinLauncher
            , tripleGunMarkV, tripleGunMarkVII, LPJ11CIWS, LMR3MRADS, destroyer, etherStrike, stella, lightingPoint, particleStream;

    public static Block etherCollector, etherCrystallizer, etherAmplifier, etherReassembler, etherFluidMixer,
            etherAlloyCompressor, etherAlloyMelter, harmonicSteelSynchronizer, etherThoriumReactor, largeHarmonicSteelSynchronizer;

    public static Block singleOverdriveProjector, singleQuickOverdriveProjector;

    public static Block damageImproveComponent, speedImproveComponent, powerEfficiencyComponent;

    public static Block hugeSiliconSmelter, fastGraphitePresser;

    public static Block largeLaserDrill, fastDrill;

    public static void load() {
        loadOilIndustry();

        loadHeaters();

        loadTurrets();

        loadCommonDrills();

        loadVoidDrills();

        loadGenerators();

        loadEtherIndustry();

        loadOverdrives();

        loadVanillaExpansions();
    }

    private static void loadCommonDrills(){
        largeLaserDrill = new Drill("large-laser-drill"){{
            description = "A large laser drill, can mine ores faster.";
            requirements(Category.production, with(copper, 200, graphite, 180, silicon, 180, titanium, 160));
            drillTime = 130;
            size = 4;
            hasPower = true;
            tier = 4;
            updateEffect = Fx.pulverizeMedium;
            drillEffect = Fx.mineBig;

            consumePower(1.8f);
            consumeLiquid(water, 0.16f).boost();
        }};

        fastDrill = new Drill("fast-drill"){{
            description = "Efficiency is all what you need.";
            requirements(Category.production, with(graphite, 240, silicon, 200, titanium, 180, thorium, 150));
            drillTime = 35;
            size = 4;
            hasPower = true;
            tier = 4;
            updateEffect = Fx.pulverizeMedium;
            drillEffect = Fx.mineBig;
            itemCapacity = 40;
            rotateSpeed = 8f;
            hardnessDrillMultiplier = 30;

            liquidBoostIntensity = 2.2f;

            consumePower(3.5f);
            consumeLiquid(water, 0.3f).boost();
        }};
    }

    private static void loadOverdrives() {
        singleOverdriveProjector = new SingleOverdrive("single-overdrive-projector"){{
            description = "Use spore to make building overdrive, but it will be hurt by the spore.";
            requirements(Category.effect, with(lead, 400, titanium, 220, silicon, 320, thorium, 300, plastanium, 300, surgeAlloy, 80));
            consumePower(12f);
            health = 440;
            size = 1;
            regionRotated1 = 2;
            speedBoost = 4f;
            speedBoostPhase = 1f;
            useTime = 120f;
            percentDamage = 3.5f;
            consumeItem(sporePod, 3);
        }};

        singleQuickOverdriveProjector = new SingleOverdrive("single-quick-overdrive-projector"){{
            description = "It consumes spore faster, and would injure the building much more series.";
            requirements(Category.effect, with(titanium, 400, silicon, 800, thorium, 650, plastanium, 700, surgeAlloy, 240, harmonicSteel, 120));
            consumePower(18f);
            health = 800;
            size = 1;
            regionRotated1 = 2;
            speedBoost = 6f;
            speedBoostPhase = 1.5f;
            itemCapacity = 20;
            useTime = 75f;
            percentDamage = 8f;
            damage = 25f;
            consumeItem(sporePod, 8);
        }};
    }

    private static void loadVanillaExpansions(){
        hugeSiliconSmelter = new GenericCrafter("huge-silicon-smelter"){{
            description = "Cause of its fast and huge, some of the silicon made from it is not pure enough to use.";
            requirements(Category.crafting, with(copper, 600, lead, 550, titanium, 300, graphite, 300, silicon, 200));
            craftEffect = Fx.smeltsmoke;
            outputItem = new ItemStack(silicon, 8);
            craftTime = 24f;
            size = 5;
            itemCapacity = 60;
            hasPower = true;
            hasLiquids = false;
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.12f;
            consumeItems(with(coal, 16, sand, 20));
            consumePower(8.5f);
        }};

        fastGraphitePresser = new GenericCrafter("fast-graphite-presser"){{
            description = "Fast but waste a lot.";
            requirements(Category.crafting, with(copper, 400, lead, 500, titanium, 350, graphite, 400));
            craftEffect = Fx.pulverizeMedium;
            outputItem = new ItemStack(graphite, 4);
            craftTime = 13f;
            itemCapacity = 20;
            size = 4;
            hasItems = true;
            hasLiquids = true;
            hasPower = true;
            consumePower(6.6f);
            consumeItem(coal, 7);
            consumeLiquid(water, 0.6f);
        }};

    }

    private static void loadEtherIndustry() {
        // 12 /s ether => 1 /s etherCrystal
        etherCollector = new GenericCrafter("ether-collector"){{
            description = "Collect ether from air.";
            requirements(Category.crafting, ItemStack.with(copper, 75, silicon, 90, titanium, 65, polyethylene, 120));
            health = 800;
            size = 2;
            craftTime = 60f;
            hasPower = true;
            consumePower(0.8f);
            hasLiquids = true;
            outputsLiquid = true;
            outputLiquid = new LiquidStack(ether, 0.25f);
        }};

        etherCrystallizer = new GenericCrafter("ether-crystallizer"){{
            description = "Crystallize the ether.";
            requirements(Category.crafting, ItemStack.with(copper, 80, lead, 100, silicon, 70, titanium, 40, polyethylene, 650));
            health = 900;
            size = 3;
            craftTime = 240f;
            itemCapacity = 40;
            liquidCapacity = 80f;
            hasPower = true;
            hasLiquids = true;
            hasItems = true;
            outputItems = ItemStack.with(etherCrystal, 27, nickel, 6); // 27/4
            consumePower(6f);
            consumeLiquid(ether, 1.35f);
            consumeItem(lead, 6);
        }};

        etherReassembler = new GenericCrafter("ether-reassembler"){{
            description = "Reassemble the ether with its core.";
            requirements(Category.crafting, ItemStack.with(copper, 200, silicon, 180, titanium, 80, polyethylene, 800));
            health = 1200;
            size = 4;
            craftTime = 60f;
            hasPower = true;
            hasLiquids = true;
            hasItems = true;
            outputsLiquid = true;
            consumePower(4.0f);
            consumeItem(etherCore, 5);
            outputLiquid = new LiquidStack(ether, 1f);
        }};

        etherFluidMixer = new GenericCrafter("ether-fluid-mixer"){{
            description = "Mix ether with cryofluid.";
            requirements(Category.crafting, ItemStack.with(copper, 180, graphite, 50, silicon, 140, titanium, 75, polyethylene, 320));
            health = 220;
            size = 2;
            craftTime = 60f;
            hasPower = true;
            hasLiquids = true;
            hasItems = true;
            outputsLiquid = true;
            consumePower(4.0f);
            consumeLiquid(cryofluid, 0.4f);
            consumeItem(etherCrystal, 5);
            outputLiquid = new LiquidStack(etherFluid, 0.4f);
        }};

        etherAlloyCompressor = new GenericCrafter("ether-alloy-compressor"){{
            description = "Produce ether alloy.";
            requirements(Category.crafting, ItemStack.with(copper, 120, silicon, 400, titanium, 320, thorium, 180, polyethylene, 600));
            health = 1630;
            size = 4;
            craftTime = 720f;
            hasPower = true;
            hasItems = true;
            consumePower(9.2f);
            consumeItems(ItemStack.with(etherCore, 8, surgeAlloy, 3));
            outputItem = new ItemStack(etherAlloy, 3);
        }};

        etherAlloyMelter = new GenericCrafter("ether-alloy-melter"){{
            description = "Melt surge alloy with ether to produce ether alloy, faster than the compressor.";
            requirements(Category.crafting, ItemStack.with(silicon, 600, titanium, 750, nickel, 200, thorium, 200, plastanium, 300, surgeAlloy, 50, polyethylene, 720));
            health = 2250;
            size = 5;
            craftTime = 120f;
            hasPower = true;
            hasLiquids = true;
            hasItems = true;
            consumePower(21.5f);
            consumeLiquid(etherFluid, 0.8f);
            consumeItem(surgeAlloy, 6);
            outputItem = new ItemStack(etherAlloy, 6);
        }};

        harmonicSteelSynchronizer = new GenericCrafter("harmonic-steel-synchronizer"){{
            description = "Synchronize harmonic steel with ether.";
            requirements(Category.crafting, ItemStack.with(silicon, 300, titanium, 450, nickel, 120, thorium, 200,
                    polyethylene, 500, plastanium, 500, surgeAlloy, 180));
            health = 770;
            size = 3;
            craftTime = 400f;
            hasPower = true;
            hasItems = true;
            consumePower(7.6f);
            consumeItems(ItemStack.with(etherCrystal, 2, thorium, 4, plastanium, 4));
            outputItem = new ItemStack(harmonicSteel, 2);
        }};

        largeHarmonicSteelSynchronizer = new GenericCrafter("large-harmonic-steel-synchronizer"){{
            description = "Synchronize harmonic steel with ether fluid.";
            requirements(Category.crafting, ItemStack.with(silicon, 400, titanium, 650, nickel, 300, thorium, 450,
                    polyethylene, 750, plastanium, 600, surgeAlloy, 250, harmonicSteel, 80));
            health = 1280;
            size = 4;
            craftTime = 120f;
            hasPower = true;
            hasLiquids = true;
            hasItems = true;
            itemCapacity = 40;
            consumePower(12.2f);
            consumeLiquid(etherFluid, 0.48f);
            consumeItems(ItemStack.with(thorium, 12, plastanium, 12));
            outputItem = new ItemStack(harmonicSteel, 8);
        }};
    }

    private static void loadGenerators() {
        ethanolPowerGenerator = new ConsumeGenerator("ethanol-power-generator"){{
            description = "Burn ethanol to get a lot of power.";
            requirements(Category.power, ItemStack.with(copper, 350, lead, 300, graphite, 250, silicon, 250, titanium, 200));
            health = 980;
            size = 3;
            liquidCapacity = 120.0F;
            powerProduction = 24.0F;
            generateEffect = Fx.burning;
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.08F;
            consumeLiquid(ethanol, 1f);
        }};

        voidPowerGenerator = new OutputGenerator("void-power-generator"){{
            description = "Using void particles to generate power and a few dark matter.";
            requirements(Category.power, ItemStack.with(copper, 500, silicon, 300, titanium, 600, plastanium, 400,
                    voidParticle, 200, harmonicSteel, 800));
            researchCostMultipliers.put(voidParticle, 0.05f);
            health = 2880;
            size = 4;
            itemCapacity = 20;
            itemDuration = 450f;
            powerProduction = 90.0F;
            generateEffect = Fx.bigShockwave;
            generateEffectRange = 16;
            ambientSound = Sounds.shockBlast;
            ambientSoundVolume = 0.16F;
            consumeItem(voidParticle, 1);
            outputItem = new ItemStack(darkMatter, 1);
            explosionRadius = 38;
            explosionDamage = 7000;
        }};

        etherAmplifier = new OutputGenerator("ether-amplifier"){{
            description = "Amplify the energy of etherCrystal";
            requirements(Category.power, ItemStack.with(copper, 300, silicon, 250, titanium, 420));
            health = 1200;
            size = 3;
            itemDuration = 240f;
            itemCapacity = 20;
            powerProduction = 12f;
            outputsPower = consumesPower = true;
            consumePower(1.8f);
            consumeItem(etherCrystal, 8);
            outputItem = new ItemStack(etherCore, 8);
        }};

        etherThoriumReactor = new OutputReactor("ether-thorium-reactor"){{
            requirements(Category.power, with(polyethylene, 800, lead, 800, silicon, 500, graphite, 200, thorium, 200, harmonicSteel, 80));
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.3f;
            size = 4;
            health = 1160;
            itemDuration = 60f;
            powerProduction = 20f;
            heating = 0.01f;
            itemCapacity = 100;
            liquidCapacity = 400f;

            consumeItem(thorium, 6);
            consumeLiquids(LiquidStack.with(cryofluid, heating/coolantPower, ether, 3.4f)).update(false);
            outputItems = ItemStack.with(nickel, 6, etherCrystal, 17); // half of thorium will change to nickel
        }};
    }

    private static void loadVoidDrills() {
        voidDrillMarkI = new VoidDrill("void-drill-mk1"){{
            description = "A drill that drills through void.";
            requirements(Category.production, ItemStack.with(copper, 800, graphite, 640, silicon, 640, titanium, 960, plastanium, 640,
                    thorium, 640, phaseFabric, 640));
            researchCostMultiplier = 0.6f;
            drillTime = 18f;
            size = 3;
            tier = 4;
            itemCapacity = 80;
            consumePower(30F);
        }};

        voidDrillMarkII = new VoidDrill("void-drill-mk2"){{
            description = "A drill that drills through void with a faster speed.";
            requirements(Category.production, ItemStack.with(copper, 1800, graphite, 1600, silicon, 1600, titanium, 2000, plastanium, 1200,
                    thorium, 1200, phaseFabric, 1200));
            researchCostMultiplier = 0.6f;
            drillTime = 9f;
            size = 3;
            tier = 6;
            itemCapacity = 200;
            lowestDrillTier = 1f;
            consumePower(80F);
        }};

        voidDrillMarkIII = new VoidDrill("void-drill-mk3"){{
            description = "This will never be the most effective one.";
            requirements(Category.production, ItemStack.with(copper, 2500, graphite, 2000, silicon, 2000, titanium, 3000, plastanium, 2000,
                    thorium, 2000, phaseFabric, 1600, surgeAlloy, 800));
            researchCostMultiplier = 0.6f;
            drillTime = 4f;
            size = 3;
            tier = 8;
            itemCapacity = 300;
            lowestDrillTier = 2f;
            voidParticleChance = 0.0005f; // 0.05%
            consumePower(240F);
        }};

        voidDrillMarkIV = new VoidDrill("void-drill-mk4"){{
            description = "Faster, faster and faster.";
            requirements(Category.production, ItemStack.with(copper, 3600, graphite, 3600, silicon, 3600, titanium, 4800, plastanium, 3600,
                    thorium, 3600, phaseFabric, 3000, surgeAlloy, 1800, voidParticle, 300));
            researchCostMultiplier = 0.8f;
            researchCostMultipliers.put(voidParticle, 0.01f);
            drillTime = 2f;
            size = 4;
            tier = 10;
            itemCapacity = 400;
            lowestDrillTier = 3f;
            voidParticleChance = 0.008f; // 0.8%
            consumePower(400F);
        }};
    }

    private static void loadOilIndustry(){
        // oil -> naphtha + heavy oil + pyratite
        fractionalDistillationTower = new EnvironmentalHeatCrafter("fractional-distillation-tower"){{
            requirements(Category.crafting, ItemStack.with(copper, 330, graphite, 175, silicon, 240, titanium, 400));
            description = "Converts oil into naphtha and heavy oil, signing the start of the petroleum industry.";
            health = 1660;
            size = 4;
            researchCostMultiplier = 2.4f;
            craftTime = 120f;
            liquidCapacity = 120f;
            craftEffect = Fx.steam;
            ambientSound = Sounds.steam;
            hasPower = true;
            hasLiquids = true;
            hasItems = true;
            outputLiquids = LiquidStack.with(naphtha, 1f, heavyOil, 0.5f);
            outputItem = new ItemStack(pyratite, 2);
            consumePower(2.8f);
            consumeLiquid(oil, 1f);

            heatRequirement = 8f;
        }};

        // heavy oil -> naphtha
        catalyticCracker = new EnvironmentalHeatCrafter("catalytic-cracker"){{
            description = "Uses catalysts to convert heavy oil into naphtha, while producing oil residue.";
            requirements(Category.crafting, ItemStack.with(copper, 240, graphite, 160, silicon, 150, titanium, 200));
            health = 720;
            size = 3;
            craftTime = 45f;
            liquidCapacity = 120f;
            craftEffect = Fx.steam;
            ambientSound = Sounds.steam;
            hasPower = true;
            hasLiquids = true;
            hasItems = true;
            outputLiquid = new LiquidStack(naphtha, 1f);
            outputItem = new ItemStack(oilResidue, 3);
            consumePower(2f);
            consumeLiquid(heavyOil, 0.5f);

            heatRequirement = 4f;
        }};

        // naphtha -> ethylene
        steamCracker = new EnvironmentalHeatCrafter("steam-cracker"){{
            description = "Cracks naphtha into ethylene.";
            requirements(Category.crafting, ItemStack.with(copper, 160, graphite, 80, silicon, 80, titanium, 280));
            health = 400;
            size = 2;
            craftTime = 60f;
            liquidCapacity = 120f;
            craftEffect = Fx.steam;
            ambientSound = Sounds.steam;
            hasPower = true;
            hasLiquids = true;
            outputLiquid = new LiquidStack(ethylene, 1f);
            consumePower(1.4f);
            consumeLiquids(LiquidStack.with(cryofluid, 0.3f, naphtha, 1f));

            heatRequirement = 12f;
        }};

        largeSteamCracker = new EnvironmentalHeatCrafter("large-steam-cracker"){{
            description = "Cracks naphtha into ethylene faster.";
            requirements(Category.crafting, ItemStack.with(copper, 350, graphite, 200, silicon, 180, titanium, 330));
            health = 650;
            size = 3;
            craftTime = 60f;
            liquidCapacity = 240f;
            craftEffect = Fx.steam;
            ambientSound = Sounds.steam;
            hasPower = true;
            hasLiquids = true;
            outputLiquid = new LiquidStack(ethylene, 2f);
            consumePower(2.2f);
            consumeLiquids(LiquidStack.with(cryofluid, 0.8f, naphtha, 2f));

            heatRequirement = 18f;
        }};

        // oil -> ethylene + other
        integratedRefinery = new EnvironmentalHeatCrafter("integrate-refinery"){{
            requirements(Category.crafting, ItemStack.with(copper, 2000, metaglass, 1200, graphite, 2000, silicon, 1600, titanium, 1200));
            description = "Integrating many the factory of oil.";
            health = 3800;
            size = 6;
            researchCostMultiplier = 0.6f;
            craftTime = 180f;
            liquidCapacity = 480f;
            itemCapacity = 50;
            craftEffect = Fx.steam;
            ambientSound = Sounds.steam;
            ambientSoundVolume = 0.15f;
            hasPower = true;
            hasLiquids = true;
            hasItems = true;
            outputLiquid = new LiquidStack(ethylene, 2f);
            outputItems = ItemStack.with(pyratite, 3, oilResidue, 12);
            consumePower(18f);
            consumeLiquids(LiquidStack.with(oil, 1f, cryofluid, 0.8f));

            heatRequirement = 40f;
        }};

        // ethylene -> ethanol
        ethyleneHydrator = new EnvironmentalHeatCrafter("ethylene-hydrator"){{
            description = "Making ethanol.";
            requirements(Category.crafting, ItemStack.with(copper, 160, graphite, 120, silicon, 120, titanium, 600));
            health = 780;
            size = 3;
            craftTime = 30f;
            liquidCapacity = 120f;
            craftEffect = Fx.steam;
            ambientSound = Sounds.steam;
            hasPower = true;
            hasLiquids = true;
            outputLiquid = new LiquidStack(ethanol, 1f);
            consumePower(2.5f);
            consumeLiquids(LiquidStack.with(water, 1f, ethylene, 1f));
        }};

        // ethylene -> polyethylene
        polyethylenePolymerizer = new GenericCrafter("polyethylene-polymerizer"){{
            description = "Polymerizes ethylene to produce inexpensive plastic.";
            requirements(Category.crafting, ItemStack.with(copper, 160, graphite, 80, silicon, 80, titanium, 280));
            health = 560;
            size = 2;
            craftTime = 60f;
            liquidCapacity = 120f;
            craftEffect = Fx.formsmoke;
            ambientSound = Sounds.hum;
            hasPower = true;
            hasItems = true;
            hasLiquids = true;
            outputItem = new ItemStack(polyethylene, 3);
            consumePower(1.2f);
            consumeLiquid(ethylene, 0.5f);
        }};

        largePolyethylenePolymerizer = new GenericCrafter("large-polyethylene-polymerizer"){{
            description = "Larger, faster, better.";
            requirements(Category.crafting, ItemStack.with(copper, 500, graphite, 420, silicon, 500, titanium, 400, polyethylene, 240));
            health = 700;
            size = 3;
            craftTime = 30f;
            liquidCapacity = 120f;
            craftEffect = Fx.formsmoke;
            ambientSound = Sounds.hum;
            hasPower = true;
            hasItems = true;
            hasLiquids = true;
            outputItem = new ItemStack(polyethylene, 6);
            consumePower(5f);
            consumeLiquid(ethylene, 2f);
        }};

        // polyethylene + titanium -> plastanium
        plastaniumInjector = new GenericCrafter("plastanium-injector"){{
            description = "Fuses plastic with titanium to create plastanium.";
            requirements(Category.crafting, ItemStack.with(copper, 160, polyethylene, 120, silicon, 80, titanium, 280));
            health = 600;
            size = 2;
            craftTime = 120f;
            liquidCapacity = 120f;
            craftEffect = Fx.formsmoke;
            updateEffect = Fx.plasticburn;
            hasPower = true;
            hasItems = true;
            outputItem = new ItemStack(plastanium, 3);
            consumePower(1.5f);
            consumeItems(ItemStack.with(polyethylene, 3, titanium, 2));
        }};

        largePlastaniumInjector = new GenericCrafter("large-plastanium-injector"){{
            description = "Fuses plastic with titanium to create plastanium.";
            requirements(Category.crafting, ItemStack.with(copper, 160, polyethylene, 120, silicon, 80, titanium, 280));
            health = 850;
            size = 3;
            craftTime = 120f;
            liquidCapacity = 120f;
            craftEffect = Fx.formsmoke;
            updateEffect = Fx.plasticburn;
            hasPower = true;
            hasItems = true;
            outputItem = new ItemStack(plastanium, 12);
            consumePower(6.25f);
            consumeItems(ItemStack.with(polyethylene, 12, titanium, 8));
        }};
    }

    private static void loadHeaters() {
        combustionHeater = new HeatProducer("combustion-heater"){{
            description = "Cheapness is its greatest advantage.";
            requirements(Category.crafting, ItemStack.with(copper, 80, graphite, 30, silicon, 25));
            health = 350;
            size = 1;
            craftTime = 180f;
            consume(new ConsumeItemFlammable());
            rotateDraw = false;
            ambientSound = Sounds.extractLoop;
            ambientSoundVolume = 0.08F;
            regionRotated1 = 2;
            heatOutput = 4.0F;
        }};

        powerHeater = new HeatProducer("power-heater") {{
            description = "It's worth it.";
            requirements(Category.crafting, ItemStack.with(copper, 400, graphite, 150, silicon, 80, titanium, 80));
            researchCostMultiplier = 2.0f;
            rotateDraw = false;
            health = 900;
            size = 2;
            heatOutput = 12.0F;
            regionRotated1 = 1;
            ambientSound = Sounds.hum;
            itemCapacity = 0;
            consumePower(1.8f);
        }};

        environmentalHeater = new EnvironmentalHeatProducer("environmental-heater"){{
            description = "What?";
            requirements(Category.crafting, ItemStack.with(copper, 400, graphite, 240, silicon, 660, titanium, 320, polyethylene, 300));
            researchCostMultiplier = 4.0f;
            rotateDraw = false;
            health = 900;
            size = 3;
            heatOutput = 8.0F;
            ambientSound = Sounds.hum;
            itemCapacity = 0;
            consumePower(6f);
            range = 200f;
        }};
    }

    private static void loadTurrets() {
        doubleGunMarkIV = new ItemTurret("double-gun-mk4"){{
            description = "The twin-mounted anti-aircraft gun is capable of engaging both air and ground targets, and it is cost-effective!";
            requirements(Category.turret, ItemStack.with(copper, 300, graphite, 300, silicon, 200, titanium, 220));
            researchCostMultiplier = 0.5f;
            health = 960;
            size = 3;
            reload = 24f;
            ammoPerShot = 5;
            rotateSpeed = 12f;
            maxAmmo = 200;
            inaccuracy = 2.4f;
            range = 514f;
            shake = 2f;
            shootSound = Sounds.shoot;
            shootCone = 12f;
            recoil = 3f;
            recoilTime = 15f;
            consumeAmmoOnce = false;
            ammoUseEffect = Fx.casing2;

            coolant = consumeCoolant(1f);
            coolantMultiplier = 2f;

            ammo(titanium, new BasicBulletType(8f, 182){{
                lifetime = 400f;
                splashDamageRadius = 48f;
                splashDamage = 19f;
                trailColor = Color.yellow;
                trailLength = 7;
                trailWidth = 4f;
                buildingDamageMultiplier = 0.4f;
                hitEffect = Fx.blastExplosion;
            }}, thorium, new BasicBulletType(8f, 244){{
                lifetime = 400f;
                splashDamageRadius = 64f;
                splashDamage = 32f;
                trailColor = Color.yellow;
                trailLength = 9;
                trailWidth = 5f;
                buildingDamageMultiplier = 0.4f;
                hitEffect = Fx.blastExplosion;
            }}, pyratite, new BasicBulletType(8f, 18){{
                lifetime = 400f;
                splashDamageRadius = 64f;
                splashDamage = 32f;
                trailColor = Pal.lightOrange;
                trailLength = 7;
                trailWidth = 4f;
                status = StatusEffects.burning;
                statusDuration = 3600f;
                incendAmount = 80;
                incendChance = 0.8f;
            }}, blastCompound, new BasicBulletType(18f, 20){{
                lifetime = 400f;
                splashDamagePierce = true;
                splashDamageRadius = 174f;
                splashDamage = 270f;
                trailColor = Color.red;
                trailLength = 9;
                trailWidth = 4f;
                hitShake = 4f;
                incendAmount = 40;
                incendChance = 0.25f;
                hitEffect = new MultiEffect(Fx.blastExplosion, Fx.fireHit);
            }}, silicon, new BasicBulletType(8f, 122){{
                homingRange = 75f;
                homingDelay = 6f;
                homingPower = 0.1f;
                lifetime = 400f;
                splashDamageRadius = 48f;
                splashDamage = 19f;
                trailColor = Color.yellow;
                trailLength = 7;
                trailWidth = 4f;
                buildingDamageMultiplier = 0.4f;
                hitEffect = Fx.blastExplosion;
            }}, nickel, new BasicBulletType(8f, 198){{
                lifetime = 400f;
                splashDamageRadius = 48f;
                splashDamage = 21f;
                trailColor = Color.yellow;
                trailLength = 7;
                trailWidth = 4f;
                buildingDamageMultiplier = 0.4f;
                hitEffect = Fx.blastExplosion;
            }});

            shoot = new ShootBarrel(){{
                barrels = new float[]{
                        -6f, 0f, 0f,
                        6f, 0f, 0f,
                };
            }};

            shoot.shots = 2;

            limitRange();
        }};

        doubleGunMarkVIII = new ItemTurret("double-gun-mk8"){{
            description = "The twin-mounted anti-aircraft gun is capable of engaging both air and ground targets!";
            requirements(Category.turret, ItemStack.with(copper, 400, graphite, 600, silicon, 800, titanium, 900));
            researchCostMultiplier = 0.7f;
            health = 1080;
            size = 3;
            reload = 8.5f;
            ammoPerShot = 5;
            rotateSpeed = 20f;
            maxAmmo = 400;
            inaccuracy = 1.5f;
            range = 576.8f;
            shake = 2f;
            shootSound = Sounds.shoot;
            shootCone = 12f;
            recoil = 3f;
            recoilTime = 15f;
            consumeAmmoOnce = false;
            ammoUseEffect = Fx.casing2;

            coolant = consumeCoolant(1.2f);
            coolantMultiplier = 3f;

            ammo(titanium, new BasicBulletType(8f, 212){{
                lifetime = 400f;
                splashDamageRadius = 48f;
                splashDamage = 21f;
                trailColor = Color.yellow;
                trailLength = 7;
                trailWidth = 4f;
                buildingDamageMultiplier = 0.4f;
                hitEffect = Fx.blastExplosion;
            }}, thorium, new BasicBulletType(8f, 276){{
                lifetime = 400f;
                splashDamageRadius = 64f;
                splashDamage = 35f;
                trailColor = Color.yellow;
                trailLength = 9;
                trailWidth = 5f;
                buildingDamageMultiplier = 0.4f;
                hitEffect = Fx.blastExplosion;
            }}, pyratite, new BasicBulletType(8f, 18){{
                lifetime = 400f;
                splashDamageRadius = 64f;
                splashDamage = 32f;
                trailColor = Pal.lightOrange;
                trailLength = 7;
                trailWidth = 4f;
                status = StatusEffects.burning;
                statusDuration = 3600f;
                incendAmount = 80;
                incendChance = 0.8f;
            }}, blastCompound, new BasicBulletType(18f, 20){{
                lifetime = 400f;
                splashDamagePierce = true;
                splashDamageRadius = 174f;
                splashDamage = 135f;
                trailColor = Color.red;
                trailLength = 9;
                trailWidth = 4f;
                hitShake = 4f;
                incendAmount = 40;
                incendChance = 0.25f;
                hitEffect = new MultiEffect(Fx.blastExplosion, Fx.fireHit);
            }}, silicon, new BasicBulletType(8f, 138){{
                homingRange = 75f;
                homingDelay = 6f;
                homingPower = 0.1f;
                lifetime = 400f;
                splashDamageRadius = 48f;
                splashDamage = 19f;
                trailColor = Color.yellow;
                trailLength = 7;
                trailWidth = 4f;
                buildingDamageMultiplier = 0.4f;
                hitEffect = Fx.blastExplosion;
            }}, nickel, new BasicBulletType(8f, 233){{
                lifetime = 400f;
                splashDamageRadius = 48f;
                splashDamage = 32f;
                trailColor = Color.yellow;
                trailLength = 7;
                trailWidth = 4f;
                buildingDamageMultiplier = 0.4f;
                hitEffect = Fx.blastExplosion;
            }});

            shoot = new ShootBarrel(){{
                barrels = new float[]{
                        -7f, 0f, 0f,
                        7f, 0f, 0f,
                };
            }};

            shoot.shots = 2;

            limitRange();
        }};

        tripleGunMarkV = new ItemTurret("triple-gun-mk5"){{
            description = "As an outdated weapon of the old era, it was replaced by the Mk6 due to insufficient firepower, and shortly thereafter, the Mk6 was in turn replaced by the Mk7 due to stability issues.";
            requirements(Category.turret, ItemStack.with(copper, 800, graphite, 800, silicon, 600, titanium, 650, plastanium, 200));
            researchCostMultiplier = 1.4f;
            health = 1540;
            size = 4;
            reload = 250f;
            ammoPerShot = 5;
            rotateSpeed = 6f;
            maxAmmo = 200;
            inaccuracy = 2.6f;
            range = 573f;
            shake = 2f;
            shootSound = Sounds.shootBig;
            recoil = 7f;
            recoilTime = 45f;
            consumeAmmoOnce = false;
            ammoUseEffect = Fx.casing2;

            coolant = consumeCoolant(1.2f);
            coolantMultiplier = 2f;

            ammo(titanium, new BasicBulletType(13f, 480){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamageRadius = 72f;
                splashDamage = 51f;
                trailColor = Color.yellow;
                trailLength = 12;
                trailWidth = 6f;
                buildingDamageMultiplier = 0.4f;
                hitShake = 3f;
                hitEffect = Fx.blastExplosion;
            }}, thorium, new BasicBulletType(13f, 710){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamageRadius = 85f;
                splashDamage = 65f;
                trailColor = Color.yellow;
                trailLength = 14;
                trailWidth = 6f;
                buildingDamageMultiplier = 0.4f;
                hitShake = 4f;
                hitEffect = Fx.blastExplosion;
            }}, pyratite, new BasicBulletType(15f, 44){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamageRadius = 256f;
                splashDamage = 18f;
                trailColor = Pal.lightOrange;
                trailLength = 12;
                trailWidth = 4f;
                buildingDamageMultiplier = 1.2f;
                hitShake = 2f;
                status = StatusEffects.burning;
                statusDuration = 3600f;
                rangeChange = 40f;
                incendAmount = 120;
                incendChance = 0.8f;
            }}, blastCompound, new BasicBulletType(18f, 60){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamageRadius = 256f;
                splashDamage = 240f;
                trailColor = Color.red;
                trailLength = 14;
                trailWidth = 4f;
                buildingDamageMultiplier = 2f;
                hitShake = 6f;
                rangeChange = 80f;
                incendAmount = 60;
                incendChance = 0.25f;
                hitEffect = new MultiEffect(Fx.blastExplosion, Fx.fireHit);
            }}, silicon, new BasicBulletType(8f, 430){{
                homingRange = 105f;
                homingDelay = 6f;
                homingPower = 0.15f;
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamageRadius = 72f;
                splashDamage = 47f;
                trailColor = Color.yellow;
                trailLength = 12;
                trailWidth = 6f;
                buildingDamageMultiplier = 0.4f;
                hitShake = 3f;
                hitEffect = Fx.blastExplosion;
            }}, nickel, new BasicBulletType(8f, 510){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamageRadius = 72f;
                splashDamage = 53f;
                trailColor = Color.yellow;
                trailLength = 12;
                trailWidth = 6f;
                buildingDamageMultiplier = 0.4f;
                hitShake = 3f;
                hitEffect = Fx.blastExplosion;
            }});

            shoot = new ShootBarrel(){{
                barrels = new float[]{
                        -6f, 0f, 0f,
                        0f, 1f, 0f,
                        6f, 0f, 0f,
                };
            }};

            shoot.shots = 3;

            limitRange();
        }};

        tripleGunMarkVII = new ItemTurret("triple-gun-mk7"){{
            description = "It has been built as the core of maritime defense, providing powerful firepower support.";
            requirements(Category.turret, ItemStack.with(copper, 600, metaglass, 300, graphite, 750, silicon, 900, titanium, 1400, plastanium, 800));
            researchCostMultiplier = 2.1f;
            health = 1980;
            size = 5;
            reload = 650f;
            ammoPerShot = 30;
            rotateSpeed = 3f;
            maxAmmo = 300;
            inaccuracy = 3f;
            range = 840f;
            shake = 3f;
            shootSound = Sounds.shootBig;
            recoil = 10f;
            recoilTime = 60f;
            consumeAmmoOnce = false;
            ammoUseEffect = Fx.casing2;

            coolant = consumeCoolant(1.6f);

            ammo(titanium, new BasicBulletType(15f, 1120){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                pierce = true;
                pierceBuilding = true;
                pierceCap = 3;
                pierceDamageFactor = 0.8f;
                removeAfterPierce = false;
                splashDamagePierce = true;
                splashDamageRadius = 94f;
                splashDamage = 80f;
                reflectable = false;
                trailColor = Color.yellow;
                trailLength = 20;
                trailWidth = 8f;
                hitShake = 4f;
                hitEffect = Fx.blastExplosion;
            }}, thorium, new BasicBulletType(15f, 1450){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                pierce = true;
                pierceBuilding = true;
                pierceCap = 5;
                pierceDamageFactor = 0.9f;
                removeAfterPierce = false;
                splashDamagePierce = true;
                splashDamageRadius = 105f;
                splashDamage = 95f;
                reflectable = false;
                trailColor = Color.yellow;
                trailLength = 22;
                trailWidth = 8f;
                hitShake = 6f;
                hitEffect = Fx.blastExplosion;
            }}, pyratite, new BasicBulletType(18f, 75){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamagePierce = true;
                splashDamageRadius = 188f;
                splashDamage = 44f;
                reflectable = false;
                trailColor = Pal.lightOrange;
                trailLength = 18;
                trailWidth = 6f;
                buildingDamageMultiplier = 1.2f;
                hitShake = 2f;
                status = StatusEffects.burning;
                statusDuration = 3600f;
                rangeChange = 40f;
                incendAmount = 120;
                incendChance = 0.8f;
            }}, blastCompound, new BasicBulletType(22f, 90){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamagePierce = true;
                splashDamageRadius = 264f;
                splashDamage = 370f;
                reflectable = false;
                trailColor = Color.red;
                trailLength = 18;
                trailWidth = 6f;
                buildingDamageMultiplier = 2f;
                hitShake = 9f;
                rangeChange = 80f;
                incendAmount = 65;
                incendChance = 0.25f;
                hitEffect = new MultiEffect(Fx.blastExplosion, Fx.fireHit);
            }}, surgeAlloy, new BasicBulletType(22f, 1750){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                pierce = true;
                pierceBuilding = true;
                pierceCap = 4;
                pierceDamageFactor = 0.9f;
                removeAfterPierce = false;
                splashDamagePierce = true;
                splashDamageRadius = 112f;
                splashDamage = 101f;
                reflectable = false;
                trailColor = Pal.surge;
                trailLength = 29;
                trailWidth = 6f;
                hitShake = 7f;
                hitEffect = Fx.blastExplosion;
                lightningDamage = 72f;
                lightningLength = 8;
                lightning = 12;
                lightningColor = Pal.surge;
                lightningLengthRand = 12;
            }}, etherCrystal, new BasicBulletType(22f, 78){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamagePierce = true;
                splashDamageRadius = 32f;
                splashDamage = 35f;
                reflectable = false;
                trailColor = Color.white.cpy().a(0.6f);
                trailLength = 24;
                trailWidth = 6f;
                hitEffect = Fx.lightning;
                lightningDamage = 102f;
                lightningLength = 16;
                lightning = 17;
                lightningColor = Color.green.cpy().a(0.3f);
                lightningLengthRand = 12;
            }}, silicon, new BasicBulletType(8f, 950){{
                homingRange = 105f;
                homingDelay = 6f;
                homingPower = 0.15f;

                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamageRadius = 94f;
                splashDamage = 75f;
                reflectable = false;
                trailColor = Color.yellow;
                trailLength = 20;
                trailWidth = 8f;
                hitShake = 4f;
                hitEffect = Fx.blastExplosion;
                reloadMultiplier = 0.8f;
            }}, nickel, new BasicBulletType(8f, 1240){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                pierce = true;
                pierceBuilding = true;
                pierceCap = 4;
                pierceDamageFactor = 0.85f;
                removeAfterPierce = false;
                splashDamagePierce = true;
                splashDamageRadius = 94f;
                splashDamage = 80f;
                reflectable = false;
                trailColor = Color.yellow;
                trailLength = 20;
                trailWidth = 8f;
                hitShake = 4f;
                hitEffect = Fx.blastExplosion;
            }});

            shoot = new ShootBarrel(){{
                barrels = new float[]{
                        -6f, 0f, 0f,
                        0f, 1f, 0f,
                        6f, 0f, 0f,
                };
            }};

            shoot.shots = 3;

            limitRange();
        }};

        destroyer = new ItemTurret("destroyer"){{
            description = "Good luck.";
            requirements(Category.turret, ItemStack.with(voidParticle, 500, darkMatter, 200));
            researchCost = ItemStack.with(voidParticle, 600, darkMatter, 320);
            health = 9999;
            size = 6;
            reload = 9000f;
            ammoPerShot = 10;
            rotateSpeed = 3f;
            maxAmmo = 20;
            range = 880f;
            cooldownTime = reload;
            shake = 9.9f;
            recoil = 20f;
            recoilTime = 60f;
            shootCone = 2f;
            ammoUseEffect = Fx.casing3;
            shootSound = Sounds.railgun;
            unitSort = UnitSorts.strongest;
            consumePower(99f);

            ammo(voidParticle, new PointBulletType(){{
                shootEffect = Fx.instShoot;
                hitEffect = Fx.instHit;
                smokeEffect = Fx.smokeCloud;
                trailEffect = Fx.instTrail;
                despawnEffect = hitEffect = new ExplosionEffect(){{
                    waveColor = Color.purple;
                    smokeColor = Pal.shadow;
                    waveStroke = 4f;
                    waveRad = 80f;
                }};
                trailSpacing = 39.9F;
                splashDamage = 99999;
                splashDamageRadius = 80f;
                speed = 880f;
                hitShake = 9.9F;
                ammoMultiplier = 1.0F;
            }}, darkMatter, new BasicBulletType(30f, 0){{
                lifetime = 400f;
                splashDamage = 7200f;
                splashDamageRadius = 1000f;
                splashDamagePierce = true;
                trailColor = Color.black;
                trailLength = 18;
                trailWidth = 9f;
                buildingDamageMultiplier = 0.4f;
                hitShake = 20f;
                hitEffect = Fx.blastExplosion;

                despawnEffect = hitEffect = new ExplosionEffect(){{
                    waveColor = Color.black;
                    smokeColor = Pal.shadow;
                    waveStroke = 2f;
                    waveRad = 1000f;
                }};

                fragBullet = new BasicBulletType(0f, 0){{
                    lifetime = 12f;
                    splashDamage = 7200f;
                    splashDamageRadius = 1000f;
                    splashDamagePierce = true;
                    trailColor = Color.black;
                    trailLength = 18;
                    trailWidth = 9f;
                    buildingDamageMultiplier = 0.4f;
                    hitShake = 20f;
                    hitEffect = Fx.blastExplosion;

                    despawnEffect = hitEffect = new ExplosionEffect(){{
                        waveColor = Color.black;
                        smokeColor = Pal.shadow;
                        waveStroke = 16f;
                        waveRad = 1000f;
                    }};
                }};

                fragBullets = 1;
            }});

            limitRange(0f);
        }};

        LPJ11CIWS = new ItemTurret("LPJ-11-CIWS"){{
            description = "It's crazy, can the logistics hold up?";
            requirements(Category.turret, ItemStack.with(copper, 500, graphite, 600, silicon, 500, titanium, 400, plastanium, 200));
            researchCostMultiplier = 1.8f;
            health = 880;
            size = 3;
            reload = 0.66f;
            rotateSpeed = 90f;
            maxAmmo = 2000;
            inaccuracy = 30f;
            range = 165f;
            shootSound = Sounds.shoot;
            shootCone = 40f;
            consumeAmmoOnce = false;
            ammoUseEffect = Fx.casing2;

            ammo(copper, new BasicBulletType(18f, 16){{
                knockback = 0.1f;
                lifetime = 120.0F;
            }}, lead, new BasicBulletType(18f, 18){{
                knockback = 0.15f;
                lifetime = 120.0F;
            }}, metaglass, new BasicBulletType(18f, 22){{
                lifetime = 120.0F;

                fragBullet = new BasicBulletType(18f, 18){{
                    fragLifeMin = 1.5f;
                    fragLifeMax = 5f;
                    fragRandomSpread = 90f;
                    fragBullets = 3;
                    fragVelocityMin = 0.8f;
                    fragVelocityMax = 1.5f;
                }};
            }}, polyethylene, new BasicBulletType(18f, 26){{
                lifetime = 120.0F;

                fragBullet = new BasicBulletType(9f, 40){{
                    fragLifeMin = 1.5f;
                    fragLifeMax = 5f;
                    fragRandomSpread = 90f;
                    fragBullets = 5;
                    fragVelocityMin = 0.8f;
                    fragVelocityMax = 1.5f;

                    fragBullet = new BasicBulletType(4.5f, 14){{
                        fragLifeMin = 1f;
                        fragLifeMax = 4f;
                        fragRandomSpread = 90f;
                        fragBullets = 3;
                        fragVelocityMin = 0.8f;
                        fragVelocityMax = 1.5f;
                    }};
                }};
            }}, titanium, new BasicBulletType(18f, 30){{
                knockback = 0.2f;
                lifetime = 120.0F;

                pierce = true;
                pierceCap = 4;
                pierceBuilding = true;
                removeAfterPierce = false;
            }}, thorium, new BasicBulletType(18f, 36){{
                knockback = 0.22f;
                lifetime = 120.0F;

                pierce = true;
                pierceCap = 6;
                pierceBuilding = true;
                removeAfterPierce = false;
            }}, surgeAlloy, new BasicBulletType(18f, 54){{
                knockback = 0.3f;
                lifetime = 120.0F;

                pierce = true;
                pierceCap = 3;
                pierceBuilding = true;
                removeAfterPierce = false;

                lightningColor = Pal.surge;
                lightningLength = 14;
                lightningDamage = 25f;
                lightning = 3;
            }}, blastCompound, new BasicBulletType(18f, 10){{
                lifetime = 120.0F;
                splashDamageRadius = 38.4f;
                splashDamage = 47f;
                trailColor = Color.red;
                trailLength = 4;
                trailWidth = 1.5f;
                hitShake = 0.9f;
                hitEffect = new MultiEffect(Fx.blastExplosion, Fx.fireHit);
                status = StatusEffects.blasted;
            }});

            shoot = new ShootBarrel(){{
                barrels = new float[]{
                        2f, 0f, 0f,
                        -8f, 0f, 0f,
                        10f, 0f, 0f,
                        -4f, 0f, 0f,
                        6f, 0f, 0f,
                        -10f, 0f, 0f,
                        -2f, 0f, 0f,
                        0f, 0f, 0f,
                        4f, 0f, 0f,
                        -6f, 0f, 0f,
                        8f, 0f, 0f,
                };
            }};

            shoot.shots = 11;
            shoot.shotDelay = 0.06f;

            limitRange();
        }};

        LMR3MRADS = new ItemTurret("LMR-3-MRADS"){{
            description = "Previously a commonly used anti-aircraft firepower, it boasted relatively superior range and high accuracy, while its lower logistical demands made it widely deployed in combat against MRKW.";
            requirements(Category.turret, ItemStack.with(copper, 400, lead, 400, graphite, 320, silicon, 300, titanium, 150));
            researchCostMultiplier = 1.2f;
            health = 720;
            size = 3;
            reload = 3.6f;
            rotateSpeed = 85f;
            maxAmmo = 2000;
            inaccuracy = 14f;
            range = 288f;
            shootSound = Sounds.shoot;
            shootCone = 20f;
            consumeAmmoOnce = false;
            ammoUseEffect = Fx.casing2;
            ammoPerShot = 2;

            coolant = consumeCoolant(2f);

            ammo(copper, new BasicBulletType(18f, 20){{
                knockback = 0.1f;
                lifetime = 120.0F;
            }}, lead, new BasicBulletType(18f, 26){{
                knockback = 0.15f;
                lifetime = 120.0F;
            }}, metaglass, new BasicBulletType(18f, 34){{
                lifetime = 120.0F;

                fragBullet = new BasicBulletType(18f, 22){{
                    fragLifeMin = 2f;
                    fragLifeMax = 6f;
                    fragRandomSpread = 18f;
                    fragBullets = 4;
                    fragVelocityMin = 3.5f;
                    fragVelocityMax = 7f;
                }};
            }}, polyethylene, new BasicBulletType(18f, 41){{
                lifetime = 120.0F;

                fragBullet = new BasicBulletType(18f, 32){{
                    fragLifeMin = 2f;
                    fragLifeMax = 6f;
                    fragRandomSpread = 22f;
                    fragBullets = 7;
                    fragVelocityMin = 3.5f;
                    fragVelocityMax = 7f;

                    fragBullet = new BasicBulletType(18f, 22){{
                        fragLifeMin = 1f;
                        fragLifeMax = 4f;
                        fragRandomSpread = 360f;
                        fragBullets = 4;
                        fragVelocityMin = 3.5f;
                        fragVelocityMax = 7f;
                    }};
                }};
            }}, titanium, new BasicBulletType(18f, 60){{
                knockback = 0.2f;
                lifetime = 120.0F;

                pierce = true;
                pierceCap = 4;
                pierceBuilding = true;
                pierceDamageFactor = 0.8f;
                removeAfterPierce = false;
            }}, thorium, new BasicBulletType(18f, 70){{
                knockback = 0.22f;
                lifetime = 120.0F;

                pierce = true;
                pierceCap = 6;
                pierceBuilding = true;
                pierceDamageFactor = 0.85f;
                removeAfterPierce = false;
            }}, surgeAlloy, new BasicBulletType(18f, 176){{
                knockback = 0.3f;
                lifetime = 120.0F;

                pierce = true;
                pierceCap = 10;
                pierceBuilding = true;
                pierceDamageFactor = 0.9f;
                removeAfterPierce = false;

                lightningColor = Pal.surge;
                lightningLength = 22;
                lightningDamage = 27f;
                lightning = 11;
                ammoMultiplier = 1f;
            }}, blastCompound, new BasicBulletType(18f, 12){{
                lifetime = 120.0F;
                splashDamageRadius = 78f;
                splashDamage = 105f;
                trailColor = Color.red;
                trailLength = 4;
                trailWidth = 1.5f;
                hitShake = 1.6f;
                hitEffect = new MultiEffect(Fx.blastExplosion, Fx.fireHit);
                status = StatusEffects.blasted;
                ammoMultiplier = 1f;
            }});


            shoot = new ShootBarrel(){{
                barrels = new float[]{
                        0f, 1f, 0f,
                        -8f, 0f, 0f,
                        8f, 0f, 0f,
                };
            }};

            shoot.shots = 3;
            shoot.shotDelay = 0.6f;

            limitRange();
        }};

        simpleLightJavelinLauncher = new DisposableTurret("simple-light-javelin-launcher") {{
            description = "Cheap, but it can only use one time!";
            requirements(Category.turret, ItemStack.with(copper, 80, lead, 80, graphite, 40, silicon, 120, etherAlloy, 20));
            health = 300;
            range = 640.0F;
            shoot.firstShotDelay = 90f;
            reload = 90.0F;
            shake = 2.0F;
            cooldownTime = reload;
            shootEffect = Fx.lancerLaserShoot;
            smokeEffect = Fx.none;
            heatColor = Color.red;
            size = 2;
            targetAir = false;
            shootSound = Sounds.railgun;
            consumePower(18.0F);

            shootType = new PointBulletType(){{
                shootEffect = Fx.instShoot;
                hitEffect = Fx.instHit;
                smokeEffect = Fx.smokeCloud;
                trailEffect = Fx.instTrail;
                despawnEffect = Fx.instBomb;
                trailSpacing = 12.0F;
                damage = 1800f;
                speed = 640f;
                hitShake = 9.9F;
                ammoMultiplier = 1.0F;
            }};

            limitRange(0f);
        }};

        lightJavelinLauncher = new PowerTurret("light-javelin-launcher") {{
            description = "Hit me with the light-javelin!";
            requirements(Category.turret, ItemStack.with(copper, 400, silicon, 700, titanium, 400, thorium, 400, surgeAlloy, 100, etherAlloy, 100));
            health = 980;
            range = 760.0F;
            shoot.firstShotDelay = 90f;
            recoil = 15f;
            recoilTime = 30f;
            reload = 90.0F;
            cooldownTime = reload;
            shake = 5.0F;
            shootEffect = Fx.lancerLaserShoot;
            smokeEffect = Fx.none;
            heatColor = Color.red;
            size = 3;
            targetAir = false;
            shootSound = Sounds.railgun;
            consumePower(15.0F);

            shootType = new PointBulletType(){{
                shootEffect = Fx.instShoot;
                hitEffect = Fx.instHit;
                smokeEffect = Fx.smokeCloud;
                trailEffect = Fx.instTrail;
                despawnEffect = Fx.instBomb;
                trailSpacing = 12.0F;
                damage = 1970f;
                speed = 760f;
                hitShake = 9.9F;
                ammoMultiplier = 1.0F;
            }};

            limitRange(0f);
        }};

        etherStrike = new ExperimentalPowerTurret("ether-strike") {{
            description = "Ether Strike.";
            requirements(Category.turret, with(polyethylene, 800, surgeAlloy, 300, harmonicSteel, 150));
            range = 640f;
            shoot.firstShotDelay = 8f;
            reload = 200f;
            shake = 8f;
            shootEffect = Fx.lancerLaserShoot;
            smokeEffect = Fx.none;
            heatColor = Color.red;
            size = 3;
            scaledHealth = 310;
            targetAir = false;
            shootSound = Sounds.laser;
            hasLiquids = true;
            liquidCapacity = 100f;
            consumeLiquid(ether, 0.8f);
            consumePower(15f);

            shootType = new LaserBulletType(700){{
                colors = new Color[]{ether.color.cpy().a(0.8f), ether.color, Color.white};
                chargeEffect = new MultiEffect(Fx.lancerLaserCharge, Fx.lancerLaserChargeBegin);

                buildingDamageMultiplier = 0.15f;
                hitEffect = Fx.hitLancer;
                hitSize = 16;
                lifetime = 25f;
                drawSize = 400f;
                collidesAir = false;
                length = 650f;
                ammoMultiplier = 1f;
                pierceCap = -1;
                statusDuration = 60*3f;
            }};
        }};

        damageImproveComponent = new DamageImproveComponent("damage-improve-component"){{
            requirements(Category.turret, with(surgeAlloy, 200, nickel, 200));
            researchCostMultiplier = 10f;
            size = 1;
            damageMultiplierImprovement = 0.75f;
            extraPowerConsumption = 0.4f;
            consumePower(1.5f);
        }};

        speedImproveComponent = new SpeedImproveComponent("speed-improve-component"){{
            requirements(Category.turret, with(surgeAlloy, 200, nickel, 200));
            researchCostMultiplier = 10f;
            size = 1;
            speedMultiplierImprovement = 0.4f;
            extraPowerConsumption = 0.4f;
            consumePower(1.5f);
        }};

        powerEfficiencyComponent = new BaseComponent("power-efficiency-component"){{
            requirements(Category.turret, with(surgeAlloy, 200, nickel, 200));
            researchCostMultiplier = 10f;
            size = 1;
            isApplyLast = true;
            extraPowerConsumption = -0.2f;
            consumePower(1.5f);
        }};

        stella = new ItemTurret("stella"){{
            description = "Based on Mk5, called as stella by its strong power.";
            requirements(Category.turret, ItemStack.with(graphite, 800, silicon, 1100, titanium, 700, thorium, 550, plastanium, 400));
            researchCostMultiplier = 1.8f;
            health = 1600;
            size = 4;
            reload = 220f;
            ammoPerShot = 50;
            rotateSpeed = 6f;
            maxAmmo = 1000;
            shoot.shots = 4;
            shoot.shotDelay = 3.5f;
            range = 895f;
            shake = 9f;
            shootSound = Sounds.shootBig;
            recoil = 11f;
            recoilTime = 15f;
            consumeAmmoOnce = false;
            ammoUseEffect = Fx.casing2;

            coolant = consumeCoolant(0.8f);
            coolantMultiplier = 6f;

            ammo(titanium, new BasicBulletType(30f, 1800){{
                lifetime = 400f;
                pierce = true;
                pierceBuilding = true;
                removeAfterPierce = false;
                trailColor = Color.blue;
                trailLength = 12;
                trailWidth = 6f;
                buildingDamageMultiplier = 0.4f;
                hitShake = 7f;
                hitEffect = Fx.blastExplosion;
            }}, thorium, new BasicBulletType(30f, 2430){{
                lifetime = 400f;
                pierce = true;
                pierceBuilding = true;
                removeAfterPierce = false;
                trailColor = Color.pink;
                trailLength = 14;
                trailWidth = 6f;
                buildingDamageMultiplier = 0.4f;
                hitShake = 8f;
                hitEffect = Fx.blastExplosion;
            }}, graphite, new BasicBulletType(30f, 1350){{
                lifetime = 400f;
                pierce = true;
                pierceBuilding = true;
                removeAfterPierce = false;
                trailColor = Color.gray;
                trailLength = 14;
                trailWidth = 6f;
                buildingDamageMultiplier = 0.4f;
                hitShake = 8f;
                hitEffect = Fx.blastExplosion;
                status = StatusEffects.freezing;
                statusDuration = 600f;
                reloadMultiplier = 1.5f;
            }}, nickel, new BasicBulletType(30f, 2160){{
                lifetime = 400f;
                pierce = true;
                pierceBuilding = true;
                removeAfterPierce = false;
                trailColor = Color.pink;
                trailLength = 14;
                trailWidth = 6f;
                buildingDamageMultiplier = 0.4f;
                hitShake = 8f;
                hitEffect = Fx.blastExplosion;
            }});

            limitRange(4f);
        }};

        lightingPoint = new PowerTurret("lighting-point"){{
            description = "Shooting power lighting point.";
            requirements(Category.turret, ItemStack.with(copper, 220, silicon, 400, titanium, 350));
            researchCostMultiplier = 1.8f;
            health = 820;
            size = 3;
            reload = 75f;
            rotateSpeed = 9f;
            range = 530f;
            inaccuracy = 2f;
            shoot.shots = 3;
            shoot.shotDelay = 3f;
            shootSound = Sounds.lasershoot;
            recoil = 1.5f;
            recoilTime = 16f;
            consumeAmmoOnce = false;

            coolant = consumeCoolant(0.6f);

            shootType = new BasicBulletType(4f, 135f){{
                shootEffect = new MultiEffect(Fx.lancerLaserCharge, new WaveEffect(){{
                    colorTo = Pal.surge;
                    sizeTo = 32f;
                    lifetime = 14f;
                    strokeFrom = 4f;
                }});
                splashDamage = 75f;
                splashDamageRadius = 40f;
                smokeEffect = Fx.smokeCloud;
                hitColor = Pal.surge;
                width = height = 16f;
                backColor = Pal.surge;
                frontColor = Color.white;
                shrinkX = shrinkY = 0f;
                despawnEffect = hitEffect = new ExplosionEffect(){{
                    waveColor = Pal.surge;
                    smokeColor = Color.gray;
                    sparkColor = Pal.orangeSpark;
                    waveStroke = 4f;
                    waveRad = 40f;
                }};
                despawnSound = Sounds.explosion;

                shootSound = Sounds.lasershoot;
            }};

            limitRange(4f);
        }};

        particleStream = new PowerTurret("particle-stream"){{
            description = "It's beautiful.";
            requirements(Category.turret, ItemStack.with(copper, 360, silicon, 500, thorium, 350, phaseFabric, 150, surgeAlloy, 150));
            researchCostMultiplier = 1.2f;
            health = 1350;
            size = 4;
            reload = 7.5f;
            rotateSpeed = 11f;
            range = 688f;
            shootSound = Sounds.lasershoot;
            recoil = 2f;
            recoilTime = 8f;
            consumeAmmoOnce = false;

            coolant = consumeCoolant(0.8f);

            shootType = new BasicBulletType(7.5f, 32f){{
                shootEffect = new MultiEffect(Fx.lancerLaserCharge, new WaveEffect(){{
                    colorTo = Color.sky;
                    sizeTo = 32f;
                    lifetime = 6f;
                    strokeFrom = 4.5f;
                }});
                splashDamage = 235f;
                splashDamageRadius = 48f;
                smokeEffect = Fx.smokeCloud;
                hitColor = Color.sky;
                width = height = 16f;
                backColor = Color.sky;
                frontColor = Color.white;
                shrinkX = shrinkY = 0f;
                buildingDamageMultiplier = 0.55f;
                despawnEffect = hitEffect = new ExplosionEffect(){{
                    waveColor = Color.sky;
                    smokeColor = Color.gray;
                    waveLife = 12f;
                    waveStroke = 4.5f;
                    waveRad = 55f;
                }};
                despawnSound = Sounds.explosion;

                shootSound = Sounds.lasershoot;
            }};

            shoot = new ShootBarrel(){{
                barrels = new float[]{
                        -12f, 0f, 0f,
                        0f, 0f, 0f,
                        8f, 0f, 0f,
                        -4f, 0f, 0f,
                        16f, 0f, 0f,
                        -8f, 0f, 0f,
                        4f, 0f, 0f,
                        -16f, 0f, 0f,
                        12f, 0f, 0f,
                };
            }};

            limitRange(4f);
        }};
    }
}