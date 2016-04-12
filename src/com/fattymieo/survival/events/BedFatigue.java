package com.fattymieo.survival.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scoreboard.Objective;

import com.fattymieo.survival.Survival;

public class BedFatigue implements Listener
{
	Objective fatigue = Survival.mainBoard.getObjective("Fatigue");
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBedLeave(PlayerBedLeaveEvent e)
	{
		long time = e.getBed().getWorld().getTime();
		if(time % 24000 == 0)
		{
			Player player = e.getPlayer();
			fatigue.getScore(player).setScore(0);
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event)
	{
		Player player = event.getPlayer();
		fatigue.getScore(player).setScore(0);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onFirstJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		if(!player.hasPlayedBefore())
		{
			fatigue.getScore(player).setScore(0);
		}
	}
}
