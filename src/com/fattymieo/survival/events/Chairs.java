package com.fattymieo.survival.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.material.Stairs;
import org.bukkit.util.Vector;

import com.fattymieo.survival.Survival;

public class Chairs implements Listener
{	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		if(event.hasBlock() && event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			Block block = event.getClickedBlock();
			
			if(Survival.allowedBlocks.contains(block.getType()))
			{
				Player player = event.getPlayer();
				Stairs stairs = (Stairs)block.getState().getData();
				int chairwidth = 1;
				
				// Check if block beneath chair is solid.
				if(block.getRelative(BlockFace.DOWN).getType() == Material.AIR || block.getRelative(BlockFace.DOWN) == null)
					return;
				
				// Check if player is sitting.
				if(!player.isSneaking() && player.getVehicle() != null)
				{
					player.getVehicle().remove();
					return;
				}
				
				// Check for distance distance between player and chair.
				if(player.getLocation().distance(block.getLocation().add(0.5, 0, 0.5)) > 2)
					return;
				
				// Check for signs.
				boolean sign1 = false;
				boolean sign2 = false;
				
				if(stairs.getDescendingDirection() == BlockFace.NORTH || stairs.getDescendingDirection() == BlockFace.SOUTH) {
					sign1 = checkSign(block, BlockFace.EAST);
					sign2 = checkSign(block, BlockFace.WEST);
				}
				else if(stairs.getDescendingDirection() == BlockFace.EAST || stairs.getDescendingDirection() == BlockFace.WEST) {
					sign1 = checkSign(block, BlockFace.NORTH);
					sign2 = checkSign(block, BlockFace.SOUTH);
				}
				
				if(!(sign1 == true && sign2 == true))
					return;
				
				// Check for maximal chair width.
				if(stairs.getDescendingDirection() == BlockFace.NORTH || stairs.getDescendingDirection() == BlockFace.SOUTH) {
					chairwidth += getChairWidth(block, BlockFace.EAST);
					chairwidth += getChairWidth(block, BlockFace.WEST);
				}
				else if(stairs.getDescendingDirection() == BlockFace.EAST || stairs.getDescendingDirection() == BlockFace.WEST) {
					chairwidth += getChairWidth(block, BlockFace.NORTH);
					chairwidth += getChairWidth(block, BlockFace.SOUTH);
				}
			
				if(chairwidth > Survival.settings.getInt("Mechanics.Chairs.MaxChairWidth"))
					return;
				
				// Sit-down process.
				if(player.getVehicle() != null)
					player.getVehicle().remove();

				ArmorStand drop = dropSeat(block, stairs);
				List<ArmorStand> drops = checkChair(drop);
				
				if (drops != null)
				{
					drop.remove();
					return;
				}
				
				// Rotate the player's view to the descending side of the block.
				Location plocation = player.getLocation();
				
				switch(stairs.getDescendingDirection())
				{
					case NORTH:
						plocation.setYaw(180);
						break;
					case EAST:
						plocation.setYaw(270);
						break;
					case SOUTH:
						plocation.setYaw(0);
						break;
					case WEST:
						plocation.setYaw(90);
					default:
				}
			
				player.teleport(plocation);
				
				// Changing the drop material is only necessary for the item merge feature of CB++
				// The client won't update the material, though.
				drop.setPassenger(player);	
				
				// Cancel BlockPlaceEvent Result, if player is rightclicking with a block in his hand.
				event.setUseInteractedBlock(Result.DENY);
			}
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		if(Survival.allowedBlocks.contains(event.getBlock().getType()))
		{
			ArmorStand drop = dropSeat(event.getBlock(), (Stairs)event.getBlock().getState().getData());
			
			for(Entity e : drop.getNearbyEntities(0.5, 0.5, 0.5))
			{
				if(e != null && e instanceof ArmorStand && e.getCustomName() == "Chair")
					e.remove();
			}
			
			drop.remove();
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		Entity vehicle = event.getPlayer().getVehicle();
		
		// Let players stand up when leaving the server.
		if(vehicle != null && vehicle instanceof ArmorStand && vehicle.getCustomName() == "Chair")
			vehicle.remove();
	}
	
	@EventHandler
	public void onHit(EntityDamageEvent event)
	{
		if(event.getEntity().getCustomName() == "Chair")
			event.setCancelled(true);
		if(event.getEntity().getVehicle() != null)
		{
			if(event.getEntity().getVehicle().getCustomName() == "Chair")
				event.getEntity().getVehicle().remove();
		}
	}
	
	private ArmorStand dropSeat(Block chair, Stairs stairs)
	{
		Location location = chair.getLocation().add(0.5, (-0.7 - 0.5), 0.5);
		
		switch(stairs.getDescendingDirection())
		{
			case NORTH:
				location.setYaw(180);
				break;
			case EAST:
				location.setYaw(270);
				break;
			case SOUTH:
				location.setYaw(0);
				break;
			case WEST:
				location.setYaw(90);
			default:
		}
		
		ArmorStand drop = (ArmorStand)location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
		drop.setCustomName("Chair");
		drop.setVelocity(new Vector(0, 0, 0));
		drop.setGravity(false);
		drop.setVisible(false);
		
		return drop;
	}
	
	private List<ArmorStand> checkChair(ArmorStand drop)
	{
		List<ArmorStand> drops = new ArrayList<ArmorStand>();
		
		// Check for already existing chair items.
		for(Entity e : drop.getNearbyEntities(0.5, 0.5, 0.5))
		{
			if(e != null && e instanceof ArmorStand && e.getCustomName() == "Chair")
			{
				if(e.getPassenger() == null)
					e.remove();
				else
					drops.add(drop);
			}
		}
		
		if(!drops.isEmpty())
			return drops;
		
		return null;
	}
	
	private int getChairWidth(Block block, BlockFace face)
	{
		int width = 0;
		
		// Go through the blocks next to the clicked block and check if there are any further stairs.
		for(int i = 1; i <= Survival.settings.getInt("Mechanics.Chairs.MaxChairWidth"); i++)
		{
			Block relative = block.getRelative(face, i);
			
			if(Survival.allowedBlocks.contains(relative.getType()) && ((Stairs)relative.getState().getData()).getDescendingDirection() == ((Stairs)block.getState().getData()).getDescendingDirection())
				width++;
			else
				break;
		}
		
		return width;
	}
	
	private boolean checkSign(Block block, BlockFace face)
	{
		// Go through the blocks next to the clicked block and check if are signs on the end.
		for(int i = 1; true; i++)
		{
			Block relative = block.getRelative(face, i);
			if(!(Survival.allowedBlocks.contains(relative.getType())) || (block instanceof Stairs && ((Stairs)relative.getState().getData()).getDescendingDirection() != ((Stairs)block.getState().getData()).getDescendingDirection()))
			{
				switch(relative.getType())
				{
					case SIGN:
					case WALL_SIGN:
					case SIGN_POST:
					case ITEM_FRAME:
					case PAINTING:
					case TRAP_DOOR:
					case IRON_TRAPDOOR:
						return true;
					default:
						return false;
				}
			}
		}
	}
}
