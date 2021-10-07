package xyz.raitaki;

import net.minecraft.server.v1_16_R3.Vector3f;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.raitaki.util.NMSArmorStand;

public class testcommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (label.equalsIgnoreCase("packetstand")) {
            NMSArmorStand stand = new NMSArmorStand(p.getLocation());
            stand.setInvisible(false);
            stand.spawn();
            stand.display(p.getLocation(), 0D);
        }
        return false;
    }
}
