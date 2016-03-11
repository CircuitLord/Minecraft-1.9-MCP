package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockVine extends Block {
   public static final PropertyBool field_176277_a = PropertyBool.func_177716_a("up");
   public static final PropertyBool field_176273_b = PropertyBool.func_177716_a("north");
   public static final PropertyBool field_176278_M = PropertyBool.func_177716_a("east");
   public static final PropertyBool field_176279_N = PropertyBool.func_177716_a("south");
   public static final PropertyBool field_176280_O = PropertyBool.func_177716_a("west");
   public static final PropertyBool[] field_176274_P = new PropertyBool[]{field_176277_a, field_176273_b, field_176279_N, field_176280_O, field_176278_M};
   protected static final AxisAlignedBB field_185757_g = new AxisAlignedBB(0.0D, 0.9375D, 0.0D, 1.0D, 1.0D, 1.0D);
   protected static final AxisAlignedBB field_185753_B = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0625D, 1.0D, 1.0D);
   protected static final AxisAlignedBB field_185754_C = new AxisAlignedBB(0.9375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
   protected static final AxisAlignedBB field_185755_D = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.0625D);
   protected static final AxisAlignedBB field_185756_E = new AxisAlignedBB(0.0D, 0.0D, 0.9375D, 1.0D, 1.0D, 1.0D);

   public BlockVine() {
      super(Material.field_151582_l);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176277_a, Boolean.valueOf(false)).func_177226_a(field_176273_b, Boolean.valueOf(false)).func_177226_a(field_176278_M, Boolean.valueOf(false)).func_177226_a(field_176279_N, Boolean.valueOf(false)).func_177226_a(field_176280_O, Boolean.valueOf(false)));
      this.func_149675_a(true);
      this.func_149647_a(CreativeTabs.field_78031_c);
   }

   public AxisAlignedBB func_180646_a(IBlockState p_180646_1_, World p_180646_2_, BlockPos p_180646_3_) {
      return field_185506_k;
   }

   public AxisAlignedBB func_185496_a(IBlockState p_185496_1_, IBlockAccess p_185496_2_, BlockPos p_185496_3_) {
      p_185496_1_ = p_185496_1_.func_185899_b(p_185496_2_, p_185496_3_);
      int i = 0;
      AxisAlignedBB axisalignedbb = field_185505_j;
      if(((Boolean)p_185496_1_.func_177229_b(field_176277_a)).booleanValue()) {
         axisalignedbb = field_185757_g;
         ++i;
      }

      if(((Boolean)p_185496_1_.func_177229_b(field_176273_b)).booleanValue()) {
         axisalignedbb = field_185755_D;
         ++i;
      }

      if(((Boolean)p_185496_1_.func_177229_b(field_176278_M)).booleanValue()) {
         axisalignedbb = field_185754_C;
         ++i;
      }

      if(((Boolean)p_185496_1_.func_177229_b(field_176279_N)).booleanValue()) {
         axisalignedbb = field_185756_E;
         ++i;
      }

      if(((Boolean)p_185496_1_.func_177229_b(field_176280_O)).booleanValue()) {
         axisalignedbb = field_185753_B;
         ++i;
      }

      return i == 1?axisalignedbb:field_185505_j;
   }

   public IBlockState func_176221_a(IBlockState p_176221_1_, IBlockAccess p_176221_2_, BlockPos p_176221_3_) {
      return p_176221_1_.func_177226_a(field_176277_a, Boolean.valueOf(p_176221_2_.func_180495_p(p_176221_3_.func_177984_a()).func_185898_k()));
   }

   public boolean func_149662_c(IBlockState p_149662_1_) {
      return false;
   }

   public boolean func_149686_d(IBlockState p_149686_1_) {
      return false;
   }

   public boolean func_176200_f(IBlockAccess p_176200_1_, BlockPos p_176200_2_) {
      return true;
   }

   public boolean func_176198_a(World p_176198_1_, BlockPos p_176198_2_, EnumFacing p_176198_3_) {
      switch(p_176198_3_) {
      case UP:
         return this.func_185752_x(p_176198_1_.func_180495_p(p_176198_2_.func_177984_a()));
      case NORTH:
      case SOUTH:
      case EAST:
      case WEST:
         return this.func_185752_x(p_176198_1_.func_180495_p(p_176198_2_.func_177972_a(p_176198_3_.func_176734_d())));
      default:
         return false;
      }
   }

   private boolean func_185752_x(IBlockState p_185752_1_) {
      return p_185752_1_.func_185917_h() && p_185752_1_.func_185904_a().func_76230_c();
   }

   private boolean func_176269_e(World p_176269_1_, BlockPos p_176269_2_, IBlockState p_176269_3_) {
      IBlockState iblockstate = p_176269_3_;

      for(EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
         PropertyBool propertybool = func_176267_a(enumfacing);
         if(((Boolean)p_176269_3_.func_177229_b(propertybool)).booleanValue() && !this.func_185752_x(p_176269_1_.func_180495_p(p_176269_2_.func_177972_a(enumfacing)))) {
            IBlockState iblockstate1 = p_176269_1_.func_180495_p(p_176269_2_.func_177984_a());
            if(iblockstate1.func_177230_c() != this || !((Boolean)iblockstate1.func_177229_b(propertybool)).booleanValue()) {
               p_176269_3_ = p_176269_3_.func_177226_a(propertybool, Boolean.valueOf(false));
            }
         }
      }

      if(func_176268_d(p_176269_3_) == 0) {
         return false;
      } else {
         if(iblockstate != p_176269_3_) {
            p_176269_1_.func_180501_a(p_176269_2_, p_176269_3_, 2);
         }

         return true;
      }
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if(!p_176204_1_.field_72995_K && !this.func_176269_e(p_176204_1_, p_176204_2_, p_176204_3_)) {
         this.func_176226_b(p_176204_1_, p_176204_2_, p_176204_3_, 0);
         p_176204_1_.func_175698_g(p_176204_2_);
      }

   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      if(!p_180650_1_.field_72995_K) {
         if(p_180650_1_.field_73012_v.nextInt(4) == 0) {
            int i = 4;
            int j = 5;
            boolean flag = false;

            label62:
            for(int k = -i; k <= i; ++k) {
               for(int l = -i; l <= i; ++l) {
                  for(int i1 = -1; i1 <= 1; ++i1) {
                     if(p_180650_1_.func_180495_p(p_180650_2_.func_177982_a(k, i1, l)).func_177230_c() == this) {
                        --j;
                        if(j <= 0) {
                           flag = true;
                           break label62;
                        }
                     }
                  }
               }
            }

            EnumFacing enumfacing1 = EnumFacing.func_176741_a(p_180650_4_);
            BlockPos blockpos2 = p_180650_2_.func_177984_a();
            if(enumfacing1 == EnumFacing.UP && p_180650_2_.func_177956_o() < 255 && p_180650_1_.func_175623_d(blockpos2)) {
               if(!flag) {
                  IBlockState iblockstate2 = p_180650_3_;

                  for(EnumFacing enumfacing2 : EnumFacing.Plane.HORIZONTAL) {
                     if(p_180650_4_.nextBoolean() || !this.func_185752_x(p_180650_1_.func_180495_p(blockpos2.func_177972_a(enumfacing2)))) {
                        iblockstate2 = iblockstate2.func_177226_a(func_176267_a(enumfacing2), Boolean.valueOf(false));
                     }
                  }

                  if(((Boolean)iblockstate2.func_177229_b(field_176273_b)).booleanValue() || ((Boolean)iblockstate2.func_177229_b(field_176278_M)).booleanValue() || ((Boolean)iblockstate2.func_177229_b(field_176279_N)).booleanValue() || ((Boolean)iblockstate2.func_177229_b(field_176280_O)).booleanValue()) {
                     p_180650_1_.func_180501_a(blockpos2, iblockstate2, 2);
                  }

               }
            } else if(enumfacing1.func_176740_k().func_176722_c() && !((Boolean)p_180650_3_.func_177229_b(func_176267_a(enumfacing1))).booleanValue()) {
               if(!flag) {
                  BlockPos blockpos4 = p_180650_2_.func_177972_a(enumfacing1);
                  IBlockState iblockstate3 = p_180650_1_.func_180495_p(blockpos4);
                  Block block1 = iblockstate3.func_177230_c();
                  if(block1.field_149764_J == Material.field_151579_a) {
                     EnumFacing enumfacing3 = enumfacing1.func_176746_e();
                     EnumFacing enumfacing4 = enumfacing1.func_176735_f();
                     boolean flag1 = ((Boolean)p_180650_3_.func_177229_b(func_176267_a(enumfacing3))).booleanValue();
                     boolean flag2 = ((Boolean)p_180650_3_.func_177229_b(func_176267_a(enumfacing4))).booleanValue();
                     BlockPos blockpos = blockpos4.func_177972_a(enumfacing3);
                     BlockPos blockpos1 = blockpos4.func_177972_a(enumfacing4);
                     if(flag1 && this.func_185752_x(p_180650_1_.func_180495_p(blockpos))) {
                        p_180650_1_.func_180501_a(blockpos4, this.func_176223_P().func_177226_a(func_176267_a(enumfacing3), Boolean.valueOf(true)), 2);
                     } else if(flag2 && this.func_185752_x(p_180650_1_.func_180495_p(blockpos1))) {
                        p_180650_1_.func_180501_a(blockpos4, this.func_176223_P().func_177226_a(func_176267_a(enumfacing4), Boolean.valueOf(true)), 2);
                     } else if(flag1 && p_180650_1_.func_175623_d(blockpos) && this.func_185752_x(p_180650_1_.func_180495_p(p_180650_2_.func_177972_a(enumfacing3)))) {
                        p_180650_1_.func_180501_a(blockpos, this.func_176223_P().func_177226_a(func_176267_a(enumfacing1.func_176734_d()), Boolean.valueOf(true)), 2);
                     } else if(flag2 && p_180650_1_.func_175623_d(blockpos1) && this.func_185752_x(p_180650_1_.func_180495_p(p_180650_2_.func_177972_a(enumfacing4)))) {
                        p_180650_1_.func_180501_a(blockpos1, this.func_176223_P().func_177226_a(func_176267_a(enumfacing1.func_176734_d()), Boolean.valueOf(true)), 2);
                     } else if(this.func_185752_x(p_180650_1_.func_180495_p(blockpos4.func_177984_a()))) {
                        p_180650_1_.func_180501_a(blockpos4, this.func_176223_P(), 2);
                     }
                  } else if(block1.field_149764_J.func_76218_k() && iblockstate3.func_185917_h()) {
                     p_180650_1_.func_180501_a(p_180650_2_, p_180650_3_.func_177226_a(func_176267_a(enumfacing1), Boolean.valueOf(true)), 2);
                  }

               }
            } else {
               if(p_180650_2_.func_177956_o() > 1) {
                  BlockPos blockpos3 = p_180650_2_.func_177977_b();
                  IBlockState iblockstate = p_180650_1_.func_180495_p(blockpos3);
                  Block block = iblockstate.func_177230_c();
                  if(block.field_149764_J == Material.field_151579_a) {
                     IBlockState iblockstate1 = p_180650_3_;

                     for(EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL) {
                        if(p_180650_4_.nextBoolean()) {
                           iblockstate1 = iblockstate1.func_177226_a(func_176267_a(enumfacing), Boolean.valueOf(false));
                        }
                     }

                     if(((Boolean)iblockstate1.func_177229_b(field_176273_b)).booleanValue() || ((Boolean)iblockstate1.func_177229_b(field_176278_M)).booleanValue() || ((Boolean)iblockstate1.func_177229_b(field_176279_N)).booleanValue() || ((Boolean)iblockstate1.func_177229_b(field_176280_O)).booleanValue()) {
                        p_180650_1_.func_180501_a(blockpos3, iblockstate1, 2);
                     }
                  } else if(block == this) {
                     IBlockState iblockstate4 = iblockstate;

                     for(EnumFacing enumfacing5 : EnumFacing.Plane.HORIZONTAL) {
                        PropertyBool propertybool = func_176267_a(enumfacing5);
                        if(p_180650_4_.nextBoolean() && ((Boolean)p_180650_3_.func_177229_b(propertybool)).booleanValue()) {
                           iblockstate4 = iblockstate4.func_177226_a(propertybool, Boolean.valueOf(true));
                        }
                     }

                     if(((Boolean)iblockstate4.func_177229_b(field_176273_b)).booleanValue() || ((Boolean)iblockstate4.func_177229_b(field_176278_M)).booleanValue() || ((Boolean)iblockstate4.func_177229_b(field_176279_N)).booleanValue() || ((Boolean)iblockstate4.func_177229_b(field_176280_O)).booleanValue()) {
                        p_180650_1_.func_180501_a(blockpos3, iblockstate4, 2);
                     }
                  }
               }

            }
         }
      }
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      IBlockState iblockstate = this.func_176223_P().func_177226_a(field_176277_a, Boolean.valueOf(false)).func_177226_a(field_176273_b, Boolean.valueOf(false)).func_177226_a(field_176278_M, Boolean.valueOf(false)).func_177226_a(field_176279_N, Boolean.valueOf(false)).func_177226_a(field_176280_O, Boolean.valueOf(false));
      return p_180642_3_.func_176740_k().func_176722_c()?iblockstate.func_177226_a(func_176267_a(p_180642_3_.func_176734_d()), Boolean.valueOf(true)):iblockstate;
   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return null;
   }

   public int func_149745_a(Random p_149745_1_) {
      return 0;
   }

   public void func_180657_a(World p_180657_1_, EntityPlayer p_180657_2_, BlockPos p_180657_3_, IBlockState p_180657_4_, TileEntity p_180657_5_, ItemStack p_180657_6_) {
      if(!p_180657_1_.field_72995_K && p_180657_6_ != null && p_180657_6_.func_77973_b() == Items.field_151097_aZ) {
         p_180657_2_.func_71029_a(StatList.func_188055_a(this));
         func_180635_a(p_180657_1_, p_180657_3_, new ItemStack(Blocks.field_150395_bd, 1, 0));
      } else {
         super.func_180657_a(p_180657_1_, p_180657_2_, p_180657_3_, p_180657_4_, p_180657_5_, p_180657_6_);
      }

   }

   public BlockRenderLayer func_180664_k() {
      return BlockRenderLayer.CUTOUT;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_176279_N, Boolean.valueOf((p_176203_1_ & 1) > 0)).func_177226_a(field_176280_O, Boolean.valueOf((p_176203_1_ & 2) > 0)).func_177226_a(field_176273_b, Boolean.valueOf((p_176203_1_ & 4) > 0)).func_177226_a(field_176278_M, Boolean.valueOf((p_176203_1_ & 8) > 0));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      int i = 0;
      if(((Boolean)p_176201_1_.func_177229_b(field_176279_N)).booleanValue()) {
         i |= 1;
      }

      if(((Boolean)p_176201_1_.func_177229_b(field_176280_O)).booleanValue()) {
         i |= 2;
      }

      if(((Boolean)p_176201_1_.func_177229_b(field_176273_b)).booleanValue()) {
         i |= 4;
      }

      if(((Boolean)p_176201_1_.func_177229_b(field_176278_M)).booleanValue()) {
         i |= 8;
      }

      return i;
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{field_176277_a, field_176273_b, field_176278_M, field_176279_N, field_176280_O});
   }

   public IBlockState func_185499_a(IBlockState p_185499_1_, Rotation p_185499_2_) {
      switch(p_185499_2_) {
      case CLOCKWISE_180:
         return p_185499_1_.func_177226_a(field_176273_b, p_185499_1_.func_177229_b(field_176279_N)).func_177226_a(field_176278_M, p_185499_1_.func_177229_b(field_176280_O)).func_177226_a(field_176279_N, p_185499_1_.func_177229_b(field_176273_b)).func_177226_a(field_176280_O, p_185499_1_.func_177229_b(field_176278_M));
      case COUNTERCLOCKWISE_90:
         return p_185499_1_.func_177226_a(field_176273_b, p_185499_1_.func_177229_b(field_176278_M)).func_177226_a(field_176278_M, p_185499_1_.func_177229_b(field_176279_N)).func_177226_a(field_176279_N, p_185499_1_.func_177229_b(field_176280_O)).func_177226_a(field_176280_O, p_185499_1_.func_177229_b(field_176273_b));
      case CLOCKWISE_90:
         return p_185499_1_.func_177226_a(field_176273_b, p_185499_1_.func_177229_b(field_176280_O)).func_177226_a(field_176278_M, p_185499_1_.func_177229_b(field_176273_b)).func_177226_a(field_176279_N, p_185499_1_.func_177229_b(field_176278_M)).func_177226_a(field_176280_O, p_185499_1_.func_177229_b(field_176279_N));
      default:
         return p_185499_1_;
      }
   }

   public IBlockState func_185471_a(IBlockState p_185471_1_, Mirror p_185471_2_) {
      switch(p_185471_2_) {
      case LEFT_RIGHT:
         return p_185471_1_.func_177226_a(field_176273_b, p_185471_1_.func_177229_b(field_176279_N)).func_177226_a(field_176279_N, p_185471_1_.func_177229_b(field_176273_b));
      case FRONT_BACK:
         return p_185471_1_.func_177226_a(field_176278_M, p_185471_1_.func_177229_b(field_176280_O)).func_177226_a(field_176280_O, p_185471_1_.func_177229_b(field_176278_M));
      default:
         return super.func_185471_a(p_185471_1_, p_185471_2_);
      }
   }

   public static PropertyBool func_176267_a(EnumFacing p_176267_0_) {
      switch(p_176267_0_) {
      case UP:
         return field_176277_a;
      case NORTH:
         return field_176273_b;
      case SOUTH:
         return field_176279_N;
      case EAST:
         return field_176278_M;
      case WEST:
         return field_176280_O;
      default:
         throw new IllegalArgumentException(p_176267_0_ + " is an invalid choice");
      }
   }

   public static int func_176268_d(IBlockState p_176268_0_) {
      int i = 0;

      for(PropertyBool propertybool : field_176274_P) {
         if(((Boolean)p_176268_0_.func_177229_b(propertybool)).booleanValue()) {
            ++i;
         }
      }

      return i;
   }
}
