package be.kingquest.spigotFriend.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static be.kingquest.spigotFriend.database.MysqlVorgaenge.addPlayer;
import static be.kingquest.spigotFriend.database.MysqlVorgaenge.istUser;
import static be.kingquest.spigotFriend.friendManager.OfflineFriends.offlineFriends;
import static be.kingquest.spigotFriend.friendManager.OfflineFriendsAnfragen.offlineFriendsAnfragen;
import static be.kingquest.spigotFriend.friendManager.OnlineFriends.OnlineFriends;
import static be.kingquest.spigotFriend.friendManager.OnlineFriendsAnfragen.onlineFriendsAnfragen;
import static be.kingquest.spigotFriend.gui.Freunde.FreundeGUI;
import static be.kingquest.spigotFriend.gui.FreundeAnfragen.FreundeAnfragen;

public class MeineFreunde  implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {

        if (!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        if(args.length == 0){
            FreundeGUI(p, OnlineFriends(p), offlineFriends(p));
            return true;
        }else if(args.length == 1){
            if(args[0].equalsIgnoreCase("request") || args[0].equalsIgnoreCase("anfrage")){
                FreundeAnfragen(p, onlineFriendsAnfragen(p), offlineFriendsAnfragen(p));
            }
        }else if(args.length == 2){
            if(args[0].equalsIgnoreCase("add")){
                if(istUser(args[1])){
                        addPlayer(p, args[1]);
                }else{
                    p.sendMessage("§cDer Player " + p.getName() + " spielt nicht auf dem Server.");
                }
            }
        }
        return false;
    }
}
