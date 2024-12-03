package be.kingquest.spigotFriend.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static be.kingquest.spigotFriend.friendManager.OfflineFriendsAnfragen.offlineFriendsAnfragen;
import static be.kingquest.spigotFriend.friendManager.OnlineFriendsAnfragen.onlineFriendsAnfragen;
import static be.kingquest.spigotFriend.gui.FreundeAnfragen.FreundeAnfragen;

public class MeineFreundschaftanfragen  implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {

        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        FreundeAnfragen(p, onlineFriendsAnfragen(p), offlineFriendsAnfragen(p));
        return false;
    }
}
