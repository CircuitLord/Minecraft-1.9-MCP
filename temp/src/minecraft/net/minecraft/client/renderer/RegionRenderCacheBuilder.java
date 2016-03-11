package net.minecraft.client.renderer;

import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.util.BlockRenderLayer;

public class RegionRenderCacheBuilder {
   private final VertexBuffer[] field_179040_a = new VertexBuffer[BlockRenderLayer.values().length];

   public RegionRenderCacheBuilder() {
      this.field_179040_a[BlockRenderLayer.SOLID.ordinal()] = new VertexBuffer(2097152);
      this.field_179040_a[BlockRenderLayer.CUTOUT.ordinal()] = new VertexBuffer(131072);
      this.field_179040_a[BlockRenderLayer.CUTOUT_MIPPED.ordinal()] = new VertexBuffer(131072);
      this.field_179040_a[BlockRenderLayer.TRANSLUCENT.ordinal()] = new VertexBuffer(262144);
   }

   public VertexBuffer func_179038_a(BlockRenderLayer p_179038_1_) {
      return this.field_179040_a[p_179038_1_.ordinal()];
   }

   public VertexBuffer func_179039_a(int p_179039_1_) {
      return this.field_179040_a[p_179039_1_];
   }
}
