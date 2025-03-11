package com.rmd.content;

import arc.struct.Seq;
import mindustry.content.SectorPresets;
import mindustry.content.TechTree;
import mindustry.content.TechTree.TechNode;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives;
import mindustry.game.Objectives.Objective;
import mindustry.type.ItemStack;

import static com.rmd.content.BFBlocks.*;
import static mindustry.content.Blocks.*;
import static mindustry.content.Items.blastCompound;
import static mindustry.content.Items.titanium;
import static mindustry.content.Liquids.oil;

public final class BFTechTree {
    static TechTree.TechNode context = null;

    public static void load(){
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
                });
            });
        });

        margeNode(combustionGenerator, () -> {
            node(combustionHeater, () -> {
                node(powerHeater, () -> {
//                    node(environmentalHeater);
                });
            });
        });

        margeNode(duo, () -> {
            node(tripleGunMarkVII, Seq.with(new Objectives.SectorComplete(SectorPresets.impact0078), new Objectives.Produce(blastCompound)), () -> {
                node(HPJ11CIWS, Seq.with(new Objectives.SectorComplete(SectorPresets.impact0078)));
            });
        });

        margeNode(laserDrill, () -> {
            node(voidDrillMarkI, Seq.with(new Objectives.Produce(titanium)));
        });
    }


    private static void margeNode(UnlockableContent parent, Runnable children){
        context = TechTree.all.find(t -> t.content == parent);
        children.run();
    }

    private static void node(UnlockableContent content, ItemStack[] requirements, Seq<Objective> objectives, Runnable children){
        TechNode node = new TechNode(context, content, requirements);
        if(objectives != null) node.objectives = objectives;

        TechNode prev = context;
        context = node;
        children.run();
        context = prev;
    }

    private static void node(UnlockableContent content, ItemStack[] requirements, Runnable children){
        node(content, requirements, null, children);
    }

    private static void node(UnlockableContent content, Seq<Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives, children);
    }

    private static void node(UnlockableContent content, Seq<Objective> objectives){
        node(content, content.researchRequirements(), objectives, () -> {});
    }

    private static void node(UnlockableContent content, Runnable children){
        node(content, content.researchRequirements(), children);
    }

    private static void node(UnlockableContent block){
        node(block, () -> {});
    }

    private static void nodeProduce(UnlockableContent content, Seq<Objective> objectives, Runnable children){
        node(content, content.researchRequirements(), objectives.add(new Objectives.Produce(content)), children);
    }

    private static void nodeProduce(UnlockableContent content, Runnable children){
        nodeProduce(content, Seq.with(), children);
    }

    private static void nodeProduce(UnlockableContent content){
        nodeProduce(content, Seq.with(), () -> {});
    }
}
