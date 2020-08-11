package de.Roxas98.moneysystem.command;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.Roxas98.moneysystem.main.Main;

public class GiveMoneyCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Logger log = Bukkit.getLogger();
		
		Player player = (Player) sender;
		
		if(sender instanceof Player) {
			if(player.hasPermission("money.give")) {
				if(args.length == 1) {
					FileConfiguration money = Main.getPlugin().getConfig();
					
					String balance = money.getString(player.getDisplayName() + ".balance");
					int newBalance = Integer.parseInt(balance) + Integer.parseInt(args[0]);
					money.set(player.getDisplayName() + ".balance", Integer.toString(newBalance));
					Main.getPlugin().saveConfig();
					
					player.sendMessage(Main.Präfix + "Deinem Kontostand wurden " + args[0] + " Euro hinzugefügt, jetzt beträgt dein Kontostand " + newBalance + " Euro");
				
				}else if(args.length == 2) {
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(player.hasPermission("money.give.other")) {
						if(target != null) {
							FileConfiguration targetMoney = Main.getPlugin().getConfig();
							
							String balance = targetMoney.getString(player.getDisplayName() + ".balance");
							int newBalance = Integer.parseInt(balance) - Integer.parseInt(args[1]);
							targetMoney.set(player.getDisplayName() + ".balance", Integer.toString(newBalance));
							
							
							String balanceTarget = targetMoney.getString(target.getDisplayName() + ".balance");
							int newBalanceTarget = Integer.parseInt(balanceTarget) + Integer.parseInt(args[1]);
							targetMoney.set(target.getDisplayName() + ".balance", Integer.toString(newBalanceTarget));
							Main.getPlugin().saveConfig();
							
							player.sendMessage(Main.Präfix + "Du hast " + target.getDisplayName() + args[1] + " Euro gegeben!");
							target.sendMessage(Main.Präfix + "Du hast von " + player.getDisplayName() + " " + args[1] + " Euro erhalten!");
							
						}else {
							player.sendMessage(Main.Präfix + "Der Spieler ist nicht online!");
						}
							
					}else {
						player.sendMessage(Main.Präfix + Main.Permission);
					}
					
					
				}else {
					player.sendMessage(Main.Präfix + "Bitte gebe /give (Betrag) oder /give (Player) (Betrag) ein!");
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
