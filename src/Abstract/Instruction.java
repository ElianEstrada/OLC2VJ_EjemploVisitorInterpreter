package Abstract;

import Environment.ReturnType;
import Environment.TableSymbol;

public abstract class Instruction {

    public int row;
    public int column;

    public Instruction(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public abstract ReturnType getValue(TableSymbol table);
    public abstract Object interpret(TableSymbol table);
}
