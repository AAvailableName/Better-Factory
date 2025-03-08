package com.rmd.content.blocks;

import mindustry.world.blocks.production.HeatCrafter;
import mindustry.world.meta.Stat;

public class LimitedHeatCrafter extends HeatCrafter {

    public LimitedHeatCrafter(String name) {
        super(name);
        maxEfficiency = 1f;
    }

    public void setStats() {
        super.setStats();
        stats.remove(Stat.maxEfficiency);
    }

    public class LimitedHeatCrafterBuild extends HeatCrafter.HeatCrafterBuild {
        public LimitedHeatCrafterBuild() {
            super();
        }
    }
}
