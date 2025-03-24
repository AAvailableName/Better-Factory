package com.rmd.content.blocks;

import arc.util.Log;
import arc.util.Threads;
import mindustry.entities.bullet.BulletType;
import mindustry.world.blocks.defense.turrets.PowerTurret;

public class DisposableTurret extends PowerTurret {
    public float extraDisposeTime; //s

    public DisposableTurret(String name) {
        super(name);
        extraDisposeTime = 0.05f;
    }

    public class DisposableTurretBuild extends PowerTurretBuild {
        @Override
        protected void shoot(BulletType type) {
            super.shoot(type);

            Threads.thread(() -> {
                try {
                    Thread.sleep((long) (type.lifetime/60 + extraDisposeTime)*1000);
                    kill();
                } catch (InterruptedException e) {
                    Log.err(e);
                }
            }).start();
        }
    }
}
