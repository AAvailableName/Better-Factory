package com.rmd.content;

import arc.graphics.Color;
import com.rmd.content.blocks.EnvironmentalHeatCrafter;
import com.rmd.content.blocks.EnvironmentalHeatProducer;
import com.rmd.content.blocks.VoidDrill;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.content.StatusEffects;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.effect.MultiEffect;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.heat.HeatProducer;
import mindustry.world.blocks.power.PowerGenerator;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.consumers.ConsumeItemFlammable;

import static com.rmd.content.BFItems.oilResidue;
import static com.rmd.content.BFItems.polyethylene;
import static com.rmd.content.BFLiquids.*;
import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;

public class BFBlocks {
    public static Block fractionalDistillationTower, catalyticCracker, steamCracker, ethyleneHydrator,
            polyethylenePolymerizer, largePolyethylenePolymerizer, plastaniumInjector, largePlastaniumInjector;

    public static Block combustionHeater, powerHeater, environmentalHeater;

    public static Block voidDrillMarkI, voidDrillMarkII;

    public static Block ethanolPowerGenerator;

    public static Block tripleGunMarkV, tripleGunMarkVII, HPJ11CIWS;

    public static void load() {
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
            consumeCoolant(0.4f).optional(true, true);

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

            heatRequirement = 15f;
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
            consumePower(5f);
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
            consumePower(16f);
            range = 200f;
        }};

        tripleGunMarkV = new ItemTurret("triple-gun-mk5"){{
            description = "As an outdated weapon of the old era, it was replaced by the Mk6 due to insufficient firepower, and shortly thereafter, the Mk6 was in turn replaced by the Mk7 due to stability issues.";
            requirements(Category.turret, ItemStack.with(copper, 800, graphite, 800, silicon, 600, titanium, 700, plastanium, 200, blastCompound, 300));
            researchCostMultiplier = 1.1f;
            health = 1540;
            size = 4;
            reload = 720f;
            ammoPerShot = 10;
            rotateSpeed = 6f;
            maxAmmo = 200;
            inaccuracy = 6f;
            shoot.shotDelay = 3F;
            shoot.shots = 3;
            range = 573f;
            shake = 2f;
            shootSound = Sounds.shootBig;
            recoil = 7f;
            recoilTime = 45f;
            consumeAmmoOnce = false;
            ammoUseEffect = Fx.casing2;

            consumeCoolant(2f).optional(true, true);
            coolantMultiplier = 2f;

            ammo(titanium, new BasicBulletType(13f, 300){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamageRadius = 176f;
                splashDamage = 64f;
                drag = 0.005f;
                trailColor = Color.yellow;
                trailLength = 12;
                trailWidth = 6f;
                buildingDamageMultiplier = 1.2f;
                hitShake = 3f;
                hitEffect = Fx.blastExplosion;
            }}, thorium, new BasicBulletType(13f, 360){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamageRadius = 184f;
                splashDamage = 75f;
                drag = 0.005f;
                trailColor = Color.yellow;
                trailLength = 14;
                trailWidth = 6f;
                buildingDamageMultiplier = 1.2f;
                hitShake = 4f;
                hitEffect = Fx.blastExplosion;
            }}, pyratite, new BasicBulletType(15f, 44){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamageRadius = 256f;
                splashDamage = 30f;
                drag = 0.02f;
                trailColor = Pal.lightOrange;
                trailLength = 12;
                trailWidth = 4f;
                buildingDamageMultiplier = 1.2f;
                hitShake = 2f;
                status = StatusEffects.burning;
                statusDuration = 3600f;
                rangeChange = 40f;
            }}, blastCompound, new BasicBulletType(18f, 60){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamageRadius = 256f;
                splashDamage = 350f;
                drag = 0.015f;
                trailColor = Color.red;
                trailLength = 14;
                trailWidth = 4f;
                buildingDamageMultiplier = 2f;
                hitShake = 6f;
                rangeChange = 80f;
                hitEffect = new MultiEffect(Fx.blastExplosion, Fx.fireHit);
            }});

            limitRange();
        }};

        tripleGunMarkVII = new ItemTurret("triple-gun-mk7"){{
            description = "It has been built as the core of maritime defense, providing powerful firepower support.";
            requirements(Category.turret, ItemStack.with(copper, 500, graphite, 650, silicon, 800, titanium, 1100, plastanium, 400, blastCompound, 400));
            researchCostMultiplier = 1.8f;
            health = 1980;
            size = 5;
            reload = 3000f;
            ammoPerShot = 50;
            rotateSpeed = 3f;
            maxAmmo = 300;
            inaccuracy = 4f;
            shoot.shotDelay = 3F;
            shoot.shots = 3;
            range = 840f;
            shake = 3f;
            shootSound = Sounds.shootBig;
            recoil = 10f;
            recoilTime = 60f;
            consumeAmmoOnce = false;
            ammoUseEffect = Fx.casing2;

            consumeCoolant(2f).optional(true, true);

            ammo(titanium, new BasicBulletType(15f, 880){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamageRadius = 224f;
                splashDamage = 160f;
                reflectable = false;
                drag = 0.005f;
                trailColor = Color.yellow;
                trailLength = 20;
                trailWidth = 8f;
                buildingDamageMultiplier = 1.5f;
                hitShake = 4f;
                hitEffect = Fx.blastExplosion;
            }}, thorium, new BasicBulletType(15f, 960){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamageRadius = 244f;
                splashDamage = 200f;
                reflectable = false;
                drag = 0.002f;
                trailColor = Color.yellow;
                trailLength = 22;
                trailWidth = 8f;
                buildingDamageMultiplier = 1.5f;
                hitShake = 6f;
                hitEffect = Fx.blastExplosion;
            }}, pyratite, new BasicBulletType(18f, 75){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamageRadius = 376f;
                splashDamage = 96f;
                reflectable = false;
                drag = 0.02f;
                trailColor = Pal.lightOrange;
                trailLength = 18;
                trailWidth = 8f;
                buildingDamageMultiplier = 1.2f;
                hitShake = 2f;
                status = StatusEffects.burning;
                statusDuration = 3600f;
                rangeChange = 40f;
            }}, blastCompound, new BasicBulletType(22f, 90){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamageRadius = 376f;
                splashDamage = 600f;
                reflectable = false;
                drag = 0.015f;
                trailColor = Color.red;
                trailLength = 18;
                trailWidth = 8f;
                buildingDamageMultiplier = 2f;
                hitShake = 9f;
                rangeChange = 80f;
                hitEffect = new MultiEffect(Fx.blastExplosion, Fx.fireHit);
            }});

            limitRange();
        }};

        HPJ11CIWS = new ItemTurret("HPJ-11-CIWS"){{
            description = "It's crazy, can the logistics hold up?";
            requirements(Category.turret, ItemStack.with(copper, 500, graphite, 600, silicon, 500, titanium, 700, plastanium, 300));
            researchCostMultiplier = 1.8f;
            health = 880;
            size = 3;
            reload = 0.6f;
            shoot.shots = 10;
            rotateSpeed = 75f;
            maxAmmo = 5000;
            inaccuracy = 30f;
            range = 140f;
            shootSound = Sounds.shoot;
            consumeAmmoOnce = false;
            ammoUseEffect = Fx.casing2;

            consumeCoolant(2f).optional(true, true);

            ammo(copper, new BasicBulletType(18f, 8){{
                knockback = 0.1f;
                lifetime = 120.0F;
            }}, lead, new BasicBulletType(18f, 9){{
                knockback = 0.15f;
                lifetime = 120.0F;
            }}, metaglass, new BasicBulletType(18f, 11){{
                lifetime = 120.0F;

                fragBullet = new BasicBulletType(18f, 9){{
                    fragLifeMin = 8f;
                    fragLifeMax = 20f;
                    fragRandomSpread = 60f;
                    fragBullets = 3;
                    fragVelocityMin = 3.5f;
                    fragVelocityMax = 7f;
                }};
            }}, polyethylene, new BasicBulletType(18f, 13){{
                lifetime = 120.0F;

                fragBullet = new BasicBulletType(18f, 10){{
                    fragLifeMin = 8f;
                    fragLifeMax = 20f;
                    fragRandomSpread = 75f;
                    fragBullets = 5;
                    fragVelocityMin = 3.5f;
                    fragVelocityMax = 7f;

                    fragBullet = new BasicBulletType(18f, 7){{
                        fragLifeMin = 8f;
                        fragLifeMax = 20f;
                        fragRandomSpread = 360f;
                        fragBullets = 3;
                        fragVelocityMin = 3.5f;
                        fragVelocityMax = 7f;
                    }};
                }};
            }}, titanium, new BasicBulletType(18f, 15){{
                knockback = 0.2f;
                lifetime = 120.0F;

                pierce = true;
                pierceCap = 4;
                pierceBuilding = true;
                pierceDamageFactor = 0.8f;
                removeAfterPierce = false;
            }}, thorium, new BasicBulletType(18f, 18){{
                knockback = 0.22f;
                lifetime = 120.0F;

                pierce = true;
                pierceCap = 6;
                pierceBuilding = true;
                pierceDamageFactor = 0.85f;
                removeAfterPierce = false;
            }}, surgeAlloy, new BasicBulletType(18f, 27){{
                knockback = 0.3f;
                lifetime = 120.0F;

                pierce = true;
                pierceCap = 10;
                pierceBuilding = true;
                pierceDamageFactor = 0.9f;
                removeAfterPierce = false;

                lightningColor = Pal.surge;
                lightningLength = 20;
                lightningDamage = 15f;
                lightning = 3;
            }});

            limitRange();
        }};

        voidDrillMarkI = new VoidDrill("void-drill-mk1"){{
            description = "A drill that drills through void.";
            requirements(Category.production, ItemStack.with(Items.copper, 1000, Items.graphite, 800, Items.silicon, 800, Items.titanium, 1200));
            researchCostMultiplier = 0.6f;
            drillTime = 18f;
            hardnessDrillMultiplier = 0f;
            size = 3;
            hasPower = true;
            tier = 4;
            updateEffect = Fx.pulverizeMedium;
            drillEffect = Fx.mineBig;
            consumePower(30F);
            canOverdrive = false;
        }};

        voidDrillMarkII = new VoidDrill("void-drill-mk2"){{
            description = "A drill that drills through void with a faster speed.";
            requirements(Category.production, ItemStack.with(Items.copper, 1800, Items.graphite, 1600, Items.silicon, 1600, Items.titanium, 2000, thorium, 800, plastanium, 800));
            researchCostMultiplier = 0.6f;
            drillTime = 9f;
            hardnessDrillMultiplier = 0f;
            size = 3;
            hasPower = true;
            tier = 5;
            updateEffect = Fx.pulverizeMedium;
            drillEffect = Fx.mineBig;
            consumePower(80F);
            canOverdrive = false;
        }};

        ethanolPowerGenerator = new PowerGenerator("ethanol-power-generator"){{
            description = "Burn ethanol to get a lot of power.";
            requirements(Category.power, ItemStack.with(copper, 350, lead, 300, graphite, 250, silicon, 250, titanium, 200));
            health = 980;
            size = 3;
            powerProduction = 24.0F;
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.08F;
            consumeLiquid(ethanol, 1f);
            hasLiquids = true;
        }};
    }
}