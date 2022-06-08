import grammar.GrammarBaseVisitor;
import grammar.GrammarParser;
import Abstract.*;
import Expression.*;
import Instructions.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class Visitor extends GrammarBaseVisitor<Object> {

    @Override
    public Object visitStart(GrammarParser.StartContext ctx) {
        return visit(ctx.listaInstrucciones());
    }

    @Override
    public Object visitListaInstrucciones(GrammarParser.ListaInstruccionesContext ctx) {

        ArrayList<Object> instructions = new ArrayList<>();

        for (ParseTree item: ctx.e){
            instructions.add(visit(item));
        }
        return instructions;
    }

    @Override
    public Object visitBlck(GrammarParser.BlckContext ctx) {
        return visit(ctx.block());
    }

    @Override
    public Object visitDeclar(GrammarParser.DeclarContext ctx) {
        return visit(ctx.declaration());
    }

    @Override
    public Object visitPtr(GrammarParser.PtrContext ctx) {
        return visit(ctx.print());
    }

    @Override
    public Object visitBlock(GrammarParser.BlockContext ctx) {
        return new Block(ctx.bkO.getLine(), ctx.bkO.getCharPositionInLine(), visit(ctx.listaInstrucciones()));
    }

    @Override
    public Object visitDeclaration(GrammarParser.DeclarationContext ctx) {
        return new Declaration(ctx.IDEN().getSymbol().getLine(), ctx.IDEN().getSymbol().getCharPositionInLine(), ctx.IDEN().getText(), (String) visit(ctx.type()), visit(ctx.expr()));
    }

    @Override
    public Object visitPrint(GrammarParser.PrintContext ctx) {
        return new Print(ctx.ins.getLine(), ctx.ins.getCharPositionInLine(), visit(ctx.expr()));
    }

    @Override
    public String visitType(GrammarParser.TypeContext ctx) {
        return ctx.getText();
    }

    @Override
    public Object visitOpExpr(GrammarParser.OpExprContext ctx) {
        return new BinaryArithmetic(visit(ctx.left), visit(ctx.right), ctx.op.getText(), ctx.op.getLine(), ctx.op.getCharPositionInLine());
    }

    @Override
    public Object visitParenExpr(GrammarParser.ParenExprContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Instruction visitAtomExpr(GrammarParser.AtomExprContext ctx) {
        return new Primitive("int", ctx.getText(), ctx.atom.getLine(), ctx.atom.getCharPositionInLine());
    }

    @Override
    public Instruction visitStrExpr(GrammarParser.StrExprContext ctx) {
        return new Primitive("string", ctx.getText().substring(1, ctx.getText().length() - 1), ctx.str.getLine(), ctx.str.getCharPositionInLine());
    }

    @Override
    public Instruction visitIdExpr(GrammarParser.IdExprContext ctx) {
        return new Identifier(ctx.id.getLine(), ctx.id.getCharPositionInLine(), ctx.getText());
    }
}
