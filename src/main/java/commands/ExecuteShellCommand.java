package commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecuteShellCommand {

    private static final Logger LOG = LoggerFactory.getLogger(ExecuteShellCommand.class);

    protected ExecuteShellCommand() {
    }

    protected void executeSimpleProcess(String command) {
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    protected String executeProcess(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            process.waitFor();
            return output.toString();

        } catch (IOException e) {
            LOG.error(e.getMessage());
            return "";
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOG.error(e.getMessage());
            return "";
        }
    }

}
