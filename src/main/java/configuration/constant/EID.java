package configuration.constant;

public enum EID {

    BOT_ID("866371868223209472"), OWNER_ID("500688503617749023");

    private final String id;

    private EID(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
