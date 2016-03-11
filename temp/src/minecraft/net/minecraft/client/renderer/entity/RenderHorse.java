package net.minecraft.client.renderer.entity;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.util.ResourceLocation;

public class RenderHorse extends RenderLiving<EntityHorse> {
   private static final Map<String, ResourceLocation> field_110852_a = Maps.<String, ResourceLocation>newHashMap();

   public RenderHorse(RenderManager p_i46170_1_, ModelHorse p_i46170_2_, float p_i46170_3_) {
      super(p_i46170_1_, p_i46170_2_, p_i46170_3_);
   }

   protected void func_77041_b(EntityHorse p_77041_1_, float p_77041_2_) {
      float f = 1.0F;
      HorseArmorType horsearmortype = p_77041_1_.func_184781_cZ();
      if(horsearmortype == HorseArmorType.DONKEY) {
         f *= 0.87F;
      } else if(horsearmortype == HorseArmorType.MULE) {
         f *= 0.92F;
      }

      GlStateManager.func_179152_a(f, f, f);
      super.func_77041_b(p_77041_1_, p_77041_2_);
   }

   protected ResourceLocation func_110775_a(EntityHorse p_110775_1_) {
      return !p_110775_1_.func_110239_cn()?p_110775_1_.func_184781_cZ().func_188592_e():this.func_188328_b(p_110775_1_);
   }

   private ResourceLocation func_188328_b(EntityHorse p_188328_1_) {
      String s = p_188328_1_.func_110264_co();
      if(!p_188328_1_.func_175507_cI()) {
         return null;
      } else {
         ResourceLocation resourcelocation = (ResourceLocation)field_110852_a.get(s);
         if(resourcelocation == null) {
            resourcelocation = new ResourceLocation(s);
            Minecraft.func_71410_x().func_110434_K().func_110579_a(resourcelocation, new LayeredTexture(p_188328_1_.func_110212_cp()));
            field_110852_a.put(s, resourcelocation);
         }

         return resourcelocation;
      }
   }
}
