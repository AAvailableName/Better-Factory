package com.rmd;

import com.rmd.content.*;
import mindustry.mod.Mod;

public class Main extends Mod{
    @Override
    public void loadContent(){
        BFItems.load();
        BFLiquids.load();

        BFStatusEffects.load();

        BFBlocks.load();
        BFSectors.load();

        BFTechTree.load();
    }
}
