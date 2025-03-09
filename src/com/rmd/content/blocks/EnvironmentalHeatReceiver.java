package com.rmd.content.blocks;

public interface EnvironmentalHeatReceiver {
    void receiveHeat(EnvironmentalHeatProducer.EnvironmentalHeatProducerBuild provider, float heat);
    float heat();
    float heatRequirement();
}
