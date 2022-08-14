package configuration.file;

public class ConfigFactory {

    private ConfigFactory() {
    }

    public static TOMLConfig getSherlock(String path) {
        return new TOMLConfig(path);
    }

}
