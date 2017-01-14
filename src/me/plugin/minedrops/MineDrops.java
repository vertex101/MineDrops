package me.plugin.minedrops;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class MineDrops extends JavaPlugin implements Listener{

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();

        int getIron, getGold, getDiamond, pctIron, pctGold, pctDiamond, pctCoal, getMaterial;

        getIron = getConfig().getInt("ironpct");
        getGold = getConfig().getInt("goldpct");
        getDiamond = getConfig().getInt("diamondpct");

        pctIron = getRandom(1, getIron + 20);
        pctGold = getRandom(1, getGold + 20);
        pctDiamond = getRandom(1, getDiamond + 20);
        pctCoal = getRandom(1, 15);
        Material[] grabMats = Material.values();
        ItemStack item = new ItemStack(grabMats[getRandom(0, grabMats.length)]);

        if (b.getType() == Material.IRON_ORE) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have mined an Iron Ore! and the perecntage was " + pctIron + " and the base perectnage is " + getIron);
            if (pctIron == 1) { b.getWorld().createExplosion(b.getLocation(), 4.0F); }
            if (pctIron <= getIron) { b.getWorld().dropItemNaturally(b.getLocation(), item); }
        } else if (b.getType() == Material.GOLD_ORE) {
            if (pctGold == 1) { b.getWorld().createExplosion(b.getLocation(), 4.0F); }
        } else if (b.getType() == Material.DIAMOND_ORE) {
            if (pctDiamond == 1) { b.getWorld().createExplosion(b.getLocation(), 4.0F); }
        } else if (b.getType() == Material.COAL_ORE) {
            if (pctCoal == 4) { b.getWorld().createExplosion(b.getLocation(), 4.0F); }
        }
    }
    public int getRandom(int min, int max) {
        return (int)(Math.random() * (max - min)) + min;
    }
}
