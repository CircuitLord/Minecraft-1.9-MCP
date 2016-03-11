package net.minecraft.tileentity;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStructure;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class TileEntityStructure extends TileEntity {
   private String field_184420_a = "";
   private String field_184421_f = "";
   private String field_184422_g = "";
   private BlockPos field_184423_h = new BlockPos(1, 1, 1);
   private BlockPos field_184424_i = BlockPos.field_177992_a;
   private Mirror field_184425_j = Mirror.NONE;
   private Rotation field_184426_k = Rotation.NONE;
   private TileEntityStructure.Mode field_184427_l = TileEntityStructure.Mode.DATA;
   private boolean field_184428_m;

   public void func_145841_b(NBTTagCompound p_145841_1_) {
      super.func_145841_b(p_145841_1_);
      p_145841_1_.func_74778_a("name", this.field_184420_a);
      p_145841_1_.func_74778_a("author", this.field_184421_f);
      p_145841_1_.func_74778_a("metadata", this.field_184422_g);
      p_145841_1_.func_74768_a("posX", this.field_184423_h.func_177958_n());
      p_145841_1_.func_74768_a("posY", this.field_184423_h.func_177956_o());
      p_145841_1_.func_74768_a("posZ", this.field_184423_h.func_177952_p());
      p_145841_1_.func_74768_a("sizeX", this.field_184424_i.func_177958_n());
      p_145841_1_.func_74768_a("sizeY", this.field_184424_i.func_177956_o());
      p_145841_1_.func_74768_a("sizeZ", this.field_184424_i.func_177952_p());
      p_145841_1_.func_74778_a("rotation", this.field_184426_k.toString());
      p_145841_1_.func_74778_a("mirror", this.field_184425_j.toString());
      p_145841_1_.func_74778_a("mode", this.field_184427_l.toString());
      p_145841_1_.func_74757_a("ignoreEntities", this.field_184428_m);
   }

   public void func_145839_a(NBTTagCompound p_145839_1_) {
      super.func_145839_a(p_145839_1_);
      this.field_184420_a = p_145839_1_.func_74779_i("name");
      this.field_184421_f = p_145839_1_.func_74779_i("author");
      this.field_184422_g = p_145839_1_.func_74779_i("metadata");
      this.field_184423_h = new BlockPos(p_145839_1_.func_74762_e("posX"), p_145839_1_.func_74762_e("posY"), p_145839_1_.func_74762_e("posZ"));
      this.field_184424_i = new BlockPos(p_145839_1_.func_74762_e("sizeX"), p_145839_1_.func_74762_e("sizeY"), p_145839_1_.func_74762_e("sizeZ"));

      try {
         this.field_184426_k = Rotation.valueOf(p_145839_1_.func_74779_i("rotation"));
      } catch (IllegalArgumentException var5) {
         this.field_184426_k = Rotation.NONE;
      }

      try {
         this.field_184425_j = Mirror.valueOf(p_145839_1_.func_74779_i("mirror"));
      } catch (IllegalArgumentException var4) {
         this.field_184425_j = Mirror.NONE;
      }

      try {
         this.field_184427_l = TileEntityStructure.Mode.valueOf(p_145839_1_.func_74779_i("mode"));
      } catch (IllegalArgumentException var3) {
         this.field_184427_l = TileEntityStructure.Mode.DATA;
      }

      this.field_184428_m = p_145839_1_.func_74767_n("ignoreEntities");
   }

   public Packet<?> func_145844_m() {
      NBTTagCompound nbttagcompound = new NBTTagCompound();
      this.func_145841_b(nbttagcompound);
      return new SPacketUpdateTileEntity(this.field_174879_c, 7, nbttagcompound);
   }

   public void func_184404_a(String p_184404_1_) {
      this.field_184420_a = p_184404_1_;
   }

   public void func_184414_b(BlockPos p_184414_1_) {
      this.field_184423_h = p_184414_1_;
   }

   public void func_184409_c(BlockPos p_184409_1_) {
      this.field_184424_i = p_184409_1_;
   }

   public void func_184411_a(Mirror p_184411_1_) {
      this.field_184425_j = p_184411_1_;
   }

   public void func_184408_a(Rotation p_184408_1_) {
      this.field_184426_k = p_184408_1_;
   }

   public void func_184410_b(String p_184410_1_) {
      this.field_184422_g = p_184410_1_;
   }

   public void func_184405_a(TileEntityStructure.Mode p_184405_1_) {
      this.field_184427_l = p_184405_1_;
      IBlockState iblockstate = this.field_145850_b.func_180495_p(this.func_174877_v());
      if(iblockstate.func_177230_c() == Blocks.field_185779_df) {
         this.field_145850_b.func_180501_a(this.func_174877_v(), iblockstate.func_177226_a(BlockStructure.field_185587_a, p_184405_1_), 2);
      }

   }

   public void func_184406_a(boolean p_184406_1_) {
      this.field_184428_m = p_184406_1_;
   }

   public boolean func_184417_l() {
      if(this.field_184427_l != TileEntityStructure.Mode.SAVE) {
         return false;
      } else {
         BlockPos blockpos = this.func_174877_v();
         int i = 128;
         BlockPos blockpos1 = new BlockPos(blockpos.func_177958_n() - 128, 0, blockpos.func_177952_p() - 128);
         BlockPos blockpos2 = new BlockPos(blockpos.func_177958_n() + 128, 255, blockpos.func_177952_p() + 128);
         List<TileEntityStructure> list = this.func_184418_a(blockpos1, blockpos2);
         List<TileEntityStructure> list1 = this.func_184415_a(list);
         if(list1.size() < 1) {
            return false;
         } else {
            StructureBoundingBox structureboundingbox = this.func_184416_a(blockpos, list1);
            if(structureboundingbox.field_78893_d - structureboundingbox.field_78897_a > 1 && structureboundingbox.field_78894_e - structureboundingbox.field_78895_b > 1 && structureboundingbox.field_78892_f - structureboundingbox.field_78896_c > 1) {
               this.field_184423_h = new BlockPos(structureboundingbox.field_78897_a - blockpos.func_177958_n() + 1, structureboundingbox.field_78895_b - blockpos.func_177956_o() + 1, structureboundingbox.field_78896_c - blockpos.func_177952_p() + 1);
               this.field_184424_i = new BlockPos(structureboundingbox.field_78893_d - structureboundingbox.field_78897_a - 1, structureboundingbox.field_78894_e - structureboundingbox.field_78895_b - 1, structureboundingbox.field_78892_f - structureboundingbox.field_78896_c - 1);
               this.func_70296_d();
               IBlockState iblockstate = this.field_145850_b.func_180495_p(blockpos);
               this.field_145850_b.func_184138_a(blockpos, iblockstate, iblockstate, 3);
               return true;
            } else {
               return false;
            }
         }
      }
   }

   private List<TileEntityStructure> func_184415_a(List<TileEntityStructure> p_184415_1_) {
      Iterable<TileEntityStructure> iterable = Iterables.filter(p_184415_1_, new Predicate<TileEntityStructure>() {
         public boolean apply(TileEntityStructure p_apply_1_) {
            return p_apply_1_.field_184427_l == TileEntityStructure.Mode.CORNER && TileEntityStructure.this.field_184420_a.equals(p_apply_1_.field_184420_a);
         }
      });
      return Lists.newArrayList(iterable);
   }

   private List<TileEntityStructure> func_184418_a(BlockPos p_184418_1_, BlockPos p_184418_2_) {
      List<TileEntityStructure> list = Lists.<TileEntityStructure>newArrayList();

      for(BlockPos.MutableBlockPos blockpos$mutableblockpos : BlockPos.func_177975_b(p_184418_1_, p_184418_2_)) {
         IBlockState iblockstate = this.field_145850_b.func_180495_p(blockpos$mutableblockpos);
         if(iblockstate.func_177230_c() == Blocks.field_185779_df) {
            TileEntity tileentity = this.field_145850_b.func_175625_s(blockpos$mutableblockpos);
            if(tileentity != null && tileentity instanceof TileEntityStructure) {
               list.add((TileEntityStructure)tileentity);
            }
         }
      }

      return list;
   }

   private StructureBoundingBox func_184416_a(BlockPos p_184416_1_, List<TileEntityStructure> p_184416_2_) {
      StructureBoundingBox structureboundingbox;
      if(p_184416_2_.size() > 1) {
         BlockPos blockpos = ((TileEntityStructure)p_184416_2_.get(0)).func_174877_v();
         structureboundingbox = new StructureBoundingBox(blockpos, blockpos);
      } else {
         structureboundingbox = new StructureBoundingBox(p_184416_1_, p_184416_1_);
      }

      for(TileEntityStructure tileentitystructure : p_184416_2_) {
         BlockPos blockpos1 = tileentitystructure.func_174877_v();
         if(blockpos1.func_177958_n() < structureboundingbox.field_78897_a) {
            structureboundingbox.field_78897_a = blockpos1.func_177958_n();
         } else if(blockpos1.func_177958_n() > structureboundingbox.field_78893_d) {
            structureboundingbox.field_78893_d = blockpos1.func_177958_n();
         }

         if(blockpos1.func_177956_o() < structureboundingbox.field_78895_b) {
            structureboundingbox.field_78895_b = blockpos1.func_177956_o();
         } else if(blockpos1.func_177956_o() > structureboundingbox.field_78894_e) {
            structureboundingbox.field_78894_e = blockpos1.func_177956_o();
         }

         if(blockpos1.func_177952_p() < structureboundingbox.field_78896_c) {
            structureboundingbox.field_78896_c = blockpos1.func_177952_p();
         } else if(blockpos1.func_177952_p() > structureboundingbox.field_78892_f) {
            structureboundingbox.field_78892_f = blockpos1.func_177952_p();
         }
      }

      return structureboundingbox;
   }

   public boolean func_184419_m() {
      if(this.field_184427_l == TileEntityStructure.Mode.SAVE && !this.field_145850_b.field_72995_K) {
         BlockPos blockpos = this.func_174877_v().func_177971_a(this.field_184423_h);
         WorldServer worldserver = (WorldServer)this.field_145850_b;
         MinecraftServer minecraftserver = this.field_145850_b.func_73046_m();
         TemplateManager templatemanager = worldserver.func_184163_y();
         Template template = templatemanager.func_186237_a(minecraftserver, new ResourceLocation(this.field_184420_a));
         template.func_186254_a(this.field_145850_b, blockpos, this.field_184424_i, !this.field_184428_m, Blocks.field_180401_cv);
         template.func_186252_a(this.field_184421_f);
         templatemanager.func_186238_c(minecraftserver, new ResourceLocation(this.field_184420_a));
         return true;
      } else {
         return false;
      }
   }

   public boolean func_184412_n() {
      if(this.field_184427_l == TileEntityStructure.Mode.LOAD && !this.field_145850_b.field_72995_K) {
         BlockPos blockpos = this.func_174877_v().func_177971_a(this.field_184423_h);
         WorldServer worldserver = (WorldServer)this.field_145850_b;
         MinecraftServer minecraftserver = this.field_145850_b.func_73046_m();
         TemplateManager templatemanager = worldserver.func_184163_y();
         Template template = templatemanager.func_186237_a(minecraftserver, new ResourceLocation(this.field_184420_a));
         if(!StringUtils.func_151246_b(template.func_186261_b())) {
            this.field_184421_f = template.func_186261_b();
         }

         if(!this.field_184424_i.equals(template.func_186259_a())) {
            this.field_184424_i = template.func_186259_a();
            return false;
         } else {
            BlockPos blockpos1 = template.func_186257_a(this.field_184426_k);

            for(Entity entity : this.field_145850_b.func_72839_b((Entity)null, new AxisAlignedBB(blockpos, blockpos1.func_177971_a(blockpos).func_177982_a(-1, -1, -1)))) {
               this.field_145850_b.func_72973_f(entity);
            }

            PlacementSettings placementsettings = (new PlacementSettings()).func_186214_a(this.field_184425_j).func_186220_a(this.field_184426_k).func_186222_a(this.field_184428_m).func_186218_a((ChunkCoordIntPair)null).func_186225_a((Block)null).func_186226_b(false);
            template.func_186260_a(this.field_145850_b, blockpos, placementsettings);
            return true;
         }
      } else {
         return false;
      }
   }

   public static enum Mode implements IStringSerializable {
      SAVE("save", 0),
      LOAD("load", 1),
      CORNER("corner", 2),
      DATA("data", 3);

      private static final TileEntityStructure.Mode[] field_185115_e = new TileEntityStructure.Mode[values().length];
      private final String field_185116_f;
      private final int field_185117_g;

      private Mode(String p_i47027_3_, int p_i47027_4_) {
         this.field_185116_f = p_i47027_3_;
         this.field_185117_g = p_i47027_4_;
      }

      public String func_176610_l() {
         return this.field_185116_f;
      }

      public int func_185110_a() {
         return this.field_185117_g;
      }

      public static TileEntityStructure.Mode func_185108_a(int p_185108_0_) {
         if(p_185108_0_ < 0 || p_185108_0_ >= field_185115_e.length) {
            p_185108_0_ = 0;
         }

         return field_185115_e[p_185108_0_];
      }

      static {
         for(TileEntityStructure.Mode tileentitystructure$mode : values()) {
            field_185115_e[tileentitystructure$mode.func_185110_a()] = tileentitystructure$mode;
         }

      }
   }
}
