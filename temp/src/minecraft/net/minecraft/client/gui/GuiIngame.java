package net.minecraft.client.gui;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiBossOverlay;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.gui.GuiOverlayDebug;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.gui.GuiSpectator;
import net.minecraft.client.gui.GuiSubtitleOverlay;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.border.WorldBorder;

public class GuiIngame extends Gui {
   private static final ResourceLocation field_110329_b = new ResourceLocation("textures/misc/vignette.png");
   private static final ResourceLocation field_110330_c = new ResourceLocation("textures/gui/widgets.png");
   private static final ResourceLocation field_110328_d = new ResourceLocation("textures/misc/pumpkinblur.png");
   private final Random field_73842_c = new Random();
   private final Minecraft field_73839_d;
   private final RenderItem field_73841_b;
   private final GuiNewChat field_73840_e;
   private int field_73837_f;
   private String field_73838_g = "";
   private int field_73845_h;
   private boolean field_73844_j;
   public float field_73843_a = 1.0F;
   private int field_92017_k;
   private ItemStack field_92016_l;
   private final GuiOverlayDebug field_175198_t;
   private final GuiSubtitleOverlay field_184049_t;
   private final GuiSpectator field_175197_u;
   private final GuiPlayerTabOverlay field_175196_v;
   private final GuiBossOverlay field_184050_w;
   private int field_175195_w;
   private String field_175201_x = "";
   private String field_175200_y = "";
   private int field_175199_z;
   private int field_175192_A;
   private int field_175193_B;
   private int field_175194_C = 0;
   private int field_175189_D = 0;
   private long field_175190_E = 0L;
   private long field_175191_F = 0L;

   public GuiIngame(Minecraft p_i46325_1_) {
      this.field_73839_d = p_i46325_1_;
      this.field_73841_b = p_i46325_1_.func_175599_af();
      this.field_175198_t = new GuiOverlayDebug(p_i46325_1_);
      this.field_175197_u = new GuiSpectator(p_i46325_1_);
      this.field_73840_e = new GuiNewChat(p_i46325_1_);
      this.field_175196_v = new GuiPlayerTabOverlay(p_i46325_1_, this);
      this.field_184050_w = new GuiBossOverlay(p_i46325_1_);
      this.field_184049_t = new GuiSubtitleOverlay(p_i46325_1_);
      this.func_175177_a();
   }

   public void func_175177_a() {
      this.field_175199_z = 10;
      this.field_175192_A = 70;
      this.field_175193_B = 20;
   }

   public void func_175180_a(float p_175180_1_) {
      ScaledResolution scaledresolution = new ScaledResolution(this.field_73839_d);
      int i = scaledresolution.func_78326_a();
      int j = scaledresolution.func_78328_b();
      FontRenderer fontrenderer = this.func_175179_f();
      this.field_73839_d.field_71460_t.func_78478_c();
      GlStateManager.func_179147_l();
      if(Minecraft.func_71375_t()) {
         this.func_180480_a(this.field_73839_d.field_71439_g.func_70013_c(p_175180_1_), scaledresolution);
      } else {
         GlStateManager.func_179126_j();
         GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
      }

      ItemStack itemstack = this.field_73839_d.field_71439_g.field_71071_by.func_70440_f(3);
      if(this.field_73839_d.field_71474_y.field_74320_O == 0 && itemstack != null && itemstack.func_77973_b() == Item.func_150898_a(Blocks.field_150423_aK)) {
         this.func_180476_e(scaledresolution);
      }

      if(!this.field_73839_d.field_71439_g.func_70644_a(MobEffects.field_76431_k)) {
         float f = this.field_73839_d.field_71439_g.field_71080_cy + (this.field_73839_d.field_71439_g.field_71086_bY - this.field_73839_d.field_71439_g.field_71080_cy) * p_175180_1_;
         if(f > 0.0F) {
            this.func_180474_b(f, scaledresolution);
         }
      }

      if(this.field_73839_d.field_71442_b.func_78747_a()) {
         this.field_175197_u.func_175264_a(scaledresolution, p_175180_1_);
      } else {
         this.func_180479_a(scaledresolution, p_175180_1_);
      }

      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_73839_d.func_110434_K().func_110577_a(field_110324_m);
      GlStateManager.func_179147_l();
      this.func_184045_a(p_175180_1_, scaledresolution);
      GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
      this.field_73839_d.field_71424_I.func_76320_a("bossHealth");
      this.field_184050_w.func_184051_a();
      this.field_73839_d.field_71424_I.func_76319_b();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_73839_d.func_110434_K().func_110577_a(field_110324_m);
      if(this.field_73839_d.field_71442_b.func_78755_b()) {
         this.func_180477_d(scaledresolution);
      }

      this.func_184047_e(scaledresolution);
      GlStateManager.func_179084_k();
      if(this.field_73839_d.field_71439_g.func_71060_bI() > 0) {
         this.field_73839_d.field_71424_I.func_76320_a("sleep");
         GlStateManager.func_179097_i();
         GlStateManager.func_179118_c();
         int j1 = this.field_73839_d.field_71439_g.func_71060_bI();
         float f1 = (float)j1 / 100.0F;
         if(f1 > 1.0F) {
            f1 = 1.0F - (float)(j1 - 100) / 10.0F;
         }

         int k = (int)(220.0F * f1) << 24 | 1052704;
         func_73734_a(0, 0, i, j, k);
         GlStateManager.func_179141_d();
         GlStateManager.func_179126_j();
         this.field_73839_d.field_71424_I.func_76319_b();
      }

      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      int k1 = i / 2 - 91;
      if(this.field_73839_d.field_71439_g.func_110317_t()) {
         this.func_175186_a(scaledresolution, k1);
      } else if(this.field_73839_d.field_71442_b.func_78763_f()) {
         this.func_175176_b(scaledresolution, k1);
      }

      if(this.field_73839_d.field_71474_y.field_92117_D && !this.field_73839_d.field_71442_b.func_78747_a()) {
         this.func_181551_a(scaledresolution);
      } else if(this.field_73839_d.field_71439_g.func_175149_v()) {
         this.field_175197_u.func_175263_a(scaledresolution);
      }

      if(this.field_73839_d.func_71355_q()) {
         this.func_175185_b(scaledresolution);
      }

      this.func_184048_a(scaledresolution);
      if(this.field_73839_d.field_71474_y.field_74330_P) {
         this.field_175198_t.func_175237_a(scaledresolution);
      }

      if(this.field_73845_h > 0) {
         this.field_73839_d.field_71424_I.func_76320_a("overlayMessage");
         float f2 = (float)this.field_73845_h - p_175180_1_;
         int l1 = (int)(f2 * 255.0F / 20.0F);
         if(l1 > 255) {
            l1 = 255;
         }

         if(l1 > 8) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b((float)(i / 2), (float)(j - 68), 0.0F);
            GlStateManager.func_179147_l();
            GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            int l = 16777215;
            if(this.field_73844_j) {
               l = MathHelper.func_181758_c(f2 / 50.0F, 0.7F, 0.6F) & 16777215;
            }

            fontrenderer.func_78276_b(this.field_73838_g, -fontrenderer.func_78256_a(this.field_73838_g) / 2, -4, l + (l1 << 24 & -16777216));
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
         }

         this.field_73839_d.field_71424_I.func_76319_b();
      }

      this.field_184049_t.func_184068_a(scaledresolution);
      if(this.field_175195_w > 0) {
         this.field_73839_d.field_71424_I.func_76320_a("titleAndSubtitle");
         float f3 = (float)this.field_175195_w - p_175180_1_;
         int i2 = 255;
         if(this.field_175195_w > this.field_175193_B + this.field_175192_A) {
            float f4 = (float)(this.field_175199_z + this.field_175192_A + this.field_175193_B) - f3;
            i2 = (int)(f4 * 255.0F / (float)this.field_175199_z);
         }

         if(this.field_175195_w <= this.field_175193_B) {
            i2 = (int)(f3 * 255.0F / (float)this.field_175193_B);
         }

         i2 = MathHelper.func_76125_a(i2, 0, 255);
         if(i2 > 8) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b((float)(i / 2), (float)(j / 2), 0.0F);
            GlStateManager.func_179147_l();
            GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.func_179094_E();
            GlStateManager.func_179152_a(4.0F, 4.0F, 4.0F);
            int j2 = i2 << 24 & -16777216;
            fontrenderer.func_175065_a(this.field_175201_x, (float)(-fontrenderer.func_78256_a(this.field_175201_x) / 2), -10.0F, 16777215 | j2, true);
            GlStateManager.func_179121_F();
            GlStateManager.func_179094_E();
            GlStateManager.func_179152_a(2.0F, 2.0F, 2.0F);
            fontrenderer.func_175065_a(this.field_175200_y, (float)(-fontrenderer.func_78256_a(this.field_175200_y) / 2), 5.0F, 16777215 | j2, true);
            GlStateManager.func_179121_F();
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
         }

         this.field_73839_d.field_71424_I.func_76319_b();
      }

      Scoreboard scoreboard = this.field_73839_d.field_71441_e.func_96441_U();
      ScoreObjective scoreobjective = null;
      ScorePlayerTeam scoreplayerteam = scoreboard.func_96509_i(this.field_73839_d.field_71439_g.func_70005_c_());
      if(scoreplayerteam != null) {
         int i1 = scoreplayerteam.func_178775_l().func_175746_b();
         if(i1 >= 0) {
            scoreobjective = scoreboard.func_96539_a(3 + i1);
         }
      }

      ScoreObjective scoreobjective1 = scoreobjective != null?scoreobjective:scoreboard.func_96539_a(1);
      if(scoreobjective1 != null) {
         this.func_180475_a(scoreobjective1, scaledresolution);
      }

      GlStateManager.func_179147_l();
      GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
      GlStateManager.func_179118_c();
      GlStateManager.func_179094_E();
      GlStateManager.func_179109_b(0.0F, (float)(j - 48), 0.0F);
      this.field_73839_d.field_71424_I.func_76320_a("chat");
      this.field_73840_e.func_146230_a(this.field_73837_f);
      this.field_73839_d.field_71424_I.func_76319_b();
      GlStateManager.func_179121_F();
      scoreobjective1 = scoreboard.func_96539_a(0);
      if(!this.field_73839_d.field_71474_y.field_74321_H.func_151470_d() || this.field_73839_d.func_71387_A() && this.field_73839_d.field_71439_g.field_71174_a.func_175106_d().size() <= 1 && scoreobjective1 == null) {
         this.field_175196_v.func_175246_a(false);
      } else {
         this.field_175196_v.func_175246_a(true);
         this.field_175196_v.func_175249_a(i, scoreboard, scoreobjective1);
      }

      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179140_f();
      GlStateManager.func_179141_d();
      String s = this.field_73839_d.func_184123_d();
   }

   private void func_184045_a(float p_184045_1_, ScaledResolution p_184045_2_) {
      GameSettings gamesettings = this.field_73839_d.field_71474_y;
      if(gamesettings.field_74320_O == 0) {
         if(this.field_73839_d.field_71442_b.func_78747_a() && this.field_73839_d.field_147125_j == null) {
            RayTraceResult raytraceresult = this.field_73839_d.field_71476_x;
            if(raytraceresult == null || raytraceresult.field_72313_a != RayTraceResult.Type.BLOCK) {
               return;
            }

            BlockPos blockpos = raytraceresult.func_178782_a();
            if(!this.field_73839_d.field_71441_e.func_180495_p(blockpos).func_177230_c().func_149716_u() || !(this.field_73839_d.field_71441_e.func_175625_s(blockpos) instanceof IInventory)) {
               return;
            }
         }

         int l = p_184045_2_.func_78326_a();
         int i1 = p_184045_2_.func_78328_b();
         if(gamesettings.field_74330_P && !gamesettings.field_74319_N && !this.field_73839_d.field_71439_g.func_175140_cp() && !gamesettings.field_178879_v) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b((float)(l / 2), (float)(i1 / 2), this.field_73735_i);
            Entity entity = this.field_73839_d.func_175606_aa();
            GlStateManager.func_179114_b(entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * p_184045_1_, -1.0F, 0.0F, 0.0F);
            GlStateManager.func_179114_b(entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * p_184045_1_, 0.0F, 1.0F, 0.0F);
            GlStateManager.func_179152_a(-1.0F, -1.0F, -1.0F);
            OpenGlHelper.func_188785_m(10);
            GlStateManager.func_179121_F();
         } else {
            GlStateManager.func_187428_a(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.func_179141_d();
            this.func_73729_b(l / 2 - 7, i1 / 2 - 7, 0, 0, 16, 16);
            if(this.field_73839_d.field_71474_y.field_186716_M == 1) {
               float f = this.field_73839_d.field_71439_g.func_184825_o(0.0F);
               if(f < 1.0F) {
                  int i = i1 / 2 - 7 + 16;
                  int j = l / 2 - 7;
                  int k = (int)(f * 17.0F);
                  this.func_73729_b(j, i, 36, 94, 16, 4);
                  this.func_73729_b(j, i, 52, 94, k, 4);
               }
            }
         }

      }
   }

   protected void func_184048_a(ScaledResolution p_184048_1_) {
      Collection<PotionEffect> collection = this.field_73839_d.field_71439_g.func_70651_bq();
      if(!collection.isEmpty()) {
         this.field_73839_d.func_110434_K().func_110577_a(GuiContainer.field_147001_a);
         GlStateManager.func_179147_l();
         int i = 0;
         int j = 0;

         for(PotionEffect potioneffect : Ordering.natural().reverse().sortedCopy(collection)) {
            Potion potion = potioneffect.func_188419_a();
            if(potion.func_76400_d() && potioneffect.func_188418_e()) {
               int k = p_184048_1_.func_78326_a();
               int l = 1;
               int i1 = potion.func_76392_e();
               float f = 1.0F;
               if(potion.func_188408_i()) {
                  ++i;
                  k = k - 25 * i;
               } else {
                  ++j;
                  k = k - 25 * j;
                  l += 26;
               }

               GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
               if(potioneffect.func_82720_e()) {
                  this.func_73729_b(k, l, 165, 166, 24, 24);
               } else {
                  this.func_73729_b(k, l, 141, 166, 24, 24);
                  if(potioneffect.func_76459_b() <= 200) {
                     int j1 = 10 - potioneffect.func_76459_b() / 20;
                     f = MathHelper.func_76131_a((float)potioneffect.func_76459_b() / 10.0F / 5.0F * 0.5F, 0.0F, 0.5F) + MathHelper.func_76134_b((float)potioneffect.func_76459_b() * 3.1415927F / 5.0F) * MathHelper.func_76131_a((float)j1 / 10.0F * 0.25F, 0.0F, 0.25F);
                  }
               }

               GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, f);
               this.func_73729_b(k + 3, l + 3, i1 % 8 * 18, 198 + i1 / 8 * 18, 18, 18);
            }
         }

      }
   }

   protected void func_180479_a(ScaledResolution p_180479_1_, float p_180479_2_) {
      if(this.field_73839_d.func_175606_aa() instanceof EntityPlayer) {
         GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
         this.field_73839_d.func_110434_K().func_110577_a(field_110330_c);
         EntityPlayer entityplayer = (EntityPlayer)this.field_73839_d.func_175606_aa();
         ItemStack itemstack = entityplayer.func_184592_cb();
         EnumHandSide enumhandside = entityplayer.func_184591_cq().func_188468_a();
         int i = p_180479_1_.func_78326_a() / 2;
         float f = this.field_73735_i;
         int j = 182;
         int k = 91;
         this.field_73735_i = -90.0F;
         this.func_73729_b(i - 91, p_180479_1_.func_78328_b() - 22, 0, 0, 182, 22);
         this.func_73729_b(i - 91 - 1 + entityplayer.field_71071_by.field_70461_c * 20, p_180479_1_.func_78328_b() - 22 - 1, 0, 22, 24, 22);
         if(itemstack != null) {
            if(enumhandside == EnumHandSide.LEFT) {
               this.func_73729_b(i - 91 - 29, p_180479_1_.func_78328_b() - 23, 24, 22, 29, 24);
            } else {
               this.func_73729_b(i + 91, p_180479_1_.func_78328_b() - 23, 53, 22, 29, 24);
            }
         }

         this.field_73735_i = f;
         GlStateManager.func_179091_B();
         GlStateManager.func_179147_l();
         GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
         RenderHelper.func_74520_c();

         for(int l = 0; l < 9; ++l) {
            int i1 = i - 90 + l * 20 + 2;
            int j1 = p_180479_1_.func_78328_b() - 16 - 3;
            this.func_184044_a(i1, j1, p_180479_2_, entityplayer, entityplayer.field_71071_by.field_70462_a[l]);
         }

         if(itemstack != null) {
            int l1 = p_180479_1_.func_78328_b() - 16 - 3;
            if(enumhandside == EnumHandSide.LEFT) {
               this.func_184044_a(i - 91 - 26, l1, p_180479_2_, entityplayer, itemstack);
            } else {
               this.func_184044_a(i + 91 + 10, l1, p_180479_2_, entityplayer, itemstack);
            }
         }

         if(this.field_73839_d.field_71474_y.field_186716_M == 2) {
            float f1 = this.field_73839_d.field_71439_g.func_184825_o(0.0F);
            if(f1 < 1.0F) {
               int i2 = p_180479_1_.func_78328_b() - 20;
               int j2 = i + 91 + 6;
               if(enumhandside == EnumHandSide.RIGHT) {
                  j2 = i - 91 - 22;
               }

               this.field_73839_d.func_110434_K().func_110577_a(Gui.field_110324_m);
               int k1 = (int)(f1 * 19.0F);
               GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
               this.func_73729_b(j2, i2, 0, 94, 18, 18);
               this.func_73729_b(j2, i2 + 18 - k1, 18, 112 - k1, 18, k1);
            }
         }

         RenderHelper.func_74518_a();
         GlStateManager.func_179101_C();
         GlStateManager.func_179084_k();
      }
   }

   public void func_175186_a(ScaledResolution p_175186_1_, int p_175186_2_) {
      this.field_73839_d.field_71424_I.func_76320_a("jumpBar");
      this.field_73839_d.func_110434_K().func_110577_a(Gui.field_110324_m);
      float f = this.field_73839_d.field_71439_g.func_110319_bJ();
      int i = 182;
      int j = (int)(f * (float)(i + 1));
      int k = p_175186_1_.func_78328_b() - 32 + 3;
      this.func_73729_b(p_175186_2_, k, 0, 84, i, 5);
      if(j > 0) {
         this.func_73729_b(p_175186_2_, k, 0, 89, j, 5);
      }

      this.field_73839_d.field_71424_I.func_76319_b();
   }

   public void func_175176_b(ScaledResolution p_175176_1_, int p_175176_2_) {
      this.field_73839_d.field_71424_I.func_76320_a("expBar");
      this.field_73839_d.func_110434_K().func_110577_a(Gui.field_110324_m);
      int i = this.field_73839_d.field_71439_g.func_71050_bK();
      if(i > 0) {
         int j = 182;
         int k = (int)(this.field_73839_d.field_71439_g.field_71106_cc * (float)(j + 1));
         int l = p_175176_1_.func_78328_b() - 32 + 3;
         this.func_73729_b(p_175176_2_, l, 0, 64, j, 5);
         if(k > 0) {
            this.func_73729_b(p_175176_2_, l, 0, 69, k, 5);
         }
      }

      this.field_73839_d.field_71424_I.func_76319_b();
      if(this.field_73839_d.field_71439_g.field_71068_ca > 0) {
         this.field_73839_d.field_71424_I.func_76320_a("expLevel");
         int k1 = 8453920;
         String s = "" + this.field_73839_d.field_71439_g.field_71068_ca;
         int l1 = (p_175176_1_.func_78326_a() - this.func_175179_f().func_78256_a(s)) / 2;
         int i1 = p_175176_1_.func_78328_b() - 31 - 4;
         int j1 = 0;
         this.func_175179_f().func_78276_b(s, l1 + 1, i1, 0);
         this.func_175179_f().func_78276_b(s, l1 - 1, i1, 0);
         this.func_175179_f().func_78276_b(s, l1, i1 + 1, 0);
         this.func_175179_f().func_78276_b(s, l1, i1 - 1, 0);
         this.func_175179_f().func_78276_b(s, l1, i1, k1);
         this.field_73839_d.field_71424_I.func_76319_b();
      }

   }

   public void func_181551_a(ScaledResolution p_181551_1_) {
      this.field_73839_d.field_71424_I.func_76320_a("selectedItemName");
      if(this.field_92017_k > 0 && this.field_92016_l != null) {
         String s = this.field_92016_l.func_82833_r();
         if(this.field_92016_l.func_82837_s()) {
            s = TextFormatting.ITALIC + s;
         }

         int i = (p_181551_1_.func_78326_a() - this.func_175179_f().func_78256_a(s)) / 2;
         int j = p_181551_1_.func_78328_b() - 59;
         if(!this.field_73839_d.field_71442_b.func_78755_b()) {
            j += 14;
         }

         int k = (int)((float)this.field_92017_k * 256.0F / 10.0F);
         if(k > 255) {
            k = 255;
         }

         if(k > 0) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179147_l();
            GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            this.func_175179_f().func_175063_a(s, (float)i, (float)j, 16777215 + (k << 24));
            GlStateManager.func_179084_k();
            GlStateManager.func_179121_F();
         }
      }

      this.field_73839_d.field_71424_I.func_76319_b();
   }

   public void func_175185_b(ScaledResolution p_175185_1_) {
      this.field_73839_d.field_71424_I.func_76320_a("demo");
      String s = "";
      if(this.field_73839_d.field_71441_e.func_82737_E() >= 120500L) {
         s = I18n.func_135052_a("demo.demoExpired", new Object[0]);
      } else {
         s = I18n.func_135052_a("demo.remainingTime", new Object[]{StringUtils.func_76337_a((int)(120500L - this.field_73839_d.field_71441_e.func_82737_E()))});
      }

      int i = this.func_175179_f().func_78256_a(s);
      this.func_175179_f().func_175063_a(s, (float)(p_175185_1_.func_78326_a() - i - 10), 5.0F, 16777215);
      this.field_73839_d.field_71424_I.func_76319_b();
   }

   private void func_180475_a(ScoreObjective p_180475_1_, ScaledResolution p_180475_2_) {
      Scoreboard scoreboard = p_180475_1_.func_96682_a();
      Collection<Score> collection = scoreboard.func_96534_i(p_180475_1_);
      List<Score> list = Lists.newArrayList(Iterables.filter(collection, new Predicate<Score>() {
         public boolean apply(Score p_apply_1_) {
            return p_apply_1_.func_96653_e() != null && !p_apply_1_.func_96653_e().startsWith("#");
         }
      }));
      if(list.size() > 15) {
         collection = Lists.newArrayList(Iterables.skip(list, collection.size() - 15));
      } else {
         collection = list;
      }

      int i = this.func_175179_f().func_78256_a(p_180475_1_.func_96678_d());

      for(Score score : collection) {
         ScorePlayerTeam scoreplayerteam = scoreboard.func_96509_i(score.func_96653_e());
         String s = ScorePlayerTeam.func_96667_a(scoreplayerteam, score.func_96653_e()) + ": " + TextFormatting.RED + score.func_96652_c();
         i = Math.max(i, this.func_175179_f().func_78256_a(s));
      }

      int i1 = collection.size() * this.func_175179_f().field_78288_b;
      int j1 = p_180475_2_.func_78328_b() / 2 + i1 / 3;
      int k1 = 3;
      int l1 = p_180475_2_.func_78326_a() - i - k1;
      int j = 0;

      for(Score score1 : collection) {
         ++j;
         ScorePlayerTeam scoreplayerteam1 = scoreboard.func_96509_i(score1.func_96653_e());
         String s1 = ScorePlayerTeam.func_96667_a(scoreplayerteam1, score1.func_96653_e());
         String s2 = TextFormatting.RED + "" + score1.func_96652_c();
         int k = j1 - j * this.func_175179_f().field_78288_b;
         int l = p_180475_2_.func_78326_a() - k1 + 2;
         func_73734_a(l1 - 2, k, l, k + this.func_175179_f().field_78288_b, 1342177280);
         this.func_175179_f().func_78276_b(s1, l1, k, 553648127);
         this.func_175179_f().func_78276_b(s2, l - this.func_175179_f().func_78256_a(s2), k, 553648127);
         if(j == collection.size()) {
            String s3 = p_180475_1_.func_96678_d();
            func_73734_a(l1 - 2, k - this.func_175179_f().field_78288_b - 1, l, k - 1, 1610612736);
            func_73734_a(l1 - 2, k - 1, l, k, 1342177280);
            this.func_175179_f().func_78276_b(s3, l1 + i / 2 - this.func_175179_f().func_78256_a(s3) / 2, k - this.func_175179_f().field_78288_b, 553648127);
         }
      }

   }

   private void func_180477_d(ScaledResolution p_180477_1_) {
      if(this.field_73839_d.func_175606_aa() instanceof EntityPlayer) {
         EntityPlayer entityplayer = (EntityPlayer)this.field_73839_d.func_175606_aa();
         int i = MathHelper.func_76123_f(entityplayer.func_110143_aJ());
         boolean flag = this.field_175191_F > (long)this.field_73837_f && (this.field_175191_F - (long)this.field_73837_f) / 3L % 2L == 1L;
         if(i < this.field_175194_C && entityplayer.field_70172_ad > 0) {
            this.field_175190_E = Minecraft.func_71386_F();
            this.field_175191_F = (long)(this.field_73837_f + 20);
         } else if(i > this.field_175194_C && entityplayer.field_70172_ad > 0) {
            this.field_175190_E = Minecraft.func_71386_F();
            this.field_175191_F = (long)(this.field_73837_f + 10);
         }

         if(Minecraft.func_71386_F() - this.field_175190_E > 1000L) {
            this.field_175194_C = i;
            this.field_175189_D = i;
            this.field_175190_E = Minecraft.func_71386_F();
         }

         this.field_175194_C = i;
         int j = this.field_175189_D;
         this.field_73842_c.setSeed((long)(this.field_73837_f * 312871));
         boolean flag1 = false;
         FoodStats foodstats = entityplayer.func_71024_bL();
         int k = foodstats.func_75116_a();
         int l = foodstats.func_75120_b();
         IAttributeInstance iattributeinstance = entityplayer.func_110148_a(SharedMonsterAttributes.field_111267_a);
         int i1 = p_180477_1_.func_78326_a() / 2 - 91;
         int j1 = p_180477_1_.func_78326_a() / 2 + 91;
         int k1 = p_180477_1_.func_78328_b() - 39;
         float f = (float)iattributeinstance.func_111126_e();
         int l1 = MathHelper.func_76123_f(entityplayer.func_110139_bj());
         int i2 = MathHelper.func_76123_f((f + (float)l1) / 2.0F / 10.0F);
         int j2 = Math.max(10 - (i2 - 2), 3);
         int k2 = k1 - (i2 - 1) * j2 - 10;
         int l2 = k1 - 10;
         int i3 = l1;
         int j3 = entityplayer.func_70658_aO();
         int k3 = -1;
         if(entityplayer.func_70644_a(MobEffects.field_76428_l)) {
            k3 = this.field_73837_f % MathHelper.func_76123_f(f + 5.0F);
         }

         this.field_73839_d.field_71424_I.func_76320_a("armor");

         for(int l3 = 0; l3 < 10; ++l3) {
            if(j3 > 0) {
               int i4 = i1 + l3 * 8;
               if(l3 * 2 + 1 < j3) {
                  this.func_73729_b(i4, k2, 34, 9, 9, 9);
               }

               if(l3 * 2 + 1 == j3) {
                  this.func_73729_b(i4, k2, 25, 9, 9, 9);
               }

               if(l3 * 2 + 1 > j3) {
                  this.func_73729_b(i4, k2, 16, 9, 9, 9);
               }
            }
         }

         this.field_73839_d.field_71424_I.func_76318_c("health");

         for(int k5 = MathHelper.func_76123_f((f + (float)l1) / 2.0F) - 1; k5 >= 0; --k5) {
            int l5 = 16;
            if(entityplayer.func_70644_a(MobEffects.field_76436_u)) {
               l5 += 36;
            } else if(entityplayer.func_70644_a(MobEffects.field_82731_v)) {
               l5 += 72;
            }

            int j4 = 0;
            if(flag) {
               j4 = 1;
            }

            int k4 = MathHelper.func_76123_f((float)(k5 + 1) / 10.0F) - 1;
            int l4 = i1 + k5 % 10 * 8;
            int i5 = k1 - k4 * j2;
            if(i <= 4) {
               i5 += this.field_73842_c.nextInt(2);
            }

            if(i3 <= 0 && k5 == k3) {
               i5 -= 2;
            }

            int j5 = 0;
            if(entityplayer.field_70170_p.func_72912_H().func_76093_s()) {
               j5 = 5;
            }

            this.func_73729_b(l4, i5, 16 + j4 * 9, 9 * j5, 9, 9);
            if(flag) {
               if(k5 * 2 + 1 < j) {
                  this.func_73729_b(l4, i5, l5 + 54, 9 * j5, 9, 9);
               }

               if(k5 * 2 + 1 == j) {
                  this.func_73729_b(l4, i5, l5 + 63, 9 * j5, 9, 9);
               }
            }

            if(i3 > 0) {
               if(i3 == l1 && l1 % 2 == 1) {
                  this.func_73729_b(l4, i5, l5 + 153, 9 * j5, 9, 9);
                  --i3;
               } else {
                  this.func_73729_b(l4, i5, l5 + 144, 9 * j5, 9, 9);
                  i3 -= 2;
               }
            } else {
               if(k5 * 2 + 1 < i) {
                  this.func_73729_b(l4, i5, l5 + 36, 9 * j5, 9, 9);
               }

               if(k5 * 2 + 1 == i) {
                  this.func_73729_b(l4, i5, l5 + 45, 9 * j5, 9, 9);
               }
            }
         }

         Entity entity = entityplayer.func_184187_bx();
         if(entity == null) {
            this.field_73839_d.field_71424_I.func_76318_c("food");

            for(int i6 = 0; i6 < 10; ++i6) {
               int k6 = k1;
               int i7 = 16;
               int k7 = 0;
               if(entityplayer.func_70644_a(MobEffects.field_76438_s)) {
                  i7 += 36;
                  k7 = 13;
               }

               if(entityplayer.func_71024_bL().func_75115_e() <= 0.0F && this.field_73837_f % (k * 3 + 1) == 0) {
                  k6 = k1 + (this.field_73842_c.nextInt(3) - 1);
               }

               if(flag1) {
                  k7 = 1;
               }

               int i8 = j1 - i6 * 8 - 9;
               this.func_73729_b(i8, k6, 16 + k7 * 9, 27, 9, 9);
               if(flag1) {
                  if(i6 * 2 + 1 < l) {
                     this.func_73729_b(i8, k6, i7 + 54, 27, 9, 9);
                  }

                  if(i6 * 2 + 1 == l) {
                     this.func_73729_b(i8, k6, i7 + 63, 27, 9, 9);
                  }
               }

               if(i6 * 2 + 1 < k) {
                  this.func_73729_b(i8, k6, i7 + 36, 27, 9, 9);
               }

               if(i6 * 2 + 1 == k) {
                  this.func_73729_b(i8, k6, i7 + 45, 27, 9, 9);
               }
            }
         }

         this.field_73839_d.field_71424_I.func_76318_c("air");
         if(entityplayer.func_70055_a(Material.field_151586_h)) {
            int j6 = this.field_73839_d.field_71439_g.func_70086_ai();
            int l6 = MathHelper.func_76143_f((double)(j6 - 2) * 10.0D / 300.0D);
            int j7 = MathHelper.func_76143_f((double)j6 * 10.0D / 300.0D) - l6;

            for(int l7 = 0; l7 < l6 + j7; ++l7) {
               if(l7 < l6) {
                  this.func_73729_b(j1 - l7 * 8 - 9, l2, 16, 18, 9, 9);
               } else {
                  this.func_73729_b(j1 - l7 * 8 - 9, l2, 25, 18, 9, 9);
               }
            }
         }

         this.field_73839_d.field_71424_I.func_76319_b();
      }
   }

   private void func_184047_e(ScaledResolution p_184047_1_) {
      if(this.field_73839_d.func_175606_aa() instanceof EntityPlayer) {
         EntityPlayer entityplayer = (EntityPlayer)this.field_73839_d.func_175606_aa();
         Entity entity = entityplayer.func_184187_bx();
         if(entity instanceof EntityLivingBase) {
            this.field_73839_d.field_71424_I.func_76318_c("mountHealth");
            EntityLivingBase entitylivingbase = (EntityLivingBase)entity;
            int i = (int)Math.ceil((double)entitylivingbase.func_110143_aJ());
            float f = entitylivingbase.func_110138_aP();
            int j = (int)(f + 0.5F) / 2;
            if(j > 30) {
               j = 30;
            }

            int k = p_184047_1_.func_78328_b() - 39;
            int l = p_184047_1_.func_78326_a() / 2 + 91;
            int i1 = k;
            int j1 = 0;

            for(boolean flag = false; j > 0; j1 += 20) {
               int k1 = Math.min(j, 10);
               j -= k1;

               for(int l1 = 0; l1 < k1; ++l1) {
                  int i2 = 52;
                  int j2 = 0;
                  if(flag) {
                     j2 = 1;
                  }

                  int k2 = l - l1 * 8 - 9;
                  this.func_73729_b(k2, i1, i2 + j2 * 9, 9, 9, 9);
                  if(l1 * 2 + 1 + j1 < i) {
                     this.func_73729_b(k2, i1, i2 + 36, 9, 9, 9);
                  }

                  if(l1 * 2 + 1 + j1 == i) {
                     this.func_73729_b(k2, i1, i2 + 45, 9, 9, 9);
                  }
               }

               i1 -= 10;
            }
         }

      }
   }

   private void func_180476_e(ScaledResolution p_180476_1_) {
      GlStateManager.func_179097_i();
      GlStateManager.func_179132_a(false);
      GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_179118_c();
      this.field_73839_d.func_110434_K().func_110577_a(field_110328_d);
      Tessellator tessellator = Tessellator.func_178181_a();
      VertexBuffer vertexbuffer = tessellator.func_178180_c();
      vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      vertexbuffer.func_181662_b(0.0D, (double)p_180476_1_.func_78328_b(), -90.0D).func_187315_a(0.0D, 1.0D).func_181675_d();
      vertexbuffer.func_181662_b((double)p_180476_1_.func_78326_a(), (double)p_180476_1_.func_78328_b(), -90.0D).func_187315_a(1.0D, 1.0D).func_181675_d();
      vertexbuffer.func_181662_b((double)p_180476_1_.func_78326_a(), 0.0D, -90.0D).func_187315_a(1.0D, 0.0D).func_181675_d();
      vertexbuffer.func_181662_b(0.0D, 0.0D, -90.0D).func_187315_a(0.0D, 0.0D).func_181675_d();
      tessellator.func_78381_a();
      GlStateManager.func_179132_a(true);
      GlStateManager.func_179126_j();
      GlStateManager.func_179141_d();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void func_180480_a(float p_180480_1_, ScaledResolution p_180480_2_) {
      p_180480_1_ = 1.0F - p_180480_1_;
      p_180480_1_ = MathHelper.func_76131_a(p_180480_1_, 0.0F, 1.0F);
      WorldBorder worldborder = this.field_73839_d.field_71441_e.func_175723_af();
      float f = (float)worldborder.func_177745_a(this.field_73839_d.field_71439_g);
      double d0 = Math.min(worldborder.func_177749_o() * (double)worldborder.func_177740_p() * 1000.0D, Math.abs(worldborder.func_177751_j() - worldborder.func_177741_h()));
      double d1 = Math.max((double)worldborder.func_177748_q(), d0);
      if((double)f < d1) {
         f = 1.0F - (float)((double)f / d1);
      } else {
         f = 0.0F;
      }

      this.field_73843_a = (float)((double)this.field_73843_a + (double)(p_180480_1_ - this.field_73843_a) * 0.01D);
      GlStateManager.func_179097_i();
      GlStateManager.func_179132_a(false);
      GlStateManager.func_187428_a(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
      if(f > 0.0F) {
         GlStateManager.func_179131_c(0.0F, f, f, 1.0F);
      } else {
         GlStateManager.func_179131_c(this.field_73843_a, this.field_73843_a, this.field_73843_a, 1.0F);
      }

      this.field_73839_d.func_110434_K().func_110577_a(field_110329_b);
      Tessellator tessellator = Tessellator.func_178181_a();
      VertexBuffer vertexbuffer = tessellator.func_178180_c();
      vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      vertexbuffer.func_181662_b(0.0D, (double)p_180480_2_.func_78328_b(), -90.0D).func_187315_a(0.0D, 1.0D).func_181675_d();
      vertexbuffer.func_181662_b((double)p_180480_2_.func_78326_a(), (double)p_180480_2_.func_78328_b(), -90.0D).func_187315_a(1.0D, 1.0D).func_181675_d();
      vertexbuffer.func_181662_b((double)p_180480_2_.func_78326_a(), 0.0D, -90.0D).func_187315_a(1.0D, 0.0D).func_181675_d();
      vertexbuffer.func_181662_b(0.0D, 0.0D, -90.0D).func_187315_a(0.0D, 0.0D).func_181675_d();
      tessellator.func_78381_a();
      GlStateManager.func_179132_a(true);
      GlStateManager.func_179126_j();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
   }

   private void func_180474_b(float p_180474_1_, ScaledResolution p_180474_2_) {
      if(p_180474_1_ < 1.0F) {
         p_180474_1_ = p_180474_1_ * p_180474_1_;
         p_180474_1_ = p_180474_1_ * p_180474_1_;
         p_180474_1_ = p_180474_1_ * 0.8F + 0.2F;
      }

      GlStateManager.func_179118_c();
      GlStateManager.func_179097_i();
      GlStateManager.func_179132_a(false);
      GlStateManager.func_187428_a(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, p_180474_1_);
      this.field_73839_d.func_110434_K().func_110577_a(TextureMap.field_110575_b);
      TextureAtlasSprite textureatlassprite = this.field_73839_d.func_175602_ab().func_175023_a().func_178122_a(Blocks.field_150427_aO.func_176223_P());
      float f = textureatlassprite.func_94209_e();
      float f1 = textureatlassprite.func_94206_g();
      float f2 = textureatlassprite.func_94212_f();
      float f3 = textureatlassprite.func_94210_h();
      Tessellator tessellator = Tessellator.func_178181_a();
      VertexBuffer vertexbuffer = tessellator.func_178180_c();
      vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
      vertexbuffer.func_181662_b(0.0D, (double)p_180474_2_.func_78328_b(), -90.0D).func_187315_a((double)f, (double)f3).func_181675_d();
      vertexbuffer.func_181662_b((double)p_180474_2_.func_78326_a(), (double)p_180474_2_.func_78328_b(), -90.0D).func_187315_a((double)f2, (double)f3).func_181675_d();
      vertexbuffer.func_181662_b((double)p_180474_2_.func_78326_a(), 0.0D, -90.0D).func_187315_a((double)f2, (double)f1).func_181675_d();
      vertexbuffer.func_181662_b(0.0D, 0.0D, -90.0D).func_187315_a((double)f, (double)f1).func_181675_d();
      tessellator.func_78381_a();
      GlStateManager.func_179132_a(true);
      GlStateManager.func_179126_j();
      GlStateManager.func_179141_d();
      GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void func_184044_a(int p_184044_1_, int p_184044_2_, float p_184044_3_, EntityPlayer p_184044_4_, ItemStack p_184044_5_) {
      if(p_184044_5_ != null) {
         float f = (float)p_184044_5_.field_77992_b - p_184044_3_;
         if(f > 0.0F) {
            GlStateManager.func_179094_E();
            float f1 = 1.0F + f / 5.0F;
            GlStateManager.func_179109_b((float)(p_184044_1_ + 8), (float)(p_184044_2_ + 12), 0.0F);
            GlStateManager.func_179152_a(1.0F / f1, (f1 + 1.0F) / 2.0F, 1.0F);
            GlStateManager.func_179109_b((float)(-(p_184044_1_ + 8)), (float)(-(p_184044_2_ + 12)), 0.0F);
         }

         this.field_73841_b.func_184391_a(p_184044_4_, p_184044_5_, p_184044_1_, p_184044_2_);
         if(f > 0.0F) {
            GlStateManager.func_179121_F();
         }

         this.field_73841_b.func_175030_a(this.field_73839_d.field_71466_p, p_184044_5_, p_184044_1_, p_184044_2_);
      }
   }

   public void func_73831_a() {
      if(this.field_73845_h > 0) {
         --this.field_73845_h;
      }

      if(this.field_175195_w > 0) {
         --this.field_175195_w;
         if(this.field_175195_w <= 0) {
            this.field_175201_x = "";
            this.field_175200_y = "";
         }
      }

      ++this.field_73837_f;
      if(this.field_73839_d.field_71439_g != null) {
         ItemStack itemstack = this.field_73839_d.field_71439_g.field_71071_by.func_70448_g();
         if(itemstack == null) {
            this.field_92017_k = 0;
         } else if(this.field_92016_l != null && itemstack.func_77973_b() == this.field_92016_l.func_77973_b() && ItemStack.func_77970_a(itemstack, this.field_92016_l) && (itemstack.func_77984_f() || itemstack.func_77960_j() == this.field_92016_l.func_77960_j())) {
            if(this.field_92017_k > 0) {
               --this.field_92017_k;
            }
         } else {
            this.field_92017_k = 40;
         }

         this.field_92016_l = itemstack;
      }

   }

   public void func_73833_a(String p_73833_1_) {
      this.func_110326_a(I18n.func_135052_a("record.nowPlaying", new Object[]{p_73833_1_}), true);
   }

   public void func_110326_a(String p_110326_1_, boolean p_110326_2_) {
      this.field_73838_g = p_110326_1_;
      this.field_73845_h = 60;
      this.field_73844_j = p_110326_2_;
   }

   public void func_175178_a(String p_175178_1_, String p_175178_2_, int p_175178_3_, int p_175178_4_, int p_175178_5_) {
      if(p_175178_1_ == null && p_175178_2_ == null && p_175178_3_ < 0 && p_175178_4_ < 0 && p_175178_5_ < 0) {
         this.field_175201_x = "";
         this.field_175200_y = "";
         this.field_175195_w = 0;
      } else if(p_175178_1_ != null) {
         this.field_175201_x = p_175178_1_;
         this.field_175195_w = this.field_175199_z + this.field_175192_A + this.field_175193_B;
      } else if(p_175178_2_ != null) {
         this.field_175200_y = p_175178_2_;
      } else {
         if(p_175178_3_ >= 0) {
            this.field_175199_z = p_175178_3_;
         }

         if(p_175178_4_ >= 0) {
            this.field_175192_A = p_175178_4_;
         }

         if(p_175178_5_ >= 0) {
            this.field_175193_B = p_175178_5_;
         }

         if(this.field_175195_w > 0) {
            this.field_175195_w = this.field_175199_z + this.field_175192_A + this.field_175193_B;
         }

      }
   }

   public void func_175188_a(ITextComponent p_175188_1_, boolean p_175188_2_) {
      this.func_110326_a(p_175188_1_.func_150260_c(), p_175188_2_);
   }

   public GuiNewChat func_146158_b() {
      return this.field_73840_e;
   }

   public int func_73834_c() {
      return this.field_73837_f;
   }

   public FontRenderer func_175179_f() {
      return this.field_73839_d.field_71466_p;
   }

   public GuiSpectator func_175187_g() {
      return this.field_175197_u;
   }

   public GuiPlayerTabOverlay func_175181_h() {
      return this.field_175196_v;
   }

   public void func_181029_i() {
      this.field_175196_v.func_181030_a();
      this.field_184050_w.func_184057_b();
   }

   public GuiBossOverlay func_184046_j() {
      return this.field_184050_w;
   }
}
