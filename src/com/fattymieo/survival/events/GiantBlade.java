package com.fattymieo.survival.events;

import java.util.Collection;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.util.Vector;

import com.fattymieo.survival.Survival;

import lib.ParticleEffect;

public class GiantBlade implements Listener
{	
	int schedule = 0;
    
    Objective charge = Survival.board.getObjective("Charge");
    Objective charging = Survival.board.getObjective("Charging");
    Objective dualWield = Survival.board.getObjective("DualWield");
    
	@EventHandler
	public void onAttack(EntityDamageByEntityEvent event)
	{
		if(event.getEntity() instanceof Player && event.getEntity() instanceof LivingEntity && event.getCause() == DamageCause.ENTITY_ATTACK)
		{
			Player player = (Player)event.getEntity();
			LivingEntity enemy = (LivingEntity)event.getDamager();
			ItemStack offItem = player.getInventory().getItemInOffHand();

			Random rand = new Random();
			
			if(offItem.getType() == Material.GOLD_HOE)
			{
				enemy.damage(event.getDamage() * 40 / 100, player);
				
				int chance_reduceDur = rand.nextInt(10) - 1;
				switch(chance_reduceDur)
				{
					case 1:
						offItem.setDurability((short)(offItem.getDurability() + 1));
						break;
					default:
				}

				if(offItem.getDurability() >= 32)
				{
					player.getLocation().getWorld().playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, rand.nextFloat() * 0.4F + 0.8F);
					player.getInventory().setItemInOffHand(null);
				}
			}
		}
	}
	
	//To prevent double messages send to player.
	Objective tech_dualWieldMsg = Survival.board.registerNewObjective("DualWieldMsg", "dummy");
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onItemClick(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		ItemStack mainItem = player.getInventory().getItemInMainHand();
		
		Score score_dualWieldMsg = tech_dualWieldMsg.getScore(player);
		
		if(dualWield.getScore(player).getScore() == 0)
		{
			if(mainItem.getType() == Material.GOLD_HOE)
			{
				if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR)
				{
					if(player.isSprinting())
					{
						if(charge.getScore(player).getScore() == 0)
						{
							Random rand = new Random();
							
							ChargeForward(player, 3, mainItem);
							
							if(player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE)
								player.setFoodLevel(player.getFoodLevel() - 1);
							
							int chance_reduceDur = rand.nextInt(10) - 1;
							switch(chance_reduceDur)
							{
								case 1:
									mainItem.setDurability((short)(mainItem.getDurability() + 1));
									break;
								default:
							}
							
							if(event.getItem().getDurability() >= 32)
							{
								player.getLocation().getWorld().playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, rand.nextFloat() * 0.4F + 0.8F);
								player.getInventory().setItemInMainHand(null);
							}
							player.updateInventory();
						}
						else
						{
							player.sendMessage(ChatColor.RED + Survival.Words.get("Unable to Charge immediately"));
						}
					}
				}
			}
		}
		else 
		{
			if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
				score_dualWieldMsg.setScore(score_dualWieldMsg.getScore() + 1);
			else if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
				score_dualWieldMsg.setScore(score_dualWieldMsg.getScore() + 2);
			if(score_dualWieldMsg.getScore() == 2)
			{
				player.sendMessage(ChatColor.RED + Survival.Words.get("Unable to dual-wield with Giant Blade"));
			}
		}
		score_dualWieldMsg.setScore(0);
	}
	
	@SuppressWarnings("deprecation")
	public void ChargeForward(Player player, int velocity, ItemStack mainItem)
	{
		player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + Survival.Words.get("CHARGE!"));
		
		Score score = charge.getScore(player);
		score.setScore(1);
		
		Location loc = player.getLocation();
		if(loc.getPitch() < 0)
			loc.setPitch(0);
	
	    Vector vel = loc.getDirection();
	    
	    Vector newVel = vel.multiply(velocity);
	    
	    player.setVelocity(newVel);
	    
	    final Player chargingPlayer = player;
	    charging.getScore(chargingPlayer).setScore(8);
	    
	    final Runnable task = new Runnable()
	    {
			public void run()
	    	{
        		damageNearbyEnemies(chargingPlayer, 8);
        		
        		Random rand = new Random();
        	    chargingPlayer.getLocation().getWorld().playSound(chargingPlayer.getLocation(), Sound.ENTITY_SHULKER_BULLET_HIT, 1.5F, rand.nextFloat() * 0.4F + 0.8F);
        		ParticleEffect.EXPLOSION_NORMAL.display(0, 0, 0, 0, 10, chargingPlayer.getLocation(), 64);

    	    	int times = charging.getScore(chargingPlayer).getScore();
        		if(--times > 1)
        			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Survival.instance, this, 1L);
        		charging.getScore(chargingPlayer).setScore(times);
            }
	    };
	    
	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Survival.instance, task, -1L);
	    
	    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Survival.instance, new Runnable()
	    {
			Score score = charge.getScore(chargingPlayer);
	    	public void run()
	    	{
	    		score.setScore(0);
				chargingPlayer.sendMessage(ChatColor.GREEN + Survival.Words.get("Ready to Charge"));
            }
	    },
	    100L);
	}
	
	public void damageNearbyEnemies(Player player, int dmg)
	{
		Collection<Entity> enemies = player.getLocation().getWorld().getNearbyEntities(player.getLocation(), 1, 2, 1);
	    for(Entity e : enemies)
	    {
	    	if(e instanceof LivingEntity && e != (Entity)player)
	    	{
	    		LivingEntity enemy = (LivingEntity)e;
	    		enemy.damage(dmg, (Entity)player);
	    	}
	    }
	}
}
