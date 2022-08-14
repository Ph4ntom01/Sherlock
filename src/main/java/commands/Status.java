package commands;

import java.util.concurrent.TimeUnit;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;

import configuration.file.TOMLConfig;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;

@CommandInfo(name = { "Status" }, description = "Returns the status of the current ARK server instance.")
@Author("Unkиown#9999")
public class Status extends Command {

    private final TOMLConfig file;

    public Status(TOMLConfig file) {
        this.file = file;
        this.name = "status";
        this.help = "returns the status of the current ARK server instance.";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {
        if (event.isFromType(ChannelType.PRIVATE)) {
            event.getPrivateChannel().sendMessage("Checking the status of the current ARK server instance ...").queue(this::status);
        } else {
            event.getTextChannel().sendMessage("Checking the status of the current ARK server instance ...").queue(this::status);
        }
    }

    private void status(Message message) {
        ExecuteShellCommand cmd = new ExecuteShellCommand();
        String state = cmd.executeProcess(file.getString("directory.sherlock_script"));
        if (!state.contains("not running")) {
            message.editMessage("```" + file.getString("markdown.yaml") + "\n" + state + "```").queueAfter(800, TimeUnit.MILLISECONDS);
        } else {
            message.editMessage(state).queueAfter(800, TimeUnit.MILLISECONDS);
        }
    }

}
