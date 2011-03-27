package dk.monkeyboy.WelcomeMe;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;



public class ReloadPluginCommand implements CommandExecutor{
	private final WelcomeMe plugin;
	
	public ReloadPluginCommand(WelcomeMe ref)
	{
		plugin = ref;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(args.length == 0){
			// CommandSender wants to see the message again
			plugin.DisplayMessage(sender);			
			return true;
		} else if(args.length == 1){
			if(args[0].equals("reload")){
				if(sender.isOp()){
					plugin.ReadMessageFromFile();
					sender.sendMessage("Welcome message file reloaded.");
					return true;
				} else {
					sender.sendMessage("You do not have permission to use this command.");
				}
			}
		}
		return false;
	}
}
