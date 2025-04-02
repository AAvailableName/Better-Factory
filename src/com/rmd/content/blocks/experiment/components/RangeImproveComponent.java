package com.rmd.content.blocks.experiment.components;

import com.rmd.content.blocks.experiment.BaseComponent;
import com.rmd.content.blocks.experiment.ExperimentalTurret;
import mindustry.entities.bullet.BulletType;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

public class RangeImproveComponent extends BaseComponent {
    public RangeImproveComponent(String name) {
        super(name);
    }

    public float rangeImprovement = 0f;

    @Override
    public void setStats() {
        super.setStats();

        if (rangeImprovement != 0f) stats.add(Stat.shootRange, "[YELLOW]+" + rangeImprovement / 8  + StatUnit.blocks.localized());
    }

    public class RangeImproveBuild extends BaseComponentBuilding{
        @Override
        public void apply(ExperimentalTurret.ExperimentalTurretBuild betaTurret) {
            BulletType bt = betaTurret.getBulletType();

            if (bt != null) {
                bt.rangeChange += rangeImprovement;
            }
        }
    }
}