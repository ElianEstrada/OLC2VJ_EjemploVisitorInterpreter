#!/usr/bin/zsh

source /home/elian_estrada/.bash_profile

antlr4 -package grammar -o src/grammar -no-listener -visitor Grammar.g4