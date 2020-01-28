package tk.shanebee.survival.data;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Holder of data for player
 * <p>You can get an instance of PlayerData from <b>{@link tk.shanebee.survival.managers.PlayerManager}</b></p>
 */
@SuppressWarnings({"unused", "FieldCanBeLocal", "SameParameterValue"})
public class PlayerData implements ConfigurationSerializable {

	private UUID uuid;
	private int thirst;

	// Nutrients
	private int proteins;
	private int carbs;
	private int salts;
    private double energy;

	// Dunno yet
	private boolean localChat = false;

	// Stats
	private int charge = 0;
	private int charging = 0;
	private int spin = 0;
	private int dualWield = 0;
	private int dualWieldMsg = 0;
	private int healing = 0;
	private int healTimes = 0;
	private int recurveFiring = 0;
	private int recurveCooldown = 0;

	// Scoreboard info
	private boolean score_hunger = true;
	private boolean score_thirst = true;
	private boolean score_energy = true;
	private boolean score_nutrients = true;

	public PlayerData(OfflinePlayer player, int thirst, int proteins, int carbs, int salts, double energy) {
		this(player.getUniqueId(), thirst, proteins, carbs, salts, energy);
	}

	public PlayerData(UUID uuid, int thirst, int proteins, int carbs, int salts, double energy) {
		this.uuid = uuid;
		this.thirst = thirst;
		this.proteins = proteins;
		this.carbs = carbs;
		this.salts = salts;
		this.energy = energy;
	}

	/** Get the player from this data
	 * @return Player from this data
	 */
	public Player getPlayer() {
		return Bukkit.getPlayer(uuid);
	}

	/** Get the UUID of the player from this data
	 * @return UUID of player from this data
	 */
	public UUID getUuid() {
		return uuid;
	}

	/** Get the thirst of this data
	 * @return Thirst of this data
	 */
	public int getThirst() {
		return thirst;
	}

	/** Set the thirst for this data
	 * @param thirst Level of thirst to set
	 */
	public void setThirst(int thirst) {
		this.thirst = Math.min(thirst, 40);
	}

	/** Increase the thirst for this data
	 * @param thirst Level of thirst to add
	 */
	public void increaseThirst(int thirst) {
		this.thirst += thirst;
	}

	/** Get the nutrients from this data
	 * @param nutrient Nutrient to get
	 * @return Level of the nutrient
	 */
	public int getNutrient(Nutrient nutrient) {
		switch (nutrient) {
			case PROTEIN:
				return proteins;
			case CARBS:
				return carbs;
			case SALTS:
				return salts;
			default:
				throw new IllegalStateException("Unexpected value: " + nutrient);
		}
	}

	/** Set the nutrients for this data
	 * @param nutrient Nutrient to set
	 * @param value Level to set
	 */
	public void setNutrient(Nutrient nutrient, int value) {
		switch (nutrient) {
			case PROTEIN:
				this.proteins = value;
				break;
			case CARBS:
				this.carbs = value;
				break;
			case SALTS:
				this.salts = value;
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + nutrient);
		}
	}

    /** Set all the nutrients for this data
     * @param carbs Level of carbs to set
     * @param proteins Level of proteins to set
     * @param salts Level of salts to set
     */
	public void setNutrients(int carbs, int proteins, int salts) {
	    this.carbs = carbs;
	    this.proteins = proteins;
	    this.salts = salts;
    }

	/** Increase a nutrient for this data
	 * @param nutrient Nutrient to increase
	 * @param value Level of increase
	 */
	public void increaseNutrient(Nutrient nutrient, int value) {
		switch (nutrient) {
			case PROTEIN:
				this.proteins += value;
				break;
			case CARBS:
				this.carbs += value;
				break;
			case SALTS:
				this.salts += value;
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + nutrient);
		}
	}

    /** Get the energy of this data
     * @return Energy of this data
     */
    public double getEnergy() {
        return energy;
    }

    /** Set the energy level for this data
     * <p>Value must be between 0.0 and 20.0</p>
     * @param energy Energy level to set
     */
    public void setEnergy(double energy) {
        if (energy > 20.0) {
            this.energy = 20.0;
        } else if (energy < 0) {
            this.energy = 0.0;
        } else {
            this.energy = energy;
        }
    }

    /** Increase the energy level for this data
     * @param energy Energy amount to increase
     */
    public void increaseEnergy(double energy) {
        if ((this.energy += energy) > 10.0) {
            this.energy = 10.0;
        }
    }

    /** Set a stat for this data
	 * @param stat Stat to set
	 * @param value Value of stat to set
	 */
	public void setStat(Stat stat, int value) {
		switch (stat) {
			case CHARGE:
				this.charge = value;
				break;
			case CHARGING:
				this.charging = value;
				break;
			case SPIN:
				this.spin = value;
				break;
			case DUAL_WIELD:
				this.dualWield = value;
				break;
			case HEALING:
				this.healing = value;
				break;
			case HEAL_TIMES:
				this.healTimes = value;
				break;
			case RECURVE_FIRING:
				this.recurveFiring = value;
				break;
			case RECURVE_COOLDOWN:
				this.recurveCooldown = value;
		}
	}

	/** Get a stat from this data
	 * @param stat Stat to retrieve
	 * @return Value of stat
	 */
	public int getStat(Stat stat) {
		switch (stat) {
			case CHARGE:
				return this.charge;
			case CHARGING:
				return this.charging;
			case SPIN:
				return this.spin;
			case DUAL_WIELD:
				return this.dualWield;
			case HEALING:
				return this.healing;
			case HEAL_TIMES:
				return this.healTimes;
			case RECURVE_FIRING:
				return this.recurveFiring;
			case RECURVE_COOLDOWN:
				return this.recurveCooldown;
			default:
				throw new IllegalStateException("Unexpected value: " + stat);
		}
	}

	/** Set whether the player is using local chat
	 * @param localChat Whether the player is using local chat
	 */
	public void setLocalChat(boolean localChat) {
		this.localChat = localChat;
	}

	/** Check if the player is using local chat
	 * @return True if local chat is activated
	 */
	public boolean isLocalChat() {
		return localChat;
	}

	/** Get the visibility of a specific healthboard info
	 * @param info Healthboard info
	 * @return True if this info is displayed on the player's scoreboard
	 */
	public boolean isInfoDisplayed(Info info) {
		switch (info) {
			case HUNGER:
				return score_hunger;
			case THIRST:
				return score_thirst;
			case ENERGY:
				return score_energy;
			case NUTRIENTS:
				return score_nutrients;
			default:
				throw new IllegalStateException("Unexpected value: " + info);
		}
	}

	/** Set the visibility of a specific healthboard info
	 * @param info Healthboard info to display
	 * @param visible Whether the info should be displayed or not
	 */
	public void setInfoDisplayed(Info info, boolean visible) {
		switch (info) {
			case HUNGER:
				this.score_hunger = visible;
				break;
			case THIRST:
				this.score_thirst = visible;
				break;
			case ENERGY:
				this.score_energy = visible;
				break;
			case NUTRIENTS:
				this.score_nutrients = visible;
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + info);
		}
	}

	/** Set the visibility of all healthboard info
	 * <p>This is mainly used internally for data transfers</p>
	 * @param hunger Whether hunger should be displayed on the player's healthboard
	 * @param thirst Whether thirst should be displayed on the player's healthboard
	 * @param energy Whether energy should be displayed on the player's healthboard
	 * @param nutrients Whether nutrients should be displayed on the player's healthboard
	 */
	public void setInfoDisplayed(boolean hunger, boolean thirst, boolean energy, boolean nutrients) {
		this.score_hunger = hunger;
		this.score_thirst = thirst;
		this.score_energy = energy;
		this.score_nutrients = nutrients;
	}

	/** Internal serializer for yaml config
	 * @return Map for config
	 */
	@SuppressWarnings("NullableProblems")
	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> result = new LinkedHashMap<>();
		result.put("uuid", uuid.toString());
		result.put("thirst", thirst);
		result.put("energy", energy);
		result.put("nutrients.proteins", proteins);
		result.put("nutrients.carbs", carbs);
		result.put("nutrients.salts", salts);
		result.put("local-chat", localChat);
		result.put("score.hunger", score_hunger);
		result.put("score.thirst", score_thirst);
		result.put("score.energy", score_energy);
		result.put("score.nutrients", score_nutrients);
		return result;
	}

	/** Internal deserializer for yaml config
	 * @param args Args from yaml config
	 * @return New PlayerData loaded from config
	 */
	public static PlayerData deserialize(Map<String, Object> args) {
		UUID uuid = UUID.fromString(args.get("uuid").toString());
		int thirst = ((Integer) args.get("thirst"));
        double energy = getDouble(args, "energy", 20.0);
		int proteins = ((Integer) args.get("nutrients.proteins"));
		int carbs = ((Integer) args.get("nutrients.carbs"));
		int salts = ((Integer) args.get("nutrients.salts"));

		PlayerData data = new PlayerData(uuid, thirst, proteins, carbs, salts, energy);

		boolean localChat = getBool(args, "local-chat", false);
		data.setLocalChat(localChat);

		boolean score_hunger = getBool(args, "score.hunger", true);
		boolean score_thirst = getBool(args, "score.thirst", true);
		boolean score_energy = getBool(args, "score.energy", true);
		boolean score_nutrients = getBool(args, "score.nutrients", false);
		data.setInfoDisplayed(score_hunger, score_thirst, score_energy, score_nutrients);

		return data;
	}

	// Methods for grabbing sections from map, defaults if section isn't set
	private static int getInt(Map<String, Object> args, String val, int def) {
		if (args.containsKey(val))
			return ((int) args.get(val));
		return def;
	}

    private static double getDouble(Map<String, Object> args, String val, double def) {
        if (args.containsKey(val))
            return ((double) args.get(val));
        return def;
    }

	private static boolean getBool(Map<String, Object> args, String val, boolean def) {
		if (args.containsKey(val))
			return (boolean) args.get(val);
		return def;
	}

}
