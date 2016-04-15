package com.fattymieo.survival.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;

import com.fattymieo.survival.Survival;

public class Status implements CommandExecutor
{
	Objective boardHunger = Survival.mainBoard.getObjective("BoardHunger");
	Objective boardThirst = Survival.mainBoard.getObjective("BoardThirst");
	Objective boardFatigue = Survival.mainBoard.getObjective("BoardFatigue");
	Objective boardNutrients = Survival.mainBoard.getObjective("BoardNutrients");
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(command.getName().equalsIgnoreCase("status"))
		{
			if (!(sender instanceof Player))
			{
				sender.sendMessage(Survival.Words.get("Works on players only"));
				return false;
			}
			
			Player player = (Player) sender;
			
			if(args.length == 0)
			{
				if(!Survival.settings.getBoolean("Mechanics.StatusScoreboard"))
				{
					player.sendMessage(Survival.ShowHunger(player).get(1) + Survival.ShowHunger(player).get(2) + " " + Survival.ShowHunger(player).get(0).toUpperCase());
					if(Survival.settings.getBoolean("Mechanics.Thirst.Enabled"))
		    			player.sendMessage(Survival.ShowThirst(player).get(1) + Survival.ShowThirst(player).get(2) + " " + Survival.ShowThirst(player).get(0).toUpperCase());
				}
				else
				{
					boardHunger.getScore(player).setScore(0);
					boardThirst.getScore(player).setScore(0);
					boardFatigue.getScore(player).setScore(1);
					boardNutrients.getScore(player).setScore(1);
				}
			}
			
			if(args.length == 1)
			{
				switch(args[0])
				{
					case "all":
						if(!Survival.settings.getBoolean("Mechanics.StatusScoreboard"))
						{
							player.sendMessage(Survival.ShowHunger(player).get(1) + Survival.ShowHunger(player).get(2) + " " + Survival.ShowHunger(player).get(0).toUpperCase());
							if(Survival.settings.getBoolean("Mechanics.Thirst.Enabled"))
				    			player.sendMessage(Survival.ShowThirst(player).get(1) + Survival.ShowThirst(player).get(2) + " " + Survival.ShowThirst(player).get(0).toUpperCase());
							if(Survival.settings.getBoolean("Mechanics.BedFatigueLevel"))
								player.sendMessage(Survival.ShowFatigue(player));
							if(Survival.settings.getBoolean("Mechanics.FoodDiversity"))
							{
								for(String s : Survival.ShowNutrients(player))
									player.sendMessage(s);
							}
						}
						else
						{
							boardHunger.getScore(player).setScore(0);
							boardThirst.getScore(player).setScore(0);
							boardFatigue.getScore(player).setScore(0);
							boardNutrients.getScore(player).setScore(0);
						}
						break;
					case "hunger":
					case "h":
						if(!Survival.settings.getBoolean("Mechanics.StatusScoreboard"))
						{
							player.sendMessage(Survival.ShowHunger(player).get(1) + Survival.ShowHunger(player).get(2) + " " + Survival.ShowHunger(player).get(0).toUpperCase());
						}
						else
							boardHunger.getScore(player).setScore(Reverse(boardHunger.getScore(player).getScore()));
						break;
					case "thirst":
					case "t":
						if(!Survival.settings.getBoolean("Mechanics.StatusScoreboard"))
						{
							if(Survival.settings.getBoolean("Mechanics.Thirst.Enabled"))
								player.sendMessage(Survival.ShowThirst(player).get(1) + Survival.ShowThirst(player).get(2) + " " + Survival.ShowThirst(player).get(0).toUpperCase());
						}
						else
							boardThirst.getScore(player).setScore(Reverse(boardThirst.getScore(player).getScore()));
						break;
					case "fatigue":
					case "f":
						if(!Survival.settings.getBoolean("Mechanics.StatusScoreboard"))
						{
							if(Survival.settings.getBoolean("Mechanics.BedFatigueLevel"))
								player.sendMessage(Survival.ShowFatigue(player));
						}
						else
							boardFatigue.getScore(player).setScore(Reverse(boardFatigue.getScore(player).getScore()));
						break;
					case "nutrients":
					case "n":
						if(!Survival.settings.getBoolean("Mechanics.StatusScoreboard"))
						{
							if(Survival.settings.getBoolean("Mechanics.FoodDiversity"))
							{
								for(String s : Survival.ShowNutrients(player))
									player.sendMessage(s);
							}
						}
						else
							boardNutrients.getScore(player).setScore(Reverse(boardNutrients.getScore(player).getScore()));
						break;
					default:
						return false;
				}
			}

			return true;
		}
		else
			return false;
	}
	
	private int Reverse(int i)
	{
		if(i <= 0)
			i = 1;
		else if(i > 0)
			i = 0;
		return i;
	}
}
