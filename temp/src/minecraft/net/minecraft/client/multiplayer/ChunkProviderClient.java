package net.minecraft.client.multiplayer;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.util.LongHashMap;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.EmptyChunk;
import net.minecraft.world.chunk.IChunkProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChunkProviderClient implements IChunkProvider {
   private static final Logger field_147436_a = LogManager.getLogger();
   private final Chunk field_73238_a;
   private final LongHashMap<Chunk> field_73236_b = new LongHashMap();
   private final List<Chunk> field_73237_c = Lists.<Chunk>newArrayList();
   private final World field_73235_d;

   public ChunkProviderClient(World p_i1184_1_) {
      this.field_73238_a = new EmptyChunk(p_i1184_1_, 0, 0);
      this.field_73235_d = p_i1184_1_;
   }

   public void func_73234_b(int p_73234_1_, int p_73234_2_) {
      Chunk chunk = this.func_186025_d(p_73234_1_, p_73234_2_);
      if(!chunk.func_76621_g()) {
         chunk.func_76623_d();
      }

      this.field_73236_b.func_76159_d(ChunkCoordIntPair.func_77272_a(p_73234_1_, p_73234_2_));
      this.field_73237_c.remove(chunk);
   }

   public Chunk func_186026_b(int p_186026_1_, int p_186026_2_) {
      return (Chunk)this.field_73236_b.func_76164_a(ChunkCoordIntPair.func_77272_a(p_186026_1_, p_186026_2_));
   }

   public Chunk func_73158_c(int p_73158_1_, int p_73158_2_) {
      Chunk chunk = new Chunk(this.field_73235_d, p_73158_1_, p_73158_2_);
      this.field_73236_b.func_76163_a(ChunkCoordIntPair.func_77272_a(p_73158_1_, p_73158_2_), chunk);
      this.field_73237_c.add(chunk);
      chunk.func_177417_c(true);
      return chunk;
   }

   public Chunk func_186025_d(int p_186025_1_, int p_186025_2_) {
      return (Chunk)Objects.firstNonNull(this.func_186026_b(p_186025_1_, p_186025_2_), this.field_73238_a);
   }

   public boolean func_73156_b() {
      long i = System.currentTimeMillis();

      for(Chunk chunk : this.field_73237_c) {
         chunk.func_150804_b(System.currentTimeMillis() - i > 5L);
      }

      if(System.currentTimeMillis() - i > 100L) {
         field_147436_a.info("Warning: Clientside chunk ticking took {} ms", new Object[]{Long.valueOf(System.currentTimeMillis() - i)});
      }

      return false;
   }

   public String func_73148_d() {
      return "MultiplayerChunkCache: " + this.field_73236_b.func_76162_a() + ", " + this.field_73237_c.size();
   }
}
