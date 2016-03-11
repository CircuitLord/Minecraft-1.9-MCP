package net.minecraft.potion;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;

public class PotionHelper {
   private static final List<PotionHelper.MixPredicate<PotionType>> field_185213_a = Lists.<PotionHelper.MixPredicate<PotionType>>newArrayList();
   private static final List<PotionHelper.MixPredicate<Item>> field_185214_b = Lists.<PotionHelper.MixPredicate<Item>>newArrayList();
   private static final List<PotionHelper.ItemPredicateInstance> field_185215_c = Lists.<PotionHelper.ItemPredicateInstance>newArrayList();
   private static final Predicate<ItemStack> field_185216_d = new Predicate<ItemStack>() {
      public boolean apply(ItemStack p_apply_1_) {
         for(PotionHelper.ItemPredicateInstance potionhelper$itempredicateinstance : PotionHelper.field_185215_c) {
            if(potionhelper$itempredicateinstance.apply(p_apply_1_)) {
               return true;
            }
         }

         return false;
      }
   };

   public static boolean func_185205_a(ItemStack p_185205_0_) {
      return func_185203_b(p_185205_0_) || func_185211_c(p_185205_0_);
   }

   protected static boolean func_185203_b(ItemStack p_185203_0_) {
      int i = 0;

      for(int j = field_185214_b.size(); i < j; ++i) {
         if(((PotionHelper.MixPredicate)field_185214_b.get(i)).field_185199_b.apply(p_185203_0_)) {
            return true;
         }
      }

      return false;
   }

   protected static boolean func_185211_c(ItemStack p_185211_0_) {
      int i = 0;

      for(int j = field_185213_a.size(); i < j; ++i) {
         if(((PotionHelper.MixPredicate)field_185213_a.get(i)).field_185199_b.apply(p_185211_0_)) {
            return true;
         }
      }

      return false;
   }

   public static boolean func_185208_a(ItemStack p_185208_0_, ItemStack p_185208_1_) {
      return !field_185216_d.apply(p_185208_0_)?false:func_185206_b(p_185208_0_, p_185208_1_) || func_185209_c(p_185208_0_, p_185208_1_);
   }

   protected static boolean func_185206_b(ItemStack p_185206_0_, ItemStack p_185206_1_) {
      Item item = p_185206_0_.func_77973_b();
      int i = 0;

      for(int j = field_185214_b.size(); i < j; ++i) {
         PotionHelper.MixPredicate<Item> mixpredicate = (PotionHelper.MixPredicate)field_185214_b.get(i);
         if(mixpredicate.field_185198_a == item && mixpredicate.field_185199_b.apply(p_185206_1_)) {
            return true;
         }
      }

      return false;
   }

   protected static boolean func_185209_c(ItemStack p_185209_0_, ItemStack p_185209_1_) {
      PotionType potiontype = PotionUtils.func_185191_c(p_185209_0_);
      int i = 0;

      for(int j = field_185213_a.size(); i < j; ++i) {
         PotionHelper.MixPredicate<PotionType> mixpredicate = (PotionHelper.MixPredicate)field_185213_a.get(i);
         if(mixpredicate.field_185198_a == potiontype && mixpredicate.field_185199_b.apply(p_185209_1_)) {
            return true;
         }
      }

      return false;
   }

   public static ItemStack func_185212_d(ItemStack p_185212_0_, ItemStack p_185212_1_) {
      if(p_185212_1_ != null) {
         PotionType potiontype = PotionUtils.func_185191_c(p_185212_1_);
         Item item = p_185212_1_.func_77973_b();
         int i = 0;

         for(int j = field_185214_b.size(); i < j; ++i) {
            PotionHelper.MixPredicate<Item> mixpredicate = (PotionHelper.MixPredicate)field_185214_b.get(i);
            if(mixpredicate.field_185198_a == item && mixpredicate.field_185199_b.apply(p_185212_0_)) {
               return PotionUtils.func_185188_a(new ItemStack((Item)mixpredicate.field_185200_c), potiontype);
            }
         }

         i = 0;

         for(int k = field_185213_a.size(); i < k; ++i) {
            PotionHelper.MixPredicate<PotionType> mixpredicate1 = (PotionHelper.MixPredicate)field_185213_a.get(i);
            if(mixpredicate1.field_185198_a == potiontype && mixpredicate1.field_185199_b.apply(p_185212_0_)) {
               return PotionUtils.func_185188_a(new ItemStack(item), (PotionType)mixpredicate1.field_185200_c);
            }
         }
      }

      return p_185212_1_;
   }

   public static void func_185207_a() {
      Predicate<ItemStack> predicate = new PotionHelper.ItemPredicateInstance(Items.field_151075_bm);
      Predicate<ItemStack> predicate1 = new PotionHelper.ItemPredicateInstance(Items.field_151150_bK);
      Predicate<ItemStack> predicate2 = new PotionHelper.ItemPredicateInstance(Items.field_151137_ax);
      Predicate<ItemStack> predicate3 = new PotionHelper.ItemPredicateInstance(Items.field_151071_bq);
      Predicate<ItemStack> predicate4 = new PotionHelper.ItemPredicateInstance(Items.field_179556_br);
      Predicate<ItemStack> predicate5 = new PotionHelper.ItemPredicateInstance(Items.field_151114_aO);
      Predicate<ItemStack> predicate6 = new PotionHelper.ItemPredicateInstance(Items.field_151064_bs);
      Predicate<ItemStack> predicate7 = new PotionHelper.ItemPredicateInstance(Items.field_151102_aT);
      Predicate<ItemStack> predicate8 = new PotionHelper.ItemPredicateInstance(Items.field_151115_aP, ItemFishFood.FishType.PUFFERFISH.func_150976_a());
      Predicate<ItemStack> predicate9 = new PotionHelper.ItemPredicateInstance(Items.field_151060_bw);
      Predicate<ItemStack> predicate10 = new PotionHelper.ItemPredicateInstance(Items.field_151070_bp);
      Predicate<ItemStack> predicate11 = new PotionHelper.ItemPredicateInstance(Items.field_151073_bk);
      Predicate<ItemStack> predicate12 = new PotionHelper.ItemPredicateInstance(Items.field_151065_br);
      func_185202_a(new PotionHelper.ItemPredicateInstance(Items.field_151068_bn));
      func_185202_a(new PotionHelper.ItemPredicateInstance(Items.field_185155_bH));
      func_185202_a(new PotionHelper.ItemPredicateInstance(Items.field_185156_bI));
      func_185201_a(Items.field_151068_bn, new PotionHelper.ItemPredicateInstance(Items.field_151016_H), Items.field_185155_bH);
      func_185201_a(Items.field_185155_bH, new PotionHelper.ItemPredicateInstance(Items.field_185157_bK), Items.field_185156_bI);
      func_185204_a(PotionTypes.field_185230_b, predicate9, PotionTypes.field_185231_c);
      func_185204_a(PotionTypes.field_185230_b, predicate11, PotionTypes.field_185231_c);
      func_185204_a(PotionTypes.field_185230_b, predicate4, PotionTypes.field_185231_c);
      func_185204_a(PotionTypes.field_185230_b, predicate12, PotionTypes.field_185231_c);
      func_185204_a(PotionTypes.field_185230_b, predicate10, PotionTypes.field_185231_c);
      func_185204_a(PotionTypes.field_185230_b, predicate7, PotionTypes.field_185231_c);
      func_185204_a(PotionTypes.field_185230_b, predicate6, PotionTypes.field_185231_c);
      func_185204_a(PotionTypes.field_185230_b, predicate5, PotionTypes.field_185232_d);
      func_185204_a(PotionTypes.field_185230_b, predicate2, PotionTypes.field_185231_c);
      func_185204_a(PotionTypes.field_185230_b, predicate, PotionTypes.field_185233_e);
      func_185204_a(PotionTypes.field_185233_e, predicate1, PotionTypes.field_185234_f);
      func_185204_a(PotionTypes.field_185234_f, predicate2, PotionTypes.field_185235_g);
      func_185204_a(PotionTypes.field_185234_f, predicate3, PotionTypes.field_185236_h);
      func_185204_a(PotionTypes.field_185235_g, predicate3, PotionTypes.field_185237_i);
      func_185204_a(PotionTypes.field_185236_h, predicate2, PotionTypes.field_185237_i);
      func_185204_a(PotionTypes.field_185233_e, predicate6, PotionTypes.field_185241_m);
      func_185204_a(PotionTypes.field_185241_m, predicate2, PotionTypes.field_185242_n);
      func_185204_a(PotionTypes.field_185233_e, predicate4, PotionTypes.field_185238_j);
      func_185204_a(PotionTypes.field_185238_j, predicate2, PotionTypes.field_185239_k);
      func_185204_a(PotionTypes.field_185238_j, predicate5, PotionTypes.field_185240_l);
      func_185204_a(PotionTypes.field_185238_j, predicate3, PotionTypes.field_185246_r);
      func_185204_a(PotionTypes.field_185239_k, predicate3, PotionTypes.field_185247_s);
      func_185204_a(PotionTypes.field_185246_r, predicate2, PotionTypes.field_185247_s);
      func_185204_a(PotionTypes.field_185243_o, predicate3, PotionTypes.field_185246_r);
      func_185204_a(PotionTypes.field_185244_p, predicate3, PotionTypes.field_185247_s);
      func_185204_a(PotionTypes.field_185233_e, predicate7, PotionTypes.field_185243_o);
      func_185204_a(PotionTypes.field_185243_o, predicate2, PotionTypes.field_185244_p);
      func_185204_a(PotionTypes.field_185243_o, predicate5, PotionTypes.field_185245_q);
      func_185204_a(PotionTypes.field_185233_e, predicate8, PotionTypes.field_185248_t);
      func_185204_a(PotionTypes.field_185248_t, predicate2, PotionTypes.field_185249_u);
      func_185204_a(PotionTypes.field_185233_e, predicate9, PotionTypes.field_185250_v);
      func_185204_a(PotionTypes.field_185250_v, predicate5, PotionTypes.field_185251_w);
      func_185204_a(PotionTypes.field_185250_v, predicate3, PotionTypes.field_185252_x);
      func_185204_a(PotionTypes.field_185251_w, predicate3, PotionTypes.field_185253_y);
      func_185204_a(PotionTypes.field_185252_x, predicate5, PotionTypes.field_185253_y);
      func_185204_a(PotionTypes.field_185254_z, predicate3, PotionTypes.field_185252_x);
      func_185204_a(PotionTypes.field_185218_A, predicate3, PotionTypes.field_185252_x);
      func_185204_a(PotionTypes.field_185219_B, predicate3, PotionTypes.field_185253_y);
      func_185204_a(PotionTypes.field_185233_e, predicate10, PotionTypes.field_185254_z);
      func_185204_a(PotionTypes.field_185254_z, predicate2, PotionTypes.field_185218_A);
      func_185204_a(PotionTypes.field_185254_z, predicate5, PotionTypes.field_185219_B);
      func_185204_a(PotionTypes.field_185233_e, predicate11, PotionTypes.field_185220_C);
      func_185204_a(PotionTypes.field_185220_C, predicate2, PotionTypes.field_185221_D);
      func_185204_a(PotionTypes.field_185220_C, predicate5, PotionTypes.field_185222_E);
      func_185204_a(PotionTypes.field_185233_e, predicate12, PotionTypes.field_185223_F);
      func_185204_a(PotionTypes.field_185223_F, predicate2, PotionTypes.field_185224_G);
      func_185204_a(PotionTypes.field_185223_F, predicate5, PotionTypes.field_185225_H);
      func_185204_a(PotionTypes.field_185230_b, predicate3, PotionTypes.field_185226_I);
      func_185204_a(PotionTypes.field_185226_I, predicate2, PotionTypes.field_185227_J);
   }

   private static void func_185201_a(ItemPotion p_185201_0_, PotionHelper.ItemPredicateInstance p_185201_1_, ItemPotion p_185201_2_) {
      field_185214_b.add(new PotionHelper.MixPredicate(p_185201_0_, p_185201_1_, p_185201_2_));
   }

   private static void func_185202_a(PotionHelper.ItemPredicateInstance p_185202_0_) {
      field_185215_c.add(p_185202_0_);
   }

   private static void func_185204_a(PotionType p_185204_0_, Predicate<ItemStack> p_185204_1_, PotionType p_185204_2_) {
      field_185213_a.add(new PotionHelper.MixPredicate(p_185204_0_, p_185204_1_, p_185204_2_));
   }

   static class ItemPredicateInstance implements Predicate<ItemStack> {
      private final Item field_185195_a;
      private final int field_185196_b;

      public ItemPredicateInstance(Item p_i47013_1_) {
         this(p_i47013_1_, -1);
      }

      public ItemPredicateInstance(Item p_i47014_1_, int p_i47014_2_) {
         this.field_185195_a = p_i47014_1_;
         this.field_185196_b = p_i47014_2_;
      }

      public boolean apply(ItemStack p_apply_1_) {
         return p_apply_1_ != null && p_apply_1_.func_77973_b() == this.field_185195_a && (this.field_185196_b == -1 || this.field_185196_b == p_apply_1_.func_77960_j());
      }
   }

   static class MixPredicate<T> {
      final T field_185198_a;
      final Predicate<ItemStack> field_185199_b;
      final T field_185200_c;

      public MixPredicate(T p_i47012_1_, Predicate<ItemStack> p_i47012_2_, T p_i47012_3_) {
         this.field_185198_a = p_i47012_1_;
         this.field_185199_b = p_i47012_2_;
         this.field_185200_c = p_i47012_3_;
      }
   }
}
