package configuration.file;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.tuweni.toml.Toml;
import org.apache.tuweni.toml.TomlParseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TOMLConfig {

    private static final Logger LOG = LoggerFactory.getLogger(TOMLConfig.class);

    private TomlParseResult toml;

    public TOMLConfig(String path) {
        try {
            toml = Toml.parse(Paths.get(path));
        } catch (IOException e) {
            toml.errors().forEach(error -> LOG.error(error.toString()));
        }
    }

    public String getString(String key) {
        return toml.getString(key);
    }

}
