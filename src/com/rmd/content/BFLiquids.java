package com.rmd.content;

import arc.graphics.Color;
import mindustry.content.StatusEffects;
import mindustry.type.Liquid;

import static mindustry.content.Liquids.oil;
import static mindustry.content.Liquids.water;

public class BFLiquids {
    public static Liquid naphtha, heavyOil;

    public static void load() {
        naphtha = new Liquid("naphtha", Color.valueOf("d6c789")){{
            heatCapacity = 0.75f;
            viscosity = 0.4f;
            explosiveness = 1.2f;
            flammability = 1.2f;
            temperature = 0.8f;
            effect = StatusEffects.tarred;
            boilPoint = 0.55f;
            barColor = Color.valueOf("d4c28d");
            gasColor = Color.valueOf("d6c789");
            gasColor.a = 0.4f;
            canStayOn.add(water);
            canStayOn.add(oil);
        }};

        heavyOil = new Liquid("heavy-oil", Color.valueOf("272727")){{
            heatCapacity = 0.75f;
            viscosity = 1.7f;
            explosiveness = 1.4f;
            flammability = 1.4f;
            temperature = 1.2f;
            effect = StatusEffects.tarred;
            boilPoint = 1.2f;
            gasColor = Color.grays(0.6f);
            barColor = Color.grays(0.8f);
        }};
    }
}