package net.minecraft.network.datasync;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;

public interface DataSerializer<T> {
   void func_187160_a(PacketBuffer var1, T var2);

   T func_187159_a(PacketBuffer var1);

   DataParameter<T> func_187161_a(int var1);
}
