package me.f1337.levelcraftcore;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Whitelist {
	public static LevelCraftCore plugin;

	public Whitelist(LevelCraftCore instance) {
		plugin = instance;
	}

	public static boolean hasAdminCommand(Player p, String c) {
		if (!plugin.PermissionUse) {
			if (p.isOp())
				return true;
			return false;
		}
		return p.hasPermission("lc.admin." + c);
	}

	/*
	 * public int cap(Player s,Plugin p){
	 * 
	 * if (!plugin.PermissionUse){ return plugin.LevelCap; } String level =
	 * plugin.LevelNames.get(p); plugin.PermissionH.g
	 * if(plugin.PermissionH.has(s, "lc.lcap."+level.toLowerCase())){ //
	 * plugin.PermissionH.getInfoInteger(arg0, arg1, arg2, arg3) //
	 * plugin.PermissionH. }
	 * 
	 * 
	 * return plugin.LevelCap; }
	 */

	public static boolean hasLevel(Player s, Plugin p) {
		for (String playerN : plugin.Bypassers) {
			if (playerN.equalsIgnoreCase(s.getName()))
				return false;
		}
		try {
			if (!plugin.PermissionUse)
				return true;
			if (s.hasPermission("lc.unlevel." + plugin.LevelNames.get(p).toLowerCase()))
				return true; // Line !
			if (s.hasPermission("lc.level." + plugin.LevelNames.get(p).toLowerCase()))
				return true; // Line !
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean hasLevelExp(Player s, Plugin p) {
		for (String playerN : plugin.Bypassers) {
			if (playerN.equalsIgnoreCase(s.getName()))
				return false;
		}
		if (!plugin.PermissionUse)
			return true;
		if (s.hasPermission("lc.level." + plugin.LevelNames.get(p).toLowerCase()))
			
			return true;
		return false;

	}

	public static boolean worldCheck(World world) {
		String w = world.getName();
		for (String s : plugin.Worlds) {
			if (w.equalsIgnoreCase(s))
				return true;
		}
		return false;
	}

	public boolean isAdmin(CommandSender s) {
		if (!plugin.PermissionUse && s.isOp())
			return true;
		if (!plugin.PermissionUse)
			return false;
		if (s instanceof Player) {
			Player pl = (Player) s;
			if (pl.hasPermission("lc.admin")
					|| pl.hasPermission("lc.admin.*")
					|| pl.hasPermission("lc.admin.purge")
					|| pl.hasPermission("lc.admin.setexp")
					|| pl.hasPermission("lc.admin.reload")
					|| pl.hasPermission("lc.admin.getexp")
					|| pl.hasPermission("lc.admin.getlvl")
					|| pl.hasPermission("lc.admin.reset")
					|| pl.hasPermission("lc.admin.setlvl")) {
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	public boolean canShout(CommandSender s) {
		if (!(s instanceof Player))
			return false;
		if (!plugin.PermissionUse)
			return true;
		return ((Player) s).hasPermission("lc.shout");
	}
}
