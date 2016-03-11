package net.minecraft.command.server;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.math.BlockPos;

public class CommandAchievement extends CommandBase {
   public String func_71517_b() {
      return "achievement";
   }

   public int func_82362_a() {
      return 2;
   }

   public String func_71518_a(ICommandSender p_71518_1_) {
      return "commands.achievement.usage";
   }

   public void func_184881_a(MinecraftServer p_184881_1_, ICommandSender p_184881_2_, String[] p_184881_3_) throws CommandException {
      if(p_184881_3_.length < 2) {
         throw new WrongUsageException("commands.achievement.usage", new Object[0]);
      } else {
         final StatBase statbase = StatList.func_151177_a(p_184881_3_[1]);
         if((statbase != null || p_184881_3_[1].equals("*")) && (statbase == null || statbase.func_75967_d())) {
            final EntityPlayerMP entityplayermp = p_184881_3_.length >= 3?func_184888_a(p_184881_1_, p_184881_2_, p_184881_3_[2]):func_71521_c(p_184881_2_);
            boolean flag = p_184881_3_[0].equalsIgnoreCase("give");
            boolean flag1 = p_184881_3_[0].equalsIgnoreCase("take");
            if(flag || flag1) {
               if(statbase == null) {
                  if(flag) {
                     for(Achievement achievement4 : AchievementList.field_187981_e) {
                        entityplayermp.func_71029_a(achievement4);
                     }

                     func_152373_a(p_184881_2_, this, "commands.achievement.give.success.all", new Object[]{entityplayermp.func_70005_c_()});
                  } else if(flag1) {
                     for(Achievement achievement5 : Lists.reverse(AchievementList.field_187981_e)) {
                        entityplayermp.func_175145_a(achievement5);
                     }

                     func_152373_a(p_184881_2_, this, "commands.achievement.take.success.all", new Object[]{entityplayermp.func_70005_c_()});
                  }

               } else {
                  if(statbase instanceof Achievement) {
                     Achievement achievement = (Achievement)statbase;
                     if(flag) {
                        if(entityplayermp.func_147099_x().func_77443_a(achievement)) {
                           throw new CommandException("commands.achievement.alreadyHave", new Object[]{entityplayermp.func_70005_c_(), statbase.func_150955_j()});
                        }

                        List<Achievement> list;
                        for(list = Lists.<Achievement>newArrayList(); achievement.field_75992_c != null && !entityplayermp.func_147099_x().func_77443_a(achievement.field_75992_c); achievement = achievement.field_75992_c) {
                           list.add(achievement.field_75992_c);
                        }

                        for(Achievement achievement1 : Lists.reverse(list)) {
                           entityplayermp.func_71029_a(achievement1);
                        }
                     } else if(flag1) {
                        if(!entityplayermp.func_147099_x().func_77443_a(achievement)) {
                           throw new CommandException("commands.achievement.dontHave", new Object[]{entityplayermp.func_70005_c_(), statbase.func_150955_j()});
                        }

                        List<Achievement> list1 = Lists.newArrayList(Iterators.filter(AchievementList.field_187981_e.iterator(), new Predicate<Achievement>() {
                           public boolean apply(Achievement p_apply_1_) {
                              return entityplayermp.func_147099_x().func_77443_a(p_apply_1_) && p_apply_1_ != statbase;
                           }
                        }));
                        List<Achievement> list2 = Lists.newArrayList(list1);

                        for(Achievement achievement2 : list1) {
                           Achievement achievement3 = achievement2;

                           boolean flag2;
                           for(flag2 = false; achievement3 != null; achievement3 = achievement3.field_75992_c) {
                              if(achievement3 == statbase) {
                                 flag2 = true;
                              }
                           }

                           if(!flag2) {
                              for(achievement3 = achievement2; achievement3 != null; achievement3 = achievement3.field_75992_c) {
                                 list2.remove(achievement2);
                              }
                           }
                        }

                        for(Achievement achievement6 : list2) {
                           entityplayermp.func_175145_a(achievement6);
                        }
                     }
                  }

                  if(flag) {
                     entityplayermp.func_71029_a(statbase);
                     func_152373_a(p_184881_2_, this, "commands.achievement.give.success.one", new Object[]{entityplayermp.func_70005_c_(), statbase.func_150955_j()});
                  } else if(flag1) {
                     entityplayermp.func_175145_a(statbase);
                     func_152373_a(p_184881_2_, this, "commands.achievement.take.success.one", new Object[]{statbase.func_150955_j(), entityplayermp.func_70005_c_()});
                  }

               }
            }
         } else {
            throw new CommandException("commands.achievement.unknownAchievement", new Object[]{p_184881_3_[1]});
         }
      }
   }

   public List<String> func_184883_a(MinecraftServer p_184883_1_, ICommandSender p_184883_2_, String[] p_184883_3_, BlockPos p_184883_4_) {
      if(p_184883_3_.length == 1) {
         return func_71530_a(p_184883_3_, new String[]{"give", "take"});
      } else if(p_184883_3_.length != 2) {
         return p_184883_3_.length == 3?func_71530_a(p_184883_3_, p_184883_1_.func_71213_z()):Collections.emptyList();
      } else {
         List<String> list = Lists.<String>newArrayList();

         for(StatBase statbase : AchievementList.field_187981_e) {
            list.add(statbase.field_75975_e);
         }

         return func_175762_a(p_184883_3_, list);
      }
   }

   public boolean func_82358_a(String[] p_82358_1_, int p_82358_2_) {
      return p_82358_2_ == 2;
   }
}
