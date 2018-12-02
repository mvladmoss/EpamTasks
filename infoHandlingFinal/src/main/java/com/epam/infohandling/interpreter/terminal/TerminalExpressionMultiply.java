package com.epam.infohandling.interpreter.terminal;

import com.epam.infohandling.interpreter.Context;
import com.epam.infohandling.interpreter.MathExpression;

public class TerminalExpressionMultiply implements MathExpression {
    @Override
    public void interpret(Context c) {
        c.pushValue(c.popValue() * c.popValue());
    }
}