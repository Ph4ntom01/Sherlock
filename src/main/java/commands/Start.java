package commands;

import java.util.concurrent.TimeUnit;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;

import configuration.file.TOMLConfig;

@CommandInfo(name = { "Start Server" }, description = "Starts the server.")
@Author("Unkиown#9999")
public class Start extends Command {

    private final TOMLConfig file;

    public Start(TOMLConfig file) {
        this.file = file;
        this.name = "start";
        this.help = "starts the server.";
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getTextChannel().sendMessage("Starting the server ...").queue(message -> {
            ExecuteShellCommand cmd = new ExecuteShellCommand();
            String state = cmd.executeProcess(file.getString("directory.sherlock_script") + " start");
            if (!state.contains("already")) {
                message.editMessage("Started ! (Should be up within 10 minutes).").queueAfter(800, TimeUnit.MILLISECONDS);
            } else {
                message.editMessage(state).queueAfter(800, TimeUnit.MILLISECONDS);
            }
        });
    }

}
