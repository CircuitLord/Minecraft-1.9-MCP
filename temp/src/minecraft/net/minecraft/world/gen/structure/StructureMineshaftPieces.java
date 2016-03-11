package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockRail;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.storage.loot.LootTableList;

public class StructureMineshaftPieces {
   public static void func_143048_a() {
      MapGenStructureIO.func_143031_a(StructureMineshaftPieces.Corridor.class, "MSCorridor");
      MapGenStructureIO.func_143031_a(StructureMineshaftPieces.Cross.class, "MSCrossing");
      MapGenStructureIO.func_143031_a(StructureMineshaftPieces.Room.class, "MSRoom");
      MapGenStructureIO.func_143031_a(StructureMineshaftPieces.Stairs.class, "MSStairs");
   }

   private static StructureComponent func_175892_a(List<StructureComponent> p_175892_0_, Random p_175892_1_, int p_175892_2_, int p_175892_3_, int p_175892_4_, EnumFacing p_175892_5_, int p_175892_6_) {
      int i = p_175892_1_.nextInt(100);
      if(i >= 80) {
         StructureBoundingBox structureboundingbox = StructureMineshaftPieces.Cross.func_175813_a(p_175892_0_, p_175892_1_, p_175892_2_, p_175892_3_, p_175892_4_, p_175892_5_);
         if(structureboundingbox != null) {
            return new StructureMineshaftPieces.Cross(p_175892_6_, p_175892_1_, structureboundingbox, p_175892_5_);
         }
      } else if(i >= 70) {
         StructureBoundingBox structureboundingbox1 = StructureMineshaftPieces.Stairs.func_175812_a(p_175892_0_, p_175892_1_, p_175892_2_, p_175892_3_, p_175892_4_, p_175892_5_);
         if(structureboundingbox1 != null) {
            return new StructureMineshaftPieces.Stairs(p_175892_6_, p_175892_1_, structureboundingbox1, p_175892_5_);
         }
      } else {
         StructureBoundingBox structureboundingbox2 = StructureMineshaftPieces.Corridor.func_175814_a(p_175892_0_, p_175892_1_, p_175892_2_, p_175892_3_, p_175892_4_, p_175892_5_);
         if(structureboundingbox2 != null) {
            return new StructureMineshaftPieces.Corridor(p_175892_6_, p_175892_1_, structureboundingbox2, p_175892_5_);
         }
      }

      return null;
   }

   private static StructureComponent func_175890_b(StructureComponent p_175890_0_, List<StructureComponent> p_175890_1_, Random p_175890_2_, int p_175890_3_, int p_175890_4_, int p_175890_5_, EnumFacing p_175890_6_, int p_175890_7_) {
      if(p_175890_7_ > 8) {
         return null;
      } else if(Math.abs(p_175890_3_ - p_175890_0_.func_74874_b().field_78897_a) <= 80 && Math.abs(p_175890_5_ - p_175890_0_.func_74874_b().field_78896_c) <= 80) {
         StructureComponent structurecomponent = func_175892_a(p_175890_1_, p_175890_2_, p_175890_3_, p_175890_4_, p_175890_5_, p_175890_6_, p_175890_7_ + 1);
         if(structurecomponent != null) {
            p_175890_1_.add(structurecomponent);
            structurecomponent.func_74861_a(p_175890_0_, p_175890_1_, p_175890_2_);
         }

         return structurecomponent;
      } else {
         return null;
      }
   }

   public static class Corridor extends StructureComponent {
      private boolean field_74958_a;
      private boolean field_74956_b;
      private boolean field_74957_c;
      private int field_74955_d;

      public Corridor() {
      }

      protected void func_143012_a(NBTTagCompound p_143012_1_) {
         p_143012_1_.func_74757_a("hr", this.field_74958_a);
         p_143012_1_.func_74757_a("sc", this.field_74956_b);
         p_143012_1_.func_74757_a("hps", this.field_74957_c);
         p_143012_1_.func_74768_a("Num", this.field_74955_d);
      }

      protected void func_143011_b(NBTTagCompound p_143011_1_) {
         this.field_74958_a = p_143011_1_.func_74767_n("hr");
         this.field_74956_b = p_143011_1_.func_74767_n("sc");
         this.field_74957_c = p_143011_1_.func_74767_n("hps");
         this.field_74955_d = p_143011_1_.func_74762_e("Num");
      }

      public Corridor(int p_i45625_1_, Random p_i45625_2_, StructureBoundingBox p_i45625_3_, EnumFacing p_i45625_4_) {
         super(p_i45625_1_);
         this.func_186164_a(p_i45625_4_);
         this.field_74887_e = p_i45625_3_;
         this.field_74958_a = p_i45625_2_.nextInt(3) == 0;
         this.field_74956_b = !this.field_74958_a && p_i45625_2_.nextInt(23) == 0;
         if(this.func_186165_e().func_176740_k() == EnumFacing.Axis.Z) {
            this.field_74955_d = p_i45625_3_.func_78880_d() / 5;
         } else {
            this.field_74955_d = p_i45625_3_.func_78883_b() / 5;
         }

      }

      public static StructureBoundingBox func_175814_a(List<StructureComponent> p_175814_0_, Random p_175814_1_, int p_175814_2_, int p_175814_3_, int p_175814_4_, EnumFacing p_175814_5_) {
         StructureBoundingBox structureboundingbox = new StructureBoundingBox(p_175814_2_, p_175814_3_, p_175814_4_, p_175814_2_, p_175814_3_ + 2, p_175814_4_);

         int i;
         for(i = p_175814_1_.nextInt(3) + 2; i > 0; --i) {
            int j = i * 5;
            switch(p_175814_5_) {
            case NORTH:
               structureboundingbox.field_78893_d = p_175814_2_ + 2;
               structureboundingbox.field_78896_c = p_175814_4_ - (j - 1);
               break;
            case SOUTH:
               structureboundingbox.field_78893_d = p_175814_2_ + 2;
               structureboundingbox.field_78892_f = p_175814_4_ + (j - 1);
               break;
            case WEST:
               structureboundingbox.field_78897_a = p_175814_2_ - (j - 1);
               structureboundingbox.field_78892_f = p_175814_4_ + 2;
               break;
            case EAST:
               structureboundingbox.field_78893_d = p_175814_2_ + (j - 1);
               structureboundingbox.field_78892_f = p_175814_4_ + 2;
            }

            if(StructureComponent.func_74883_a(p_175814_0_, structureboundingbox) == null) {
               break;
            }
         }

         return i > 0?structureboundingbox:null;
      }

      public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
         int i = this.func_74877_c();
         int j = p_74861_3_.nextInt(4);
         EnumFacing enumfacing = this.func_186165_e();
         if(enumfacing != null) {
            switch(enumfacing) {
            case NORTH:
               if(j <= 1) {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c - 1, enumfacing, i);
               } else if(j == 2) {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c, EnumFacing.WEST, i);
               } else {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c, EnumFacing.EAST, i);
               }
               break;
            case SOUTH:
               if(j <= 1) {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78892_f + 1, enumfacing, i);
               } else if(j == 2) {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78892_f - 3, EnumFacing.WEST, i);
               } else {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78892_f - 3, EnumFacing.EAST, i);
               }
               break;
            case WEST:
               if(j <= 1) {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c, enumfacing, i);
               } else if(j == 2) {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c - 1, EnumFacing.NORTH, i);
               } else {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78892_f + 1, EnumFacing.SOUTH, i);
               }
               break;
            case EAST:
               if(j <= 1) {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c, enumfacing, i);
               } else if(j == 2) {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d - 3, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78896_c - 1, EnumFacing.NORTH, i);
               } else {
                  StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d - 3, this.field_74887_e.field_78895_b - 1 + p_74861_3_.nextInt(3), this.field_74887_e.field_78892_f + 1, EnumFacing.SOUTH, i);
               }
            }
         }

         if(i < 8) {
            if(enumfacing != EnumFacing.NORTH && enumfacing != EnumFacing.SOUTH) {
               for(int i1 = this.field_74887_e.field_78897_a + 3; i1 + 3 <= this.field_74887_e.field_78893_d; i1 += 5) {
                  int j1 = p_74861_3_.nextInt(5);
                  if(j1 == 0) {
                     StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, i1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c - 1, EnumFacing.NORTH, i + 1);
                  } else if(j1 == 1) {
                     StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, i1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f + 1, EnumFacing.SOUTH, i + 1);
                  }
               }
            } else {
               for(int k = this.field_74887_e.field_78896_c + 3; k + 3 <= this.field_74887_e.field_78892_f; k += 5) {
                  int l = p_74861_3_.nextInt(5);
                  if(l == 0) {
                     StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b, k, EnumFacing.WEST, i + 1);
                  } else if(l == 1) {
                     StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b, k, EnumFacing.EAST, i + 1);
                  }
               }
            }
         }

      }

      protected boolean func_186167_a(World p_186167_1_, StructureBoundingBox p_186167_2_, Random p_186167_3_, int p_186167_4_, int p_186167_5_, int p_186167_6_, ResourceLocation p_186167_7_) {
         BlockPos blockpos = new BlockPos(this.func_74865_a(p_186167_4_, p_186167_6_), this.func_74862_a(p_186167_5_), this.func_74873_b(p_186167_4_, p_186167_6_));
         if(p_186167_2_.func_175898_b(blockpos) && p_186167_1_.func_180495_p(blockpos).func_185904_a() == Material.field_151579_a) {
            IBlockState iblockstate = Blocks.field_150448_aq.func_176223_P().func_177226_a(BlockRail.field_176565_b, p_186167_3_.nextBoolean()?BlockRailBase.EnumRailDirection.NORTH_SOUTH:BlockRailBase.EnumRailDirection.EAST_WEST);
            this.func_175811_a(p_186167_1_, iblockstate, p_186167_4_, p_186167_5_, p_186167_6_, p_186167_2_);
            EntityMinecartChest entityminecartchest = new EntityMinecartChest(p_186167_1_, (double)((float)blockpos.func_177958_n() + 0.5F), (double)((float)blockpos.func_177956_o() + 0.5F), (double)((float)blockpos.func_177952_p() + 0.5F));
            entityminecartchest.func_184289_a(p_186167_7_, p_186167_3_.nextLong());
            p_186167_1_.func_72838_d(entityminecartchest);
            return true;
         } else {
            return false;
         }
      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         if(this.func_74860_a(p_74875_1_, p_74875_3_)) {
            return false;
         } else {
            int i = 0;
            int j = 2;
            int k = 0;
            int l = 2;
            int i1 = this.field_74955_d * 5 - 1;
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 0, 0, 2, 1, i1, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            this.func_175805_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.8F, 0, 2, 0, 2, 2, i1, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            if(this.field_74956_b) {
               this.func_175805_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.6F, 0, 0, 0, 2, 1, i1, Blocks.field_150321_G.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            }

            for(int j1 = 0; j1 < this.field_74955_d; ++j1) {
               int k1 = 2 + j1 * 5;
               this.func_175804_a(p_74875_1_, p_74875_3_, 0, 0, k1, 0, 1, k1, Blocks.field_180407_aO.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
               this.func_175804_a(p_74875_1_, p_74875_3_, 2, 0, k1, 2, 1, k1, Blocks.field_180407_aO.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
               if(p_74875_2_.nextInt(4) == 0) {
                  this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, k1, 0, 2, k1, Blocks.field_150344_f.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
                  this.func_175804_a(p_74875_1_, p_74875_3_, 2, 2, k1, 2, 2, k1, Blocks.field_150344_f.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
               } else {
                  this.func_175804_a(p_74875_1_, p_74875_3_, 0, 2, k1, 2, 2, k1, Blocks.field_150344_f.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
               }

               this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.1F, 0, 2, k1 - 1, Blocks.field_150321_G.func_176223_P());
               this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.1F, 2, 2, k1 - 1, Blocks.field_150321_G.func_176223_P());
               this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.1F, 0, 2, k1 + 1, Blocks.field_150321_G.func_176223_P());
               this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.1F, 2, 2, k1 + 1, Blocks.field_150321_G.func_176223_P());
               this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.05F, 0, 2, k1 - 2, Blocks.field_150321_G.func_176223_P());
               this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.05F, 2, 2, k1 - 2, Blocks.field_150321_G.func_176223_P());
               this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.05F, 0, 2, k1 + 2, Blocks.field_150321_G.func_176223_P());
               this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.05F, 2, 2, k1 + 2, Blocks.field_150321_G.func_176223_P());
               this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.05F, 1, 2, k1 - 1, Blocks.field_150478_aa.func_176223_P().func_177226_a(BlockTorch.field_176596_a, EnumFacing.SOUTH));
               this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.05F, 1, 2, k1 + 1, Blocks.field_150478_aa.func_176223_P().func_177226_a(BlockTorch.field_176596_a, EnumFacing.NORTH));
               if(p_74875_2_.nextInt(100) == 0) {
                  this.func_186167_a(p_74875_1_, p_74875_3_, p_74875_2_, 2, 0, k1 - 1, LootTableList.field_186424_f);
               }

               if(p_74875_2_.nextInt(100) == 0) {
                  this.func_186167_a(p_74875_1_, p_74875_3_, p_74875_2_, 0, 0, k1 + 1, LootTableList.field_186424_f);
               }

               if(this.field_74956_b && !this.field_74957_c) {
                  int l1 = this.func_74862_a(0);
                  int i2 = k1 - 1 + p_74875_2_.nextInt(3);
                  int j2 = this.func_74865_a(1, i2);
                  i2 = this.func_74873_b(1, i2);
                  BlockPos blockpos = new BlockPos(j2, l1, i2);
                  if(p_74875_3_.func_175898_b(blockpos)) {
                     this.field_74957_c = true;
                     p_74875_1_.func_180501_a(blockpos, Blocks.field_150474_ac.func_176223_P(), 2);
                     TileEntity tileentity = p_74875_1_.func_175625_s(blockpos);
                     if(tileentity instanceof TileEntityMobSpawner) {
                        ((TileEntityMobSpawner)tileentity).func_145881_a().func_98272_a("CaveSpider");
                     }
                  }
               }
            }

            for(int k2 = 0; k2 <= 2; ++k2) {
               for(int l2 = 0; l2 <= i1; ++l2) {
                  int j3 = -1;
                  IBlockState iblockstate2 = this.func_175807_a(p_74875_1_, k2, j3, l2, p_74875_3_);
                  if(iblockstate2.func_185904_a() == Material.field_151579_a) {
                     int k3 = -1;
                     this.func_175811_a(p_74875_1_, Blocks.field_150344_f.func_176223_P(), k2, k3, l2, p_74875_3_);
                  }
               }
            }

            if(this.field_74958_a) {
               IBlockState iblockstate = Blocks.field_150448_aq.func_176223_P().func_177226_a(BlockRail.field_176565_b, BlockRailBase.EnumRailDirection.NORTH_SOUTH);

               for(int i3 = 0; i3 <= i1; ++i3) {
                  IBlockState iblockstate1 = this.func_175807_a(p_74875_1_, 1, -1, i3, p_74875_3_);
                  if(iblockstate1.func_185904_a() != Material.field_151579_a && iblockstate1.func_185913_b()) {
                     this.func_175809_a(p_74875_1_, p_74875_3_, p_74875_2_, 0.7F, 1, 0, i3, iblockstate);
                  }
               }
            }

            return true;
         }
      }
   }

   public static class Cross extends StructureComponent {
      private EnumFacing field_74953_a;
      private boolean field_74952_b;

      public Cross() {
      }

      protected void func_143012_a(NBTTagCompound p_143012_1_) {
         p_143012_1_.func_74757_a("tf", this.field_74952_b);
         p_143012_1_.func_74768_a("D", this.field_74953_a.func_176736_b());
      }

      protected void func_143011_b(NBTTagCompound p_143011_1_) {
         this.field_74952_b = p_143011_1_.func_74767_n("tf");
         this.field_74953_a = EnumFacing.func_176731_b(p_143011_1_.func_74762_e("D"));
      }

      public Cross(int p_i45624_1_, Random p_i45624_2_, StructureBoundingBox p_i45624_3_, EnumFacing p_i45624_4_) {
         super(p_i45624_1_);
         this.field_74953_a = p_i45624_4_;
         this.field_74887_e = p_i45624_3_;
         this.field_74952_b = p_i45624_3_.func_78882_c() > 3;
      }

      public static StructureBoundingBox func_175813_a(List<StructureComponent> p_175813_0_, Random p_175813_1_, int p_175813_2_, int p_175813_3_, int p_175813_4_, EnumFacing p_175813_5_) {
         StructureBoundingBox structureboundingbox = new StructureBoundingBox(p_175813_2_, p_175813_3_, p_175813_4_, p_175813_2_, p_175813_3_ + 2, p_175813_4_);
         if(p_175813_1_.nextInt(4) == 0) {
            structureboundingbox.field_78894_e += 4;
         }

         switch(p_175813_5_) {
         case NORTH:
            structureboundingbox.field_78897_a = p_175813_2_ - 1;
            structureboundingbox.field_78893_d = p_175813_2_ + 3;
            structureboundingbox.field_78896_c = p_175813_4_ - 4;
            break;
         case SOUTH:
            structureboundingbox.field_78897_a = p_175813_2_ - 1;
            structureboundingbox.field_78893_d = p_175813_2_ + 3;
            structureboundingbox.field_78892_f = p_175813_4_ + 3 + 1;
            break;
         case WEST:
            structureboundingbox.field_78897_a = p_175813_2_ - 4;
            structureboundingbox.field_78896_c = p_175813_4_ - 1;
            structureboundingbox.field_78892_f = p_175813_4_ + 3;
            break;
         case EAST:
            structureboundingbox.field_78893_d = p_175813_2_ + 3 + 1;
            structureboundingbox.field_78896_c = p_175813_4_ - 1;
            structureboundingbox.field_78892_f = p_175813_4_ + 3;
         }

         return StructureComponent.func_74883_a(p_175813_0_, structureboundingbox) != null?null:structureboundingbox;
      }

      public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
         int i = this.func_74877_c();
         switch(this.field_74953_a) {
         case NORTH:
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c - 1, EnumFacing.NORTH, i);
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, EnumFacing.WEST, i);
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, EnumFacing.EAST, i);
            break;
         case SOUTH:
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f + 1, EnumFacing.SOUTH, i);
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, EnumFacing.WEST, i);
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, EnumFacing.EAST, i);
            break;
         case WEST:
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c - 1, EnumFacing.NORTH, i);
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f + 1, EnumFacing.SOUTH, i);
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, EnumFacing.WEST, i);
            break;
         case EAST:
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c - 1, EnumFacing.NORTH, i);
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f + 1, EnumFacing.SOUTH, i);
            StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, EnumFacing.EAST, i);
         }

         if(this.field_74952_b) {
            if(p_74861_3_.nextBoolean()) {
               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b + 3 + 1, this.field_74887_e.field_78896_c - 1, EnumFacing.NORTH, i);
            }

            if(p_74861_3_.nextBoolean()) {
               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b + 3 + 1, this.field_74887_e.field_78896_c + 1, EnumFacing.WEST, i);
            }

            if(p_74861_3_.nextBoolean()) {
               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b + 3 + 1, this.field_74887_e.field_78896_c + 1, EnumFacing.EAST, i);
            }

            if(p_74861_3_.nextBoolean()) {
               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b + 3 + 1, this.field_74887_e.field_78892_f + 1, EnumFacing.SOUTH, i);
            }
         }

      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         if(this.func_74860_a(p_74875_1_, p_74875_3_)) {
            return false;
         } else {
            if(this.field_74952_b) {
               this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78895_b + 3 - 1, this.field_74887_e.field_78892_f, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
               this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, this.field_74887_e.field_78893_d, this.field_74887_e.field_78895_b + 3 - 1, this.field_74887_e.field_78892_f - 1, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
               this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78894_e - 2, this.field_74887_e.field_78896_c, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
               this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78894_e - 2, this.field_74887_e.field_78896_c + 1, this.field_74887_e.field_78893_d, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f - 1, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
               this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b + 3, this.field_74887_e.field_78896_c + 1, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78895_b + 3, this.field_74887_e.field_78892_f - 1, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            } else {
               this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
               this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, this.field_74887_e.field_78893_d, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f - 1, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            }

            this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78894_e, this.field_74887_e.field_78896_c + 1, Blocks.field_150344_f.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f - 1, this.field_74887_e.field_78897_a + 1, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f - 1, Blocks.field_150344_f.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c + 1, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78894_e, this.field_74887_e.field_78896_c + 1, Blocks.field_150344_f.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f - 1, this.field_74887_e.field_78893_d - 1, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f - 1, Blocks.field_150344_f.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);

            for(int i = this.field_74887_e.field_78897_a; i <= this.field_74887_e.field_78893_d; ++i) {
               for(int j = this.field_74887_e.field_78896_c; j <= this.field_74887_e.field_78892_f; ++j) {
                  if(this.func_175807_a(p_74875_1_, i, this.field_74887_e.field_78895_b - 1, j, p_74875_3_).func_185904_a() == Material.field_151579_a) {
                     this.func_175811_a(p_74875_1_, Blocks.field_150344_f.func_176223_P(), i, this.field_74887_e.field_78895_b - 1, j, p_74875_3_);
                  }
               }
            }

            return true;
         }
      }
   }

   public static class Room extends StructureComponent {
      private List<StructureBoundingBox> field_74949_a = Lists.<StructureBoundingBox>newLinkedList();

      public Room() {
      }

      public Room(int p_i2037_1_, Random p_i2037_2_, int p_i2037_3_, int p_i2037_4_) {
         super(p_i2037_1_);
         this.field_74887_e = new StructureBoundingBox(p_i2037_3_, 50, p_i2037_4_, p_i2037_3_ + 7 + p_i2037_2_.nextInt(6), 54 + p_i2037_2_.nextInt(6), p_i2037_4_ + 7 + p_i2037_2_.nextInt(6));
      }

      public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
         int i = this.func_74877_c();
         int k = this.field_74887_e.func_78882_c() - 3 - 1;
         if(k <= 0) {
            k = 1;
         }

         int l;
         for(int j = 0; j < this.field_74887_e.func_78883_b(); j = l + 4) {
            l = j + p_74861_3_.nextInt(this.field_74887_e.func_78883_b());
            if(l + 3 > this.field_74887_e.func_78883_b()) {
               break;
            }

            StructureComponent structurecomponent = StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + l, this.field_74887_e.field_78895_b + p_74861_3_.nextInt(k) + 1, this.field_74887_e.field_78896_c - 1, EnumFacing.NORTH, i);
            if(structurecomponent != null) {
               StructureBoundingBox structureboundingbox = structurecomponent.func_74874_b();
               this.field_74949_a.add(new StructureBoundingBox(structureboundingbox.field_78897_a, structureboundingbox.field_78895_b, this.field_74887_e.field_78896_c, structureboundingbox.field_78893_d, structureboundingbox.field_78894_e, this.field_74887_e.field_78896_c + 1));
            }
         }

         for(int i1 = 0; i1 < this.field_74887_e.func_78883_b(); i1 = l + 4) {
            l = i1 + p_74861_3_.nextInt(this.field_74887_e.func_78883_b());
            if(l + 3 > this.field_74887_e.func_78883_b()) {
               break;
            }

            StructureComponent structurecomponent1 = StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a + l, this.field_74887_e.field_78895_b + p_74861_3_.nextInt(k) + 1, this.field_74887_e.field_78892_f + 1, EnumFacing.SOUTH, i);
            if(structurecomponent1 != null) {
               StructureBoundingBox structureboundingbox1 = structurecomponent1.func_74874_b();
               this.field_74949_a.add(new StructureBoundingBox(structureboundingbox1.field_78897_a, structureboundingbox1.field_78895_b, this.field_74887_e.field_78892_f - 1, structureboundingbox1.field_78893_d, structureboundingbox1.field_78894_e, this.field_74887_e.field_78892_f));
            }
         }

         for(int j1 = 0; j1 < this.field_74887_e.func_78880_d(); j1 = l + 4) {
            l = j1 + p_74861_3_.nextInt(this.field_74887_e.func_78880_d());
            if(l + 3 > this.field_74887_e.func_78880_d()) {
               break;
            }

            StructureComponent structurecomponent2 = StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b + p_74861_3_.nextInt(k) + 1, this.field_74887_e.field_78896_c + l, EnumFacing.WEST, i);
            if(structurecomponent2 != null) {
               StructureBoundingBox structureboundingbox2 = structurecomponent2.func_74874_b();
               this.field_74949_a.add(new StructureBoundingBox(this.field_74887_e.field_78897_a, structureboundingbox2.field_78895_b, structureboundingbox2.field_78896_c, this.field_74887_e.field_78897_a + 1, structureboundingbox2.field_78894_e, structureboundingbox2.field_78892_f));
            }
         }

         for(int k1 = 0; k1 < this.field_74887_e.func_78880_d(); k1 = l + 4) {
            l = k1 + p_74861_3_.nextInt(this.field_74887_e.func_78880_d());
            if(l + 3 > this.field_74887_e.func_78880_d()) {
               break;
            }

            StructureComponent structurecomponent3 = StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b + p_74861_3_.nextInt(k) + 1, this.field_74887_e.field_78896_c + l, EnumFacing.EAST, i);
            if(structurecomponent3 != null) {
               StructureBoundingBox structureboundingbox3 = structurecomponent3.func_74874_b();
               this.field_74949_a.add(new StructureBoundingBox(this.field_74887_e.field_78893_d - 1, structureboundingbox3.field_78895_b, structureboundingbox3.field_78896_c, this.field_74887_e.field_78893_d, structureboundingbox3.field_78894_e, structureboundingbox3.field_78892_f));
            }
         }

      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         if(this.func_74860_a(p_74875_1_, p_74875_3_)) {
            return false;
         } else {
            this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c, this.field_74887_e.field_78893_d, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f, Blocks.field_150346_d.func_176223_P(), Blocks.field_150350_a.func_176223_P(), true);
            this.func_175804_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b + 1, this.field_74887_e.field_78896_c, this.field_74887_e.field_78893_d, Math.min(this.field_74887_e.field_78895_b + 3, this.field_74887_e.field_78894_e), this.field_74887_e.field_78892_f, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);

            for(StructureBoundingBox structureboundingbox : this.field_74949_a) {
               this.func_175804_a(p_74875_1_, p_74875_3_, structureboundingbox.field_78897_a, structureboundingbox.field_78894_e - 2, structureboundingbox.field_78896_c, structureboundingbox.field_78893_d, structureboundingbox.field_78894_e, structureboundingbox.field_78892_f, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            }

            this.func_180777_a(p_74875_1_, p_74875_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b + 4, this.field_74887_e.field_78896_c, this.field_74887_e.field_78893_d, this.field_74887_e.field_78894_e, this.field_74887_e.field_78892_f, Blocks.field_150350_a.func_176223_P(), false);
            return true;
         }
      }

      public void func_181138_a(int p_181138_1_, int p_181138_2_, int p_181138_3_) {
         super.func_181138_a(p_181138_1_, p_181138_2_, p_181138_3_);

         for(StructureBoundingBox structureboundingbox : this.field_74949_a) {
            structureboundingbox.func_78886_a(p_181138_1_, p_181138_2_, p_181138_3_);
         }

      }

      protected void func_143012_a(NBTTagCompound p_143012_1_) {
         NBTTagList nbttaglist = new NBTTagList();

         for(StructureBoundingBox structureboundingbox : this.field_74949_a) {
            nbttaglist.func_74742_a(structureboundingbox.func_151535_h());
         }

         p_143012_1_.func_74782_a("Entrances", nbttaglist);
      }

      protected void func_143011_b(NBTTagCompound p_143011_1_) {
         NBTTagList nbttaglist = p_143011_1_.func_150295_c("Entrances", 11);

         for(int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            this.field_74949_a.add(new StructureBoundingBox(nbttaglist.func_150306_c(i)));
         }

      }
   }

   public static class Stairs extends StructureComponent {
      public Stairs() {
      }

      public Stairs(int p_i45623_1_, Random p_i45623_2_, StructureBoundingBox p_i45623_3_, EnumFacing p_i45623_4_) {
         super(p_i45623_1_);
         this.func_186164_a(p_i45623_4_);
         this.field_74887_e = p_i45623_3_;
      }

      protected void func_143012_a(NBTTagCompound p_143012_1_) {
      }

      protected void func_143011_b(NBTTagCompound p_143011_1_) {
      }

      public static StructureBoundingBox func_175812_a(List<StructureComponent> p_175812_0_, Random p_175812_1_, int p_175812_2_, int p_175812_3_, int p_175812_4_, EnumFacing p_175812_5_) {
         StructureBoundingBox structureboundingbox = new StructureBoundingBox(p_175812_2_, p_175812_3_ - 5, p_175812_4_, p_175812_2_, p_175812_3_ + 2, p_175812_4_);
         switch(p_175812_5_) {
         case NORTH:
            structureboundingbox.field_78893_d = p_175812_2_ + 2;
            structureboundingbox.field_78896_c = p_175812_4_ - 8;
            break;
         case SOUTH:
            structureboundingbox.field_78893_d = p_175812_2_ + 2;
            structureboundingbox.field_78892_f = p_175812_4_ + 8;
            break;
         case WEST:
            structureboundingbox.field_78897_a = p_175812_2_ - 8;
            structureboundingbox.field_78892_f = p_175812_4_ + 2;
            break;
         case EAST:
            structureboundingbox.field_78893_d = p_175812_2_ + 8;
            structureboundingbox.field_78892_f = p_175812_4_ + 2;
         }

         return StructureComponent.func_74883_a(p_175812_0_, structureboundingbox) != null?null:structureboundingbox;
      }

      public void func_74861_a(StructureComponent p_74861_1_, List<StructureComponent> p_74861_2_, Random p_74861_3_) {
         int i = this.func_74877_c();
         EnumFacing enumfacing = this.func_186165_e();
         if(enumfacing != null) {
            switch(enumfacing) {
            case NORTH:
               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c - 1, EnumFacing.NORTH, i);
               break;
            case SOUTH:
               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a, this.field_74887_e.field_78895_b, this.field_74887_e.field_78892_f + 1, EnumFacing.SOUTH, i);
               break;
            case WEST:
               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78897_a - 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c, EnumFacing.WEST, i);
               break;
            case EAST:
               StructureMineshaftPieces.func_175890_b(p_74861_1_, p_74861_2_, p_74861_3_, this.field_74887_e.field_78893_d + 1, this.field_74887_e.field_78895_b, this.field_74887_e.field_78896_c, EnumFacing.EAST, i);
            }
         }

      }

      public boolean func_74875_a(World p_74875_1_, Random p_74875_2_, StructureBoundingBox p_74875_3_) {
         if(this.func_74860_a(p_74875_1_, p_74875_3_)) {
            return false;
         } else {
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 5, 0, 2, 7, 1, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            this.func_175804_a(p_74875_1_, p_74875_3_, 0, 0, 7, 2, 2, 8, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);

            for(int i = 0; i < 5; ++i) {
               this.func_175804_a(p_74875_1_, p_74875_3_, 0, 5 - i - (i < 4?1:0), 2 + i, 2, 7 - i, 2 + i, Blocks.field_150350_a.func_176223_P(), Blocks.field_150350_a.func_176223_P(), false);
            }

            return true;
         }
      }
   }
}
