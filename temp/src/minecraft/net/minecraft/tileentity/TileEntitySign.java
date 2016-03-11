package net.minecraft.tileentity;

import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateSign;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;

public class TileEntitySign extends TileEntity {
   public final ITextComponent[] field_145915_a = new ITextComponent[]{new TextComponentString(""), new TextComponentString(""), new TextComponentString(""), new TextComponentString("")};
   public int field_145918_i = -1;
   private boolean field_145916_j = true;
   private EntityPlayer field_145917_k;
   private final CommandResultStats field_174883_i = new CommandResultStats();

   public void func_145841_b(NBTTagCompound p_145841_1_) {
      super.func_145841_b(p_145841_1_);

      for(int i = 0; i < 4; ++i) {
         String s = ITextComponent.Serializer.func_150696_a(this.field_145915_a[i]);
         p_145841_1_.func_74778_a("Text" + (i + 1), s);
      }

      this.field_174883_i.func_179670_b(p_145841_1_);
   }

   public void func_145839_a(NBTTagCompound p_145839_1_) {
      this.field_145916_j = false;
      super.func_145839_a(p_145839_1_);
      ICommandSender icommandsender = new ICommandSender() {
         public String func_70005_c_() {
            return "Sign";
         }

         public ITextComponent func_145748_c_() {
            return new TextComponentString(this.func_70005_c_());
         }

         public void func_145747_a(ITextComponent p_145747_1_) {
         }

         public boolean func_70003_b(int p_70003_1_, String p_70003_2_) {
            return true;
         }

         public BlockPos func_180425_c() {
            return TileEntitySign.this.field_174879_c;
         }

         public Vec3d func_174791_d() {
            return new Vec3d((double)TileEntitySign.this.field_174879_c.func_177958_n() + 0.5D, (double)TileEntitySign.this.field_174879_c.func_177956_o() + 0.5D, (double)TileEntitySign.this.field_174879_c.func_177952_p() + 0.5D);
         }

         public World func_130014_f_() {
            return TileEntitySign.this.field_145850_b;
         }

         public Entity func_174793_f() {
            return null;
         }

         public boolean func_174792_t_() {
            return false;
         }

         public void func_174794_a(CommandResultStats.Type p_174794_1_, int p_174794_2_) {
         }

         public MinecraftServer func_184102_h() {
            return TileEntitySign.this.field_145850_b.func_73046_m();
         }
      };

      for(int i = 0; i < 4; ++i) {
         String s = p_145839_1_.func_74779_i("Text" + (i + 1));
         ITextComponent itextcomponent = ITextComponent.Serializer.func_150699_a(s);

         try {
            this.field_145915_a[i] = TextComponentUtils.func_179985_a(icommandsender, itextcomponent, (Entity)null);
         } catch (CommandException var7) {
            this.field_145915_a[i] = itextcomponent;
         }
      }

      this.field_174883_i.func_179668_a(p_145839_1_);
   }

   public Packet<?> func_145844_m() {
      ITextComponent[] aitextcomponent = new ITextComponent[4];
      System.arraycopy(this.field_145915_a, 0, aitextcomponent, 0, 4);
      return new SPacketUpdateSign(this.field_145850_b, this.field_174879_c, aitextcomponent);
   }

   public boolean func_183000_F() {
      return true;
   }

   public boolean func_145914_a() {
      return this.field_145916_j;
   }

   public void func_145913_a(boolean p_145913_1_) {
      this.field_145916_j = p_145913_1_;
      if(!p_145913_1_) {
         this.field_145917_k = null;
      }

   }

   public void func_145912_a(EntityPlayer p_145912_1_) {
      this.field_145917_k = p_145912_1_;
   }

   public EntityPlayer func_145911_b() {
      return this.field_145917_k;
   }

   public boolean func_174882_b(final EntityPlayer p_174882_1_) {
      ICommandSender icommandsender = new ICommandSender() {
         public String func_70005_c_() {
            return p_174882_1_.func_70005_c_();
         }

         public ITextComponent func_145748_c_() {
            return p_174882_1_.func_145748_c_();
         }

         public void func_145747_a(ITextComponent p_145747_1_) {
         }

         public boolean func_70003_b(int p_70003_1_, String p_70003_2_) {
            return p_70003_1_ <= 2;
         }

         public BlockPos func_180425_c() {
            return TileEntitySign.this.field_174879_c;
         }

         public Vec3d func_174791_d() {
            return new Vec3d((double)TileEntitySign.this.field_174879_c.func_177958_n() + 0.5D, (double)TileEntitySign.this.field_174879_c.func_177956_o() + 0.5D, (double)TileEntitySign.this.field_174879_c.func_177952_p() + 0.5D);
         }

         public World func_130014_f_() {
            return p_174882_1_.func_130014_f_();
         }

         public Entity func_174793_f() {
            return p_174882_1_;
         }

         public boolean func_174792_t_() {
            return false;
         }

         public void func_174794_a(CommandResultStats.Type p_174794_1_, int p_174794_2_) {
            if(TileEntitySign.this.field_145850_b != null && !TileEntitySign.this.field_145850_b.field_72995_K) {
               TileEntitySign.this.field_174883_i.func_184932_a(TileEntitySign.this.field_145850_b.func_73046_m(), this, p_174794_1_, p_174794_2_);
            }

         }

         public MinecraftServer func_184102_h() {
            return p_174882_1_.func_184102_h();
         }
      };

      for(int i = 0; i < this.field_145915_a.length; ++i) {
         Style style = this.field_145915_a[i] == null?null:this.field_145915_a[i].func_150256_b();
         if(style != null && style.func_150235_h() != null) {
            ClickEvent clickevent = style.func_150235_h();
            if(clickevent.func_150669_a() == ClickEvent.Action.RUN_COMMAND) {
               p_174882_1_.func_184102_h().func_71187_D().func_71556_a(icommandsender, clickevent.func_150668_b());
            }
         }
      }

      return true;
   }

   public CommandResultStats func_174880_d() {
      return this.field_174883_i;
   }
}
