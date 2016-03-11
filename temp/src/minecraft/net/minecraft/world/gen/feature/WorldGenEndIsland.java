package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenEndIsland extends WorldGenerator {
   public boolean func_180709_b(World p_180709_1_, Random p_180709_2_, BlockPos p_180709_3_) {
      int i = p_180709_2_.nextInt(3) + 4;
      float f = (float)i;

      for(int j = 0; f > 0.5F; --j) {
         for(int k = MathHelper.func_76141_d(-f); k <= MathHelper.func_76123_f(f); ++k) {
            for(int l = MathHelper.func_76141_d(-f); l <= MathHelper.func_76123_f(f); ++l) {
               if((float)(k * k + l * l) <= (f + 1.0F) * (f + 1.0F)) {
                  this.func_175903_a(p_180709_1_, p_180709_3_.func_177982_a(k, j, l), Blocks.field_150377_bs.func_176223_P());
               }
            }
         }

         f = (float)((double)f - ((double)p_180709_2_.nextInt(2) + 0.5D));
      }

      return true;
   }
}
