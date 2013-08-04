package Listeners;


import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.Colors;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Triggers.GIT;

public class GitListener extends ListenerAdapter
{

    public static List<String> AllowGIT = new ArrayList<String>();

    @Override
    public void onMessage(MessageEvent event) throws SQLException {
        String[] msg = Colors.removeFormattingAndColors(event.getMessage()).split(" ");
        if(msg[0].equals("!git"))
        {
            if(msg[1].equals("request"))
            {
                GIT.Request(event.getChannel().getName());
            }

            if(msg[1].equals("accept"))
            {
                GIT.Accept(msg[2]);
            }
        }
    }

}
