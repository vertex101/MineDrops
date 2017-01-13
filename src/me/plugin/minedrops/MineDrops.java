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

import java.util.Random;

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

        Random rand = new Random();
        int num = rand.nextInt(101);
        int pct;
        int pctTotal = 0;

        if (num == 101) {
            num = rand.nextInt(101);
        } else {
            pctTotal = (num * 100 / 100);
        }
        pct = getConfig().getInt("ironpct");

        if (b.getType() == Material.IRON_ORE) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have mined an Iron Ore! and the perecntage was " + pctTotal + " and the base perectnage is " + pct);
            if (pctTotal == pct) { b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.GLOWSTONE_DUST)); }
        }
    }
}
