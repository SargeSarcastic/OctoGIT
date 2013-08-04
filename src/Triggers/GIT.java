package Triggers;

import Main.DB;
import Main.Main;
import org.pircbotx.Colors;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GIT
{
    public static void Request(String channel) throws SQLException
    {
        ResultSet result = DB.get("SELECT * FROM `channels` WHERE `channel`='"+channel+"';");
        while(result.next())
        {

           switch(result.getInt("git"))
           {
               case 0:
                   Main.bot.sendMessage(channel, "Current permission status: " + Colors.BOLD + "PENDING");
                   break;
               case 1:
                   Main.bot.sendMessage(channel, "Current permission status: " + Colors.BOLD + Colors.RED + "DECLINED");
                   break;
               case 2:
                   Main.bot.sendMessage(channel, "Current permission status: " + Colors.BOLD + Colors.GREEN + "ACCEPTED");
                   break;
               default:
                   Main.bot.sendMessage(channel, "Current permission status: " + Colors.BOLD + "UNKNOWN");
                   break;
           }
        }
     }

    public static void Accept(String channel) throws SQLException
    {
        if(channel.isEmpty()) { return; }
        DB.exec("UPDATE `channels` SET `git`=2 WHERE `channel`='" + channel + "';");
        Main.bot.joinChannel(channel);
        Main.bot.sendMessage(channel, "Your channel has been accepted!");
    }

    public static void Info()
    {

    }
}
