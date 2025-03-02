package com.rmd.content

import arc.struct.Seq
import mindustry.content.Blocks
import mindustry.content.Liquids
import mindustry.content.TechTree
import mindustry.content.TechTree.TechNode
import mindustry.content.TechTree.node
import mindustry.ctype.UnlockableContent
import mindustry.game.Objectives.Objective
import mindustry.game.Objectives.Produce
import mindustry.type.ItemStack


class BFTechTree {
    companion object {
        var context: TechNode? = null

        fun load() {
            margeNode(Blocks.oilExtractor) {
                Companion.node(BFBlocks.fractionalDistillationTower, Seq.with(Produce(Liquids.oil))){}
            }


        }

        private fun margeNode(parent: UnlockableContent, children: Runnable) {
            val parnode = TechTree.all.find { t: TechNode -> t.content === parent }
            context = parnode
            children.run()
        }

        private fun node(
            content: UnlockableContent,
            requirements: Array<ItemStack>,
            objectives: Seq<Objective>?,
            children: Runnable
        ) {
            val node = TechNode(context, content, requirements)
            if (objectives != null) node.objectives = objectives
            val prev = context
            context = node
            children.run()
            context = prev
        }

        private fun node(content: UnlockableContent, requirements: Array<ItemStack>, children: Runnable) {
            node(content, requirements, null, children)
        }

        private fun node(content: UnlockableContent, objectives: Seq<Objective>, children: Runnable) {
            node(content, content.researchRequirements(), objectives, children)
        }

        private fun node(content: UnlockableContent, children: Runnable) {
            node(content, content.researchRequirements(), children)
        }
    }
}
