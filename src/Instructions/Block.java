package Instructions;

import Abstract.Instruction;
import Environment.*;
import java.util.List;

public class Block extends Instruction {

    public Object listInstructions;

    public Block(int row, int column, Object listInstructions) {
        super(row, column);
        this.listInstructions = listInstructions;
    }

    @Override
    public ReturnType getValue(TableSymbol table) {
        return null;
    }

    @Override
    public Object interpret(TableSymbol table) {

        TableSymbol newTableSymbol = new TableSymbol(table);

        for (Object item: (List<?>) listInstructions){
            ((Instruction) item).interpret(newTableSymbol);
        }

        return null;
    }

}
