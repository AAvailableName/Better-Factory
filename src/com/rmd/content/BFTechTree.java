package com.rmd.content;

import arc.struct.Seq;
import mindustry.content.TechTree;
import mindustry.content.TechTree.TechNode;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives;
import mindustry.game.Objectives.Objective;
import mindustry.type.ItemStack;
import mindustry.type.Planet;
import mindustry.type.SectorPreset;

import static com.rmd.content.BFBlocks.*;
import static com.rmd.content.BFItems.*;
import static com.rmd.content.BFLiquids.ethanol;
import static com.rmd.content.BFSectors.fireImpact;
import static mindustry.content.Blocks.*;
import static mindustry.content.Items.*;
import static mindustry.content.Liquids.oil;
import static mindustry.content.SectorPresets.impact0078;
import static mindustry.content.SectorPresets.windsweptIslands;

public final class BFTechTree {
    static TechTree.TechNode context = null;

    public static void load() {
        // oil industry
        margeNode(sporePress, () -> {
            node(fractionalDistillationTower, Seq.with(produce(oil)), () -> {
                node(catalyticCracker);

                node(steamCracker, () -> {
                    node(polyethylenePolymerizer, () -> {
                        node(plastaniumInjector, () -> {
                            node(largePlastaniumInjector);
                        });

                        node(largePolyethylenePolymerizer);
                    });

                    node(ethyleneHydrator);
                });
            });
        });

        margeNode(combustionGenerator, () -> {
            // heater
            node(combustionHeater, () -> {
                node(powerHeater, () -> {
                    node(environmentalHeater);
                });
            });

            // power generator
            node(ethanolPowerGenerator, Seq.with(produce(ethanol)), () -> {
                node(voidPowerGenerator, Seq.with(produce(voidParticle)));
            });
        });

        // weapon
        margeNode(duo, () -> {
            node(simpleLightJavelinLauncher, Seq.with(sectorComplete(fireImpact), research(lancer), produce(etherAlloy)), () -> {
                node(lightJavelinLauncher, Seq.with(research(foreshadow)));
            });

            node(doubleGunMarkIV, Seq.with(produce(titanium)), () -> {
                node(doubleGunMarkVIII);
            });

            node(tripleGunMarkV, Seq.with(sectorComplete(windsweptIslands), produce(plastanium)), () -> {
                node(tripleGunMarkVII, Seq.with(sectorComplete(fireImpact)), () -> {
                    node(destroyer, Seq.with(produce(voidParticle), produce(darkMatter)));
                });
            });

            node(LMR3MRADS, Seq.with(sectorComplete(fireImpact)), () -> {
                node(LPJ11CIWS);
            });
        });

        // ether
        node(etherCollector, Seq.with(research(thoriumReactor)), () -> {
            node(etherCrystallizer, () -> {
                node(etherReassembler, () -> {
                    node(etherAlloyCompressor, Seq.with(produce(surgeAlloy)), () -> {
                        node(etherAlloyMelter);
                    });
                });

                node(etherFluidMixer, () -> {
                    node(harmonicSteelSynchronizer);
                });
            });
        });

        margeNode(thoriumReactor, () -> {
            node(etherAmplifier, Seq.with(produce(etherCrystal)));

            node(etherThoriumReactor, Seq.with(produce(etherCrystal)));
        });

        // drill
        margeNode(laserDrill, () -> {
            node(voidDrillMarkI, Seq.with(produce(titanium), produce(thorium), produce(plastanium), produce(phaseFabric)), () -> {
                node(voidDrillMarkII, () -> {
                    node(voidDrillMarkIII, Seq.with(produce(surgeAlloy)), () -> {
                        node(voidDrillMarkIV, Seq.with(produce(voidParticle)));
                    });
                });
            });
        });

        // sector preset
        margeNode(impact0078, () -> {
            node(fireImpact, Seq.with(research(tripleGunMarkV)));
        });
    }

    private static void margeNode(UnlockableContent parent, Runnable children) {
        context = TechTree.all.find(t -> t.content == parent);
        children.run();
    }

    private static void node(UnlockableContent content, ItemStack[] requirements, Seq<Objective> objectives, Runnable children) {
        TechNode node = new TechNode(context, content, requirements);
        if (objectives != null) node.objectives = objectives;

        TechNode prev = context;
        context = node;
        children.run();
        context = prev;
    }

    private static void node(UnlockableContent content, ItemStack[] requirements, Runnable children) {
        node(content, requirements, null, children);
    }

    private static void node(UnlockableContent content, Seq<Objective> objectives, Runnable children) {
        node(content, content.researchRequirements(), objectives, children);
    }

    private static void node(UnlockableContent content, Seq<Objective> objectives) {
        node(content, content.researchRequirements(), objectives, () -> {
        });
    }

    private static void node(UnlockableContent content, Runnable children) {
        node(content, content.researchRequirements(), children);
    }

    private static void node(UnlockableContent block) {
        node(block, () -> {
        });
    }

    private static Objective produce(UnlockableContent content) {
        return new Objectives.Produce(content);
    }

    private static Objective research(UnlockableContent content) {
        return new Objectives.Research(content);
    }

    private static Objective sectorComplete(SectorPreset sector) {
        return new Objectives.SectorComplete(sector);
    }

    private static Objective onSector(SectorPreset sector) {
        return new Objectives.OnSector(sector);
    }

    private static Objective onPlanet(Planet planet) {
        return new Objectives.OnPlanet(planet);
    }
}
