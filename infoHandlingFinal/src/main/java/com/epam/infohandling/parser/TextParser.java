package com.epam.infohandling.parser;

import com.epam.infohandling.entity.Component;
import com.epam.infohandling.entity.TypeOfComponent;
import com.epam.infohandling.entity.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class TextParser extends Parser {
    private static final Logger LOGGER = LogManager.getLogger(TextParser.class);
    private static final String SPLIT_ON_PARAGRAPHS_REGEXP = "\t";
    private Parser successor;

    public TextParser(Parser successor){
        this.successor = successor;
    }

    @Override
    public Component parse(String text) {

        String[] masOfParagraphs = text.split(SPLIT_ON_PARAGRAPHS_REGEXP);
        List<String> listParagraphs =Arrays.asList(masOfParagraphs);
        TextComponent textComponent = new TextComponent(TypeOfComponent.TEXT);
        listParagraphs.stream().forEach( o-> {
            Component paragraph = successor.parse(o);
            textComponent.addComponent(paragraph);
        });
        LOGGER.info("Text was parsed on paragraphs");
        return textComponent;
    }

}
