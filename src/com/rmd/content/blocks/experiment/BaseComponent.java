package com.rmd.content.blocks.experiment;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.util.Tmp;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.world.Block;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;
import mindustry.world.meta.StatUnit;

public class BaseComponent extends Block {
    public boolean isApplyLast = false;
    public float extraPowerConsumption = 0f; // percentage
    public float powerConsumptionIncrease = 0f;

    public BaseComponent(String name) {
        super(name);

        update = true;
        hasPower = true;

        rotateDraw = false;
        rotate = true;
        canOverdrive = false;
        drawArrow = true;
    }

    @Override
    public void setStats() {
        super.setStats();

        if (powerConsumptionIncrease != 0f) {
            stats.add(new Stat("powerUseIncrease", StatCat.power), "[YELLOW]" + ((powerConsumptionIncrease > 0f) ? "+" : "") + powerConsumptionIncrease * 60 + StatUnit.perSecond.localized());
        }

        if (extraPowerConsumption != 0f) {
            stats.add(new Stat("powerMultiplier", StatCat.power), "[YELLOW]" + ((extraPowerConsumption > 0f) ? "+" : "") + extraPowerConsumption * 100  + StatUnit.percent.localized());
        }
    }

    @Override
    public boolean outputsItems(){
        return false;
    }

    public class BaseComponentBuilding extends Building implements ExperimentalBuildingComponent {
        public BaseComponentBuilding() {
            super();
        }

        @Override
        public void updateTile() {
            super.updateTile();

            Building b = nearby(rotation);

            if (b != null && b.team == team && isEnabled() && b instanceof ExperimentalTurret.ExperimentalTurretBuild betaTurret) {
                betaTurret.addComponent(this);
            }
        }

        @Override
        public void drawSelect() {
            Building b = nearby(rotation);

            if (b == this) return;

            if (b != null && b.team == team) {
                if (b instanceof ExperimentalTurret.ExperimentalTurretBuild) Drawf.selected(b, Tmp.c1.set(Color.gold).a(Mathf.absin(4f, 1f)));
                else Drawf.selected(b, Tmp.c1.set(Color.red).a(Mathf.absin(4f, 1f)));
            }
        }

        @Override
        public void apply(ExperimentalTurret.ExperimentalTurretBuild betaTurret) {

        }

        @Override
        public float extraPowerConsume() {
            return powerConsumptionIncrease;
        }

        @Override
        public float extraPowerConsumePercentage() {
            return extraPowerConsumption;
        }

        @Override
        public boolean isEnabled() {
            return enabled() && !dead() && power.status > 0;
        }

        @Override
        public boolean isApplyLast() {
            return block != null && isApplyLast;
        }
    }
}
