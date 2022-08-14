package configuration.file;

import configuration.constant.EFolder;

public class ConfigFactory {

    private static final String PATH = EFolder.RESOURCES.getPath();

    private ConfigFactory() {}

    public static TOMLConfig getSherlock() {
        return new TOMLConfig(PATH + "sherlock.toml");
    }

}
