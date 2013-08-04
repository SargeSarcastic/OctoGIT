package Listeners;

import java.sql.SQLException;

import Main.DB;
import Main.Globals;
import Main.Main;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.*;

@SuppressWarnings("rawtypes")
public class GeneralListener extends ListenerAdapter/*<PircBotX>*/
{

    @Override
    public void onConnect(ConnectEvent event)
    {
        event.getBot().identify(Globals.IRCGATE);
    }

    @Override
    public void onKick(KickEvent event) throws SQLException
    {
        DB.exec("INSERT into `kicks` ");
    }

    @Override
    public void onJoin(JoinEvent event)
    {
        if (event.getUser().getNick().equals(event.getBot().getNick()))
            try
            {
                DB.exec("INSERT INTO Channels VALUES ('" + event.getChannel().getName() + "');");
            } catch (SQLException e) { }
    }
}
