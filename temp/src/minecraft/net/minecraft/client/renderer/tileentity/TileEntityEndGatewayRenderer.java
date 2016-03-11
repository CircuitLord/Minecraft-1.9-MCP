package net.minecraft.client.renderer.tileentity;

import java.nio.FloatBuffer;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityBeaconRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.tileentity.TileEntityEndGateway;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class TileEntityEndGatewayRenderer extends TileEntitySpecialRenderer<TileEntityEndGateway> {
   private static final ResourceLocation field_188197_d = new ResourceLocation("textures/environment/end_sky.png");
   private static final ResourceLocation field_188198_e = new ResourceLocation("textures/entity/end_portal.png");
   private static final ResourceLocation field_188199_f = new ResourceLocation("textures/entity/end_gateway_beam.png");
   private static final Random field_188200_g = new Random(31100L);
   private static final FloatBuffer field_188201_h = GLAllocation.func_74529_h(16);
   private static final FloatBuffer field_188202_i = GLAllocation.func_74529_h(16);
   FloatBuffer field_188196_a = GLAllocation.func_74529_h(16);

   public void func_180535_a(TileEntityEndGateway p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
      GlStateManager.func_179106_n();
      if(p_180535_1_.func_184309_b() || p_180535_1_.func_184310_d()) {
         GlStateManager.func_179092_a(516, 0.1F);
         this.func_147499_a(field_188199_f);
         float f = p_180535_1_.func_184309_b()?p_180535_1_.func_184302_e():p_180535_1_.func_184305_g();
         double d0 = p_180535_1_.func_184309_b()?256.0D - p_180535_4_:25.0D;
         f = MathHelper.func_76126_a(f * 3.1415927F);
         int j = MathHelper.func_76128_c((double)f * d0);
         float[] afloat = EntitySheep.func_175513_a(p_180535_1_.func_184309_b()?EnumDyeColor.MAGENTA:EnumDyeColor.YELLOW);
         TileEntityBeaconRenderer.func_188205_a(p_180535_2_, p_180535_4_, p_180535_6_, (double)p_180535_8_, (double)f, (double)p_180535_1_.func_145831_w().func_82737_E(), 0, j, afloat, 0.15D, 0.175D);
         TileEntityBeaconRenderer.func_188205_a(p_180535_2_, p_180535_4_, p_180535_6_, (double)p_180535_8_, (double)f, (double)p_180535_1_.func_145831_w().func_82737_E(), 0, -j, afloat, 0.15D, 0.175D);
      }

      GlStateManager.func_179140_f();
      field_188200_g.setSeed(31100L);
      GlStateManager.func_179111_a(2982, field_188201_h);
      GlStateManager.func_179111_a(2983, field_188202_i);
      double d1 = p_180535_2_ * p_180535_2_ + p_180535_4_ * p_180535_4_ + p_180535_6_ * p_180535_6_;
      int i;
      if(d1 > 36864.0D) {
         i = 2;
      } else if(d1 > 25600.0D) {
         i = 4;
      } else if(d1 > 16384.0D) {
         i = 6;
      } else if(d1 > 9216.0D) {
         i = 8;
      } else if(d1 > 4096.0D) {
         i = 10;
      } else if(d1 > 1024.0D) {
         i = 12;
      } else if(d1 > 576.0D) {
         i = 14;
      } else if(d1 > 256.0D) {
         i = 15;
      } else {
         i = 16;
      }

      for(int k = 0; k < i; ++k) {
         GlStateManager.func_179094_E();
         float f5 = 2.0F / (float)(18 - k);
         if(k == 0) {
            this.func_147499_a(field_188197_d);
            f5 = 0.15F;
            GlStateManager.func_179147_l();
            GlStateManager.func_187401_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
         }

         if(k >= 1) {
            this.func_147499_a(field_188198_e);
         }

         if(k == 1) {
            GlStateManager.func_179147_l();
            GlStateManager.func_187401_a(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
         }

         GlStateManager.func_179149_a(GlStateManager.TexGen.S, 9216);
         GlStateManager.func_179149_a(GlStateManager.TexGen.T, 9216);
         GlStateManager.func_179149_a(GlStateManager.TexGen.R, 9216);
         GlStateManager.func_179105_a(GlStateManager.TexGen.S, 9474, this.func_188193_a(1.0F, 0.0F, 0.0F, 0.0F));
         GlStateManager.func_179105_a(GlStateManager.TexGen.T, 9474, this.func_188193_a(0.0F, 1.0F, 0.0F, 0.0F));
         GlStateManager.func_179105_a(GlStateManager.TexGen.R, 9474, this.func_188193_a(0.0F, 0.0F, 1.0F, 0.0F));
         GlStateManager.func_179087_a(GlStateManager.TexGen.S);
         GlStateManager.func_179087_a(GlStateManager.TexGen.T);
         GlStateManager.func_179087_a(GlStateManager.TexGen.R);
         GlStateManager.func_179121_F();
         GlStateManager.func_179128_n(5890);
         GlStateManager.func_179094_E();
         GlStateManager.func_179096_D();
         GlStateManager.func_179109_b(0.5F, 0.5F, 0.0F);
         GlStateManager.func_179152_a(0.5F, 0.5F, 1.0F);
         float f1 = (float)(k + 1);
         GlStateManager.func_179109_b(17.0F / f1, (2.0F + f1 / 1.5F) * ((float)Minecraft.func_71386_F() % 800000.0F / 800000.0F), 0.0F);
         GlStateManager.func_179114_b((f1 * f1 * 4321.0F + f1 * 9.0F) * 2.0F, 0.0F, 0.0F, 1.0F);
         GlStateManager.func_179152_a(4.5F - f1 / 4.0F, 4.5F - f1 / 4.0F, 1.0F);
         GlStateManager.func_179110_a(field_188202_i);
         GlStateManager.func_179110_a(field_188201_h);
         Tessellator tessellator = Tessellator.func_178181_a();
         VertexBuffer vertexbuffer = tessellator.func_178180_c();
         vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181706_f);
         float f2 = (field_188200_g.nextFloat() * 0.5F + 0.1F) * f5;
         float f3 = (field_188200_g.nextFloat() * 0.5F + 0.4F) * f5;
         float f4 = (field_188200_g.nextFloat() * 0.5F + 0.5F) * f5;
         if(k == 0) {
            f2 = f3 = f4 = 1.0F * f5;
         }

         if(p_180535_1_.func_184313_a(EnumFacing.SOUTH)) {
            vertexbuffer.func_181662_b(p_180535_2_, p_180535_4_, p_180535_6_ + 1.0D).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
            vertexbuffer.func_181662_b(p_180535_2_ + 1.0D, p_180535_4_, p_180535_6_ + 1.0D).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
            vertexbuffer.func_181662_b(p_180535_2_ + 1.0D, p_180535_4_ + 1.0D, p_180535_6_ + 1.0D).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
            vertexbuffer.func_181662_b(p_180535_2_, p_180535_4_ + 1.0D, p_180535_6_ + 1.0D).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
         }

         if(p_180535_1_.func_184313_a(EnumFacing.NORTH)) {
            vertexbuffer.func_181662_b(p_180535_2_, p_180535_4_ + 1.0D, p_180535_6_).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
            vertexbuffer.func_181662_b(p_180535_2_ + 1.0D, p_180535_4_ + 1.0D, p_180535_6_).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
            vertexbuffer.func_181662_b(p_180535_2_ + 1.0D, p_180535_4_, p_180535_6_).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
            vertexbuffer.func_181662_b(p_180535_2_, p_180535_4_, p_180535_6_).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
         }

         if(p_180535_1_.func_184313_a(EnumFacing.EAST)) {
            vertexbuffer.func_181662_b(p_180535_2_ + 1.0D, p_180535_4_ + 1.0D, p_180535_6_).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
            vertexbuffer.func_181662_b(p_180535_2_ + 1.0D, p_180535_4_ + 1.0D, p_180535_6_ + 1.0D).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
            vertexbuffer.func_181662_b(p_180535_2_ + 1.0D, p_180535_4_, p_180535_6_ + 1.0D).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
            vertexbuffer.func_181662_b(p_180535_2_ + 1.0D, p_180535_4_, p_180535_6_).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
         }

         if(p_180535_1_.func_184313_a(EnumFacing.WEST)) {
            vertexbuffer.func_181662_b(p_180535_2_, p_180535_4_, p_180535_6_).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
            vertexbuffer.func_181662_b(p_180535_2_, p_180535_4_, p_180535_6_ + 1.0D).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
            vertexbuffer.func_181662_b(p_180535_2_, p_180535_4_ + 1.0D, p_180535_6_ + 1.0D).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
            vertexbuffer.func_181662_b(p_180535_2_, p_180535_4_ + 1.0D, p_180535_6_).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
         }

         if(p_180535_1_.func_184313_a(EnumFacing.DOWN)) {
            vertexbuffer.func_181662_b(p_180535_2_, p_180535_4_, p_180535_6_).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
            vertexbuffer.func_181662_b(p_180535_2_ + 1.0D, p_180535_4_, p_180535_6_).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
            vertexbuffer.func_181662_b(p_180535_2_ + 1.0D, p_180535_4_, p_180535_6_ + 1.0D).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
            vertexbuffer.func_181662_b(p_180535_2_, p_180535_4_, p_180535_6_ + 1.0D).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
         }

         if(p_180535_1_.func_184313_a(EnumFacing.UP)) {
            vertexbuffer.func_181662_b(p_180535_2_, p_180535_4_ + 1.0D, p_180535_6_ + 1.0D).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
            vertexbuffer.func_181662_b(p_180535_2_ + 1.0D, p_180535_4_ + 1.0D, p_180535_6_ + 1.0D).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
            vertexbuffer.func_181662_b(p_180535_2_ + 1.0D, p_180535_4_ + 1.0D, p_180535_6_).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
            vertexbuffer.func_181662_b(p_180535_2_, p_180535_4_ + 1.0D, p_180535_6_).func_181666_a(f2, f3, f4, 1.0F).func_181675_d();
         }

         tessellator.func_78381_a();
         GlStateManager.func_179121_F();
         GlStateManager.func_179128_n(5888);
         this.func_147499_a(field_188197_d);
      }

      GlStateManager.func_179084_k();
      GlStateManager.func_179100_b(GlStateManager.TexGen.S);
      GlStateManager.func_179100_b(GlStateManager.TexGen.T);
      GlStateManager.func_179100_b(GlStateManager.TexGen.R);
      GlStateManager.func_179145_e();
      GlStateManager.func_179127_m();
   }

   private FloatBuffer func_188193_a(float p_188193_1_, float p_188193_2_, float p_188193_3_, float p_188193_4_) {
      this.field_188196_a.clear();
      this.field_188196_a.put(p_188193_1_).put(p_188193_2_).put(p_188193_3_).put(p_188193_4_);
      this.field_188196_a.flip();
      return this.field_188196_a;
   }

   public boolean func_188185_a(TileEntityEndGateway p_188185_1_) {
      return p_188185_1_.func_184309_b() || p_188185_1_.func_184310_d();
   }
}
