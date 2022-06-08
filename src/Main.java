import Abstract.Instruction;
import Environment.Console;
import Environment.TableSymbol;
import grammar.GrammarLexer;
import grammar.GrammarParser;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        CharStream charStream = CharStreams.fromString("""
                int var1 = 10 + 10;
                print(var1);
                print("hola");
                print(2+2);
                {
                    int var1 = 4;
                    print(var1);
                    {
                        int var1 = 123;
                        print(var1);
                    }
                    print(var1);
                }
                print(var1);
                """);

        GrammarLexer grammarLexer = new GrammarLexer(charStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(grammarLexer);

        GrammarParser grammarParser = new GrammarParser(commonTokenStream);
        GrammarParser.StartContext startContext = grammarParser.start();

        Visitor visitor = new Visitor();
        TableSymbol globalTable = new TableSymbol(null);

        Object treeAst = visitor.visit(startContext);

        for (Object item: (List<?>) treeAst) {
            ((Instruction) item).interpret(globalTable);
        }

        List<String> ruleNames = Arrays.asList(grammarParser.getRuleNames());
        TreeViewer treeViewer = new TreeViewer(ruleNames, startContext);
        treeViewer.open();

        Console console = Console.getInstance();
        System.out.println(console.console);
    }
}