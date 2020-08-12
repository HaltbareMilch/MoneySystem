package de.Roxas98.moneysystem.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.Roxas98.moneysystem.command.GiveMoneyCMD;
import de.Roxas98.moneysystem.command.MoneyCMD;
import de.Roxas98.moneysystem.command.SetMoneyCMD;

public class Main extends JavaPlugin{
	
	public static String Präfix = "§8[§4MoneySystem§8] §r";
	public static String Permission = "Du hast dazu keine Rechte!";
	public static char Waehrung = '€';
	
	public static String CommandLine = "Dieser Befehel kann nur von Spielern ausgeführt werden";
	
	private static Main plugin;
	
	Logger log = Bukkit.getLogger();
	
	
	public void onEnable() {
		plugin = this;
		
		log.info("MoneySystem ist hochgefahren");
		
		registerCommands();
	}
	
	public void onDisable() {
		log.info("MoneySystem ist heruntergefahren");
		
	}
	
	public void registerCommands() {
		getCommand("money").setExecutor(new MoneyCMD());
		getCommand("give").setExecutor(new GiveMoneyCMD());
		getCommand("set").setExecutor(new SetMoneyCMD());
	}
	
	public static Main getPlugin(){
		return plugin;
	}
	
}
