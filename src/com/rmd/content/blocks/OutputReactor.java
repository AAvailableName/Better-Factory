package com.rmd.content.blocks;

import arc.Core;
import arc.util.Strings;
import arc.util.Time;
import mindustry.graphics.Pal;
import mindustry.logic.LAccess;
import mindustry.type.ItemStack;
import mindustry.ui.Bar;
import mindustry.world.blocks.power.NuclearReactor;
import mindustry.world.meta.Stat;
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

        if(outputItems != null){
            stats.add(Stat.output, StatValues.items(itemDuration, outputItems));
        }

        if (consumesPower) {
            addBar("power", (GeneratorBuild entity) -> new Bar(() ->
                    Core.bundle.format("bar.poweroutput",
                            Strings.fixed(Math.max(entity.getPowerProduction() - consPower.usage, 0) * 60 * entity.timeScale(), 1)),
                    () -> Pal.powerBar,
                    () -> entity.productionEfficiency));
        }
    }

    public class OutputReactorBuild extends NuclearReactor.NuclearReactorBuild {
        public float progress;

        @Override
        public void updateTile() {
            super.updateTile();

            progress += 1.0F / itemDuration * Time.delta;

            if (outputItems == null) return;

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
