package Instructions;

import Abstract.Instruction;
import Environment.*;

public class Print extends Instruction {

    public Object expression;

    public Print(int row, int column, Object expression) {
        super(row, column);
        this.expression = expression;
    }

    @Override
    public ReturnType getValue(TableSymbol table) {
        return null;
    }

    @Override
    public Object interpret(TableSymbol table) {

        ReturnType expr = ((Instruction) this.expression).getValue(table);

        if (expr.value == null) {
            //Semantic error
            return null;
        }

        Console console = Console.getInstance();

        console.console += expr.value.toString() + "\n";

        return null;
    }

}
