package net.minecraft.world.chunk;

import java.util.List;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

public interface IChunkGenerator {
   Chunk func_185932_a(int var1, int var2);

   void func_185931_b(int var1, int var2);

   boolean func_185933_a(Chunk var1, int var2, int var3);

   List<BiomeGenBase.SpawnListEntry> func_177458_a(EnumCreatureType var1, BlockPos var2);

   BlockPos func_180513_a(World var1, String var2, BlockPos var3);

   void func_180514_a(Chunk var1, int var2, int var3);
}
