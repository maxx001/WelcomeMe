package dk.monkeyboy.WelcomeMe;

import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerListener;

public class WelcomeMePlayerListener extends PlayerListener{
	private final WelcomeMe plugin;
	
	public WelcomeMePlayerListener(WelcomeMe ref)
	{
		plugin = ref;
	}
	
	public void onPlayerJoin(PlayerEvent event)
	{
		plugin.DisplayMessage(event.getPlayer());
	}
}
