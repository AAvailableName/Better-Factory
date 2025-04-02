package com.rmd.content.blocks.experiment;

import arc.struct.ObjectMap;
import mindustry.entities.bullet.BulletType;
import mindustry.logic.LAccess;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatValues;

public class ExperimentalPowerTurret extends ExperimentalTurret {
    public BulletType shootType;

    public ExperimentalPowerTurret(String name){
        super(name);
        hasPower = true;
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(Stat.ammo, StatValues.ammo(ObjectMap.of(this, shootType)));
    }

    public void limitRange(float margin){
        limitRange(shootType, margin);
    }

    public class ExperimentalPowerTurretBuild extends ExperimentalTurretBuild {
        private BulletType copy;
        protected float originReload = -1f;
        protected float originUsage = -1f;

        private float powerMultiplier1 = 1f;
        private float powerIncrease1 = 0f;

        private float powerMultiplier2 = 1f;
        private float powerIncrease2 = 0f;

        @Override
        public void refresh() {
            super.refresh();

            if (originReload > 0) reload = originReload;
            if (copy != null) copy = shootType.copy();
        }

        @Override
        public void refreshPower() {
            super.refreshPower();

            powerMultiplier1 = 1f;
            powerIncrease1 = 0f;
            powerMultiplier2 = 1f;
            powerIncrease2 = 0f;
            consumePower(originUsage);
        }

        @Override
        public void update() {
            if (originUsage == -1f && consPower != null) originUsage = consPower.usage;
            if (originReload == -1f && reload > 0) originReload = reload;
            if (copy == null) copy = shootType.copy();

            super.update();
        }

        @Override
        public void extraPowerConsume() {
            if (components != null && !components.isEmpty()) {
                components.select(component -> !component.isApplyLast()).forEach(component -> {
                    if (component.isEnabled()) {
                        powerMultiplier1 *= 1+component.extraPowerConsumePercentage();
                        powerIncrease1 += component.extraPowerConsume();
                    }
                });

                components.select(ExperimentalBuildingComponent::isApplyLast).forEach(component -> {
                    if (component.isEnabled()) {
                        powerMultiplier2 *= 1+component.extraPowerConsumePercentage();
                        powerIncrease2 += component.extraPowerConsume();
                    }
                });

                consumePower((originUsage * powerMultiplier1 + powerIncrease1) * powerMultiplier2 + powerIncrease2);
            }
        }

        @Override
        public void updateTile(){
            unit.ammo(power.status * unit.type().ammoCapacity);

            super.updateTile();
        }

        @Override
        public double sense(LAccess sensor){
            return switch(sensor){
                case ammo -> power.status;
                case ammoCapacity -> 1;
                default -> super.sense(sensor);
            };
        }

        @Override
        public BulletType useAmmo(){
            return copy;
        }

        @Override
        public boolean hasAmmo(){
            return true;
        }

        @Override
        public BulletType peekAmmo(){
            return copy;
        }

        @Override
        public BulletType getBulletType() {
            return peekAmmo();
        }
    }
}
