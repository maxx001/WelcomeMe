package dk.monkeyboy.WelcomeMe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class WelcomeMe extends JavaPlugin {	
	public ArrayList<String> UnformattedMessage = new ArrayList<String>();
	Logger log = Logger.getLogger("Minecraft");
	
	public void onDisable() { };
	
	public void onEnable()
	{
		PluginDescriptionFile pdfFile = this.getDescription();
        //System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
		log.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
		
        PluginManager pm = getServer().getPluginManager();
        
        pm.registerEvent(Event.Type.PLAYER_JOIN, new WelcomeMePlayerListener(this), Event.Priority.Normal, this);
        
        getCommand("wm").setExecutor(new ReloadPluginCommand(this));
	}
	
	public void onLoad()
	{
		ReadMessageFromFile();
	}
	
	// Credit to Bone008 @http://forums.bukkit.org/threads/parse-for-color-codes.25965/
	public String Colorize(String s)
	{
		if(s == null) return null;
		return s.replaceAll("&([0-9a-f])", "\u00A7$1");
	}
	
	public void ReadMessageFromFile()
	{
		BufferedReader b = null;
		String str = null;
		
		try {
			b = new BufferedReader(new FileReader("plugins/WelcomeMe/message.txt"));
			UnformattedMessage.clear();
			
			while((str = b.readLine()) != null)
			{
				UnformattedMessage.add(Colorize(str));
			}
			b.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void DisplayMessage(Player player)
	{
		String playersString = "";
		String maxplayers = Integer.toString(getServer().getMaxPlayers());
		String numplayers = Integer.toString(getServer().getOnlinePlayers().length);
		
		for(Player p : getServer().getOnlinePlayers())
		{
			if(playersString.length() < 1){
				playersString = p.getDisplayName();
			} else {
				playersString = playersString + "," + p.getDisplayName();
			}
		}
		
		for(String s : UnformattedMessage)
		{
			s = s.replaceAll("%op", numplayers);
			s = s.replaceAll("%mp", maxplayers);
			s = s.replaceAll("%pl", playersString);
			s = s.replaceAll("%np", player.getDisplayName());
			player.sendMessage(s);
		}
	}
	
	public void DisplayMessage(CommandSender sender)
	{
		String playersString = "";
		String maxplayers = Integer.toString(getServer().getMaxPlayers());
		String numplayers = Integer.toString(getServer().getOnlinePlayers().length);
		String np = "";
		
		if(sender instanceof Player){
			Player player = (Player)sender;
			np = player.getDisplayName();
		} else if(sender instanceof ConsoleCommandSender){
			np = "Console";
		} else {
			np = "Unknown";
		}
		
		for(Player p : getServer().getOnlinePlayers())
		{
			if(playersString.length() < 1){
				playersString = p.getDisplayName();
			} else {
				playersString = playersString + "," + p.getDisplayName();
			}
		}
		
		for(String s : UnformattedMessage)
		{
			s = s.replaceAll("%op", numplayers);
			s = s.replaceAll("%mp", maxplayers);
			s = s.replaceAll("%pl", playersString);
			s = s.replaceAll("%np", np);
			sender.sendMessage(s);
		}
	}
}
