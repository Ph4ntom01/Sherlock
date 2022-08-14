package configuration.constant;

public enum EFolder {

    RESOURCES("/home/orlando/discordbots/sherlock/resources/"), SCRIPT_SHERLOCK("/home/orlando/docker/traefik/containers/ark-server/");

    private final String path;

    private EFolder(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
