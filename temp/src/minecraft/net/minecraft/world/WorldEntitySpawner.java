package net.minecraft.world;

import com.google.common.collect.Sets;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.management.PlayerManager;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

public final class WorldEntitySpawner {
   private static final int field_180268_a = (int)Math.pow(17.0D, 2.0D);
   private final Set<ChunkCoordIntPair> field_77193_b = Sets.<ChunkCoordIntPair>newHashSet();

   public int func_77192_a(WorldServer p_77192_1_, boolean p_77192_2_, boolean p_77192_3_, boolean p_77192_4_) {
      if(!p_77192_2_ && !p_77192_3_) {
         return 0;
      } else {
         this.field_77193_b.clear();
         int i = 0;

         for(EntityPlayer entityplayer : p_77192_1_.field_73010_i) {
            if(!entityplayer.func_175149_v()) {
               int j = MathHelper.func_76128_c(entityplayer.field_70165_t / 16.0D);
               int k = MathHelper.func_76128_c(entityplayer.field_70161_v / 16.0D);
               int l = 8;

               for(int i1 = -l; i1 <= l; ++i1) {
                  for(int j1 = -l; j1 <= l; ++j1) {
                     boolean flag = i1 == -l || i1 == l || j1 == -l || j1 == l;
                     ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i1 + j, j1 + k);
                     if(!this.field_77193_b.contains(chunkcoordintpair)) {
                        ++i;
                        if(!flag && p_77192_1_.func_175723_af().func_177730_a(chunkcoordintpair)) {
                           PlayerManager.PlayerInstance playermanager$playerinstance = p_77192_1_.func_184164_w().func_187301_b(chunkcoordintpair.field_77276_a, chunkcoordintpair.field_77275_b);
                           if(playermanager$playerinstance != null && playermanager$playerinstance.func_187274_e()) {
                              this.field_77193_b.add(chunkcoordintpair);
                           }
                        }
                     }
                  }
               }
            }
         }

         int j4 = 0;
         BlockPos blockpos1 = p_77192_1_.func_175694_M();

         for(EnumCreatureType enumcreaturetype : EnumCreatureType.values()) {
            if((!enumcreaturetype.func_75599_d() || p_77192_3_) && (enumcreaturetype.func_75599_d() || p_77192_2_) && (!enumcreaturetype.func_82705_e() || p_77192_4_)) {
               int k4 = p_77192_1_.func_72907_a(enumcreaturetype.func_75598_a());
               int l4 = enumcreaturetype.func_75601_b() * i / field_180268_a;
               if(k4 <= l4) {
                  BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                  label415:
                  for(ChunkCoordIntPair chunkcoordintpair1 : this.field_77193_b) {
                     BlockPos blockpos = func_180621_a(p_77192_1_, chunkcoordintpair1.field_77276_a, chunkcoordintpair1.field_77275_b);
                     int k1 = blockpos.func_177958_n();
                     int l1 = blockpos.func_177956_o();
                     int i2 = blockpos.func_177952_p();
                     IBlockState iblockstate = p_77192_1_.func_180495_p(blockpos);
                     if(!iblockstate.func_185915_l()) {
                        int j2 = 0;

                        for(int k2 = 0; k2 < 3; ++k2) {
                           int l2 = k1;
                           int i3 = l1;
                           int j3 = i2;
                           int k3 = 6;
                           BiomeGenBase.SpawnListEntry biomegenbase$spawnlistentry = null;
                           IEntityLivingData ientitylivingdata = null;
                           int l3 = MathHelper.func_76143_f(Math.random() * 4.0D);

                           for(int i4 = 0; i4 < l3; ++i4) {
                              l2 += p_77192_1_.field_73012_v.nextInt(k3) - p_77192_1_.field_73012_v.nextInt(k3);
                              i3 += p_77192_1_.field_73012_v.nextInt(1) - p_77192_1_.field_73012_v.nextInt(1);
                              j3 += p_77192_1_.field_73012_v.nextInt(k3) - p_77192_1_.field_73012_v.nextInt(k3);
                              blockpos$mutableblockpos.func_181079_c(l2, i3, j3);
                              float f = (float)l2 + 0.5F;
                              float f1 = (float)j3 + 0.5F;
                              if(!p_77192_1_.func_175636_b((double)f, (double)i3, (double)f1, 24.0D) && blockpos1.func_177954_c((double)f, (double)i3, (double)f1) >= 576.0D) {
                                 if(biomegenbase$spawnlistentry == null) {
                                    biomegenbase$spawnlistentry = p_77192_1_.func_175734_a(enumcreaturetype, blockpos$mutableblockpos);
                                    if(biomegenbase$spawnlistentry == null) {
                                       break;
                                    }
                                 }

                                 if(p_77192_1_.func_175732_a(enumcreaturetype, biomegenbase$spawnlistentry, blockpos$mutableblockpos) && func_180267_a(EntitySpawnPlacementRegistry.func_180109_a(biomegenbase$spawnlistentry.field_76300_b), p_77192_1_, blockpos$mutableblockpos)) {
                                    EntityLiving entityliving;
                                    try {
                                       entityliving = (EntityLiving)biomegenbase$spawnlistentry.field_76300_b.getConstructor(new Class[]{World.class}).newInstance(new Object[]{p_77192_1_});
                                    } catch (Exception exception) {
                                       exception.printStackTrace();
                                       return j4;
                                    }

                                    entityliving.func_70012_b((double)f, (double)i3, (double)f1, p_77192_1_.field_73012_v.nextFloat() * 360.0F, 0.0F);
                                    if(entityliving.func_70601_bi() && entityliving.func_70058_J()) {
                                       ientitylivingdata = entityliving.func_180482_a(p_77192_1_.func_175649_E(new BlockPos(entityliving)), ientitylivingdata);
                                       if(entityliving.func_70058_J()) {
                                          ++j2;
                                          p_77192_1_.func_72838_d(entityliving);
                                       } else {
                                          entityliving.func_70106_y();
                                       }

                                       if(j2 >= entityliving.func_70641_bl()) {
                                          continue label415;
                                       }
                                    }

                                    j4 += j2;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         return j4;
      }
   }

   protected static BlockPos func_180621_a(World p_180621_0_, int p_180621_1_, int p_180621_2_) {
      Chunk chunk = p_180621_0_.func_72964_e(p_180621_1_, p_180621_2_);
      int i = p_180621_1_ * 16 + p_180621_0_.field_73012_v.nextInt(16);
      int j = p_180621_2_ * 16 + p_180621_0_.field_73012_v.nextInt(16);
      int k = MathHelper.func_154354_b(chunk.func_177433_f(new BlockPos(i, 0, j)) + 1, 16);
      int l = p_180621_0_.field_73012_v.nextInt(k > 0?k:chunk.func_76625_h() + 16 - 1);
      return new BlockPos(i, l, j);
   }

   public static boolean func_185331_a(IBlockState p_185331_0_) {
      return p_185331_0_.func_185898_k()?false:(p_185331_0_.func_185897_m()?false:(p_185331_0_.func_185904_a().func_76224_d()?false:!BlockRailBase.func_176563_d(p_185331_0_)));
   }

   public static boolean func_180267_a(EntityLiving.SpawnPlacementType p_180267_0_, World p_180267_1_, BlockPos p_180267_2_) {
      if(!p_180267_1_.func_175723_af().func_177746_a(p_180267_2_)) {
         return false;
      } else {
         IBlockState iblockstate = p_180267_1_.func_180495_p(p_180267_2_);
         if(p_180267_0_ == EntityLiving.SpawnPlacementType.IN_WATER) {
            return iblockstate.func_185904_a().func_76224_d() && p_180267_1_.func_180495_p(p_180267_2_.func_177977_b()).func_185904_a().func_76224_d() && !p_180267_1_.func_180495_p(p_180267_2_.func_177984_a()).func_185915_l();
         } else {
            BlockPos blockpos = p_180267_2_.func_177977_b();
            if(!p_180267_1_.func_180495_p(blockpos).func_185896_q()) {
               return false;
            } else {
               Block block = p_180267_1_.func_180495_p(blockpos).func_177230_c();
               boolean flag = block != Blocks.field_150357_h && block != Blocks.field_180401_cv;
               return flag && func_185331_a(iblockstate) && func_185331_a(p_180267_1_.func_180495_p(p_180267_2_.func_177984_a()));
            }
         }
      }
   }

   public static void func_77191_a(World p_77191_0_, BiomeGenBase p_77191_1_, int p_77191_2_, int p_77191_3_, int p_77191_4_, int p_77191_5_, Random p_77191_6_) {
      List<BiomeGenBase.SpawnListEntry> list = p_77191_1_.func_76747_a(EnumCreatureType.CREATURE);
      if(!list.isEmpty()) {
         while(p_77191_6_.nextFloat() < p_77191_1_.func_76741_f()) {
            BiomeGenBase.SpawnListEntry biomegenbase$spawnlistentry = (BiomeGenBase.SpawnListEntry)WeightedRandom.func_76271_a(p_77191_0_.field_73012_v, list);
            int i = biomegenbase$spawnlistentry.field_76301_c + p_77191_6_.nextInt(1 + biomegenbase$spawnlistentry.field_76299_d - biomegenbase$spawnlistentry.field_76301_c);
            IEntityLivingData ientitylivingdata = null;
            int j = p_77191_2_ + p_77191_6_.nextInt(p_77191_4_);
            int k = p_77191_3_ + p_77191_6_.nextInt(p_77191_5_);
            int l = j;
            int i1 = k;

            for(int j1 = 0; j1 < i; ++j1) {
               boolean flag = false;

               for(int k1 = 0; !flag && k1 < 4; ++k1) {
                  BlockPos blockpos = p_77191_0_.func_175672_r(new BlockPos(j, 0, k));
                  if(func_180267_a(EntityLiving.SpawnPlacementType.ON_GROUND, p_77191_0_, blockpos)) {
                     EntityLiving entityliving;
                     try {
                        entityliving = (EntityLiving)biomegenbase$spawnlistentry.field_76300_b.getConstructor(new Class[]{World.class}).newInstance(new Object[]{p_77191_0_});
                     } catch (Exception exception) {
                        exception.printStackTrace();
                        continue;
                     }

                     entityliving.func_70012_b((double)((float)j + 0.5F), (double)blockpos.func_177956_o(), (double)((float)k + 0.5F), p_77191_6_.nextFloat() * 360.0F, 0.0F);
                     p_77191_0_.func_72838_d(entityliving);
                     ientitylivingdata = entityliving.func_180482_a(p_77191_0_.func_175649_E(new BlockPos(entityliving)), ientitylivingdata);
                     flag = true;
                  }

                  j += p_77191_6_.nextInt(5) - p_77191_6_.nextInt(5);

                  for(k += p_77191_6_.nextInt(5) - p_77191_6_.nextInt(5); j < p_77191_2_ || j >= p_77191_2_ + p_77191_4_ || k < p_77191_3_ || k >= p_77191_3_ + p_77191_4_; k = i1 + p_77191_6_.nextInt(5) - p_77191_6_.nextInt(5)) {
                     j = l + p_77191_6_.nextInt(5) - p_77191_6_.nextInt(5);
                  }
               }
            }
         }

      }
   }
}
