package com.rmd.content.blocks;

import arc.Core;
import arc.math.Mathf;
import arc.util.Strings;
import arc.util.Time;
import mindustry.graphics.Pal;
import mindustry.logic.LAccess;
import mindustry.type.ItemStack;
import mindustry.ui.Bar;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatValues;

public class OutputGenerator extends ConsumeGenerator {
    public ItemStack outputItem;
    public ItemStack[] outputItems;

    public OutputGenerator(String name) {
        super(name);
        hasItems = true;
    }

    @Override
    public void init() {
        if(outputItems == null && outputItem != null){
            outputItems = new ItemStack[]{outputItem};
        }

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

    public class OutputGeneratorBuild extends ConsumeGenerator.ConsumeGeneratorBuild {
        public float progress;
        public float warmup;

        @Override
        public void updateTile() {
            super.updateTile();

            if (efficiency > 0.0F) {
                progress += 1.0F / itemDuration * Time.delta;
                warmup = Mathf.approachDelta(warmup, 1.0f, warmupSpeed);
            } else {
                warmup = Mathf.approachDelta(warmup, 0.0F, warmupSpeed);
            }

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
