package net.minecraft.network.play.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class SPacketChunkData implements Packet<INetHandlerPlayClient> {
   private int field_149284_a;
   private int field_149282_b;
   private int field_186948_c;
   private byte[] field_186949_d;
   private boolean field_149279_g;

   public SPacketChunkData() {
   }

   public SPacketChunkData(Chunk p_i46941_1_, boolean p_i46941_2_, int p_i46941_3_) {
      this.field_149284_a = p_i46941_1_.field_76635_g;
      this.field_149282_b = p_i46941_1_.field_76647_h;
      this.field_149279_g = p_i46941_2_;
      boolean flag = !p_i46941_1_.func_177412_p().field_73011_w.func_177495_o();
      this.field_186949_d = new byte[func_186944_a(p_i46941_1_, p_i46941_2_, flag, p_i46941_3_)];
      this.field_186948_c = func_186947_a(new PacketBuffer(this.func_186945_f()), p_i46941_1_, p_i46941_2_, flag, p_i46941_3_);
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_149284_a = p_148837_1_.readInt();
      this.field_149282_b = p_148837_1_.readInt();
      this.field_149279_g = p_148837_1_.readBoolean();
      this.field_186948_c = p_148837_1_.func_150792_a();
      int i = p_148837_1_.func_150792_a();
      if(i > 2097152) {
         throw new RuntimeException("Chunk Packet trying to allocate too much memory on read.");
      } else {
         this.field_186949_d = new byte[i];
         p_148837_1_.readBytes(this.field_186949_d);
      }
   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.writeInt(this.field_149284_a);
      p_148840_1_.writeInt(this.field_149282_b);
      p_148840_1_.writeBoolean(this.field_149279_g);
      p_148840_1_.func_150787_b(this.field_186948_c);
      p_148840_1_.func_150787_b(this.field_186949_d.length);
      p_148840_1_.writeBytes(this.field_186949_d);
   }

   public void func_148833_a(INetHandlerPlayClient p_148833_1_) {
      p_148833_1_.func_147263_a(this);
   }

   public PacketBuffer func_186946_a() {
      return new PacketBuffer(Unpooled.wrappedBuffer(this.field_186949_d));
   }

   private ByteBuf func_186945_f() {
      ByteBuf bytebuf = Unpooled.wrappedBuffer(this.field_186949_d);
      bytebuf.writerIndex(0);
      return bytebuf;
   }

   public static int func_186947_a(PacketBuffer p_186947_0_, Chunk p_186947_1_, boolean p_186947_2_, boolean p_186947_3_, int p_186947_4_) {
      int i = 0;
      ExtendedBlockStorage[] aextendedblockstorage = p_186947_1_.func_76587_i();
      int j = 0;

      for(int k = aextendedblockstorage.length; j < k; ++j) {
         ExtendedBlockStorage extendedblockstorage = aextendedblockstorage[j];
         if(extendedblockstorage != Chunk.field_186036_a && (!p_186947_2_ || !extendedblockstorage.func_76663_a()) && (p_186947_4_ & 1 << j) != 0) {
            i |= 1 << j;
            extendedblockstorage.func_186049_g().func_186009_b(p_186947_0_);
            p_186947_0_.writeBytes(extendedblockstorage.func_76661_k().func_177481_a());
            if(p_186947_3_) {
               p_186947_0_.writeBytes(extendedblockstorage.func_76671_l().func_177481_a());
            }
         }
      }

      if(p_186947_2_) {
         p_186947_0_.writeBytes(p_186947_1_.func_76605_m());
      }

      return i;
   }

   protected static int func_186944_a(Chunk p_186944_0_, boolean p_186944_1_, boolean p_186944_2_, int p_186944_3_) {
      int i = 0;
      ExtendedBlockStorage[] aextendedblockstorage = p_186944_0_.func_76587_i();
      int j = 0;

      for(int k = aextendedblockstorage.length; j < k; ++j) {
         ExtendedBlockStorage extendedblockstorage = aextendedblockstorage[j];
         if(extendedblockstorage != Chunk.field_186036_a && (!p_186944_1_ || !extendedblockstorage.func_76663_a()) && (p_186944_3_ & 1 << j) != 0) {
            i = i + extendedblockstorage.func_186049_g().func_186018_a();
            i = i + extendedblockstorage.func_76661_k().func_177481_a().length;
            if(p_186944_2_) {
               i += extendedblockstorage.func_76671_l().func_177481_a().length;
            }
         }
      }

      if(p_186944_1_) {
         i += p_186944_0_.func_76605_m().length;
      }

      return i;
   }

   public int func_149273_e() {
      return this.field_149284_a;
   }

   public int func_149271_f() {
      return this.field_149282_b;
   }

   public int func_149276_g() {
      return this.field_186948_c;
   }

   public boolean func_149274_i() {
      return this.field_149279_g;
   }
}
