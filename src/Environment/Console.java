package Environment;

public class Console {

    public String console;
    public static Console myConsole;

    private Console() {
        this.console = "";
    }

    public static Console getInstance() {
        if (myConsole == null){
            myConsole = new Console();
        }

        return myConsole;
    }

}
