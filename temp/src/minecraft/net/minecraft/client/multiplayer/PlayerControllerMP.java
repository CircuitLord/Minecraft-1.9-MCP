package net.minecraft.client.multiplayer;

import io.netty.buffer.Unpooled;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketClickWindow;
import net.minecraft.network.play.client.CPacketCreativeInventoryAction;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.network.play.client.CPacketEnchantItem;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerBlockPlacement;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;

public class PlayerControllerMP {
   private final Minecraft field_78776_a;
   private final NetHandlerPlayClient field_78774_b;
   private BlockPos field_178895_c = new BlockPos(-1, -1, -1);
   private ItemStack field_85183_f;
   private float field_78770_f;
   private float field_78780_h;
   private int field_78781_i;
   private boolean field_78778_j;
   private WorldSettings.GameType field_78779_k = WorldSettings.GameType.SURVIVAL;
   private int field_78777_l;

   public PlayerControllerMP(Minecraft p_i45062_1_, NetHandlerPlayClient p_i45062_2_) {
      this.field_78776_a = p_i45062_1_;
      this.field_78774_b = p_i45062_2_;
   }

   public static void func_178891_a(Minecraft p_178891_0_, PlayerControllerMP p_178891_1_, BlockPos p_178891_2_, EnumFacing p_178891_3_) {
      if(!p_178891_0_.field_71441_e.func_175719_a(p_178891_0_.field_71439_g, p_178891_2_, p_178891_3_)) {
         p_178891_1_.func_187103_a(p_178891_2_);
      }

   }

   public void func_78748_a(EntityPlayer p_78748_1_) {
      this.field_78779_k.func_77147_a(p_78748_1_.field_71075_bZ);
   }

   public boolean func_78747_a() {
      return this.field_78779_k == WorldSettings.GameType.SPECTATOR;
   }

   public void func_78746_a(WorldSettings.GameType p_78746_1_) {
      this.field_78779_k = p_78746_1_;
      this.field_78779_k.func_77147_a(this.field_78776_a.field_71439_g.field_71075_bZ);
   }

   public void func_78745_b(EntityPlayer p_78745_1_) {
      p_78745_1_.field_70177_z = -180.0F;
   }

   public boolean func_78755_b() {
      return this.field_78779_k.func_77144_e();
   }

   public boolean func_187103_a(BlockPos p_187103_1_) {
      if(this.field_78779_k.func_82752_c()) {
         if(this.field_78779_k == WorldSettings.GameType.SPECTATOR) {
            return false;
         }

         if(!this.field_78776_a.field_71439_g.func_175142_cm()) {
            ItemStack itemstack = this.field_78776_a.field_71439_g.func_184614_ca();
            if(itemstack == null) {
               return false;
            }

            if(!itemstack.func_179544_c(this.field_78776_a.field_71441_e.func_180495_p(p_187103_1_).func_177230_c())) {
               return false;
            }
         }
      }

      if(this.field_78779_k.func_77145_d() && this.field_78776_a.field_71439_g.func_184614_ca() != null && this.field_78776_a.field_71439_g.func_184614_ca().func_77973_b() instanceof ItemSword) {
         return false;
      } else {
         World world = this.field_78776_a.field_71441_e;
         IBlockState iblockstate = world.func_180495_p(p_187103_1_);
         Block block = iblockstate.func_177230_c();
         if(block instanceof BlockCommandBlock && !this.field_78776_a.field_71439_g.func_70003_b(2, "")) {
            return false;
         } else if(iblockstate.func_185904_a() == Material.field_151579_a) {
            return false;
         } else {
            world.func_175718_b(2001, p_187103_1_, Block.func_176210_f(iblockstate));
            block.func_176208_a(world, p_187103_1_, iblockstate, this.field_78776_a.field_71439_g);
            boolean flag = world.func_180501_a(p_187103_1_, Blocks.field_150350_a.func_176223_P(), 11);
            if(flag) {
               block.func_176206_d(world, p_187103_1_, iblockstate);
            }

            this.field_178895_c = new BlockPos(this.field_178895_c.func_177958_n(), -1, this.field_178895_c.func_177952_p());
            if(!this.field_78779_k.func_77145_d()) {
               ItemStack itemstack1 = this.field_78776_a.field_71439_g.func_184614_ca();
               if(itemstack1 != null) {
                  itemstack1.func_179548_a(world, iblockstate, p_187103_1_, this.field_78776_a.field_71439_g);
                  if(itemstack1.field_77994_a == 0) {
                     this.field_78776_a.field_71439_g.func_184611_a(EnumHand.MAIN_HAND, (ItemStack)null);
                  }
               }
            }

            return flag;
         }
      }
   }

   public boolean func_180511_b(BlockPos p_180511_1_, EnumFacing p_180511_2_) {
      if(this.field_78779_k.func_82752_c()) {
         if(this.field_78779_k == WorldSettings.GameType.SPECTATOR) {
            return false;
         }

         if(!this.field_78776_a.field_71439_g.func_175142_cm()) {
            ItemStack itemstack = this.field_78776_a.field_71439_g.func_184614_ca();
            if(itemstack == null) {
               return false;
            }

            if(!itemstack.func_179544_c(this.field_78776_a.field_71441_e.func_180495_p(p_180511_1_).func_177230_c())) {
               return false;
            }
         }
      }

      if(!this.field_78776_a.field_71441_e.func_175723_af().func_177746_a(p_180511_1_)) {
         return false;
      } else {
         if(this.field_78779_k.func_77145_d()) {
            this.field_78774_b.func_147297_a(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, p_180511_1_, p_180511_2_));
            func_178891_a(this.field_78776_a, this, p_180511_1_, p_180511_2_);
            this.field_78781_i = 5;
         } else if(!this.field_78778_j || !this.func_178893_a(p_180511_1_)) {
            if(this.field_78778_j) {
               this.field_78774_b.func_147297_a(new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.field_178895_c, p_180511_2_));
            }

            this.field_78774_b.func_147297_a(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, p_180511_1_, p_180511_2_));
            IBlockState iblockstate = this.field_78776_a.field_71441_e.func_180495_p(p_180511_1_);
            boolean flag = iblockstate.func_185904_a() != Material.field_151579_a;
            if(flag && this.field_78770_f == 0.0F) {
               iblockstate.func_177230_c().func_180649_a(this.field_78776_a.field_71441_e, p_180511_1_, this.field_78776_a.field_71439_g);
            }

            if(flag && iblockstate.func_185903_a(this.field_78776_a.field_71439_g, this.field_78776_a.field_71439_g.field_70170_p, p_180511_1_) >= 1.0F) {
               this.func_187103_a(p_180511_1_);
            } else {
               this.field_78778_j = true;
               this.field_178895_c = p_180511_1_;
               this.field_85183_f = this.field_78776_a.field_71439_g.func_184614_ca();
               this.field_78770_f = 0.0F;
               this.field_78780_h = 0.0F;
               this.field_78776_a.field_71441_e.func_175715_c(this.field_78776_a.field_71439_g.func_145782_y(), this.field_178895_c, (int)(this.field_78770_f * 10.0F) - 1);
            }
         }

         return true;
      }
   }

   public void func_78767_c() {
      if(this.field_78778_j) {
         this.field_78774_b.func_147297_a(new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.field_178895_c, EnumFacing.DOWN));
         this.field_78778_j = false;
         this.field_78770_f = 0.0F;
         this.field_78776_a.field_71441_e.func_175715_c(this.field_78776_a.field_71439_g.func_145782_y(), this.field_178895_c, -1);
         this.field_78776_a.field_71439_g.func_184821_cY();
      }

   }

   public boolean func_180512_c(BlockPos p_180512_1_, EnumFacing p_180512_2_) {
      this.func_78750_j();
      if(this.field_78781_i > 0) {
         --this.field_78781_i;
         return true;
      } else if(this.field_78779_k.func_77145_d() && this.field_78776_a.field_71441_e.func_175723_af().func_177746_a(p_180512_1_)) {
         this.field_78781_i = 5;
         this.field_78774_b.func_147297_a(new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, p_180512_1_, p_180512_2_));
         func_178891_a(this.field_78776_a, this, p_180512_1_, p_180512_2_);
         return true;
      } else if(this.func_178893_a(p_180512_1_)) {
         IBlockState iblockstate = this.field_78776_a.field_71441_e.func_180495_p(p_180512_1_);
         Block block = iblockstate.func_177230_c();
         if(iblockstate.func_185904_a() == Material.field_151579_a) {
            this.field_78778_j = false;
            return false;
         } else {
            this.field_78770_f += iblockstate.func_185903_a(this.field_78776_a.field_71439_g, this.field_78776_a.field_71439_g.field_70170_p, p_180512_1_);
            if(this.field_78780_h % 4.0F == 0.0F) {
               SoundType soundtype = block.func_185467_w();
               this.field_78776_a.func_147118_V().func_147682_a(new PositionedSoundRecord(soundtype.func_185846_f(), SoundCategory.NEUTRAL, (soundtype.func_185843_a() + 1.0F) / 8.0F, soundtype.func_185847_b() * 0.5F, p_180512_1_));
            }

            ++this.field_78780_h;
            if(this.field_78770_f >= 1.0F) {
               this.field_78778_j = false;
               this.field_78774_b.func_147297_a(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, p_180512_1_, p_180512_2_));
               this.func_187103_a(p_180512_1_);
               this.field_78770_f = 0.0F;
               this.field_78780_h = 0.0F;
               this.field_78781_i = 5;
            }

            this.field_78776_a.field_71441_e.func_175715_c(this.field_78776_a.field_71439_g.func_145782_y(), this.field_178895_c, (int)(this.field_78770_f * 10.0F) - 1);
            return true;
         }
      } else {
         return this.func_180511_b(p_180512_1_, p_180512_2_);
      }
   }

   public float func_78757_d() {
      return this.field_78779_k.func_77145_d()?5.0F:4.5F;
   }

   public void func_78765_e() {
      this.func_78750_j();
      if(this.field_78774_b.func_147298_b().func_150724_d()) {
         this.field_78774_b.func_147298_b().func_74428_b();
      } else {
         this.field_78774_b.func_147298_b().func_179293_l();
      }

   }

   private boolean func_178893_a(BlockPos p_178893_1_) {
      ItemStack itemstack = this.field_78776_a.field_71439_g.func_184614_ca();
      boolean flag = this.field_85183_f == null && itemstack == null;
      if(this.field_85183_f != null && itemstack != null) {
         flag = itemstack.func_77973_b() == this.field_85183_f.func_77973_b() && ItemStack.func_77970_a(itemstack, this.field_85183_f) && (itemstack.func_77984_f() || itemstack.func_77960_j() == this.field_85183_f.func_77960_j());
      }

      return p_178893_1_.equals(this.field_178895_c) && flag;
   }

   private void func_78750_j() {
      int i = this.field_78776_a.field_71439_g.field_71071_by.field_70461_c;
      if(i != this.field_78777_l) {
         this.field_78777_l = i;
         this.field_78774_b.func_147297_a(new CPacketHeldItemChange(this.field_78777_l));
      }

   }

   public EnumActionResult func_187099_a(EntityPlayerSP p_187099_1_, WorldClient p_187099_2_, ItemStack p_187099_3_, BlockPos p_187099_4_, EnumFacing p_187099_5_, Vec3d p_187099_6_, EnumHand p_187099_7_) {
      this.func_78750_j();
      float f = (float)(p_187099_6_.field_72450_a - (double)p_187099_4_.func_177958_n());
      float f1 = (float)(p_187099_6_.field_72448_b - (double)p_187099_4_.func_177956_o());
      float f2 = (float)(p_187099_6_.field_72449_c - (double)p_187099_4_.func_177952_p());
      boolean flag = false;
      if(!this.field_78776_a.field_71441_e.func_175723_af().func_177746_a(p_187099_4_)) {
         return EnumActionResult.FAIL;
      } else {
         if(this.field_78779_k != WorldSettings.GameType.SPECTATOR) {
            IBlockState iblockstate = p_187099_2_.func_180495_p(p_187099_4_);
            if((!p_187099_1_.func_70093_af() || p_187099_1_.func_184614_ca() == null && p_187099_1_.func_184592_cb() == null) && iblockstate.func_177230_c().func_180639_a(p_187099_2_, p_187099_4_, iblockstate, p_187099_1_, p_187099_7_, p_187099_3_, p_187099_5_, f, f1, f2)) {
               flag = true;
            }

            if(!flag && p_187099_3_ != null && p_187099_3_.func_77973_b() instanceof ItemBlock) {
               ItemBlock itemblock = (ItemBlock)p_187099_3_.func_77973_b();
               if(!itemblock.func_179222_a(p_187099_2_, p_187099_4_, p_187099_5_, p_187099_1_, p_187099_3_)) {
                  return EnumActionResult.FAIL;
               }
            }
         }

         this.field_78774_b.func_147297_a(new CPacketPlayerTryUseItem(p_187099_4_, p_187099_5_, p_187099_7_, f, f1, f2));
         if(!flag && this.field_78779_k != WorldSettings.GameType.SPECTATOR) {
            if(p_187099_3_ == null) {
               return EnumActionResult.PASS;
            } else if(p_187099_1_.func_184811_cZ().func_185141_a(p_187099_3_.func_77973_b())) {
               return EnumActionResult.PASS;
            } else if(p_187099_3_.func_77973_b() instanceof ItemBlock && ((ItemBlock)p_187099_3_.func_77973_b()).func_179223_d() instanceof BlockCommandBlock && !p_187099_1_.func_70003_b(2, "")) {
               return EnumActionResult.FAIL;
            } else if(this.field_78779_k.func_77145_d()) {
               int i = p_187099_3_.func_77960_j();
               int j = p_187099_3_.field_77994_a;
               EnumActionResult enumactionresult = p_187099_3_.func_179546_a(p_187099_1_, p_187099_2_, p_187099_4_, p_187099_7_, p_187099_5_, f, f1, f2);
               p_187099_3_.func_77964_b(i);
               p_187099_3_.field_77994_a = j;
               return enumactionresult;
            } else {
               return p_187099_3_.func_179546_a(p_187099_1_, p_187099_2_, p_187099_4_, p_187099_7_, p_187099_5_, f, f1, f2);
            }
         } else {
            return EnumActionResult.SUCCESS;
         }
      }
   }

   public EnumActionResult func_187101_a(EntityPlayer p_187101_1_, World p_187101_2_, ItemStack p_187101_3_, EnumHand p_187101_4_) {
      if(this.field_78779_k == WorldSettings.GameType.SPECTATOR) {
         return EnumActionResult.PASS;
      } else {
         this.func_78750_j();
         this.field_78774_b.func_147297_a(new CPacketPlayerBlockPlacement(p_187101_4_));
         if(p_187101_1_.func_184811_cZ().func_185141_a(p_187101_3_.func_77973_b())) {
            return EnumActionResult.PASS;
         } else {
            int i = p_187101_3_.field_77994_a;
            ActionResult<ItemStack> actionresult = p_187101_3_.func_77957_a(p_187101_2_, p_187101_1_, p_187101_4_);
            ItemStack itemstack = (ItemStack)actionresult.func_188398_b();
            if(itemstack != p_187101_3_ || itemstack.field_77994_a != i) {
               p_187101_1_.func_184611_a(p_187101_4_, itemstack);
               if(itemstack.field_77994_a == 0) {
                  p_187101_1_.func_184611_a(p_187101_4_, (ItemStack)null);
               }
            }

            return actionresult.func_188397_a();
         }
      }
   }

   public EntityPlayerSP func_178892_a(World p_178892_1_, StatFileWriter p_178892_2_) {
      return new EntityPlayerSP(this.field_78776_a, p_178892_1_, this.field_78774_b, p_178892_2_);
   }

   public void func_78764_a(EntityPlayer p_78764_1_, Entity p_78764_2_) {
      this.func_78750_j();
      this.field_78774_b.func_147297_a(new CPacketUseEntity(p_78764_2_));
      if(this.field_78779_k != WorldSettings.GameType.SPECTATOR) {
         p_78764_1_.func_71059_n(p_78764_2_);
         p_78764_1_.func_184821_cY();
      }

   }

   public EnumActionResult func_187097_a(EntityPlayer p_187097_1_, Entity p_187097_2_, ItemStack p_187097_3_, EnumHand p_187097_4_) {
      this.func_78750_j();
      this.field_78774_b.func_147297_a(new CPacketUseEntity(p_187097_2_, p_187097_4_));
      return this.field_78779_k == WorldSettings.GameType.SPECTATOR?EnumActionResult.PASS:p_187097_1_.func_184822_a(p_187097_2_, p_187097_3_, p_187097_4_);
   }

   public EnumActionResult func_187102_a(EntityPlayer p_187102_1_, Entity p_187102_2_, RayTraceResult p_187102_3_, ItemStack p_187102_4_, EnumHand p_187102_5_) {
      this.func_78750_j();
      Vec3d vec3d = new Vec3d(p_187102_3_.field_72307_f.field_72450_a - p_187102_2_.field_70165_t, p_187102_3_.field_72307_f.field_72448_b - p_187102_2_.field_70163_u, p_187102_3_.field_72307_f.field_72449_c - p_187102_2_.field_70161_v);
      this.field_78774_b.func_147297_a(new CPacketUseEntity(p_187102_2_, p_187102_5_, vec3d));
      return this.field_78779_k == WorldSettings.GameType.SPECTATOR?EnumActionResult.PASS:p_187102_2_.func_184199_a(p_187102_1_, vec3d, p_187102_4_, p_187102_5_);
   }

   public ItemStack func_187098_a(int p_187098_1_, int p_187098_2_, int p_187098_3_, ClickType p_187098_4_, EntityPlayer p_187098_5_) {
      short short1 = p_187098_5_.field_71070_bA.func_75136_a(p_187098_5_.field_71071_by);
      ItemStack itemstack = p_187098_5_.field_71070_bA.func_184996_a(p_187098_2_, p_187098_3_, p_187098_4_, p_187098_5_);
      this.field_78774_b.func_147297_a(new CPacketClickWindow(p_187098_1_, p_187098_2_, p_187098_3_, p_187098_4_, itemstack, short1));
      return itemstack;
   }

   public void func_78756_a(int p_78756_1_, int p_78756_2_) {
      this.field_78774_b.func_147297_a(new CPacketEnchantItem(p_78756_1_, p_78756_2_));
   }

   public void func_78761_a(ItemStack p_78761_1_, int p_78761_2_) {
      if(this.field_78779_k.func_77145_d()) {
         this.field_78774_b.func_147297_a(new CPacketCreativeInventoryAction(p_78761_2_, p_78761_1_));
      }

   }

   public void func_78752_a(ItemStack p_78752_1_) {
      if(this.field_78779_k.func_77145_d() && p_78752_1_ != null) {
         this.field_78774_b.func_147297_a(new CPacketCreativeInventoryAction(-1, p_78752_1_));
      }

   }

   public void func_78766_c(EntityPlayer p_78766_1_) {
      this.func_78750_j();
      this.field_78774_b.func_147297_a(new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.field_177992_a, EnumFacing.DOWN));
      p_78766_1_.func_184597_cx();
   }

   public boolean func_78763_f() {
      return this.field_78779_k.func_77144_e();
   }

   public boolean func_78762_g() {
      return !this.field_78779_k.func_77145_d();
   }

   public boolean func_78758_h() {
      return this.field_78779_k.func_77145_d();
   }

   public boolean func_78749_i() {
      return this.field_78779_k.func_77145_d();
   }

   public boolean func_110738_j() {
      return this.field_78776_a.field_71439_g.func_184218_aH() && this.field_78776_a.field_71439_g.func_184187_bx() instanceof EntityHorse;
   }

   public boolean func_178887_k() {
      return this.field_78779_k == WorldSettings.GameType.SPECTATOR;
   }

   public WorldSettings.GameType func_178889_l() {
      return this.field_78779_k;
   }

   public boolean func_181040_m() {
      return this.field_78778_j;
   }

   public void func_187100_a(int p_187100_1_) {
      this.field_78774_b.func_147297_a(new CPacketCustomPayload("MC|PickItem", (new PacketBuffer(Unpooled.buffer())).func_150787_b(p_187100_1_)));
   }
}
