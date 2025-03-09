package com.rmd.content.blocks;

import mindustry.gen.Building;
import mindustry.world.meta.Stat;

import java.util.concurrent.ConcurrentHashMap;

public class EnvironmentalHeatCrafter extends LimitedHeatCrafter {
    public EnvironmentalHeatCrafter(String name) {
        super(name);
        maxEfficiency = 1f;
    }

    public void setStats() {
        super.setStats();
        stats.remove(Stat.maxEfficiency);
    }

    public class EnvironmentalHeatCrafterBuild extends LimitedHeatCrafter.LimitedHeatCrafterBuild implements EnvironmentalHeatReceiver {
        public ConcurrentHashMap<Building, Float> envHeatProduce = new ConcurrentHashMap<>();

        public EnvironmentalHeatCrafterBuild() {
            super();
        }

        @Override
        public void updateTile() {
            super.updateTile();

            heat += (float) (envHeatProduce.values().stream().mapToDouble(f -> f).sum());

            envHeatProduce.keySet().forEach(b -> {
                if (b.dead || !b.enabled || b.power.status <= 0) releaseReceiver(b);
            });
        }

        @Override
        public void receiveHeat(Building provider, float heat) {
            if (envHeatProduce.containsKey(provider)) envHeatProduce.replace(provider, heat);
            else envHeatProduce.put(provider, heat);
        }

        @Override
        public void releaseReceiver(Building provider) {
            envHeatProduce.remove(provider);
        }

        @Override
        public float heat() {
            return heat;
        }
    }
}