package com.rmd.content;

import arc.util.Strings;
import mindustry.type.ItemStack;
import mindustry.ui.ItemImage;
import mindustry.ui.Styles;
import mindustry.world.meta.StatValue;

public class BFStatValues {
    public static StatValue itemChance(float chance, ItemStack item){
        return (table) -> {
            table.add(new ItemImage(item.item.uiIcon, item.amount));
            table.add(item.item.localizedName + "\n [yellow]" + Strings.fixed(chance * 100.0F, 2) + "%[]").padLeft(2.0F).padRight(5.0F).style(Styles.outlineLabel);
        };
    }
}
