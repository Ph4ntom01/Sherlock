package sherlock;

import javax.security.auth.login.LoginException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import commands.Backup;
import commands.ChkUpdate;
import commands.ListPlayers;
import commands.Start;
import commands.Status;
import commands.Stop;
import commands.Update;
import configuration.constant.EID;
import configuration.file.ConfigFactory;
import configuration.file.TOMLConfig;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Discord {

    private static final Logger LOG = LoggerFactory.getLogger(Discord.class);

    private final TOMLConfig file;

    public Discord() {
        file = ConfigFactory.getSherlock();
    }

    protected void connect() {

        // Define an event waiter, don't forget to add this to the JDABuilder !
        EventWaiter waiter = new EventWaiter();

        // Define a command client.
        CommandClientBuilder client = new CommandClientBuilder();

        client.useDefaultGame();

        client.setOwnerId(EID.OWNER_ID.getId());

        client.setEmojis("\uD83D\uDE03", "\uD83D\uDE2E", "\uD83D\uDE26");

        client.setPrefix("?");

        // Adds commands.
        client.addCommands(new Start(), new Stop(), new ListPlayers(file), new Backup(), new ChkUpdate(file), new Update(), new Status(file));

        try {
            JDABuilder.createDefault(file.getString("token.discord")).enableIntents(GatewayIntent.GUILD_MEMBERS).addEventListeners(waiter, client.build()).build();
        } catch (LoginException e) {
            LOG.error(e.getMessage());
        }
    }

}
