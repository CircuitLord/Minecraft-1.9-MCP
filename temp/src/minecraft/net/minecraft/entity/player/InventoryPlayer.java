package net.minecraft.entity.player;

import java.util.Arrays;
import java.util.concurrent.Callable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ReportedException;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class InventoryPlayer implements IInventory {
   public final ItemStack[] field_70462_a = new ItemStack[36];
   public final ItemStack[] field_70460_b = new ItemStack[4];
   public final ItemStack[] field_184439_c = new ItemStack[1];
   private final ItemStack[][] field_184440_g;
   public int field_70461_c;
   public EntityPlayer field_70458_d;
   private ItemStack field_70457_g;
   public boolean field_70459_e;

   public InventoryPlayer(EntityPlayer p_i1750_1_) {
      this.field_184440_g = new ItemStack[][]{this.field_70462_a, this.field_70460_b, this.field_184439_c};
      this.field_70458_d = p_i1750_1_;
   }

   public ItemStack func_70448_g() {
      return func_184435_e(this.field_70461_c)?this.field_70462_a[this.field_70461_c]:null;
   }

   public static int func_70451_h() {
      return 9;
   }

   private boolean func_184436_a(ItemStack p_184436_1_, ItemStack p_184436_2_) {
      return p_184436_1_ != null && this.func_184431_b(p_184436_1_, p_184436_2_) && p_184436_1_.func_77985_e() && p_184436_1_.field_77994_a < p_184436_1_.func_77976_d() && p_184436_1_.field_77994_a < this.func_70297_j_();
   }

   private boolean func_184431_b(ItemStack p_184431_1_, ItemStack p_184431_2_) {
      return p_184431_1_.func_77973_b() == p_184431_2_.func_77973_b() && (!p_184431_1_.func_77981_g() || p_184431_1_.func_77960_j() == p_184431_2_.func_77960_j()) && ItemStack.func_77970_a(p_184431_1_, p_184431_2_);
   }

   public int func_70447_i() {
      for(int i = 0; i < this.field_70462_a.length; ++i) {
         if(this.field_70462_a[i] == null) {
            return i;
         }
      }

      return -1;
   }

   public void func_184434_a(ItemStack p_184434_1_) {
      int i = this.func_184429_b(p_184434_1_);
      if(func_184435_e(i)) {
         this.field_70461_c = i;
      } else {
         if(i == -1) {
            this.field_70461_c = this.func_184433_k();
            if(this.field_70462_a[this.field_70461_c] != null) {
               int j = this.func_70447_i();
               if(j != -1) {
                  this.field_70462_a[j] = this.field_70462_a[this.field_70461_c];
               }
            }

            this.field_70462_a[this.field_70461_c] = p_184434_1_;
         } else {
            this.func_184430_d(i);
         }

      }
   }

   public void func_184430_d(int p_184430_1_) {
      this.field_70461_c = this.func_184433_k();
      ItemStack itemstack = this.field_70462_a[this.field_70461_c];
      this.field_70462_a[this.field_70461_c] = this.field_70462_a[p_184430_1_];
      this.field_70462_a[p_184430_1_] = itemstack;
   }

   public static boolean func_184435_e(int p_184435_0_) {
      return p_184435_0_ >= 0 && p_184435_0_ < 9;
   }

   public int func_184429_b(ItemStack p_184429_1_) {
      for(int i = 0; i < this.field_70462_a.length; ++i) {
         if(this.field_70462_a[i] != null && this.func_184431_b(p_184429_1_, this.field_70462_a[i])) {
            return i;
         }
      }

      return -1;
   }

   public int func_184433_k() {
      for(int i = 0; i < 9; ++i) {
         int j = (this.field_70461_c + i) % 9;
         if(this.field_70462_a[j] == null) {
            return j;
         }
      }

      for(int k = 0; k < 9; ++k) {
         int l = (this.field_70461_c + k) % 9;
         if(!this.field_70462_a[l].func_77948_v()) {
            return l;
         }
      }

      return this.field_70461_c;
   }

   public void func_70453_c(int p_70453_1_) {
      if(p_70453_1_ > 0) {
         p_70453_1_ = 1;
      }

      if(p_70453_1_ < 0) {
         p_70453_1_ = -1;
      }

      for(this.field_70461_c -= p_70453_1_; this.field_70461_c < 0; this.field_70461_c += 9) {
         ;
      }

      while(this.field_70461_c >= 9) {
         this.field_70461_c -= 9;
      }

   }

   public int func_174925_a(Item p_174925_1_, int p_174925_2_, int p_174925_3_, NBTTagCompound p_174925_4_) {
      int i = 0;

      for(int j = 0; j < this.func_70302_i_(); ++j) {
         ItemStack itemstack = this.func_70301_a(j);
         if(itemstack != null && (p_174925_1_ == null || itemstack.func_77973_b() == p_174925_1_) && (p_174925_2_ <= -1 || itemstack.func_77960_j() == p_174925_2_) && (p_174925_4_ == null || NBTUtil.func_181123_a(p_174925_4_, itemstack.func_77978_p(), true))) {
            int k = p_174925_3_ <= 0?itemstack.field_77994_a:Math.min(p_174925_3_ - i, itemstack.field_77994_a);
            i += k;
            if(p_174925_3_ != 0) {
               itemstack.field_77994_a -= k;
               if(itemstack.field_77994_a == 0) {
                  this.func_70299_a(j, (ItemStack)null);
               }

               if(p_174925_3_ > 0 && i >= p_174925_3_) {
                  return i;
               }
            }
         }
      }

      if(this.field_70457_g != null) {
         if(p_174925_1_ != null && this.field_70457_g.func_77973_b() != p_174925_1_) {
            return i;
         }

         if(p_174925_2_ > -1 && this.field_70457_g.func_77960_j() != p_174925_2_) {
            return i;
         }

         if(p_174925_4_ != null && !NBTUtil.func_181123_a(p_174925_4_, this.field_70457_g.func_77978_p(), true)) {
            return i;
         }

         int l = p_174925_3_ <= 0?this.field_70457_g.field_77994_a:Math.min(p_174925_3_ - i, this.field_70457_g.field_77994_a);
         i += l;
         if(p_174925_3_ != 0) {
            this.field_70457_g.field_77994_a -= l;
            if(this.field_70457_g.field_77994_a == 0) {
               this.field_70457_g = null;
            }

            if(p_174925_3_ > 0 && i >= p_174925_3_) {
               return i;
            }
         }
      }

      return i;
   }

   private int func_70452_e(ItemStack p_70452_1_) {
      Item item = p_70452_1_.func_77973_b();
      int i = p_70452_1_.field_77994_a;
      int j = this.func_70432_d(p_70452_1_);
      if(j == -1) {
         j = this.func_70447_i();
      }

      if(j == -1) {
         return i;
      } else {
         ItemStack itemstack = this.func_70301_a(j);
         if(itemstack == null) {
            itemstack = new ItemStack(item, 0, p_70452_1_.func_77960_j());
            if(p_70452_1_.func_77942_o()) {
               itemstack.func_77982_d((NBTTagCompound)p_70452_1_.func_77978_p().func_74737_b());
            }

            this.func_70299_a(j, itemstack);
         }

         int k = i;
         if(i > itemstack.func_77976_d() - itemstack.field_77994_a) {
            k = itemstack.func_77976_d() - itemstack.field_77994_a;
         }

         if(k > this.func_70297_j_() - itemstack.field_77994_a) {
            k = this.func_70297_j_() - itemstack.field_77994_a;
         }

         if(k == 0) {
            return i;
         } else {
            i = i - k;
            itemstack.field_77994_a += k;
            itemstack.field_77992_b = 5;
            return i;
         }
      }
   }

   private int func_70432_d(ItemStack p_70432_1_) {
      if(this.func_184436_a(this.func_70301_a(this.field_70461_c), p_70432_1_)) {
         return this.field_70461_c;
      } else if(this.func_184436_a(this.func_70301_a(40), p_70432_1_)) {
         return 40;
      } else {
         for(int i = 0; i < this.field_70462_a.length; ++i) {
            if(this.func_184436_a(this.field_70462_a[i], p_70432_1_)) {
               return i;
            }
         }

         return -1;
      }
   }

   public void func_70429_k() {
      for(int i = 0; i < this.field_184440_g.length; ++i) {
         ItemStack[] aitemstack = this.field_184440_g[i];

         for(int j = 0; j < aitemstack.length; ++j) {
            if(aitemstack[j] != null) {
               aitemstack[j].func_77945_a(this.field_70458_d.field_70170_p, this.field_70458_d, j, this.field_70461_c == j);
            }
         }
      }

   }

   public boolean func_70441_a(final ItemStack p_70441_1_) {
      if(p_70441_1_ != null && p_70441_1_.field_77994_a != 0 && p_70441_1_.func_77973_b() != null) {
         try {
            if(p_70441_1_.func_77951_h()) {
               int j = this.func_70447_i();
               if(j >= 0) {
                  this.field_70462_a[j] = ItemStack.func_77944_b(p_70441_1_);
                  this.field_70462_a[j].field_77992_b = 5;
                  p_70441_1_.field_77994_a = 0;
                  return true;
               } else if(this.field_70458_d.field_71075_bZ.field_75098_d) {
                  p_70441_1_.field_77994_a = 0;
                  return true;
               } else {
                  return false;
               }
            } else {
               int i;
               while(true) {
                  i = p_70441_1_.field_77994_a;
                  p_70441_1_.field_77994_a = this.func_70452_e(p_70441_1_);
                  if(p_70441_1_.field_77994_a <= 0 || p_70441_1_.field_77994_a >= i) {
                     break;
                  }
               }

               if(p_70441_1_.field_77994_a == i && this.field_70458_d.field_71075_bZ.field_75098_d) {
                  p_70441_1_.field_77994_a = 0;
                  return true;
               } else {
                  return p_70441_1_.field_77994_a < i;
               }
            }
         } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.func_85055_a(throwable, "Adding item to inventory");
            CrashReportCategory crashreportcategory = crashreport.func_85058_a("Item being added");
            crashreportcategory.func_71507_a("Item ID", Integer.valueOf(Item.func_150891_b(p_70441_1_.func_77973_b())));
            crashreportcategory.func_71507_a("Item data", Integer.valueOf(p_70441_1_.func_77960_j()));
            crashreportcategory.func_71500_a("Item name", new Callable<String>() {
               public String call() throws Exception {
                  return p_70441_1_.func_82833_r();
               }
            });
            throw new ReportedException(crashreport);
         }
      } else {
         return false;
      }
   }

   public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
      ItemStack[] aitemstack = null;

      for(ItemStack[] aitemstack1 : this.field_184440_g) {
         if(p_70298_1_ < aitemstack1.length) {
            aitemstack = aitemstack1;
            break;
         }

         p_70298_1_ -= aitemstack1.length;
      }

      return aitemstack != null && aitemstack[p_70298_1_] != null?ItemStackHelper.func_188382_a(aitemstack, p_70298_1_, p_70298_2_):null;
   }

   public void func_184437_d(ItemStack p_184437_1_) {
      for(ItemStack[] aitemstack : this.field_184440_g) {
         for(int i = 0; i < aitemstack.length; ++i) {
            if(aitemstack[i] == p_184437_1_) {
               aitemstack[i] = null;
               break;
            }
         }
      }

   }

   public ItemStack func_70304_b(int p_70304_1_) {
      ItemStack[] aitemstack = null;

      for(ItemStack[] aitemstack1 : this.field_184440_g) {
         if(p_70304_1_ < aitemstack1.length) {
            aitemstack = aitemstack1;
            break;
         }

         p_70304_1_ -= aitemstack1.length;
      }

      if(aitemstack != null && aitemstack[p_70304_1_] != null) {
         ItemStack itemstack = aitemstack[p_70304_1_];
         aitemstack[p_70304_1_] = null;
         return itemstack;
      } else {
         return null;
      }
   }

   public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
      ItemStack[] aitemstack = null;

      for(ItemStack[] aitemstack1 : this.field_184440_g) {
         if(p_70299_1_ < aitemstack1.length) {
            aitemstack = aitemstack1;
            break;
         }

         p_70299_1_ -= aitemstack1.length;
      }

      if(aitemstack != null) {
         aitemstack[p_70299_1_] = p_70299_2_;
      }

   }

   public float func_184438_a(IBlockState p_184438_1_) {
      float f = 1.0F;
      if(this.field_70462_a[this.field_70461_c] != null) {
         f *= this.field_70462_a[this.field_70461_c].func_150997_a(p_184438_1_);
      }

      return f;
   }

   public NBTTagList func_70442_a(NBTTagList p_70442_1_) {
      for(int i = 0; i < this.field_70462_a.length; ++i) {
         if(this.field_70462_a[i] != null) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            nbttagcompound.func_74774_a("Slot", (byte)i);
            this.field_70462_a[i].func_77955_b(nbttagcompound);
            p_70442_1_.func_74742_a(nbttagcompound);
         }
      }

      for(int j = 0; j < this.field_70460_b.length; ++j) {
         if(this.field_70460_b[j] != null) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.func_74774_a("Slot", (byte)(j + 100));
            this.field_70460_b[j].func_77955_b(nbttagcompound1);
            p_70442_1_.func_74742_a(nbttagcompound1);
         }
      }

      for(int k = 0; k < this.field_184439_c.length; ++k) {
         if(this.field_184439_c[k] != null) {
            NBTTagCompound nbttagcompound2 = new NBTTagCompound();
            nbttagcompound2.func_74774_a("Slot", (byte)(k + 150));
            this.field_184439_c[k].func_77955_b(nbttagcompound2);
            p_70442_1_.func_74742_a(nbttagcompound2);
         }
      }

      return p_70442_1_;
   }

   public void func_70443_b(NBTTagList p_70443_1_) {
      Arrays.fill(this.field_70462_a, (Object)null);
      Arrays.fill(this.field_70460_b, (Object)null);
      Arrays.fill(this.field_184439_c, (Object)null);

      for(int i = 0; i < p_70443_1_.func_74745_c(); ++i) {
         NBTTagCompound nbttagcompound = p_70443_1_.func_150305_b(i);
         int j = nbttagcompound.func_74771_c("Slot") & 255;
         ItemStack itemstack = ItemStack.func_77949_a(nbttagcompound);
         if(itemstack != null) {
            if(j >= 0 && j < this.field_70462_a.length) {
               this.field_70462_a[j] = itemstack;
            } else if(j >= 100 && j < this.field_70460_b.length + 100) {
               this.field_70460_b[j - 100] = itemstack;
            } else if(j >= 150 && j < this.field_184439_c.length + 150) {
               this.field_184439_c[j - 150] = itemstack;
            }
         }
      }

   }

   public int func_70302_i_() {
      return this.field_70462_a.length + this.field_70460_b.length + this.field_184439_c.length;
   }

   public ItemStack func_70301_a(int p_70301_1_) {
      ItemStack[] aitemstack = null;

      for(ItemStack[] aitemstack1 : this.field_184440_g) {
         if(p_70301_1_ < aitemstack1.length) {
            aitemstack = aitemstack1;
            break;
         }

         p_70301_1_ -= aitemstack1.length;
      }

      return aitemstack == null?null:aitemstack[p_70301_1_];
   }

   public String func_70005_c_() {
      return "container.inventory";
   }

   public boolean func_145818_k_() {
      return false;
   }

   public ITextComponent func_145748_c_() {
      return (ITextComponent)(this.func_145818_k_()?new TextComponentString(this.func_70005_c_()):new TextComponentTranslation(this.func_70005_c_(), new Object[0]));
   }

   public int func_70297_j_() {
      return 64;
   }

   public boolean func_184432_b(IBlockState p_184432_1_) {
      if(p_184432_1_.func_185904_a().func_76229_l()) {
         return true;
      } else {
         ItemStack itemstack = this.func_70301_a(this.field_70461_c);
         return itemstack != null?itemstack.func_150998_b(p_184432_1_):false;
      }
   }

   public ItemStack func_70440_f(int p_70440_1_) {
      return this.field_70460_b[p_70440_1_];
   }

   public void func_70449_g(float p_70449_1_) {
      p_70449_1_ = p_70449_1_ / 4.0F;
      if(p_70449_1_ < 1.0F) {
         p_70449_1_ = 1.0F;
      }

      for(int i = 0; i < this.field_70460_b.length; ++i) {
         if(this.field_70460_b[i] != null && this.field_70460_b[i].func_77973_b() instanceof ItemArmor) {
            this.field_70460_b[i].func_77972_a((int)p_70449_1_, this.field_70458_d);
            if(this.field_70460_b[i].field_77994_a == 0) {
               this.field_70460_b[i] = null;
            }
         }
      }

   }

   public void func_70436_m() {
      for(ItemStack[] aitemstack : this.field_184440_g) {
         for(int i = 0; i < aitemstack.length; ++i) {
            if(aitemstack[i] != null) {
               this.field_70458_d.func_146097_a(aitemstack[i], true, false);
               aitemstack[i] = null;
            }
         }
      }

   }

   public void func_70296_d() {
      this.field_70459_e = true;
   }

   public void func_70437_b(ItemStack p_70437_1_) {
      this.field_70457_g = p_70437_1_;
   }

   public ItemStack func_70445_o() {
      return this.field_70457_g;
   }

   public boolean func_70300_a(EntityPlayer p_70300_1_) {
      return this.field_70458_d.field_70128_L?false:p_70300_1_.func_70068_e(this.field_70458_d) <= 64.0D;
   }

   public boolean func_70431_c(ItemStack p_70431_1_) {
      for(ItemStack[] aitemstack : this.field_184440_g) {
         for(int i = 0; i < aitemstack.length; ++i) {
            if(aitemstack[i] != null && aitemstack[i].func_77969_a(p_70431_1_)) {
               return true;
            }
         }
      }

      return false;
   }

   public void func_174889_b(EntityPlayer p_174889_1_) {
   }

   public void func_174886_c(EntityPlayer p_174886_1_) {
   }

   public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
      return true;
   }

   public void func_70455_b(InventoryPlayer p_70455_1_) {
      for(int i = 0; i < this.func_70302_i_(); ++i) {
         this.func_70299_a(i, p_70455_1_.func_70301_a(i));
      }

      this.field_70461_c = p_70455_1_.field_70461_c;
   }

   public int func_174887_a_(int p_174887_1_) {
      return 0;
   }

   public void func_174885_b(int p_174885_1_, int p_174885_2_) {
   }

   public int func_174890_g() {
      return 0;
   }

   public void func_174888_l() {
      for(ItemStack[] aitemstack : this.field_184440_g) {
         for(int i = 0; i < aitemstack.length; ++i) {
            aitemstack[i] = null;
         }
      }

   }
}
