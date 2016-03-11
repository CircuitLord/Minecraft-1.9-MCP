package net.minecraft.tileentity;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandResultStats;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.CommandBlockBaseLogic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TileEntityCommandBlock extends TileEntity {
   private boolean field_184259_a;
   private boolean field_184260_f;
   private boolean field_184261_g;
   private boolean field_184262_h;
   private final CommandBlockBaseLogic field_145994_a = new CommandBlockBaseLogic() {
      public BlockPos func_180425_c() {
         return TileEntityCommandBlock.this.field_174879_c;
      }

      public Vec3d func_174791_d() {
         return new Vec3d((double)TileEntityCommandBlock.this.field_174879_c.func_177958_n() + 0.5D, (double)TileEntityCommandBlock.this.field_174879_c.func_177956_o() + 0.5D, (double)TileEntityCommandBlock.this.field_174879_c.func_177952_p() + 0.5D);
      }

      public World func_130014_f_() {
         return TileEntityCommandBlock.this.func_145831_w();
      }

      public void func_145752_a(String p_145752_1_) {
         super.func_145752_a(p_145752_1_);
         TileEntityCommandBlock.this.func_70296_d();
      }

      public void func_145756_e() {
         IBlockState iblockstate = TileEntityCommandBlock.this.field_145850_b.func_180495_p(TileEntityCommandBlock.this.field_174879_c);
         TileEntityCommandBlock.this.func_145831_w().func_184138_a(TileEntityCommandBlock.this.field_174879_c, iblockstate, iblockstate, 3);
      }

      public int func_145751_f() {
         return 0;
      }

      public void func_145757_a(ByteBuf p_145757_1_) {
         p_145757_1_.writeInt(TileEntityCommandBlock.this.field_174879_c.func_177958_n());
         p_145757_1_.writeInt(TileEntityCommandBlock.this.field_174879_c.func_177956_o());
         p_145757_1_.writeInt(TileEntityCommandBlock.this.field_174879_c.func_177952_p());
      }

      public Entity func_174793_f() {
         return null;
      }

      public MinecraftServer func_184102_h() {
         return TileEntityCommandBlock.this.field_145850_b.func_73046_m();
      }
   };

   public void func_145841_b(NBTTagCompound p_145841_1_) {
      super.func_145841_b(p_145841_1_);
      this.field_145994_a.func_145758_a(p_145841_1_);
      p_145841_1_.func_74757_a("powered", this.func_184255_d());
      p_145841_1_.func_74757_a("conditionMet", this.func_184256_g());
      p_145841_1_.func_74757_a("auto", this.func_184254_e());
   }

   public void func_145839_a(NBTTagCompound p_145839_1_) {
      super.func_145839_a(p_145839_1_);
      this.field_145994_a.func_145759_b(p_145839_1_);
      this.func_184250_a(p_145839_1_.func_74767_n("powered"));
      this.func_184249_c(p_145839_1_.func_74767_n("conditionMet"));
      this.func_184253_b(p_145839_1_.func_74767_n("auto"));
   }

   public Packet<?> func_145844_m() {
      if(this.func_184257_h()) {
         this.func_184252_d(false);
         NBTTagCompound nbttagcompound = new NBTTagCompound();
         this.func_145841_b(nbttagcompound);
         return new SPacketUpdateTileEntity(this.field_174879_c, 2, nbttagcompound);
      } else {
         return null;
      }
   }

   public boolean func_183000_F() {
      return true;
   }

   public CommandBlockBaseLogic func_145993_a() {
      return this.field_145994_a;
   }

   public CommandResultStats func_175124_c() {
      return this.field_145994_a.func_175572_n();
   }

   public void func_184250_a(boolean p_184250_1_) {
      this.field_184259_a = p_184250_1_;
   }

   public boolean func_184255_d() {
      return this.field_184259_a;
   }

   public boolean func_184254_e() {
      return this.field_184260_f;
   }

   public void func_184253_b(boolean p_184253_1_) {
      boolean flag = this.field_184260_f;
      this.field_184260_f = p_184253_1_;
      if(!flag && p_184253_1_ && !this.field_184259_a && this.field_145850_b != null && this.func_184251_i() != TileEntityCommandBlock.Mode.SEQUENCE) {
         Block block = this.func_145838_q();
         if(block instanceof BlockCommandBlock) {
            BlockPos blockpos = this.func_174877_v();
            BlockCommandBlock blockcommandblock = (BlockCommandBlock)block;
            this.field_184261_g = !this.func_184258_j() || blockcommandblock.func_185562_e(this.field_145850_b, blockpos, this.field_145850_b.func_180495_p(blockpos));
            this.field_145850_b.func_175684_a(blockpos, block, block.func_149738_a(this.field_145850_b));
            if(this.field_184261_g) {
               blockcommandblock.func_185563_c(this.field_145850_b, blockpos);
            }
         }
      }

   }

   public boolean func_184256_g() {
      return this.field_184261_g;
   }

   public void func_184249_c(boolean p_184249_1_) {
      this.field_184261_g = p_184249_1_;
   }

   public boolean func_184257_h() {
      return this.field_184262_h;
   }

   public void func_184252_d(boolean p_184252_1_) {
      this.field_184262_h = p_184252_1_;
   }

   public TileEntityCommandBlock.Mode func_184251_i() {
      Block block = this.func_145838_q();
      return block == Blocks.field_150483_bI?TileEntityCommandBlock.Mode.REDSTONE:(block == Blocks.field_185776_dc?TileEntityCommandBlock.Mode.AUTO:(block == Blocks.field_185777_dd?TileEntityCommandBlock.Mode.SEQUENCE:TileEntityCommandBlock.Mode.REDSTONE));
   }

   public boolean func_184258_j() {
      IBlockState iblockstate = this.field_145850_b.func_180495_p(this.func_174877_v());
      return iblockstate.func_177230_c() instanceof BlockCommandBlock?((Boolean)iblockstate.func_177229_b(BlockCommandBlock.field_185565_b)).booleanValue():false;
   }

   public void func_145829_t() {
      this.field_145854_h = null;
      super.func_145829_t();
   }

   public static enum Mode {
      SEQUENCE,
      AUTO,
      REDSTONE;
   }
}
