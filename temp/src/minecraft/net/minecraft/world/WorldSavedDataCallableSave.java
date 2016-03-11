package net.minecraft.world;

import net.minecraft.world.WorldSavedData;

public class WorldSavedDataCallableSave implements Runnable {
   private final WorldSavedData field_186338_a;

   public WorldSavedDataCallableSave(WorldSavedData p_i46651_1_) {
      this.field_186338_a = p_i46651_1_;
   }

   public void run() {
      this.field_186338_a.func_76185_a();
   }
}
