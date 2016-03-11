package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.CommandBlockBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCommandBlock extends BlockContainer {
   public static final PropertyDirection field_185564_a = BlockDirectional.field_176387_N;
   public static final PropertyBool field_185565_b = PropertyBool.func_177716_a("conditional");

   public BlockCommandBlock(MapColor p_i46688_1_) {
      super(Material.field_151573_f, p_i46688_1_);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_185564_a, EnumFacing.NORTH).func_177226_a(field_185565_b, Boolean.valueOf(false)));
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      TileEntityCommandBlock tileentitycommandblock = new TileEntityCommandBlock();
      tileentitycommandblock.func_184253_b(this == Blocks.field_185777_dd);
      return tileentitycommandblock;
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if(!p_176204_1_.field_72995_K) {
         TileEntity tileentity = p_176204_1_.func_175625_s(p_176204_2_);
         if(tileentity instanceof TileEntityCommandBlock) {
            TileEntityCommandBlock tileentitycommandblock = (TileEntityCommandBlock)tileentity;
            boolean flag = p_176204_1_.func_175640_z(p_176204_2_);
            boolean flag1 = tileentitycommandblock.func_184255_d();
            boolean flag2 = tileentitycommandblock.func_184254_e();
            if(flag && !flag1) {
               tileentitycommandblock.func_184250_a(true);
               if(tileentitycommandblock.func_184251_i() != TileEntityCommandBlock.Mode.SEQUENCE && !flag2) {
                  boolean flag3 = !tileentitycommandblock.func_184258_j() || this.func_185562_e(p_176204_1_, p_176204_2_, p_176204_3_);
                  tileentitycommandblock.func_184249_c(flag3);
                  p_176204_1_.func_175684_a(p_176204_2_, this, this.func_149738_a(p_176204_1_));
                  if(flag3) {
                     this.func_185563_c(p_176204_1_, p_176204_2_);
                  }
               }
            } else if(!flag && flag1) {
               tileentitycommandblock.func_184250_a(false);
            }

         }
      }
   }

   public void func_180650_b(World p_180650_1_, BlockPos p_180650_2_, IBlockState p_180650_3_, Random p_180650_4_) {
      if(!p_180650_1_.field_72995_K) {
         TileEntity tileentity = p_180650_1_.func_175625_s(p_180650_2_);
         if(tileentity instanceof TileEntityCommandBlock) {
            TileEntityCommandBlock tileentitycommandblock = (TileEntityCommandBlock)tileentity;
            CommandBlockBaseLogic commandblockbaselogic = tileentitycommandblock.func_145993_a();
            boolean flag = !StringUtils.func_151246_b(commandblockbaselogic.func_145753_i());
            TileEntityCommandBlock.Mode tileentitycommandblock$mode = tileentitycommandblock.func_184251_i();
            boolean flag1 = !tileentitycommandblock.func_184258_j() || this.func_185562_e(p_180650_1_, p_180650_2_, p_180650_3_);
            boolean flag2 = tileentitycommandblock.func_184256_g();
            boolean flag3 = false;
            if(tileentitycommandblock$mode != TileEntityCommandBlock.Mode.SEQUENCE && flag2 && flag) {
               commandblockbaselogic.func_145755_a(p_180650_1_);
               flag3 = true;
            }

            if(tileentitycommandblock.func_184255_d() || tileentitycommandblock.func_184254_e()) {
               if(tileentitycommandblock$mode == TileEntityCommandBlock.Mode.SEQUENCE && flag1 && flag) {
                  commandblockbaselogic.func_145755_a(p_180650_1_);
                  flag3 = true;
               }

               if(tileentitycommandblock$mode == TileEntityCommandBlock.Mode.AUTO) {
                  p_180650_1_.func_175684_a(p_180650_2_, this, this.func_149738_a(p_180650_1_));
                  if(flag1) {
                     this.func_185563_c(p_180650_1_, p_180650_2_);
                  }
               }
            }

            if(!flag3) {
               commandblockbaselogic.func_184167_a(0);
            }

            tileentitycommandblock.func_184249_c(flag1);
            p_180650_1_.func_175666_e(p_180650_2_, this);
         }

      }
   }

   public boolean func_185562_e(World p_185562_1_, BlockPos p_185562_2_, IBlockState p_185562_3_) {
      EnumFacing enumfacing = (EnumFacing)p_185562_3_.func_177229_b(field_185564_a);
      TileEntity tileentity = p_185562_1_.func_175625_s(p_185562_2_.func_177972_a(enumfacing.func_176734_d()));
      return tileentity instanceof TileEntityCommandBlock && ((TileEntityCommandBlock)tileentity).func_145993_a().func_145760_g() > 0;
   }

   public int func_149738_a(World p_149738_1_) {
      return 1;
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumHand p_180639_5_, ItemStack p_180639_6_, EnumFacing p_180639_7_, float p_180639_8_, float p_180639_9_, float p_180639_10_) {
      TileEntity tileentity = p_180639_1_.func_175625_s(p_180639_2_);
      if(tileentity instanceof TileEntityCommandBlock) {
         if(!p_180639_4_.field_71075_bZ.field_75098_d) {
            return false;
         } else {
            p_180639_4_.func_184824_a((TileEntityCommandBlock)tileentity);
            return true;
         }
      } else {
         return false;
      }
   }

   public boolean func_149740_M(IBlockState p_149740_1_) {
      return true;
   }

   public int func_180641_l(IBlockState p_180641_1_, World p_180641_2_, BlockPos p_180641_3_) {
      TileEntity tileentity = p_180641_2_.func_175625_s(p_180641_3_);
      return tileentity instanceof TileEntityCommandBlock?((TileEntityCommandBlock)tileentity).func_145993_a().func_145760_g():0;
   }

   public void func_180633_a(World p_180633_1_, BlockPos p_180633_2_, IBlockState p_180633_3_, EntityLivingBase p_180633_4_, ItemStack p_180633_5_) {
      TileEntity tileentity = p_180633_1_.func_175625_s(p_180633_2_);
      if(tileentity instanceof TileEntityCommandBlock) {
         TileEntityCommandBlock tileentitycommandblock = (TileEntityCommandBlock)tileentity;
         CommandBlockBaseLogic commandblockbaselogic = tileentitycommandblock.func_145993_a();
         if(p_180633_5_.func_82837_s()) {
            commandblockbaselogic.func_145754_b(p_180633_5_.func_82833_r());
         }

         if(!p_180633_1_.field_72995_K) {
            NBTTagCompound nbttagcompound = p_180633_5_.func_77978_p();
            if(nbttagcompound == null || !nbttagcompound.func_150297_b("BlockEntityTag", 10)) {
               commandblockbaselogic.func_175573_a(p_180633_1_.func_82736_K().func_82766_b("sendCommandFeedback"));
               tileentitycommandblock.func_184253_b(this == Blocks.field_185777_dd);
            }

            if(tileentitycommandblock.func_184251_i() == TileEntityCommandBlock.Mode.SEQUENCE) {
               boolean flag = p_180633_1_.func_175640_z(p_180633_2_);
               tileentitycommandblock.func_184250_a(flag);
            }
         }

      }
   }

   public int func_149745_a(Random p_149745_1_) {
      return 0;
   }

   public EnumBlockRenderType func_149645_b(IBlockState p_149645_1_) {
      return EnumBlockRenderType.MODEL;
   }

   public IBlockState func_176203_a(int p_176203_1_) {
      return this.func_176223_P().func_177226_a(field_185564_a, EnumFacing.func_82600_a(p_176203_1_ & 7)).func_177226_a(field_185565_b, Boolean.valueOf((p_176203_1_ & 8) != 0));
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((EnumFacing)p_176201_1_.func_177229_b(field_185564_a)).func_176745_a() | (((Boolean)p_176201_1_.func_177229_b(field_185565_b)).booleanValue()?8:0);
   }

   public IBlockState func_185499_a(IBlockState p_185499_1_, Rotation p_185499_2_) {
      return p_185499_1_.func_177226_a(field_185564_a, p_185499_2_.func_185831_a((EnumFacing)p_185499_1_.func_177229_b(field_185564_a)));
   }

   public IBlockState func_185471_a(IBlockState p_185471_1_, Mirror p_185471_2_) {
      return p_185471_1_.func_185907_a(p_185471_2_.func_185800_a((EnumFacing)p_185471_1_.func_177229_b(field_185564_a)));
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{field_185564_a, field_185565_b});
   }

   public IBlockState func_180642_a(World p_180642_1_, BlockPos p_180642_2_, EnumFacing p_180642_3_, float p_180642_4_, float p_180642_5_, float p_180642_6_, int p_180642_7_, EntityLivingBase p_180642_8_) {
      return this.func_176223_P().func_177226_a(field_185564_a, BlockPistonBase.func_185647_a(p_180642_2_, p_180642_8_)).func_177226_a(field_185565_b, Boolean.valueOf(false));
   }

   public void func_185563_c(World p_185563_1_, BlockPos p_185563_2_) {
      IBlockState iblockstate = p_185563_1_.func_180495_p(p_185563_2_);
      if(iblockstate.func_177230_c() == Blocks.field_150483_bI || iblockstate.func_177230_c() == Blocks.field_185776_dc) {
         BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(p_185563_2_);
         blockpos$mutableblockpos.func_185337_c((EnumFacing)iblockstate.func_177229_b(field_185564_a));

         for(TileEntity tileentity = p_185563_1_.func_175625_s(blockpos$mutableblockpos); tileentity instanceof TileEntityCommandBlock; tileentity = p_185563_1_.func_175625_s(blockpos$mutableblockpos)) {
            TileEntityCommandBlock tileentitycommandblock = (TileEntityCommandBlock)tileentity;
            if(tileentitycommandblock.func_184251_i() != TileEntityCommandBlock.Mode.SEQUENCE) {
               break;
            }

            IBlockState iblockstate1 = p_185563_1_.func_180495_p(blockpos$mutableblockpos);
            Block block = iblockstate1.func_177230_c();
            if(block != Blocks.field_185777_dd || p_185563_1_.func_184145_b(blockpos$mutableblockpos, block)) {
               break;
            }

            p_185563_1_.func_175684_a(new BlockPos(blockpos$mutableblockpos), block, this.func_149738_a(p_185563_1_));
            blockpos$mutableblockpos.func_185337_c((EnumFacing)iblockstate1.func_177229_b(field_185564_a));
         }

      }
   }
}
