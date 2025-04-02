package com.rmd.content.blocks;

import mindustry.entities.Mover;
import mindustry.entities.bullet.BulletType;
import mindustry.world.blocks.defense.turrets.PowerTurret;

public class DisposableTurret extends PowerTurret {
    public DisposableTurret(String name) {
        super(name);
    }

    public class DisposableTurretBuild extends PowerTurretBuild {
        @Override
        protected void bullet(BulletType type, float xOffset, float yOffset, float angleOffset, Mover mover) {
            super.bullet(type, xOffset, yOffset, angleOffset, mover);

            kill();
        }
    }
}
