package net.minecraft.client.renderer.tileentity;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelShield;
import net.minecraft.client.renderer.BannerTextures;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;

public class TileEntityItemStackRenderer {
   public static TileEntityItemStackRenderer field_147719_a = new TileEntityItemStackRenderer();
   private TileEntityChest field_147717_b = new TileEntityChest(BlockChest.Type.BASIC);
   private TileEntityChest field_147718_c = new TileEntityChest(BlockChest.Type.TRAP);
   private TileEntityEnderChest field_147716_d = new TileEntityEnderChest();
   private TileEntityBanner field_179024_e = new TileEntityBanner();
   private TileEntitySkull field_179023_f = new TileEntitySkull();
   private ModelShield field_187318_g = new ModelShield();

   public void func_179022_a(ItemStack p_179022_1_) {
      if(p_179022_1_.func_77973_b() == Items.field_179564_cE) {
         this.field_179024_e.func_175112_a(p_179022_1_);
         TileEntityRendererDispatcher.field_147556_a.func_147549_a(this.field_179024_e, 0.0D, 0.0D, 0.0D, 0.0F);
      } else if(p_179022_1_.func_77973_b() == Items.field_185159_cQ) {
         if(p_179022_1_.func_179543_a("BlockEntityTag", false) != null) {
            this.field_179024_e.func_175112_a(p_179022_1_);
            Minecraft.func_71410_x().func_110434_K().func_110577_a(BannerTextures.field_187485_b.func_187478_a(this.field_179024_e.func_175116_e(), this.field_179024_e.func_175114_c(), this.field_179024_e.func_175110_d()));
         } else {
            Minecraft.func_71410_x().func_110434_K().func_110577_a(BannerTextures.field_187486_c);
         }

         GlStateManager.func_179094_E();
         GlStateManager.func_179152_a(1.0F, -1.0F, -1.0F);
         this.field_187318_g.func_187062_a();
         GlStateManager.func_179121_F();
      } else if(p_179022_1_.func_77973_b() == Items.field_151144_bL) {
         GameProfile gameprofile = null;
         if(p_179022_1_.func_77942_o()) {
            NBTTagCompound nbttagcompound = p_179022_1_.func_77978_p();
            if(nbttagcompound.func_150297_b("SkullOwner", 10)) {
               gameprofile = NBTUtil.func_152459_a(nbttagcompound.func_74775_l("SkullOwner"));
            } else if(nbttagcompound.func_150297_b("SkullOwner", 8) && !nbttagcompound.func_74779_i("SkullOwner").isEmpty()) {
               GameProfile lvt_2_2_ = new GameProfile((UUID)null, nbttagcompound.func_74779_i("SkullOwner"));
               gameprofile = TileEntitySkull.func_174884_b(lvt_2_2_);
               nbttagcompound.func_82580_o("SkullOwner");
               nbttagcompound.func_74782_a("SkullOwner", NBTUtil.func_180708_a(new NBTTagCompound(), gameprofile));
            }
         }

         if(TileEntitySkullRenderer.field_147536_b != null) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179129_p();
            TileEntitySkullRenderer.field_147536_b.func_188190_a(0.0F, 0.0F, 0.0F, EnumFacing.UP, 0.0F, p_179022_1_.func_77960_j(), gameprofile, -1, 0.0F);
            GlStateManager.func_179089_o();
            GlStateManager.func_179121_F();
         }
      } else {
         Block block = Block.func_149634_a(p_179022_1_.func_77973_b());
         if(block == Blocks.field_150477_bB) {
            TileEntityRendererDispatcher.field_147556_a.func_147549_a(this.field_147716_d, 0.0D, 0.0D, 0.0D, 0.0F);
         } else if(block == Blocks.field_150447_bR) {
            TileEntityRendererDispatcher.field_147556_a.func_147549_a(this.field_147718_c, 0.0D, 0.0D, 0.0D, 0.0F);
         } else {
            TileEntityRendererDispatcher.field_147556_a.func_147549_a(this.field_147717_b, 0.0D, 0.0D, 0.0D, 0.0F);
         }
      }

   }
}
