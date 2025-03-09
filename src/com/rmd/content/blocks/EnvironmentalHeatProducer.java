package com.rmd.content.blocks;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.struct.Seq;
import arc.util.Tmp;
import mindustry.Vars;
import mindustry.graphics.Drawf;
import mindustry.world.blocks.heat.HeatProducer;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

public class EnvironmentalHeatProducer extends HeatProducer {
    public float range = 80f;
    public Color baseColor;
    public Color phaseColor;

    public EnvironmentalHeatProducer(String name) {
        super(name);
        this.baseColor = Color.valueOf("feb380");
        this.phaseColor = Color.valueOf("ffd59e");
    }

    public void setStats() {
        super.setStats();
        stats.add(Stat.range, range / 8.0F, StatUnit.blocks);
    }

    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);
        Drawf.dashCircle((float)(x * 8) + this.offset, (float)(y * 8) + this.offset, this.range, this.baseColor);
        Vars.indexer.eachBlock(Vars.player.team(), (float)(x * 8) + this.offset, (float)(y * 8) + this.offset, this.range, (other) -> other instanceof EnvironmentalHeatReceiver, (other) -> {
            Drawf.selected(other, Tmp.c1.set(this.baseColor).a(Mathf.absin(4.0F, 1.0F)));
        });
    }

    public class EnvironmentalHeatProducerBuild extends HeatProducerBuild {
        private final Seq<EnvironmentalHeatReceiver> receivers = new Seq<>();
        private float realHeat = heat;

        @Override
        public void updateTile() {
            super.updateTile();

            realHeat = heat;

            distributeHeat();
        }

        public void distributeHeat() {
            Seq<EnvironmentalHeatReceiver> cur = new Seq<>();

            Vars.indexer.eachBlock(this, range, b -> b instanceof EnvironmentalHeatReceiver, b -> {
                var receiver = (EnvironmentalHeatReceiver)b;

                cur.add(receiver);
            });

            cur.forEach(receiver -> {
                if (!receivers.contains(receiver)) receivers.add(receiver);
            });

            receivers.forEach(receiver -> {
                if (!cur.contains(receiver)) {
                    receiver.releaseReceiver(this);
                    receivers.remove(receiver);
                }
            });

            Seq<EnvironmentalHeatReceiver> leftDown = new Seq<>();
            // from small to big
//            receivers.sort((a, b) -> (int) (a.heatRequirement() - b.heatRequirement()));

            receivers.forEach(receiver -> {
                float requirement = receiver.heatRequirement() - receiver.heat();

                if (realHeat < requirement) leftDown.add(receiver);
                else {
                    receiver.receiveHeat(this, requirement);
                    realHeat -= requirement;
                }
            });

            float heatPerCrafter = realHeat / leftDown.size;

            leftDown.forEach(receiver -> receiver.receiveHeat(this, heatPerCrafter));
        }

        public void drawSelect() {
            Vars.indexer.eachBlock(this, range, (other) -> {
                return other instanceof EnvironmentalHeatReceiver;
            }, (other) -> {
                Drawf.selected(other, Tmp.c1.set(baseColor).a(Mathf.absin(4.0F, 1.0F)));
            });
            Drawf.dashCircle(this.x, this.y, range, baseColor);
        }
    }
}
