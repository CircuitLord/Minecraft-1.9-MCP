package net.minecraft.stats;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.JsonSerializableSet;

public class AchievementList {
   public static int field_76010_a;
   public static int field_76008_b;
   public static int field_76009_c;
   public static int field_76006_d;
   public static final List<Achievement> field_187981_e = Lists.<Achievement>newArrayList();
   public static final Achievement field_187982_f = (new Achievement("achievement.openInventory", "openInventory", 0, 0, Items.field_151122_aG, (Achievement)null)).func_75966_h().func_75971_g();
   public static final Achievement field_187983_g = (new Achievement("achievement.mineWood", "mineWood", 2, 1, Blocks.field_150364_r, field_187982_f)).func_75971_g();
   public static final Achievement field_187984_h = (new Achievement("achievement.buildWorkBench", "buildWorkBench", 4, -1, Blocks.field_150462_ai, field_187983_g)).func_75971_g();
   public static final Achievement field_187985_i = (new Achievement("achievement.buildPickaxe", "buildPickaxe", 4, 2, Items.field_151039_o, field_187984_h)).func_75971_g();
   public static final Achievement field_187986_j = (new Achievement("achievement.buildFurnace", "buildFurnace", 3, 4, Blocks.field_150460_al, field_187985_i)).func_75971_g();
   public static final Achievement field_187987_k = (new Achievement("achievement.acquireIron", "acquireIron", 1, 4, Items.field_151042_j, field_187986_j)).func_75971_g();
   public static final Achievement field_76013_l = (new Achievement("achievement.buildHoe", "buildHoe", 2, -3, Items.field_151017_I, field_187984_h)).func_75971_g();
   public static final Achievement field_187988_m = (new Achievement("achievement.makeBread", "makeBread", -1, -3, Items.field_151025_P, field_76013_l)).func_75971_g();
   public static final Achievement field_76011_n = (new Achievement("achievement.bakeCake", "bakeCake", 0, -5, Items.field_151105_aU, field_76013_l)).func_75971_g();
   public static final Achievement field_187989_o = (new Achievement("achievement.buildBetterPickaxe", "buildBetterPickaxe", 6, 2, Items.field_151050_s, field_187985_i)).func_75971_g();
   public static final Achievement field_76026_p = (new Achievement("achievement.cookFish", "cookFish", 2, 6, Items.field_179566_aV, field_187986_j)).func_75971_g();
   public static final Achievement field_187990_q = (new Achievement("achievement.onARail", "onARail", 2, 3, Blocks.field_150448_aq, field_187987_k)).func_75987_b().func_75971_g();
   public static final Achievement field_187991_r = (new Achievement("achievement.buildSword", "buildSword", 6, -1, Items.field_151041_m, field_187984_h)).func_75971_g();
   public static final Achievement field_187992_s = (new Achievement("achievement.killEnemy", "killEnemy", 8, -1, Items.field_151103_aS, field_187991_r)).func_75971_g();
   public static final Achievement field_76022_t = (new Achievement("achievement.killCow", "killCow", 7, -3, Items.field_151116_aA, field_187991_r)).func_75971_g();
   public static final Achievement field_187993_u = (new Achievement("achievement.flyPig", "flyPig", 9, -3, Items.field_151141_av, field_76022_t)).func_75987_b().func_75971_g();
   public static final Achievement field_187994_v = (new Achievement("achievement.snipeSkeleton", "snipeSkeleton", 7, 0, Items.field_151031_f, field_187992_s)).func_75987_b().func_75971_g();
   public static final Achievement field_187995_w = (new Achievement("achievement.diamonds", "diamonds", -1, 5, Blocks.field_150482_ag, field_187987_k)).func_75971_g();
   public static final Achievement field_187996_x = (new Achievement("achievement.diamondsToYou", "diamondsToYou", -1, 2, Items.field_151045_i, field_187995_w)).func_75971_g();
   public static final Achievement field_187997_y = (new Achievement("achievement.portal", "portal", -1, 7, Blocks.field_150343_Z, field_187995_w)).func_75971_g();
   public static final Achievement field_76028_y = (new Achievement("achievement.ghast", "ghast", -4, 8, Items.field_151073_bk, field_187997_y)).func_75987_b().func_75971_g();
   public static final Achievement field_187969_A = (new Achievement("achievement.blazeRod", "blazeRod", 0, 9, Items.field_151072_bj, field_187997_y)).func_75971_g();
   public static final Achievement field_187970_B = (new Achievement("achievement.potion", "potion", 2, 8, Items.field_151068_bn, field_187969_A)).func_75971_g();
   public static final Achievement field_76002_B = (new Achievement("achievement.theEnd", "theEnd", 3, 10, Items.field_151061_bv, field_187969_A)).func_75987_b().func_75971_g();
   public static final Achievement field_187971_D = (new Achievement("achievement.theEnd2", "theEnd2", 4, 13, Blocks.field_150380_bt, field_76002_B)).func_75987_b().func_75971_g();
   public static final Achievement field_187972_E = (new Achievement("achievement.enchantments", "enchantments", -4, 4, Blocks.field_150381_bn, field_187995_w)).func_75971_g();
   public static final Achievement field_187973_F = (new Achievement("achievement.overkill", "overkill", -4, 1, Items.field_151048_u, field_187972_E)).func_75987_b().func_75971_g();
   public static final Achievement field_187974_G = (new Achievement("achievement.bookcase", "bookcase", -3, 6, Blocks.field_150342_X, field_187972_E)).func_75971_g();
   public static final Achievement field_187975_H = (new Achievement("achievement.breedCow", "breedCow", 7, -5, Items.field_151015_O, field_76022_t)).func_75971_g();
   public static final Achievement field_187976_I = (new Achievement("achievement.spawnWither", "spawnWither", 7, 12, new ItemStack(Items.field_151144_bL, 1, 1), field_187971_D)).func_75971_g();
   public static final Achievement field_187977_J = (new Achievement("achievement.killWither", "killWither", 7, 10, Items.field_151156_bN, field_187976_I)).func_75971_g();
   public static final Achievement field_187978_K = (new Achievement("achievement.fullBeacon", "fullBeacon", 7, 8, Blocks.field_150461_bJ, field_187977_J)).func_75987_b().func_75971_g();
   public static final Achievement field_187979_L = (new Achievement("achievement.exploreAllBiomes", "exploreAllBiomes", 4, 8, Items.field_151175_af, field_76002_B)).func_150953_b(JsonSerializableSet.class).func_75987_b().func_75971_g();
   public static final Achievement field_187980_M = (new Achievement("achievement.overpowered", "overpowered", 6, 4, new ItemStack(Items.field_151153_ao, 1, 1), field_187989_o)).func_75987_b().func_75971_g();

   public static void func_75997_a() {
   }
}
