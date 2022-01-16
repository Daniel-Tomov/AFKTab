package me.cageydinosaur.afktab;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.ess3.api.IUser;
import net.ess3.api.events.AfkStatusChangeEvent;
import net.luckperms.api.node.types.PrefixNode;

public class Events implements Listener {

	Main plugin;

	public Events(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerChangeAfkStatus(AfkStatusChangeEvent e) {

		IUser i = e.getAffected();
		Player p = Bukkit.getPlayer(i.getName());
		if (i.isAfk()) {
			if (p.hasPermission("afk.sendmessage.global")) {
				Bukkit.broadcastMessage(
						plugin.chat(plugin.globalNonAFKMessage().replace("{player}", p.getName())));
			} else if (p.hasPermission("afk.sendmessage.private")) {
				i.setAfkMessage(plugin.chat(plugin.privateNonAFKMessage()));
			}
			p.setPlayerListName(ChatColor.WHITE + p.getName());
			PrefixNode node = PrefixNode.builder(plugin.chat(plugin.tabListText()), plugin.priorityLevel()).build();

			plugin.api.getUserManager().modifyUser(p.getUniqueId(), user -> {
				user.data().remove(node);
			});
		} else {
			if (p.hasPermission("afk.sendmessage.global")) {
				Bukkit.broadcastMessage(
						plugin.chat(plugin.globalAFKMessage().replace("{player}", p.getDisplayName())));
			} else if (p.hasPermission("afk.sendmessage.private")) {
				i.setAfkMessage(plugin.chat(plugin.privateAFKMessage()));
			}
			p.setPlayerListName(ChatColor.GRAY + plugin.chat(plugin.tabListText()) + p.getName());
			PrefixNode node = PrefixNode.builder(plugin.chat(plugin.tabListText()), plugin.priorityLevel()).build();

			plugin.api.getUserManager().modifyUser(p.getUniqueId(), user -> {
				user.data().add(node);
			});
		}

	}

}
