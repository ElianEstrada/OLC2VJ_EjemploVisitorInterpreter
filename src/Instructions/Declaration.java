package Instructions;

import Abstract.Instruction;
import Environment.*;
import java.util.List;

import java.util.Objects;
import java.util.Stack;

public class Declaration extends Instruction {

    public String id;
    public String type;
    public Object listDec;

    public Declaration(int row, int column, String id, String type, Object listDec) {
        super(row, column);
        this.id = id;
        this.type = type;
        this.listDec = listDec;
    }
    @Override
    public ReturnType getValue(TableSymbol table) {
        return null;
    }

    @Override
    public Object interpret(TableSymbol table) {

        ReturnType value;
        Symbol symbol;

        if ( ((List<?>) this.listDec).size() == 0 ){
            if (!table.addSymbol(this.id, new Symbol(this.type, 0))){
                return null;
            }
            return null;
        }

        Stack<String> ids = new Stack<>();

        int count = 1;
        for (Object item: ((List<?>) this.listDec)){
            if (count == 1) {
                if (item instanceof String) {
                    ids.push((String) item);

                    if (!table.addSymbol(this.id, new Symbol(this.type, 0))){
                        System.out.println("ERror");
                    }

                } else {
                    value = ((Instruction) item).getValue(table);

                    if (!table.addSymbol(this.id, new Symbol(value.type, value.value))){
                        // error
                        System.out.println("variable ya existe");
                    }
                }
            }else {
                if (item instanceof  String){

                    if(!ids.empty()) {
                        String id = ids.pop();

                        if (!table.addSymbol(id, new Symbol(this.type, 0))){
                            System.out.println("ERror");
                        }
                    }

                    ids.push((String) item);

                } else {
                    String id = ids.pop();
                    value = ((Instruction) item).getValue(table);
                    if (!table.addSymbol(id, new Symbol(value.type, value.value))){
                        //error
                        System.out.println("Variable ya existe");
                    }
                }
            }
            count++;
        }

        if (!ids.empty()){
            String id = ids.pop();

            if (!table.addSymbol(id, new Symbol(this.type, 0))){
                //errr
                System.out.println("Errr");
            }
        }

        //value = ((Instruction)this.value).getValue(table);

        /*if (value.value == null) {
            //Semantic error;
            return null;
        }*/

        /*if (!(this.type.equals("null") || !Objects.equals(value.type, this.type)) || this.type.equals("null")){

            symbol = new Symbol(value.type, value.value);
            if (!table.addSymbol(this.id, symbol)) {
                //Semantic Error
                return null;
            }
        } else {
            //Semantic Error
            return null;
        }*/

        return null;
    }
}
