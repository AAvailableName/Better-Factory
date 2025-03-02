package com.rmd.content

import arc.graphics.Color
import mindustry.type.Item


class BFItems {
    companion object {
        lateinit var polyethylene : Item
        lateinit var oilResidue : Item

        fun load(){
            polyethylene = Item("polyethylene", Color.valueOf("FFFFFF")).apply {
                hardness = 2
                flammability = 0.15f
                cost = 1.2f
            }

            oilResidue = Item("oil-residue", Color.brown).apply {
                hardness = 1
                flammability = 0.15f
                cost = 1.2f
            }
        }
    }
}