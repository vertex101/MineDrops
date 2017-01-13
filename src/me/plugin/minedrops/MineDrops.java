package me.plugin.minedrops;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("minedrop")) {
            if (args[0].equalsIgnoreCase("add")){
                if (args[1].equalsIgnoreCase("iron")) {
                    sender.sendMessage("This is a Test Command of iron!!!!");
                } else if (args[1].equalsIgnoreCase("gold")) {

                } else if (args[1].equalsIgnoreCase("diamond")) {

                } else {
                    sender.sendMessage("Please use /md add then either select iron, gold, or diamond");
                    return true;
                }
            } else {
                sender.sendMessage("Please use the following /minedrop or /md then add");
                return true;
            }

        } else {
            sender.sendMessage("Please use the following /minedrop or /md then add");
            return true;
        }
        return true;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        Player p = e.getPlayer();

        int getIron, getGold, getDiamond;
        int pctIron, pctGold, pctDiamond;

        getIron = getConfig().getInt("ironpct");
        getGold = getConfig().getInt("goldpct");
        getDiamond = getConfig().getInt("diamondpct");

        pctIron = getRandom(1, getIron + 20);
        pctGold = getRandom(1, getGold + 20);
        pctDiamond = getRandom(1, getDiamond + 20);

        if (b.getType() == Material.IRON_ORE) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have mined an Iron Ore! and the perecentage was " + pctIron + " and the base perectnage is " + getIron);
            if (pctIron == getIron) { b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.GLOWSTONE_DUST)); }
        } else if (b.getType() == Material.GOLD_ORE) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have mined an Iron Ore! and the perecentage was " + pctGold + " and the base perectnage is " + getGold);
        } else if (b.getType() == Material.DIAMOND_ORE) {
            p.sendMessage(ChatColor.DARK_AQUA + "You have mined an Iron Ore! and the perecentage was " + pctDiamond + " and the base perectnage is " + getDiamond);
        }
    }

    public int getRandom(int min, int max) {
        return (int)(Math.random() * (max - min)) + min;
    }
}
