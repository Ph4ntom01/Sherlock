package commands;

import java.util.concurrent.TimeUnit;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;

import configuration.constant.EFolder;
import configuration.constant.EName;

@CommandInfo(name = { "Backup" }, description = "Saves a backup of the server inside the backup directory.")
@Author("Unkиown#9999")
public class Backup extends Command {

    public Backup() {
        this.name = "backup";
        this.help = "saves a backup of the server inside the backup directory.";
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getTextChannel().sendMessage("Creating a backup ...").queue(message -> {
            ExecuteShellCommand cmd = new ExecuteShellCommand();
            String state = cmd.executeProcess(EFolder.SCRIPT_SHERLOCK.getPath() + EName.SHERLOCK_SH.getName() + " backup");
            message.editMessage(state).queueAfter(800, TimeUnit.MILLISECONDS);
        });
    }

}
