package Expression;

import Abstract.Instruction;
import Environment.*;

public class Primitive extends Instruction {

    public String type;
    public String value;

    public Primitive(String type, String value, int row, int column){
        super(row, column);
        this.type = type;
        this.value = value;
    }

    @Override
    public ReturnType getValue(TableSymbol table) {
        return new ReturnType(this.type, this.value);
    }

    @Override
    public Object interpret(TableSymbol table) {
        return null;
    }

}
