package net.minecraft.entity.passive;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IJumpingMount;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIRunAroundLikeCrazy;
import net.minecraft.entity.ai.EntityAISkeletonRiders;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.HorseArmorType;
import net.minecraft.entity.passive.HorseType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.AnimalChest;
import net.minecraft.inventory.IInvBasic;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityHorse extends EntityAnimal implements IInvBasic, IJumpingMount {
   private static final Predicate<Entity> field_110276_bu = new Predicate<Entity>() {
      public boolean apply(Entity p_apply_1_) {
         return p_apply_1_ instanceof EntityHorse && ((EntityHorse)p_apply_1_).func_110205_ce();
      }
   };
   private static final IAttribute field_110271_bv = (new RangedAttribute((IAttribute)null, "horse.jumpStrength", 0.7D, 0.0D, 2.0D)).func_111117_a("Jump Strength").func_111112_a(true);
   private static final UUID field_184786_bD = UUID.fromString("556E1665-8B10-40C8-8F9D-CF9B1667F295");
   private static final DataParameter<Byte> field_184787_bE = EntityDataManager.<Byte>func_187226_a(EntityHorse.class, DataSerializers.field_187191_a);
   private static final DataParameter<Integer> field_184788_bF = EntityDataManager.<Integer>func_187226_a(EntityHorse.class, DataSerializers.field_187192_b);
   private static final DataParameter<Integer> field_184789_bG = EntityDataManager.<Integer>func_187226_a(EntityHorse.class, DataSerializers.field_187192_b);
   private static final DataParameter<Optional<UUID>> field_184790_bH = EntityDataManager.<Optional<UUID>>func_187226_a(EntityHorse.class, DataSerializers.field_187203_m);
   private static final DataParameter<Integer> field_184791_bI = EntityDataManager.<Integer>func_187226_a(EntityHorse.class, DataSerializers.field_187192_b);
   private static final String[] field_110268_bz = new String[]{"textures/entity/horse/horse_white.png", "textures/entity/horse/horse_creamy.png", "textures/entity/horse/horse_chestnut.png", "textures/entity/horse/horse_brown.png", "textures/entity/horse/horse_black.png", "textures/entity/horse/horse_gray.png", "textures/entity/horse/horse_darkbrown.png"};
   private static final String[] field_110269_bA = new String[]{"hwh", "hcr", "hch", "hbr", "hbl", "hgr", "hdb"};
   private static final String[] field_110291_bB = new String[]{null, "textures/entity/horse/horse_markings_white.png", "textures/entity/horse/horse_markings_whitefield.png", "textures/entity/horse/horse_markings_whitedots.png", "textures/entity/horse/horse_markings_blackdots.png"};
   private static final String[] field_110292_bC = new String[]{"", "wo_", "wmo", "wdo", "bdo"};
   private final EntityAISkeletonRiders field_184792_bN = new EntityAISkeletonRiders(this);
   private int field_110289_bD;
   private int field_110290_bE;
   private int field_110295_bF;
   public int field_110278_bp;
   public int field_110279_bq;
   protected boolean field_110275_br;
   private AnimalChest field_110296_bG;
   private boolean field_110293_bH;
   protected int field_110274_bs;
   protected float field_110277_bt;
   private boolean field_110294_bI;
   private boolean field_184793_bU;
   private int field_184794_bV = 0;
   private float field_110283_bJ;
   private float field_110284_bK;
   private float field_110281_bL;
   private float field_110282_bM;
   private float field_110287_bN;
   private float field_110288_bO;
   private int field_110285_bP;
   private String field_110286_bQ;
   private String[] field_110280_bR = new String[3];
   private boolean field_175508_bO = false;

   public EntityHorse(World p_i1685_1_) {
      super(p_i1685_1_);
      this.func_70105_a(1.3964844F, 1.6F);
      this.field_70178_ae = false;
      this.func_110207_m(false);
      this.func_110226_cD();
   }

   protected void func_184651_r() {
      this.field_70714_bg.func_75776_a(0, new EntityAISwimming(this));
      this.field_70714_bg.func_75776_a(1, new EntityAIPanic(this, 1.2D));
      this.field_70714_bg.func_75776_a(1, new EntityAIRunAroundLikeCrazy(this, 1.2D));
      this.field_70714_bg.func_75776_a(2, new EntityAIMate(this, 1.0D));
      this.field_70714_bg.func_75776_a(4, new EntityAIFollowParent(this, 1.0D));
      this.field_70714_bg.func_75776_a(6, new EntityAIWander(this, 0.7D));
      this.field_70714_bg.func_75776_a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
      this.field_70714_bg.func_75776_a(8, new EntityAILookIdle(this));
   }

   protected void func_70088_a() {
      super.func_70088_a();
      this.field_70180_af.func_187214_a(field_184787_bE, Byte.valueOf((byte)0));
      this.field_70180_af.func_187214_a(field_184788_bF, Integer.valueOf(HorseArmorType.HORSE.func_188595_k()));
      this.field_70180_af.func_187214_a(field_184789_bG, Integer.valueOf(0));
      this.field_70180_af.func_187214_a(field_184790_bH, Optional.<T>absent());
      this.field_70180_af.func_187214_a(field_184791_bI, Integer.valueOf(HorseType.NONE.func_188579_a()));
   }

   public void func_184778_a(HorseArmorType p_184778_1_) {
      this.field_70180_af.func_187227_b(field_184788_bF, Integer.valueOf(p_184778_1_.func_188595_k()));
      this.func_110230_cF();
   }

   public HorseArmorType func_184781_cZ() {
      return HorseArmorType.func_188591_a(((Integer)this.field_70180_af.func_187225_a(field_184788_bF)).intValue());
   }

   public void func_110235_q(int p_110235_1_) {
      this.field_70180_af.func_187227_b(field_184789_bG, Integer.valueOf(p_110235_1_));
      this.func_110230_cF();
   }

   public int func_110202_bQ() {
      return ((Integer)this.field_70180_af.func_187225_a(field_184789_bG)).intValue();
   }

   public String func_70005_c_() {
      return this.func_145818_k_()?this.func_95999_t():this.func_184781_cZ().func_188596_d().func_150260_c();
   }

   private boolean func_110233_w(int p_110233_1_) {
      return (((Byte)this.field_70180_af.func_187225_a(field_184787_bE)).byteValue() & p_110233_1_) != 0;
   }

   private void func_110208_b(int p_110208_1_, boolean p_110208_2_) {
      byte b0 = ((Byte)this.field_70180_af.func_187225_a(field_184787_bE)).byteValue();
      if(p_110208_2_) {
         this.field_70180_af.func_187227_b(field_184787_bE, Byte.valueOf((byte)(b0 | p_110208_1_)));
      } else {
         this.field_70180_af.func_187227_b(field_184787_bE, Byte.valueOf((byte)(b0 & ~p_110208_1_)));
      }

   }

   public boolean func_110228_bR() {
      return !this.func_70631_g_();
   }

   public boolean func_110248_bS() {
      return this.func_110233_w(2);
   }

   public boolean func_110253_bW() {
      return this.func_110228_bR();
   }

   public UUID func_184780_dh() {
      return (UUID)((Optional)this.field_70180_af.func_187225_a(field_184790_bH)).orNull();
   }

   public void func_184779_b(UUID p_184779_1_) {
      this.field_70180_af.func_187227_b(field_184790_bH, Optional.<T>fromNullable(p_184779_1_));
   }

   public float func_110254_bY() {
      return 0.5F;
   }

   public void func_98054_a(boolean p_98054_1_) {
      if(p_98054_1_) {
         this.func_98055_j(this.func_110254_bY());
      } else {
         this.func_98055_j(1.0F);
      }

   }

   public boolean func_110246_bZ() {
      return this.field_110275_br;
   }

   public void func_110234_j(boolean p_110234_1_) {
      this.func_110208_b(2, p_110234_1_);
   }

   public void func_110255_k(boolean p_110255_1_) {
      this.field_110275_br = p_110255_1_;
   }

   public boolean func_184652_a(EntityPlayer p_184652_1_) {
      return !this.func_184781_cZ().func_188602_h() && super.func_184652_a(p_184652_1_);
   }

   protected void func_142017_o(float p_142017_1_) {
      if(p_142017_1_ > 6.0F && this.func_110204_cc()) {
         this.func_110227_p(false);
      }

   }

   public boolean func_110261_ca() {
      return this.func_184781_cZ().func_188600_f() && this.func_110233_w(8);
   }

   public HorseType func_184783_dl() {
      return HorseType.func_188575_a(((Integer)this.field_70180_af.func_187225_a(field_184791_bI)).intValue());
   }

   public boolean func_110204_cc() {
      return this.func_110233_w(32);
   }

   public boolean func_110209_cd() {
      return this.func_110233_w(64);
   }

   public boolean func_110205_ce() {
      return this.func_110233_w(16);
   }

   public boolean func_110243_cf() {
      return this.field_110293_bH;
   }

   public void func_146086_d(ItemStack p_146086_1_) {
      HorseType horsetype = HorseType.func_188580_a(p_146086_1_);
      this.field_70180_af.func_187227_b(field_184791_bI, Integer.valueOf(horsetype.func_188579_a()));
      this.func_110230_cF();
      if(!this.field_70170_p.field_72995_K) {
         this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_188479_b(field_184786_bD);
         int i = horsetype.func_188578_c();
         if(i != 0) {
            this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111121_a((new AttributeModifier(field_184786_bD, "Horse armor bonus", (double)i, 0)).func_111168_a(false));
         }
      }

   }

   public void func_110242_l(boolean p_110242_1_) {
      this.func_110208_b(16, p_110242_1_);
   }

   public void func_110207_m(boolean p_110207_1_) {
      this.func_110208_b(8, p_110207_1_);
   }

   public void func_110221_n(boolean p_110221_1_) {
      this.field_110293_bH = p_110221_1_;
   }

   public void func_110251_o(boolean p_110251_1_) {
      this.func_110208_b(4, p_110251_1_);
   }

   public int func_110252_cg() {
      return this.field_110274_bs;
   }

   public void func_110238_s(int p_110238_1_) {
      this.field_110274_bs = p_110238_1_;
   }

   public int func_110198_t(int p_110198_1_) {
      int i = MathHelper.func_76125_a(this.func_110252_cg() + p_110198_1_, 0, this.func_110218_cm());
      this.func_110238_s(i);
      return i;
   }

   public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
      Entity entity = p_70097_1_.func_76346_g();
      return this.func_184207_aI() && entity != null && this.func_184215_y(entity)?false:super.func_70097_a(p_70097_1_, p_70097_2_);
   }

   public boolean func_70104_M() {
      return !this.func_184207_aI();
   }

   public boolean func_110262_ch() {
      int i = MathHelper.func_76128_c(this.field_70165_t);
      int j = MathHelper.func_76128_c(this.field_70161_v);
      this.field_70170_p.func_180494_b(new BlockPos(i, 0, j));
      return true;
   }

   public void func_110224_ci() {
      if(!this.field_70170_p.field_72995_K && this.func_110261_ca()) {
         this.func_145779_a(Item.func_150898_a(Blocks.field_150486_ae), 1);
         this.func_110207_m(false);
      }
   }

   private void func_110266_cB() {
      this.func_110249_cI();
      if(!this.func_174814_R()) {
         this.field_70170_p.func_184148_a((EntityPlayer)null, this.field_70165_t, this.field_70163_u, this.field_70161_v, SoundEvents.field_187711_cp, this.func_184176_by(), 1.0F, 1.0F + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F);
      }

   }

   public void func_180430_e(float p_180430_1_, float p_180430_2_) {
      if(p_180430_1_ > 1.0F) {
         this.func_184185_a(SoundEvents.field_187723_ct, 0.4F, 1.0F);
      }

      int i = MathHelper.func_76123_f((p_180430_1_ * 0.5F - 3.0F) * p_180430_2_);
      if(i > 0) {
         this.func_70097_a(DamageSource.field_76379_h, (float)i);
         if(this.func_184207_aI()) {
            for(Entity entity : this.func_184182_bu()) {
               entity.func_70097_a(DamageSource.field_76379_h, (float)i);
            }
         }

         IBlockState iblockstate = this.field_70170_p.func_180495_p(new BlockPos(this.field_70165_t, this.field_70163_u - 0.2D - (double)this.field_70126_B, this.field_70161_v));
         Block block = iblockstate.func_177230_c();
         if(iblockstate.func_185904_a() != Material.field_151579_a && !this.func_174814_R()) {
            SoundType soundtype = block.func_185467_w();
            this.field_70170_p.func_184148_a((EntityPlayer)null, this.field_70165_t, this.field_70163_u, this.field_70161_v, soundtype.func_185844_d(), this.func_184176_by(), soundtype.func_185843_a() * 0.5F, soundtype.func_185847_b() * 0.75F);
         }

      }
   }

   private int func_110225_cC() {
      HorseArmorType horsearmortype = this.func_184781_cZ();
      return this.func_110261_ca() && horsearmortype.func_188600_f()?17:2;
   }

   private void func_110226_cD() {
      AnimalChest animalchest = this.field_110296_bG;
      this.field_110296_bG = new AnimalChest("HorseChest", this.func_110225_cC());
      this.field_110296_bG.func_110133_a(this.func_70005_c_());
      if(animalchest != null) {
         animalchest.func_110132_b(this);
         int i = Math.min(animalchest.func_70302_i_(), this.field_110296_bG.func_70302_i_());

         for(int j = 0; j < i; ++j) {
            ItemStack itemstack = animalchest.func_70301_a(j);
            if(itemstack != null) {
               this.field_110296_bG.func_70299_a(j, itemstack.func_77946_l());
            }
         }
      }

      this.field_110296_bG.func_110134_a(this);
      this.func_110232_cE();
   }

   private void func_110232_cE() {
      if(!this.field_70170_p.field_72995_K) {
         this.func_110251_o(this.field_110296_bG.func_70301_a(0) != null);
         if(this.func_184781_cZ().func_188603_j()) {
            this.func_146086_d(this.field_110296_bG.func_70301_a(1));
         }
      }

   }

   public void func_76316_a(InventoryBasic p_76316_1_) {
      HorseType horsetype = this.func_184783_dl();
      boolean flag = this.func_110257_ck();
      this.func_110232_cE();
      if(this.field_70173_aa > 20) {
         if(horsetype == HorseType.NONE && horsetype != this.func_184783_dl()) {
            this.func_184185_a(SoundEvents.field_187702_cm, 0.5F, 1.0F);
         } else if(horsetype != this.func_184783_dl()) {
            this.func_184185_a(SoundEvents.field_187702_cm, 0.5F, 1.0F);
         }

         if(!flag && this.func_110257_ck()) {
            this.func_184185_a(SoundEvents.field_187726_cu, 0.5F, 1.0F);
         }
      }

   }

   public boolean func_70601_bi() {
      this.func_110262_ch();
      return super.func_70601_bi();
   }

   protected EntityHorse func_110250_a(Entity p_110250_1_, double p_110250_2_) {
      double d0 = Double.MAX_VALUE;
      Entity entity = null;

      for(Entity entity1 : this.field_70170_p.func_175674_a(p_110250_1_, p_110250_1_.func_174813_aQ().func_72321_a(p_110250_2_, p_110250_2_, p_110250_2_), field_110276_bu)) {
         double d1 = entity1.func_70092_e(p_110250_1_.field_70165_t, p_110250_1_.field_70163_u, p_110250_1_.field_70161_v);
         if(d1 < d0) {
            entity = entity1;
            d0 = d1;
         }
      }

      return (EntityHorse)entity;
   }

   public double func_110215_cj() {
      return this.func_110148_a(field_110271_bv).func_111126_e();
   }

   protected SoundEvent func_184615_bR() {
      this.func_110249_cI();
      return this.func_184781_cZ().func_188593_c();
   }

   protected SoundEvent func_184601_bQ() {
      this.func_110249_cI();
      if(this.field_70146_Z.nextInt(3) == 0) {
         this.func_110220_cK();
      }

      return this.func_184781_cZ().func_188597_b();
   }

   public boolean func_110257_ck() {
      return this.func_110233_w(4);
   }

   protected SoundEvent func_184639_G() {
      this.func_110249_cI();
      if(this.field_70146_Z.nextInt(10) == 0 && !this.func_70610_aX()) {
         this.func_110220_cK();
      }

      return this.func_184781_cZ().func_188599_a();
   }

   protected SoundEvent func_184785_dv() {
      this.func_110249_cI();
      this.func_110220_cK();
      HorseArmorType horsearmortype = this.func_184781_cZ();
      return horsearmortype.func_188602_h()?null:(horsearmortype.func_188601_g()?SoundEvents.field_187582_aw:SoundEvents.field_187699_cl);
   }

   protected void func_180429_a(BlockPos p_180429_1_, Block p_180429_2_) {
      SoundType soundtype = p_180429_2_.func_185467_w();
      if(this.field_70170_p.func_180495_p(p_180429_1_.func_177984_a()).func_177230_c() == Blocks.field_150431_aC) {
         soundtype = Blocks.field_150431_aC.func_185467_w();
      }

      if(!p_180429_2_.func_176223_P().func_185904_a().func_76224_d()) {
         HorseArmorType horsearmortype = this.func_184781_cZ();
         if(this.func_184207_aI() && !horsearmortype.func_188601_g()) {
            ++this.field_110285_bP;
            if(this.field_110285_bP > 5 && this.field_110285_bP % 3 == 0) {
               this.func_184185_a(SoundEvents.field_187714_cq, soundtype.func_185843_a() * 0.15F, soundtype.func_185847_b());
               if(horsearmortype == HorseArmorType.HORSE && this.field_70146_Z.nextInt(10) == 0) {
                  this.func_184185_a(SoundEvents.field_187705_cn, soundtype.func_185843_a() * 0.6F, soundtype.func_185847_b());
               }
            } else if(this.field_110285_bP <= 5) {
               this.func_184185_a(SoundEvents.field_187732_cw, soundtype.func_185843_a() * 0.15F, soundtype.func_185847_b());
            }
         } else if(soundtype == SoundType.field_185848_a) {
            this.func_184185_a(SoundEvents.field_187732_cw, soundtype.func_185843_a() * 0.15F, soundtype.func_185847_b());
         } else {
            this.func_184185_a(SoundEvents.field_187729_cv, soundtype.func_185843_a() * 0.15F, soundtype.func_185847_b());
         }
      }

   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110140_aT().func_111150_b(field_110271_bv);
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(53.0D);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.22499999403953552D);
   }

   public int func_70641_bl() {
      return 6;
   }

   public int func_110218_cm() {
      return 100;
   }

   protected float func_70599_aP() {
      return 0.8F;
   }

   public int func_70627_aG() {
      return 400;
   }

   public boolean func_110239_cn() {
      return this.func_184781_cZ() == HorseArmorType.HORSE || this.func_184783_dl() != HorseType.NONE;
   }

   private void func_110230_cF() {
      this.field_110286_bQ = null;
   }

   public boolean func_175507_cI() {
      return this.field_175508_bO;
   }

   private void func_110247_cG() {
      this.field_110286_bQ = "horse/";
      this.field_110280_bR[0] = null;
      this.field_110280_bR[1] = null;
      this.field_110280_bR[2] = null;
      HorseArmorType horsearmortype = this.func_184781_cZ();
      int i = this.func_110202_bQ();
      if(horsearmortype == HorseArmorType.HORSE) {
         int j = i & 255;
         int k = (i & '\uff00') >> 8;
         if(j >= field_110268_bz.length) {
            this.field_175508_bO = false;
            return;
         }

         this.field_110280_bR[0] = field_110268_bz[j];
         this.field_110286_bQ = this.field_110286_bQ + field_110269_bA[j];
         if(k >= field_110291_bB.length) {
            this.field_175508_bO = false;
            return;
         }

         this.field_110280_bR[1] = field_110291_bB[k];
         this.field_110286_bQ = this.field_110286_bQ + field_110292_bC[k];
      } else {
         this.field_110280_bR[0] = "";
         this.field_110286_bQ = this.field_110286_bQ + "_" + horsearmortype + "_";
      }

      HorseType horsetype = this.func_184783_dl();
      this.field_110280_bR[2] = horsetype.func_188574_d();
      this.field_110286_bQ = this.field_110286_bQ + horsetype.func_188573_b();
      this.field_175508_bO = true;
   }

   public String func_110264_co() {
      if(this.field_110286_bQ == null) {
         this.func_110247_cG();
      }

      return this.field_110286_bQ;
   }

   public String[] func_110212_cp() {
      if(this.field_110286_bQ == null) {
         this.func_110247_cG();
      }

      return this.field_110280_bR;
   }

   public void func_110199_f(EntityPlayer p_110199_1_) {
      if(!this.field_70170_p.field_72995_K && (!this.func_184207_aI() || this.func_184196_w(p_110199_1_)) && this.func_110248_bS()) {
         this.field_110296_bG.func_110133_a(this.func_70005_c_());
         p_110199_1_.func_184826_a(this, this.field_110296_bG);
      }

   }

   public boolean func_184645_a(EntityPlayer p_184645_1_, EnumHand p_184645_2_, ItemStack p_184645_3_) {
      if(p_184645_3_ != null && p_184645_3_.func_77973_b() == Items.field_151063_bx) {
         return super.func_184645_a(p_184645_1_, p_184645_2_, p_184645_3_);
      } else if(!this.func_110248_bS() && this.func_184781_cZ().func_188602_h()) {
         return false;
      } else if(this.func_110248_bS() && this.func_110228_bR() && p_184645_1_.func_70093_af()) {
         this.func_110199_f(p_184645_1_);
         return true;
      } else if(this.func_110253_bW() && this.func_184207_aI()) {
         return super.func_184645_a(p_184645_1_, p_184645_2_, p_184645_3_);
      } else {
         if(p_184645_3_ != null) {
            if(this.func_184781_cZ().func_188603_j()) {
               HorseType horsetype = HorseType.func_188580_a(p_184645_3_);
               if(horsetype != HorseType.NONE) {
                  if(!this.func_110248_bS()) {
                     this.func_110231_cz();
                     return true;
                  }

                  this.func_110199_f(p_184645_1_);
                  return true;
               }
            }

            boolean flag = false;
            if(!this.func_184781_cZ().func_188602_h()) {
               float f = 0.0F;
               int i = 0;
               int j = 0;
               if(p_184645_3_.func_77973_b() == Items.field_151015_O) {
                  f = 2.0F;
                  i = 20;
                  j = 3;
               } else if(p_184645_3_.func_77973_b() == Items.field_151102_aT) {
                  f = 1.0F;
                  i = 30;
                  j = 3;
               } else if(Block.func_149634_a(p_184645_3_.func_77973_b()) == Blocks.field_150407_cf) {
                  f = 20.0F;
                  i = 180;
               } else if(p_184645_3_.func_77973_b() == Items.field_151034_e) {
                  f = 3.0F;
                  i = 60;
                  j = 3;
               } else if(p_184645_3_.func_77973_b() == Items.field_151150_bK) {
                  f = 4.0F;
                  i = 60;
                  j = 5;
                  if(this.func_110248_bS() && this.func_70874_b() == 0) {
                     flag = true;
                     this.func_146082_f(p_184645_1_);
                  }
               } else if(p_184645_3_.func_77973_b() == Items.field_151153_ao) {
                  f = 10.0F;
                  i = 240;
                  j = 10;
                  if(this.func_110248_bS() && this.func_70874_b() == 0 && !this.func_70880_s()) {
                     flag = true;
                     this.func_146082_f(p_184645_1_);
                  }
               }

               if(this.func_110143_aJ() < this.func_110138_aP() && f > 0.0F) {
                  this.func_70691_i(f);
                  flag = true;
               }

               if(!this.func_110228_bR() && i > 0) {
                  if(!this.field_70170_p.field_72995_K) {
                     this.func_110195_a(i);
                  }

                  flag = true;
               }

               if(j > 0 && (flag || !this.func_110248_bS()) && this.func_110252_cg() < this.func_110218_cm()) {
                  flag = true;
                  if(!this.field_70170_p.field_72995_K) {
                     this.func_110198_t(j);
                  }
               }

               if(flag) {
                  this.func_110266_cB();
               }
            }

            if(!this.func_110248_bS() && !flag) {
               if(p_184645_3_.func_111282_a(p_184645_1_, this, p_184645_2_)) {
                  return true;
               }

               this.func_110231_cz();
               return true;
            }

            if(!flag && this.func_184781_cZ().func_188600_f() && !this.func_110261_ca() && p_184645_3_.func_77973_b() == Item.func_150898_a(Blocks.field_150486_ae)) {
               this.func_110207_m(true);
               this.func_184185_a(SoundEvents.field_187584_ax, 1.0F, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F);
               flag = true;
               this.func_110226_cD();
            }

            if(!flag && this.func_110253_bW() && !this.func_110257_ck() && p_184645_3_.func_77973_b() == Items.field_151141_av) {
               this.func_110199_f(p_184645_1_);
               return true;
            }

            if(flag) {
               if(!p_184645_1_.field_71075_bZ.field_75098_d) {
                  --p_184645_3_.field_77994_a;
               }

               return true;
            }
         }

         if(this.func_110253_bW() && !this.func_184207_aI()) {
            if(p_184645_3_ != null && p_184645_3_.func_111282_a(p_184645_1_, this, p_184645_2_)) {
               return true;
            } else {
               this.func_110237_h(p_184645_1_);
               return true;
            }
         } else {
            return super.func_184645_a(p_184645_1_, p_184645_2_, p_184645_3_);
         }
      }
   }

   private void func_110237_h(EntityPlayer p_110237_1_) {
      p_110237_1_.field_70177_z = this.field_70177_z;
      p_110237_1_.field_70125_A = this.field_70125_A;
      this.func_110227_p(false);
      this.func_110219_q(false);
      if(!this.field_70170_p.field_72995_K) {
         p_110237_1_.func_184220_m(this);
      }

   }

   protected boolean func_70610_aX() {
      return this.func_184207_aI() && this.func_110257_ck()?true:this.func_110204_cc() || this.func_110209_cd();
   }

   public boolean func_70877_b(ItemStack p_70877_1_) {
      return false;
   }

   private void func_110210_cH() {
      this.field_110278_bp = 1;
   }

   public void func_70645_a(DamageSource p_70645_1_) {
      super.func_70645_a(p_70645_1_);
      if(!this.field_70170_p.field_72995_K) {
         this.func_110244_cA();
      }

   }

   public void func_70636_d() {
      if(this.field_70146_Z.nextInt(200) == 0) {
         this.func_110210_cH();
      }

      super.func_70636_d();
      if(!this.field_70170_p.field_72995_K) {
         if(this.field_70146_Z.nextInt(900) == 0 && this.field_70725_aQ == 0) {
            this.func_70691_i(1.0F);
         }

         if(!this.func_110204_cc() && !this.func_184207_aI() && this.field_70146_Z.nextInt(300) == 0 && this.field_70170_p.func_180495_p(new BlockPos(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u) - 1, MathHelper.func_76128_c(this.field_70161_v))).func_177230_c() == Blocks.field_150349_c) {
            this.func_110227_p(true);
         }

         if(this.func_110204_cc() && ++this.field_110289_bD > 50) {
            this.field_110289_bD = 0;
            this.func_110227_p(false);
         }

         if(this.func_110205_ce() && !this.func_110228_bR() && !this.func_110204_cc()) {
            EntityHorse entityhorse = this.func_110250_a(this, 16.0D);
            if(entityhorse != null && this.func_70068_e(entityhorse) > 4.0D) {
               this.field_70699_by.func_75494_a(entityhorse);
            }
         }

         if(this.func_184782_dG() && this.field_184794_bV++ >= 18000) {
            this.func_70106_y();
         }
      }

   }

   public void func_70071_h_() {
      super.func_70071_h_();
      if(this.field_70170_p.field_72995_K && this.field_70180_af.func_187223_a()) {
         this.field_70180_af.func_187230_e();
         this.func_110230_cF();
      }

      if(this.field_110290_bE > 0 && ++this.field_110290_bE > 30) {
         this.field_110290_bE = 0;
         this.func_110208_b(128, false);
      }

      if(this.func_184186_bw() && this.field_110295_bF > 0 && ++this.field_110295_bF > 20) {
         this.field_110295_bF = 0;
         this.func_110219_q(false);
      }

      if(this.field_110278_bp > 0 && ++this.field_110278_bp > 8) {
         this.field_110278_bp = 0;
      }

      if(this.field_110279_bq > 0) {
         ++this.field_110279_bq;
         if(this.field_110279_bq > 300) {
            this.field_110279_bq = 0;
         }
      }

      this.field_110284_bK = this.field_110283_bJ;
      if(this.func_110204_cc()) {
         this.field_110283_bJ += (1.0F - this.field_110283_bJ) * 0.4F + 0.05F;
         if(this.field_110283_bJ > 1.0F) {
            this.field_110283_bJ = 1.0F;
         }
      } else {
         this.field_110283_bJ += (0.0F - this.field_110283_bJ) * 0.4F - 0.05F;
         if(this.field_110283_bJ < 0.0F) {
            this.field_110283_bJ = 0.0F;
         }
      }

      this.field_110282_bM = this.field_110281_bL;
      if(this.func_110209_cd()) {
         this.field_110284_bK = this.field_110283_bJ = 0.0F;
         this.field_110281_bL += (1.0F - this.field_110281_bL) * 0.4F + 0.05F;
         if(this.field_110281_bL > 1.0F) {
            this.field_110281_bL = 1.0F;
         }
      } else {
         this.field_110294_bI = false;
         this.field_110281_bL += (0.8F * this.field_110281_bL * this.field_110281_bL * this.field_110281_bL - this.field_110281_bL) * 0.6F - 0.05F;
         if(this.field_110281_bL < 0.0F) {
            this.field_110281_bL = 0.0F;
         }
      }

      this.field_110288_bO = this.field_110287_bN;
      if(this.func_110233_w(128)) {
         this.field_110287_bN += (1.0F - this.field_110287_bN) * 0.7F + 0.05F;
         if(this.field_110287_bN > 1.0F) {
            this.field_110287_bN = 1.0F;
         }
      } else {
         this.field_110287_bN += (0.0F - this.field_110287_bN) * 0.7F - 0.05F;
         if(this.field_110287_bN < 0.0F) {
            this.field_110287_bN = 0.0F;
         }
      }

   }

   private void func_110249_cI() {
      if(!this.field_70170_p.field_72995_K) {
         this.field_110290_bE = 1;
         this.func_110208_b(128, true);
      }

   }

   private boolean func_110200_cJ() {
      return !this.func_184207_aI() && !this.func_184218_aH() && this.func_110248_bS() && this.func_110228_bR() && this.func_184781_cZ().func_188590_i() && this.func_110143_aJ() >= this.func_110138_aP() && this.func_70880_s();
   }

   public void func_110227_p(boolean p_110227_1_) {
      this.func_110208_b(32, p_110227_1_);
   }

   public void func_110219_q(boolean p_110219_1_) {
      if(p_110219_1_) {
         this.func_110227_p(false);
      }

      this.func_110208_b(64, p_110219_1_);
   }

   private void func_110220_cK() {
      if(this.func_184186_bw()) {
         this.field_110295_bF = 1;
         this.func_110219_q(true);
      }

   }

   public void func_110231_cz() {
      this.func_110220_cK();
      SoundEvent soundevent = this.func_184785_dv();
      if(soundevent != null) {
         this.func_184185_a(soundevent, this.func_70599_aP(), this.func_70647_i());
      }

   }

   public void func_110244_cA() {
      this.func_110240_a(this, this.field_110296_bG);
      this.func_110224_ci();
   }

   private void func_110240_a(Entity p_110240_1_, AnimalChest p_110240_2_) {
      if(p_110240_2_ != null && !this.field_70170_p.field_72995_K) {
         for(int i = 0; i < p_110240_2_.func_70302_i_(); ++i) {
            ItemStack itemstack = p_110240_2_.func_70301_a(i);
            if(itemstack != null) {
               this.func_70099_a(itemstack, 0.0F);
            }
         }

      }
   }

   public boolean func_110263_g(EntityPlayer p_110263_1_) {
      this.func_184779_b(p_110263_1_.func_110124_au());
      this.func_110234_j(true);
      return true;
   }

   public void func_70612_e(float p_70612_1_, float p_70612_2_) {
      if(this.func_184207_aI() && this.func_82171_bF() && this.func_110257_ck()) {
         EntityLivingBase entitylivingbase = (EntityLivingBase)this.func_184179_bs();
         this.field_70126_B = this.field_70177_z = entitylivingbase.field_70177_z;
         this.field_70125_A = entitylivingbase.field_70125_A * 0.5F;
         this.func_70101_b(this.field_70177_z, this.field_70125_A);
         this.field_70759_as = this.field_70761_aq = this.field_70177_z;
         p_70612_1_ = entitylivingbase.field_70702_br * 0.5F;
         p_70612_2_ = entitylivingbase.field_70701_bs;
         if(p_70612_2_ <= 0.0F) {
            p_70612_2_ *= 0.25F;
            this.field_110285_bP = 0;
         }

         if(this.field_70122_E && this.field_110277_bt == 0.0F && this.func_110209_cd() && !this.field_110294_bI) {
            p_70612_1_ = 0.0F;
            p_70612_2_ = 0.0F;
         }

         if(this.field_110277_bt > 0.0F && !this.func_110246_bZ() && this.field_70122_E) {
            this.field_70181_x = this.func_110215_cj() * (double)this.field_110277_bt;
            if(this.func_70644_a(MobEffects.field_76430_j)) {
               this.field_70181_x += (double)((float)(this.func_70660_b(MobEffects.field_76430_j).func_76458_c() + 1) * 0.1F);
            }

            this.func_110255_k(true);
            this.field_70160_al = true;
            if(p_70612_2_ > 0.0F) {
               float f = MathHelper.func_76126_a(this.field_70177_z * 0.017453292F);
               float f1 = MathHelper.func_76134_b(this.field_70177_z * 0.017453292F);
               this.field_70159_w += (double)(-0.4F * f * this.field_110277_bt);
               this.field_70179_y += (double)(0.4F * f1 * this.field_110277_bt);
               this.func_184185_a(SoundEvents.field_187720_cs, 0.4F, 1.0F);
            }

            this.field_110277_bt = 0.0F;
         }

         this.field_70138_W = 1.0F;
         this.field_70747_aH = this.func_70689_ay() * 0.1F;
         if(this.func_184186_bw()) {
            this.func_70659_e((float)this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e());
            super.func_70612_e(p_70612_1_, p_70612_2_);
         } else if(entitylivingbase instanceof EntityPlayer) {
            this.field_70159_w = 0.0D;
            this.field_70181_x = 0.0D;
            this.field_70179_y = 0.0D;
         }

         if(this.field_70122_E) {
            this.field_110277_bt = 0.0F;
            this.func_110255_k(false);
         }

         this.field_184618_aE = this.field_70721_aZ;
         double d1 = this.field_70165_t - this.field_70169_q;
         double d0 = this.field_70161_v - this.field_70166_s;
         float f2 = MathHelper.func_76133_a(d1 * d1 + d0 * d0) * 4.0F;
         if(f2 > 1.0F) {
            f2 = 1.0F;
         }

         this.field_70721_aZ += (f2 - this.field_70721_aZ) * 0.4F;
         this.field_184619_aG += this.field_70721_aZ;
      } else {
         this.field_70138_W = 0.5F;
         this.field_70747_aH = 0.02F;
         super.func_70612_e(p_70612_1_, p_70612_2_);
      }
   }

   public void func_70014_b(NBTTagCompound p_70014_1_) {
      super.func_70014_b(p_70014_1_);
      p_70014_1_.func_74757_a("EatingHaystack", this.func_110204_cc());
      p_70014_1_.func_74757_a("ChestedHorse", this.func_110261_ca());
      p_70014_1_.func_74757_a("HasReproduced", this.func_110243_cf());
      p_70014_1_.func_74757_a("Bred", this.func_110205_ce());
      p_70014_1_.func_74768_a("Type", this.func_184781_cZ().func_188595_k());
      p_70014_1_.func_74768_a("Variant", this.func_110202_bQ());
      p_70014_1_.func_74768_a("Temper", this.func_110252_cg());
      p_70014_1_.func_74757_a("Tame", this.func_110248_bS());
      p_70014_1_.func_74757_a("SkeletonTrap", this.func_184782_dG());
      p_70014_1_.func_74768_a("SkeletonTrapTime", this.field_184794_bV);
      if(this.func_184780_dh() != null) {
         p_70014_1_.func_74778_a("OwnerUUID", this.func_184780_dh().toString());
      }

      if(this.func_110261_ca()) {
         NBTTagList nbttaglist = new NBTTagList();

         for(int i = 2; i < this.field_110296_bG.func_70302_i_(); ++i) {
            ItemStack itemstack = this.field_110296_bG.func_70301_a(i);
            if(itemstack != null) {
               NBTTagCompound nbttagcompound = new NBTTagCompound();
               nbttagcompound.func_74774_a("Slot", (byte)i);
               itemstack.func_77955_b(nbttagcompound);
               nbttaglist.func_74742_a(nbttagcompound);
            }
         }

         p_70014_1_.func_74782_a("Items", nbttaglist);
      }

      if(this.field_110296_bG.func_70301_a(1) != null) {
         p_70014_1_.func_74782_a("ArmorItem", this.field_110296_bG.func_70301_a(1).func_77955_b(new NBTTagCompound()));
      }

      if(this.field_110296_bG.func_70301_a(0) != null) {
         p_70014_1_.func_74782_a("SaddleItem", this.field_110296_bG.func_70301_a(0).func_77955_b(new NBTTagCompound()));
      }

   }

   public void func_70037_a(NBTTagCompound p_70037_1_) {
      super.func_70037_a(p_70037_1_);
      this.func_110227_p(p_70037_1_.func_74767_n("EatingHaystack"));
      this.func_110242_l(p_70037_1_.func_74767_n("Bred"));
      this.func_110207_m(p_70037_1_.func_74767_n("ChestedHorse"));
      this.func_110221_n(p_70037_1_.func_74767_n("HasReproduced"));
      this.func_184778_a(HorseArmorType.func_188591_a(p_70037_1_.func_74762_e("Type")));
      this.func_110235_q(p_70037_1_.func_74762_e("Variant"));
      this.func_110238_s(p_70037_1_.func_74762_e("Temper"));
      this.func_110234_j(p_70037_1_.func_74767_n("Tame"));
      this.func_184784_x(p_70037_1_.func_74767_n("SkeletonTrap"));
      this.field_184794_bV = p_70037_1_.func_74762_e("SkeletonTrapTime");
      String s = "";
      if(p_70037_1_.func_150297_b("OwnerUUID", 8)) {
         s = p_70037_1_.func_74779_i("OwnerUUID");
      } else {
         String s1 = p_70037_1_.func_74779_i("Owner");
         s = PreYggdrasilConverter.func_187473_a(this.func_184102_h(), s1);
      }

      if(!s.isEmpty()) {
         this.func_184779_b(UUID.fromString(s));
      }

      IAttributeInstance iattributeinstance = this.func_110140_aT().func_111152_a("Speed");
      if(iattributeinstance != null) {
         this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(iattributeinstance.func_111125_b() * 0.25D);
      }

      if(this.func_110261_ca()) {
         NBTTagList nbttaglist = p_70037_1_.func_150295_c("Items", 10);
         this.func_110226_cD();

         for(int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
            int j = nbttagcompound.func_74771_c("Slot") & 255;
            if(j >= 2 && j < this.field_110296_bG.func_70302_i_()) {
               this.field_110296_bG.func_70299_a(j, ItemStack.func_77949_a(nbttagcompound));
            }
         }
      }

      if(p_70037_1_.func_150297_b("ArmorItem", 10)) {
         ItemStack itemstack = ItemStack.func_77949_a(p_70037_1_.func_74775_l("ArmorItem"));
         if(itemstack != null && HorseType.func_188577_b(itemstack.func_77973_b())) {
            this.field_110296_bG.func_70299_a(1, itemstack);
         }
      }

      if(p_70037_1_.func_150297_b("SaddleItem", 10)) {
         ItemStack itemstack1 = ItemStack.func_77949_a(p_70037_1_.func_74775_l("SaddleItem"));
         if(itemstack1 != null && itemstack1.func_77973_b() == Items.field_151141_av) {
            this.field_110296_bG.func_70299_a(0, itemstack1);
         }
      }

      this.func_110232_cE();
   }

   public boolean func_70878_b(EntityAnimal p_70878_1_) {
      if(p_70878_1_ == this) {
         return false;
      } else if(p_70878_1_.getClass() != this.getClass()) {
         return false;
      } else {
         EntityHorse entityhorse = (EntityHorse)p_70878_1_;
         if(this.func_110200_cJ() && entityhorse.func_110200_cJ()) {
            HorseArmorType horsearmortype = this.func_184781_cZ();
            HorseArmorType horsearmortype1 = entityhorse.func_184781_cZ();
            return horsearmortype == horsearmortype1 || horsearmortype == HorseArmorType.HORSE && horsearmortype1 == HorseArmorType.DONKEY || horsearmortype == HorseArmorType.DONKEY && horsearmortype1 == HorseArmorType.HORSE;
         } else {
            return false;
         }
      }
   }

   public EntityAgeable func_90011_a(EntityAgeable p_90011_1_) {
      EntityHorse entityhorse = (EntityHorse)p_90011_1_;
      EntityHorse entityhorse1 = new EntityHorse(this.field_70170_p);
      HorseArmorType horsearmortype = this.func_184781_cZ();
      HorseArmorType horsearmortype1 = entityhorse.func_184781_cZ();
      HorseArmorType horsearmortype2 = HorseArmorType.HORSE;
      if(horsearmortype == horsearmortype1) {
         horsearmortype2 = horsearmortype;
      } else if(horsearmortype == HorseArmorType.HORSE && horsearmortype1 == HorseArmorType.DONKEY || horsearmortype == HorseArmorType.DONKEY && horsearmortype1 == HorseArmorType.HORSE) {
         horsearmortype2 = HorseArmorType.MULE;
      }

      if(horsearmortype2 == HorseArmorType.HORSE) {
         int j = this.field_70146_Z.nextInt(9);
         int i;
         if(j < 4) {
            i = this.func_110202_bQ() & 255;
         } else if(j < 8) {
            i = entityhorse.func_110202_bQ() & 255;
         } else {
            i = this.field_70146_Z.nextInt(7);
         }

         int k = this.field_70146_Z.nextInt(5);
         if(k < 2) {
            i = i | this.func_110202_bQ() & '\uff00';
         } else if(k < 4) {
            i = i | entityhorse.func_110202_bQ() & '\uff00';
         } else {
            i = i | this.field_70146_Z.nextInt(5) << 8 & '\uff00';
         }

         entityhorse1.func_110235_q(i);
      }

      entityhorse1.func_184778_a(horsearmortype2);
      double d1 = this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() + p_90011_1_.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() + (double)this.func_110267_cL();
      entityhorse1.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(d1 / 3.0D);
      double d2 = this.func_110148_a(field_110271_bv).func_111125_b() + p_90011_1_.func_110148_a(field_110271_bv).func_111125_b() + this.func_110245_cM();
      entityhorse1.func_110148_a(field_110271_bv).func_111128_a(d2 / 3.0D);
      double d0 = this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111125_b() + p_90011_1_.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111125_b() + this.func_110203_cN();
      entityhorse1.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(d0 / 3.0D);
      return entityhorse1;
   }

   public IEntityLivingData func_180482_a(DifficultyInstance p_180482_1_, IEntityLivingData p_180482_2_) {
      p_180482_2_ = super.func_180482_a(p_180482_1_, p_180482_2_);
      HorseArmorType horsearmortype = HorseArmorType.HORSE;
      int i = 0;
      if(p_180482_2_ instanceof EntityHorse.GroupData) {
         horsearmortype = ((EntityHorse.GroupData)p_180482_2_).field_188476_a;
         i = ((EntityHorse.GroupData)p_180482_2_).field_188477_b & 255 | this.field_70146_Z.nextInt(5) << 8;
      } else {
         if(this.field_70146_Z.nextInt(10) == 0) {
            horsearmortype = HorseArmorType.DONKEY;
         } else {
            int j = this.field_70146_Z.nextInt(7);
            int k = this.field_70146_Z.nextInt(5);
            horsearmortype = HorseArmorType.HORSE;
            i = j | k << 8;
         }

         p_180482_2_ = new EntityHorse.GroupData(horsearmortype, i);
      }

      this.func_184778_a(horsearmortype);
      this.func_110235_q(i);
      if(this.field_70146_Z.nextInt(5) == 0) {
         this.func_70873_a(-24000);
      }

      if(horsearmortype.func_188602_h()) {
         this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(15.0D);
         this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.20000000298023224D);
      } else {
         this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)this.func_110267_cL());
         if(horsearmortype == HorseArmorType.HORSE) {
            this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(this.func_110203_cN());
         } else {
            this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.17499999701976776D);
         }
      }

      if(horsearmortype.func_188601_g()) {
         this.func_110148_a(field_110271_bv).func_111128_a(0.5D);
      } else {
         this.func_110148_a(field_110271_bv).func_111128_a(this.func_110245_cM());
      }

      this.func_70606_j(this.func_110138_aP());
      return p_180482_2_;
   }

   public boolean func_82171_bF() {
      Entity entity = this.func_184179_bs();
      return entity instanceof EntityLivingBase;
   }

   public float func_110258_o(float p_110258_1_) {
      return this.field_110284_bK + (this.field_110283_bJ - this.field_110284_bK) * p_110258_1_;
   }

   public float func_110223_p(float p_110223_1_) {
      return this.field_110282_bM + (this.field_110281_bL - this.field_110282_bM) * p_110223_1_;
   }

   public float func_110201_q(float p_110201_1_) {
      return this.field_110288_bO + (this.field_110287_bN - this.field_110288_bO) * p_110201_1_;
   }

   public void func_110206_u(int p_110206_1_) {
      if(this.func_110257_ck()) {
         if(p_110206_1_ < 0) {
            p_110206_1_ = 0;
         } else {
            this.field_110294_bI = true;
            this.func_110220_cK();
         }

         if(p_110206_1_ >= 90) {
            this.field_110277_bt = 1.0F;
         } else {
            this.field_110277_bt = 0.4F + 0.4F * (float)p_110206_1_ / 90.0F;
         }
      }

   }

   public boolean func_184776_b() {
      return this.func_110257_ck();
   }

   public void func_184775_b(int p_184775_1_) {
      this.field_110294_bI = true;
      this.func_110220_cK();
   }

   public void func_184777_r_() {
   }

   protected void func_110216_r(boolean p_110216_1_) {
      EnumParticleTypes enumparticletypes = p_110216_1_?EnumParticleTypes.HEART:EnumParticleTypes.SMOKE_NORMAL;

      for(int i = 0; i < 7; ++i) {
         double d0 = this.field_70146_Z.nextGaussian() * 0.02D;
         double d1 = this.field_70146_Z.nextGaussian() * 0.02D;
         double d2 = this.field_70146_Z.nextGaussian() * 0.02D;
         this.field_70170_p.func_175688_a(enumparticletypes, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N, this.field_70163_u + 0.5D + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0F) - (double)this.field_70130_N, d0, d1, d2, new int[0]);
      }

   }

   public void func_70103_a(byte p_70103_1_) {
      if(p_70103_1_ == 7) {
         this.func_110216_r(true);
      } else if(p_70103_1_ == 6) {
         this.func_110216_r(false);
      } else {
         super.func_70103_a(p_70103_1_);
      }

   }

   public void func_184232_k(Entity p_184232_1_) {
      super.func_184232_k(p_184232_1_);
      if(p_184232_1_ instanceof EntityLiving) {
         EntityLiving entityliving = (EntityLiving)p_184232_1_;
         this.field_70761_aq = entityliving.field_70761_aq;
      }

      if(this.field_110282_bM > 0.0F) {
         float f3 = MathHelper.func_76126_a(this.field_70761_aq * 0.017453292F);
         float f = MathHelper.func_76134_b(this.field_70761_aq * 0.017453292F);
         float f1 = 0.7F * this.field_110282_bM;
         float f2 = 0.15F * this.field_110282_bM;
         p_184232_1_.func_70107_b(this.field_70165_t + (double)(f1 * f3), this.field_70163_u + this.func_70042_X() + p_184232_1_.func_70033_W() + (double)f2, this.field_70161_v - (double)(f1 * f));
         if(p_184232_1_ instanceof EntityLivingBase) {
            ((EntityLivingBase)p_184232_1_).field_70761_aq = this.field_70761_aq;
         }
      }

   }

   public double func_70042_X() {
      double d0 = super.func_70042_X();
      if(this.func_184781_cZ() == HorseArmorType.SKELETON) {
         d0 -= 0.1875D;
      } else if(this.func_184781_cZ() == HorseArmorType.DONKEY) {
         d0 -= 0.25D;
      }

      return d0;
   }

   private float func_110267_cL() {
      return 15.0F + (float)this.field_70146_Z.nextInt(8) + (float)this.field_70146_Z.nextInt(9);
   }

   private double func_110245_cM() {
      return 0.4000000059604645D + this.field_70146_Z.nextDouble() * 0.2D + this.field_70146_Z.nextDouble() * 0.2D + this.field_70146_Z.nextDouble() * 0.2D;
   }

   private double func_110203_cN() {
      return (0.44999998807907104D + this.field_70146_Z.nextDouble() * 0.3D + this.field_70146_Z.nextDouble() * 0.3D + this.field_70146_Z.nextDouble() * 0.3D) * 0.25D;
   }

   public boolean func_184782_dG() {
      return this.field_184793_bU;
   }

   public void func_184784_x(boolean p_184784_1_) {
      if(p_184784_1_ != this.field_184793_bU) {
         this.field_184793_bU = p_184784_1_;
         if(p_184784_1_) {
            this.field_70714_bg.func_75776_a(1, this.field_184792_bN);
         } else {
            this.field_70714_bg.func_85156_a(this.field_184792_bN);
         }
      }

   }

   public boolean func_70617_f_() {
      return false;
   }

   public float func_70047_e() {
      return this.field_70131_O;
   }

   public boolean func_174820_d(int p_174820_1_, ItemStack p_174820_2_) {
      if(p_174820_1_ == 499 && this.func_184781_cZ().func_188600_f()) {
         if(p_174820_2_ == null && this.func_110261_ca()) {
            this.func_110207_m(false);
            this.func_110226_cD();
            return true;
         }

         if(p_174820_2_ != null && p_174820_2_.func_77973_b() == Item.func_150898_a(Blocks.field_150486_ae) && !this.func_110261_ca()) {
            this.func_110207_m(true);
            this.func_110226_cD();
            return true;
         }
      }

      int i = p_174820_1_ - 400;
      if(i >= 0 && i < 2 && i < this.field_110296_bG.func_70302_i_()) {
         if(i == 0 && p_174820_2_ != null && p_174820_2_.func_77973_b() != Items.field_151141_av) {
            return false;
         } else if(i != 1 || (p_174820_2_ == null || HorseType.func_188577_b(p_174820_2_.func_77973_b())) && this.func_184781_cZ().func_188603_j()) {
            this.field_110296_bG.func_70299_a(i, p_174820_2_);
            this.func_110232_cE();
            return true;
         } else {
            return false;
         }
      } else {
         int j = p_174820_1_ - 500 + 2;
         if(j >= 2 && j < this.field_110296_bG.func_70302_i_()) {
            this.field_110296_bG.func_70299_a(j, p_174820_2_);
            return true;
         } else {
            return false;
         }
      }
   }

   public Entity func_184179_bs() {
      return this.func_184188_bt().isEmpty()?null:(Entity)this.func_184188_bt().get(0);
   }

   public EnumCreatureAttribute func_70668_bt() {
      return this.func_184781_cZ().func_188602_h()?EnumCreatureAttribute.UNDEAD:EnumCreatureAttribute.UNDEFINED;
   }

   protected ResourceLocation func_184647_J() {
      return this.func_184781_cZ().func_188598_l();
   }

   public static class GroupData implements IEntityLivingData {
      public HorseArmorType field_188476_a;
      public int field_188477_b;

      public GroupData(HorseArmorType p_i46589_1_, int p_i46589_2_) {
         this.field_188476_a = p_i46589_1_;
         this.field_188477_b = p_i46589_2_;
      }
   }
}
