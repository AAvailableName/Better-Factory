package com.rmd.content;

import mindustry.type.Item;


public class BFItems {
    public static Item polyethylene, oilResidue, voidParticle, darkMatter,
            etherCore, etherCrystal, etherAlloy, harmonicSteel, nickel;

    public static void load() {
        etherCore = new Item("ether-core") {{
            description = "The core of ether.";
            hardness = 3;
            cost = 1.5f;
        }};

        etherCrystal = new Item("ether-crystal") {{
            description = "Crystallized form of ether with enhanced energy properties.";
            hardness = 1;
            cost = 2.0f;
            buildable = false;
            charge = 4f;
        }};

        etherAlloy = new Item("ether-alloy") {{
            description = "Alloy infused with ether for enhanced structural integrity.";
            hardness = 5;
            cost = 8.0f;
            charge = 7f;
        }};

        harmonicSteel = new Item("harmonic-steel") {{
            hardness = 6;
            cost = 3.5f;
            radioactivity = 0.5f;
            charge = 1.25f;
        }};

        nickel = new Item("nickel") {{
            description = "Hard and corrosion-resistant metallic element.";
            hardness = 2;
            cost = 0.8f;
        }};

        polyethylene = new Item("polyethylene") {{
            description = "Cheap and durable.";
            hardness = 2;
            cost = 0.5f;
        }};

        oilResidue = new Item("oil-residue") {{
            description = "Residue from the oil.";
            hardness = 1;
            flammability = 0.2f;
            explosiveness = 0.15f;
            buildable = false;
        }};

        voidParticle = new Item("void-particle"){{
            description = "A mysterious particle from the void.";
            hardness = 50;
            cost = 12f;
        }};

        darkMatter = new Item("dark-matter") {{
            description = "A mysterious substance with immense energy potential.";
            hardness = 40;
            cost = 8f;
        }};
    }
}