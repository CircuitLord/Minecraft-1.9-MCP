package net.minecraft.command;

import net.minecraft.command.CommandResultStats;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public interface ICommandSender {
   String func_70005_c_();

   ITextComponent func_145748_c_();

   void func_145747_a(ITextComponent var1);

   boolean func_70003_b(int var1, String var2);

   BlockPos func_180425_c();

   Vec3d func_174791_d();

   World func_130014_f_();

   Entity func_174793_f();

   boolean func_174792_t_();

   void func_174794_a(CommandResultStats.Type var1, int var2);

   MinecraftServer func_184102_h();
}
