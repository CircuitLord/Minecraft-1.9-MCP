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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.border.WorldBorder;

public class CommandWorldBorder extends CommandBase {
   public String func_71517_b() {
      return "worldborder";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.worldborder.usage";
   }

   public void func_184881_a(MinecraftServer p_184881_1_, ICommandSender p_184881_2_, String[] p_184881_3_) throws CommandException {
      if(p_184881_3_.length < 1) {
         throw new WrongUsageException("commands.worldborder.usage", new Object[0]);
      } else {
         WorldBorder worldborder = this.func_184931_a(p_184881_1_);
         if(p_184881_3_[0].equals("set")) {
            if(p_184881_3_.length != 2 && p_184881_3_.length != 3) {
               throw new WrongUsageException("commands.worldborder.set.usage", new Object[0]);
            }

            double d0 = worldborder.func_177751_j();
            double d2 = func_175756_a(p_184881_3_[1], 1.0D, 6.0E7D);
            long i = p_184881_3_.length > 2?func_175760_a(p_184881_3_[2], 0L, 9223372036854775L) * 1000L:0L;
            if(i > 0L) {
               worldborder.func_177738_a(d0, d2, i);
               if(d0 > d2) {
                  func_152373_a(p_184881_2_, this, "commands.worldborder.setSlowly.shrink.success", new Object[]{String.format("%.1f", new Object[]{Double.valueOf(d2)}), String.format("%.1f", new Object[]{Double.valueOf(d0)}), Long.toString(i / 1000L)});
               } else {
                  func_152373_a(p_184881_2_, this, "commands.worldborder.setSlowly.grow.success", new Object[]{String.format("%.1f", new Object[]{Double.valueOf(d2)}), String.format("%.1f", new Object[]{Double.valueOf(d0)}), Long.toString(i / 1000L)});
               }
            } else {
               worldborder.func_177750_a(d2);
               func_152373_a(p_184881_2_, this, "commands.worldborder.set.success", new Object[]{String.format("%.1f", new Object[]{Double.valueOf(d2)}), String.format("%.1f", new Object[]{Double.valueOf(d0)})});
            }
         } else if(p_184881_3_[0].equals("add")) {
            if(p_184881_3_.length != 2 && p_184881_3_.length != 3) {
               throw new WrongUsageException("commands.worldborder.add.usage", new Object[0]);
            }

            double d4 = worldborder.func_177741_h();
            double d8 = d4 + func_175756_a(p_184881_3_[1], -d4, 6.0E7D - d4);
            long j1 = worldborder.func_177732_i() + (p_184881_3_.length > 2?func_175760_a(p_184881_3_[2], 0L, 9223372036854775L) * 1000L:0L);
            if(j1 > 0L) {
               worldborder.func_177738_a(d4, d8, j1);
               if(d4 > d8) {
                  func_152373_a(p_184881_2_, this, "commands.worldborder.setSlowly.shrink.success", new Object[]{String.format("%.1f", new Object[]{Double.valueOf(d8)}), String.format("%.1f", new Object[]{Double.valueOf(d4)}), Long.toString(j1 / 1000L)});
               } else {
                  func_152373_a(p_184881_2_, this, "commands.worldborder.setSlowly.grow.success", new Object[]{String.format("%.1f", new Object[]{Double.valueOf(d8)}), String.format("%.1f", new Object[]{Double.valueOf(d4)}), Long.toString(j1 / 1000L)});
               }
            } else {
               worldborder.func_177750_a(d8);
               func_152373_a(p_184881_2_, this, "commands.worldborder.set.success", new Object[]{String.format("%.1f", new Object[]{Double.valueOf(d8)}), String.format("%.1f", new Object[]{Double.valueOf(d4)})});
            }
         } else if(p_184881_3_[0].equals("center")) {
            if(p_184881_3_.length != 3) {
               throw new WrongUsageException("commands.worldborder.center.usage", new Object[0]);
            }

            BlockPos blockpos = p_184881_2_.func_180425_c();
            double d1 = func_175761_b((double)blockpos.func_177958_n() + 0.5D, p_184881_3_[1], true);
            double d3 = func_175761_b((double)blockpos.func_177952_p() + 0.5D, p_184881_3_[2], true);
            worldborder.func_177739_c(d1, d3);
            func_152373_a(p_184881_2_, this, "commands.worldborder.center.success", new Object[]{Double.valueOf(d1), Double.valueOf(d3)});
         } else if(p_184881_3_[0].equals("damage")) {
            if(p_184881_3_.length < 2) {
               throw new WrongUsageException("commands.worldborder.damage.usage", new Object[0]);
            }

            if(p_184881_3_[1].equals("buffer")) {
               if(p_184881_3_.length != 3) {
                  throw new WrongUsageException("commands.worldborder.damage.buffer.usage", new Object[0]);
               }

               double d5 = func_180526_a(p_184881_3_[2], 0.0D);
               double d9 = worldborder.func_177742_m();
               worldborder.func_177724_b(d5);
               func_152373_a(p_184881_2_, this, "commands.worldborder.damage.buffer.success", new Object[]{String.format("%.1f", new Object[]{Double.valueOf(d5)}), String.format("%.1f", new Object[]{Double.valueOf(d9)})});
            } else if(p_184881_3_[1].equals("amount")) {
               if(p_184881_3_.length != 3) {
                  throw new WrongUsageException("commands.worldborder.damage.amount.usage", new Object[0]);
               }

               double d6 = func_180526_a(p_184881_3_[2], 0.0D);
               double d10 = worldborder.func_177727_n();
               worldborder.func_177744_c(d6);
               func_152373_a(p_184881_2_, this, "commands.worldborder.damage.amount.success", new Object[]{String.format("%.2f", new Object[]{Double.valueOf(d6)}), String.format("%.2f", new Object[]{Double.valueOf(d10)})});
            }
         } else if(p_184881_3_[0].equals("warning")) {
            if(p_184881_3_.length < 2) {
               throw new WrongUsageException("commands.worldborder.warning.usage", new Object[0]);
            }

            if(p_184881_3_[1].equals("time")) {
               if(p_184881_3_.length != 3) {
                  throw new WrongUsageException("commands.worldborder.warning.time.usage", new Object[0]);
               }

               int j = func_180528_a(p_184881_3_[2], 0);
               int l = worldborder.func_177740_p();
               worldborder.func_177723_b(j);
               func_152373_a(p_184881_2_, this, "commands.worldborder.warning.time.success", new Object[]{Integer.valueOf(j), Integer.valueOf(l)});
            } else if(p_184881_3_[1].equals("distance")) {
               if(p_184881_3_.length != 3) {
                  throw new WrongUsageException("commands.worldborder.warning.distance.usage", new Object[0]);
               }

               int k = func_180528_a(p_184881_3_[2], 0);
               int i1 = worldborder.func_177748_q();
               worldborder.func_177747_c(k);
               func_152373_a(p_184881_2_, this, "commands.worldborder.warning.distance.success", new Object[]{Integer.valueOf(k), Integer.valueOf(i1)});
            }
         } else {
            if(!p_184881_3_[0].equals("get")) {
               throw new WrongUsageException("commands.worldborder.usage", new Object[0]);
            }

            double d7 = worldborder.func_177741_h();
            p_184881_2_.func_174794_a(CommandResultStats.Type.QUERY_RESULT, MathHelper.func_76128_c(d7 + 0.5D));
            p_184881_2_.func_145747_a(new TextComponentTranslation("commands.worldborder.get.success", new Object[]{String.format("%.0f", new Object[]{Double.valueOf(d7)})}));
         }

      }
   }

   protected WorldBorder func_184931_a(MinecraftServer p_184931_1_) {
      return p_184931_1_.field_71305_c[0].func_175723_af();
   }

   public List<String> func_184883_a(MinecraftServer p_184883_1_, ICommandSender p_184883_2_, String[] p_184883_3_, BlockPos p_184883_4_) {
      return p_184883_3_.length == 1?func_71530_a(p_184883_3_, new String[]{"set", "center", "damage", "warning", "add", "get"}):(p_184883_3_.length == 2 && p_184883_3_[0].equals("damage")?func_71530_a(p_184883_3_, new String[]{"buffer", "amount"}):(p_184883_3_.length >= 2 && p_184883_3_.length <= 3 && p_184883_3_[0].equals("center")?func_181043_b(p_184883_3_, 1, p_184883_4_):(p_184883_3_.length == 2 && p_184883_3_[0].equals("warning")?func_71530_a(p_184883_3_, new String[]{"time", "distance"}):Collections.emptyList())));
   }
}
