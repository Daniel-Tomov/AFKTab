package me.cageydinosaur.afktab;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public class Cmd implements CommandExecutor {

	Main plugin;

	public Cmd(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender.hasPermission("afk.reload")) {
			if (args[0].equalsIgnoreCase("reload")) {
				plugin.reloadConfig();
				return true;
			}
		} else {
			sender.sendMessage(ChatColor.RED + "You do not have permission to use that command");
			return true;
		}
		return false;
	}

}
