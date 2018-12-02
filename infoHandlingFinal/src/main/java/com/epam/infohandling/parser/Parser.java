package com.epam.infohandling.parser;
import com.epam.infohandling.entity.Component;

public abstract class Parser {

    private Parser successor;

    abstract Component parse(String text);

    public Parser getSuccessor(){
        return successor;
    }

    public void setSuccessor(Parser successor) {
        this.successor = successor;
    }


}
