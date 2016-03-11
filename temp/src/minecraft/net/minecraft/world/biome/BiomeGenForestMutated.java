package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenForest;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenForestMutated extends BiomeGenForest {
   public BiomeGenForestMutated(BiomeGenBase.BiomeProperties p_i46702_1_) {
      super(BiomeGenForest.Type.BIRCH, p_i46702_1_);
   }

   public WorldGenAbstractTree func_150567_a(Random p_150567_1_) {
      return p_150567_1_.nextBoolean()?BiomeGenForest.field_150629_aC:BiomeGenForest.field_150630_aD;
   }
}
