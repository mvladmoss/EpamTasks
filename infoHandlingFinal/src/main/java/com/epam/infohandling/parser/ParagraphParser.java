package com.epam.infohandling.parser;

import com.epam.infohandling.entity.Component;
import com.epam.infohandling.entity.TypeOfComponent;
import com.epam.infohandling.entity.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class ParagraphParser extends Parser {
    private static final Logger LOGGER = LogManager.getLogger(ParagraphParser.class);
    private static final String SPLIT_ON_SENTENCES_REGEXP = "(?<=[.{1}!?\t])\\s";
    private Parser successor;

    public ParagraphParser(Parser successor){
        this.successor = successor;
    }

    @Override
    public Component parse(String paragraph) {
        List<String> sentences = getSentences(paragraph);
        Component component = new TextComponent(TypeOfComponent.PARAGRAPH);
        sentences.stream().forEach( o -> {
            Component sentence = successor.parse(o);
            component.addComponent(sentence);
        });

        LOGGER.info("Paragraph was parsed on sentences");

        return component;
    }

    private List<String> getSentences(String paragraph){
        String[] sentencesArray = paragraph.split(SPLIT_ON_SENTENCES_REGEXP);
        List<String> allSentences = Arrays.asList(sentencesArray);
        return allSentences;
    }

}
