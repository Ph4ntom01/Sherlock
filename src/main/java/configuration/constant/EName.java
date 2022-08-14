package configuration.constant;

public enum EName {

    SHERLOCK("sherlock"), SHERLOCK_SH("sherlock.sh");

    private final String name;

    private EName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
