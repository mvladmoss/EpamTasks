package com.epam.infohandling.interpreter.nonterminal;

import com.epam.infohandling.interpreter.Context;
import com.epam.infohandling.interpreter.MathExpression;

public class NonterminalExpressionNumber implements MathExpression {
    private int number;

    public NonterminalExpressionNumber(int number) {
        this.number = number;
    }

    @Override
    public void interpret(Context c) {
        c.pushValue(number);
    }
}