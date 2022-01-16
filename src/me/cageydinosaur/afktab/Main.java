package me.cageydinosaur.afktab;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.luckperms.api.LuckPerms;

public class Main extends JavaPlugin {

	LuckPerms api = null;

	public void onEnable() {
		getCommand("afktab").setExecutor(new Cmd(this));
		this.getServer().getPluginManager().registerEvents(new Events(this), this);
		this.saveDefaultConfig();

		RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
		if (provider != null) {
			api = provider.getProvider();
		}
	}

	public String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public String privateNonAFKMessage() {
		return this.getConfig().getString("privateNonAFKMessage");
	}

	public String privateAFKMessage() {
		return this.getConfig().getString("privateAFKMessage");
	}

	public String globalAFKMessage() {
		return this.getConfig().getString("globalAFKMessage");
	}

	public String globalNonAFKMessage() {
		return this.getConfig().getString("globalNonAFKMessage");
	}

	public String tabListText() {
		return this.getConfig().getString("tabListText");
	}

	public int priorityLevel() {
		return this.getConfig().getInt("priorityLevel");
	}
}
