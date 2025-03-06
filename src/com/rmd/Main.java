package com.rmd;

import com.rmd.content.BFBlocks;
import com.rmd.content.BFItems;
import com.rmd.content.BFLiquids;
import com.rmd.content.BFTechTree;
import mindustry.mod.Mod;

public class Main extends Mod{
    @Override
    public void loadContent(){
        BFItems.load();
        BFLiquids.load();

        BFBlocks.load();
        BFTechTree.load();
    }
}
