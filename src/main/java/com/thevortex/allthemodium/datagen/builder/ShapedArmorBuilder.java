package com.thevortex.allthemodium.datagen.builder;

import com.thevortex.allthemodium.datagen.RecipeException;
import com.thevortex.allthemodium.reference.Reference;

import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger.TriggerInstance;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.EnumMap;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class ShapedArmorBuilder {

    public enum Slot {
        HELMET, CHESTPLATE, LEGGINGS, BOOTS;

        public String lower() {
            return toString().toLowerCase(Locale.ROOT);
        }
    }

    private final String criteriaName;
    private final Criterion<TriggerInstance> criterion;
    private final EnumMap<Slot, Item> pieces = new EnumMap<>(Slot.class);
    private final TagKey<Item> ingot;
    private Item core;


    public ShapedArmorBuilder(TagKey<Item> ingot) {
        this.ingot = ingot;

        this.criteriaName = String.format("has_%s_ingot", ingot);

        ItemPredicate predicate = ItemPredicate.Builder.item().of(ingot).build();
        this.criterion = InventoryChangeTrigger.TriggerInstance.hasItems(predicate);

    }

    public static ShapedArmorBuilder builder(TagKey<Item> ingot) {
        return new ShapedArmorBuilder(ingot);
    }


    public ShapedArmorBuilder setHelmet(DeferredHolder<Item,ArmorItem> object) {
        pieces.put(Slot.HELMET, object.get());
        return this;
    }

    public ShapedArmorBuilder setChestplate(DeferredHolder<Item,ArmorItem> object) {
        pieces.put(Slot.CHESTPLATE, object.get());
        return this;
    }

    public ShapedArmorBuilder setLeggings(DeferredHolder<Item,ArmorItem> object) {
        pieces.put(Slot.LEGGINGS, object.get());
        return this;
    }

    public ShapedArmorBuilder setBoots(DeferredHolder<Item,ArmorItem> object) {
        pieces.put(Slot.BOOTS, object.get());
        return this;
    }


    protected void validate(ResourceLocation id) {
        if (pieces.isEmpty()) {
            throw new RecipeException(id.toString(), "recipe must have at least 1 output");
        }
    }

    public void build(RecipeOutput consumer) {

        Consumer<ShapedRecipeBuilder> register = builder -> builder.save(consumer);

        Optional.ofNullable(pieces.get(Slot.HELMET))
            .map(this::helmet)
            .map(this::addCriterion)
            .ifPresent(register);

        Optional.ofNullable(pieces.get(Slot.CHESTPLATE))
            .map(this::chestplate)
            .map(this::addCriterion)
            .ifPresent(register);

        Optional.ofNullable(pieces.get(Slot.LEGGINGS))
            .map(this::leggings)
            .map(this::addCriterion)
            .ifPresent(register);

        Optional.ofNullable(pieces.get(Slot.BOOTS))
            .map(this::boots)
            .map(this::addCriterion)
            .ifPresent(register);
    }

    private ShapedRecipeBuilder shaped(ItemLike provider) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC,provider)
            .group(Reference.MOD_ID);
    }

    private ShapedRecipeBuilder addCriterion(ShapedRecipeBuilder builder) {
        return builder
            .define('a', ingot)
            .unlockedBy(criteriaName, criterion);
    }

    private ShapedRecipeBuilder helmet(ItemLike provider) {
        return shaped(provider)
            .pattern("aaa")
            .pattern("a a")
            .pattern("   ");

    }

    private ShapedRecipeBuilder chestplate(ItemLike provider) {
        return shaped(provider)
            .pattern("a a")
            .pattern("aaa")
            .pattern("aaa");
    }

    private ShapedRecipeBuilder leggings(ItemLike provider) {
        return shaped(provider)
            .pattern("aaa")
            .pattern("a a")
            .pattern("a a");
    }

    private ShapedRecipeBuilder boots(ItemLike provider) {
        return shaped(provider)
            .pattern("a a")
            .pattern("a a")
            .pattern("   ");
    }
}
