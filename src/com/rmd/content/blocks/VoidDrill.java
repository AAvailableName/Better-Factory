package com.rmd.content.blocks;

import arc.math.Mathf;
import com.rmd.content.BFStatValues;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.game.Team;
import mindustry.type.ItemStack;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.production.Drill;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatValues;

import static com.rmd.content.BFItems.voidParticle;

public class VoidDrill extends Drill {
    public float lowestDrillTier = 0f;
    public float voidParticleChance = 0f;

    public VoidDrill(String name) {
        super(name);
        hasPower = true;
	    hasLiquids = false;
        liquidCapacity = 0;
        updateEffect = Fx.pulverizeMedium;
        drillEffect = Fx.mineBig;
        canOverdrive = false;
        hardnessDrillMultiplier = 0f;
    }

    @Override
    public void init() {
        super.init();

        Vars.content.blocks().forEach(block -> {
            if (block instanceof Floor f && enableMine(f)) {
                itemArray.add(f.itemDrop);
            }
        });
    }

    @Override
    public void setStats() {
        super.setStats();
        stats.remove(Stat.drillTier);
        stats.add(Stat.drillTier, StatValues.drillables(this.drillTime, this.hardnessDrillMultiplier, (float)(this.size * this.size), this.drillMultipliers, (b) -> {
            if (b instanceof Floor f) {
                return enableMine(f);
            }

            return false;
        }));

        if (voidParticleChance > 0f) stats.add(Stat.output, BFStatValues.itemChance(voidParticleChance, new ItemStack(voidParticle, 1)));
    }

    public boolean enableMine(Floor floor){
        return !floor.wallOre && floor.itemDrop != null && floor.itemDrop.hardness <= tier && floor.itemDrop.hardness >= lowestDrillTier && floor.itemDrop != blockedItem;
    }

    @Override
    public void setBars() {
        super.setBars();
        removeBar("liquid");
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        return true;
    }

    @Override
    protected void countOre(Tile tile) {
        returnItem = itemArray.random();
        returnCount = size * size;
    }

    public class VoidDrillBuild extends DrillBuild {
        @Override
        public void updateTile() {
            if (timer(timerDump, 1.0F)) {
                if (tier >= 6 && Mathf.chance(voidParticleChance * warmup)) {
                    items.add(voidParticle, 1);
                }

                dump(dominantItem != null && items.has(dominantItem) ? dominantItem : null);
                onProximityUpdate();
            }



            if (dominantItem != null) {
                timeDrilled += warmup * delta();
                float delay = getDrillTime(dominantItem);
                if (items.total() < itemCapacity && dominantItems > 0 && efficiency > 0.0F) {
                    lastDrillSpeed = efficiency * (float)dominantItems * warmup / delay;
                    warmup = Mathf.approachDelta(warmup, efficiency, warmupSpeed);
                    progress += delta() * (float)dominantItems * efficiency * warmup;
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