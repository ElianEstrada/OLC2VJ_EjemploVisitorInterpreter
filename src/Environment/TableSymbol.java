package Environment;
import java.util.HashMap;

public class TableSymbol {

    private HashMap<String, Symbol> SymbolTable;
    private TableSymbol prev;

    public TableSymbol(TableSymbol prev) {
        this.prev = prev;
        SymbolTable = new HashMap<String, Symbol>();
    }

    public boolean addSymbol(String name, Symbol newSymbol) {

        if (SymbolTable.containsKey(name.toLowerCase())) {
            //Add list error
            System.out.println("This variable already exist in current context");
            return false;
        }

        SymbolTable.put(name.toLowerCase(), newSymbol);

        return true;
    }

    public Symbol getSymbol(String name) {

        TableSymbol currentTableSymbol = this;

        while (currentTableSymbol != null) {
            if (currentTableSymbol.getSymbolTable().containsKey(name)) {
                return currentTableSymbol.getSymbolTable().get(name);
            }

            currentTableSymbol = currentTableSymbol.getPrev();
        }
        return null;
    }

    public HashMap<String, Symbol> getSymbolTable() {
        return SymbolTable;
    }

    public void setSymbolTable(HashMap<String, Symbol> symbolTable) {
        SymbolTable = symbolTable;
    }

    public TableSymbol getPrev() {
        return prev;
    }

    public void setPrev(TableSymbol prev) {
        this.prev = prev;
    }

}
