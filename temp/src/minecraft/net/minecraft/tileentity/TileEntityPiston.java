package net.minecraft.tileentity;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class TileEntityPiston extends TileEntity implements ITickable {
   private IBlockState field_174932_a;
   private EnumFacing field_174931_f;
   private boolean field_145875_k;
   private boolean field_145872_l;
   private float field_145873_m;
   private float field_145870_n;

   public TileEntityPiston() {
   }

   public TileEntityPiston(IBlockState p_i45665_1_, EnumFacing p_i45665_2_, boolean p_i45665_3_, boolean p_i45665_4_) {
      this.field_174932_a = p_i45665_1_;
      this.field_174931_f = p_i45665_2_;
      this.field_145875_k = p_i45665_3_;
      this.field_145872_l = p_i45665_4_;
   }

   public IBlockState func_174927_b() {
      return this.field_174932_a;
   }

   public int func_145832_p() {
      return 0;
   }

   public boolean func_145868_b() {
      return this.field_145875_k;
   }

   public EnumFacing func_174930_e() {
      return this.field_174931_f;
   }

   public boolean func_145867_d() {
      return this.field_145872_l;
   }

   public float func_145860_a(float p_145860_1_) {
      if(p_145860_1_ > 1.0F) {
         p_145860_1_ = 1.0F;
      }

      return this.field_145870_n + (this.field_145873_m - this.field_145870_n) * p_145860_1_;
   }

   public float func_174929_b(float p_174929_1_) {
      return (float)this.field_174931_f.func_82601_c() * this.func_184320_e(this.func_145860_a(p_174929_1_));
   }

   public float func_174928_c(float p_174928_1_) {
      return (float)this.field_174931_f.func_96559_d() * this.func_184320_e(this.func_145860_a(p_174928_1_));
   }

   public float func_174926_d(float p_174926_1_) {
      return (float)this.field_174931_f.func_82599_e() * this.func_184320_e(this.func_145860_a(p_174926_1_));
   }

   private float func_184320_e(float p_184320_1_) {
      return this.field_145875_k?p_184320_1_ - 1.0F:1.0F - p_184320_1_;
   }

   public AxisAlignedBB func_184321_a(IBlockAccess p_184321_1_, BlockPos p_184321_2_) {
      return this.func_184319_a(p_184321_1_, p_184321_2_, this.field_145873_m).func_111270_a(this.func_184319_a(p_184321_1_, p_184321_2_, this.field_145870_n));
   }

   public AxisAlignedBB func_184319_a(IBlockAccess p_184319_1_, BlockPos p_184319_2_, float p_184319_3_) {
      p_184319_3_ = this.func_184320_e(p_184319_3_);
      return this.field_174932_a.func_185900_c(p_184319_1_, p_184319_2_).func_72317_d((double)(p_184319_3_ * (float)this.field_174931_f.func_82601_c()), (double)(p_184319_3_ * (float)this.field_174931_f.func_96559_d()), (double)(p_184319_3_ * (float)this.field_174931_f.func_82599_e()));
   }

   private void func_184322_i() {
      AxisAlignedBB axisalignedbb = this.func_184321_a(this.field_145850_b, this.field_174879_c).func_186670_a(this.field_174879_c);
      List<Entity> list = this.field_145850_b.func_72839_b((Entity)null, axisalignedbb);
      if(!list.isEmpty()) {
         EnumFacing enumfacing = this.field_145875_k?this.field_174931_f:this.field_174931_f.func_176734_d();

         for(int i = 0; i < list.size(); ++i) {
            Entity entity = (Entity)list.get(i);
            if(entity.func_184192_z() != EnumPushReaction.IGNORE) {
               if(this.field_174932_a.func_177230_c() == Blocks.field_180399_cE) {
                  switch(enumfacing.func_176740_k()) {
                  case X:
                     entity.field_70159_w = (double)enumfacing.func_82601_c();
                     break;
                  case Y:
                     entity.field_70181_x = (double)enumfacing.func_96559_d();
                     break;
                  case Z:
                     entity.field_70179_y = (double)enumfacing.func_82599_e();
                  }
               }

               double d0 = 0.0D;
               double d1 = 0.0D;
               double d2 = 0.0D;
               AxisAlignedBB axisalignedbb1 = entity.func_174813_aQ();
               switch(enumfacing.func_176740_k()) {
               case X:
                  if(enumfacing.func_176743_c() == EnumFacing.AxisDirection.POSITIVE) {
                     d0 = axisalignedbb.field_72336_d - axisalignedbb1.field_72340_a;
                  } else {
                     d0 = axisalignedbb1.field_72336_d - axisalignedbb.field_72340_a;
                  }

                  d0 = d0 + 0.01D;
                  break;
               case Y:
                  if(enumfacing.func_176743_c() == EnumFacing.AxisDirection.POSITIVE) {
                     d1 = axisalignedbb.field_72337_e - axisalignedbb1.field_72338_b;
                  } else {
                     d1 = axisalignedbb1.field_72337_e - axisalignedbb.field_72338_b;
                  }

                  d1 = d1 + 0.01D;
                  break;
               case Z:
                  if(enumfacing.func_176743_c() == EnumFacing.AxisDirection.POSITIVE) {
                     d2 = axisalignedbb.field_72334_f - axisalignedbb1.field_72339_c;
                  } else {
                     d2 = axisalignedbb1.field_72334_f - axisalignedbb.field_72339_c;
                  }

                  d2 = d2 + 0.01D;
               }

               entity.func_70091_d(d0 * (double)enumfacing.func_82601_c(), d1 * (double)enumfacing.func_96559_d(), d2 * (double)enumfacing.func_82599_e());
            }
         }

      }
   }

   public void func_145866_f() {
      if(this.field_145870_n < 1.0F && this.field_145850_b != null) {
         this.field_145870_n = this.field_145873_m = 1.0F;
         this.field_145850_b.func_175713_t(this.field_174879_c);
         this.func_145843_s();
         if(this.field_145850_b.func_180495_p(this.field_174879_c).func_177230_c() == Blocks.field_180384_M) {
            this.field_145850_b.func_180501_a(this.field_174879_c, this.field_174932_a, 3);
            this.field_145850_b.func_180496_d(this.field_174879_c, this.field_174932_a.func_177230_c());
         }
      }

   }

   public void func_73660_a() {
      this.field_145870_n = this.field_145873_m;
      if(this.field_145870_n >= 1.0F) {
         this.func_184322_i();
         this.field_145850_b.func_175713_t(this.field_174879_c);
         this.func_145843_s();
         if(this.field_145850_b.func_180495_p(this.field_174879_c).func_177230_c() == Blocks.field_180384_M) {
            this.field_145850_b.func_180501_a(this.field_174879_c, this.field_174932_a, 3);
            this.field_145850_b.func_180496_d(this.field_174879_c, this.field_174932_a.func_177230_c());
         }

      } else {
         this.field_145873_m += 0.5F;
         if(this.field_145873_m >= 1.0F) {
            this.field_145873_m = 1.0F;
         }

         this.func_184322_i();
      }
   }

   public void func_145839_a(NBTTagCompound p_145839_1_) {
      super.func_145839_a(p_145839_1_);
      this.field_174932_a = Block.func_149729_e(p_145839_1_.func_74762_e("blockId")).func_176203_a(p_145839_1_.func_74762_e("blockData"));
      this.field_174931_f = EnumFacing.func_82600_a(p_145839_1_.func_74762_e("facing"));
      this.field_145870_n = this.field_145873_m = p_145839_1_.func_74760_g("progress");
      this.field_145875_k = p_145839_1_.func_74767_n("extending");
   }

   public void func_145841_b(NBTTagCompound p_145841_1_) {
      super.func_145841_b(p_145841_1_);
      p_145841_1_.func_74768_a("blockId", Block.func_149682_b(this.field_174932_a.func_177230_c()));
      p_145841_1_.func_74768_a("blockData", this.field_174932_a.func_177230_c().func_176201_c(this.field_174932_a));
      p_145841_1_.func_74768_a("facing", this.field_174931_f.func_176745_a());
      p_145841_1_.func_74776_a("progress", this.field_145870_n);
      p_145841_1_.func_74757_a("extending", this.field_145875_k);
   }
}
