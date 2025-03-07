package com.rmd.content;

import arc.graphics.Color;
import mindustry.content.StatusEffects;
import mindustry.type.Liquid;

import static mindustry.content.Liquids.oil;
import static mindustry.content.Liquids.water;

public class BFLiquids {
    public static Liquid naphtha, heavyOil, ethylene;

    public static void load() {
        naphtha = new Liquid("naphtha", Color.valueOf("d6c789")){{
            heatCapacity = 0.75f;
            viscosity = 0.55f;
            explosiveness = 1.2f;
            flammability = 1.3f;
            temperature = 0.8f;
            effect = StatusEffects.tarred;
            boilPoint = 0.7f;
            barColor = Color.valueOf("d4c28d");
            gasColor = Color.valueOf("d6c789");
            gasColor.a = 0.4f;
            canStayOn.add(water);
            canStayOn.add(oil);
        }};

        heavyOil = new Liquid("heavy-oil", Color.valueOf("232323")){{
            heatCapacity = 0.75f;
            viscosity = 1.7f;
            explosiveness = 1.1f;
            flammability = 1.2f;
            temperature = 1.4f;
            effect = StatusEffects.tarred;
            boilPoint = 2.2f;
            gasColor = Color.grays(0.3f);
            barColor = Color.grays(0.15f);
        }};

        ethylene = new Liquid("ethylene", Color.valueOf("f2eeb9")){{
            heatCapacity = 1.2f;
            viscosity = 0.3f;
            explosiveness = 1.55f;
            flammability = 1.7f;
            temperature = 0.2f;
            effect = StatusEffects.freezing;
            boilPoint = 0.2f;
            barColor = Color.valueOf("f2eeb9");
            gasColor = Color.valueOf("fcfbe2");
            canStayOn.add(water);
            canStayOn.add(oil);
        }};
    }
}