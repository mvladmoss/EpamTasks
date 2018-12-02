package com.epam.infohandling.entity;

import java.util.List;

public interface Component {

    boolean addComponent(Component component);
    Component getComponent(int index);
    int getComponentsSize();
    TypeOfComponent getTypeOfComponent();
    List<Component> getChildrenComponent();
}
