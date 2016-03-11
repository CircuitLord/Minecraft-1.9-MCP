package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class CPacketResourcePackStatus implements Packet<INetHandlerPlayServer> {
   private String field_179720_a;
   private CPacketResourcePackStatus.Action field_179719_b;

   public CPacketResourcePackStatus() {
   }

   public CPacketResourcePackStatus(String p_i46866_1_, CPacketResourcePackStatus.Action p_i46866_2_) {
      if(p_i46866_1_.length() > 40) {
         p_i46866_1_ = p_i46866_1_.substring(0, 40);
      }

      this.field_179720_a = p_i46866_1_;
      this.field_179719_b = p_i46866_2_;
   }

   public void func_148837_a(PacketBuffer p_148837_1_) throws IOException {
      this.field_179720_a = p_148837_1_.func_150789_c(40);
      this.field_179719_b = (CPacketResourcePackStatus.Action)p_148837_1_.func_179257_a(CPacketResourcePackStatus.Action.class);
   }

   public void func_148840_b(PacketBuffer p_148840_1_) throws IOException {
      p_148840_1_.func_180714_a(this.field_179720_a);
      p_148840_1_.func_179249_a(this.field_179719_b);
   }

   public void func_148833_a(INetHandlerPlayServer p_148833_1_) {
      p_148833_1_.func_175086_a(this);
   }

   public static enum Action {
      SUCCESSFULLY_LOADED,
      DECLINED,
      FAILED_DOWNLOAD,
      ACCEPTED;
   }
}
