package de.Roxas98.moneysystem.command;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.Roxas98.moneysystem.main.Main;

public class SetMoneyCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Logger log = Bukkit.getLogger();
		
		Player player = (Player) sender;
		
		if(sender instanceof Player) {
			if(player.hasPermission("money.set")) {
				if(args.length == 1) {
					FileConfiguration money = Main.getPlugin().getConfig();
					money.set(player.getDisplayName() + ".balance", args[0]);
					Main.getPlugin().saveConfig();
					player.sendMessage(Main.Präfix + "Dein Kontostand wurde auf " + args[0] + " gesetzt");
				
				}else if(args.length == 2) {
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(player.hasPermission("money.set.other")) {
						if(target != null) {
							FileConfiguration targetMoney = Main.getPlugin().getConfig();
							targetMoney.set(target.getDisplayName() + ".balance", args[1]);
							Main.getPlugin().saveConfig();
							player.sendMessage(Main.Präfix + "Kontostand vom Spieler " + target.getDisplayName() + " wurde auf " + args[1] + " Euro gesetzt!");
							target.sendMessage(Main.Präfix + "Dein Kontostand wurde auf " + args[1] + " Euro gesetzt!");
						}else {
							player.sendMessage(Main.Präfix + "Der Spieler ist nicht online!");
						}
					}else {
						player.sendMessage(Main.Präfix + Main.Permission);
					}
				}else {
					player.sendMessage(Main.Präfix + "Bitte gebe /set (Betrag) oder /set (Player) (Betrag) ein!");
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
