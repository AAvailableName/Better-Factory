package com.rmd.content.blocks.experiment;

public interface ExperimentalBuildingComponent {
    void apply(ExperimentalTurret.ExperimentalTurretBuild betaTurret);

    float extraPowerConsumePercentage();

    float extraPowerConsume();

    boolean isEnabled();

    boolean isApplyLast();
}
