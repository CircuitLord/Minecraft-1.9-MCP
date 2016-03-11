package net.minecraft.command.server;

import com.mojang.authlib.GameProfile;
import java.util.Collections;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandWhitelist extends CommandBase {
   public String func_71517_b() {
      return "whitelist";
   }

   public int func_82362_a() {
      return 3;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.whitelist.usage";
   }

   public void func_184881_a(MinecraftServer p_184881_1_, ICommandSender p_184881_2_, String[] p_184881_3_) throws CommandException {
      if(p_184881_3_.length < 1) {
         throw new WrongUsageException("commands.whitelist.usage", new Object[0]);
      } else {
         if(p_184881_3_[0].equals("on")) {
            p_184881_1_.func_184103_al().func_72371_a(true);
            func_152373_a(p_184881_2_, this, "commands.whitelist.enabled", new Object[0]);
         } else if(p_184881_3_[0].equals("off")) {
            p_184881_1_.func_184103_al().func_72371_a(false);
            func_152373_a(p_184881_2_, this, "commands.whitelist.disabled", new Object[0]);
         } else if(p_184881_3_[0].equals("list")) {
            p_184881_2_.func_145747_a(new TextComponentTranslation("commands.whitelist.list", new Object[]{Integer.valueOf(p_184881_1_.func_184103_al().func_152598_l().length), Integer.valueOf(p_184881_1_.func_184103_al().func_72373_m().length)}));
            String[] astring = p_184881_1_.func_184103_al().func_152598_l();
            p_184881_2_.func_145747_a(new TextComponentString(func_71527_a(astring)));
         } else if(p_184881_3_[0].equals("add")) {
            if(p_184881_3_.length < 2) {
               throw new WrongUsageException("commands.whitelist.add.usage", new Object[0]);
            }

            GameProfile gameprofile = p_184881_1_.func_152358_ax().func_152655_a(p_184881_3_[1]);
            if(gameprofile == null) {
               throw new CommandException("commands.whitelist.add.failed", new Object[]{p_184881_3_[1]});
            }

            p_184881_1_.func_184103_al().func_152601_d(gameprofile);
            func_152373_a(p_184881_2_, this, "commands.whitelist.add.success", new Object[]{p_184881_3_[1]});
         } else if(p_184881_3_[0].equals("remove")) {
            if(p_184881_3_.length < 2) {
               throw new WrongUsageException("commands.whitelist.remove.usage", new Object[0]);
            }

            GameProfile gameprofile1 = p_184881_1_.func_184103_al().func_152599_k().func_152706_a(p_184881_3_[1]);
            if(gameprofile1 == null) {
               throw new CommandException("commands.whitelist.remove.failed", new Object[]{p_184881_3_[1]});
            }

            p_184881_1_.func_184103_al().func_152597_c(gameprofile1);
            func_152373_a(p_184881_2_, this, "commands.whitelist.remove.success", new Object[]{p_184881_3_[1]});
         } else if(p_184881_3_[0].equals("reload")) {
            p_184881_1_.func_184103_al().func_187244_a();
            func_152373_a(p_184881_2_, this, "commands.whitelist.reloaded", new Object[0]);
         }

      }
   }

   public List<String> func_184883_a(MinecraftServer p_184883_1_, ICommandSender p_184883_2_, String[] p_184883_3_, BlockPos p_184883_4_) {
      if(p_184883_3_.length == 1) {
         return func_71530_a(p_184883_3_, new String[]{"on", "off", "list", "add", "remove", "reload"});
      } else {
         if(p_184883_3_.length == 2) {
            if(p_184883_3_[0].equals("remove")) {
               return func_71530_a(p_184883_3_, p_184883_1_.func_184103_al().func_152598_l());
            }

            if(p_184883_3_[0].equals("add")) {
               return func_71530_a(p_184883_3_, p_184883_1_.func_152358_ax().func_152654_a());
            }
         }

         return Collections.<String>emptyList();
      }
   }
}
