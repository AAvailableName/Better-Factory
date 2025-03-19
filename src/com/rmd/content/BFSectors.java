package com.rmd.content;

import mindustry.type.SectorPreset;

import static mindustry.content.Planets.serpulo;

public class BFSectors {
    public static SectorPreset fireImpact;

    public static void load() {
        fireImpact = new SectorPreset("fire-impact", serpulo, 29) {{
            difficulty = 7.2f;
        }};
    }
}
