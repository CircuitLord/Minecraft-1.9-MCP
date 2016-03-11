package net.minecraft.entity.passive;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.storage.loot.LootTableList;

public enum HorseArmorType {
   HORSE("horse", "horse_white", SoundEvents.field_187696_ck, SoundEvents.field_187717_cr, SoundEvents.field_187708_co, LootTableList.field_186396_D),
   DONKEY("donkey", "donkey", SoundEvents.field_187580_av, SoundEvents.field_187588_az, SoundEvents.field_187586_ay, LootTableList.field_186396_D),
   MULE("mule", "mule", SoundEvents.field_187786_du, SoundEvents.field_187790_dw, SoundEvents.field_187788_dv, LootTableList.field_186396_D),
   ZOMBIE("zombiehorse", "horse_zombie", SoundEvents.field_187931_he, SoundEvents.field_187933_hg, SoundEvents.field_187932_hf, LootTableList.field_186397_E),
   SKELETON("skeletonhorse", "horse_skeleton", SoundEvents.field_187858_fe, SoundEvents.field_187862_fg, SoundEvents.field_187860_ff, LootTableList.field_186398_F);

   private final TextComponentTranslation field_188609_f;
   private final ResourceLocation field_188610_g;
   private final SoundEvent field_188611_h;
   private final SoundEvent field_188612_i;
   private final SoundEvent field_188613_j;
   private ResourceLocation field_188614_k;

   private HorseArmorType(String p_i46798_3_, String p_i46798_4_, SoundEvent p_i46798_5_, SoundEvent p_i46798_6_, SoundEvent p_i46798_7_, ResourceLocation p_i46798_8_) {
      this.field_188614_k = p_i46798_8_;
      this.field_188609_f = new TextComponentTranslation("entity." + p_i46798_3_ + ".name", new Object[0]);
      this.field_188610_g = new ResourceLocation("textures/entity/horse/" + p_i46798_4_ + ".png");
      this.field_188611_h = p_i46798_6_;
      this.field_188612_i = p_i46798_5_;
      this.field_188613_j = p_i46798_7_;
   }

   public SoundEvent func_188599_a() {
      return this.field_188612_i;
   }

   public SoundEvent func_188597_b() {
      return this.field_188611_h;
   }

   public SoundEvent func_188593_c() {
      return this.field_188613_j;
   }

   public TextComponentTranslation func_188596_d() {
      return this.field_188609_f;
   }

   public ResourceLocation func_188592_e() {
      return this.field_188610_g;
   }

   public boolean func_188600_f() {
      return this == DONKEY || this == MULE;
   }

   public boolean func_188601_g() {
      return this == DONKEY || this == MULE;
   }

   public boolean func_188602_h() {
      return this == ZOMBIE || this == SKELETON;
   }

   public boolean func_188590_i() {
      return !this.func_188602_h() && this != MULE;
   }

   public boolean func_188603_j() {
      return this == HORSE;
   }

   public int func_188595_k() {
      return this.ordinal();
   }

   public static HorseArmorType func_188591_a(int p_188591_0_) {
      return values()[p_188591_0_];
   }

   public ResourceLocation func_188598_l() {
      return this.field_188614_k;
   }
}
