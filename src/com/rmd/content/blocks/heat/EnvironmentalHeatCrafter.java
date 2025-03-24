package com.rmd.content.blocks.heat;

import java.util.concurrent.ConcurrentHashMap;

public class EnvironmentalHeatCrafter extends LimitedHeatCrafter {
    public EnvironmentalHeatCrafter(String name) {
        super(name);
    }

    public class EnvironmentalHeatCrafterBuild extends LimitedHeatCrafter.LimitedHeatCrafterBuild implements EnvironmentalHeatReceiver {
        private ConcurrentHashMap<EnvironmentalHeatProducer.EnvironmentalHeatProducerBuild, Float> envHeat = new ConcurrentHashMap<>();

        public EnvironmentalHeatCrafterBuild() {
            super();
        }

        @Override
        public void updateTile() {
            super.updateTile();

            envHeat.keySet().forEach(EnvironmentalHeatProducer.EnvironmentalHeatProducerBuild::updateTile);

            heat += (float) envHeat.values().stream().mapToDouble(f -> f).sum();
            envHeat.clear();
        }

        @Override
        public void receiveHeat(EnvironmentalHeatProducer.EnvironmentalHeatProducerBuild producer, float heat) {
            if (envHeat.containsKey(producer)){
                envHeat.replace(producer, Math.min(Math.max(heat, 0f), producer.heat));
            }else {
                envHeat.put(producer, Math.min(Math.max(heat, 0f), producer.heat));
            }
        }

        @Override
        public float heat() {
            return heat;
        }
    }
}