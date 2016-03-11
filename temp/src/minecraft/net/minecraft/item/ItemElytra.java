package net.minecraft.item;

import net.minecraft.block.BlockDispenser;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ItemElytra extends Item {
   public ItemElytra() {
      this.field_77777_bU = 1;
      this.func_77656_e(432);
      this.func_77637_a(CreativeTabs.field_78029_e);
      this.func_185043_a(new ResourceLocation("broken"), new IItemPropertyGetter() {
         public float func_185085_a(ItemStack p_185085_1_, World p_185085_2_, EntityLivingBase p_185085_3_) {
            return ItemElytra.func_185069_d(p_185085_1_)?0.0F:1.0F;
         }
      });
      BlockDispenser.field_149943_a.func_82595_a(this, ItemArmor.field_96605_cw);
   }

   public static boolean func_185069_d(ItemStack p_185069_0_) {
      return p_185069_0_.func_77952_i() < p_185069_0_.func_77958_k() - 1;
   }

   public boolean func_82789_a(ItemStack p_82789_1_, ItemStack p_82789_2_) {
      return p_82789_2_.func_77973_b() == Items.field_151116_aA;
   }

   public ActionResult<ItemStack> func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_, EnumHand p_77659_4_) {
      EntityEquipmentSlot entityequipmentslot = EntityLiving.func_184640_d(p_77659_1_);
      ItemStack itemstack = p_77659_3_.func_184582_a(entityequipmentslot);
      if(itemstack == null) {
         p_77659_3_.func_184201_a(entityequipmentslot, p_77659_1_.func_77946_l());
         p_77659_1_.field_77994_a = 0;
         return new ActionResult(EnumActionResult.SUCCESS, p_77659_1_);
      } else {
         return new ActionResult(EnumActionResult.FAIL, p_77659_1_);
      }
   }
}
