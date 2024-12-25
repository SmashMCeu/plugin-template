package eu.smashmc.sekai;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SekaiPlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		Bukkit.broadcastMessage("Hello world");
		super.onEnable();
	}
}
