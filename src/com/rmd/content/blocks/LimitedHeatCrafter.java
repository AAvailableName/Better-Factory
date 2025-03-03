package com.rmd.content.blocks;

import arc.math.Mathf;
import mindustry.world.blocks.production.HeatCrafter;

public class LimitedHeatCrafter extends HeatCrafter {
    public float brokenOverHeat = 5f;
    public float meltdownOverHeat = 10f;
    public float heatEfficiency = 0.8f;
    public float coolantEfficiency = 0.8f;
    public float coolantConsumeEfficiency = 0.5f; // each tick

    public LimitedHeatCrafter(String name) {
        super(name);
    }

    public class LimitedHeatCrafterBuild extends HeatCrafterBuild {
        @Override
        public void update() {
            super.update();

            heat += consPower.capacity * heatEfficiency * Math.min(this.delta(), 4.0F);

            float over = Math.max(this.heat - heatRequirement, 0.0F);

            if (over >= brokenOverHeat) {
                health -= (over - brokenOverHeat) * maxHealth * 0.01f;
            }

            if (over >= meltdownOverHeat) {
                kill();
            }

            if (heat > 0.0F) {
                heat -= coolantConsumeEfficiency * coolantEfficiency;
                liquids.remove(liquids.current(), coolantConsumeEfficiency);
            }
        }

        @Override
        public float efficiencyScale() {
            float over = Math.max(this.heat - heatRequirement, 0.0F);

            if (over >= 0f && over < brokenOverHeat){
                return Math.min(Mathf.clamp(this.heat / heatRequirement) + over / heatRequirement * overheatScale, maxEfficiency);
            }else if(over >= brokenOverHeat){
                return Math.min(Mathf.clamp(this.heat / heatRequirement) + over / heatRequirement * overheatScale, maxEfficiency) * 0.2f;
            }else {
                return Math.min(Mathf.clamp(this.heat / heatRequirement), maxEfficiency) * 0.8f;
            }
        }
    }
}
