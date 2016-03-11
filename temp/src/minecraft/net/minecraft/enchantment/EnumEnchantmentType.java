package net.minecraft.enchantment;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public enum EnumEnchantmentType {
   ALL,
   ARMOR,
   ARMOR_FEET,
   ARMOR_LEGS,
   ARMOR_CHEST,
   ARMOR_HEAD,
   WEAPON,
   DIGGER,
   FISHING_ROD,
   BREAKABLE,
   BOW;

   public boolean func_77557_a(Item p_77557_1_) {
      if(this == ALL) {
         return true;
      } else if(this == BREAKABLE && p_77557_1_.func_77645_m()) {
         return true;
      } else if(p_77557_1_ instanceof ItemArmor) {
         if(this == ARMOR) {
            return true;
         } else {
            ItemArmor itemarmor = (ItemArmor)p_77557_1_;
            return itemarmor.field_77881_a == EntityEquipmentSlot.HEAD?this == ARMOR_HEAD:(itemarmor.field_77881_a == EntityEquipmentSlot.LEGS?this == ARMOR_LEGS:(itemarmor.field_77881_a == EntityEquipmentSlot.CHEST?this == ARMOR_CHEST:(itemarmor.field_77881_a == EntityEquipmentSlot.FEET?this == ARMOR_FEET:false)));
         }
      } else {
         return p_77557_1_ instanceof ItemSword?this == WEAPON:(p_77557_1_ instanceof ItemTool?this == DIGGER:(p_77557_1_ instanceof ItemBow?this == BOW:(p_77557_1_ instanceof ItemFishingRod?this == FISHING_ROD:false)));
      }
   }
}
