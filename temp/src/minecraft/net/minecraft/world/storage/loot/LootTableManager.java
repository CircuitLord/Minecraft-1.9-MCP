package net.minecraft.world.storage.loot;

import com.google.common.base.Charsets;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.LootConditionManager;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LootTableManager {
   private static final Logger field_186525_a = LogManager.getLogger();
   private static final Gson field_186526_b = (new GsonBuilder()).registerTypeAdapter(RandomValueRange.class, new RandomValueRange.Serializer()).registerTypeAdapter(LootPool.class, new LootPool.Serializer()).registerTypeAdapter(LootTable.class, new LootTable.Serializer()).registerTypeHierarchyAdapter(LootEntry.class, new LootEntry.Serializer()).registerTypeHierarchyAdapter(LootFunction.class, new LootFunctionManager.Serializer()).registerTypeHierarchyAdapter(LootCondition.class, new LootConditionManager.Serializer()).registerTypeHierarchyAdapter(LootContext.EntityTarget.class, new LootContext.EntityTarget.Serializer()).create();
   private final LoadingCache<ResourceLocation, LootTable> field_186527_c = CacheBuilder.newBuilder().<ResourceLocation, LootTable>build(new LootTableManager.Loader());
   private final File field_186528_d;

   public LootTableManager(File p_i46632_1_) {
      this.field_186528_d = p_i46632_1_;
      this.func_186522_a();
   }

   public LootTable func_186521_a(ResourceLocation p_186521_1_) {
      return (LootTable)this.field_186527_c.getUnchecked(p_186521_1_);
   }

   public void func_186522_a() {
      this.field_186527_c.invalidateAll();

      for(ResourceLocation resourcelocation : LootTableList.func_186374_a()) {
         this.func_186521_a(resourcelocation);
      }

   }

   class Loader extends CacheLoader<ResourceLocation, LootTable> {
      private Loader() {
      }

      public LootTable load(ResourceLocation p_load_1_) throws Exception {
         if(p_load_1_.func_110623_a().contains(".")) {
            LootTableManager.field_186525_a.debug("Invalid loot table name \'" + p_load_1_ + "\' (can\'t contain periods)");
            return LootTable.field_186464_a;
         } else {
            LootTable loottable = this.func_186517_b(p_load_1_);
            if(loottable == null) {
               loottable = this.func_186518_c(p_load_1_);
            }

            if(loottable == null) {
               loottable = LootTable.field_186464_a;
               LootTableManager.field_186525_a.warn("Couldn\'t find resource table {}", new Object[]{p_load_1_});
            }

            return loottable;
         }
      }

      private LootTable func_186517_b(ResourceLocation p_186517_1_) {
         File file1 = new File(new File(LootTableManager.this.field_186528_d, p_186517_1_.func_110624_b()), p_186517_1_.func_110623_a() + ".json");
         if(file1.exists()) {
            if(file1.isFile()) {
               String s;
               try {
                  s = Files.toString(file1, Charsets.UTF_8);
               } catch (IOException ioexception) {
                  LootTableManager.field_186525_a.warn((String)("Couldn\'t load loot table " + p_186517_1_ + " from " + file1), (Throwable)ioexception);
                  return LootTable.field_186464_a;
               }

               try {
                  return (LootTable)LootTableManager.field_186526_b.fromJson(s, LootTable.class);
               } catch (JsonParseException jsonparseexception) {
                  LootTableManager.field_186525_a.error((String)("Couldn\'t load loot table " + p_186517_1_ + " from " + file1), (Throwable)jsonparseexception);
                  return LootTable.field_186464_a;
               }
            } else {
               LootTableManager.field_186525_a.warn("Expected to find loot table " + p_186517_1_ + " at " + file1 + " but it was a folder.");
               return LootTable.field_186464_a;
            }
         } else {
            return null;
         }
      }

      private LootTable func_186518_c(ResourceLocation p_186518_1_) {
         URL url = LootTableManager.class.getResource("/assets/" + p_186518_1_.func_110624_b() + "/loot_tables/" + p_186518_1_.func_110623_a() + ".json");
         if(url != null) {
            String s;
            try {
               s = Resources.toString(url, Charsets.UTF_8);
            } catch (IOException ioexception) {
               LootTableManager.field_186525_a.warn((String)("Couldn\'t load loot table " + p_186518_1_ + " from " + url), (Throwable)ioexception);
               return LootTable.field_186464_a;
            }

            try {
               return (LootTable)LootTableManager.field_186526_b.fromJson(s, LootTable.class);
            } catch (JsonParseException jsonparseexception) {
               LootTableManager.field_186525_a.error((String)("Couldn\'t load loot table " + p_186518_1_ + " from " + url), (Throwable)jsonparseexception);
               return LootTable.field_186464_a;
            }
         } else {
            return null;
         }
      }
   }
}
