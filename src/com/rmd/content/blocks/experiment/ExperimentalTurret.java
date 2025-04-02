package com.rmd.content.blocks.experiment;

import arc.struct.Seq;
import mindustry.entities.bullet.BulletType;
import mindustry.world.blocks.defense.turrets.Turret;

public abstract class ExperimentalTurret extends Turret {
    public ExperimentalTurret(String name) {
        super(name);
    }

    public abstract class ExperimentalTurretBuild extends TurretBuild {
        protected Seq<ExperimentalBuildingComponent> components = new Seq<>();
        protected float originRangeChange = -1f;

        public void refresh() {
            components.clear();
            if (originRangeChange != -1f && getBulletType() != null) getBulletType().rangeChange = originRangeChange;
        }

        @Override
        public void update() {
            if (originRangeChange == -1f && getBulletType() != null) originRangeChange = getBulletType().rangeChange;

            refreshPower();

            applyComponents();

            extraPowerConsume();

            super.update();

            refresh();
        }

        public void refreshPower() {

        }

        public void addComponent(ExperimentalBuildingComponent component) {
            components.addUnique(component);
        }

        public void applyComponents() {
            if (components != null && !components.isEmpty()) {
                components.select(component -> !component.isApplyLast()).forEach(component -> {
                    if (component.isEnabled()) component.apply(this);
                });

                components.select(ExperimentalBuildingComponent::isApplyLast).forEach(component -> {
                    if (component.isEnabled()) component.apply(this);
                });
            }
        }

        public abstract void extraPowerConsume();

        public abstract BulletType getBulletType();
    }
}
