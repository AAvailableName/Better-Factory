package com.rmd.content.blocks.experiment.components;

import com.rmd.content.blocks.experiment.BaseComponent;
import com.rmd.content.blocks.experiment.ExperimentalTurret;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;
import mindustry.world.meta.StatUnit;

public class SpeedImproveComponent extends BaseComponent {
    public SpeedImproveComponent(String name) {
        super(name);
    }

    public float speedMultiplierImprovement = 0f;
    public float speedImprovement = 0f;

    @Override
    public void setStats() {
        super.setStats();

        if (speedMultiplierImprovement > 0f) stats.add(new Stat("speedMultiplier", StatCat.function), "[YELLOW]-" + speedMultiplierImprovement * 100 + StatUnit.percent.localized());
        if (speedImprovement > 0f) stats.add(Stat.speedIncrease, "[YELLOW]-" + speedImprovement);

    }

    public class SpeedImproveBuild extends BaseComponentBuilding{
        @Override
        public void apply(ExperimentalTurret.ExperimentalTurretBuild betaTurret) {
            var b = (ExperimentalTurret) betaTurret.block;

            b.reload *= 1-speedMultiplierImprovement;
            b.reload -= speedImprovement;
            b.reload = Math.max(b.reload, 0f);
        }
    }
}
