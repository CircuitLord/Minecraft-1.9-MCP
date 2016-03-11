package net.minecraft.world.biome;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBeach;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenEnd;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.biome.BiomeGenForestMutated;
import net.minecraft.world.biome.BiomeGenHell;
import net.minecraft.world.biome.BiomeGenHills;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenMesa;
import net.minecraft.world.biome.BiomeGenMushroomIsland;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.biome.BiomeGenRiver;
import net.minecraft.world.biome.BiomeGenSavanna;
import net.minecraft.world.biome.BiomeGenSavannaMutated;
import net.minecraft.world.biome.BiomeGenSnow;
import net.minecraft.world.biome.BiomeGenStoneBeach;
import net.minecraft.world.biome.BiomeGenSwamp;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraft.world.biome.BiomeVoid;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;
import net.minecraft.world.gen.feature.WorldGenSwamp;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BiomeGenBase {
   private static final Logger field_150586_aC = LogManager.getLogger();
   protected static final IBlockState field_185365_a = Blocks.field_150348_b.func_176223_P();
   protected static final IBlockState field_185366_b = Blocks.field_150350_a.func_176223_P();
   protected static final IBlockState field_185367_c = Blocks.field_150357_h.func_176223_P();
   protected static final IBlockState field_185368_d = Blocks.field_150351_n.func_176223_P();
   protected static final IBlockState field_185369_e = Blocks.field_180395_cM.func_176223_P();
   protected static final IBlockState field_185370_f = Blocks.field_150322_A.func_176223_P();
   protected static final IBlockState field_185371_g = Blocks.field_150432_aD.func_176223_P();
   protected static final IBlockState field_185372_h = Blocks.field_150355_j.func_176223_P();
   public static final Set<BiomeGenBase> field_150597_n = Sets.<BiomeGenBase>newHashSet();
   public static final ObjectIntIdentityMap<BiomeGenBase> field_185373_j = new ObjectIntIdentityMap();
   protected static final NoiseGeneratorPerlin field_150605_ac = new NoiseGeneratorPerlin(new Random(1234L), 1);
   protected static final NoiseGeneratorPerlin field_180281_af = new NoiseGeneratorPerlin(new Random(2345L), 1);
   protected static final WorldGenDoublePlant field_180280_ag = new WorldGenDoublePlant();
   protected static final WorldGenTrees field_76757_N = new WorldGenTrees(false);
   protected static final WorldGenBigTree field_76758_O = new WorldGenBigTree(false);
   protected static final WorldGenSwamp field_76763_Q = new WorldGenSwamp();
   public static final RegistryNamespaced<ResourceLocation, BiomeGenBase> field_185377_q = new RegistryNamespaced();
   private final String field_76791_y;
   private final float field_76748_D;
   private final float field_76749_E;
   private final float field_76750_F;
   private final float field_76751_G;
   private final int field_76759_H;
   private final boolean field_76766_R;
   private final boolean field_76765_S;
   private final String field_185364_H;
   public IBlockState field_76752_A = Blocks.field_150349_c.func_176223_P();
   public IBlockState field_76753_B = Blocks.field_150346_d.func_176223_P();
   public BiomeDecorator field_76760_I;
   protected List<BiomeGenBase.SpawnListEntry> field_76761_J = Lists.<BiomeGenBase.SpawnListEntry>newArrayList();
   protected List<BiomeGenBase.SpawnListEntry> field_76762_K = Lists.<BiomeGenBase.SpawnListEntry>newArrayList();
   protected List<BiomeGenBase.SpawnListEntry> field_76755_L = Lists.<BiomeGenBase.SpawnListEntry>newArrayList();
   protected List<BiomeGenBase.SpawnListEntry> field_82914_M = Lists.<BiomeGenBase.SpawnListEntry>newArrayList();

   public static int func_185362_a(BiomeGenBase p_185362_0_) {
      return field_185377_q.func_148757_b(p_185362_0_);
   }

   public static BiomeGenBase func_185357_a(int p_185357_0_) {
      return (BiomeGenBase)field_185377_q.func_148754_a(p_185357_0_);
   }

   public static BiomeGenBase func_185356_b(BiomeGenBase p_185356_0_) {
      return (BiomeGenBase)field_185373_j.func_148745_a(func_185362_a(p_185356_0_));
   }

   protected BiomeGenBase(BiomeGenBase.BiomeProperties p_i46713_1_) {
      this.field_76791_y = p_i46713_1_.field_185412_a;
      this.field_76748_D = p_i46713_1_.field_185413_b;
      this.field_76749_E = p_i46713_1_.field_185414_c;
      this.field_76750_F = p_i46713_1_.field_185415_d;
      this.field_76751_G = p_i46713_1_.field_185416_e;
      this.field_76759_H = p_i46713_1_.field_185417_f;
      this.field_76766_R = p_i46713_1_.field_185418_g;
      this.field_76765_S = p_i46713_1_.field_185419_h;
      this.field_185364_H = p_i46713_1_.field_185420_i;
      this.field_76760_I = this.func_76729_a();
      this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntitySheep.class, 12, 4, 4));
      this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityPig.class, 10, 4, 4));
      this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityChicken.class, 10, 4, 4));
      this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityCow.class, 8, 4, 4));
      this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 4, 4));
      this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 4, 4));
      this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
      this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityCreeper.class, 100, 4, 4));
      this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntitySlime.class, 100, 4, 4));
      this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityEnderman.class, 10, 1, 4));
      this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityWitch.class, 5, 1, 1));
      this.field_76755_L.add(new BiomeGenBase.SpawnListEntry(EntitySquid.class, 10, 4, 4));
      this.field_82914_M.add(new BiomeGenBase.SpawnListEntry(EntityBat.class, 10, 8, 8));
   }

   protected BiomeDecorator func_76729_a() {
      return new BiomeDecorator();
   }

   public boolean func_185363_b() {
      return this.field_185364_H != null;
   }

   public WorldGenAbstractTree func_150567_a(Random p_150567_1_) {
      return (WorldGenAbstractTree)(p_150567_1_.nextInt(10) == 0?field_76758_O:field_76757_N);
   }

   public WorldGenerator func_76730_b(Random p_76730_1_) {
      return new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
   }

   public BlockFlower.EnumFlowerType func_180623_a(Random p_180623_1_, BlockPos p_180623_2_) {
      return p_180623_1_.nextInt(3) > 0?BlockFlower.EnumFlowerType.DANDELION:BlockFlower.EnumFlowerType.POPPY;
   }

   public int func_76731_a(float p_76731_1_) {
      p_76731_1_ = p_76731_1_ / 3.0F;
      p_76731_1_ = MathHelper.func_76131_a(p_76731_1_, -1.0F, 1.0F);
      return MathHelper.func_181758_c(0.62222224F - p_76731_1_ * 0.05F, 0.5F + p_76731_1_ * 0.1F, 1.0F);
   }

   public List<BiomeGenBase.SpawnListEntry> func_76747_a(EnumCreatureType p_76747_1_) {
      switch(p_76747_1_) {
      case MONSTER:
         return this.field_76761_J;
      case CREATURE:
         return this.field_76762_K;
      case WATER_CREATURE:
         return this.field_76755_L;
      case AMBIENT:
         return this.field_82914_M;
      default:
         return Collections.<BiomeGenBase.SpawnListEntry>emptyList();
      }
   }

   public boolean func_76746_c() {
      return this.func_150559_j();
   }

   public boolean func_76738_d() {
      return this.func_150559_j()?false:this.field_76765_S;
   }

   public boolean func_76736_e() {
      return this.func_76727_i() > 0.85F;
   }

   public float func_76741_f() {
      return 0.1F;
   }

   public final float func_180626_a(BlockPos p_180626_1_) {
      if(p_180626_1_.func_177956_o() > 64) {
         float f = (float)(field_150605_ac.func_151601_a((double)((float)p_180626_1_.func_177958_n() / 8.0F), (double)((float)p_180626_1_.func_177952_p() / 8.0F)) * 4.0D);
         return this.func_185353_n() - (f + (float)p_180626_1_.func_177956_o() - 64.0F) * 0.05F / 30.0F;
      } else {
         return this.func_185353_n();
      }
   }

   public void func_180624_a(World p_180624_1_, Random p_180624_2_, BlockPos p_180624_3_) {
      this.field_76760_I.func_180292_a(p_180624_1_, p_180624_2_, this, p_180624_3_);
   }

   public int func_180627_b(BlockPos p_180627_1_) {
      double d0 = (double)MathHelper.func_76131_a(this.func_180626_a(p_180627_1_), 0.0F, 1.0F);
      double d1 = (double)MathHelper.func_76131_a(this.func_76727_i(), 0.0F, 1.0F);
      return ColorizerGrass.func_77480_a(d0, d1);
   }

   public int func_180625_c(BlockPos p_180625_1_) {
      double d0 = (double)MathHelper.func_76131_a(this.func_180626_a(p_180625_1_), 0.0F, 1.0F);
      double d1 = (double)MathHelper.func_76131_a(this.func_76727_i(), 0.0F, 1.0F);
      return ColorizerFoliage.func_77470_a(d0, d1);
   }

   public void func_180622_a(World p_180622_1_, Random p_180622_2_, ChunkPrimer p_180622_3_, int p_180622_4_, int p_180622_5_, double p_180622_6_) {
      this.func_180628_b(p_180622_1_, p_180622_2_, p_180622_3_, p_180622_4_, p_180622_5_, p_180622_6_);
   }

   public final void func_180628_b(World p_180628_1_, Random p_180628_2_, ChunkPrimer p_180628_3_, int p_180628_4_, int p_180628_5_, double p_180628_6_) {
      int i = p_180628_1_.func_181545_F();
      IBlockState iblockstate = this.field_76752_A;
      IBlockState iblockstate1 = this.field_76753_B;
      int j = -1;
      int k = (int)(p_180628_6_ / 3.0D + 3.0D + p_180628_2_.nextDouble() * 0.25D);
      int l = p_180628_4_ & 15;
      int i1 = p_180628_5_ & 15;
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

      for(int j1 = 255; j1 >= 0; --j1) {
         if(j1 <= p_180628_2_.nextInt(5)) {
            p_180628_3_.func_177855_a(i1, j1, l, field_185367_c);
         } else {
            IBlockState iblockstate2 = p_180628_3_.func_177856_a(i1, j1, l);
            if(iblockstate2.func_185904_a() == Material.field_151579_a) {
               j = -1;
            } else if(iblockstate2.func_177230_c() == Blocks.field_150348_b) {
               if(j == -1) {
                  if(k <= 0) {
                     iblockstate = field_185366_b;
                     iblockstate1 = field_185365_a;
                  } else if(j1 >= i - 4 && j1 <= i + 1) {
                     iblockstate = this.field_76752_A;
                     iblockstate1 = this.field_76753_B;
                  }

                  if(j1 < i && (iblockstate == null || iblockstate.func_185904_a() == Material.field_151579_a)) {
                     if(this.func_180626_a(blockpos$mutableblockpos.func_181079_c(p_180628_4_, j1, p_180628_5_)) < 0.15F) {
                        iblockstate = field_185371_g;
                     } else {
                        iblockstate = field_185372_h;
                     }
                  }

                  j = k;
                  if(j1 >= i - 1) {
                     p_180628_3_.func_177855_a(i1, j1, l, iblockstate);
                  } else if(j1 < i - 7 - k) {
                     iblockstate = field_185366_b;
                     iblockstate1 = field_185365_a;
                     p_180628_3_.func_177855_a(i1, j1, l, field_185368_d);
                  } else {
                     p_180628_3_.func_177855_a(i1, j1, l, iblockstate1);
                  }
               } else if(j > 0) {
                  --j;
                  p_180628_3_.func_177855_a(i1, j1, l, iblockstate1);
                  if(j == 0 && iblockstate1.func_177230_c() == Blocks.field_150354_m) {
                     j = p_180628_2_.nextInt(4) + Math.max(0, j1 - 63);
                     iblockstate1 = iblockstate1.func_177229_b(BlockSand.field_176504_a) == BlockSand.EnumType.RED_SAND?field_185369_e:field_185370_f;
                  }
               }
            }
         }
      }

   }

   public Class<? extends BiomeGenBase> func_150562_l() {
      return this.getClass();
   }

   public BiomeGenBase.TempCategory func_150561_m() {
      return (double)this.func_185353_n() < 0.2D?BiomeGenBase.TempCategory.COLD:((double)this.func_185353_n() < 1.0D?BiomeGenBase.TempCategory.MEDIUM:BiomeGenBase.TempCategory.WARM);
   }

   public static BiomeGenBase func_150568_d(int p_150568_0_) {
      return func_180276_a(p_150568_0_, (BiomeGenBase)null);
   }

   public static BiomeGenBase func_180276_a(int p_180276_0_, BiomeGenBase p_180276_1_) {
      BiomeGenBase biomegenbase = func_185357_a(p_180276_0_);
      return biomegenbase == null?p_180276_1_:biomegenbase;
   }

   public boolean func_185352_i() {
      return false;
   }

   public final float func_185355_j() {
      return this.field_76748_D;
   }

   public final float func_76727_i() {
      return this.field_76751_G;
   }

   public final String func_185359_l() {
      return this.field_76791_y;
   }

   public final float func_185360_m() {
      return this.field_76749_E;
   }

   public final float func_185353_n() {
      return this.field_76750_F;
   }

   public final int func_185361_o() {
      return this.field_76759_H;
   }

   public final boolean func_150559_j() {
      return this.field_76766_R;
   }

   public static void func_185358_q() {
      func_185354_a(0, "ocean", new BiomeGenOcean((new BiomeGenBase.BiomeProperties("Ocean")).func_185398_c(-1.0F).func_185400_d(0.1F)));
      func_185354_a(1, "plains", new BiomeGenPlains(false, (new BiomeGenBase.BiomeProperties("Plains")).func_185398_c(0.125F).func_185400_d(0.05F).func_185410_a(0.8F).func_185395_b(0.4F)));
      func_185354_a(2, "desert", new BiomeGenDesert((new BiomeGenBase.BiomeProperties("Desert")).func_185398_c(0.125F).func_185400_d(0.05F).func_185410_a(2.0F).func_185395_b(0.0F).func_185396_a()));
      func_185354_a(3, "extreme_hills", new BiomeGenHills(BiomeGenHills.Type.NORMAL, (new BiomeGenBase.BiomeProperties("Extreme Hills")).func_185398_c(1.0F).func_185400_d(0.5F).func_185410_a(0.2F).func_185395_b(0.3F)));
      func_185354_a(4, "forest", new BiomeGenForest(BiomeGenForest.Type.NORMAL, (new BiomeGenBase.BiomeProperties("Forest")).func_185410_a(0.7F).func_185395_b(0.8F)));
      func_185354_a(5, "taiga", new BiomeGenTaiga(BiomeGenTaiga.Type.NORMAL, (new BiomeGenBase.BiomeProperties("Taiga")).func_185398_c(0.2F).func_185400_d(0.2F).func_185410_a(0.25F).func_185395_b(0.8F)));
      func_185354_a(6, "swampland", new BiomeGenSwamp((new BiomeGenBase.BiomeProperties("Swampland")).func_185398_c(-0.2F).func_185400_d(0.1F).func_185410_a(0.8F).func_185395_b(0.9F).func_185402_a(14745518)));
      func_185354_a(7, "river", new BiomeGenRiver((new BiomeGenBase.BiomeProperties("River")).func_185398_c(-0.5F).func_185400_d(0.0F)));
      func_185354_a(8, "hell", new BiomeGenHell((new BiomeGenBase.BiomeProperties("Hell")).func_185410_a(2.0F).func_185395_b(0.0F).func_185396_a()));
      func_185354_a(9, "sky", new BiomeGenEnd((new BiomeGenBase.BiomeProperties("The End")).func_185396_a()));
      func_185354_a(10, "frozen_ocean", new BiomeGenOcean((new BiomeGenBase.BiomeProperties("FrozenOcean")).func_185398_c(-1.0F).func_185400_d(0.1F).func_185410_a(0.0F).func_185395_b(0.5F).func_185411_b()));
      func_185354_a(11, "frozen_river", new BiomeGenRiver((new BiomeGenBase.BiomeProperties("FrozenRiver")).func_185398_c(-0.5F).func_185400_d(0.0F).func_185410_a(0.0F).func_185395_b(0.5F).func_185411_b()));
      func_185354_a(12, "ice_flats", new BiomeGenSnow(false, (new BiomeGenBase.BiomeProperties("Ice Plains")).func_185398_c(0.125F).func_185400_d(0.05F).func_185410_a(0.0F).func_185395_b(0.5F).func_185411_b()));
      func_185354_a(13, "ice_mountains", new BiomeGenSnow(false, (new BiomeGenBase.BiomeProperties("Ice Mountains")).func_185398_c(0.45F).func_185400_d(0.3F).func_185410_a(0.0F).func_185395_b(0.5F).func_185411_b()));
      func_185354_a(14, "mushroom_island", new BiomeGenMushroomIsland((new BiomeGenBase.BiomeProperties("MushroomIsland")).func_185398_c(0.2F).func_185400_d(0.3F).func_185410_a(0.9F).func_185395_b(1.0F)));
      func_185354_a(15, "mushroom_island_shore", new BiomeGenMushroomIsland((new BiomeGenBase.BiomeProperties("MushroomIslandShore")).func_185398_c(0.0F).func_185400_d(0.025F).func_185410_a(0.9F).func_185395_b(1.0F)));
      func_185354_a(16, "beaches", new BiomeGenBeach((new BiomeGenBase.BiomeProperties("Beach")).func_185398_c(0.0F).func_185400_d(0.025F).func_185410_a(0.8F).func_185395_b(0.4F)));
      func_185354_a(17, "desert_hills", new BiomeGenDesert((new BiomeGenBase.BiomeProperties("DesertHills")).func_185398_c(0.45F).func_185400_d(0.3F).func_185410_a(2.0F).func_185395_b(0.0F).func_185396_a()));
      func_185354_a(18, "forest_hills", new BiomeGenForest(BiomeGenForest.Type.NORMAL, (new BiomeGenBase.BiomeProperties("ForestHills")).func_185398_c(0.45F).func_185400_d(0.3F).func_185410_a(0.7F).func_185395_b(0.8F)));
      func_185354_a(19, "taiga_hills", new BiomeGenTaiga(BiomeGenTaiga.Type.NORMAL, (new BiomeGenBase.BiomeProperties("TaigaHills")).func_185410_a(0.25F).func_185395_b(0.8F).func_185398_c(0.45F).func_185400_d(0.3F)));
      func_185354_a(20, "smaller_extreme_hills", new BiomeGenHills(BiomeGenHills.Type.EXTRA_TREES, (new BiomeGenBase.BiomeProperties("Extreme Hills Edge")).func_185398_c(0.8F).func_185400_d(0.3F).func_185410_a(0.2F).func_185395_b(0.3F)));
      func_185354_a(21, "jungle", new BiomeGenJungle(false, (new BiomeGenBase.BiomeProperties("Jungle")).func_185410_a(0.95F).func_185395_b(0.9F)));
      func_185354_a(22, "jungle_hills", new BiomeGenJungle(false, (new BiomeGenBase.BiomeProperties("JungleHills")).func_185398_c(0.45F).func_185400_d(0.3F).func_185410_a(0.95F).func_185395_b(0.9F)));
      func_185354_a(23, "jungle_edge", new BiomeGenJungle(true, (new BiomeGenBase.BiomeProperties("JungleEdge")).func_185410_a(0.95F).func_185395_b(0.8F)));
      func_185354_a(24, "deep_ocean", new BiomeGenOcean((new BiomeGenBase.BiomeProperties("Deep Ocean")).func_185398_c(-1.8F).func_185400_d(0.1F)));
      func_185354_a(25, "stone_beach", new BiomeGenStoneBeach((new BiomeGenBase.BiomeProperties("Stone Beach")).func_185398_c(0.1F).func_185400_d(0.8F).func_185410_a(0.2F).func_185395_b(0.3F)));
      func_185354_a(26, "cold_beach", new BiomeGenBeach((new BiomeGenBase.BiomeProperties("Cold Beach")).func_185398_c(0.0F).func_185400_d(0.025F).func_185410_a(0.05F).func_185395_b(0.3F).func_185411_b()));
      func_185354_a(27, "birch_forest", new BiomeGenForest(BiomeGenForest.Type.BIRCH, (new BiomeGenBase.BiomeProperties("Birch Forest")).func_185410_a(0.6F).func_185395_b(0.6F)));
      func_185354_a(28, "birch_forest_hills", new BiomeGenForest(BiomeGenForest.Type.BIRCH, (new BiomeGenBase.BiomeProperties("Birch Forest Hills")).func_185398_c(0.45F).func_185400_d(0.3F).func_185410_a(0.6F).func_185395_b(0.6F)));
      func_185354_a(29, "roofed_forest", new BiomeGenForest(BiomeGenForest.Type.ROOFED, (new BiomeGenBase.BiomeProperties("Roofed Forest")).func_185410_a(0.7F).func_185395_b(0.8F)));
      func_185354_a(30, "taiga_cold", new BiomeGenTaiga(BiomeGenTaiga.Type.NORMAL, (new BiomeGenBase.BiomeProperties("Cold Taiga")).func_185398_c(0.2F).func_185400_d(0.2F).func_185410_a(-0.5F).func_185395_b(0.4F).func_185411_b()));
      func_185354_a(31, "taiga_cold_hills", new BiomeGenTaiga(BiomeGenTaiga.Type.NORMAL, (new BiomeGenBase.BiomeProperties("Cold Taiga Hills")).func_185398_c(0.45F).func_185400_d(0.3F).func_185410_a(-0.5F).func_185395_b(0.4F).func_185411_b()));
      func_185354_a(32, "redwood_taiga", new BiomeGenTaiga(BiomeGenTaiga.Type.MEGA, (new BiomeGenBase.BiomeProperties("Mega Taiga")).func_185410_a(0.3F).func_185395_b(0.8F).func_185398_c(0.2F).func_185400_d(0.2F)));
      func_185354_a(33, "redwood_taiga_hills", new BiomeGenTaiga(BiomeGenTaiga.Type.MEGA, (new BiomeGenBase.BiomeProperties("Mega Taiga Hills")).func_185398_c(0.45F).func_185400_d(0.3F).func_185410_a(0.3F).func_185395_b(0.8F)));
      func_185354_a(34, "extreme_hills_with_trees", new BiomeGenHills(BiomeGenHills.Type.EXTRA_TREES, (new BiomeGenBase.BiomeProperties("Extreme Hills+")).func_185398_c(1.0F).func_185400_d(0.5F).func_185410_a(0.2F).func_185395_b(0.3F)));
      func_185354_a(35, "savanna", new BiomeGenSavanna((new BiomeGenBase.BiomeProperties("Savanna")).func_185398_c(0.125F).func_185400_d(0.05F).func_185410_a(1.2F).func_185395_b(0.0F).func_185396_a()));
      func_185354_a(36, "savanna_rock", new BiomeGenSavanna((new BiomeGenBase.BiomeProperties("Savanna Plateau")).func_185398_c(1.5F).func_185400_d(0.025F).func_185410_a(1.0F).func_185395_b(0.0F).func_185396_a()));
      func_185354_a(37, "mesa", new BiomeGenMesa(false, false, (new BiomeGenBase.BiomeProperties("Mesa")).func_185410_a(2.0F).func_185395_b(0.0F).func_185396_a()));
      func_185354_a(38, "mesa_rock", new BiomeGenMesa(false, true, (new BiomeGenBase.BiomeProperties("Mesa Plateau F")).func_185398_c(1.5F).func_185400_d(0.025F).func_185410_a(2.0F).func_185395_b(0.0F).func_185396_a()));
      func_185354_a(39, "mesa_clear_rock", new BiomeGenMesa(false, false, (new BiomeGenBase.BiomeProperties("Mesa Plateau")).func_185398_c(1.5F).func_185400_d(0.025F).func_185410_a(2.0F).func_185395_b(0.0F).func_185396_a()));
      func_185354_a(127, "void", new BiomeVoid((new BiomeGenBase.BiomeProperties("The Void")).func_185396_a()));
      func_185354_a(129, "mutated_plains", new BiomeGenPlains(true, (new BiomeGenBase.BiomeProperties("Sunflower Plains")).func_185399_a("plains").func_185398_c(0.125F).func_185400_d(0.05F).func_185410_a(0.8F).func_185395_b(0.4F)));
      func_185354_a(130, "mutated_desert", new BiomeGenDesert((new BiomeGenBase.BiomeProperties("Desert M")).func_185399_a("desert").func_185398_c(0.225F).func_185400_d(0.25F).func_185410_a(2.0F).func_185395_b(0.0F).func_185396_a()));
      func_185354_a(131, "mutated_extreme_hills", new BiomeGenHills(BiomeGenHills.Type.MUTATED, (new BiomeGenBase.BiomeProperties("Extreme Hills M")).func_185399_a("extreme_hills").func_185398_c(1.0F).func_185400_d(0.5F).func_185410_a(0.2F).func_185395_b(0.3F)));
      func_185354_a(132, "mutated_forest", new BiomeGenForest(BiomeGenForest.Type.FLOWER, (new BiomeGenBase.BiomeProperties("Flower Forest")).func_185399_a("forest").func_185400_d(0.4F).func_185410_a(0.7F).func_185395_b(0.8F)));
      func_185354_a(133, "mutated_taiga", new BiomeGenTaiga(BiomeGenTaiga.Type.NORMAL, (new BiomeGenBase.BiomeProperties("Taiga M")).func_185399_a("taiga").func_185398_c(0.3F).func_185400_d(0.4F).func_185410_a(0.25F).func_185395_b(0.8F)));
      func_185354_a(134, "mutated_swampland", new BiomeGenSwamp((new BiomeGenBase.BiomeProperties("Swampland M")).func_185399_a("swampland").func_185398_c(-0.1F).func_185400_d(0.3F).func_185410_a(0.8F).func_185395_b(0.9F).func_185402_a(14745518)));
      func_185354_a(140, "mutated_ice_flats", new BiomeGenSnow(true, (new BiomeGenBase.BiomeProperties("Ice Plains Spikes")).func_185399_a("ice_flats").func_185398_c(0.425F).func_185400_d(0.45000002F).func_185410_a(0.0F).func_185395_b(0.5F).func_185411_b()));
      func_185354_a(149, "mutated_jungle", new BiomeGenJungle(false, (new BiomeGenBase.BiomeProperties("Jungle M")).func_185399_a("jungle").func_185398_c(0.2F).func_185400_d(0.4F).func_185410_a(0.95F).func_185395_b(0.9F)));
      func_185354_a(151, "mutated_jungle_edge", new BiomeGenJungle(true, (new BiomeGenBase.BiomeProperties("JungleEdge M")).func_185399_a("jungle_edge").func_185398_c(0.2F).func_185400_d(0.4F).func_185410_a(0.95F).func_185395_b(0.8F)));
      func_185354_a(155, "mutated_birch_forest", new BiomeGenForestMutated((new BiomeGenBase.BiomeProperties("Birch Forest M")).func_185399_a("birch_forest").func_185398_c(0.2F).func_185400_d(0.4F).func_185410_a(0.6F).func_185395_b(0.6F)));
      func_185354_a(156, "mutated_birch_forest_hills", new BiomeGenForestMutated((new BiomeGenBase.BiomeProperties("Birch Forest Hills M")).func_185399_a("birch_forest").func_185398_c(0.55F).func_185400_d(0.5F).func_185410_a(0.6F).func_185395_b(0.6F)));
      func_185354_a(157, "mutated_roofed_forest", new BiomeGenForest(BiomeGenForest.Type.ROOFED, (new BiomeGenBase.BiomeProperties("Roofed Forest M")).func_185399_a("roofed_forest").func_185398_c(0.2F).func_185400_d(0.4F).func_185410_a(0.7F).func_185395_b(0.8F)));
      func_185354_a(158, "mutated_taiga_cold", new BiomeGenTaiga(BiomeGenTaiga.Type.NORMAL, (new BiomeGenBase.BiomeProperties("Cold Taiga M")).func_185399_a("taiga_cold").func_185398_c(0.3F).func_185400_d(0.4F).func_185410_a(-0.5F).func_185395_b(0.4F).func_185411_b()));
      func_185354_a(160, "mutated_redwood_taiga", new BiomeGenTaiga(BiomeGenTaiga.Type.MEGA_SPRUCE, (new BiomeGenBase.BiomeProperties("Mega Spruce Taiga")).func_185399_a("redwood_taiga").func_185398_c(0.2F).func_185400_d(0.2F).func_185410_a(0.25F).func_185395_b(0.8F)));
      func_185354_a(161, "mutated_redwood_taiga_hills", new BiomeGenTaiga(BiomeGenTaiga.Type.MEGA_SPRUCE, (new BiomeGenBase.BiomeProperties("Redwood Taiga Hills M")).func_185399_a("redwood_taiga_hills").func_185398_c(0.2F).func_185400_d(0.2F).func_185410_a(0.25F).func_185395_b(0.8F)));
      func_185354_a(162, "mutated_extreme_hills_with_trees", new BiomeGenHills(BiomeGenHills.Type.MUTATED, (new BiomeGenBase.BiomeProperties("Extreme Hills+ M")).func_185399_a("extreme_hills_with_trees").func_185398_c(1.0F).func_185400_d(0.5F).func_185410_a(0.2F).func_185395_b(0.3F)));
      func_185354_a(163, "mutated_savanna", new BiomeGenSavannaMutated((new BiomeGenBase.BiomeProperties("Savanna M")).func_185399_a("savanna").func_185398_c(0.3625F).func_185400_d(1.225F).func_185410_a(1.1F).func_185395_b(0.0F).func_185396_a()));
      func_185354_a(164, "mutated_savanna_rock", new BiomeGenSavannaMutated((new BiomeGenBase.BiomeProperties("Savanna Plateau M")).func_185399_a("savanna_rock").func_185398_c(1.05F).func_185400_d(1.2125001F).func_185410_a(1.0F).func_185395_b(0.0F).func_185396_a()));
      func_185354_a(165, "mutated_mesa", new BiomeGenMesa(true, false, (new BiomeGenBase.BiomeProperties("Mesa (Bryce)")).func_185399_a("mesa").func_185410_a(2.0F).func_185395_b(0.0F).func_185396_a()));
      func_185354_a(166, "mutated_mesa_rock", new BiomeGenMesa(false, true, (new BiomeGenBase.BiomeProperties("Mesa Plateau F M")).func_185399_a("mesa_rock").func_185398_c(0.45F).func_185400_d(0.3F).func_185410_a(2.0F).func_185395_b(0.0F).func_185396_a()));
      func_185354_a(167, "mutated_mesa_clear_rock", new BiomeGenMesa(false, false, (new BiomeGenBase.BiomeProperties("Mesa Plateau M")).func_185399_a("mesa_clear_rock").func_185398_c(0.45F).func_185400_d(0.3F).func_185410_a(2.0F).func_185395_b(0.0F).func_185396_a()));
      Collections.addAll(field_150597_n, new BiomeGenBase[]{Biomes.field_76771_b, Biomes.field_76772_c, Biomes.field_76769_d, Biomes.field_76770_e, Biomes.field_76767_f, Biomes.field_76768_g, Biomes.field_76780_h, Biomes.field_76781_i, Biomes.field_76777_m, Biomes.field_76774_n, Biomes.field_76775_o, Biomes.field_76789_p, Biomes.field_76788_q, Biomes.field_76787_r, Biomes.field_76786_s, Biomes.field_76785_t, Biomes.field_76784_u, Biomes.field_76782_w, Biomes.field_76792_x, Biomes.field_150574_L, Biomes.field_150575_M, Biomes.field_150576_N, Biomes.field_150577_O, Biomes.field_150583_P, Biomes.field_150582_Q, Biomes.field_150585_R, Biomes.field_150584_S, Biomes.field_150579_T, Biomes.field_150578_U, Biomes.field_150581_V, Biomes.field_150580_W, Biomes.field_150588_X, Biomes.field_150587_Y, Biomes.field_150589_Z, Biomes.field_150607_aa, Biomes.field_150608_ab});
   }

   private static void func_185354_a(int p_185354_0_, String p_185354_1_, BiomeGenBase p_185354_2_) {
      field_185377_q.func_177775_a(p_185354_0_, new ResourceLocation(p_185354_1_), p_185354_2_);
      if(p_185354_2_.func_185363_b()) {
         field_185373_j.func_148746_a(p_185354_2_, func_185362_a((BiomeGenBase)field_185377_q.func_82594_a(new ResourceLocation(p_185354_2_.field_185364_H))));
      }

   }

   public static class BiomeProperties {
      private final String field_185412_a;
      private float field_185413_b = 0.1F;
      private float field_185414_c = 0.2F;
      private float field_185415_d = 0.5F;
      private float field_185416_e = 0.5F;
      private int field_185417_f = 16777215;
      private boolean field_185418_g;
      private boolean field_185419_h = true;
      private String field_185420_i;

      public BiomeProperties(String p_i47073_1_) {
         this.field_185412_a = p_i47073_1_;
      }

      protected BiomeGenBase.BiomeProperties func_185410_a(float p_185410_1_) {
         if(p_185410_1_ > 0.1F && p_185410_1_ < 0.2F) {
            throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
         } else {
            this.field_185415_d = p_185410_1_;
            return this;
         }
      }

      protected BiomeGenBase.BiomeProperties func_185395_b(float p_185395_1_) {
         this.field_185416_e = p_185395_1_;
         return this;
      }

      protected BiomeGenBase.BiomeProperties func_185398_c(float p_185398_1_) {
         this.field_185413_b = p_185398_1_;
         return this;
      }

      protected BiomeGenBase.BiomeProperties func_185400_d(float p_185400_1_) {
         this.field_185414_c = p_185400_1_;
         return this;
      }

      protected BiomeGenBase.BiomeProperties func_185396_a() {
         this.field_185419_h = false;
         return this;
      }

      protected BiomeGenBase.BiomeProperties func_185411_b() {
         this.field_185418_g = true;
         return this;
      }

      protected BiomeGenBase.BiomeProperties func_185402_a(int p_185402_1_) {
         this.field_185417_f = p_185402_1_;
         return this;
      }

      protected BiomeGenBase.BiomeProperties func_185399_a(String p_185399_1_) {
         this.field_185420_i = p_185399_1_;
         return this;
      }
   }

   public static class SpawnListEntry extends WeightedRandom.Item {
      public Class<? extends EntityLiving> field_76300_b;
      public int field_76301_c;
      public int field_76299_d;

      public SpawnListEntry(Class<? extends EntityLiving> p_i1970_1_, int p_i1970_2_, int p_i1970_3_, int p_i1970_4_) {
         super(p_i1970_2_);
         this.field_76300_b = p_i1970_1_;
         this.field_76301_c = p_i1970_3_;
         this.field_76299_d = p_i1970_4_;
      }

      public String toString() {
         return this.field_76300_b.getSimpleName() + "*(" + this.field_76301_c + "-" + this.field_76299_d + "):" + this.field_76292_a;
      }
   }

   public static enum TempCategory {
      OCEAN,
      COLD,
      MEDIUM,
      WARM;
   }
}
