package Main;

import org.pircbotx.Colors;
import org.pircbotx.PircBotX;
import Listeners.*;
import org.pircbotx.exception.IrcException;
import org.pircbotx.exception.NickAlreadyInUseException;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static PircBotX bot;

    public static void main(String[] args) throws IrcException, IOException, SQLException {
        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    Thread.sleep(25 * 1000);
                } catch(InterruptedException e) { }
            }
        }).start();



        bot = new PircBotX();
        bot.setVerbose(true);
        bot.setName("OctoGIT");
        bot.setLogin("OctoGIT");
        bot.setVersion("Your friendly GIT bot!");
        bot.setMessageDelay(500);

        DB.init();

        // Initialize the listeners
        bot.getListenerManager().addListener(new GeneralListener());
        bot.getListenerManager().addListener(new GitListener());


        // Finished, lets connect!
        bot.connect("irc.siglost.com", 7000);

        ResultSet result = DB.get("SELECT * FROM channels");
        while(result.next())
        {
            bot.joinChannel(result.getString("channel"));
            if(result.getInt("git") == 2)
            {
                GitListener.AllowGIT.add(result.getString("channel"));
                bot.sendMessage(result.getString("channel"), "GIT functions have now been: " + Colors.BOLD + Colors.GREEN + "enabled");
            }
        }
    }
}
