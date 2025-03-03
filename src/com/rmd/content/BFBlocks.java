package com.rmd.content;

import com.rmd.content.blocks.LimitedHeatCrafter;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;

import static mindustry.content.Liquids.cryofluid;
import static mindustry.content.Liquids.oil;

public class BFBlocks {
    public static LimitedHeatCrafter fdt;

    public static Block catalyticCracker, polyethyleneCompressor, plastaniumInjector;

    public static void load() {
        fdt = new LimitedHeatCrafter("fractional-distillation-tower"){{
            requirements(Category.crafting, ItemStack.with(Items.copper, 330, Items.graphite, 175, Items.silicon, 240, Items.titanium, 400));
            size = 3;
            craftTime = 765f;
            liquidCapacity = 120f;
            craftEffect = Fx.steam;
            ambientSound = Sounds.steam;
            hasPower = true;
            hasLiquids = true;
            hasItems = true;
            outputLiquids = LiquidStack.with(BFLiquids.naphtha, 1f, BFLiquids.heavyOil, 0.5f);
            outputItem = new ItemStack(Items.pyratite, 2);
            consumePower(7f);
            consumeLiquid(oil, 1f);
            consumeLiquid(cryofluid, 0.4f).update(false);

            heatRequirement = 8f;
            brokenOverHeat = 4f;
            meltdownOverHeat = 8f;
            overheatScale = 2f;
            maxEfficiency = 1.5f;
        }};


    }
}
