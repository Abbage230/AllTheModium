package com.thevortex.allthemodium.material;

import com.thevortex.allthemodium.registry.ModRegistry;
import com.thevortex.allthemodium.registry.TagRegistry;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

public class ATMTier implements Tier {
    private float attackBonus;
    private int enchantmentValue;
    private TagKey<Block> incorrectBlocksForDrops;
    private Ingredient repairIngredient;
    private float speed;
    private int uses;

    public static final ATMTier ALLTHEMODIUM = createTier(TagRegistry.INCORRECT_FOR_ALLTHEMODIUM_TOOL, 15.0F, -1,  12.0F, 85, Ingredient.of(TagRegistry.ALLTHEMODIUM_INGOT));
    public static final ATMTier VIBRANIUM = createTier(TagRegistry.INCORRECT_FOR_VIBRANIUM_TOOL, 16.0F, -1,  25.0F, 100, Ingredient.of(TagRegistry.VIBRANIUM_INGOT));
    public static final ATMTier UNOBTAINIUM = createTier(TagRegistry.INCORRECT_FOR_UNOBTAINIUM_TOOL, 18.0F, -1,  35.0F, 125, Ingredient.of(TagRegistry.UNOBTAINIUM_INGOT));
    public static final ATMTier ALLOY = createTier(TagRegistry.NEEDS_ALLOY_TOOL, 20.0F, -1,  65.0F, 200, Ingredient.of(ModRegistry.UNOBTAINIUM_ALLTHEMODIUM_ALLOY.get()));
    public ATMTier(TagKey<Block> incorrectBlocksForDrops, float speed, int uses, float attackBonus, int enchantmentValue, Ingredient repairIngredient) {
        this.attackBonus = attackBonus;
        this.enchantmentValue = enchantmentValue;
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
        this.repairIngredient = repairIngredient;
        this.speed = speed;
        this.uses = uses;
    }

    public static ATMTier createTier(TagKey<Block> incorrectBlocksForDrops, float speed, int uses, float attackBonus, int enchantmentValue, Ingredient repairIngredient) {
        return new ATMTier(incorrectBlocksForDrops, speed, uses, attackBonus, enchantmentValue, repairIngredient);
    }
    @Override
    public float getAttackDamageBonus() {
        return this.attackBonus;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return this.incorrectBlocksForDrops;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public int getUses() {
        return this.uses;
    }

}
