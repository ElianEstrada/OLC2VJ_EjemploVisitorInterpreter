package Environment;

public class ReturnType {

    public String type;
    public Object value;

    public ReturnType(String type, Object value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return (String) this.value;
    }

}
