package me.plugin.minedrops;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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

        int getIron, getGold, getDiamond, pctIron, pctGold, pctDiamond;

        getIron = getConfig().getInt("ironpct");
        getGold = getConfig().getInt("goldpct");
        getDiamond = getConfig().getInt("diamondpct");

        pctIron = getRandom(1, getIron + 20);
        pctGold = getRandom(1, getGold + 20);
        pctDiamond = getRandom(1, getDiamond + 20);

        if (b.getType() == Material.IRON_ORE) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have mined an Iron Ore! and the perecntage was " + pctIron + " and the base perectnage is " + getIron);
            if (pctIron == getIron) { b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.GLOWSTONE_DUST)); }
        }
    }

    public int getRandom(int min, int max) {
        return (int)(Math.random() * (max - min)) + min;
    }
}
