package de.Roxas98.moneysystem.command;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.Roxas98.moneysystem.main.Main;

public class MoneyCMD implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Logger log = Bukkit.getLogger();
		
		Player player = (Player) sender;
		
		if(sender instanceof Player) {
			if(player.hasPermission("money.balance")){
				if(args.length == 0) {
					
					FileConfiguration money = Main.getPlugin().getConfig();
					String balance = (String) money.get(player.getDisplayName() + ".balance");
					Main.getPlugin().saveConfig();
					player.sendMessage(Main.Präfix + "Dein Kontosand beträgt: " + balance);
					
				}else if(args.length == 1) {
					if(player.hasPermission("money.balance.other")) {
						Player target = Bukkit.getServer().getPlayer(args[0]);
						
						FileConfiguration targetMoney = Main.getPlugin().getConfig();
						if(targetMoney.getString(target.getDisplayName()) != null) {
							player.sendMessage(Main.Präfix + "Der Spieler hat " + targetMoney.getString(target.getDisplayName() + ".balance") + " Euro");
						}else {
							player.sendMessage(Main.Präfix + "Der Spieler hat noch kein Kontostand!");
						}
						Main.getPlugin().saveConfig();
						
					}else {
						player.sendMessage(Main.Präfix + Main.Permission);
						
					}
					
				}else {
					player.sendMessage(Main.Präfix + "Bitte gebe /money ein!");
				}
				
			}else {
				player.sendMessage(Main.Präfix + Main.Permission);
			}
			
		}else {
			log.info(Main.CommandLine);
		}
		
		return false;
	}

}
