package net.minecraft.world.gen.structure;

import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.ChunkProviderEnd;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureEndCityPieces;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenEndCity extends MapGenStructure {
   private int field_186131_a = 20;
   private int field_186132_b = 11;
   private final ChunkProviderEnd field_186133_d;

   public MapGenEndCity(ChunkProviderEnd p_i46665_1_) {
      this.field_186133_d = p_i46665_1_;
   }

   public String func_143025_a() {
      return "EndCity";
   }

   protected boolean func_75047_a(int p_75047_1_, int p_75047_2_) {
      int i = p_75047_1_;
      int j = p_75047_2_;
      if(p_75047_1_ < 0) {
         p_75047_1_ -= this.field_186131_a - 1;
      }

      if(p_75047_2_ < 0) {
         p_75047_2_ -= this.field_186131_a - 1;
      }

      int k = p_75047_1_ / this.field_186131_a;
      int l = p_75047_2_ / this.field_186131_a;
      Random random = this.field_75039_c.func_72843_D(k, l, 10387313);
      k = k * this.field_186131_a;
      l = l * this.field_186131_a;
      k = k + (random.nextInt(this.field_186131_a - this.field_186132_b) + random.nextInt(this.field_186131_a - this.field_186132_b)) / 2;
      l = l + (random.nextInt(this.field_186131_a - this.field_186132_b) + random.nextInt(this.field_186131_a - this.field_186132_b)) / 2;
      return i == k && j == l && this.field_186133_d.func_185961_c(i, j);
   }

   protected StructureStart func_75049_b(int p_75049_1_, int p_75049_2_) {
      return new MapGenEndCity.Start(this.field_75039_c, this.field_186133_d, this.field_75038_b, p_75049_1_, p_75049_2_);
   }

   public static class Start extends StructureStart {
      private boolean field_186163_c;

      public Start() {
      }

      public Start(World p_i46760_1_, ChunkProviderEnd p_i46760_2_, Random p_i46760_3_, int p_i46760_4_, int p_i46760_5_) {
         super(p_i46760_4_, p_i46760_5_);
         this.func_186162_a(p_i46760_1_, p_i46760_2_, p_i46760_3_, p_i46760_4_, p_i46760_5_);
      }

      private void func_186162_a(World p_186162_1_, ChunkProviderEnd p_186162_2_, Random p_186162_3_, int p_186162_4_, int p_186162_5_) {
         Rotation rotation = Rotation.values()[p_186162_3_.nextInt(Rotation.values().length)];
         ChunkPrimer chunkprimer = new ChunkPrimer();
         p_186162_2_.func_180518_a(p_186162_4_, p_186162_5_, chunkprimer);
         int i = 5;
         int j = 5;
         if(rotation == Rotation.CLOCKWISE_90) {
            i = -5;
         } else if(rotation == Rotation.CLOCKWISE_180) {
            i = -5;
            j = -5;
         } else if(rotation == Rotation.COUNTERCLOCKWISE_90) {
            j = -5;
         }

         int k = chunkprimer.func_186138_a(7, 7);
         int l = chunkprimer.func_186138_a(7, 7 + j);
         int i1 = chunkprimer.func_186138_a(7 + i, 7);
         int j1 = chunkprimer.func_186138_a(7 + i, 7 + j);
         int k1 = Math.min(Math.min(k, l), Math.min(i1, j1));
         if(k1 < 60) {
            this.field_186163_c = false;
         } else {
            BlockPos blockpos = new BlockPos(p_186162_4_ * 16 + 8, k1, p_186162_5_ * 16 + 8);
            StructureEndCityPieces.func_186190_a(blockpos, rotation, this.field_75075_a, p_186162_3_);
            this.func_75072_c();
            this.field_186163_c = true;
         }
      }

      public boolean func_75069_d() {
         return this.field_186163_c;
      }

      public void func_143022_a(NBTTagCompound p_143022_1_) {
         super.func_143022_a(p_143022_1_);
      }

      public void func_143017_b(NBTTagCompound p_143017_1_) {
         super.func_143017_b(p_143017_1_);
      }
   }
}
