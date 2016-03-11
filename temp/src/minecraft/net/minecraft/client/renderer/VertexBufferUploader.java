package net.minecraft.client.renderer;

import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.WorldVertexBufferUploader;

public class VertexBufferUploader extends WorldVertexBufferUploader {
   private net.minecraft.client.renderer.vertex.VertexBuffer field_178179_a = null;

   public void func_181679_a(VertexBuffer p_181679_1_) {
      p_181679_1_.func_178965_a();
      this.field_178179_a.func_181722_a(p_181679_1_.func_178966_f());
   }

   public void func_178178_a(net.minecraft.client.renderer.vertex.VertexBuffer p_178178_1_) {
      this.field_178179_a = p_178178_1_;
   }
}
