package com.rmd.content;

import arc.struct.Seq;
import mindustry.content.TechTree;
import mindustry.content.TechTree.TechNode;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives;
import mindustry.game.Objectives.Objective;
import mindustry.type.ItemStack;

import static com.rmd.content.BFBlocks.*;
import static com.rmd.content.BFItems.darkMatter;
import static com.rmd.content.BFItems.voidParticle;
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
            node(fractionalDistillationTower, Seq.with(new Objectives.Produce(oil)), () -> {
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
            node(ethanolPowerGenerator, Seq.with(new Objectives.Produce(ethanol)), () -> {
                node(voidPowerGenerator, Seq.with(new Objectives.Produce(voidParticle)));
            });
        });

        // weapon
        margeNode(duo, () -> {
            node(doubleGunMarkIV, Seq.with(new Objectives.Produce(titanium)), () -> {
                node(doubleGunMarkVIII);
            });

            node(tripleGunMarkV, Seq.with(new Objectives.SectorComplete(windsweptIslands), new Objectives.Produce(plastanium)), () -> {
                node(tripleGunMarkVII, Seq.with(new Objectives.SectorComplete(fireImpact)), () -> {
                    node(destroyer, Seq.with(new Objectives.Produce(voidParticle), new Objectives.Produce(darkMatter)));
                });
            });

            node(LMR3MRADS, Seq.with(new Objectives.SectorComplete(fireImpact)), () -> {
                node(LPJ11CIWS);
            });
        });

        margeNode(lancer, () -> {
            node(simpleLightJavelinLauncher, Seq.with(new Objectives.SectorComplete(fireImpact)), () -> {
                node(lightJavelinLauncher, Seq.with(new Objectives.Research(foreshadow), new Objectives.Produce(surgeAlloy)));
            });
        });

        // drill
        margeNode(laserDrill, () -> {
            node(voidDrillMarkI, Seq.with(new Objectives.Produce(titanium), new Objectives.Produce(thorium),
                    new Objectives.Produce(plastanium), new Objectives.Produce(phaseFabric)), () -> {
                node(voidDrillMarkII, () -> {
                    node(voidDrillMarkIII, Seq.with(new Objectives.Produce(surgeAlloy)), () -> {
                        node(voidDrillMarkIV, Seq.with(new Objectives.Produce(voidParticle)));
                    });
                });
            });
        });

        // sector preset
        margeNode(impact0078, () -> {
            node(fireImpact, Seq.with(new Objectives.Research(tripleGunMarkV)));
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
}
