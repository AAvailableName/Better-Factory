package com.rmd;

import com.rmd.content.BFBlocks;
import com.rmd.content.BFItems;
import com.rmd.content.BFLiquids;
import com.rmd.content.BFTechTree;
import mindustry.mod.Mod;

class Main extends Mod{
    public void loadContent(){
        BFBlocks.load();
        BFItems.load();
        BFLiquids.load();
        BFTechTree.load();
    }
}
