package tk.shanebee.survival.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import tk.shanebee.survival.Survival;

public class Backpack implements Listener
{
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		//if(true) return;
		
		if(event.getRawSlot() < event.getView().getTopInventory().getSize()) //Bottom Inventory only
			return;
		
		final Player player = (Player) event.getWhoClicked();
		player.sendMessage("In inventory now, clicking slot " + Integer.toString(event.getSlot()));
		
		if(!(player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE))
			return;
		
		ItemStack backpackSlot = event.getView().getBottomInventory().getItem(event.getSlot());
		ItemStack cursorSlot = event.getCursor();
		
		switch(event.getSlot())
		{
			case 19:
			case 22:
			case 25:
				if(event.getClick() != ClickType.LEFT)
				{
					event.setCancelled(true);
					return;
				}
				
				if(backpackSlot != null && backpackSlot.getType() != Material.AIR) //If slot is not empty
				{
					if(backpackSlot.getType() == Material.WOODEN_HOE) //If slot is has wood hoe
					{
						if(cursorSlot != null && cursorSlot.getType() != Material.AIR) //If cursor is not empty
						{
							event.setCancelled(true);
						}
					}
					else
					{
						event.setCancelled(true);
						player.getWorld().dropItem(player.getLocation(), player.getInventory().getItem(event.getSlot()));
						player.getInventory().clear(event.getSlot());
						player.updateInventory();
						player.sendMessage("ERROR, NON BACKPACK DETECTED!");
					}
				}
				else
				{
					if(!(cursorSlot != null && cursorSlot.getType() == Material.WOODEN_HOE)) //If cursor is not a wood hoe
					{
						event.setCancelled(true);
					}
				}
				break;
			case 9:
			case 10:
			case 11:
			case 18:
			case 20:
			case 27:
			case 28:
			case 29:
			case 12:
			case 13:
			case 14:
			case 21:
			case 23:
			case 30:
			case 31:
			case 32:
			case 15:
			case 16:
			case 17:
			case 24:
			case 26:
			case 33:
			case 34:
			case 35:
				if(backpackSlot != null && Survival.CheckIfLockedSlot(backpackSlot))
				{
					event.setCancelled(true);
				}
				break;
			default:
				return;
		}
	}
	
	@EventHandler
	public void onPickupItem(EntityPickupItemEvent event)
	{
		/*
		if(!(event.getEntity() instanceof Player))
			return;
		
		Player player = (Player)event.getEntity();
		ItemStack item = event.getItem().getItemStack();
		
		Inventory inv = player.getInventory();
		
		//int maxSize = inv.getSize();
		int maxSize = 36;
		int newItemSlot = inv.firstEmpty();
		
		while(newItemSlot < maxSize)
		{
			if(!isSlotAvailableInBackpack(inv, newItemSlot))
			{
				newItemSlot++;
			}
			else
			{
				if(inv.getItem(newItemSlot) != null)
				{
					if(inv.getItem(newItemSlot).getType() == Material.AIR)
					{
						event.setCancelled(false);
						event.getItem().remove();
						player.getInventory().setItem(newItemSlot, item);
						return;
					}
					else
					{
						newItemSlot++;
					}
				}
				else
				{
					newItemSlot++;
				}
			}
		}*/
	}
	
	public boolean isSlotAvailableInBackpack(Inventory inv, int slot)
	{
		switch(slot)
		{
		case 9:
		case 10:
		case 11:
		case 18:
		case 20:
		case 27:
		case 28:
		case 29:
			return (inv.getItem(19) != null && inv.getItem(19).getType() == Material.WOODEN_HOE);
		case 12:
		case 13:
		case 14:
		case 21:
		case 23:
		case 30:
		case 31:
		case 32:
			return (inv.getItem(22) != null && inv.getItem(22).getType() == Material.WOODEN_HOE);
		case 15:
		case 16:
		case 17:
		case 24:
		case 26:
		case 33:
		case 34:
		case 35:
			return (inv.getItem(25) != null && inv.getItem(25).getType() == Material.WOODEN_HOE);
		default:
			return true;
		}
	}
	
	/*
	@EventHandler
	public void onItemClick(PlayerInteractEvent event)
	{
		if(event.hasItem())
		{
			Player player = event.getPlayer();
			if(event.getItem().getType() == Material.WOOD_SPADE)
			{
				if(player.isSneaking())
				{
					if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
					{
						if(player.getInventory().getItemInMainHand().getType() == event.getItem().getType())
							player.getInventory().setItemInMainHand(null);
						else if(player.getInventory().getItemInOffHand().getType() == event.getItem().getType())
							player.getInventory().setItemInOffHand(null);
						player.updateInventory();

						Random rand = new Random();
		            	player.getLocation().getWorld().playSound(player.getLocation(), Sound.ITEM_SHOVEL_FLATTEN, 1.0F, rand.nextFloat() * 0.4F + 0.8F);
		            	
						Inventory firestriker = Bukkit.createInventory(player, InventoryType.FURNACE, Survival.Words.get("Firestriker"));
						player.openInventory(firestriker);
						firestriker.setItem(1, event.getItem());
						event.setCancelled(true);
					}
				}
				else
				{
					if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
					{
						switch(event.getClickedBlock().getType())
						{
							case ENCHANTMENT_TABLE:
							case ANVIL:
							case BREWING_STAND:
							case SPRUCE_DOOR:
							case BIRCH_DOOR:
							case WOOD_DOOR:
							case JUNGLE_DOOR:
							case ACACIA_DOOR:
							case DARK_OAK_DOOR:
							case IRON_DOOR:
							case TRAPPED_CHEST:
							case CHEST:
							case BED:
							case NOTE_BLOCK:
							case FENCE_GATE:
							case SPRUCE_FENCE_GATE:
							case BIRCH_FENCE_GATE:
							case JUNGLE_FENCE_GATE:
							case DARK_OAK_FENCE_GATE:
							case ACACIA_FENCE_GATE:
							case TRAP_DOOR:
							case IRON_TRAPDOOR:
							case FURNACE:
							case BURNING_FURNACE:
							case HOPPER:
							case WORKBENCH:
							case DROPPER:
							case DISPENSER:
								return;
							default:
						}
						
						Location loc = event.getClickedBlock().getRelative(event.getBlockFace()).getLocation();
						if(ignite(player, loc, IgniteCause.FLINT_AND_STEEL))
						{
							Random rand = new Random();
			            	player.getLocation().getWorld().playSound(player.getLocation(), Sound.ITEM_SHOVEL_FLATTEN, 1.0F, rand.nextFloat() * 0.4F + 0.8F);
							
							event.getItem().setDurability((short)(event.getItem().getDurability() + 7));
							if(event.getItem().getDurability() >= 56)
							{
								player.getLocation().getWorld().playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, rand.nextFloat() * 0.4F + 0.8F);
								if(player.getInventory().getItemInMainHand().getType() == event.getItem().getType())
									player.getInventory().setItemInMainHand(null);
								else if(player.getInventory().getItemInOffHand().getType() == event.getItem().getType())
									player.getInventory().setItemInOffHand(null);
							}
							player.updateInventory();
						}
					}
				}
			}
		}
	}
	
	public boolean ignite(Player igniter, Location loc, IgniteCause cause) {
	    Random rand = new Random();

	    loc.add(0.5, 0.5, 0.5);
	    
	    BlockIgniteEvent igniteEvent = new BlockIgniteEvent(loc.getBlock(), 
	            cause, (org.bukkit.entity.Entity) igniter);
	    Bukkit.getServer().getPluginManager().callEvent(igniteEvent);
	    if (igniteEvent.isCancelled()) {
	        return false;
	    }
	 
	    BlockState blockState = loc.getBlock().getState();
	 
	    BlockPlaceEvent placeEvent = new BlockPlaceEvent(loc.getBlock(), 
	            blockState, loc.getBlock(), igniter.getItemInHand(), igniter, true);
	    Bukkit.getServer().getPluginManager().callEvent(placeEvent);
	 
	    if (placeEvent.isCancelled() || !placeEvent.canBuild()) {
	        placeEvent.getBlockPlaced().setTypeIdAndData(0, (byte) 0, false);
	        return false;
	    }

	    loc.getWorld().playSound(loc, Sound.ITEM_FLINTANDSTEEL_USE, 1.0F, rand.nextFloat() * 0.4F + 0.8F);
	    loc.getBlock().setType(Material.FIRE);
	    
	    return true;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		if(event.getInventory().getTitle() != Survival.Words.get("Firestriker"))
			return;
		
		final Player player = (Player) event.getWhoClicked();
		
		switch(event.getRawSlot())
		{
			case 1:
			event.setCancelled(true);
			player.closeInventory();
			break;
			
			case 2:
			event.setCancelled(true);
			if(event.getClick() == ClickType.LEFT)
			{
				if(event.getCursor() == null || event.getCursor().getType() == Material.AIR)
				{
					if(event.getInventory().getItem(2) == null || event.getInventory().getItem(2).getType() == Material.AIR)
					{
						if(event.getInventory().getItem(1) != null && event.getInventory().getItem(1).getType() == Material.WOOD_SPADE)
						{
							if(smeltCheck(event.getInventory(), event.getInventory().getItem(0)))
							{
								event.setCancelled(true);
								Random randBurn = new Random();
								player.getLocation().getWorld().playSound(player.getLocation(), Sound.ITEM_FLINTANDSTEEL_USE, 1.0F, randBurn.nextFloat() * 0.4F + 0.8F);
								player.getLocation().getWorld().playSound(player.getLocation(), Sound.ENTITY_GENERIC_BURN, 1.0F, randBurn.nextFloat() * 0.4F + 0.8F);
								
								ItemStack i_smelt = event.getInventory().getItem(0);
								i_smelt.setAmount(i_smelt.getAmount() - 1);
								event.getInventory().setItem(0, i_smelt);
								
								ItemStack i_firecracker = event.getInventory().getItem(1);
								i_firecracker.setDurability((short)(i_firecracker.getDurability() + 7));
								if(i_firecracker.getDurability() >= 56)
								{
									Random rand = new Random();
									player.getLocation().getWorld().playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, rand.nextFloat() * 0.4F + 0.8F);
									event.getInventory().setItem(1, null);
								}
							}
						}
					}
					else
					{
						event.setCursor(event.getInventory().getItem(2));
						event.getInventory().setItem(2, null);
					}
				}
				else
					event.setCancelled(true);
			}
			else
				event.setCancelled(true);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Survival.instance, new Runnable()
		    {
		    	public void run()
		    	{
					player.updateInventory();
	            }
		    }, 1L);
			break;
		}
	}
	
	public boolean smeltCheck(Inventory inv, ItemStack item)
	{
		boolean canSmelt = true;

	    if(item == null)
	    	canSmelt = false;
	    else if(item.getType() == Material.PORK)
			inv.setItem(2, new ItemStack(Material.GRILLED_PORK));
		else if(item.getType() == Material.RAW_BEEF)
			inv.setItem(2, new ItemStack(Material.COOKED_BEEF));
		else if(item.getType() == Material.RAW_CHICKEN)
			inv.setItem(2, new ItemStack(Material.COOKED_CHICKEN));
		else if(item.getType() == Material.RAW_FISH)
			inv.setItem(2, new ItemStack(Material.COOKED_FISH, 1, item.getDurability()));
		else if(item.getType() == Material.POTATO_ITEM)
			inv.setItem(2, new ItemStack(Material.BAKED_POTATO));
		else if(item.getType() == Material.MUTTON)
			inv.setItem(2, new ItemStack(Material.COOKED_MUTTON));
		else if(item.getType() == Material.RABBIT)
			inv.setItem(2, new ItemStack(Material.COOKED_RABBIT));
		else if(item.getType() == Material.SAND)
			inv.setItem(2, new ItemStack(Material.GLASS));
		else if(item.getType() == Material.CLAY_BALL)
			inv.setItem(2, new ItemStack(Material.CLAY_BRICK));
		else if(item.getType() == Material.LOG)
			inv.setItem(2, new ItemStack(Material.COAL, 1, (short)1));
		else if(item.getType() == Material.LOG_2)
			inv.setItem(2, new ItemStack(Material.COAL, 1, (short)1));
		else
			canSmelt = false;
		
		return canSmelt;
	}
	
	@EventHandler
	public void onCloseInventory(InventoryCloseEvent event)
	{
		if(event.getInventory().getTitle() == Survival.Words.get("Firestriker"))
		{
			Inventory inv = event.getInventory();
			Player player = (Player) event.getPlayer();
			if(inv.getItem(0) != null)
				player.getWorld().dropItem(player.getLocation(), inv.getItem(0));
			if(inv.getItem(1) != null && inv.getItem(1).getType() != Material.BARRIER)
				player.getInventory().addItem(inv.getItem(1));
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onAttack(EntityDamageByEntityEvent event)
	{
		if(event.isCancelled()) return;
		if(event.getDamager() instanceof Player && event.getEntity() instanceof LivingEntity && event.getCause() == DamageCause.ENTITY_ATTACK)
		{
			Player player = (Player)event.getDamager();
			ItemStack item = player.getItemInHand();
			if(item.getType() == Material.WOOD_SPADE)
			{
				item.setDurability((short)(item.getDurability() - 2));
			}
		}
	}
	*/
}