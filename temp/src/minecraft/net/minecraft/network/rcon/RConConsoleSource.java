package net.minecraft.network.rcon;

import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class RConConsoleSource implements ICommandSender {
   private final StringBuffer field_70009_b = new StringBuffer();
   private final MinecraftServer field_184171_b;

   public RConConsoleSource(MinecraftServer p_i46835_1_) {
      this.field_184171_b = p_i46835_1_;
   }

   public String func_70005_c_() {
      return "Rcon";
   }

   public ITextComponent func_145748_c_() {
      return new TextComponentString(this.func_70005_c_());
   }

   public void func_145747_a(ITextComponent p_145747_1_) {
      this.field_70009_b.append(p_145747_1_.func_150260_c());
   }

   public boolean func_70003_b(int p_70003_1_, String p_70003_2_) {
      return true;
   }

   public BlockPos func_180425_c() {
      return BlockPos.field_177992_a;
   }

   public Vec3d func_174791_d() {
      return Vec3d.field_186680_a;
   }

   public World func_130014_f_() {
      return this.field_184171_b.func_130014_f_();
   }

   public Entity func_174793_f() {
      return null;
   }

   public boolean func_174792_t_() {
      return true;
   }

   public void func_174794_a(CommandResultStats.Type p_174794_1_, int p_174794_2_) {
   }

   public MinecraftServer func_184102_h() {
      return this.field_184171_b;
   }
}
