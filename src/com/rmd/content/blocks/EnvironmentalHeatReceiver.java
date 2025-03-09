package com.rmd.content.blocks;

import mindustry.gen.Building;

public interface EnvironmentalHeatReceiver {
    void receiveHeat(Building provider, float heat);
    void releaseReceiver(Building provider);
    float heat();
    float heatRequirement();
}
