package com.epam.infohandling.interpreter.calculator;

import com.epam.infohandling.interpreter.Context;
import com.epam.infohandling.interpreter.MathExpression;
import com.epam.infohandling.interpreter.nonterminal.NonterminalExpressionNumber;
import com.epam.infohandling.interpreter.terminal.TerminalExpressionDivide;
import com.epam.infohandling.interpreter.terminal.TerminalExpressionMinus;
import com.epam.infohandling.interpreter.terminal.TerminalExpressionMultiply;
import com.epam.infohandling.interpreter.terminal.TerminalExpressionPlus;

import java.util.ArrayList;
import java.util.Scanner;

public class Calculator {
    private ArrayList<MathExpression> listExpression;

    private void parse(String expression) {
        for (String lexeme : expression.split("&")) {
            if (lexeme.isEmpty()) {
                continue;
            }
            char temp = lexeme.charAt(0);
            switch (temp) {
                case '+': {
                    listExpression.add(new TerminalExpressionPlus());
                    break;
                }
                case '-': {
                    listExpression.add(new TerminalExpressionMinus());
                    break;
                }
                case '*': {
                    listExpression.add(new TerminalExpressionMultiply());
                    break;
                }
                case '/': {
                    listExpression.add(new TerminalExpressionDivide());
                    break;
                }
                default: {
                    Scanner scan = new Scanner(lexeme);
                    if (scan.hasNextInt()) {
                        listExpression.add(new NonterminalExpressionNumber(scan.nextInt()));
                    }
                }
            }
        }
    }

    public Number calculate(String expression) {
        listExpression = new ArrayList<>();
        parse(expression);
        Context context = new Context();
        for (MathExpression terminal : listExpression) {
            terminal.interpret(context);
        }
        return context.popValue();
    }
}