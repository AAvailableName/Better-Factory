package com.rmd.content;

import arc.graphics.Color;
import mindustry.type.Item;


public class BFItems {
    public static Item polyethylene, oilResidue;

    public static void load() {
        polyethylene = new Item("polyethylene", Color.valueOf("FFFFFF")) {{
            hardness = 2;
            cost = 1.2f;
        }};

        oilResidue = new Item("oil-residue", Color.brown) {{
            hardness = 1;
            flammability = 0.2f;
            explosiveness = 0.15f;
            cost = 1.2f;
        }};
    }
}