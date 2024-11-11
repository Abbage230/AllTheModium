package com.thevortex.allthemodium.items.toolitems.armor;

import com.thevortex.allthemodium.AllTheModium;
import com.thevortex.allthemodium.registry.ModRegistry;
import com.thevortex.allthemodium.registry.TagRegistry;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class Allthemodium_Boots extends ArmorItem {


    public Allthemodium_Boots(Holder<ArmorMaterial> allthemodium, EquipmentSlot slot, Properties builder) {
		super(allthemodium, Type.BOOTS, builder);

	}

    @Override
    public boolean canWalkOnPowderedSnow(ItemStack stack, LivingEntity wearer)
    {
        if(stack.is(TagRegistry.ATM_BOOTS)){ return true; }
      
        return false;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if(entity instanceof LivingEntity && stack.is(TagRegistry.ATM_BOOTS) && (slot == 36)){
            if(((LivingEntity)entity).isInLava()){
                entity.setPos(entity.getX(), entity.getY() + 0.2, entity.getZ());
                entity.clearFire();
            }
        }
    }
    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer)
    {
        return true;
    }
}
