package net.minecraft.world.gen;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChunkProviderServer implements IChunkProvider {
   private static final Logger field_147417_b = LogManager.getLogger();
   private final Set<Long> field_73248_b = Collections.<Long>newSetFromMap(new ConcurrentHashMap());
   private final IChunkGenerator field_186029_c;
   private final IChunkLoader field_73247_e;
   private final LongHashMap<Chunk> field_73244_f = new LongHashMap();
   private final List<Chunk> field_73245_g = Lists.<Chunk>newArrayList();
   private final WorldServer field_73251_h;

   public ChunkProviderServer(WorldServer p_i46838_1_, IChunkLoader p_i46838_2_, IChunkGenerator p_i46838_3_) {
      this.field_73251_h = p_i46838_1_;
      this.field_73247_e = p_i46838_2_;
      this.field_186029_c = p_i46838_3_;
   }

   public List<Chunk> func_152380_a() {
      return this.field_73245_g;
   }

   public void func_73241_b(int p_73241_1_, int p_73241_2_) {
      if(this.field_73251_h.field_73011_w.func_186056_c(p_73241_1_, p_73241_2_)) {
         this.field_73248_b.add(Long.valueOf(ChunkCoordIntPair.func_77272_a(p_73241_1_, p_73241_2_)));
      }

   }

   public void func_73240_a() {
      for(Chunk chunk : this.field_73245_g) {
         this.func_73241_b(chunk.field_76635_g, chunk.field_76647_h);
      }

   }

   public Chunk func_186026_b(int p_186026_1_, int p_186026_2_) {
      long i = ChunkCoordIntPair.func_77272_a(p_186026_1_, p_186026_2_);
      Chunk chunk = (Chunk)this.field_73244_f.func_76164_a(i);
      this.field_73248_b.remove(Long.valueOf(i));
      return chunk;
   }

   public Chunk func_186028_c(int p_186028_1_, int p_186028_2_) {
      Chunk chunk = this.func_186026_b(p_186028_1_, p_186028_2_);
      if(chunk == null) {
         chunk = this.func_73239_e(p_186028_1_, p_186028_2_);
         if(chunk != null) {
            this.field_73244_f.func_76163_a(ChunkCoordIntPair.func_77272_a(p_186028_1_, p_186028_2_), chunk);
            this.field_73245_g.add(chunk);
            chunk.func_76631_c();
            chunk.func_186030_a(this, this.field_186029_c);
         }
      }

      return chunk;
   }

   public Chunk func_186025_d(int p_186025_1_, int p_186025_2_) {
      Chunk chunk = this.func_186028_c(p_186025_1_, p_186025_2_);
      if(chunk == null) {
         long i = ChunkCoordIntPair.func_77272_a(p_186025_1_, p_186025_2_);
         chunk = this.func_73239_e(p_186025_1_, p_186025_2_);
         if(chunk == null) {
            try {
               chunk = this.field_186029_c.func_185932_a(p_186025_1_, p_186025_2_);
            } catch (Throwable throwable) {
               CrashReport crashreport = CrashReport.func_85055_a(throwable, "Exception generating new chunk");
               CrashReportCategory crashreportcategory = crashreport.func_85058_a("Chunk to be generated");
               crashreportcategory.func_71507_a("Location", String.format("%d,%d", new Object[]{Integer.valueOf(p_186025_1_), Integer.valueOf(p_186025_2_)}));
               crashreportcategory.func_71507_a("Position hash", Long.valueOf(i));
               crashreportcategory.func_71507_a("Generator", this.field_186029_c);
               throw new ReportedException(crashreport);
            }
         }

         this.field_73244_f.func_76163_a(i, chunk);
         this.field_73245_g.add(chunk);
         chunk.func_76631_c();
         chunk.func_186030_a(this, this.field_186029_c);
      }

      return chunk;
   }

   private Chunk func_73239_e(int p_73239_1_, int p_73239_2_) {
      try {
         Chunk chunk = this.field_73247_e.func_75815_a(this.field_73251_h, p_73239_1_, p_73239_2_);
         if(chunk != null) {
            chunk.func_177432_b(this.field_73251_h.func_82737_E());
            this.field_186029_c.func_180514_a(chunk, p_73239_1_, p_73239_2_);
         }

         return chunk;
      } catch (Exception exception) {
         field_147417_b.error((String)"Couldn\'t load chunk", (Throwable)exception);
         return null;
      }
   }

   private void func_73243_a(Chunk p_73243_1_) {
      try {
         this.field_73247_e.func_75819_b(this.field_73251_h, p_73243_1_);
      } catch (Exception exception) {
         field_147417_b.error((String)"Couldn\'t save entities", (Throwable)exception);
      }

   }

   private void func_73242_b(Chunk p_73242_1_) {
      try {
         p_73242_1_.func_177432_b(this.field_73251_h.func_82737_E());
         this.field_73247_e.func_75816_a(this.field_73251_h, p_73242_1_);
      } catch (IOException ioexception) {
         field_147417_b.error((String)"Couldn\'t save chunk", (Throwable)ioexception);
      } catch (MinecraftException minecraftexception) {
         field_147417_b.error((String)"Couldn\'t save chunk; already in use by another instance of Minecraft?", (Throwable)minecraftexception);
      }

   }

   public boolean func_186027_a(boolean p_186027_1_) {
      int i = 0;
      List<Chunk> list = Lists.newArrayList(this.field_73245_g);

      for(int j = 0; j < ((List)list).size(); ++j) {
         Chunk chunk = (Chunk)list.get(j);
         if(p_186027_1_) {
            this.func_73243_a(chunk);
         }

         if(chunk.func_76601_a(p_186027_1_)) {
            this.func_73242_b(chunk);
            chunk.func_177427_f(false);
            ++i;
            if(i == 24 && !p_186027_1_) {
               return false;
            }
         }
      }

      return true;
   }

   public void func_104112_b() {
      this.field_73247_e.func_75818_b();
   }

   public boolean func_73156_b() {
      if(!this.field_73251_h.field_73058_d) {
         for(int i = 0; i < 100; ++i) {
            if(!this.field_73248_b.isEmpty()) {
               Long olong = (Long)this.field_73248_b.iterator().next();
               Chunk chunk = (Chunk)this.field_73244_f.func_76164_a(olong.longValue());
               if(chunk != null) {
                  chunk.func_76623_d();
                  this.func_73242_b(chunk);
                  this.func_73243_a(chunk);
                  this.field_73244_f.func_76159_d(olong.longValue());
                  this.field_73245_g.remove(chunk);
               }

               this.field_73248_b.remove(olong);
            }
         }

         this.field_73247_e.func_75817_a();
      }

      return false;
   }

   public boolean func_73157_c() {
      return !this.field_73251_h.field_73058_d;
   }

   public String func_73148_d() {
      return "ServerChunkCache: " + this.field_73244_f.func_76162_a() + " Drop: " + this.field_73248_b.size();
   }

   public List<BiomeGenBase.SpawnListEntry> func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_) {
      return this.field_186029_c.func_177458_a(p_177458_1_, p_177458_2_);
   }

   public BlockPos func_180513_a(World p_180513_1_, String p_180513_2_, BlockPos p_180513_3_) {
      return this.field_186029_c.func_180513_a(p_180513_1_, p_180513_2_, p_180513_3_);
   }

   public int func_73152_e() {
      return this.field_73244_f.func_76162_a();
   }

   public boolean func_73149_a(int p_73149_1_, int p_73149_2_) {
      return this.field_73244_f.func_76161_b(ChunkCoordIntPair.func_77272_a(p_73149_1_, p_73149_2_));
   }
}
