package dk.monkeyboy.WelcomeMe;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class WelcomeMePlayerListener extends PlayerListener{
	private final WelcomeMe plugin;
	
	public WelcomeMePlayerListener(WelcomeMe ref)
	{
		plugin = ref;
	}
	
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		plugin.DisplayMessage(event.getPlayer());
	}
}
