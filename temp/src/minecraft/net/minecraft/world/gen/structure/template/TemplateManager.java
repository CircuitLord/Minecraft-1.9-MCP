package net.minecraft.world.gen.structure.template;

import com.google.common.collect.Maps;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.template.Template;
import org.apache.commons.io.IOUtils;

public class TemplateManager {
   private final Map<String, Template> field_186240_a;
   private final String field_186241_b;

   public TemplateManager() {
      this("structures");
   }

   public TemplateManager(String p_i46661_1_) {
      this.field_186240_a = Maps.<String, Template>newHashMap();
      this.field_186241_b = p_i46661_1_;
   }

   public Template func_186237_a(MinecraftServer p_186237_1_, ResourceLocation p_186237_2_) {
      String s = p_186237_2_.func_110623_a();
      if(this.field_186240_a.containsKey(s)) {
         return (Template)this.field_186240_a.get(s);
      } else {
         if(p_186237_1_ != null) {
            this.func_186235_b(p_186237_1_, p_186237_2_);
         } else {
            this.func_186236_a(p_186237_2_);
         }

         if(this.field_186240_a.containsKey(s)) {
            return (Template)this.field_186240_a.get(s);
         } else {
            Template template = new Template();
            this.field_186240_a.put(s, template);
            return template;
         }
      }
   }

   public boolean func_186235_b(MinecraftServer p_186235_1_, ResourceLocation p_186235_2_) {
      String s = p_186235_2_.func_110623_a();
      File file1 = p_186235_1_.func_71209_f(this.field_186241_b);
      File file2 = new File(file1, s + ".nbt");
      if(!file2.exists()) {
         return this.func_186236_a(p_186235_2_);
      } else {
         InputStream inputstream = null;

         boolean flag;
         try {
            inputstream = new FileInputStream(file2);
            this.func_186239_a(s, inputstream);
            return true;
         } catch (Throwable var12) {
            flag = false;
         } finally {
            IOUtils.closeQuietly(inputstream);
         }

         return flag;
      }
   }

   private boolean func_186236_a(ResourceLocation p_186236_1_) {
      String s = p_186236_1_.func_110624_b();
      String s1 = p_186236_1_.func_110623_a();
      InputStream inputstream = null;

      boolean flag;
      try {
         inputstream = MinecraftServer.class.getResourceAsStream("/assets/" + s + "/structures/" + s1 + ".nbt");
         this.func_186239_a(s1, inputstream);
         return true;
      } catch (Throwable var10) {
         flag = false;
      } finally {
         IOUtils.closeQuietly(inputstream);
      }

      return flag;
   }

   private void func_186239_a(String p_186239_1_, InputStream p_186239_2_) throws IOException {
      NBTTagCompound nbttagcompound = CompressedStreamTools.func_74796_a(p_186239_2_);
      Template template = new Template();
      template.func_186256_b(nbttagcompound);
      this.field_186240_a.put(p_186239_1_, template);
   }

   public boolean func_186238_c(MinecraftServer p_186238_1_, ResourceLocation p_186238_2_) {
      String s = p_186238_2_.func_110623_a();
      if(!this.field_186240_a.containsKey(s)) {
         return false;
      } else {
         File file1 = p_186238_1_.func_71209_f(this.field_186241_b);
         if(!file1.exists()) {
            if(!file1.mkdirs()) {
               return false;
            }
         } else if(!file1.isDirectory()) {
            return false;
         }

         File file2 = new File(file1, s + ".nbt");
         NBTTagCompound nbttagcompound = new NBTTagCompound();
         Template template = (Template)this.field_186240_a.get(s);
         OutputStream outputstream = null;

         boolean flag;
         try {
            template.func_186265_a(nbttagcompound);
            outputstream = new FileOutputStream(file2);
            CompressedStreamTools.func_74799_a(nbttagcompound, outputstream);
            return true;
         } catch (Throwable var14) {
            flag = false;
         } finally {
            IOUtils.closeQuietly(outputstream);
         }

         return flag;
      }
   }
}
