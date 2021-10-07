package xyz.raitaki;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {

    private void registerCommands(String[] cmds, CommandExecutor cmdExecutor)
    {
        for (String cmd : cmds)
        {
            getCommand(cmd).setExecutor(cmdExecutor);
        }
    }

    @Override
    public void onEnable() {
        registerCommands(new String[] { "packetstand" }, new testcommand() );
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
