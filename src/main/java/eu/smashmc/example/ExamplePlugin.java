package eu.smashmc.example;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class ExamplePlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		Bukkit.broadcastMessage("Hello world");
		super.onEnable();
	}
}
