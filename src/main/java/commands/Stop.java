package commands;

import java.util.concurrent.TimeUnit;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;

import configuration.constant.EFolder;
import configuration.constant.EName;

@CommandInfo(name = { "Stop" }, description = "Stops the server.")
@Author("Unkиown#9999")
public class Stop extends Command {

    public Stop() {
        this.name = "stop";
        this.help = "stops the server.";
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getTextChannel().sendMessage("Stopping the server ...").queue(message -> {
            ExecuteShellCommand cmd = new ExecuteShellCommand();
            String state = cmd.executeProcess(EFolder.SCRIPT_SHERLOCK.getPath() + EName.SHERLOCK_SH.getName() + " stop");
            if (!state.contains("not running")) {
                message.editMessage("Stopped !").queueAfter(800, TimeUnit.MILLISECONDS);
            } else {
                message.editMessage(state).queueAfter(800, TimeUnit.MILLISECONDS);
            }
        });
    }

}
