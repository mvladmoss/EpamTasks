package com.epam.infohandling.entity;

import java.util.List;
import java.util.Objects;

public class Lexeme implements Component {

    private String content;

    public void setContent(String lexeme) {
        this.content = lexeme;
    }
    TypeOfComponent type;

    public Lexeme(String lexeme,TypeOfComponent type) {
        this.content = lexeme;
        this.type = type;
    }

    public int countOfOrderedSymbol(char orderSymbol){
        int count = 0;
        for(int i = 0; i < content.length(); i++){
            if(content.charAt(i)==orderSymbol){
                count++;
            }
        }
        return count;
    }

    public String getContent(){
        return  content;
    }

    @Override
    public List<Component> getChildrenComponent() {
        throw new UnsupportedOperationException("That method is not for leaf object");
    }

    @Override
    public boolean addComponent(Component component) {
        return false;
    }
    @Override
    public Component getComponent(int index) {
        throw new UnsupportedOperationException("That method is not for leaf object");
    }
    @Override
    public int getComponentsSize() {
        throw new UnsupportedOperationException("That method is not for leaf object");
    }
    @Override
    public TypeOfComponent getTypeOfComponent() {
        return type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lexeme)) return false;
        Lexeme that = (Lexeme) o;
        return Objects.equals(content, that.content);
    }
    @Override
    public int hashCode() {
        return content.hashCode();//CORRECT
    }
    @Override
    public String toString() {
        return content;
    }

}
