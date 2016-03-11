package net.minecraft.item.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.world.World;

public class ShieldRecipes {
   public void func_185256_a(CraftingManager p_185256_1_) {
      p_185256_1_.func_92103_a(new ItemStack(Items.field_185159_cQ), new Object[]{"WoW", "WWW", " W ", Character.valueOf('W'), Blocks.field_150344_f, Character.valueOf('o'), Items.field_151042_j});
      p_185256_1_.func_180302_a(new ShieldRecipes.Decoration());
   }

   static class Decoration implements IRecipe {
      private Decoration() {
      }

      public boolean func_77569_a(InventoryCrafting p_77569_1_, World p_77569_2_) {
         ItemStack itemstack = null;
         ItemStack itemstack1 = null;

         for(int i = 0; i < p_77569_1_.func_70302_i_(); ++i) {
            ItemStack itemstack2 = p_77569_1_.func_70301_a(i);
            if(itemstack2 != null) {
               if(itemstack2.func_77973_b() == Items.field_179564_cE) {
                  if(itemstack1 != null) {
                     return false;
                  }

                  itemstack1 = itemstack2;
               } else {
                  if(itemstack2.func_77973_b() != Items.field_185159_cQ) {
                     return false;
                  }

                  if(itemstack != null) {
                     return false;
                  }

                  if(itemstack2.func_179543_a("BlockEntityTag", false) != null) {
                     return false;
                  }

                  itemstack = itemstack2;
               }
            }
         }

         if(itemstack != null && itemstack1 != null) {
            return true;
         } else {
            return false;
         }
      }

      public ItemStack func_77572_b(InventoryCrafting p_77572_1_) {
         ItemStack itemstack = null;

         for(int i = 0; i < p_77572_1_.func_70302_i_(); ++i) {
            ItemStack itemstack1 = p_77572_1_.func_70301_a(i);
            if(itemstack1 != null && itemstack1.func_77973_b() == Items.field_179564_cE) {
               itemstack = itemstack1;
            }
         }

         ItemStack itemstack2 = new ItemStack(Items.field_185159_cQ, 1, 0);
         EnumDyeColor enumdyecolor;
         NBTTagCompound nbttagcompound;
         if(itemstack.func_77942_o()) {
            nbttagcompound = (NBTTagCompound)itemstack.func_77978_p().func_74737_b();
            enumdyecolor = EnumDyeColor.func_176766_a(TileEntityBanner.func_175111_b(itemstack));
         } else {
            nbttagcompound = new NBTTagCompound();
            enumdyecolor = EnumDyeColor.func_176766_a(itemstack.func_77952_i());
         }

         itemstack2.func_77982_d(nbttagcompound);
         TileEntityBanner.func_184248_a(itemstack2, enumdyecolor);
         return itemstack2;
      }

      public int func_77570_a() {
         return 2;
      }

      public ItemStack func_77571_b() {
         return null;
      }

      public ItemStack[] func_179532_b(InventoryCrafting p_179532_1_) {
         ItemStack[] aitemstack = new ItemStack[p_179532_1_.func_70302_i_()];

         for(int i = 0; i < aitemstack.length; ++i) {
            ItemStack itemstack = p_179532_1_.func_70301_a(i);
            if(itemstack != null && itemstack.func_77973_b().func_77634_r()) {
               aitemstack[i] = new ItemStack(itemstack.func_77973_b().func_77668_q());
            }
         }

         return aitemstack;
      }
   }
}
