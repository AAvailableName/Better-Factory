package com.rmd.content.blocks;

import arc.math.Mathf;
import mindustry.world.blocks.production.HeatCrafter;

public class FractionalDistillationTower extends HeatCrafter {
    public FractionalDistillationTower(String name) {
        super(name);
    }

    public class FractionalDistillationTowerBuild extends HeatCrafterBuild {
        public float brokenLine = 5f;
        public float meltdown = 12f;

        @Override
        public void update() {
            super.update();

            float over = Math.max(this.heat - heatRequirement, 0.0F);

            if (over >= brokenLine) {
                health -= (over - brokenLine) * maxHealth * 0.01f;
            }

            if (over >= meltdown) {
                health = 0;
            }
        }

        @Override
        public float efficiencyScale() {
            float over = Math.max(this.heat - heatRequirement, 0.0F);

            if (over >= 0f && over < brokenLine){
                return Math.min(Mathf.clamp(this.heat / heatRequirement) + over / heatRequirement * overheatScale, maxEfficiency);
            }else if(over >= brokenLine){
                return Math.min(Mathf.clamp(this.heat / heatRequirement) + over / heatRequirement * overheatScale, maxEfficiency) * 0.2f;
            }else {
                return Math.min(Mathf.clamp(this.heat / heatRequirement), maxEfficiency) * 0.8f;
            }
        }
    }
}
