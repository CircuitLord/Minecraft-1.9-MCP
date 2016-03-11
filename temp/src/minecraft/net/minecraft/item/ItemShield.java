package net.minecraft.item;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class ItemShield extends Item {
   public ItemShield() {
      this.field_77777_bU = 1;
      this.func_77637_a(CreativeTabs.field_78037_j);
      this.func_77656_e(336);
      this.func_185043_a(new ResourceLocation("blocking"), new IItemPropertyGetter() {
         public float func_185085_a(ItemStack p_185085_1_, World p_185085_2_, EntityLivingBase p_185085_3_) {
            return p_185085_3_ != null && p_185085_3_.func_184587_cr() && p_185085_3_.func_184607_cu() == p_185085_1_?1.0F:0.0F;
         }
      });
   }

   public EnumActionResult func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumHand p_180614_5_, EnumFacing p_180614_6_, float p_180614_7_, float p_180614_8_, float p_180614_9_) {
      return super.func_180614_a(p_180614_1_, p_180614_2_, p_180614_3_, p_180614_4_, p_180614_5_, p_180614_6_, p_180614_7_, p_180614_8_, p_180614_9_);
   }

   public String func_77653_i(ItemStack p_77653_1_) {
      if(p_77653_1_.func_179543_a("BlockEntityTag", false) != null) {
         String s = "item.shield.";
         EnumDyeColor enumdyecolor = ItemBanner.func_179225_h(p_77653_1_);
         s = s + enumdyecolor.func_176762_d() + ".name";
         return I18n.func_74838_a(s);
      } else {
         return I18n.func_74838_a("item.shield.name");
      }
   }

   public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> p_77624_3_, boolean p_77624_4_) {
      ItemBanner.func_185054_a(p_77624_1_, p_77624_3_);
   }

   public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List<ItemStack> p_150895_3_) {
      ItemStack itemstack = new ItemStack(p_150895_1_, 1, 0);
      p_150895_3_.add(itemstack);
   }

   public CreativeTabs func_77640_w() {
      return CreativeTabs.field_78037_j;
   }

   public EnumAction func_77661_b(ItemStack p_77661_1_) {
      return EnumAction.BLOCK;
   }

   public int func_77626_a(ItemStack p_77626_1_) {
      return 72000;
   }

   public ActionResult<ItemStack> func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_, EnumHand p_77659_4_) {
      p_77659_3_.func_184598_c(p_77659_4_);
      return new ActionResult(EnumActionResult.SUCCESS, p_77659_1_);
   }

   public boolean func_82789_a(ItemStack p_82789_1_, ItemStack p_82789_2_) {
      return p_82789_2_.func_77973_b() == Item.func_150898_a(Blocks.field_150344_f)?true:super.func_82789_a(p_82789_1_, p_82789_2_);
   }
}
