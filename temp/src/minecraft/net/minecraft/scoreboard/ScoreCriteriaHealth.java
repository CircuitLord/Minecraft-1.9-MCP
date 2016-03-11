package net.minecraft.scoreboard;

import net.minecraft.scoreboard.IScoreCriteria;
import net.minecraft.scoreboard.ScoreCriteria;

public class ScoreCriteriaHealth extends ScoreCriteria {
   public ScoreCriteriaHealth(String p_i2312_1_) {
      super(p_i2312_1_);
   }

   public boolean func_96637_b() {
      return true;
   }

   public IScoreCriteria.EnumRenderType func_178790_c() {
      return IScoreCriteria.EnumRenderType.HEARTS;
   }
}
