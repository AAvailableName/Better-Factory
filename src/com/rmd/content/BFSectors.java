package com.rmd.content;

import mindustry.type.SectorPreset;

import static mindustry.content.Planets.serpulo;

public class BFSectors {
    public static SectorPreset fireImpact, experimentField;

    public static void load() {
        fireImpact = new SectorPreset("fire-impact", serpulo, 29) {{
            difficulty = 7.2f;
        }};

        experimentField = new SectorPreset("experiment-field", serpulo, 61) {{
            difficulty = 15f;
        }};
    }
}
