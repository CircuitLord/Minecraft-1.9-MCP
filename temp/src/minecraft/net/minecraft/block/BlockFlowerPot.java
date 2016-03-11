package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFlowerPot extends BlockContainer {
   public static final PropertyInteger field_176444_a = PropertyInteger.func_177719_a("legacy_data", 0, 15);
   public static final PropertyEnum<BlockFlowerPot.EnumFlowerType> field_176443_b = PropertyEnum.<BlockFlowerPot.EnumFlowerType>func_177709_a("contents", BlockFlowerPot.EnumFlowerType.class);
   protected static final AxisAlignedBB field_185570_c = new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 0.375D, 0.6875D);

   public BlockFlowerPot() {
      super(Material.field_151594_q);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(field_176443_b, BlockFlowerPot.EnumFlowerType.EMPTY).func_177226_a(field_176444_a, Integer.valueOf(0)));
   }

   public String func_149732_F() {
      return I18n.func_74838_a("item.flowerPot.name");
   }

   public AxisAlignedBB func_185496_a(IBlockState p_185496_1_, IBlockAccess p_185496_2_, BlockPos p_185496_3_) {
      return field_185570_c;
   }

   public boolean func_149662_c(IBlockState p_149662_1_) {
      return false;
   }

   public EnumBlockRenderType func_149645_b(IBlockState p_149645_1_) {
      return EnumBlockRenderType.MODEL;
   }

   public boolean func_149686_d(IBlockState p_149686_1_) {
      return false;
   }

   public boolean func_180639_a(World p_180639_1_, BlockPos p_180639_2_, IBlockState p_180639_3_, EntityPlayer p_180639_4_, EnumHand p_180639_5_, ItemStack p_180639_6_, EnumFacing p_180639_7_, float p_180639_8_, float p_180639_9_, float p_180639_10_) {
      if(p_180639_6_ != null && p_180639_6_.func_77973_b() instanceof ItemBlock) {
         TileEntityFlowerPot tileentityflowerpot = this.func_176442_d(p_180639_1_, p_180639_2_);
         if(tileentityflowerpot == null) {
            return false;
         } else if(tileentityflowerpot.func_145965_a() != null) {
            return false;
         } else {
            Block block = Block.func_149634_a(p_180639_6_.func_77973_b());
            if(!this.func_149928_a(block, p_180639_6_.func_77960_j())) {
               return false;
            } else {
               tileentityflowerpot.func_145964_a(p_180639_6_.func_77973_b(), p_180639_6_.func_77960_j());
               tileentityflowerpot.func_70296_d();
               p_180639_1_.func_184138_a(p_180639_2_, p_180639_3_, p_180639_3_, 3);
               p_180639_4_.func_71029_a(StatList.field_188088_V);
               if(!p_180639_4_.field_71075_bZ.field_75098_d) {
                  --p_180639_6_.field_77994_a;
               }

               return true;
            }
         }
      } else {
         return false;
      }
   }

   private boolean func_149928_a(Block p_149928_1_, int p_149928_2_) {
      return p_149928_1_ != Blocks.field_150327_N && p_149928_1_ != Blocks.field_150328_O && p_149928_1_ != Blocks.field_150434_aF && p_149928_1_ != Blocks.field_150338_P && p_149928_1_ != Blocks.field_150337_Q && p_149928_1_ != Blocks.field_150345_g && p_149928_1_ != Blocks.field_150330_I?p_149928_1_ == Blocks.field_150329_H && p_149928_2_ == BlockTallGrass.EnumType.FERN.func_177044_a():true;
   }

   public ItemStack func_185473_a(World p_185473_1_, BlockPos p_185473_2_, IBlockState p_185473_3_) {
      TileEntityFlowerPot tileentityflowerpot = this.func_176442_d(p_185473_1_, p_185473_2_);
      if(tileentityflowerpot != null) {
         ItemStack itemstack = tileentityflowerpot.func_184403_b();
         if(itemstack != null) {
            return itemstack;
         }
      }

      return new ItemStack(Items.field_151162_bE);
   }

   public boolean func_176196_c(World p_176196_1_, BlockPos p_176196_2_) {
      return super.func_176196_c(p_176196_1_, p_176196_2_) && p_176196_1_.func_180495_p(p_176196_2_.func_177977_b()).func_185896_q();
   }

   public void func_176204_a(World p_176204_1_, BlockPos p_176204_2_, IBlockState p_176204_3_, Block p_176204_4_) {
      if(!p_176204_1_.func_180495_p(p_176204_2_.func_177977_b()).func_185896_q()) {
         this.func_176226_b(p_176204_1_, p_176204_2_, p_176204_3_, 0);
         p_176204_1_.func_175698_g(p_176204_2_);
      }

   }

   public void func_180663_b(World p_180663_1_, BlockPos p_180663_2_, IBlockState p_180663_3_) {
      TileEntityFlowerPot tileentityflowerpot = this.func_176442_d(p_180663_1_, p_180663_2_);
      if(tileentityflowerpot != null && tileentityflowerpot.func_145965_a() != null) {
         func_180635_a(p_180663_1_, p_180663_2_, new ItemStack(tileentityflowerpot.func_145965_a(), 1, tileentityflowerpot.func_145966_b()));
      }

      super.func_180663_b(p_180663_1_, p_180663_2_, p_180663_3_);
   }

   public void func_176208_a(World p_176208_1_, BlockPos p_176208_2_, IBlockState p_176208_3_, EntityPlayer p_176208_4_) {
      super.func_176208_a(p_176208_1_, p_176208_2_, p_176208_3_, p_176208_4_);
      if(p_176208_4_.field_71075_bZ.field_75098_d) {
         TileEntityFlowerPot tileentityflowerpot = this.func_176442_d(p_176208_1_, p_176208_2_);
         if(tileentityflowerpot != null) {
            tileentityflowerpot.func_145964_a((Item)null, 0);
         }
      }

   }

   public Item func_180660_a(IBlockState p_180660_1_, Random p_180660_2_, int p_180660_3_) {
      return Items.field_151162_bE;
   }

   private TileEntityFlowerPot func_176442_d(World p_176442_1_, BlockPos p_176442_2_) {
      TileEntity tileentity = p_176442_1_.func_175625_s(p_176442_2_);
      return tileentity instanceof TileEntityFlowerPot?(TileEntityFlowerPot)tileentity:null;
   }

   public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
      Block block = null;
      int i = 0;
      switch(p_149915_2_) {
      case 1:
         block = Blocks.field_150328_O;
         i = BlockFlower.EnumFlowerType.POPPY.func_176968_b();
         break;
      case 2:
         block = Blocks.field_150327_N;
         break;
      case 3:
         block = Blocks.field_150345_g;
         i = BlockPlanks.EnumType.OAK.func_176839_a();
         break;
      case 4:
         block = Blocks.field_150345_g;
         i = BlockPlanks.EnumType.SPRUCE.func_176839_a();
         break;
      case 5:
         block = Blocks.field_150345_g;
         i = BlockPlanks.EnumType.BIRCH.func_176839_a();
         break;
      case 6:
         block = Blocks.field_150345_g;
         i = BlockPlanks.EnumType.JUNGLE.func_176839_a();
         break;
      case 7:
         block = Blocks.field_150337_Q;
         break;
      case 8:
         block = Blocks.field_150338_P;
         break;
      case 9:
         block = Blocks.field_150434_aF;
         break;
      case 10:
         block = Blocks.field_150330_I;
         break;
      case 11:
         block = Blocks.field_150329_H;
         i = BlockTallGrass.EnumType.FERN.func_177044_a();
         break;
      case 12:
         block = Blocks.field_150345_g;
         i = BlockPlanks.EnumType.ACACIA.func_176839_a();
         break;
      case 13:
         block = Blocks.field_150345_g;
         i = BlockPlanks.EnumType.DARK_OAK.func_176839_a();
      }

      return new TileEntityFlowerPot(Item.func_150898_a(block), i);
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{field_176443_b, field_176444_a});
   }

   public int func_176201_c(IBlockState p_176201_1_) {
      return ((Integer)p_176201_1_.func_177229_b(field_176444_a)).intValue();
   }

   public IBlockState func_176221_a(IBlockState p_176221_1_, IBlockAccess p_176221_2_, BlockPos p_176221_3_) {
      BlockFlowerPot.EnumFlowerType blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.EMPTY;
      TileEntity tileentity = p_176221_2_.func_175625_s(p_176221_3_);
      if(tileentity instanceof TileEntityFlowerPot) {
         TileEntityFlowerPot tileentityflowerpot = (TileEntityFlowerPot)tileentity;
         Item item = tileentityflowerpot.func_145965_a();
         if(item instanceof ItemBlock) {
            int i = tileentityflowerpot.func_145966_b();
            Block block = Block.func_149634_a(item);
            if(block == Blocks.field_150345_g) {
               switch(BlockPlanks.EnumType.func_176837_a(i)) {
               case OAK:
                  blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.OAK_SAPLING;
                  break;
               case SPRUCE:
                  blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.SPRUCE_SAPLING;
                  break;
               case BIRCH:
                  blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.BIRCH_SAPLING;
                  break;
               case JUNGLE:
                  blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.JUNGLE_SAPLING;
                  break;
               case ACACIA:
                  blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.ACACIA_SAPLING;
                  break;
               case DARK_OAK:
                  blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.DARK_OAK_SAPLING;
                  break;
               default:
                  blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.EMPTY;
               }
            } else if(block == Blocks.field_150329_H) {
               switch(i) {
               case 0:
                  blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.DEAD_BUSH;
                  break;
               case 2:
                  blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.FERN;
                  break;
               default:
                  blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.EMPTY;
               }
            } else if(block == Blocks.field_150327_N) {
               blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.DANDELION;
            } else if(block == Blocks.field_150328_O) {
               switch(BlockFlower.EnumFlowerType.func_176967_a(BlockFlower.EnumFlowerColor.RED, i)) {
               case POPPY:
                  blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.POPPY;
                  break;
               case BLUE_ORCHID:
                  blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.BLUE_ORCHID;
                  break;
               case ALLIUM:
                  blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.ALLIUM;
                  break;
               case HOUSTONIA:
                  blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.HOUSTONIA;
                  break;
               case RED_TULIP:
                  blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.RED_TULIP;
                  break;
               case ORANGE_TULIP:
                  blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.ORANGE_TULIP;
                  break;
               case WHITE_TULIP:
                  blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.WHITE_TULIP;
                  break;
               case PINK_TULIP:
                  blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.PINK_TULIP;
                  break;
               case OXEYE_DAISY:
                  blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.OXEYE_DAISY;
                  break;
               default:
                  blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.EMPTY;
               }
            } else if(block == Blocks.field_150337_Q) {
               blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.MUSHROOM_RED;
            } else if(block == Blocks.field_150338_P) {
               blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.MUSHROOM_BROWN;
            } else if(block == Blocks.field_150330_I) {
               blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.DEAD_BUSH;
            } else if(block == Blocks.field_150434_aF) {
               blockflowerpot$enumflowertype = BlockFlowerPot.EnumFlowerType.CACTUS;
            }
         }
      }

      return p_176221_1_.func_177226_a(field_176443_b, blockflowerpot$enumflowertype);
   }

   public BlockRenderLayer func_180664_k() {
      return BlockRenderLayer.CUTOUT;
   }

   public static enum EnumFlowerType implements IStringSerializable {
      EMPTY("empty"),
      POPPY("rose"),
      BLUE_ORCHID("blue_orchid"),
      ALLIUM("allium"),
      HOUSTONIA("houstonia"),
      RED_TULIP("red_tulip"),
      ORANGE_TULIP("orange_tulip"),
      WHITE_TULIP("white_tulip"),
      PINK_TULIP("pink_tulip"),
      OXEYE_DAISY("oxeye_daisy"),
      DANDELION("dandelion"),
      OAK_SAPLING("oak_sapling"),
      SPRUCE_SAPLING("spruce_sapling"),
      BIRCH_SAPLING("birch_sapling"),
      JUNGLE_SAPLING("jungle_sapling"),
      ACACIA_SAPLING("acacia_sapling"),
      DARK_OAK_SAPLING("dark_oak_sapling"),
      MUSHROOM_RED("mushroom_red"),
      MUSHROOM_BROWN("mushroom_brown"),
      DEAD_BUSH("dead_bush"),
      FERN("fern"),
      CACTUS("cactus");

      private final String field_177006_w;

      private EnumFlowerType(String p_i45715_3_) {
         this.field_177006_w = p_i45715_3_;
      }

      public String toString() {
         return this.field_177006_w;
      }

      public String func_176610_l() {
         return this.field_177006_w;
      }
   }
}
