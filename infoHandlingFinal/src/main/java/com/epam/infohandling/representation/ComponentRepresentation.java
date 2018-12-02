package com.epam.infohandling.representation;

import com.epam.infohandling.entity.Component;
import com.epam.infohandling.entity.Lexeme;
import com.epam.infohandling.service.ExpressionConverter;

import java.util.List;

public class ComponentRepresentation {

    private ExpressionConverter converter = new ExpressionConverter();

    public String represent(Component component) {
        String text = buildText(component);
        return (text);
    }

    private String buildText(Component component) {
        StringBuilder stringBuilder = new StringBuilder();
        switch (component.getTypeOfComponent()) {
            case TEXT: {
                List<Component> childComponents = component.getChildrenComponent();
                childComponents.forEach(o -> {
                    stringBuilder.append("\n\t");
                    String paragraph = represent(o);
                    stringBuilder.append(paragraph);
                });
                break;
            }
            case EXPRESSION: {
                Lexeme lexeme = (Lexeme) component;
                String expression = lexeme.getContent();
                String convertExpression = converter.replaceExpressionsByNumber(expression);
                stringBuilder.append(convertExpression + " ");
                break;
            }

            case WORD: {
                Lexeme lexeme = (Lexeme) component;
                String word = lexeme.getContent();
                stringBuilder.append(word + " ");
                break;
            }

            default: {
                List<Component> childComponents = component.getChildrenComponent();
                childComponents.forEach(o -> {
                    String paragraph = represent(o);
                    stringBuilder.append(paragraph);
                });
            }
        }


        return stringBuilder.toString();
    }
}

