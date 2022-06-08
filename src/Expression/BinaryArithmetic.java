package Expression;

import Abstract.Instruction;
import Environment.*;

public class BinaryArithmetic extends Instruction {

    public Object left;
    public Object right;
    public String op;
    public String type;
    public Object value;

    public BinaryArithmetic(Object left, Object right, String op, int row, int column) {
        super(row, column);
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public ReturnType getValue(TableSymbol table) {

        ReturnType left;
        ReturnType right;

        if (this.right.getClass().getName().equals("Identifier")) {
            Symbol exitRight = table.getSymbol(((Identifier)this.right).id);

            if (exitRight == null){
                //Semantic Error
                return null;
            }
        }

        right = ((Instruction) this.right).getValue(table);

        if (right == null) {
            //Semantic Error
            return null;
        }

        if (this.left.getClass().getName().equals("Identifier")) {
            Symbol exitLeft = table.getSymbol(((Identifier) this.left).id);

            if (exitLeft == null) {
                //Semantic Error
                return null;
            }
        }

        left = ((Instruction) this.left).getValue(table);

        if (left == null) {
            //Semantic Error
            return null;
        }

        return switch (this.op) {
            case "+" -> new ReturnType("int", Integer.parseInt(left.value.toString()) + Integer.parseInt(right.value.toString()));
            case "-" -> new ReturnType("int", Integer.parseInt(left.value.toString()) - Integer.parseInt(right.value.toString()));
            default ->
                //Error Semantic
                    null;
        };
    }

    @Override
    public Object interpret(TableSymbol table) {
        return null;
    }

}
