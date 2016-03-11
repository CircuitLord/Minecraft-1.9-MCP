package net.minecraft.tileentity;

import java.util.Random;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.ILootContainer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;

public abstract class TileEntityLockableLoot extends TileEntityLockable implements ILootContainer {
   protected ResourceLocation field_184284_m;
   protected long field_184285_n;

   protected boolean func_184283_b(NBTTagCompound p_184283_1_) {
      if(p_184283_1_.func_150297_b("LootTable", 8)) {
         this.field_184284_m = new ResourceLocation(p_184283_1_.func_74779_i("LootTable"));
         this.field_184285_n = p_184283_1_.func_74763_f("LootTableSeed");
         return true;
      } else {
         return false;
      }
   }

   protected boolean func_184282_c(NBTTagCompound p_184282_1_) {
      if(this.field_184284_m != null) {
         p_184282_1_.func_74778_a("LootTable", this.field_184284_m.toString());
         if(this.field_184285_n != 0L) {
            p_184282_1_.func_74772_a("LootTableSeed", this.field_184285_n);
         }

         return true;
      } else {
         return false;
      }
   }

   protected void func_184281_d(EntityPlayer p_184281_1_) {
      if(this.field_184284_m != null) {
         LootTable loottable = this.field_145850_b.func_184146_ak().func_186521_a(this.field_184284_m);
         this.field_184284_m = null;
         Random random;
         if(this.field_184285_n == 0L) {
            random = new Random();
         } else {
            random = new Random(this.field_184285_n);
         }

         LootContext.Builder lootcontext$builder = new LootContext.Builder((WorldServer)this.field_145850_b);
         if(p_184281_1_ != null) {
            lootcontext$builder.func_186469_a(p_184281_1_.func_184817_da());
         }

         loottable.func_186460_a(this, random, lootcontext$builder.func_186471_a());
      }

   }

   public ResourceLocation func_184276_b() {
      return this.field_184284_m;
   }
}
