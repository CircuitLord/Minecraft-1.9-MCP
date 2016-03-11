package net.minecraft.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBlock extends Item {
   protected final Block field_150939_a;

   public ItemBlock(Block p_i45328_1_) {
      this.field_150939_a = p_i45328_1_;
   }

   public ItemBlock func_77655_b(String p_77655_1_) {
      super.func_77655_b(p_77655_1_);
      return this;
   }

   public EnumActionResult func_180614_a(ItemStack p_180614_1_, EntityPlayer p_180614_2_, World p_180614_3_, BlockPos p_180614_4_, EnumHand p_180614_5_, EnumFacing p_180614_6_, float p_180614_7_, float p_180614_8_, float p_180614_9_) {
      IBlockState iblockstate = p_180614_3_.func_180495_p(p_180614_4_);
      Block block = iblockstate.func_177230_c();
      if(!block.func_176200_f(p_180614_3_, p_180614_4_)) {
         p_180614_4_ = p_180614_4_.func_177972_a(p_180614_6_);
      }

      if(p_180614_1_.field_77994_a != 0 && p_180614_2_.func_175151_a(p_180614_4_, p_180614_6_, p_180614_1_) && p_180614_3_.func_175716_a(this.field_150939_a, p_180614_4_, false, p_180614_6_, (Entity)null, p_180614_1_)) {
         int i = this.func_77647_b(p_180614_1_.func_77960_j());
         IBlockState iblockstate1 = this.field_150939_a.func_180642_a(p_180614_3_, p_180614_4_, p_180614_6_, p_180614_7_, p_180614_8_, p_180614_9_, i, p_180614_2_);
         if(p_180614_3_.func_180501_a(p_180614_4_, iblockstate1, 11)) {
            iblockstate1 = p_180614_3_.func_180495_p(p_180614_4_);
            if(iblockstate1.func_177230_c() == this.field_150939_a) {
               func_179224_a(p_180614_3_, p_180614_2_, p_180614_4_, p_180614_1_);
               this.field_150939_a.func_180633_a(p_180614_3_, p_180614_4_, iblockstate1, p_180614_2_, p_180614_1_);
            }

            SoundType soundtype = this.field_150939_a.func_185467_w();
            p_180614_3_.func_184133_a(p_180614_2_, p_180614_4_, soundtype.func_185841_e(), SoundCategory.BLOCKS, (soundtype.func_185843_a() + 1.0F) / 2.0F, soundtype.func_185847_b() * 0.8F);
            --p_180614_1_.field_77994_a;
         }

         return EnumActionResult.SUCCESS;
      } else {
         return EnumActionResult.FAIL;
      }
   }

   public static boolean func_179224_a(World p_179224_0_, EntityPlayer p_179224_1_, BlockPos p_179224_2_, ItemStack p_179224_3_) {
      MinecraftServer minecraftserver = p_179224_0_.func_73046_m();
      if(minecraftserver == null) {
         return false;
      } else {
         if(p_179224_3_.func_77942_o() && p_179224_3_.func_77978_p().func_150297_b("BlockEntityTag", 10)) {
            TileEntity tileentity = p_179224_0_.func_175625_s(p_179224_2_);
            if(tileentity != null) {
               if(!p_179224_0_.field_72995_K && tileentity.func_183000_F() && (p_179224_1_ == null || !minecraftserver.func_184103_al().func_152596_g(p_179224_1_.func_146103_bH()))) {
                  return false;
               }

               NBTTagCompound nbttagcompound = new NBTTagCompound();
               NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttagcompound.func_74737_b();
               tileentity.func_145841_b(nbttagcompound);
               NBTTagCompound nbttagcompound2 = (NBTTagCompound)p_179224_3_.func_77978_p().func_74781_a("BlockEntityTag");
               nbttagcompound.func_179237_a(nbttagcompound2);
               nbttagcompound.func_74768_a("x", p_179224_2_.func_177958_n());
               nbttagcompound.func_74768_a("y", p_179224_2_.func_177956_o());
               nbttagcompound.func_74768_a("z", p_179224_2_.func_177952_p());
               if(!nbttagcompound.equals(nbttagcompound1)) {
                  tileentity.func_145839_a(nbttagcompound);
                  tileentity.func_70296_d();
                  return true;
               }
            }
         }

         return false;
      }
   }

   public boolean func_179222_a(World p_179222_1_, BlockPos p_179222_2_, EnumFacing p_179222_3_, EntityPlayer p_179222_4_, ItemStack p_179222_5_) {
      Block block = p_179222_1_.func_180495_p(p_179222_2_).func_177230_c();
      if(block == Blocks.field_150431_aC) {
         p_179222_3_ = EnumFacing.UP;
      } else if(!block.func_176200_f(p_179222_1_, p_179222_2_)) {
         p_179222_2_ = p_179222_2_.func_177972_a(p_179222_3_);
      }

      return p_179222_1_.func_175716_a(this.field_150939_a, p_179222_2_, false, p_179222_3_, (Entity)null, p_179222_5_);
   }

   public String func_77667_c(ItemStack p_77667_1_) {
      return this.field_150939_a.func_149739_a();
   }

   public String func_77658_a() {
      return this.field_150939_a.func_149739_a();
   }

   public CreativeTabs func_77640_w() {
      return this.field_150939_a.func_149708_J();
   }

   public void func_150895_a(Item p_150895_1_, CreativeTabs p_150895_2_, List<ItemStack> p_150895_3_) {
      this.field_150939_a.func_149666_a(p_150895_1_, p_150895_2_, p_150895_3_);
   }

   public Block func_179223_d() {
      return this.field_150939_a;
   }
}
