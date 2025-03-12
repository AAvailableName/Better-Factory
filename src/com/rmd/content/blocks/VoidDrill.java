package com.rmd.content.blocks;

import arc.math.Mathf;
import mindustry.Vars;
import mindustry.game.Team;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.production.Drill;

public class VoidDrill extends Drill {
    public VoidDrill(String name) {
        super(name);

        Vars.content.blocks().forEach(block -> {
            if (block instanceof Floor f && (
                    !f.wallOre && f.itemDrop != null && f.itemDrop.hardness <= tier &&
                            f.itemDrop != blockedItem && (Vars.indexer.isBlockPresent(f) || Vars.state.isMenu()))) {
                if (canMine(f.newBuilding().tile)) itemArray.add(f.itemDrop);
            }
        });
    }

    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        return true;
    }

    protected void countOre(Tile tile) {
        returnItem = itemArray.random();
        returnCount = size * size;
    }

    public class VoidDrillBuild extends DrillBuild {
        @Override
        public void updateTile() {
            if (timer(timerDump, 5.0F)) {
                dump(dominantItem != null && items.has(dominantItem) ? dominantItem : null);
            }

            if (dominantItem != null) {
                timeDrilled += warmup * delta();
                float delay = getDrillTime(dominantItem);
                if (items.total() < itemCapacity && dominantItems > 0 && efficiency > 0.0F) {
                    float speed = Mathf.lerp(1.0F, liquidBoostIntensity, optionalEfficiency) * efficiency;
                    lastDrillSpeed = speed * (float)dominantItems * warmup / delay;
                    warmup = Mathf.approachDelta(warmup, speed, warmupSpeed);
                    progress += delta() * (float)dominantItems * speed * warmup;
                    if (Mathf.chanceDelta(updateEffectChance * warmup)) {
                        updateEffect.at(x + Mathf.range((float)size * 2.0F), y + Mathf.range((float)size * 2.0F));
                    }

                    if (dominantItems > 0 && progress >= delay && items.total() < itemCapacity) {
                        offload(dominantItem);
                        progress %= delay;
                        if (wasVisible && Mathf.chanceDelta(updateEffectChance * warmup)) {
                            drillEffect.at(x + Mathf.range(drillEffectRnd), y + Mathf.range(drillEffectRnd), dominantItem.color);
                        }
                    }

                } else {
                    this.lastDrillSpeed = 0.0F;
                    this.warmup = Mathf.approachDelta(this.warmup, 0.0F, warmupSpeed);
                }
            }
        }
    }
}
