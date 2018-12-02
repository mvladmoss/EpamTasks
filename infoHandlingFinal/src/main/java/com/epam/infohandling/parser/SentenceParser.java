package com.epam.infohandling.parser;

import com.epam.infohandling.entity.Component;
import com.epam.infohandling.entity.Lexeme;
import com.epam.infohandling.entity.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.epam.infohandling.entity.TypeOfComponent.EXPRESSION;
import static com.epam.infohandling.entity.TypeOfComponent.SENTENCE;
import static com.epam.infohandling.entity.TypeOfComponent.WORD;

public class SentenceParser extends Parser {

    private static final Logger LOGGER = LogManager.getLogger(TextParser.class);

    private static final String SPLIT_ON_LEXEME_REGEXP = "\\s(?=([\\w\\[]))";
    private static final Pattern EXPRESSION_PATTERN = Pattern.compile("(?<=\\[)[\\s\\d+*-/&]*(?=\\])");

    @Override
    public Component parse(String sentence) {
        String[] allLexemes = sentence.split(SPLIT_ON_LEXEME_REGEXP);
        List<String> listLexemes = Arrays.asList(allLexemes);
        LOGGER.info("Sentence was parsed on lexemes");
        return getLexemes(listLexemes);
    }

    private Component getLexemes(List<String> allLexemes){
        Component component = new TextComponent(SENTENCE);
        allLexemes.stream().forEach(o -> component.addComponent(createLexeme(o)));
        return component;
    }

    private Component createLexeme(String lexeme) {
        Matcher matcher = EXPRESSION_PATTERN.matcher(lexeme);
        if (matcher.find()) {
            return new Lexeme(lexeme, EXPRESSION);
        } else{

            return new Lexeme(lexeme, WORD);
        }
    }

}
