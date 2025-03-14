package com.rmd.content.blocks;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.struct.Seq;
import arc.util.Tmp;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.Vars;
import mindustry.graphics.Drawf;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

public class EnvironmentalHeatProducer extends GenericCrafter {
    public float heatOutput = 10.0F;
    public float warmupRate = 0.15F;
    public float range = 80f;
    public Color color;

    public EnvironmentalHeatProducer(String name) {
        super(name);
        color = Color.valueOf("feb380");
        canOverdrive = false;
    }

    public void setStats() {
        super.setStats();
        stats.add(Stat.range, range / 8.0F, StatUnit.blocks);
        stats.add(Stat.output, heatOutput, StatUnit.heatUnits);
    }

    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);
        Drawf.dashCircle((float)(x * 8) + offset, (float)(y * 8) + offset, range, color);
        Vars.indexer.eachBlock(Vars.player.team(), (float)(x * 8) + offset, (float)(y * 8) + offset, range, (other) -> other instanceof EnvironmentalHeatReceiver, (other) -> {
            Drawf.selected(other, Tmp.c1.set(color).a(Mathf.absin(4.0F, 1.0F)));
        });
    }

    public class EnvironmentalHeatProducerBuild extends GenericCrafterBuild {
        public float heat;

        @Override
        public void updateTile() {
            super.updateTile();

            this.heat = Mathf.approachDelta(this.heat, heatOutput * this.efficiency, warmupRate * this.delta());

            Seq<EnvironmentalHeatReceiver> receivers = new Seq<>();

            Vars.indexer.eachBlock(this, range, b -> b instanceof EnvironmentalHeatReceiver, b -> {
                EnvironmentalHeatReceiver receiver = (EnvironmentalHeatReceiver) b;

                receivers.add(receiver);
            });

            receivers.forEach(receiver -> receiver.receiveHeat(this, heat));
        }

        public void drawSelect() {
            Vars.indexer.eachBlock(this, range, (other) -> other instanceof EnvironmentalHeatReceiver, (other) -> {
                Drawf.selected(other, Tmp.c1.set(color).a(Mathf.absin(4.0F, 1.0F)));
            });
            Drawf.dashCircle(x, y, range, color);
        }

        public void write(Writes write) {
            super.write(write);
            write.f(this.heat);
        }

        public void read(Reads read, byte revision) {
            super.read(read, revision);
            this.heat = read.f();
        }
    }
}
