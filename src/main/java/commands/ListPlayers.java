package commands;

import java.util.concurrent.TimeUnit;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;

import configuration.constant.EFolder;
import configuration.constant.EName;
import configuration.file.TOMLConfig;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;

@CommandInfo(name = { "Players" }, description = "List all players who are connected to the server, along with their Steam IDs.")
@Author("Unkиown#9999")
public class ListPlayers extends Command {

    private final TOMLConfig file;

    public ListPlayers(TOMLConfig file) {
        this.file = file;
        this.name = "players";
        this.help = "list all players who are connected to the server, along with their Steam IDs.";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {
        if (event.isFromType(ChannelType.PRIVATE)) {
            event.getPrivateChannel().sendMessage("Listing players ...").queue(this::listPlayers);
        } else {
            event.getTextChannel().sendMessage("Listing players ...").queue(this::listPlayers);
        }
    }

    private void listPlayers(Message message) {
        ExecuteShellCommand cmd = new ExecuteShellCommand();
        String state = cmd.executeProcess(EFolder.SCRIPT_SHERLOCK.getPath() + EName.SHERLOCK_SH.getName() + " listPlayers");
        if (!state.contains("not running") && !state.contains("empty")) {
            message.editMessage("```" + file.getString("markdown.c") + "\n" + state + "```").queueAfter(800, TimeUnit.MILLISECONDS);
        } else {
            message.editMessage(state).queueAfter(800, TimeUnit.MILLISECONDS);
        }
    }

    /*private void deleteHistory(CommandEvent event) {
        List<Message> messages = event.getAuthor().openPrivateChannel().complete().getIterableHistory().complete();
        for (int i = 1; i < messages.size(); i++) {
            String id = messages.get(i).getId();
            event.getAuthor().openPrivateChannel().flatMap(channel -> channel.deleteMessageById(id)).queueAfter(2, TimeUnit.SECONDS);
        }
    }*/

}
