package net.minecraft.network;

import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.util.concurrent.Futures;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IJumpingMount;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecartCommandBlock;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.item.ItemWrittenBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketClickWindow;
import net.minecraft.network.play.client.CPacketClientSettings;
import net.minecraft.network.play.client.CPacketClientStatus;
import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.client.CPacketConfirmTransaction;
import net.minecraft.network.play.client.CPacketCreativeInventoryAction;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.network.play.client.CPacketEnchantItem;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketInput;
import net.minecraft.network.play.client.CPacketKeepAlive;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerAbilities;
import net.minecraft.network.play.client.CPacketPlayerBlockPlacement;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.play.client.CPacketResourcePackStatus;
import net.minecraft.network.play.client.CPacketSpectate;
import net.minecraft.network.play.client.CPacketSteerBoat;
import net.minecraft.network.play.client.CPacketTabComplete;
import net.minecraft.network.play.client.CPacketUpdateSign;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.client.CPacketVehicleMove;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.network.play.server.SPacketConfirmTransaction;
import net.minecraft.network.play.server.SPacketDisconnect;
import net.minecraft.network.play.server.SPacketHeldItemChange;
import net.minecraft.network.play.server.SPacketKeepAlive;
import net.minecraft.network.play.server.SPacketMoveVehicle;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraft.network.play.server.SPacketSetSlot;
import net.minecraft.network.play.server.SPacketTabComplete;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.CommandBlockBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.tileentity.TileEntityStructure;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.Mirror;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetHandlerPlayServer implements INetHandlerPlayServer, ITickable {
   private static final Logger field_147370_c = LogManager.getLogger();
   public final NetworkManager field_147371_a;
   private final MinecraftServer field_147367_d;
   public EntityPlayerMP field_147369_b;
   private int field_147368_e;
   private int field_147378_h;
   private long field_147379_i;
   private long field_147377_k;
   private int field_147374_l;
   private int field_147375_m;
   private final IntHashMap<Short> field_147372_n = new IntHashMap();
   private double field_184349_l;
   private double field_184350_m;
   private double field_184351_n;
   private double field_184352_o;
   private double field_184353_p;
   private double field_184354_q;
   private Entity field_184355_r;
   private double field_184356_s;
   private double field_184357_t;
   private double field_184358_u;
   private double field_184359_v;
   private double field_184360_w;
   private double field_184361_x;
   private Vec3d field_184362_y;
   private int field_184363_z;
   private int field_184343_A;
   private boolean field_184344_B;
   private int field_147365_f;
   private boolean field_184345_D;
   private int field_184346_E;
   private int field_184347_F;
   private int field_184348_G;

   public NetHandlerPlayServer(MinecraftServer p_i1530_1_, NetworkManager p_i1530_2_, EntityPlayerMP p_i1530_3_) {
      this.field_147367_d = p_i1530_1_;
      this.field_147371_a = p_i1530_2_;
      p_i1530_2_.func_150719_a(this);
      this.field_147369_b = p_i1530_3_;
      p_i1530_3_.field_71135_a = this;
   }

   public void func_73660_a() {
      this.func_184342_d();
      this.field_147369_b.func_71127_g();
      this.field_147369_b.func_70080_a(this.field_184349_l, this.field_184350_m, this.field_184351_n, this.field_147369_b.field_70177_z, this.field_147369_b.field_70125_A);
      ++this.field_147368_e;
      this.field_184348_G = this.field_184347_F;
      if(this.field_184344_B) {
         if(++this.field_147365_f > 80) {
            field_147370_c.warn(this.field_147369_b.func_70005_c_() + " was kicked for floating too long!");
            this.func_147360_c("Flying is not enabled on this server");
            return;
         }
      } else {
         this.field_184344_B = false;
         this.field_147365_f = 0;
      }

      this.field_184355_r = this.field_147369_b.func_184208_bv();
      if(this.field_184355_r != this.field_147369_b && this.field_184355_r.func_184179_bs() == this.field_147369_b) {
         this.field_184356_s = this.field_184355_r.field_70165_t;
         this.field_184357_t = this.field_184355_r.field_70163_u;
         this.field_184358_u = this.field_184355_r.field_70161_v;
         this.field_184359_v = this.field_184355_r.field_70165_t;
         this.field_184360_w = this.field_184355_r.field_70163_u;
         this.field_184361_x = this.field_184355_r.field_70161_v;
         if(this.field_184345_D && this.field_147369_b.func_184208_bv().func_184179_bs() == this.field_147369_b) {
            if(++this.field_184346_E > 80) {
               field_147370_c.warn(this.field_147369_b.func_70005_c_() + " was kicked for floating a vehicle too long!");
               this.func_147360_c("Flying is not enabled on this server");
               return;
            }
         } else {
            this.field_184345_D = false;
            this.field_184346_E = 0;
         }
      } else {
         this.field_184355_r = null;
         this.field_184345_D = false;
         this.field_184346_E = 0;
      }

      this.field_147367_d.field_71304_b.func_76320_a("keepAlive");
      if((long)this.field_147368_e - this.field_147377_k > 40L) {
         this.field_147377_k = (long)this.field_147368_e;
         this.field_147379_i = this.func_147363_d();
         this.field_147378_h = (int)this.field_147379_i;
         this.func_147359_a(new SPacketKeepAlive(this.field_147378_h));
      }

      this.field_147367_d.field_71304_b.func_76319_b();
      if(this.field_147374_l > 0) {
         --this.field_147374_l;
      }

      if(this.field_147375_m > 0) {
         --this.field_147375_m;
      }

      if(this.field_147369_b.func_154331_x() > 0L && this.field_147367_d.func_143007_ar() > 0 && MinecraftServer.func_130071_aq() - this.field_147369_b.func_154331_x() > (long)(this.field_147367_d.func_143007_ar() * 1000 * 60)) {
         this.func_147360_c("You have been idle for too long!");
      }

   }

   private void func_184342_d() {
      this.field_184349_l = this.field_147369_b.field_70165_t;
      this.field_184350_m = this.field_147369_b.field_70163_u;
      this.field_184351_n = this.field_147369_b.field_70161_v;
      this.field_184352_o = this.field_147369_b.field_70165_t;
      this.field_184353_p = this.field_147369_b.field_70163_u;
      this.field_184354_q = this.field_147369_b.field_70161_v;
   }

   public NetworkManager func_147362_b() {
      return this.field_147371_a;
   }

   public void func_147360_c(String p_147360_1_) {
      final TextComponentString textcomponentstring = new TextComponentString(p_147360_1_);
      this.field_147371_a.func_179288_a(new SPacketDisconnect(textcomponentstring), new GenericFutureListener<Future<? super Void>>() {
         public void operationComplete(Future<? super Void> p_operationComplete_1_) throws Exception {
            NetHandlerPlayServer.this.field_147371_a.func_150718_a(textcomponentstring);
         }
      }, new GenericFutureListener[0]);
      this.field_147371_a.func_150721_g();
      Futures.getUnchecked(this.field_147367_d.func_152344_a(new Runnable() {
         public void run() {
            NetHandlerPlayServer.this.field_147371_a.func_179293_l();
         }
      }));
   }

   public void func_147358_a(CPacketInput p_147358_1_) {
      PacketThreadUtil.func_180031_a(p_147358_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_110430_a(p_147358_1_.func_149620_c(), p_147358_1_.func_149616_d(), p_147358_1_.func_149618_e(), p_147358_1_.func_149617_f());
   }

   private static boolean func_183006_b(CPacketPlayer p_183006_0_) {
      return Doubles.isFinite(p_183006_0_.func_186997_a(0.0D)) && Doubles.isFinite(p_183006_0_.func_186996_b(0.0D)) && Doubles.isFinite(p_183006_0_.func_187000_c(0.0D)) && Floats.isFinite(p_183006_0_.func_186998_b(0.0F)) && Floats.isFinite(p_183006_0_.func_186999_a(0.0F))?false:Math.abs(p_183006_0_.func_186997_a(0.0D)) <= 3.0E7D && Math.abs(p_183006_0_.func_186997_a(0.0D)) <= 3.0E7D;
   }

   private static boolean func_184341_b(CPacketVehicleMove p_184341_0_) {
      return !Doubles.isFinite(p_184341_0_.func_187004_a()) || !Doubles.isFinite(p_184341_0_.func_187002_b()) || !Doubles.isFinite(p_184341_0_.func_187003_c()) || !Floats.isFinite(p_184341_0_.func_187005_e()) || !Floats.isFinite(p_184341_0_.func_187006_d());
   }

   public void func_184338_a(CPacketVehicleMove p_184338_1_) {
      PacketThreadUtil.func_180031_a(p_184338_1_, this, this.field_147369_b.func_71121_q());
      if(func_184341_b(p_184338_1_)) {
         this.func_147360_c("Invalid move vehicle packet received");
      } else {
         Entity entity = this.field_147369_b.func_184208_bv();
         if(entity != this.field_147369_b && entity.func_184179_bs() == this.field_147369_b && entity == this.field_184355_r) {
            WorldServer worldserver = this.field_147369_b.func_71121_q();
            double d0 = entity.field_70165_t;
            double d1 = entity.field_70163_u;
            double d2 = entity.field_70161_v;
            double d3 = p_184338_1_.func_187004_a();
            double d4 = p_184338_1_.func_187002_b();
            double d5 = p_184338_1_.func_187003_c();
            float f = p_184338_1_.func_187006_d();
            float f1 = p_184338_1_.func_187005_e();
            double d6 = d3 - this.field_184356_s;
            double d7 = d4 - this.field_184357_t;
            double d8 = d5 - this.field_184358_u;
            double d9 = entity.field_70159_w * entity.field_70159_w + entity.field_70181_x * entity.field_70181_x + entity.field_70179_y * entity.field_70179_y;
            double d10 = d6 * d6 + d7 * d7 + d8 * d8;
            if(d10 - d9 > 100.0D && (!this.field_147367_d.func_71264_H() || !this.field_147367_d.func_71214_G().equals(entity.func_70005_c_()))) {
               field_147370_c.warn(entity.func_70005_c_() + " (vehicle of " + this.field_147369_b.func_70005_c_() + ") moved too quickly! " + d6 + "," + d7 + "," + d8);
               this.field_147371_a.func_179290_a(new SPacketMoveVehicle(entity));
               return;
            }

            boolean flag = worldserver.func_184144_a(entity, entity.func_174813_aQ().func_186664_h(0.0625D)).isEmpty();
            d6 = d3 - this.field_184359_v;
            d7 = d4 - this.field_184360_w - 1.0E-6D;
            d8 = d5 - this.field_184361_x;
            entity.func_70091_d(d6, d7, d8);
            double d11 = d7;
            d6 = d3 - entity.field_70165_t;
            d7 = d4 - entity.field_70163_u;
            if(d7 > -0.5D || d7 < 0.5D) {
               d7 = 0.0D;
            }

            d8 = d5 - entity.field_70161_v;
            d10 = d6 * d6 + d7 * d7 + d8 * d8;
            boolean flag1 = false;
            if(d10 > 0.0625D) {
               flag1 = true;
               field_147370_c.warn(entity.func_70005_c_() + " moved wrongly!");
            }

            entity.func_70080_a(d3, d4, d5, f, f1);
            boolean flag2 = worldserver.func_184144_a(entity, entity.func_174813_aQ().func_186664_h(0.0625D)).isEmpty();
            if(flag && (flag1 || !flag2)) {
               entity.func_70080_a(d0, d1, d2, f, f1);
               this.field_147371_a.func_179290_a(new SPacketMoveVehicle(entity));
               return;
            }

            this.field_147367_d.func_184103_al().func_72358_d(this.field_147369_b);
            this.field_147369_b.func_71000_j(this.field_147369_b.field_70165_t - d0, this.field_147369_b.field_70163_u - d1, this.field_147369_b.field_70161_v - d2);
            this.field_184345_D = d11 >= -0.03125D && !this.field_147367_d.func_71231_X() && !worldserver.func_72829_c(entity.func_174813_aQ().func_186662_g(0.0625D).func_72321_a(0.0D, -0.55D, 0.0D));
            this.field_184359_v = entity.field_70165_t;
            this.field_184360_w = entity.field_70163_u;
            this.field_184361_x = entity.field_70161_v;
         }

      }
   }

   public void func_184339_a(CPacketConfirmTeleport p_184339_1_) {
      PacketThreadUtil.func_180031_a(p_184339_1_, this, this.field_147369_b.func_71121_q());
      if(p_184339_1_.func_186987_a() == this.field_184363_z) {
         this.field_147369_b.func_70080_a(this.field_184362_y.field_72450_a, this.field_184362_y.field_72448_b, this.field_184362_y.field_72449_c, this.field_147369_b.field_70177_z, this.field_147369_b.field_70125_A);
         if(this.field_147369_b.func_184850_K()) {
            this.field_184352_o = this.field_184362_y.field_72450_a;
            this.field_184353_p = this.field_184362_y.field_72448_b;
            this.field_184354_q = this.field_184362_y.field_72449_c;
            this.field_147369_b.func_184846_L();
         }

         this.field_184362_y = null;
      }

   }

   public void func_147347_a(CPacketPlayer p_147347_1_) {
      PacketThreadUtil.func_180031_a(p_147347_1_, this, this.field_147369_b.func_71121_q());
      if(func_183006_b(p_147347_1_)) {
         this.func_147360_c("Invalid move player packet received");
      } else {
         WorldServer worldserver = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);
         if(!this.field_147369_b.field_71136_j) {
            if(this.field_147368_e == 0) {
               this.func_184342_d();
            }

            if(this.field_184362_y != null) {
               if(this.field_147368_e - this.field_184343_A > 20) {
                  this.field_184343_A = this.field_147368_e;
                  this.func_147364_a(this.field_184362_y.field_72450_a, this.field_184362_y.field_72448_b, this.field_184362_y.field_72449_c, this.field_147369_b.field_70177_z, this.field_147369_b.field_70125_A);
               }

            } else {
               this.field_184343_A = this.field_147368_e;
               if(this.field_147369_b.func_184218_aH()) {
                  this.field_147369_b.func_70080_a(this.field_147369_b.field_70165_t, this.field_147369_b.field_70163_u, this.field_147369_b.field_70161_v, p_147347_1_.func_186999_a(this.field_147369_b.field_70177_z), p_147347_1_.func_186998_b(this.field_147369_b.field_70125_A));
                  this.field_147367_d.func_184103_al().func_72358_d(this.field_147369_b);
               } else {
                  double d0 = this.field_147369_b.field_70165_t;
                  double d1 = this.field_147369_b.field_70163_u;
                  double d2 = this.field_147369_b.field_70161_v;
                  double d3 = this.field_147369_b.field_70163_u;
                  double d4 = p_147347_1_.func_186997_a(this.field_147369_b.field_70165_t);
                  double d5 = p_147347_1_.func_186996_b(this.field_147369_b.field_70163_u);
                  double d6 = p_147347_1_.func_187000_c(this.field_147369_b.field_70161_v);
                  float f = p_147347_1_.func_186999_a(this.field_147369_b.field_70177_z);
                  float f1 = p_147347_1_.func_186998_b(this.field_147369_b.field_70125_A);
                  double d7 = d4 - this.field_184349_l;
                  double d8 = d5 - this.field_184350_m;
                  double d9 = d6 - this.field_184351_n;
                  double d10 = this.field_147369_b.field_70159_w * this.field_147369_b.field_70159_w + this.field_147369_b.field_70181_x * this.field_147369_b.field_70181_x + this.field_147369_b.field_70179_y * this.field_147369_b.field_70179_y;
                  double d11 = d7 * d7 + d8 * d8 + d9 * d9;
                  ++this.field_184347_F;
                  int i = this.field_184347_F - this.field_184348_G;
                  if(i > 5) {
                     field_147370_c.debug(this.field_147369_b.func_70005_c_() + " is sending move packets too frequently (" + i + " packets since last tick)");
                     i = 1;
                  }

                  if(!this.field_147369_b.func_184850_K() && (!this.field_147369_b.func_71121_q().func_82736_K().func_82766_b("disableElytraMovementCheck") || !this.field_147369_b.func_184613_cA())) {
                     float f2 = this.field_147369_b.func_184613_cA()?300.0F:100.0F;
                     if(d11 - d10 > (double)(f2 * (float)i) && (!this.field_147367_d.func_71264_H() || !this.field_147367_d.func_71214_G().equals(this.field_147369_b.func_70005_c_()))) {
                        field_147370_c.warn(this.field_147369_b.func_70005_c_() + " moved too quickly! " + d7 + "," + d8 + "," + d9);
                        this.func_147364_a(this.field_147369_b.field_70165_t, this.field_147369_b.field_70163_u, this.field_147369_b.field_70161_v, this.field_147369_b.field_70177_z, this.field_147369_b.field_70125_A);
                        return;
                     }
                  }

                  boolean flag2 = worldserver.func_184144_a(this.field_147369_b, this.field_147369_b.func_174813_aQ().func_186664_h(0.0625D)).isEmpty();
                  d7 = d4 - this.field_184352_o;
                  d8 = d5 - this.field_184353_p;
                  d9 = d6 - this.field_184354_q;
                  if(this.field_147369_b.field_70122_E && !p_147347_1_.func_149465_i() && d8 > 0.0D) {
                     this.field_147369_b.func_70664_aZ();
                  }

                  this.field_147369_b.func_70091_d(d7, d8, d9);
                  this.field_147369_b.field_70122_E = p_147347_1_.func_149465_i();
                  double d12 = d8;
                  d7 = d4 - this.field_147369_b.field_70165_t;
                  d8 = d5 - this.field_147369_b.field_70163_u;
                  if(d8 > -0.5D || d8 < 0.5D) {
                     d8 = 0.0D;
                  }

                  d9 = d6 - this.field_147369_b.field_70161_v;
                  d11 = d7 * d7 + d8 * d8 + d9 * d9;
                  boolean flag = false;
                  if(!this.field_147369_b.func_184850_K() && d11 > 0.0625D && !this.field_147369_b.func_70608_bn() && !this.field_147369_b.field_71134_c.func_73083_d() && this.field_147369_b.field_71134_c.func_73081_b() != WorldSettings.GameType.SPECTATOR) {
                     flag = true;
                     field_147370_c.warn(this.field_147369_b.func_70005_c_() + " moved wrongly!");
                  }

                  this.field_147369_b.func_70080_a(d4, d5, d6, f, f1);
                  this.field_147369_b.func_71000_j(this.field_147369_b.field_70165_t - d0, this.field_147369_b.field_70163_u - d1, this.field_147369_b.field_70161_v - d2);
                  if(!this.field_147369_b.field_70145_X && !this.field_147369_b.func_70608_bn()) {
                     boolean flag1 = worldserver.func_184144_a(this.field_147369_b, this.field_147369_b.func_174813_aQ().func_186664_h(0.0625D)).isEmpty();
                     if(flag2 && (flag || !flag1)) {
                        this.func_147364_a(d0, d1, d2, f, f1);
                        return;
                     }
                  }

                  this.field_184344_B = d12 >= -0.03125D;
                  this.field_184344_B &= !this.field_147367_d.func_71231_X() && !this.field_147369_b.field_71075_bZ.field_75101_c;
                  this.field_184344_B &= !this.field_147369_b.func_70644_a(MobEffects.field_188424_y) && !this.field_147369_b.func_184613_cA() && !worldserver.func_72829_c(this.field_147369_b.func_174813_aQ().func_186662_g(0.0625D).func_72321_a(0.0D, -0.55D, 0.0D));
                  this.field_147369_b.field_70122_E = p_147347_1_.func_149465_i();
                  this.field_147367_d.func_184103_al().func_72358_d(this.field_147369_b);
                  this.field_147369_b.func_71122_b(this.field_147369_b.field_70163_u - d3, p_147347_1_.func_149465_i());
                  this.field_184352_o = this.field_147369_b.field_70165_t;
                  this.field_184353_p = this.field_147369_b.field_70163_u;
                  this.field_184354_q = this.field_147369_b.field_70161_v;
               }
            }
         }
      }
   }

   public void func_147364_a(double p_147364_1_, double p_147364_3_, double p_147364_5_, float p_147364_7_, float p_147364_8_) {
      this.func_175089_a(p_147364_1_, p_147364_3_, p_147364_5_, p_147364_7_, p_147364_8_, Collections.<SPacketPlayerPosLook.EnumFlags>emptySet());
   }

   public void func_175089_a(double p_175089_1_, double p_175089_3_, double p_175089_5_, float p_175089_7_, float p_175089_8_, Set<SPacketPlayerPosLook.EnumFlags> p_175089_9_) {
      double d0 = p_175089_9_.contains(SPacketPlayerPosLook.EnumFlags.X)?this.field_147369_b.field_70165_t:0.0D;
      double d1 = p_175089_9_.contains(SPacketPlayerPosLook.EnumFlags.Y)?this.field_147369_b.field_70163_u:0.0D;
      double d2 = p_175089_9_.contains(SPacketPlayerPosLook.EnumFlags.Z)?this.field_147369_b.field_70161_v:0.0D;
      this.field_184362_y = new Vec3d(p_175089_1_ + d0, p_175089_3_ + d1, p_175089_5_ + d2);
      float f = p_175089_7_;
      float f1 = p_175089_8_;
      if(p_175089_9_.contains(SPacketPlayerPosLook.EnumFlags.Y_ROT)) {
         f = p_175089_7_ + this.field_147369_b.field_70177_z;
      }

      if(p_175089_9_.contains(SPacketPlayerPosLook.EnumFlags.X_ROT)) {
         f1 = p_175089_8_ + this.field_147369_b.field_70125_A;
      }

      if(++this.field_184363_z == Integer.MAX_VALUE) {
         this.field_184363_z = 0;
      }

      this.field_184343_A = this.field_147368_e;
      this.field_147369_b.func_70080_a(this.field_184362_y.field_72450_a, this.field_184362_y.field_72448_b, this.field_184362_y.field_72449_c, f, f1);
      this.field_147369_b.field_71135_a.func_147359_a(new SPacketPlayerPosLook(p_175089_1_, p_175089_3_, p_175089_5_, p_175089_7_, p_175089_8_, p_175089_9_, this.field_184363_z));
   }

   public void func_147345_a(CPacketPlayerDigging p_147345_1_) {
      PacketThreadUtil.func_180031_a(p_147345_1_, this, this.field_147369_b.func_71121_q());
      WorldServer worldserver = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);
      BlockPos blockpos = p_147345_1_.func_179715_a();
      this.field_147369_b.func_143004_u();
      switch(p_147345_1_.func_180762_c()) {
      case SWAP_HELD_ITEMS:
         if(!this.field_147369_b.func_175149_v()) {
            ItemStack itemstack1 = this.field_147369_b.func_184586_b(EnumHand.OFF_HAND);
            this.field_147369_b.func_184611_a(EnumHand.OFF_HAND, this.field_147369_b.func_184586_b(EnumHand.MAIN_HAND));
            this.field_147369_b.func_184611_a(EnumHand.MAIN_HAND, itemstack1);
         }

         return;
      case DROP_ITEM:
         if(!this.field_147369_b.func_175149_v()) {
            this.field_147369_b.func_71040_bB(false);
         }

         return;
      case DROP_ALL_ITEMS:
         if(!this.field_147369_b.func_175149_v()) {
            this.field_147369_b.func_71040_bB(true);
         }

         return;
      case RELEASE_USE_ITEM:
         this.field_147369_b.func_184597_cx();
         ItemStack itemstack = this.field_147369_b.func_184614_ca();
         if(itemstack != null && itemstack.field_77994_a == 0) {
            this.field_147369_b.func_184611_a(EnumHand.MAIN_HAND, (ItemStack)null);
         }

         return;
      case START_DESTROY_BLOCK:
      case ABORT_DESTROY_BLOCK:
      case STOP_DESTROY_BLOCK:
         double d0 = this.field_147369_b.field_70165_t - ((double)blockpos.func_177958_n() + 0.5D);
         double d1 = this.field_147369_b.field_70163_u - ((double)blockpos.func_177956_o() + 0.5D) + 1.5D;
         double d2 = this.field_147369_b.field_70161_v - ((double)blockpos.func_177952_p() + 0.5D);
         double d3 = d0 * d0 + d1 * d1 + d2 * d2;
         if(d3 > 36.0D) {
            return;
         } else if(blockpos.func_177956_o() >= this.field_147367_d.func_71207_Z()) {
            return;
         } else {
            if(p_147345_1_.func_180762_c() == CPacketPlayerDigging.Action.START_DESTROY_BLOCK) {
               if(!this.field_147367_d.func_175579_a(worldserver, blockpos, this.field_147369_b) && worldserver.func_175723_af().func_177746_a(blockpos)) {
                  this.field_147369_b.field_71134_c.func_180784_a(blockpos, p_147345_1_.func_179714_b());
               } else {
                  this.field_147369_b.field_71135_a.func_147359_a(new SPacketBlockChange(worldserver, blockpos));
               }
            } else {
               if(p_147345_1_.func_180762_c() == CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK) {
                  this.field_147369_b.field_71134_c.func_180785_a(blockpos);
               } else if(p_147345_1_.func_180762_c() == CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK) {
                  this.field_147369_b.field_71134_c.func_180238_e();
               }

               if(worldserver.func_180495_p(blockpos).func_185904_a() != Material.field_151579_a) {
                  this.field_147369_b.field_71135_a.func_147359_a(new SPacketBlockChange(worldserver, blockpos));
               }
            }

            return;
         }
      default:
         throw new IllegalArgumentException("Invalid player action");
      }
   }

   public void func_184337_a(CPacketPlayerTryUseItem p_184337_1_) {
      PacketThreadUtil.func_180031_a(p_184337_1_, this, this.field_147369_b.func_71121_q());
      WorldServer worldserver = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);
      EnumHand enumhand = p_184337_1_.func_187022_c();
      ItemStack itemstack = this.field_147369_b.func_184586_b(enumhand);
      BlockPos blockpos = p_184337_1_.func_187023_a();
      EnumFacing enumfacing = p_184337_1_.func_187024_b();
      this.field_147369_b.func_143004_u();
      if(blockpos.func_177956_o() < this.field_147367_d.func_71207_Z() - 1 || enumfacing != EnumFacing.UP && blockpos.func_177956_o() < this.field_147367_d.func_71207_Z()) {
         if(this.field_184362_y == null && this.field_147369_b.func_70092_e((double)blockpos.func_177958_n() + 0.5D, (double)blockpos.func_177956_o() + 0.5D, (double)blockpos.func_177952_p() + 0.5D) < 64.0D && !this.field_147367_d.func_175579_a(worldserver, blockpos, this.field_147369_b) && worldserver.func_175723_af().func_177746_a(blockpos)) {
            this.field_147369_b.field_71134_c.func_187251_a(this.field_147369_b, worldserver, itemstack, enumhand, blockpos, enumfacing, p_184337_1_.func_187026_d(), p_184337_1_.func_187025_e(), p_184337_1_.func_187020_f());
         }
      } else {
         TextComponentTranslation textcomponenttranslation = new TextComponentTranslation("build.tooHigh", new Object[]{Integer.valueOf(this.field_147367_d.func_71207_Z())});
         textcomponenttranslation.func_150256_b().func_150238_a(TextFormatting.RED);
         this.field_147369_b.field_71135_a.func_147359_a(new SPacketChat(textcomponenttranslation));
      }

      this.field_147369_b.field_71135_a.func_147359_a(new SPacketBlockChange(worldserver, blockpos));
      this.field_147369_b.field_71135_a.func_147359_a(new SPacketBlockChange(worldserver, blockpos.func_177972_a(enumfacing)));
      itemstack = this.field_147369_b.func_184586_b(enumhand);
      if(itemstack != null && itemstack.field_77994_a == 0) {
         this.field_147369_b.func_184611_a(enumhand, (ItemStack)null);
         itemstack = null;
      }

   }

   public void func_147346_a(CPacketPlayerBlockPlacement p_147346_1_) {
      PacketThreadUtil.func_180031_a(p_147346_1_, this, this.field_147369_b.func_71121_q());
      WorldServer worldserver = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);
      EnumHand enumhand = p_147346_1_.func_187028_a();
      ItemStack itemstack = this.field_147369_b.func_184586_b(enumhand);
      this.field_147369_b.func_143004_u();
      if(itemstack != null) {
         this.field_147369_b.field_71134_c.func_187250_a(this.field_147369_b, worldserver, itemstack, enumhand);
         itemstack = this.field_147369_b.func_184586_b(enumhand);
         if(itemstack != null && itemstack.field_77994_a == 0) {
            this.field_147369_b.func_184611_a(enumhand, (ItemStack)null);
            itemstack = null;
         }

      }
   }

   public void func_175088_a(CPacketSpectate p_175088_1_) {
      PacketThreadUtil.func_180031_a(p_175088_1_, this, this.field_147369_b.func_71121_q());
      if(this.field_147369_b.func_175149_v()) {
         Entity entity = null;

         for(WorldServer worldserver : this.field_147367_d.field_71305_c) {
            if(worldserver != null) {
               entity = p_175088_1_.func_179727_a(worldserver);
               if(entity != null) {
                  break;
               }
            }
         }

         if(entity != null) {
            this.field_147369_b.func_175399_e(this.field_147369_b);
            this.field_147369_b.func_184210_p();
            if(entity.field_70170_p != this.field_147369_b.field_70170_p) {
               WorldServer worldserver1 = this.field_147369_b.func_71121_q();
               WorldServer worldserver2 = (WorldServer)entity.field_70170_p;
               this.field_147369_b.field_71093_bK = entity.field_71093_bK;
               this.func_147359_a(new SPacketRespawn(this.field_147369_b.field_71093_bK, worldserver1.func_175659_aa(), worldserver1.func_72912_H().func_76067_t(), this.field_147369_b.field_71134_c.func_73081_b()));
               this.field_147367_d.func_184103_al().func_187243_f(this.field_147369_b);
               worldserver1.func_72973_f(this.field_147369_b);
               this.field_147369_b.field_70128_L = false;
               this.field_147369_b.func_70012_b(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entity.field_70177_z, entity.field_70125_A);
               if(this.field_147369_b.func_70089_S()) {
                  worldserver1.func_72866_a(this.field_147369_b, false);
                  worldserver2.func_72838_d(this.field_147369_b);
                  worldserver2.func_72866_a(this.field_147369_b, false);
               }

               this.field_147369_b.func_70029_a(worldserver2);
               this.field_147367_d.func_184103_al().func_72375_a(this.field_147369_b, worldserver1);
               this.field_147369_b.func_70634_a(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
               this.field_147369_b.field_71134_c.func_73080_a(worldserver2);
               this.field_147367_d.func_184103_al().func_72354_b(this.field_147369_b, worldserver2);
               this.field_147367_d.func_184103_al().func_72385_f(this.field_147369_b);
            } else {
               this.field_147369_b.func_70634_a(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
            }
         }
      }

   }

   public void func_175086_a(CPacketResourcePackStatus p_175086_1_) {
   }

   public void func_184340_a(CPacketSteerBoat p_184340_1_) {
      PacketThreadUtil.func_180031_a(p_184340_1_, this, this.field_147369_b.func_71121_q());
      Entity entity = this.field_147369_b.func_184187_bx();
      if(entity instanceof EntityBoat) {
         ((EntityBoat)entity).func_184445_a(p_184340_1_.func_187012_a(), p_184340_1_.func_187014_b());
      }

   }

   public void func_147231_a(ITextComponent p_147231_1_) {
      field_147370_c.info(this.field_147369_b.func_70005_c_() + " lost connection: " + p_147231_1_);
      this.field_147367_d.func_147132_au();
      TextComponentTranslation textcomponenttranslation = new TextComponentTranslation("multiplayer.player.left", new Object[]{this.field_147369_b.func_145748_c_()});
      textcomponenttranslation.func_150256_b().func_150238_a(TextFormatting.YELLOW);
      this.field_147367_d.func_184103_al().func_148539_a(textcomponenttranslation);
      this.field_147369_b.func_71123_m();
      this.field_147367_d.func_184103_al().func_72367_e(this.field_147369_b);
      if(this.field_147367_d.func_71264_H() && this.field_147369_b.func_70005_c_().equals(this.field_147367_d.func_71214_G())) {
         field_147370_c.info("Stopping singleplayer server as player logged out");
         this.field_147367_d.func_71263_m();
      }

   }

   public void func_147359_a(final Packet<?> p_147359_1_) {
      if(p_147359_1_ instanceof SPacketChat) {
         SPacketChat spacketchat = (SPacketChat)p_147359_1_;
         EntityPlayer.EnumChatVisibility entityplayer$enumchatvisibility = this.field_147369_b.func_147096_v();
         if(entityplayer$enumchatvisibility == EntityPlayer.EnumChatVisibility.HIDDEN) {
            return;
         }

         if(entityplayer$enumchatvisibility == EntityPlayer.EnumChatVisibility.SYSTEM && !spacketchat.func_148916_d()) {
            return;
         }
      }

      try {
         this.field_147371_a.func_179290_a(p_147359_1_);
      } catch (Throwable throwable) {
         CrashReport crashreport = CrashReport.func_85055_a(throwable, "Sending packet");
         CrashReportCategory crashreportcategory = crashreport.func_85058_a("Packet being sent");
         crashreportcategory.func_71500_a("Packet class", new Callable<String>() {
            public String call() throws Exception {
               return p_147359_1_.getClass().getCanonicalName();
            }
         });
         throw new ReportedException(crashreport);
      }
   }

   public void func_147355_a(CPacketHeldItemChange p_147355_1_) {
      PacketThreadUtil.func_180031_a(p_147355_1_, this, this.field_147369_b.func_71121_q());
      if(p_147355_1_.func_149614_c() >= 0 && p_147355_1_.func_149614_c() < InventoryPlayer.func_70451_h()) {
         this.field_147369_b.field_71071_by.field_70461_c = p_147355_1_.func_149614_c();
         this.field_147369_b.func_143004_u();
      } else {
         field_147370_c.warn(this.field_147369_b.func_70005_c_() + " tried to set an invalid carried item");
      }
   }

   public void func_147354_a(CPacketChatMessage p_147354_1_) {
      PacketThreadUtil.func_180031_a(p_147354_1_, this, this.field_147369_b.func_71121_q());
      if(this.field_147369_b.func_147096_v() == EntityPlayer.EnumChatVisibility.HIDDEN) {
         TextComponentTranslation textcomponenttranslation = new TextComponentTranslation("chat.cannotSend", new Object[0]);
         textcomponenttranslation.func_150256_b().func_150238_a(TextFormatting.RED);
         this.func_147359_a(new SPacketChat(textcomponenttranslation));
      } else {
         this.field_147369_b.func_143004_u();
         String s = p_147354_1_.func_149439_c();
         s = StringUtils.normalizeSpace(s);

         for(int i = 0; i < s.length(); ++i) {
            if(!ChatAllowedCharacters.func_71566_a(s.charAt(i))) {
               this.func_147360_c("Illegal characters in chat");
               return;
            }
         }

         if(s.startsWith("/")) {
            this.func_147361_d(s);
         } else {
            ITextComponent itextcomponent = new TextComponentTranslation("chat.type.text", new Object[]{this.field_147369_b.func_145748_c_(), s});
            this.field_147367_d.func_184103_al().func_148544_a(itextcomponent, false);
         }

         this.field_147374_l += 20;
         if(this.field_147374_l > 200 && !this.field_147367_d.func_184103_al().func_152596_g(this.field_147369_b.func_146103_bH())) {
            this.func_147360_c("disconnect.spam");
         }

      }
   }

   private void func_147361_d(String p_147361_1_) {
      this.field_147367_d.func_71187_D().func_71556_a(this.field_147369_b, p_147361_1_);
   }

   public void func_175087_a(CPacketAnimation p_175087_1_) {
      PacketThreadUtil.func_180031_a(p_175087_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_143004_u();
      this.field_147369_b.func_184609_a(p_175087_1_.func_187018_a());
   }

   public void func_147357_a(CPacketEntityAction p_147357_1_) {
      PacketThreadUtil.func_180031_a(p_147357_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_143004_u();
      switch(p_147357_1_.func_180764_b()) {
      case START_SNEAKING:
         this.field_147369_b.func_70095_a(true);
         break;
      case STOP_SNEAKING:
         this.field_147369_b.func_70095_a(false);
         break;
      case START_SPRINTING:
         this.field_147369_b.func_70031_b(true);
         break;
      case STOP_SPRINTING:
         this.field_147369_b.func_70031_b(false);
         break;
      case STOP_SLEEPING:
         this.field_147369_b.func_70999_a(false, true, true);
         this.field_184362_y = new Vec3d(this.field_147369_b.field_70165_t, this.field_147369_b.field_70163_u, this.field_147369_b.field_70161_v);
         break;
      case START_RIDING_JUMP:
         if(this.field_147369_b.func_184187_bx() instanceof IJumpingMount) {
            IJumpingMount ijumpingmount1 = (IJumpingMount)this.field_147369_b.func_184187_bx();
            int i = p_147357_1_.func_149512_e();
            if(ijumpingmount1.func_184776_b() && i > 0) {
               ijumpingmount1.func_184775_b(i);
            }
         }
         break;
      case STOP_RIDING_JUMP:
         if(this.field_147369_b.func_184187_bx() instanceof IJumpingMount) {
            IJumpingMount ijumpingmount = (IJumpingMount)this.field_147369_b.func_184187_bx();
            ijumpingmount.func_184777_r_();
         }
         break;
      case OPEN_INVENTORY:
         if(this.field_147369_b.func_184187_bx() instanceof EntityHorse) {
            ((EntityHorse)this.field_147369_b.func_184187_bx()).func_110199_f(this.field_147369_b);
         }
         break;
      case START_FALL_FLYING:
         if(!this.field_147369_b.field_70122_E && this.field_147369_b.field_70181_x < 0.0D && !this.field_147369_b.func_184613_cA() && !this.field_147369_b.func_70090_H()) {
            ItemStack itemstack = this.field_147369_b.func_184582_a(EntityEquipmentSlot.CHEST);
            if(itemstack != null && itemstack.func_77973_b() == Items.field_185160_cR && ItemElytra.func_185069_d(itemstack)) {
               this.field_147369_b.func_184847_M();
            }
         } else {
            this.field_147369_b.func_189103_N();
         }
         break;
      default:
         throw new IllegalArgumentException("Invalid client command!");
      }

   }

   public void func_147340_a(CPacketUseEntity p_147340_1_) {
      PacketThreadUtil.func_180031_a(p_147340_1_, this, this.field_147369_b.func_71121_q());
      WorldServer worldserver = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);
      Entity entity = p_147340_1_.func_149564_a(worldserver);
      this.field_147369_b.func_143004_u();
      if(entity != null) {
         boolean flag = this.field_147369_b.func_70685_l(entity);
         double d0 = 36.0D;
         if(!flag) {
            d0 = 9.0D;
         }

         if(this.field_147369_b.func_70068_e(entity) < d0) {
            if(p_147340_1_.func_149565_c() == CPacketUseEntity.Action.INTERACT) {
               EnumHand enumhand = p_147340_1_.func_186994_b();
               ItemStack itemstack = this.field_147369_b.func_184586_b(enumhand);
               this.field_147369_b.func_184822_a(entity, itemstack, enumhand);
            } else if(p_147340_1_.func_149565_c() == CPacketUseEntity.Action.INTERACT_AT) {
               EnumHand enumhand1 = p_147340_1_.func_186994_b();
               ItemStack itemstack1 = this.field_147369_b.func_184586_b(enumhand1);
               entity.func_184199_a(this.field_147369_b, p_147340_1_.func_179712_b(), itemstack1, enumhand1);
            } else if(p_147340_1_.func_149565_c() == CPacketUseEntity.Action.ATTACK) {
               if(entity instanceof EntityItem || entity instanceof EntityXPOrb || entity instanceof EntityArrow || entity == this.field_147369_b) {
                  this.func_147360_c("Attempting to attack an invalid entity");
                  this.field_147367_d.func_71236_h("Player " + this.field_147369_b.func_70005_c_() + " tried to attack an invalid entity");
                  return;
               }

               this.field_147369_b.func_71059_n(entity);
            }
         }
      }

   }

   public void func_147342_a(CPacketClientStatus p_147342_1_) {
      PacketThreadUtil.func_180031_a(p_147342_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_143004_u();
      CPacketClientStatus.State cpacketclientstatus$state = p_147342_1_.func_149435_c();
      switch(cpacketclientstatus$state) {
      case PERFORM_RESPAWN:
         if(this.field_147369_b.field_71136_j) {
            this.field_147369_b.field_71136_j = false;
            this.field_147369_b = this.field_147367_d.func_184103_al().func_72368_a(this.field_147369_b, 0, true);
         } else {
            if(this.field_147369_b.func_110143_aJ() > 0.0F) {
               return;
            }

            this.field_147369_b = this.field_147367_d.func_184103_al().func_72368_a(this.field_147369_b, 0, false);
            if(this.field_147367_d.func_71199_h()) {
               this.field_147369_b.func_71033_a(WorldSettings.GameType.SPECTATOR);
               this.field_147369_b.func_71121_q().func_82736_K().func_82764_b("spectatorsGenerateChunks", "false");
            }
         }
         break;
      case REQUEST_STATS:
         this.field_147369_b.func_147099_x().func_150876_a(this.field_147369_b);
         break;
      case OPEN_INVENTORY_ACHIEVEMENT:
         this.field_147369_b.func_71029_a(AchievementList.field_187982_f);
      }

   }

   public void func_147356_a(CPacketCloseWindow p_147356_1_) {
      PacketThreadUtil.func_180031_a(p_147356_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_71128_l();
   }

   public void func_147351_a(CPacketClickWindow p_147351_1_) {
      PacketThreadUtil.func_180031_a(p_147351_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_143004_u();
      if(this.field_147369_b.field_71070_bA.field_75152_c == p_147351_1_.func_149548_c() && this.field_147369_b.field_71070_bA.func_75129_b(this.field_147369_b)) {
         if(this.field_147369_b.func_175149_v()) {
            List<ItemStack> list = Lists.<ItemStack>newArrayList();

            for(int i = 0; i < this.field_147369_b.field_71070_bA.field_75151_b.size(); ++i) {
               list.add(((Slot)this.field_147369_b.field_71070_bA.field_75151_b.get(i)).func_75211_c());
            }

            this.field_147369_b.func_71110_a(this.field_147369_b.field_71070_bA, list);
         } else {
            ItemStack itemstack2 = this.field_147369_b.field_71070_bA.func_184996_a(p_147351_1_.func_149544_d(), p_147351_1_.func_149543_e(), p_147351_1_.func_186993_f(), this.field_147369_b);
            if(ItemStack.func_77989_b(p_147351_1_.func_149546_g(), itemstack2)) {
               this.field_147369_b.field_71135_a.func_147359_a(new SPacketConfirmTransaction(p_147351_1_.func_149548_c(), p_147351_1_.func_149547_f(), true));
               this.field_147369_b.field_71137_h = true;
               this.field_147369_b.field_71070_bA.func_75142_b();
               this.field_147369_b.func_71113_k();
               this.field_147369_b.field_71137_h = false;
            } else {
               this.field_147372_n.func_76038_a(this.field_147369_b.field_71070_bA.field_75152_c, Short.valueOf(p_147351_1_.func_149547_f()));
               this.field_147369_b.field_71135_a.func_147359_a(new SPacketConfirmTransaction(p_147351_1_.func_149548_c(), p_147351_1_.func_149547_f(), false));
               this.field_147369_b.field_71070_bA.func_75128_a(this.field_147369_b, false);
               List<ItemStack> list1 = Lists.<ItemStack>newArrayList();

               for(int j = 0; j < this.field_147369_b.field_71070_bA.field_75151_b.size(); ++j) {
                  ItemStack itemstack = ((Slot)this.field_147369_b.field_71070_bA.field_75151_b.get(j)).func_75211_c();
                  ItemStack itemstack1 = itemstack != null && itemstack.field_77994_a > 0?itemstack:null;
                  list1.add(itemstack1);
               }

               this.field_147369_b.func_71110_a(this.field_147369_b.field_71070_bA, list1);
            }
         }
      }

   }

   public void func_147338_a(CPacketEnchantItem p_147338_1_) {
      PacketThreadUtil.func_180031_a(p_147338_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_143004_u();
      if(this.field_147369_b.field_71070_bA.field_75152_c == p_147338_1_.func_149539_c() && this.field_147369_b.field_71070_bA.func_75129_b(this.field_147369_b) && !this.field_147369_b.func_175149_v()) {
         this.field_147369_b.field_71070_bA.func_75140_a(this.field_147369_b, p_147338_1_.func_149537_d());
         this.field_147369_b.field_71070_bA.func_75142_b();
      }

   }

   public void func_147344_a(CPacketCreativeInventoryAction p_147344_1_) {
      PacketThreadUtil.func_180031_a(p_147344_1_, this, this.field_147369_b.func_71121_q());
      if(this.field_147369_b.field_71134_c.func_73083_d()) {
         boolean flag = p_147344_1_.func_149627_c() < 0;
         ItemStack itemstack = p_147344_1_.func_149625_d();
         if(itemstack != null && itemstack.func_77942_o() && itemstack.func_77978_p().func_150297_b("BlockEntityTag", 10)) {
            NBTTagCompound nbttagcompound = itemstack.func_77978_p().func_74775_l("BlockEntityTag");
            if(nbttagcompound.func_74764_b("x") && nbttagcompound.func_74764_b("y") && nbttagcompound.func_74764_b("z")) {
               BlockPos blockpos = new BlockPos(nbttagcompound.func_74762_e("x"), nbttagcompound.func_74762_e("y"), nbttagcompound.func_74762_e("z"));
               TileEntity tileentity = this.field_147369_b.field_70170_p.func_175625_s(blockpos);
               if(tileentity != null) {
                  NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                  tileentity.func_145841_b(nbttagcompound1);
                  nbttagcompound1.func_82580_o("x");
                  nbttagcompound1.func_82580_o("y");
                  nbttagcompound1.func_82580_o("z");
                  itemstack.func_77983_a("BlockEntityTag", nbttagcompound1);
               }
            }
         }

         boolean flag1 = p_147344_1_.func_149627_c() >= 1 && p_147344_1_.func_149627_c() <= 45;
         boolean flag2 = itemstack == null || itemstack.func_77973_b() != null;
         boolean flag3 = itemstack == null || itemstack.func_77960_j() >= 0 && itemstack.field_77994_a <= 64 && itemstack.field_77994_a > 0;
         if(flag1 && flag2 && flag3) {
            if(itemstack == null) {
               this.field_147369_b.field_71069_bz.func_75141_a(p_147344_1_.func_149627_c(), (ItemStack)null);
            } else {
               this.field_147369_b.field_71069_bz.func_75141_a(p_147344_1_.func_149627_c(), itemstack);
            }

            this.field_147369_b.field_71069_bz.func_75128_a(this.field_147369_b, true);
         } else if(flag && flag2 && flag3 && this.field_147375_m < 200) {
            this.field_147375_m += 20;
            EntityItem entityitem = this.field_147369_b.func_71019_a(itemstack, true);
            if(entityitem != null) {
               entityitem.func_70288_d();
            }
         }
      }

   }

   public void func_147339_a(CPacketConfirmTransaction p_147339_1_) {
      PacketThreadUtil.func_180031_a(p_147339_1_, this, this.field_147369_b.func_71121_q());
      Short oshort = (Short)this.field_147372_n.func_76041_a(this.field_147369_b.field_71070_bA.field_75152_c);
      if(oshort != null && p_147339_1_.func_149533_d() == oshort.shortValue() && this.field_147369_b.field_71070_bA.field_75152_c == p_147339_1_.func_149532_c() && !this.field_147369_b.field_71070_bA.func_75129_b(this.field_147369_b) && !this.field_147369_b.func_175149_v()) {
         this.field_147369_b.field_71070_bA.func_75128_a(this.field_147369_b, true);
      }

   }

   public void func_147343_a(CPacketUpdateSign p_147343_1_) {
      PacketThreadUtil.func_180031_a(p_147343_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_143004_u();
      WorldServer worldserver = this.field_147367_d.func_71218_a(this.field_147369_b.field_71093_bK);
      BlockPos blockpos = p_147343_1_.func_179722_a();
      if(worldserver.func_175667_e(blockpos)) {
         IBlockState iblockstate = worldserver.func_180495_p(blockpos);
         TileEntity tileentity = worldserver.func_175625_s(blockpos);
         if(!(tileentity instanceof TileEntitySign)) {
            return;
         }

         TileEntitySign tileentitysign = (TileEntitySign)tileentity;
         if(!tileentitysign.func_145914_a() || tileentitysign.func_145911_b() != this.field_147369_b) {
            this.field_147367_d.func_71236_h("Player " + this.field_147369_b.func_70005_c_() + " just tried to change non-editable sign");
            return;
         }

         String[] astring = p_147343_1_.func_187017_b();

         for(int i = 0; i < astring.length; ++i) {
            tileentitysign.field_145915_a[i] = new TextComponentString(TextFormatting.func_110646_a(astring[i]));
         }

         tileentitysign.func_70296_d();
         worldserver.func_184138_a(blockpos, iblockstate, iblockstate, 3);
      }

   }

   public void func_147353_a(CPacketKeepAlive p_147353_1_) {
      if(p_147353_1_.func_149460_c() == this.field_147378_h) {
         int i = (int)(this.func_147363_d() - this.field_147379_i);
         this.field_147369_b.field_71138_i = (this.field_147369_b.field_71138_i * 3 + i) / 4;
      }

   }

   private long func_147363_d() {
      return System.nanoTime() / 1000000L;
   }

   public void func_147348_a(CPacketPlayerAbilities p_147348_1_) {
      PacketThreadUtil.func_180031_a(p_147348_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.field_71075_bZ.field_75100_b = p_147348_1_.func_149488_d() && this.field_147369_b.field_71075_bZ.field_75101_c;
   }

   public void func_147341_a(CPacketTabComplete p_147341_1_) {
      PacketThreadUtil.func_180031_a(p_147341_1_, this, this.field_147369_b.func_71121_q());
      List<String> list = Lists.<String>newArrayList();

      for(String s : this.field_147367_d.func_184104_a(this.field_147369_b, p_147341_1_.func_149419_c(), p_147341_1_.func_179709_b(), p_147341_1_.func_186989_c())) {
         list.add(s);
      }

      this.field_147369_b.field_71135_a.func_147359_a(new SPacketTabComplete((String[])list.toArray(new String[list.size()])));
   }

   public void func_147352_a(CPacketClientSettings p_147352_1_) {
      PacketThreadUtil.func_180031_a(p_147352_1_, this, this.field_147369_b.func_71121_q());
      this.field_147369_b.func_147100_a(p_147352_1_);
   }

   public void func_147349_a(CPacketCustomPayload p_147349_1_) {
      PacketThreadUtil.func_180031_a(p_147349_1_, this, this.field_147369_b.func_71121_q());
      String s = p_147349_1_.func_149559_c();
      if("MC|BEdit".equals(s)) {
         PacketBuffer packetbuffer6 = new PacketBuffer(Unpooled.wrappedBuffer((ByteBuf)p_147349_1_.func_180760_b()));

         try {
            ItemStack itemstack1 = packetbuffer6.func_150791_c();
            if(itemstack1 != null) {
               if(!ItemWritableBook.func_150930_a(itemstack1.func_77978_p())) {
                  throw new IOException("Invalid book tag!");
               }

               ItemStack itemstack3 = this.field_147369_b.func_184614_ca();
               if(itemstack3 == null) {
                  return;
               }

               if(itemstack1.func_77973_b() == Items.field_151099_bA && itemstack1.func_77973_b() == itemstack3.func_77973_b()) {
                  itemstack3.func_77983_a("pages", itemstack1.func_77978_p().func_150295_c("pages", 8));
               }

               return;
            }
         } catch (Exception exception6) {
            field_147370_c.error((String)"Couldn\'t handle book info", (Throwable)exception6);
            return;
         } finally {
            packetbuffer6.release();
         }

         return;
      } else if("MC|BSign".equals(s)) {
         PacketBuffer packetbuffer5 = new PacketBuffer(Unpooled.wrappedBuffer((ByteBuf)p_147349_1_.func_180760_b()));

         try {
            ItemStack itemstack = packetbuffer5.func_150791_c();
            if(itemstack != null) {
               if(!ItemWrittenBook.func_77828_a(itemstack.func_77978_p())) {
                  throw new IOException("Invalid book tag!");
               }

               ItemStack itemstack2 = this.field_147369_b.func_184614_ca();
               if(itemstack2 == null) {
                  return;
               }

               if(itemstack.func_77973_b() == Items.field_151099_bA && itemstack2.func_77973_b() == Items.field_151099_bA) {
                  itemstack2.func_77983_a("author", new NBTTagString(this.field_147369_b.func_70005_c_()));
                  itemstack2.func_77983_a("title", new NBTTagString(itemstack.func_77978_p().func_74779_i("title")));
                  NBTTagList nbttaglist = itemstack.func_77978_p().func_150295_c("pages", 8);

                  for(int j1 = 0; j1 < nbttaglist.func_74745_c(); ++j1) {
                     String s4 = nbttaglist.func_150307_f(j1);
                     ITextComponent itextcomponent = new TextComponentString(s4);
                     s4 = ITextComponent.Serializer.func_150696_a(itextcomponent);
                     nbttaglist.func_150304_a(j1, new NBTTagString(s4));
                  }

                  itemstack2.func_77983_a("pages", nbttaglist);
                  itemstack2.func_150996_a(Items.field_151164_bB);
               }

               return;
            }
         } catch (Exception exception7) {
            field_147370_c.error((String)"Couldn\'t sign book", (Throwable)exception7);
            return;
         } finally {
            packetbuffer5.release();
         }

         return;
      } else if("MC|TrSel".equals(s)) {
         try {
            int i = p_147349_1_.func_180760_b().readInt();
            Container container = this.field_147369_b.field_71070_bA;
            if(container instanceof ContainerMerchant) {
               ((ContainerMerchant)container).func_75175_c(i);
            }
         } catch (Exception exception5) {
            field_147370_c.error((String)"Couldn\'t select trade", (Throwable)exception5);
         }
      } else if("MC|AdvCmd".equals(s)) {
         if(!this.field_147367_d.func_82356_Z()) {
            this.field_147369_b.func_145747_a(new TextComponentTranslation("advMode.notEnabled", new Object[0]));
            return;
         }

         if(!this.field_147369_b.func_70003_b(2, "") || !this.field_147369_b.field_71075_bZ.field_75098_d) {
            this.field_147369_b.func_145747_a(new TextComponentTranslation("advMode.notAllowed", new Object[0]));
            return;
         }

         PacketBuffer packetbuffer = p_147349_1_.func_180760_b();

         try {
            int j = packetbuffer.readByte();
            CommandBlockBaseLogic commandblockbaselogic = null;
            if(j == 0) {
               TileEntity tileentity = this.field_147369_b.field_70170_p.func_175625_s(new BlockPos(packetbuffer.readInt(), packetbuffer.readInt(), packetbuffer.readInt()));
               if(tileentity instanceof TileEntityCommandBlock) {
                  commandblockbaselogic = ((TileEntityCommandBlock)tileentity).func_145993_a();
               }
            } else if(j == 1) {
               Entity entity = this.field_147369_b.field_70170_p.func_73045_a(packetbuffer.readInt());
               if(entity instanceof EntityMinecartCommandBlock) {
                  commandblockbaselogic = ((EntityMinecartCommandBlock)entity).func_145822_e();
               }
            }

            String s3 = packetbuffer.func_150789_c(packetbuffer.readableBytes());
            boolean flag = packetbuffer.readBoolean();
            if(commandblockbaselogic != null) {
               commandblockbaselogic.func_145752_a(s3);
               commandblockbaselogic.func_175573_a(flag);
               if(!flag) {
                  commandblockbaselogic.func_145750_b((ITextComponent)null);
               }

               commandblockbaselogic.func_145756_e();
               this.field_147369_b.func_145747_a(new TextComponentTranslation("advMode.setCommand.success", new Object[]{s3}));
            }
         } catch (Exception exception4) {
            field_147370_c.error((String)"Couldn\'t set command block", (Throwable)exception4);
         } finally {
            packetbuffer.release();
         }
      } else if("MC|AutoCmd".equals(s)) {
         if(!this.field_147367_d.func_82356_Z()) {
            this.field_147369_b.func_145747_a(new TextComponentTranslation("advMode.notEnabled", new Object[0]));
            return;
         }

         if(!this.field_147369_b.func_70003_b(2, "") || !this.field_147369_b.field_71075_bZ.field_75098_d) {
            this.field_147369_b.func_145747_a(new TextComponentTranslation("advMode.notAllowed", new Object[0]));
            return;
         }

         PacketBuffer packetbuffer1 = p_147349_1_.func_180760_b();

         try {
            CommandBlockBaseLogic commandblockbaselogic1 = null;
            TileEntityCommandBlock tileentitycommandblock = null;
            BlockPos blockpos1 = new BlockPos(packetbuffer1.readInt(), packetbuffer1.readInt(), packetbuffer1.readInt());
            TileEntity tileentity2 = this.field_147369_b.field_70170_p.func_175625_s(blockpos1);
            if(tileentity2 instanceof TileEntityCommandBlock) {
               tileentitycommandblock = (TileEntityCommandBlock)tileentity2;
               commandblockbaselogic1 = tileentitycommandblock.func_145993_a();
            }

            String s1 = packetbuffer1.func_150789_c(packetbuffer1.readableBytes());
            boolean flag1 = packetbuffer1.readBoolean();
            TileEntityCommandBlock.Mode tileentitycommandblock$mode = TileEntityCommandBlock.Mode.valueOf(packetbuffer1.func_150789_c(16));
            boolean flag2 = packetbuffer1.readBoolean();
            boolean flag3 = packetbuffer1.readBoolean();
            if(commandblockbaselogic1 != null) {
               EnumFacing enumfacing = (EnumFacing)this.field_147369_b.field_70170_p.func_180495_p(blockpos1).func_177229_b(BlockCommandBlock.field_185564_a);
               switch(tileentitycommandblock$mode) {
               case SEQUENCE:
                  IBlockState iblockstate3 = Blocks.field_185777_dd.func_176223_P();
                  this.field_147369_b.field_70170_p.func_180501_a(blockpos1, iblockstate3.func_177226_a(BlockCommandBlock.field_185564_a, enumfacing).func_177226_a(BlockCommandBlock.field_185565_b, Boolean.valueOf(flag2)), 2);
                  break;
               case AUTO:
                  IBlockState iblockstate2 = Blocks.field_185776_dc.func_176223_P();
                  this.field_147369_b.field_70170_p.func_180501_a(blockpos1, iblockstate2.func_177226_a(BlockCommandBlock.field_185564_a, enumfacing).func_177226_a(BlockCommandBlock.field_185565_b, Boolean.valueOf(flag2)), 2);
                  break;
               case REDSTONE:
                  IBlockState lvt_14_1_ = Blocks.field_150483_bI.func_176223_P();
                  this.field_147369_b.field_70170_p.func_180501_a(blockpos1, lvt_14_1_.func_177226_a(BlockCommandBlock.field_185564_a, enumfacing).func_177226_a(BlockCommandBlock.field_185565_b, Boolean.valueOf(flag2)), 2);
               }

               tileentity2.func_145829_t();
               this.field_147369_b.field_70170_p.func_175690_a(blockpos1, tileentity2);
               commandblockbaselogic1.func_145752_a(s1);
               commandblockbaselogic1.func_175573_a(flag1);
               if(!flag1) {
                  commandblockbaselogic1.func_145750_b((ITextComponent)null);
               }

               tileentitycommandblock.func_184253_b(flag3);
               commandblockbaselogic1.func_145756_e();
               if(!net.minecraft.util.StringUtils.func_151246_b(s1)) {
                  this.field_147369_b.func_145747_a(new TextComponentTranslation("advMode.setCommand.success", new Object[]{s1}));
               }
            }
         } catch (Exception exception3) {
            field_147370_c.error((String)"Couldn\'t set command block", (Throwable)exception3);
         } finally {
            packetbuffer1.release();
         }
      } else if("MC|Beacon".equals(s)) {
         if(this.field_147369_b.field_71070_bA instanceof ContainerBeacon) {
            try {
               PacketBuffer packetbuffer2 = p_147349_1_.func_180760_b();
               int k = packetbuffer2.readInt();
               int i1 = packetbuffer2.readInt();
               ContainerBeacon containerbeacon = (ContainerBeacon)this.field_147369_b.field_71070_bA;
               Slot slot = containerbeacon.func_75139_a(0);
               if(slot.func_75216_d()) {
                  slot.func_75209_a(1);
                  IInventory iinventory = containerbeacon.func_180611_e();
                  iinventory.func_174885_b(1, k);
                  iinventory.func_174885_b(2, i1);
                  iinventory.func_70296_d();
               }
            } catch (Exception exception2) {
               field_147370_c.error((String)"Couldn\'t set beacon", (Throwable)exception2);
            }
         }
      } else if("MC|ItemName".equals(s)) {
         if(this.field_147369_b.field_71070_bA instanceof ContainerRepair) {
            ContainerRepair containerrepair = (ContainerRepair)this.field_147369_b.field_71070_bA;
            if(p_147349_1_.func_180760_b() != null && p_147349_1_.func_180760_b().readableBytes() >= 1) {
               String s2 = ChatAllowedCharacters.func_71565_a(p_147349_1_.func_180760_b().func_150789_c(32767));
               if(s2.length() <= 30) {
                  containerrepair.func_82850_a(s2);
               }
            } else {
               containerrepair.func_82850_a("");
            }
         }
      } else if("MC|Struct".equals(s)) {
         PacketBuffer packetbuffer3 = p_147349_1_.func_180760_b();

         try {
            if(this.field_147369_b.func_70003_b(4, "") && this.field_147369_b.field_71075_bZ.field_75098_d) {
               BlockPos blockpos = new BlockPos(packetbuffer3.readInt(), packetbuffer3.readInt(), packetbuffer3.readInt());
               IBlockState iblockstate1 = this.field_147369_b.field_70170_p.func_180495_p(blockpos);
               TileEntity tileentity1 = this.field_147369_b.field_70170_p.func_175625_s(blockpos);
               if(tileentity1 instanceof TileEntityStructure) {
                  TileEntityStructure tileentitystructure = (TileEntityStructure)tileentity1;
                  int k1 = packetbuffer3.readByte();
                  String s5 = packetbuffer3.func_150789_c(32);
                  tileentitystructure.func_184405_a(TileEntityStructure.Mode.valueOf(s5));
                  tileentitystructure.func_184404_a(packetbuffer3.func_150789_c(64));
                  tileentitystructure.func_184414_b(new BlockPos(packetbuffer3.readInt(), packetbuffer3.readInt(), packetbuffer3.readInt()));
                  tileentitystructure.func_184409_c(new BlockPos(packetbuffer3.readInt(), packetbuffer3.readInt(), packetbuffer3.readInt()));
                  String s6 = packetbuffer3.func_150789_c(32);
                  tileentitystructure.func_184411_a(Mirror.valueOf(s6));
                  String s7 = packetbuffer3.func_150789_c(32);
                  tileentitystructure.func_184408_a(Rotation.valueOf(s7));
                  tileentitystructure.func_184410_b(packetbuffer3.func_150789_c(128));
                  tileentitystructure.func_184406_a(packetbuffer3.readBoolean());
                  if(k1 == 2) {
                     if(tileentitystructure.func_184419_m()) {
                        this.field_147369_b.func_146105_b(new TextComponentString("Structure saved"));
                     } else {
                        this.field_147369_b.func_146105_b(new TextComponentString("Structure NOT saved"));
                     }
                  } else if(k1 == 3) {
                     if(tileentitystructure.func_184412_n()) {
                        this.field_147369_b.func_146105_b(new TextComponentString("Structure loaded"));
                     } else {
                        this.field_147369_b.func_146105_b(new TextComponentString("Structure prepared"));
                     }
                  } else if(k1 == 4 && tileentitystructure.func_184417_l()) {
                     this.field_147369_b.func_146105_b(new TextComponentString("Size detected"));
                  }

                  tileentitystructure.func_70296_d();
                  this.field_147369_b.field_70170_p.func_184138_a(blockpos, iblockstate1, iblockstate1, 3);
               }
            }
         } catch (Exception exception1) {
            field_147370_c.error((String)"Couldn\'t set structure block", (Throwable)exception1);
         } finally {
            packetbuffer3.release();
         }
      } else if("MC|PickItem".equals(s)) {
         PacketBuffer packetbuffer4 = p_147349_1_.func_180760_b();

         try {
            int l = packetbuffer4.func_150792_a();
            this.field_147369_b.field_71071_by.func_184430_d(l);
            this.field_147369_b.field_71135_a.func_147359_a(new SPacketSetSlot(-2, this.field_147369_b.field_71071_by.field_70461_c, this.field_147369_b.field_71071_by.func_70301_a(this.field_147369_b.field_71071_by.field_70461_c)));
            this.field_147369_b.field_71135_a.func_147359_a(new SPacketSetSlot(-2, l, this.field_147369_b.field_71071_by.func_70301_a(l)));
            this.field_147369_b.field_71135_a.func_147359_a(new SPacketHeldItemChange(this.field_147369_b.field_71071_by.field_70461_c));
         } catch (Exception exception) {
            field_147370_c.error((String)"Couldn\'t pick item", (Throwable)exception);
         } finally {
            packetbuffer4.release();
         }
      }

   }
}
