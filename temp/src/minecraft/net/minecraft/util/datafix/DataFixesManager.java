package net.minecraft.util.datafix;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.IDataFixer;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.util.datafix.fixes.ArmorStandSilent;
import net.minecraft.util.datafix.fixes.BookPagesStrictJSON;
import net.minecraft.util.datafix.fixes.EntityArmorAndHeld;
import net.minecraft.util.datafix.fixes.EntityHealth;
import net.minecraft.util.datafix.fixes.HorseSaddle;
import net.minecraft.util.datafix.fixes.ItemIntIDToString;
import net.minecraft.util.datafix.fixes.MinecartEntityTypes;
import net.minecraft.util.datafix.fixes.PaintingDirection;
import net.minecraft.util.datafix.fixes.PotionItems;
import net.minecraft.util.datafix.fixes.RedundantChanceTags;
import net.minecraft.util.datafix.fixes.RidingToPassengers;
import net.minecraft.util.datafix.fixes.SignStrictJSON;
import net.minecraft.util.datafix.fixes.SpawnEggNames;
import net.minecraft.util.datafix.fixes.SpawnerEntityTypes;
import net.minecraft.util.datafix.fixes.StringToUUID;
import net.minecraft.util.datafix.walkers.BlockEntityTag;
import net.minecraft.util.datafix.walkers.EntityTag;
import net.minecraft.util.datafix.walkers.ItemStackData;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;

public class DataFixesManager {
   private static void func_188276_a(DataFixer p_188276_0_) {
      p_188276_0_.func_188256_a(FixTypes.ENTITY, new EntityArmorAndHeld());
      p_188276_0_.func_188256_a(FixTypes.BLOCK_ENTITY, new SignStrictJSON());
      p_188276_0_.func_188256_a(FixTypes.ITEM_INSTANCE, new ItemIntIDToString());
      p_188276_0_.func_188256_a(FixTypes.ITEM_INSTANCE, new PotionItems());
      p_188276_0_.func_188256_a(FixTypes.ITEM_INSTANCE, new SpawnEggNames());
      p_188276_0_.func_188256_a(FixTypes.ENTITY, new MinecartEntityTypes());
      p_188276_0_.func_188256_a(FixTypes.BLOCK_ENTITY, new SpawnerEntityTypes());
      p_188276_0_.func_188256_a(FixTypes.ENTITY, new StringToUUID());
      p_188276_0_.func_188256_a(FixTypes.ENTITY, new EntityHealth());
      p_188276_0_.func_188256_a(FixTypes.ENTITY, new HorseSaddle());
      p_188276_0_.func_188256_a(FixTypes.ENTITY, new PaintingDirection());
      p_188276_0_.func_188256_a(FixTypes.ENTITY, new RedundantChanceTags());
      p_188276_0_.func_188256_a(FixTypes.ENTITY, new RidingToPassengers());
      p_188276_0_.func_188256_a(FixTypes.ENTITY, new ArmorStandSilent());
      p_188276_0_.func_188256_a(FixTypes.ITEM_INSTANCE, new BookPagesStrictJSON());
   }

   public static DataFixer func_188279_a() {
      DataFixer datafixer = new DataFixer(169);
      datafixer.func_188258_a(FixTypes.LEVEL, new IDataWalker() {
         public NBTTagCompound func_188266_a(IDataFixer p_188266_1_, NBTTagCompound p_188266_2_, int p_188266_3_) {
            if(p_188266_2_.func_150297_b("Player", 10)) {
               p_188266_2_.func_74782_a("Player", p_188266_1_.func_188251_a(FixTypes.PLAYER, p_188266_2_.func_74775_l("Player"), p_188266_3_));
            }

            return p_188266_2_;
         }
      });
      datafixer.func_188258_a(FixTypes.PLAYER, new IDataWalker() {
         public NBTTagCompound func_188266_a(IDataFixer p_188266_1_, NBTTagCompound p_188266_2_, int p_188266_3_) {
            DataFixesManager.func_188278_b(p_188266_1_, p_188266_2_, p_188266_3_, "Inventory");
            DataFixesManager.func_188278_b(p_188266_1_, p_188266_2_, p_188266_3_, "EnderItems");
            return p_188266_2_;
         }
      });
      datafixer.func_188258_a(FixTypes.CHUNK, new IDataWalker() {
         public NBTTagCompound func_188266_a(IDataFixer p_188266_1_, NBTTagCompound p_188266_2_, int p_188266_3_) {
            if(p_188266_2_.func_150297_b("Level", 10)) {
               NBTTagCompound nbttagcompound = p_188266_2_.func_74775_l("Level");
               if(nbttagcompound.func_150297_b("Entities", 9)) {
                  NBTTagList nbttaglist = nbttagcompound.func_150295_c("Entities", 10);

                  for(int i = 0; i < nbttaglist.func_74745_c(); ++i) {
                     nbttaglist.func_150304_a(i, p_188266_1_.func_188251_a(FixTypes.ENTITY, (NBTTagCompound)nbttaglist.func_179238_g(i), p_188266_3_));
                  }
               }

               if(nbttagcompound.func_150297_b("TileEntities", 9)) {
                  NBTTagList nbttaglist1 = nbttagcompound.func_150295_c("TileEntities", 10);

                  for(int j = 0; j < nbttaglist1.func_74745_c(); ++j) {
                     nbttaglist1.func_150304_a(j, p_188266_1_.func_188251_a(FixTypes.BLOCK_ENTITY, (NBTTagCompound)nbttaglist1.func_179238_g(j), p_188266_3_));
                  }
               }
            }

            return p_188266_2_;
         }
      });
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackData("Item", new String[]{"Item"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackData("ThrownPotion", new String[]{"Potion"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackData("ItemFrame", new String[]{"Item"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackData("FireworksRocketEntity", new String[]{"FireworksItem"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackData("TippedArrow", new String[]{"Item"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("MinecartChest", new String[]{"Items"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("MinecartHopper", new String[]{"Items"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Enderman", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("ArmorStand", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Bat", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Blaze", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("CaveSpider", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Chicken", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Cow", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Creeper", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("EnderDragon", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Endermite", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Ghast", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Giant", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Guardian", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("LavaSlime", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Mob", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Monster", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("MushroomCow", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Ozelot", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Pig", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("PigZombie", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Rabbit", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Sheep", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Shulker", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Silverfish", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Skeleton", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Slime", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("SnowMan", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Spider", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Squid", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("VillagerGolem", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Witch", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("WitherBoss", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Wolf", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Zombie", new String[]{"ArmorItems", "HandItems"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("EntityHorse", new String[]{"ArmorItems", "HandItems", "Items"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackData("EntityHorse", new String[]{"ArmorItem", "SaddleItem"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new ItemStackDataLists("Villager", new String[]{"ArmorItems", "HandItems", "Inventory"}));
      datafixer.func_188258_a(FixTypes.ENTITY, new IDataWalker() {
         public NBTTagCompound func_188266_a(IDataFixer p_188266_1_, NBTTagCompound p_188266_2_, int p_188266_3_) {
            if("Villager".equals(p_188266_2_.func_74779_i("id")) && p_188266_2_.func_150297_b("Offers", 10)) {
               NBTTagCompound nbttagcompound = p_188266_2_.func_74775_l("Offers");
               if(nbttagcompound.func_150297_b("Recipes", 9)) {
                  NBTTagList nbttaglist = nbttagcompound.func_150295_c("Recipes", 10);

                  for(int i = 0; i < nbttaglist.func_74745_c(); ++i) {
                     NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
                     DataFixesManager.func_188277_a(p_188266_1_, nbttagcompound1, p_188266_3_, "buy");
                     DataFixesManager.func_188277_a(p_188266_1_, nbttagcompound1, p_188266_3_, "buyB");
                     DataFixesManager.func_188277_a(p_188266_1_, nbttagcompound1, p_188266_3_, "sell");
                     nbttaglist.func_150304_a(i, nbttagcompound1);
                  }
               }
            }

            return p_188266_2_;
         }
      });
      datafixer.func_188258_a(FixTypes.ENTITY, new IDataWalker() {
         public NBTTagCompound func_188266_a(IDataFixer p_188266_1_, NBTTagCompound p_188266_2_, int p_188266_3_) {
            if("MinecartSpawner".equals(p_188266_2_.func_74779_i("id"))) {
               p_188266_2_.func_74778_a("id", "MobSpawner");
               p_188266_1_.func_188251_a(FixTypes.BLOCK_ENTITY, p_188266_2_, p_188266_3_);
               p_188266_2_.func_74778_a("id", "MinecartSpawner");
            }

            return p_188266_2_;
         }
      });
      datafixer.func_188258_a(FixTypes.ENTITY, new IDataWalker() {
         public NBTTagCompound func_188266_a(IDataFixer p_188266_1_, NBTTagCompound p_188266_2_, int p_188266_3_) {
            if("MinecartCommandBlock".equals(p_188266_2_.func_74779_i("id"))) {
               p_188266_2_.func_74778_a("id", "Control");
               p_188266_1_.func_188251_a(FixTypes.BLOCK_ENTITY, p_188266_2_, p_188266_3_);
               p_188266_2_.func_74778_a("id", "MinecartCommandBlock");
            }

            return p_188266_2_;
         }
      });
      datafixer.func_188258_a(FixTypes.BLOCK_ENTITY, new ItemStackDataLists("Furnace", new String[]{"Items"}));
      datafixer.func_188258_a(FixTypes.BLOCK_ENTITY, new ItemStackDataLists("Chest", new String[]{"Items"}));
      datafixer.func_188258_a(FixTypes.BLOCK_ENTITY, new ItemStackDataLists("Trap", new String[]{"Items"}));
      datafixer.func_188258_a(FixTypes.BLOCK_ENTITY, new ItemStackDataLists("Dropper", new String[]{"Items"}));
      datafixer.func_188258_a(FixTypes.BLOCK_ENTITY, new ItemStackDataLists("Cauldron", new String[]{"Items"}));
      datafixer.func_188258_a(FixTypes.BLOCK_ENTITY, new ItemStackDataLists("Hopper", new String[]{"Items"}));
      datafixer.func_188258_a(FixTypes.BLOCK_ENTITY, new ItemStackData("RecordPlayer", new String[]{"RecordItem"}));
      datafixer.func_188258_a(FixTypes.BLOCK_ENTITY, new IDataWalker() {
         public NBTTagCompound func_188266_a(IDataFixer p_188266_1_, NBTTagCompound p_188266_2_, int p_188266_3_) {
            if("MobSpawner".equals(p_188266_2_.func_74779_i("id"))) {
               if(p_188266_2_.func_150297_b("SpawnPotentials", 9)) {
                  NBTTagList nbttaglist = p_188266_2_.func_150295_c("SpawnPotentials", 10);

                  for(int i = 0; i < nbttaglist.func_74745_c(); ++i) {
                     NBTTagCompound nbttagcompound = nbttaglist.func_150305_b(i);
                     nbttagcompound.func_74782_a("Entity", p_188266_1_.func_188251_a(FixTypes.ENTITY, nbttagcompound.func_74775_l("Entity"), p_188266_3_));
                  }
               }

               p_188266_2_.func_74782_a("SpawnData", p_188266_1_.func_188251_a(FixTypes.ENTITY, p_188266_2_.func_74775_l("SpawnData"), p_188266_3_));
            }

            return p_188266_2_;
         }
      });
      datafixer.func_188258_a(FixTypes.ITEM_INSTANCE, new BlockEntityTag());
      datafixer.func_188258_a(FixTypes.ITEM_INSTANCE, new EntityTag());
      func_188276_a(datafixer);
      return datafixer;
   }

   public static NBTTagCompound func_188277_a(IDataFixer p_188277_0_, NBTTagCompound p_188277_1_, int p_188277_2_, String p_188277_3_) {
      if(p_188277_1_.func_150297_b(p_188277_3_, 10)) {
         p_188277_1_.func_74782_a(p_188277_3_, p_188277_0_.func_188251_a(FixTypes.ITEM_INSTANCE, p_188277_1_.func_74775_l(p_188277_3_), p_188277_2_));
      }

      return p_188277_1_;
   }

   public static NBTTagCompound func_188278_b(IDataFixer p_188278_0_, NBTTagCompound p_188278_1_, int p_188278_2_, String p_188278_3_) {
      if(p_188278_1_.func_150297_b(p_188278_3_, 9)) {
         NBTTagList nbttaglist = p_188278_1_.func_150295_c(p_188278_3_, 10);

         for(int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            nbttaglist.func_150304_a(i, p_188278_0_.func_188251_a(FixTypes.ITEM_INSTANCE, nbttaglist.func_150305_b(i), p_188278_2_));
         }
      }

      return p_188278_1_;
   }
}
