package com.epam.infohandling.service;

import com.epam.infohandling.entity.Component;
import com.epam.infohandling.parser.ParagraphParser;
import com.epam.infohandling.parser.SentenceParser;
import com.epam.infohandling.parser.TextParser;

public class TextComponentCreator {

    public Component create(String text){
        TextParser textParser = new TextParser(new ParagraphParser(new SentenceParser()));
        Component component = textParser.parse(text);
        return component;
    }

}
