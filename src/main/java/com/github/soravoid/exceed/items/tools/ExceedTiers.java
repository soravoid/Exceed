package com.github.soravoid.exceed.items.tools;

import net.minecraft.item.IItemTier;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

public enum ExceedTiers implements IItemTier
{
    EXCEED(3.0F, 8.0F, 10, 3, 1561, Ingredient.fromItems(Items.DIAMOND)), //Placeholder, Same as Diamond
    ;

    float atkDmg, eff;
    int enchant, hvrstLvl, dura;
    Ingredient repairItem;

    ExceedTiers(float atkDmg, float eff, int enchant, int harvestLvl, int dura, Ingredient item)
    {
        this.atkDmg = atkDmg;
        this.eff = eff;
        this.enchant = enchant;
        this.hvrstLvl = harvestLvl;
        this.dura = dura;
        this.repairItem = item;
    }

    @Override
    public float getAttackDamage() {
        return atkDmg;
    }

    @Override
    public float getEfficiency() {
        return eff;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return repairItem;
    }

    @Override
    public int getEnchantability() {
        return enchant;
    }

    @Override
    public int getHarvestLevel() {
        return hvrstLvl;
    }

    @Override
    public int getMaxUses() {
        return dura;
    }
}
