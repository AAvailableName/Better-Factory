package com.rmd.content.blocks.experiment.components;

import com.rmd.content.blocks.experiment.BaseComponent;
import com.rmd.content.blocks.experiment.ExperimentalTurret;
import mindustry.entities.bullet.BulletType;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

public class DamageImproveComponent extends BaseComponent {
    public DamageImproveComponent(String name) {
        super(name);
    }

    public float damageMultiplierImprovement = 0f;
    public float damageImprovement = 0f;

    @Override
    public void setStats() {
        super.setStats();

        if (damageMultiplierImprovement > 0f) stats.add(Stat.damageMultiplier, "[YELLOW]+" + damageMultiplierImprovement * 100  + StatUnit.percent.localized());
        if (damageImprovement > 0f) stats.add(Stat.damage, "[YELLOW]+" + damageImprovement);
    }

    public class DamageImproveBuild extends BaseComponentBuilding{
        @Override
        public void apply(ExperimentalTurret.ExperimentalTurretBuild betaTurret) {
            BulletType bt = betaTurret.getBulletType();

            if (bt.damage > 0) {
                bt.damage *= 1+damageMultiplierImprovement;
                bt.damage += damageImprovement;
            }

            if (bt.splashDamage > 0) {
                bt.splashDamage *= 1+damageMultiplierImprovement;
                bt.splashDamage += damageImprovement;
            }
        }
    }
}
