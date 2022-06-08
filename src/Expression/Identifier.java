package Expression;

import Abstract.Instruction;
import Environment.*;

public class Identifier extends Instruction {

    public String id;
    public String type;
    public Object value;

    public Identifier(int row, int column, String id) {
        super(row, column);
        this.id = id;
        this.type = null;
        this.value = null;
    }

    @Override
    public ReturnType getValue(TableSymbol table) {

        Symbol symbol = table.getSymbol(this.id);

        if (symbol == null){
            System.out.println("No existe la variable en el contexto actual");
            return null;
        }

        this.type = symbol.getType();
        this.value = symbol.getValue();

        return new ReturnType(this.type, this.value);
    }

    @Override
    public Object interpret(TableSymbol table) {
        return null;
    }

}
