package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemMapBase;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;

public class ItemEmptyMap extends ItemMapBase {
   protected ItemEmptyMap() {
      this.func_77637_a(CreativeTabs.field_78026_f);
   }

   public ActionResult<ItemStack> func_77659_a(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_, EnumHand p_77659_4_) {
      ItemStack itemstack = new ItemStack(Items.field_151098_aY, 1, p_77659_2_.func_72841_b("map"));
      String s = "map_" + itemstack.func_77960_j();
      MapData mapdata = new MapData(s);
      p_77659_2_.func_72823_a(s, mapdata);
      mapdata.field_76197_d = 0;
      mapdata.func_176054_a(p_77659_3_.field_70165_t, p_77659_3_.field_70161_v, mapdata.field_76197_d);
      mapdata.field_76200_c = (byte)p_77659_2_.field_73011_w.func_186058_p().func_186068_a();
      mapdata.field_186210_e = true;
      mapdata.func_76185_a();
      --p_77659_1_.field_77994_a;
      if(p_77659_1_.field_77994_a <= 0) {
         return new ActionResult(EnumActionResult.SUCCESS, itemstack);
      } else {
         if(!p_77659_3_.field_71071_by.func_70441_a(itemstack.func_77946_l())) {
            p_77659_3_.func_71019_a(itemstack, false);
         }

         p_77659_3_.func_71029_a(StatList.func_188057_b(this));
         return new ActionResult(EnumActionResult.SUCCESS, p_77659_1_);
      }
   }
}
