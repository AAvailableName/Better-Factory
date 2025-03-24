package com.rmd.content.blocks;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.util.Time;
import arc.util.Tmp;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.Block;
import mindustry.world.blocks.defense.OverdriveProjector;
import mindustry.world.consumers.ConsumeItems;
import mindustry.world.meta.*;

import static mindustry.Vars.tilesize;

public class SingleOverdrive extends Block {
    public final int timerUse = timers++;

    public float reload = 60f;
    public float speedBoost = 1.5f;
    public float speedBoostPhase = 0.75f;
    public float useTime = 400f;
    public boolean hasBoost = true;
    public Color baseColor = Color.valueOf("feb380");
    public Color phaseColor = Color.valueOf("ffd59e");

    public SingleOverdrive(String name){
        super(name);
        solid = true;
        update = true;
        group = BlockGroup.projectors;
        hasPower = true;
        hasItems = true;
        emitLight = true;
        lightRadius = 50f;
        envEnabled |= Env.space;

        rotateDraw = false;
        rotate = true;
        canOverdrive = false;
        drawArrow = true;
    }

    @Override
    public boolean outputsItems(){
        return false;
    }


    @Override
    public void setStats(){
        stats.timePeriod = useTime;
        super.setStats();

        stats.add(Stat.speedIncrease, "+" + (int)(speedBoost * 100f - 100) + "%");
        stats.add(Stat.productionTime, useTime / 60f, StatUnit.seconds);

        if(hasBoost && findConsumer(f -> f instanceof ConsumeItems) instanceof ConsumeItems items){
            stats.remove(Stat.booster);
            stats.add(Stat.booster, StatValues.itemBoosters("+{0}%", stats.timePeriod, speedBoostPhase * 100f, 0, items.items, this::consumesItem));
        }
    }

    @Override
    public void setBars(){
        super.setBars();
        addBar("boost", (OverdriveProjector.OverdriveBuild entity) -> new Bar(() -> Core.bundle.format("bar.boost", Mathf.round(Math.max((entity.realBoost() * 100 - 100), 0))), () -> Pal.accent, () -> entity.realBoost() / (hasBoost ? speedBoost + speedBoostPhase : speedBoost)));
    }

    public class SingleOverdriveBuild extends Building {
        public float heat, charge = Mathf.random(reload), phaseHeat, smoothEfficiency;

        @Override
        public void drawLight(){
            Drawf.light(x, y, lightRadius * smoothEfficiency, baseColor, 0.7f * smoothEfficiency);
        }

        @Override
        public void updateTile(){
            smoothEfficiency = Mathf.lerpDelta(smoothEfficiency, efficiency, 0.08f);
            heat = Mathf.lerpDelta(heat, efficiency > 0 ? 1f : 0f, 0.08f);
            charge += heat * Time.delta;

            if(hasBoost){
                phaseHeat = Mathf.lerpDelta(phaseHeat, optionalEfficiency, 0.1f);
            }

            if(charge >= reload){
                charge = 0f;

                Point2 p = getEdges()[regionRotated1-1];
                Building b = nearby(p.x, p.y);

                if (b.team == team && b.block.canOverdrive) {
                    applyBoost(realBoost(), reload + 1f);
                }
            }

            if(timer(timerUse, useTime) && efficiency > 0){
                consume();
            }
        }

        public float realBoost(){
            return (speedBoost + phaseHeat * speedBoostPhase) * efficiency;
        }

        @Override
        public void drawSelect(){
            Point2 p = getEdges()[regionRotated1-1];
            Building b = nearby(p.x, p.y);

            if (b.team == team && b.block.canOverdrive) {
                Drawf.selected(b, Tmp.c1.set(baseColor).a(Mathf.absin(4f, 1f)));
            }
        }

        @Override
        public void draw(){
            super.draw();

            float f = 1f - (Time.time / 100f) % 1f;

            Draw.color(baseColor, phaseColor, phaseHeat);
            Draw.alpha(heat * Mathf.absin(Time.time, 50f / Mathf.PI2, 1f) * 0.5f);
            Draw.alpha(1f);
            Lines.stroke((2f * f + 0.1f) * heat);

            float r = Math.max(0f, Mathf.clamp(2f - f * 2f) * size * tilesize / 2f - f - 0.2f), w = Mathf.clamp(0.5f - f) * size * tilesize;
            Lines.beginLine();
            for(int i = 0; i < 4; i++){
                Lines.linePoint(x + Geometry.d4(i).x * r + Geometry.d4(i).y * w, y + Geometry.d4(i).y * r - Geometry.d4(i).x * w);
                if(f < 0.5f) Lines.linePoint(x + Geometry.d4(i).x * r - Geometry.d4(i).y * w, y + Geometry.d4(i).y * r + Geometry.d4(i).x * w);
            }
            Lines.endLine(true);

            Draw.reset();
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(heat);
            write.f(phaseHeat);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            heat = read.f();
            phaseHeat = read.f();
        }
    }
}
