package net.minecraft.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityPistonRenderer extends TileEntitySpecialRenderer<TileEntityPiston> {
   private final BlockRendererDispatcher field_178462_c = Minecraft.func_71410_x().func_175602_ab();

   public void func_180535_a(TileEntityPiston p_180535_1_, double p_180535_2_, double p_180535_4_, double p_180535_6_, float p_180535_8_, int p_180535_9_) {
      BlockPos blockpos = p_180535_1_.func_174877_v();
      IBlockState iblockstate = p_180535_1_.func_174927_b();
      Block block = iblockstate.func_177230_c();
      if(iblockstate.func_185904_a() != Material.field_151579_a && p_180535_1_.func_145860_a(p_180535_8_) < 1.0F) {
         Tessellator tessellator = Tessellator.func_178181_a();
         VertexBuffer vertexbuffer = tessellator.func_178180_c();
         this.func_147499_a(TextureMap.field_110575_b);
         RenderHelper.func_74518_a();
         GlStateManager.func_187401_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
         GlStateManager.func_179147_l();
         GlStateManager.func_179129_p();
         if(Minecraft.func_71379_u()) {
            GlStateManager.func_179103_j(7425);
         } else {
            GlStateManager.func_179103_j(7424);
         }

         vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_176600_a);
         vertexbuffer.func_178969_c((double)((float)p_180535_2_ - (float)blockpos.func_177958_n() + p_180535_1_.func_174929_b(p_180535_8_)), (double)((float)p_180535_4_ - (float)blockpos.func_177956_o() + p_180535_1_.func_174928_c(p_180535_8_)), (double)((float)p_180535_6_ - (float)blockpos.func_177952_p() + p_180535_1_.func_174926_d(p_180535_8_)));
         World world = this.func_178459_a();
         if(block == Blocks.field_150332_K && p_180535_1_.func_145860_a(p_180535_8_) < 0.5F) {
            iblockstate = iblockstate.func_177226_a(BlockPistonExtension.field_176327_M, Boolean.valueOf(true));
            this.func_188186_a(blockpos, iblockstate, vertexbuffer, world, true);
         } else if(p_180535_1_.func_145867_d() && !p_180535_1_.func_145868_b()) {
            BlockPistonExtension.EnumPistonType blockpistonextension$enumpistontype = block == Blocks.field_150320_F?BlockPistonExtension.EnumPistonType.STICKY:BlockPistonExtension.EnumPistonType.DEFAULT;
            IBlockState iblockstate1 = Blocks.field_150332_K.func_176223_P().func_177226_a(BlockPistonExtension.field_176325_b, blockpistonextension$enumpistontype).func_177226_a(BlockPistonExtension.field_176387_N, iblockstate.func_177229_b(BlockPistonBase.field_176387_N));
            iblockstate1 = iblockstate1.func_177226_a(BlockPistonExtension.field_176327_M, Boolean.valueOf(p_180535_1_.func_145860_a(p_180535_8_) >= 0.5F));
            this.func_188186_a(blockpos, iblockstate1, vertexbuffer, world, true);
            vertexbuffer.func_178969_c((double)((float)p_180535_2_ - (float)blockpos.func_177958_n()), (double)((float)p_180535_4_ - (float)blockpos.func_177956_o()), (double)((float)p_180535_6_ - (float)blockpos.func_177952_p()));
            iblockstate = iblockstate.func_177226_a(BlockPistonBase.field_176320_b, Boolean.valueOf(true));
            this.func_188186_a(blockpos, iblockstate, vertexbuffer, world, true);
         } else {
            this.func_188186_a(blockpos, iblockstate, vertexbuffer, world, false);
         }

         vertexbuffer.func_178969_c(0.0D, 0.0D, 0.0D);
         tessellator.func_78381_a();
         RenderHelper.func_74519_b();
      }
   }

   private boolean func_188186_a(BlockPos p_188186_1_, IBlockState p_188186_2_, VertexBuffer p_188186_3_, World p_188186_4_, boolean p_188186_5_) {
      return this.field_178462_c.func_175019_b().func_178267_a(p_188186_4_, this.field_178462_c.func_184389_a(p_188186_2_), p_188186_2_, p_188186_1_, p_188186_3_, p_188186_5_);
   }
}
