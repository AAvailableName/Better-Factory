package com.rmd.content;

import arc.graphics.Color;
import mindustry.content.StatusEffects;
import mindustry.type.Liquid;

import static mindustry.content.Liquids.oil;
import static mindustry.content.Liquids.water;

public class BFLiquids {
    public static Liquid naphtha, heavyOil, ethylene, ethanol, ether, etherFluid;

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
            temperature = 1.3f;
            effect = StatusEffects.tarred;
            boilPoint = 3.5f;
            gasColor = Color.grays(0.5f);
            barColor = Color.grays(0.35f);
        }};

        ethylene = new Liquid("ethylene", Color.valueOf("f2eeb9")){{
            heatCapacity = 1.2f;
            viscosity = 0.3f;
            explosiveness = 1.25f;
            flammability = 1.1f;
            temperature = 0.5f;
            effect = StatusEffects.freezing;
            boilPoint = 0.9f;
            barColor = Color.valueOf("f2eeb9");
            gasColor = Color.valueOf("fcfbe2");
            canStayOn.add(water);
            canStayOn.add(oil);
        }};

        ethanol = new Liquid("ethanol", Color.valueOf("596ab8").a(0.5f)){{
            heatCapacity = 1.0f;
            viscosity = 0.4f;
            explosiveness = 1.15f;
            flammability = 1.4f;
            temperature = 0.2f;
            boilPoint = 0.8f;
            barColor = Color.white.cpy().a(0.8f);
            gasColor = Color.clear;
            canStayOn.add(water);
            canStayOn.add(oil);
            coolant = true;
        }};

        ether = new Liquid("ether", Color.valueOf("7F00FF")){{
            barColor = Color.valueOf("7F00FF");
            gasColor = Color.valueOf("aa59fc");
            coolant = false;
        }};

        etherFluid = new Liquid("ether-fluid", Color.valueOf("9e6bfe")){{
            barColor = Color.valueOf("9e6bfe");
            gasColor = Color.valueOf("6819f9");
            coolant = false;
        }};
    }
}