package net.minecraft.command;

import java.util.Collections;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldSettings;

public class CommandGameMode extends CommandBase {
   public String func_71517_b() {
      return "gamemode";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.gamemode.usage";
   }

   public void func_184881_a(MinecraftServer p_184881_1_, ICommandSender p_184881_2_, String[] p_184881_3_) throws CommandException {
      if(p_184881_3_.length <= 0) {
         throw new WrongUsageException("commands.gamemode.usage", new Object[0]);
      } else {
         WorldSettings.GameType worldsettings$gametype = this.func_71539_b(p_184881_2_, p_184881_3_[0]);
         EntityPlayer entityplayer = p_184881_3_.length >= 2?func_184888_a(p_184881_1_, p_184881_2_, p_184881_3_[1]):func_71521_c(p_184881_2_);
         entityplayer.func_71033_a(worldsettings$gametype);
         ITextComponent itextcomponent = new TextComponentTranslation("gameMode." + worldsettings$gametype.func_77149_b(), new Object[0]);
         if(p_184881_2_.func_130014_f_().func_82736_K().func_82766_b("sendCommandFeedback")) {
            entityplayer.func_145747_a(new TextComponentTranslation("gameMode.changed", new Object[]{itextcomponent}));
         }

         if(entityplayer != p_184881_2_) {
            func_152374_a(p_184881_2_, this, 1, "commands.gamemode.success.other", new Object[]{entityplayer.func_70005_c_(), itextcomponent});
         } else {
            func_152374_a(p_184881_2_, this, 1, "commands.gamemode.success.self", new Object[]{itextcomponent});
         }

      }
   }

   protected WorldSettings.GameType func_71539_b(ICommandSender p_71539_1_, String p_71539_2_) throws CommandException, NumberInvalidException {
      WorldSettings.GameType worldsettings$gametype = WorldSettings.GameType.func_185328_a(p_71539_2_, WorldSettings.GameType.NOT_SET);
      return worldsettings$gametype == WorldSettings.GameType.NOT_SET?WorldSettings.func_77161_a(func_175764_a(p_71539_2_, 0, WorldSettings.GameType.values().length - 2)):worldsettings$gametype;
   }

   public List<String> func_184883_a(MinecraftServer p_184883_1_, ICommandSender p_184883_2_, String[] p_184883_3_, BlockPos p_184883_4_) {
      return p_184883_3_.length == 1?func_71530_a(p_184883_3_, new String[]{"survival", "creative", "adventure", "spectator"}):(p_184883_3_.length == 2?func_71530_a(p_184883_3_, p_184883_1_.func_71213_z()):Collections.emptyList());
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 1;
   }
}
