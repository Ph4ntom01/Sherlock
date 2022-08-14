package commands;

import java.util.concurrent.TimeUnit;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;

import configuration.constant.EFolder;
import configuration.constant.EName;

@CommandInfo(name = { "Update" }, description = "Check for a new ARK server version, if needed, stops the server, updates it, and starts it again.")
@Author("Unkиown#9999")
public class Update extends Command {

    public Update() {
        this.name = "update";
        this.help = "check for a new ARK server version, if needed, stops the server, updates it, and starts it again.";
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getTextChannel().sendMessage("Checking for a new ARK server version ...").queue(message -> {
            ExecuteShellCommand cmd = new ExecuteShellCommand();
            String state = cmd.executeProcess(EFolder.SCRIPT_SHERLOCK.getPath() + EName.SHERLOCK_SH.getName() + " update");
            message.editMessage(state).queueAfter(800, TimeUnit.MILLISECONDS);
        });
    }

}
