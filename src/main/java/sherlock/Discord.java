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
import configuration.file.ConfigFactory;
import configuration.file.TOMLConfig;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Discord {

    private static final Logger LOG = LoggerFactory.getLogger(Discord.class);

    private final TOMLConfig file;

    public Discord(String path) {
        file = ConfigFactory.getSherlock(path);
    }

    protected void connect() {

        // Define an event waiter, don't forget to add this to the JDABuilder !
        EventWaiter waiter = new EventWaiter();

        // Define a command client.
        CommandClientBuilder client = new CommandClientBuilder();

        client.useDefaultGame();

        client.setOwnerId(file.getString("owner.id"));

        client.setEmojis("\uD83D\uDE03", "\uD83D\uDE2E", "\uD83D\uDE26");

        client.setPrefix(file.getString("bot.prefix"));

        // Adds commands.
        client.addCommands(new Start(file), new Stop(file), new ListPlayers(file), new Backup(file), new ChkUpdate(file), new Update(file), new Status(file));

        try {
            JDABuilder.createDefault(file.getString("bot.token")).enableIntents(GatewayIntent.GUILD_MEMBERS).addEventListeners(waiter, client.build()).build();
        } catch (LoginException e) {
            LOG.error(e.getMessage());
        }
    }

}
