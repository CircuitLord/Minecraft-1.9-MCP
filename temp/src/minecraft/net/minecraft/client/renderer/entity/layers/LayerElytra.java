package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelElytra;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class LayerElytra implements LayerRenderer<AbstractClientPlayer> {
   private static final ResourceLocation field_188355_a = new ResourceLocation("textures/entity/elytra.png");
   private final RenderPlayer field_188356_b;
   private final ModelElytra field_188357_c = new ModelElytra();

   public LayerElytra(RenderPlayer p_i46546_1_) {
      this.field_188356_b = p_i46546_1_;
   }

   public void func_177141_a(AbstractClientPlayer p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
      ItemStack itemstack = p_177141_1_.func_184582_a(EntityEquipmentSlot.CHEST);
      if(itemstack != null && itemstack.func_77973_b() == Items.field_185160_cR) {
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         if(p_177141_1_.func_184833_s() && p_177141_1_.func_184834_t() != null) {
            this.field_188356_b.func_110776_a(p_177141_1_.func_184834_t());
         } else if(p_177141_1_.func_152122_n() && p_177141_1_.func_110303_q() != null && p_177141_1_.func_175148_a(EnumPlayerModelParts.CAPE)) {
            this.field_188356_b.func_110776_a(p_177141_1_.func_110303_q());
         } else {
            this.field_188356_b.func_110776_a(field_188355_a);
         }

         GlStateManager.func_179094_E();
         GlStateManager.func_179109_b(0.0F, 0.0F, 0.125F);
         this.field_188357_c.func_78087_a(p_177141_2_, p_177141_3_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_, p_177141_1_);
         this.field_188357_c.func_78088_a(p_177141_1_, p_177141_2_, p_177141_3_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
         if(itemstack.func_77948_v()) {
            LayerArmorBase.func_188364_a(this.field_188356_b, p_177141_1_, this.field_188357_c, p_177141_2_, p_177141_3_, p_177141_4_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
         }

         GlStateManager.func_179121_F();
      }
   }

   public boolean func_177142_b() {
      return false;
   }
}
