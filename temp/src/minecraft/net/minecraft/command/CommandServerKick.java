package net.minecraft.command;

import java.util.Collections;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CommandServerKick extends CommandBase {
   public String func_71517_b() {
      return "kick";
   }

   public int func_82362_a() {
      return 3;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.kick.usage";
   }

   public void func_184881_a(MinecraftServer p_184881_1_, ICommandSender p_184881_2_, String[] p_184881_3_) throws CommandException {
      if(p_184881_3_.length > 0 && p_184881_3_[0].length() > 1) {
         EntityPlayerMP entityplayermp = p_184881_1_.func_184103_al().func_152612_a(p_184881_3_[0]);
         String s = "Kicked by an operator.";
         boolean flag = false;
         if(entityplayermp == null) {
            throw new PlayerNotFoundException();
         } else {
            if(p_184881_3_.length >= 2) {
               s = func_147178_a(p_184881_2_, p_184881_3_, 1).func_150260_c();
               flag = true;
            }

            entityplayermp.field_71135_a.func_147360_c(s);
            if(flag) {
               func_152373_a(p_184881_2_, this, "commands.kick.success.reason", new Object[]{entityplayermp.func_70005_c_(), s});
            } else {
               func_152373_a(p_184881_2_, this, "commands.kick.success", new Object[]{entityplayermp.func_70005_c_()});
            }

         }
      } else {
         throw new WrongUsageException("commands.kick.usage", new Object[0]);
      }
   }

   public List<String> func_184883_a(MinecraftServer p_184883_1_, ICommandSender p_184883_2_, String[] p_184883_3_, BlockPos p_184883_4_) {
      return p_184883_3_.length >= 1?func_71530_a(p_184883_3_, p_184883_1_.func_71213_z()):Collections.emptyList();
   }
}
