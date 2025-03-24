package com.rmd.content.blocks;

import arc.util.Time;
import mindustry.logic.LAccess;
import mindustry.type.ItemStack;
import mindustry.world.blocks.power.NuclearReactor;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import mindustry.world.meta.StatValues;

public class OutputReactor extends NuclearReactor {
    public ItemStack outputItem;
    public ItemStack[] outputItems;

    public OutputReactor(String name) {
        super(name);
        hasItems = true;
    }

    @Override
    public void init() {
        if(outputItems == null && outputItem != null){
            outputItems = new ItemStack[]{outputItem};
        }

        if(outputItems != null) hasItems = true;

        super.init();
    }

    @Override
    public void setStats() {
        super.setStats();

        if((hasItems && itemCapacity > 0) || outputItems != null){
            stats.add(Stat.productionTime, itemDuration / 60f, StatUnit.seconds);
        }

        if(outputItems != null){
            stats.add(Stat.output, StatValues.items(itemDuration, outputItems));
        }
    }

    public class OutputReactorBuild extends NuclearReactor.NuclearReactorBuild {
        public float progress;

        @Override
        public void updateTile() {
            super.updateTile();

            progress += 1.0F / itemDuration * Time.delta;

            if (progress >= 1.0F) {
                progress %= 1.0F;
                items.add(outputItem.item, outputItem.amount);
            }

            dump(outputItem.item);
        }

        @Override
        public double sense(LAccess sensor){
            if(sensor == LAccess.progress) return progress;
            return super.sense(sensor);
        }
    }
}
