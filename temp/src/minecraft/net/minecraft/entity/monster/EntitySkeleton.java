package net.minecraft.entity.monster;

import java.util.Calendar;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRangedBow;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.storage.loot.LootTableList;

public class EntitySkeleton extends EntityMob implements IRangedAttackMob {
   private static final DataParameter<Integer> field_184727_a = EntityDataManager.<Integer>func_187226_a(EntitySkeleton.class, DataSerializers.field_187192_b);
   private static final DataParameter<Boolean> field_184728_b = EntityDataManager.<Boolean>func_187226_a(EntitySkeleton.class, DataSerializers.field_187198_h);
   private final EntityAIAttackRangedBow field_85037_d = new EntityAIAttackRangedBow(this, 1.0D, 20, 15.0F);
   private final EntityAIAttackMelee field_85038_e = new EntityAIAttackMelee(this, 1.2D, p_i47000_5_) {
      public void func_75251_c() {
         super.func_75251_c();
         EntitySkeleton.this.func_184724_a(false);
      }

      public void func_75249_e() {
         super.func_75249_e();
         EntitySkeleton.this.func_184724_a(true);
      }
   };

   public EntitySkeleton(World p_i1741_1_) {
      super(p_i1741_1_);
      this.func_85036_m();
   }

   protected void func_184651_r() {
      this.field_70714_bg.func_75776_a(1, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(2, new EntityAIRestrictSun(this));
      this.field_70714_bg.func_75776_a(3, new EntityAIFleeSun(this, 1.0D));
      this.field_70714_bg.func_75776_a(3, new EntityAIAvoidEntity(this, EntityWolf.class, 6.0F, 1.0D, 1.2D));
      this.field_70714_bg.func_75776_a(5, new EntityAIWander(this, 1.0D));
      this.field_70714_bg.func_75776_a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
      this.field_70714_bg.func_75776_a(6, new EntityAILookIdle(this));
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, false, new Class[0]));
      this.field_70715_bh.func_75776_a(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
      this.field_70715_bh.func_75776_a(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(field_184727_a, Integer.valueOf(0));
      this.field_70180_af.func_187214_a(field_184728_b, Boolean.valueOf(false));
   }

   protected SoundEvent func_184639_G() {
      return SoundEvents.field_187854_fc;
   }

   protected SoundEvent func_184601_bQ() {
      return SoundEvents.field_187864_fh;
   }

   protected SoundEvent func_184615_bR() {
      return SoundEvents.field_187856_fd;
   }

   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
      this.func_184185_a(SoundEvents.field_187868_fj, 0.15F, 1.0F);
   }

   public boolean func_70652_k(Entity p_70652_1_) {
      if(super.func_70652_k(p_70652_1_)) {
         if(this.func_82202_m() == 1 && p_70652_1_ instanceof EntityLivingBase) {
            ((EntityLivingBase)p_70652_1_).func_70690_d(new PotionEffect(MobEffects.field_82731_v, 200));
         }

         return true;
      } else {
         return false;
      }
   }

   public EnumCreatureAttribute func_70668_bt() {
      return EnumCreatureAttribute.UNDEAD;
   }

   public void func_70636_d() {
      if(this.field_70170_p.func_72935_r() && !this.field_70170_p.field_72995_K) {
         float f = this.func_70013_c(1.0F);
         BlockPos blockpos = this.func_184187_bx() instanceof EntityBoat?(new BlockPos(this.field_70165_t, (double)Math.round(this.field_70163_u), this.field_70161_v)).func_177984_a():new BlockPos(this.field_70165_t, (double)Math.round(this.field_70163_u), this.field_70161_v);
         if(f > 0.5F && this.field_70146_Z.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.field_70170_p.func_175678_i(blockpos)) {
            boolean flag = true;
            ItemStack itemstack = this.func_184582_a(EntityEquipmentSlot.HEAD);
            if(itemstack != null) {
               if(itemstack.func_77984_f()) {
                  itemstack.func_77964_b(itemstack.func_77952_i() + this.field_70146_Z.nextInt(2));
                  if(itemstack.func_77952_i() >= itemstack.func_77958_k()) {
                     this.func_70669_a(itemstack);
                     this.func_184201_a(EntityEquipmentSlot.HEAD, (ItemStack)null);
                  }
               }

               flag = false;
            }

            if(flag) {
               this.func_70015_d(8);
            }
         }
      }

      if(this.field_70170_p.field_72995_K) {
         this.func_184726_b(this.func_82202_m());
      }

      super.func_70636_d();
   }

   public void func_70098_U() {
      super.func_70098_U();
      if(this.func_184187_bx() instanceof EntityCreature) {
         EntityCreature entitycreature = (EntityCreature)this.func_184187_bx();
         this.field_70761_aq = entitycreature.field_70761_aq;
      }

   }

   public void func_70645_a(DamageSource p_70645_1_) {
      super.func_70645_a(p_70645_1_);
      if(p_70645_1_.func_76364_f() instanceof EntityArrow && p_70645_1_.func_76346_g() instanceof EntityPlayer) {
         EntityPlayer entityplayer = (EntityPlayer)p_70645_1_.func_76346_g();
         double d0 = entityplayer.field_70165_t - this.field_70165_t;
         double d1 = entityplayer.field_70161_v - this.field_70161_v;
         if(d0 * d0 + d1 * d1 >= 2500.0D) {
            entityplayer.func_71029_a(AchievementList.field_187994_v);
         }
      } else if(p_70645_1_.func_76346_g() instanceof EntityCreeper && ((EntityCreeper)p_70645_1_.func_76346_g()).func_70830_n() && ((EntityCreeper)p_70645_1_.func_76346_g()).func_70650_aV()) {
         ((EntityCreeper)p_70645_1_.func_76346_g()).func_175493_co();
         this.func_70099_a(new ItemStack(Items.field_151144_bL, 1, this.func_82202_m() == 1?1:0), 0.0F);
      }

   }

   protected ResourceLocation func_184647_J() {
      return this.func_82202_m() == 1?LootTableList.field_186386_ak:LootTableList.field_186385_aj;
   }

   protected void func_180481_a(DifficultyInstance p_180481_1_) {
      super.func_180481_a(p_180481_1_);
      this.func_184201_a(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.field_151031_f));
   }

   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
      p_180482_2_ = super.func_180482_a(p_180482_1_, p_180482_2_);
      if(this.field_70170_p.field_73011_w instanceof WorldProviderHell && this.func_70681_au().nextInt(5) > 0) {
         this.field_70714_bg.func_75776_a(4, this.field_85038_e);
         this.func_82201_a(1);
         this.func_184201_a(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.field_151052_q));
         this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0D);
      } else {
         this.field_70714_bg.func_75776_a(4, this.field_85037_d);
         this.func_180481_a(p_180482_1_);
         this.func_180483_b(p_180482_1_);
      }

      this.func_98053_h(this.field_70146_Z.nextFloat() < 0.55F * p_180482_1_.func_180170_c());
      if(this.func_184582_a(EntityEquipmentSlot.HEAD) == null) {
         Calendar calendar = this.field_70170_p.func_83015_S();
         if(calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.field_70146_Z.nextFloat() < 0.25F) {
            this.func_184201_a(EntityEquipmentSlot.HEAD, new ItemStack(this.field_70146_Z.nextFloat() < 0.1F?Blocks.field_150428_aP:Blocks.field_150423_aK));
            this.field_184655_bs[EntityEquipmentSlot.HEAD.func_188454_b()] = 0.0F;
         }
      }

      return p_180482_2_;
   }

   public void func_85036_m() {
      if(this.field_70170_p != null && !this.field_70170_p.field_72995_K) {
         this.field_70714_bg.func_85156_a(this.field_85038_e);
         this.field_70714_bg.func_85156_a(this.field_85037_d);
         ItemStack itemstack = this.func_184614_ca();
         if(itemstack != null && itemstack.func_77973_b() == Items.field_151031_f) {
            this.field_70714_bg.func_75776_a(4, this.field_85037_d);
         } else {
            this.field_70714_bg.func_75776_a(4, this.field_85038_e);
         }
      }

   }

   public void func_82196_d(EntityLivingBase p_82196_1_, float p_82196_2_) {
      EntityArrow entityarrow = new EntityTippedArrow(this.field_70170_p, this);
      double d0 = p_82196_1_.field_70165_t - this.field_70165_t;
      double d1 = p_82196_1_.func_174813_aQ().field_72338_b + (double)(p_82196_1_.field_70131_O / 3.0F) - entityarrow.field_70163_u;
      double d2 = p_82196_1_.field_70161_v - this.field_70161_v;
      double d3 = (double)MathHelper.func_76133_a(d0 * d0 + d2 * d2);
      entityarrow.func_70186_c(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.field_70170_p.func_175659_aa().func_151525_a() * 4));
      int i = EnchantmentHelper.func_185284_a(Enchantments.field_185309_u, this);
      int j = EnchantmentHelper.func_185284_a(Enchantments.field_185310_v, this);
      entityarrow.func_70239_b((double)(p_82196_2_ * 2.0F) + this.field_70146_Z.nextGaussian() * 0.25D + (double)((float)this.field_70170_p.func_175659_aa().func_151525_a() * 0.11F));
      if(i > 0) {
         entityarrow.func_70239_b(entityarrow.func_70242_d() + (double)i * 0.5D + 0.5D);
      }

      if(j > 0) {
         entityarrow.func_70240_a(j);
      }

      if(EnchantmentHelper.func_185284_a(Enchantments.field_185311_w, this) > 0 || this.func_82202_m() == 1) {
         entityarrow.func_70015_d(100);
      }

      this.func_184185_a(SoundEvents.field_187866_fi, 1.0F, 1.0F / (this.func_70681_au().nextFloat() * 0.4F + 0.8F));
      this.field_70170_p.func_72838_d(entityarrow);
   }

   public int func_82202_m() {
      return ((Integer)this.field_70180_af.func_187225_a(field_184727_a)).intValue();
   }

   public void func_82201_a(int p_82201_1_) {
      this.field_70180_af.func_187227_b(field_184727_a, Integer.valueOf(p_82201_1_));
      this.field_70178_ae = p_82201_1_ == 1;
      this.func_184726_b(p_82201_1_);
   }

   private void func_184726_b(int p_184726_1_) {
      if(p_184726_1_ == 1) {
         this.func_70105_a(0.7F, 2.4F);
      } else {
         this.func_70105_a(0.6F, 1.99F);
      }

   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      if(p_70037_1_.func_150297_b("SkeletonType", 99)) {
         int i = p_70037_1_.func_74771_c("SkeletonType");
         this.func_82201_a(i);
      }

      this.func_85036_m();
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      p_70014_1_.func_74774_a("SkeletonType", (byte)this.func_82202_m());
   }

   public void func_184201_a(EntityEquipmentSlot p_184201_1_, ItemStack p_184201_2_) {
      super.func_184201_a(p_184201_1_, p_184201_2_);
      if(!this.field_70170_p.field_72995_K && p_184201_1_ == EntityEquipmentSlot.MAINHAND) {
         this.func_85036_m();
      }

   }

   public float func_70047_e() {
      return this.func_82202_m() == 1?2.1F:1.74F;
   }

   public double func_70033_W() {
      return -0.35D;
   }

   public boolean func_184725_db() {
      return ((Boolean)this.field_70180_af.func_187225_a(field_184728_b)).booleanValue();
   }

   public void func_184724_a(boolean p_184724_1_) {
      this.field_70180_af.func_187227_b(field_184728_b, Boolean.valueOf(p_184724_1_));
   }
}
