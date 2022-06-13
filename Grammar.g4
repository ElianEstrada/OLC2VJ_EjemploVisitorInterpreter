grammar Grammar;

INT             : [0-9]+;
IDEN            : [_a-zA-Z][a-zA-Z0-9_]*;
COMMENT         : '//'(~[\r\n])* '\r'? '\n' -> skip;
STRING          : '"' (~["\r\n] | '""')+ '"';
WS              : [ \t\r\n]+ -> skip;


start : listaInstrucciones EOF;

listaInstrucciones : e += instruction*
                   ;

instruction : block             #blck
            | declaration       #declar
            | print             #ptr
            ;

block : bkO='{' listaInstrucciones bkC='}'
      ;

declaration : t=type id=IDEN list+=declarationL* ';'  //var1, var2, var3 = expr;
            ;

declarationL : ('=' e=expr | ',' id=IDEN)
             ;

print   : ins='print' '(' expr ')' ';'
        ;

type : 'int'
     | 'string'
     ;

expr :   left = expr op = ('*' | '/') right = expr        #opExpr
       | left = expr op = ('+' | '-') right = expr        #opExpr
       | '(' expr ')'                                     #parenExpr
       | atom=INT                                         #atomExpr
       | str=STRING                                       #strExpr
       | id=IDEN                                          #idExpr
       ;