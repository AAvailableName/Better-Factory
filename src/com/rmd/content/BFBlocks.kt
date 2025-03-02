package com.rmd.content

import com.rmd.content.blocks.FractionalDistillationTower
import mindustry.content.Fx
import mindustry.content.Items
import mindustry.content.Liquids
import mindustry.gen.Sounds
import mindustry.type.Category
import mindustry.type.ItemStack
import mindustry.type.LiquidStack
import mindustry.world.Block

class BFBlocks {
    companion object {
        // 原油分馏塔 => 消耗石油，产出少量硫化物、大量石脑油、少量重油
        lateinit var fractionalDistillationTower : Block
        // 催化裂化器 => 消耗少量重油，产出大量石脑油、一定量油渣(oilResidue)
        lateinit var catalyticCracker : Block
        // 塑料压塑机 => 消耗石脑油、水，产出塑料
        lateinit var polyethyleneCompressor : Block
        // 塑钢注塑机 => 消耗塑料、钛，产出塑钢
        lateinit var plastaniumInjector : Block

        fun load(){
            fractionalDistillationTower = FractionalDistillationTower("fractional-distillation-tower").apply {
                requirements(
                    Category.crafting,
                    ItemStack.with(Items.copper, 330, Items.graphite, 175, Items.silicon, 240, Items.titanium, 400)
                )
                size = 3
                craftTime = 765f
                liquidCapacity = 120f
                craftEffect = Fx.steam
                ambientSound = Sounds.steam
                hasPower = true
                hasLiquids = true
                hasItems = true
                outputLiquids = LiquidStack.with(BFLiquids.naphtha, 1f, BFLiquids.heavyOil, 0.5f)
                outputItem = ItemStack(Items.pyratite, 2)
                consumePower(7f)
                consumeLiquid(Liquids.oil, 1f)

                heatRequirement = 8f
                overheatScale = 2f
                maxEfficiency = 1.5f
            }
        }
    }
}