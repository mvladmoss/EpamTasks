package com.epam.infohandling.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextComponent implements Component {
    private static final String TO_STRING_MESSAGE = "\n\t";
    private List<Component> components = new ArrayList<>();
    private TypeOfComponent type;

    public TextComponent(TypeOfComponent type) {
        this.type = type;
    }

    @Override
    public List<Component> getChildrenComponent() {
        return components;
    }

    @Override
    public TypeOfComponent getTypeOfComponent() {
        return type;
    }
    @Override
    public com.epam.infohandling.entity.Component getComponent(int index) {
        return components.get(index);
    }
    @Override
    public int getComponentsSize() {
        return components.size();
    }

    @Override
    public boolean addComponent(Component textTextComponent) {
        return components.add(textTextComponent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Component)) return false;
        TextComponent that = (TextComponent) o;
        return Objects.equals(components, that.components) &&
                type == that.type;
    }
    @Override
    public int hashCode() {
        int hashCode = type.hashCode();
        for(Component component : components){
            hashCode += component.hashCode() * 31;
        }

        return hashCode;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getTypeOfComponent() + ":");
        for(Component paragraph : components){
            stringBuilder.append(TO_STRING_MESSAGE);
            stringBuilder.append(paragraph);
        }
        return stringBuilder.toString();
    }

}
