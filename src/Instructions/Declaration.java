package Instructions;

import Abstract.Instruction;
import Environment.*;

import java.util.Objects;

public class Declaration extends Instruction {

    public String id;
    public String type;
    public Object value;

    public Declaration(int row, int column, String id, String type, Object value) {
        super(row, column);
        this.id = id;
        this.type = type;
        this.value = value;
    }
    @Override
    public ReturnType getValue(TableSymbol table) {
        return null;
    }

    @Override
    public Object interpret(TableSymbol table) {

        ReturnType value;
        Symbol symbol;

        if (this.value == null){
            //Semantic Error
            return null;
        }

        value = ((Instruction)this.value).getValue(table);

        if (value.value == null) {
            //Semantic error;
            return null;
        }

        if (!(this.type.equals("null") || !Objects.equals(value.type, this.type)) || this.type.equals("null")){

            symbol = new Symbol(value.type, value.value);
            if (!table.addSymbol(this.id, symbol)) {
                //Semantic Error
                return null;
            }
        } else {
            //Semantic Error
            return null;
        }

        return null;
    }

}
