package net.minecraft.server.management;

import com.google.common.base.Predicate;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.network.play.server.SPacketChunkData;
import net.minecraft.network.play.server.SPacketMultiBlockChange;
import net.minecraft.network.play.server.SPacketUnloadChunk;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerManager {
   private static final Predicate<EntityPlayerMP> field_187308_a = new Predicate<EntityPlayerMP>() {
      public boolean apply(EntityPlayerMP p_apply_1_) {
         return p_apply_1_ != null && !p_apply_1_.func_175149_v();
      }
   };
   private static final Predicate<EntityPlayerMP> field_187309_b = new Predicate<EntityPlayerMP>() {
      public boolean apply(EntityPlayerMP p_apply_1_) {
         return p_apply_1_ != null && (!p_apply_1_.func_175149_v() || p_apply_1_.func_71121_q().func_82736_K().func_82766_b("spectatorsGenerateChunks"));
      }
   };
   private final WorldServer field_72701_a;
   private final List<EntityPlayerMP> field_72699_b = Lists.<EntityPlayerMP>newArrayList();
   private final LongHashMap<PlayerManager.PlayerInstance> field_72700_c = new LongHashMap();
   private final Set<PlayerManager.PlayerInstance> field_72697_d = Sets.<PlayerManager.PlayerInstance>newHashSet();
   private final List<PlayerManager.PlayerInstance> field_187310_g = Lists.<PlayerManager.PlayerInstance>newLinkedList();
   private final List<PlayerManager.PlayerInstance> field_187311_h = Lists.<PlayerManager.PlayerInstance>newLinkedList();
   private final List<PlayerManager.PlayerInstance> field_111193_e = Lists.<PlayerManager.PlayerInstance>newArrayList();
   private int field_72698_e;
   private long field_111192_g;
   private boolean field_187312_l = true;
   private boolean field_187313_m = true;

   public PlayerManager(WorldServer p_i1176_1_) {
      this.field_72701_a = p_i1176_1_;
      this.func_152622_a(p_i1176_1_.func_73046_m().func_184103_al().func_72395_o());
   }

   public WorldServer func_72688_a() {
      return this.field_72701_a;
   }

   public Iterator<Chunk> func_187300_b() {
      final Iterator<PlayerManager.PlayerInstance> iterator = this.field_111193_e.iterator();
      return new AbstractIterator<Chunk>() {
         protected Chunk computeNext() {
            while(true) {
               if(iterator.hasNext()) {
                  PlayerManager.PlayerInstance playermanager$playerinstance = (PlayerManager.PlayerInstance)iterator.next();
                  Chunk chunk = playermanager$playerinstance.func_187266_f();
                  if(chunk == null) {
                     continue;
                  }

                  if(!chunk.func_177423_u() && chunk.func_177419_t()) {
                     return chunk;
                  }

                  if(!chunk.func_186035_j()) {
                     return chunk;
                  }

                  if(!playermanager$playerinstance.func_187271_a(128.0D, PlayerManager.field_187308_a)) {
                     continue;
                  }

                  return chunk;
               }

               return (Chunk)this.endOfData();
            }
         }
      };
   }

   public void func_72693_b() {
      long i = this.field_72701_a.func_82737_E();
      if(i - this.field_111192_g > 8000L) {
         this.field_111192_g = i;

         for(int j = 0; j < this.field_111193_e.size(); ++j) {
            PlayerManager.PlayerInstance playermanager$playerinstance = (PlayerManager.PlayerInstance)this.field_111193_e.get(j);
            playermanager$playerinstance.func_187280_d();
            playermanager$playerinstance.func_187279_c();
         }
      }

      if(!this.field_72697_d.isEmpty()) {
         for(PlayerManager.PlayerInstance playermanager$playerinstance2 : this.field_72697_d) {
            playermanager$playerinstance2.func_187280_d();
         }

         this.field_72697_d.clear();
      }

      if(this.field_187312_l && i % 4L == 0L) {
         this.field_187312_l = false;
         Collections.sort(this.field_187311_h, new Comparator<PlayerManager.PlayerInstance>() {
            public int compare(PlayerManager.PlayerInstance p_compare_1_, PlayerManager.PlayerInstance p_compare_2_) {
               return ComparisonChain.start().compare(p_compare_1_.func_187270_g(), p_compare_2_.func_187270_g()).result();
            }
         });
      }

      if(this.field_187313_m && i % 4L == 2L) {
         this.field_187313_m = false;
         Collections.sort(this.field_187310_g, new Comparator<PlayerManager.PlayerInstance>() {
            public int compare(PlayerManager.PlayerInstance p_compare_1_, PlayerManager.PlayerInstance p_compare_2_) {
               return ComparisonChain.start().compare(p_compare_1_.func_187270_g(), p_compare_2_.func_187270_g()).result();
            }
         });
      }

      if(!this.field_187311_h.isEmpty()) {
         long l = System.nanoTime() + 50000000L;
         int k = 49;
         Iterator<PlayerManager.PlayerInstance> iterator = this.field_187311_h.iterator();

         while(iterator.hasNext()) {
            PlayerManager.PlayerInstance playermanager$playerinstance1 = (PlayerManager.PlayerInstance)iterator.next();
            if(playermanager$playerinstance1.func_187266_f() == null) {
               boolean flag = playermanager$playerinstance1.func_187269_a(field_187309_b);
               if(playermanager$playerinstance1.func_187268_a(flag)) {
                  iterator.remove();
                  if(playermanager$playerinstance1.func_187272_b()) {
                     this.field_187310_g.remove(playermanager$playerinstance1);
                  }

                  --k;
                  if(k < 0 || System.nanoTime() > l) {
                     break;
                  }
               }
            }
         }
      }

      if(!this.field_187310_g.isEmpty()) {
         int i1 = 81;
         Iterator<PlayerManager.PlayerInstance> iterator1 = this.field_187310_g.iterator();

         while(iterator1.hasNext()) {
            PlayerManager.PlayerInstance playermanager$playerinstance3 = (PlayerManager.PlayerInstance)iterator1.next();
            if(playermanager$playerinstance3.func_187272_b()) {
               iterator1.remove();
               --i1;
               if(i1 < 0) {
                  break;
               }
            }
         }
      }

      if(this.field_72699_b.isEmpty()) {
         WorldProvider worldprovider = this.field_72701_a.field_73011_w;
         if(!worldprovider.func_76567_e()) {
            this.field_72701_a.func_72863_F().func_73240_a();
         }
      }

   }

   public boolean func_152621_a(int p_152621_1_, int p_152621_2_) {
      long i = func_187307_d(p_152621_1_, p_152621_2_);
      return this.field_72700_c.func_76164_a(i) != null;
   }

   public PlayerManager.PlayerInstance func_187301_b(int p_187301_1_, int p_187301_2_) {
      return (PlayerManager.PlayerInstance)this.field_72700_c.func_76164_a(func_187307_d(p_187301_1_, p_187301_2_));
   }

   private PlayerManager.PlayerInstance func_187302_c(int p_187302_1_, int p_187302_2_) {
      long i = func_187307_d(p_187302_1_, p_187302_2_);
      PlayerManager.PlayerInstance playermanager$playerinstance = (PlayerManager.PlayerInstance)this.field_72700_c.func_76164_a(i);
      if(playermanager$playerinstance == null) {
         playermanager$playerinstance = new PlayerManager.PlayerInstance(p_187302_1_, p_187302_2_);
         this.field_72700_c.func_76163_a(i, playermanager$playerinstance);
         this.field_111193_e.add(playermanager$playerinstance);
         if(playermanager$playerinstance.func_187266_f() == null) {
            this.field_187311_h.add(playermanager$playerinstance);
         }

         if(!playermanager$playerinstance.func_187272_b()) {
            this.field_187310_g.add(playermanager$playerinstance);
         }
      }

      return playermanager$playerinstance;
   }

   public void func_180244_a(BlockPos p_180244_1_) {
      int i = p_180244_1_.func_177958_n() >> 4;
      int j = p_180244_1_.func_177952_p() >> 4;
      PlayerManager.PlayerInstance playermanager$playerinstance = this.func_187301_b(i, j);
      if(playermanager$playerinstance != null) {
         playermanager$playerinstance.func_187265_a(p_180244_1_.func_177958_n() & 15, p_180244_1_.func_177956_o(), p_180244_1_.func_177952_p() & 15);
      }

   }

   public void func_72683_a(EntityPlayerMP p_72683_1_) {
      int i = (int)p_72683_1_.field_70165_t >> 4;
      int j = (int)p_72683_1_.field_70161_v >> 4;
      p_72683_1_.field_71131_d = p_72683_1_.field_70165_t;
      p_72683_1_.field_71132_e = p_72683_1_.field_70161_v;

      for(int k = i - this.field_72698_e; k <= i + this.field_72698_e; ++k) {
         for(int l = j - this.field_72698_e; l <= j + this.field_72698_e; ++l) {
            this.func_187302_c(k, l).func_187276_a(p_72683_1_);
         }
      }

      this.field_72699_b.add(p_72683_1_);
      this.func_187306_e();
   }

   public void func_72695_c(EntityPlayerMP p_72695_1_) {
      int i = (int)p_72695_1_.field_71131_d >> 4;
      int j = (int)p_72695_1_.field_71132_e >> 4;

      for(int k = i - this.field_72698_e; k <= i + this.field_72698_e; ++k) {
         for(int l = j - this.field_72698_e; l <= j + this.field_72698_e; ++l) {
            PlayerManager.PlayerInstance playermanager$playerinstance = this.func_187301_b(k, l);
            if(playermanager$playerinstance != null) {
               playermanager$playerinstance.func_187277_b(p_72695_1_);
            }
         }
      }

      this.field_72699_b.remove(p_72695_1_);
      this.func_187306_e();
   }

   private boolean func_72684_a(int p_72684_1_, int p_72684_2_, int p_72684_3_, int p_72684_4_, int p_72684_5_) {
      int i = p_72684_1_ - p_72684_3_;
      int j = p_72684_2_ - p_72684_4_;
      return i >= -p_72684_5_ && i <= p_72684_5_?j >= -p_72684_5_ && j <= p_72684_5_:false;
   }

   public void func_72685_d(EntityPlayerMP p_72685_1_) {
      int i = (int)p_72685_1_.field_70165_t >> 4;
      int j = (int)p_72685_1_.field_70161_v >> 4;
      double d0 = p_72685_1_.field_71131_d - p_72685_1_.field_70165_t;
      double d1 = p_72685_1_.field_71132_e - p_72685_1_.field_70161_v;
      double d2 = d0 * d0 + d1 * d1;
      if(d2 >= 64.0D) {
         int k = (int)p_72685_1_.field_71131_d >> 4;
         int l = (int)p_72685_1_.field_71132_e >> 4;
         int i1 = this.field_72698_e;
         int j1 = i - k;
         int k1 = j - l;
         if(j1 != 0 || k1 != 0) {
            for(int l1 = i - i1; l1 <= i + i1; ++l1) {
               for(int i2 = j - i1; i2 <= j + i1; ++i2) {
                  if(!this.func_72684_a(l1, i2, k, l, i1)) {
                     this.func_187302_c(l1, i2).func_187276_a(p_72685_1_);
                  }

                  if(!this.func_72684_a(l1 - j1, i2 - k1, i, j, i1)) {
                     PlayerManager.PlayerInstance playermanager$playerinstance = this.func_187301_b(l1 - j1, i2 - k1);
                     if(playermanager$playerinstance != null) {
                        playermanager$playerinstance.func_187277_b(p_72685_1_);
                     }
                  }
               }
            }

            p_72685_1_.field_71131_d = p_72685_1_.field_70165_t;
            p_72685_1_.field_71132_e = p_72685_1_.field_70161_v;
            this.func_187306_e();
         }
      }
   }

   public boolean func_72694_a(EntityPlayerMP p_72694_1_, int p_72694_2_, int p_72694_3_) {
      PlayerManager.PlayerInstance playermanager$playerinstance = this.func_187301_b(p_72694_2_, p_72694_3_);
      return playermanager$playerinstance != null && playermanager$playerinstance.func_187275_d(p_72694_1_) && playermanager$playerinstance.func_187274_e();
   }

   public void func_152622_a(int p_152622_1_) {
      p_152622_1_ = MathHelper.func_76125_a(p_152622_1_, 3, 32);
      if(p_152622_1_ != this.field_72698_e) {
         int i = p_152622_1_ - this.field_72698_e;

         for(EntityPlayerMP entityplayermp : Lists.newArrayList(this.field_72699_b)) {
            int j = (int)entityplayermp.field_70165_t >> 4;
            int k = (int)entityplayermp.field_70161_v >> 4;
            if(i > 0) {
               for(int j1 = j - p_152622_1_; j1 <= j + p_152622_1_; ++j1) {
                  for(int k1 = k - p_152622_1_; k1 <= k + p_152622_1_; ++k1) {
                     PlayerManager.PlayerInstance playermanager$playerinstance = this.func_187302_c(j1, k1);
                     if(!playermanager$playerinstance.func_187275_d(entityplayermp)) {
                        playermanager$playerinstance.func_187276_a(entityplayermp);
                     }
                  }
               }
            } else {
               for(int l = j - this.field_72698_e; l <= j + this.field_72698_e; ++l) {
                  for(int i1 = k - this.field_72698_e; i1 <= k + this.field_72698_e; ++i1) {
                     if(!this.func_72684_a(l, i1, j, k, p_152622_1_)) {
                        this.func_187302_c(l, i1).func_187277_b(entityplayermp);
                     }
                  }
               }
            }
         }

         this.field_72698_e = p_152622_1_;
         this.func_187306_e();
      }
   }

   private void func_187306_e() {
      this.field_187312_l = true;
      this.field_187313_m = true;
   }

   public static int func_72686_a(int p_72686_0_) {
      return p_72686_0_ * 16 - 16;
   }

   private static long func_187307_d(int p_187307_0_, int p_187307_1_) {
      return (long)p_187307_0_ + 2147483647L | (long)p_187307_1_ + 2147483647L << 32;
   }

   public void func_187304_a(PlayerManager.PlayerInstance p_187304_1_) {
      this.field_72697_d.add(p_187304_1_);
   }

   public void func_187305_b(PlayerManager.PlayerInstance p_187305_1_) {
      ChunkCoordIntPair chunkcoordintpair = p_187305_1_.func_187264_a();
      long i = func_187307_d(chunkcoordintpair.field_77276_a, chunkcoordintpair.field_77275_b);
      p_187305_1_.func_187279_c();
      this.field_72700_c.func_76159_d(i);
      this.field_111193_e.remove(p_187305_1_);
      this.field_72697_d.remove(p_187305_1_);
      this.field_187310_g.remove(p_187305_1_);
      this.field_187311_h.remove(p_187305_1_);
      this.func_72688_a().func_72863_F().func_73241_b(chunkcoordintpair.field_77276_a, chunkcoordintpair.field_77275_b);
   }

   public class PlayerInstance {
      private static final Logger field_187281_a = LogManager.getLogger();
      private final List<EntityPlayerMP> field_187283_c = Lists.<EntityPlayerMP>newArrayList();
      private final ChunkCoordIntPair field_187284_d;
      private final short[] field_187285_e = new short[64];
      private Chunk field_187286_f;
      private int field_187287_g;
      private int field_187288_h;
      private long field_187289_i;
      private boolean field_187290_j;

      public PlayerInstance(int p_i1518_2_, int p_i1518_3_) {
         this.field_187284_d = new ChunkCoordIntPair(p_i1518_2_, p_i1518_3_);
         this.field_187286_f = PlayerManager.this.func_72688_a().func_72863_F().func_186028_c(p_i1518_2_, p_i1518_3_);
      }

      public ChunkCoordIntPair func_187264_a() {
         return this.field_187284_d;
      }

      public void func_187276_a(EntityPlayerMP p_187276_1_) {
         if(this.field_187283_c.contains(p_187276_1_)) {
            field_187281_a.debug("Failed to add player. {} already is in chunk {}, {}", new Object[]{p_187276_1_, Integer.valueOf(this.field_187284_d.field_77276_a), Integer.valueOf(this.field_187284_d.field_77275_b)});
         } else {
            if(this.field_187283_c.isEmpty()) {
               this.field_187289_i = PlayerManager.this.func_72688_a().func_82737_E();
            }

            this.field_187283_c.add(p_187276_1_);
            if(this.field_187290_j) {
               this.func_187278_c(p_187276_1_);
            }

         }
      }

      public void func_187277_b(EntityPlayerMP p_187277_1_) {
         if(this.field_187283_c.contains(p_187277_1_)) {
            if(this.field_187290_j) {
               p_187277_1_.field_71135_a.func_147359_a(new SPacketUnloadChunk(this.field_187284_d.field_77276_a, this.field_187284_d.field_77275_b));
            }

            this.field_187283_c.remove(p_187277_1_);
            if(this.field_187283_c.isEmpty()) {
               PlayerManager.this.func_187305_b(this);
            }

         }
      }

      public boolean func_187268_a(boolean p_187268_1_) {
         if(this.field_187286_f != null) {
            return true;
         } else {
            if(p_187268_1_) {
               this.field_187286_f = PlayerManager.this.func_72688_a().func_72863_F().func_186025_d(this.field_187284_d.field_77276_a, this.field_187284_d.field_77275_b);
            } else {
               this.field_187286_f = PlayerManager.this.func_72688_a().func_72863_F().func_186028_c(this.field_187284_d.field_77276_a, this.field_187284_d.field_77275_b);
            }

            return this.field_187286_f != null;
         }
      }

      public boolean func_187272_b() {
         if(this.field_187290_j) {
            return true;
         } else if(this.field_187286_f == null) {
            return false;
         } else if(!this.field_187286_f.func_150802_k()) {
            return false;
         } else {
            this.field_187287_g = 0;
            this.field_187288_h = 0;
            this.field_187290_j = true;
            List<TileEntity> list = Lists.newArrayList(PlayerManager.this.func_72688_a().func_147486_a(this.field_187284_d.field_77276_a * 16, 0, this.field_187284_d.field_77275_b * 16, this.field_187284_d.field_77276_a * 16 + 16, 256, this.field_187284_d.field_77275_b * 16 + 16));
            SPacketChunkData spacketchunkdata = new SPacketChunkData(this.field_187286_f, true, '\uffff');

            for(EntityPlayerMP entityplayermp : this.field_187283_c) {
               entityplayermp.field_71135_a.func_147359_a(spacketchunkdata);

               for(TileEntity tileentity : list) {
                  Packet<?> packet = tileentity.func_145844_m();
                  if(packet != null) {
                     entityplayermp.field_71135_a.func_147359_a(packet);
                  }
               }

               PlayerManager.this.func_72688_a().func_73039_n().func_85172_a(entityplayermp, this.field_187286_f);
            }

            return true;
         }
      }

      public void func_187278_c(EntityPlayerMP p_187278_1_) {
         if(this.field_187290_j) {
            p_187278_1_.field_71135_a.func_147359_a(new SPacketChunkData(this.field_187286_f, true, '\uffff'));

            for(TileEntity tileentity : PlayerManager.this.func_72688_a().func_147486_a(this.field_187284_d.field_77276_a * 16, 0, this.field_187284_d.field_77275_b * 16, this.field_187284_d.field_77276_a * 16 + 16, 256, this.field_187284_d.field_77275_b * 16 + 16)) {
               Packet<?> packet = tileentity.func_145844_m();
               if(packet != null) {
                  p_187278_1_.field_71135_a.func_147359_a(packet);
               }
            }

            PlayerManager.this.func_72688_a().func_73039_n().func_85172_a(p_187278_1_, this.field_187286_f);
         }
      }

      public void func_187279_c() {
         if(this.field_187286_f != null) {
            this.field_187286_f.func_177415_c(this.field_187286_f.func_177416_w() + PlayerManager.this.func_72688_a().func_82737_E() - this.field_187289_i);
         }

         this.field_187289_i = PlayerManager.this.func_72688_a().func_82737_E();
      }

      public void func_187265_a(int p_187265_1_, int p_187265_2_, int p_187265_3_) {
         if(this.field_187290_j) {
            if(this.field_187287_g == 0) {
               PlayerManager.this.func_187304_a(this);
            }

            this.field_187288_h |= 1 << (p_187265_2_ >> 4);
            if(this.field_187287_g < 64) {
               short short1 = (short)(p_187265_1_ << 12 | p_187265_3_ << 8 | p_187265_2_);

               for(int i = 0; i < this.field_187287_g; ++i) {
                  if(this.field_187285_e[i] == short1) {
                     return;
                  }
               }

               this.field_187285_e[this.field_187287_g++] = short1;
            }

         }
      }

      public void func_187267_a(Packet<?> p_187267_1_) {
         if(this.field_187290_j) {
            for(int i = 0; i < this.field_187283_c.size(); ++i) {
               ((EntityPlayerMP)this.field_187283_c.get(i)).field_71135_a.func_147359_a(p_187267_1_);
            }

         }
      }

      public void func_187280_d() {
         if(this.field_187290_j && this.field_187286_f != null) {
            if(this.field_187287_g != 0) {
               if(this.field_187287_g == 1) {
                  int i = (this.field_187285_e[0] >> 12 & 15) + this.field_187284_d.field_77276_a * 16;
                  int j = this.field_187285_e[0] & 255;
                  int k = (this.field_187285_e[0] >> 8 & 15) + this.field_187284_d.field_77275_b * 16;
                  BlockPos blockpos = new BlockPos(i, j, k);
                  this.func_187267_a(new SPacketBlockChange(PlayerManager.this.func_72688_a(), blockpos));
                  if(PlayerManager.this.func_72688_a().func_180495_p(blockpos).func_177230_c().func_149716_u()) {
                     this.func_187273_a(PlayerManager.this.func_72688_a().func_175625_s(blockpos));
                  }
               } else if(this.field_187287_g == 64) {
                  int i1 = this.field_187284_d.field_77276_a * 16;
                  int k1 = this.field_187284_d.field_77275_b * 16;
                  this.func_187267_a(new SPacketChunkData(this.field_187286_f, false, this.field_187288_h));

                  for(int i2 = 0; i2 < 16; ++i2) {
                     if((this.field_187288_h & 1 << i2) != 0) {
                        int k2 = i2 << 4;
                        List<TileEntity> list = PlayerManager.this.func_72688_a().func_147486_a(i1, k2, k1, i1 + 16, k2 + 16, k1 + 16);

                        for(int l = 0; l < list.size(); ++l) {
                           this.func_187273_a((TileEntity)list.get(l));
                        }
                     }
                  }
               } else {
                  this.func_187267_a(new SPacketMultiBlockChange(this.field_187287_g, this.field_187285_e, this.field_187286_f));

                  for(int j1 = 0; j1 < this.field_187287_g; ++j1) {
                     int l1 = (this.field_187285_e[j1] >> 12 & 15) + this.field_187284_d.field_77276_a * 16;
                     int j2 = this.field_187285_e[j1] & 255;
                     int l2 = (this.field_187285_e[j1] >> 8 & 15) + this.field_187284_d.field_77275_b * 16;
                     BlockPos blockpos1 = new BlockPos(l1, j2, l2);
                     if(PlayerManager.this.func_72688_a().func_180495_p(blockpos1).func_177230_c().func_149716_u()) {
                        this.func_187273_a(PlayerManager.this.func_72688_a().func_175625_s(blockpos1));
                     }
                  }
               }

               this.field_187287_g = 0;
               this.field_187288_h = 0;
            }
         }
      }

      private void func_187273_a(TileEntity p_187273_1_) {
         if(p_187273_1_ != null) {
            Packet<?> packet = p_187273_1_.func_145844_m();
            if(packet != null) {
               this.func_187267_a(packet);
            }
         }

      }

      public boolean func_187275_d(EntityPlayerMP p_187275_1_) {
         return this.field_187283_c.contains(p_187275_1_);
      }

      public boolean func_187269_a(Predicate<EntityPlayerMP> p_187269_1_) {
         return Iterables.tryFind(this.field_187283_c, p_187269_1_).isPresent();
      }

      public boolean func_187271_a(double p_187271_1_, Predicate<EntityPlayerMP> p_187271_3_) {
         int i = 0;

         for(int j = this.field_187283_c.size(); i < j; ++i) {
            EntityPlayerMP entityplayermp = (EntityPlayerMP)this.field_187283_c.get(i);
            if(p_187271_3_.apply(entityplayermp) && this.field_187284_d.func_185327_a(entityplayermp) < p_187271_1_ * p_187271_1_) {
               return true;
            }
         }

         return false;
      }

      public boolean func_187274_e() {
         return this.field_187290_j;
      }

      public Chunk func_187266_f() {
         return this.field_187286_f;
      }

      public double func_187270_g() {
         double d0 = Double.MAX_VALUE;

         for(EntityPlayerMP entityplayermp : this.field_187283_c) {
            double d1 = this.field_187284_d.func_185327_a(entityplayermp);
            if(d1 < d0) {
               d0 = d1;
            }
         }

         return d0;
      }
   }
}
