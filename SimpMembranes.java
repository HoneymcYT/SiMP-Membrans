package me.Honeys.Simpmembrans;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Bee;
import org.bukkit.entity.Phantom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpMembranes extends JavaPlugin implements Listener {
   private boolean enabled;

   public void onEnable() {
      this.enabled = this.getConfig().getBoolean("enabled", true);
      this.saveDefaultConfig();
      Bukkit.getPluginManager().registerEvents(this, this);
   }

   @EventHandler
   public void onPhantomSpawn(EntitySpawnEvent event) {
      if (event.getEntity() instanceof Phantom && this.enabled) {
         event.setCancelled(true);
         event.getLocation().getWorld().spawn(event.getLocation(), Bee.class);
      }

   }

   @EventHandler
   public void onBeeDeath(EntityDeathEvent event) {
      if (event.getEntity() instanceof Bee && this.enabled) {
         event.getDrops().clear();
         event.getDrops().add(new ItemStack(Material.PHANTOM_MEMBRANE, 1));
      }

   }
}
