package sherlock;

public class Startup {

    public static void main(String[] args) {
        Discord bot = new Discord(args[0]);
        bot.connect();
    }

}
