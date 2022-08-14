package commands;

import java.util.concurrent.TimeUnit;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import com.jagrosh.jdautilities.examples.doc.Author;

import configuration.constant.EFolder;
import configuration.constant.EName;
import configuration.file.TOMLConfig;

@CommandInfo(name = { "Update" }, description = "Check for a new ARK server version.")
@Author("Unkиown#9999")
public class ChkUpdate extends Command {

    private final TOMLConfig file;

    public ChkUpdate(TOMLConfig file) {
        this.file = file;
        this.name = "chkupdate";
        this.help = "check for a new ARK server version.";
        this.guildOnly = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.getTextChannel().sendMessage("Checking for a new ARK server version ...").queue(message -> {
            ExecuteShellCommand cmd = new ExecuteShellCommand();
            String state = cmd.executeProcess(EFolder.SCRIPT_SHERLOCK.getPath() + EName.SHERLOCK_SH.getName() + " chkupdate");
            message.editMessage("```" + file.getString("markdown.yaml") + "\n" + state + "```").queueAfter(800, TimeUnit.MILLISECONDS);
        });
    }

}
