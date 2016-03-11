package net.minecraft.command;

import java.util.Collections;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

public class CommandTime extends CommandBase {
   public String func_71517_b() {
      return "time";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.time.usage";
   }

   public void func_184881_a(MinecraftServer p_184881_1_, ICommandSender p_184881_2_, String[] p_184881_3_) throws CommandException {
      if(p_184881_3_.length > 1) {
         if(p_184881_3_[0].equals("set")) {
            int i1;
            if(p_184881_3_[1].equals("day")) {
               i1 = 1000;
            } else if(p_184881_3_[1].equals("night")) {
               i1 = 13000;
            } else {
               i1 = func_180528_a(p_184881_3_[1], 0);
            }

            this.func_184929_a(p_184881_1_, i1);
            func_152373_a(p_184881_2_, this, "commands.time.set", new Object[]{Integer.valueOf(i1)});
            return;
         }

         if(p_184881_3_[0].equals("add")) {
            int l = func_180528_a(p_184881_3_[1], 0);
            this.func_184928_b(p_184881_1_, l);
            func_152373_a(p_184881_2_, this, "commands.time.added", new Object[]{Integer.valueOf(l)});
            return;
         }

         if(p_184881_3_[0].equals("query")) {
            if(p_184881_3_[1].equals("daytime")) {
               int k = (int)(p_184881_2_.func_130014_f_().func_72820_D() % 24000L);
               p_184881_2_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, k);
               func_152373_a(p_184881_2_, this, "commands.time.query", new Object[]{Integer.valueOf(k)});
               return;
            }

            if(p_184881_3_[1].equals("day")) {
               int j = (int)(p_184881_2_.func_130014_f_().func_72820_D() / 24000L % 2147483647L);
               p_184881_2_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, j);
               func_152373_a(p_184881_2_, this, "commands.time.query", new Object[]{Integer.valueOf(j)});
               return;
            }

            if(p_184881_3_[1].equals("gametime")) {
               int i = (int)(p_184881_2_.func_130014_f_().func_82737_E() % 2147483647L);
               p_184881_2_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, i);
               func_152373_a(p_184881_2_, this, "commands.time.query", new Object[]{Integer.valueOf(i)});
               return;
            }
         }
      }

      throw new WrongUsageException("commands.time.usage", new Object[0]);
   }

   public List<String> func_184883_a(MinecraftServer p_184883_1_, ICommandSender p_184883_2_, String[] p_184883_3_, BlockPos p_184883_4_) {
      return p_184883_3_.length == 1?func_71530_a(p_184883_3_, new String[]{"set", "add", "query"}):(p_184883_3_.length == 2 && p_184883_3_[0].equals("set")?func_71530_a(p_184883_3_, new String[]{"day", "night"}):(p_184883_3_.length == 2 && p_184883_3_[0].equals("query")?func_71530_a(p_184883_3_, new String[]{"daytime", "gametime", "day"}):Collections.emptyList()));
   }

   protected void func_184929_a(MinecraftServer p_184929_1_, int p_184929_2_) {
      for(int i = 0; i < p_184929_1_.field_71305_c.length; ++i) {
         p_184929_1_.field_71305_c[i].func_72877_b((long)p_184929_2_);
      }

   }

   protected void func_184928_b(MinecraftServer p_184928_1_, int p_184928_2_) {
      for(int i = 0; i < p_184928_1_.field_71305_c.length; ++i) {
         WorldServer worldserver = p_184928_1_.field_71305_c[i];
         worldserver.func_72877_b(worldserver.func_72820_D() + (long)p_184928_2_);
      }

   }
}
