package com.rmd.content;

import arc.graphics.Color;
import com.rmd.content.blocks.EnvironmentalHeatCrafter;
import com.rmd.content.blocks.EnvironmentalHeatProducer;
import com.rmd.content.blocks.OutputGenerator;
import com.rmd.content.blocks.VoidDrill;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.UnitSorts;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.bullet.PointBulletType;
import mindustry.entities.effect.MultiEffect;
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
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.consumers.ConsumeItemFlammable;

import static com.rmd.content.BFItems.*;
import static com.rmd.content.BFLiquids.*;
import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;

public class BFBlocks {
    public static Block fractionalDistillationTower, catalyticCracker, steamCracker, ethyleneHydrator,
            polyethylenePolymerizer, largePolyethylenePolymerizer, plastaniumInjector, largePlastaniumInjector;

    public static Block combustionHeater, powerHeater, environmentalHeater;

    public static Block voidDrillMarkI, voidDrillMarkII, voidDrillMarkIII, voidDrillMarkIV;

    public static Block ethanolPowerGenerator, voidPowerGenerator;

    public static Block doubleGunMarkIV, doubleGunMarkVIII, simpleLightJavelinLauncher, lightJavelinLauncher
            , tripleGunMarkV, tripleGunMarkVII, LPJ11CIWS, LMR3MRADS, destroyer;

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

        doubleGunMarkIV = new ItemTurret("double-gun-mk4"){{
            description = "The twin-mounted anti-aircraft gun is capable of engaging both air and ground targets, and it is cost-effective!";
            requirements(Category.turret, ItemStack.with(copper, 300, graphite, 300, silicon, 200, titanium, 180));
            researchCostMultiplier = 0.5f;
            health = 960;
            size = 3;
            reload = 24f;
            ammoPerShot = 5;
            rotateSpeed = 12f;
            maxAmmo = 100;
            inaccuracy = 4f;
            shoot.shotDelay = 3F;
            shoot.shots = 2;
            range = 514f;
            shake = 2f;
            shootSound = Sounds.shoot;
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
                hitEffect = Fx.blastExplosion;
            }}, thorium, new BasicBulletType(8f, 244){{
                lifetime = 400f;
                splashDamageRadius = 64f;
                splashDamage = 32f;
                trailColor = Color.yellow;
                trailLength = 9;
                trailWidth = 5f;
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
            }}, blastCompound, new BasicBulletType(18f, 20){{
                lifetime = 400f;
                splashDamagePierce = true;
                splashDamageRadius = 174f;
                splashDamage = 270f;
                trailColor = Color.red;
                trailLength = 9;
                trailWidth = 4f;
                hitShake = 4f;
                hitEffect = new MultiEffect(Fx.blastExplosion, Fx.fireHit);
                status = StatusEffects.blasted;
            }});

            limitRange();
        }};

        doubleGunMarkVIII = new ItemTurret("double-gun-mk8"){{
            description = "The twin-mounted anti-aircraft gun is capable of engaging both air and ground targets!";
            requirements(Category.turret, ItemStack.with(copper, 600, metaglass, 200, graphite, 600, silicon, 800, titanium, 1000));
            researchCostMultiplier = 0.7f;
            health = 1080;
            size = 3;
            reload = 9f;
            ammoPerShot = 5;
            rotateSpeed = 20f;
            maxAmmo = 240;
            inaccuracy = 2.2f;
            shoot.shotDelay = 3F;
            shoot.shots = 2;
            range = 514f;
            shake = 2f;
            shootSound = Sounds.shoot;
            recoil = 3f;
            recoilTime = 15f;
            consumeAmmoOnce = false;
            ammoUseEffect = Fx.casing2;

            coolant = consumeCoolant(2f);
            coolantMultiplier = 2f;

            ammo(titanium, new BasicBulletType(8f, 182){{
                lifetime = 400f;
                splashDamageRadius = 48f;
                splashDamage = 19f;
                trailColor = Color.yellow;
                trailLength = 7;
                trailWidth = 4f;
                hitEffect = Fx.blastExplosion;
            }}, thorium, new BasicBulletType(8f, 244){{
                lifetime = 400f;
                splashDamageRadius = 64f;
                splashDamage = 32f;
                trailColor = Color.yellow;
                trailLength = 9;
                trailWidth = 5f;
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
            }}, blastCompound, new BasicBulletType(18f, 20){{
                lifetime = 400f;
                splashDamagePierce = true;
                splashDamageRadius = 174f;
                splashDamage = 270f;
                trailColor = Color.red;
                trailLength = 9;
                trailWidth = 4f;
                hitShake = 4f;
                hitEffect = new MultiEffect(Fx.blastExplosion, Fx.fireHit);
                status = StatusEffects.blasted;
            }});

            limitRange();
        }};

        tripleGunMarkV = new ItemTurret("triple-gun-mk5"){{
            description = "As an outdated weapon of the old era, it was replaced by the Mk6 due to insufficient firepower, and shortly thereafter, the Mk6 was in turn replaced by the Mk7 due to stability issues.";
            requirements(Category.turret, ItemStack.with(copper, 800, graphite, 800, silicon, 600, titanium, 650, plastanium, 200));
            researchCostMultiplier = 1.4f;
            health = 1540;
            size = 4;
            reload = 480f;
            ammoPerShot = 10;
            rotateSpeed = 6f;
            maxAmmo = 200;
            inaccuracy = 8f;
            shoot.shotDelay = 3F;
            shoot.shots = 3;
            range = 573f;
            shake = 2f;
            shootSound = Sounds.shootBig;
            recoil = 7f;
            recoilTime = 45f;
            consumeAmmoOnce = false;
            ammoUseEffect = Fx.casing2;

            coolant = consumeCoolant(2f);
            coolantMultiplier = 2f;

            ammo(titanium, new BasicBulletType(13f, 300){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamageRadius = 176f;
                splashDamage = 64f;
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
                trailColor = Color.red;
                trailLength = 14;
                trailWidth = 4f;
                buildingDamageMultiplier = 2f;
                hitShake = 6f;
                rangeChange = 80f;
                hitEffect = new MultiEffect(Fx.blastExplosion, Fx.fireHit);
                status = StatusEffects.blasted;
            }});

            limitRange();
        }};

        tripleGunMarkVII = new ItemTurret("triple-gun-mk7"){{
            description = "It has been built as the core of maritime defense, providing powerful firepower support.";
            requirements(Category.turret, ItemStack.with(copper, 600, metaglass, 300, graphite, 750, silicon, 900, titanium, 1400, plastanium, 600));
            researchCostMultiplier = 2.1f;
            health = 1980;
            size = 5;
            reload = 1500f;
            ammoPerShot = 50;
            rotateSpeed = 3f;
            maxAmmo = 300;
            inaccuracy = 6f;
            shoot.shotDelay = 3F;
            shoot.shots = 3;
            range = 840f;
            shake = 3f;
            shootSound = Sounds.shootBig;
            recoil = 10f;
            recoilTime = 60f;
            consumeAmmoOnce = false;
            ammoUseEffect = Fx.casing2;


            coolant = consumeCoolant(2f);

            ammo(titanium, new BasicBulletType(15f, 880){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamagePierce = true;
                splashDamageRadius = 224f;
                splashDamage = 160f;
                reflectable = false;
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
                splashDamagePierce = true;
                splashDamageRadius = 244f;
                splashDamage = 200f;
                reflectable = false;
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
                splashDamagePierce = true;
                splashDamageRadius = 376f;
                splashDamage = 96f;
                reflectable = false;
                trailColor = Pal.lightOrange;
                trailLength = 18;
                trailWidth = 6f;
                buildingDamageMultiplier = 1.2f;
                hitShake = 2f;
                status = StatusEffects.burning;
                statusDuration = 3600f;
                rangeChange = 40f;
            }}, blastCompound, new BasicBulletType(22f, 90){{
                targetAir = false;
                lifetime = 400f;
                collidesAir = false;
                splashDamagePierce = true;
                splashDamageRadius = 376f;
                splashDamage = 600f;
                reflectable = false;
                trailColor = Color.red;
                trailLength = 18;
                trailWidth = 6f;
                buildingDamageMultiplier = 2f;
                hitShake = 9f;
                rangeChange = 80f;
                hitEffect = new MultiEffect(Fx.blastExplosion, Fx.fireHit);
                status = StatusEffects.blasted;
            }});

            limitRange();
        }};

        destroyer = new ItemTurret("destroyer"){{
            description = "Good luck.";
            requirements(Category.turret, ItemStack.with(voidParticle, 500, darkMatter, 200));
            researchCost = ItemStack.with(voidParticle, 600, darkMatter, 320);
            health = 9999;
            size = 6;
            reload = 6000f;
            ammoPerShot = 1;
            rotateSpeed = 3f;
            maxAmmo = 20;
            range = 880f;
            shake = 9.9f;
            recoil = 100f;
            recoilTime = 600f;
            ammoUseEffect = Fx.casing3;
            shootSound = Sounds.railgun;
            unitSort = UnitSorts.strongest;
            consumePower(99f);

            ammo(voidParticle, new PointBulletType(){{
                shootEffect = Fx.instShoot;
                hitEffect = Fx.instHit;
                smokeEffect = Fx.smokeCloud;
                trailEffect = Fx.instTrail;
                despawnEffect = Fx.instBomb;
                trailSpacing = 20.0F;
                splashDamageRadius = 99.9F;
                splashDamage = 99999.9F;
                speed = range;
                hitShake = 9.9F;
                ammoMultiplier = 1.0F;
            }});
        }};

        LPJ11CIWS = new ItemTurret("LPJ-11-CIWS"){{
            description = "It's crazy, can the logistics hold up?";
            requirements(Category.turret, ItemStack.with(copper, 500, graphite, 600, silicon, 500, titanium, 700, plastanium, 300));
            researchCostMultiplier = 1.8f;
            health = 880;
            size = 3;
            reload = 0.6f;
            shoot.shots = 10;
            shoot.shotDelay = 0.06f;
            rotateSpeed = 90f;
            maxAmmo = 5000;
            inaccuracy = 30f;
            range = 140f;
            shootSound = Sounds.shoot;
            consumeAmmoOnce = false;
            ammoUseEffect = Fx.casing2;

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
            }}, blastCompound, new BasicBulletType(18f, 5){{
                lifetime = 120.0F;
                splashDamageRadius = 38.4f;
                splashDamage = 20f;
                trailColor = Color.red;
                trailLength = 4;
                trailWidth = 1.5f;
                hitShake = 0.9f;
                hitEffect = new MultiEffect(Fx.blastExplosion, Fx.fireHit);
                status = StatusEffects.blasted;
            }});

            limitRange();
        }};

        LMR3MRADS = new ItemTurret("LMR-3-MRADS"){{
            description = "Previously a commonly used anti-aircraft firepower, it boasted relatively superior range and high accuracy, while its lower logistical demands made it widely deployed in combat against MRKW.";
            requirements(Category.turret, ItemStack.with(copper, 350, graphite, 400, silicon, 550, titanium, 600, plastanium, 100));
            researchCostMultiplier = 1.2f;
            health = 720;
            size = 3;
            reload = 1.2f;
            shoot.shots = 2;
            shoot.shotDelay = 0.6f;
            rotateSpeed = 85f;
            maxAmmo = 2000;
            inaccuracy = 14f;
            range = 288f;
            shootSound = Sounds.shoot;
            consumeAmmoOnce = false;
            ammoUseEffect = Fx.casing2;
            ammoPerShot = 2;

            coolant = consumeCoolant(2f);

            ammo(copper, new BasicBulletType(18f, 10){{
                knockback = 0.1f;
                lifetime = 120.0F;
            }}, lead, new BasicBulletType(18f, 13){{
                knockback = 0.15f;
                lifetime = 120.0F;
            }}, metaglass, new BasicBulletType(18f, 17){{
                lifetime = 120.0F;

                fragBullet = new BasicBulletType(18f, 11){{
                    fragLifeMin = 20f;
                    fragLifeMax = 32f;
                    fragRandomSpread = 18f;
                    fragBullets = 4;
                    fragVelocityMin = 3.5f;
                    fragVelocityMax = 7f;
                }};
            }}, polyethylene, new BasicBulletType(18f, 21){{
                lifetime = 120.0F;

                fragBullet = new BasicBulletType(18f, 16){{
                    fragLifeMin = 20f;
                    fragLifeMax = 36f;
                    fragRandomSpread = 22f;
                    fragBullets = 7;
                    fragVelocityMin = 3.5f;
                    fragVelocityMax = 7f;

                    fragBullet = new BasicBulletType(18f, 11){{
                        fragLifeMin = 8f;
                        fragLifeMax = 20f;
                        fragRandomSpread = 360f;
                        fragBullets = 4;
                        fragVelocityMin = 3.5f;
                        fragVelocityMax = 7f;
                    }};
                }};
            }}, titanium, new BasicBulletType(18f, 30){{
                knockback = 0.2f;
                lifetime = 120.0F;

                pierce = true;
                pierceCap = 4;
                pierceBuilding = true;
                pierceDamageFactor = 0.8f;
                removeAfterPierce = false;
            }}, thorium, new BasicBulletType(18f, 35){{
                knockback = 0.22f;
                lifetime = 120.0F;

                pierce = true;
                pierceCap = 6;
                pierceBuilding = true;
                pierceDamageFactor = 0.85f;
                removeAfterPierce = false;
            }}, surgeAlloy, new BasicBulletType(18f, 88){{
                knockback = 0.3f;
                lifetime = 120.0F;

                pierce = true;
                pierceCap = 10;
                pierceBuilding = true;
                pierceDamageFactor = 0.9f;
                removeAfterPierce = false;

                lightningColor = Pal.surge;
                lightningLength = 20;
                lightningDamage = 18f;
                lightning = 7;
                ammoMultiplier = 1f;
            }}, blastCompound, new BasicBulletType(18f, 6){{
                lifetime = 120.0F;
                splashDamageRadius = 38.4f;
                splashDamage = 70f;
                trailColor = Color.red;
                trailLength = 4;
                trailWidth = 1.5f;
                hitShake = 1.6f;
                hitEffect = new MultiEffect(Fx.blastExplosion, Fx.fireHit);
                status = StatusEffects.blasted;
                ammoMultiplier = 1f;
            }});

            limitRange();
        }};

        simpleLightJavelinLauncher = new PowerTurret("simple-light-javelin-launcher") {{
            description = "Cheap, but it can only use one time!";
            requirements(Category.turret, ItemStack.with(copper, 80, lead, 80, graphite, 40, silicon, 120));
            health = 300;
            range = 640.0F;
            shoot.firstShotDelay = 90f;
            reload = 90.0F;
            shake = 2.0F;
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
                trailSpacing = 8.0F;
                damage = 700f;
                speed = range;
                hitShake = 9.9F;
                ammoMultiplier = 1.0F;
            }};

            limitRange(0f);
        }

            public class SimpleLightJavelinLauncherBuild extends PowerTurretBuild {
                @Override
                protected void shoot(BulletType type) {
                    super.shoot(type);
                    kill();
                }
            }
        };

        lightJavelinLauncher = new PowerTurret("light-javelin-launcher") {{
            description = "Hit me with the light javelin!";
            requirements(Category.turret, ItemStack.with(copper, 400, silicon, 700, titanium, 800, thorium, 800, surgeAlloy, 100));
            health = 980;
            range = 760.0F;
            shoot.firstShotDelay = 90f;
            recoil = 15f;
            recoilTime = 30f;
            reload = 90.0F;
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
                trailSpacing = 8.0F;
                damage = 820f;
                speed = range;
                hitShake = 9.9F;
                ammoMultiplier = 1.0F;
            }};

            limitRange(0f);
        }};

        voidDrillMarkI = new VoidDrill("void-drill-mk1"){{
            description = "A drill that drills through void.";
            requirements(Category.production, ItemStack.with(copper, 1000, graphite, 800, silicon, 800, titanium, 1200, plastanium, 800,
                    thorium, 800, phaseFabric, 800));
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
            consumePower(180F);
        }};

        voidDrillMarkIV = new VoidDrill("void-drill-mk4"){{
            description = "Faster, faster and faster.";
            requirements(Category.production, ItemStack.with(copper, 3000, graphite, 3000, silicon, 3000, titanium, 4000, plastanium, 3000,
                    thorium, 3000, phaseFabric, 2500, surgeAlloy, 1500, voidParticle, 200));
            researchCostMultiplier = 0.6f;
            researchCostMultipliers.put(voidParticle, 0.01f);
            drillTime = 2f;
            size = 4;
            tier = 10;
            itemCapacity = 400;
            lowestDrillTier = 3f;
            voidParticleChance = 0.008f; // 0.8%
            consumePower(300F);
        }};

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
            requirements(Category.power, ItemStack.with(copper, 500, silicon, 300, titanium, 250, thorium, 200, plastanium, 200, voidParticle, 50));
            researchCostMultipliers.put(voidParticle, 0.1f);
            health = 2880;
            size = 4;
            itemCapacity = 20;
            itemDuration = 600f;
            powerProduction = 40.0F;
            generateEffect = Fx.bigShockwave;
            generateEffectRange = 16;
            ambientSound = Sounds.shockBlast;
            ambientSoundVolume = 0.16F;
            consumeItem(voidParticle, 1);
            outputItem = new ItemStack(darkMatter, 1);
            explosionRadius = 38;
            explosionDamage = 7000;
        }};
    }
}