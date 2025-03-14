package com.rmd.content;

import arc.graphics.Color;
import mindustry.type.Item;


public class BFItems {
    public static Item polyethylene, oilResidue, voidParticle, darkMatter;

    public static void load() {
        polyethylene = new Item("polyethylene", Color.valueOf("FFFFFF")) {{
            description = "Cheap and durable.";
            hardness = 2;
            cost = 0.5f;
        }};

        oilResidue = new Item("oil-residue", Color.brown) {{
            description = "Residue from the oil.";
            hardness = 1;
            flammability = 0.2f;
            explosiveness = 0.15f;
            buildable = false;
        }};

        voidParticle= new Item("void-particle"){{
            description = "A mysterious particle from the void.";
            hardness = 50;
            cost = 8f;
        }};

        darkMatter = new Item("dark-matter") {{
            description = "A mysterious substance with immense energy potential.";
            hardness = 40;
            cost = 6.5f;
        }};
    }
}