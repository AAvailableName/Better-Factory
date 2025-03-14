package com.rmd.content.blocks;

import arc.math.Mathf;
import arc.util.Time;
import mindustry.type.ItemStack;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatValues;

public class OutputGenerator extends ConsumeGenerator {
    public ItemStack outputItem;

    public OutputGenerator(String name) {
        super(name);
    }

    @Override
    public void setStats() {
        super.setStats();

        if (this.outputItem != null) {
            this.stats.add(Stat.output, StatValues.items(itemDuration, outputItem));
        }
    }

    public class OutputGeneratorBuild extends ConsumeGenerator.ConsumeGeneratorBuild {
        public float progress;
        public float totalProgress;
        public float warmup;

        public void updateTile() {
            super.updateTile();

            if (efficiency > 0.0F) {
                progress += 1.0F / itemDuration * Time.delta;
                warmup = Mathf.approachDelta(warmup, 1.0f, warmupSpeed);
            } else {
                warmup = Mathf.approachDelta(warmup, 0.0F, warmupSpeed);
            }

            totalProgress += warmup * Time.delta;
            if (progress >= 1.0F) {
                progress %= 1.0F;
                items.add(outputItem.item, outputItem.amount);
            }

            dump(outputItem.item);
        }
    }
}
