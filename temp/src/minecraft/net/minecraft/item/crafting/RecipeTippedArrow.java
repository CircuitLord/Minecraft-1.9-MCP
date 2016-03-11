package net.minecraft.item.crafting;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionUtils;
import net.minecraft.world.World;

class RecipeTippedArrow implements IRecipe {
   private static final ItemStack[] field_185255_a = new ItemStack[9];

   public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_) {
      if(p_77569_1_.func_174922_i() == 3 && p_77569_1_.func_174923_h() == 3) {
         for(int i = 0; i < p_77569_1_.func_174922_i(); ++i) {
            for(int j = 0; j < p_77569_1_.func_174923_h(); ++j) {
               ItemStack itemstack = p_77569_1_.func_70463_b(i, j);
               if(itemstack == null) {
                  return false;
               }

               Item item = itemstack.func_77973_b();
               if(i == 1 && j == 1) {
                  if(item != Items.field_185156_bI) {
                     return false;
                  }
               } else if(item != Items.field_151032_g) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public ItemStack func_77572_b(InventoryCrafting p_77572_1_) {
      ItemStack itemstack = p_77572_1_.func_70463_b(1, 1);
      if(itemstack != null && itemstack.func_77973_b() == Items.field_185156_bI) {
         ItemStack itemstack1 = new ItemStack(Items.field_185167_i, 8);
         PotionUtils.func_185188_a(itemstack1, PotionUtils.func_185191_c(itemstack));
         PotionUtils.func_185184_a(itemstack1, PotionUtils.func_185190_b(itemstack));
         return itemstack1;
      } else {
         return null;
      }
   }

   public int func_77570_a() {
      return 9;
   }

   public ItemStack func_77571_b() {
      return null;
   }

   public ItemStack[] func_179532_b(InventoryCrafting p_179532_1_) {
      return field_185255_a;
   }
}
