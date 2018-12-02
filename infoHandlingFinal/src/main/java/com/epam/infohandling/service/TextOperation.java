package com.epam.infohandling.service;

import com.epam.infohandling.entity.Component;
import com.epam.infohandling.entity.TypeOfComponent;
import com.epam.infohandling.entity.Lexeme;
import com.epam.infohandling.entity.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TextOperation {
    private static final Logger LOGGER = LogManager.getLogger(TextOperation.class);
    public TextComponent sortParagraphsByNumOFSentences(Component text) {
            TextComponent result = new TextComponent(TypeOfComponent.TEXT);

            if (text == null) {
                LOGGER.error("text can't be null");
                return result;
            }

            List<Component> paragraphs = new ArrayList<>();
            for (int i = 0; i < text.getComponentsSize(); i++) {
                paragraphs.add(text.getComponent(i));
            }
            paragraphs = paragraphs.stream().sorted(Comparator.comparingInt(Component::getComponentsSize)).collect(Collectors.toList());
            for (Component paragraph : paragraphs) {
                result.addComponent(paragraph);
            }

            LOGGER.info("Paragraphs was sorted by number of sentences");

            return result;

        }
    public Component sortByLengthOfWordsText(Component text) {
        Component result = new TextComponent(TypeOfComponent.TEXT);

        if (text == null) {
            LOGGER.error("text can't be null");
            return result;
        }

        for (int i = 0; i < text.getComponentsSize(); i++) {
            Component paragraph = text.getComponent(i);
            TextComponent resultParagraph = new TextComponent(TypeOfComponent.PARAGRAPH);

            for (int j = 0; j < paragraph.getComponentsSize(); j++) {
                Component sentence = paragraph.getComponent(j);
                TextComponent resultSentence = new TextComponent(TypeOfComponent.SENTENCE);
                List<Lexeme> allLexemes = new ArrayList<>();

                for(int k = 0; k < sentence.getComponentsSize(); k++){
                    Lexeme lexeme = (Lexeme) sentence.getComponent(k);
                    repairLexeme(lexeme);
                    allLexemes.add(lexeme);
                }

                allLexemes.sort(Comparator.comparingInt(o -> o.getContent().
                                                               trim().
                                                               length()));
                int sizeOfAllLexemes = allLexemes.size();
                Lexeme endOfSentenceLexeme = allLexemes.get(sizeOfAllLexemes-1);
                String endOfSentenceString = endOfSentenceLexeme.getContent();
                endOfSentenceLexeme.setContent(endOfSentenceString + ".");
                for(Lexeme lexeme : allLexemes){
                    resultSentence.addComponent(lexeme);
                }
                resultParagraph.addComponent(resultSentence);
                }
                result.addComponent(resultParagraph);
            }

        LOGGER.info("Sentences was sorted by length of tokens");

        return result;

    }
    public TextComponent reverseSortLexemesByOrderSymbol(Component text, char searchSymbol){
        TextComponent result = new TextComponent(TypeOfComponent.TEXT);

        if(text == null){
            LOGGER.error("text can't be null");
            return result;
        }


        for (int i = 0; i < text.getComponentsSize(); i++) {
            Component paragraph = text.getComponent(i);
            TextComponent resultParagraph = new TextComponent(TypeOfComponent.PARAGRAPH);

            for (int j = 0; j < paragraph.getComponentsSize(); j++) {
                Component sentence = paragraph.getComponent(j);
                TextComponent resultSentence = new TextComponent(TypeOfComponent.SENTENCE);

                List<Lexeme> allLexemes = new ArrayList<>();
                for (int k = 0; k < sentence.getComponentsSize(); k++) {
                    Lexeme lexeme = (Lexeme) sentence.getComponent(k);
                    repairLexeme(lexeme);
                    allLexemes.add(lexeme);
                }
                allLexemes.sort(Comparator.comparing( o -> ((Lexeme) o).countOfOrderedSymbol(searchSymbol))
                                                                       .thenComparing( (e1, e2) -> ((Lexeme) e2).getContent()
                                                                       .compareToIgnoreCase(((Lexeme) e1).getContent())));
                Collections.reverse(allLexemes);

                int sizeOfAllLexemes = allLexemes.size();
                String endOfSentence = allLexemes.get(sizeOfAllLexemes-1).getContent();
                allLexemes.get(sizeOfAllLexemes-1).setContent(endOfSentence + ".");
                for(Component lexeme : allLexemes){
                    resultSentence.addComponent(lexeme);
                }
                resultParagraph.addComponent(resultSentence);
            }

            result.addComponent(resultParagraph);
        }

        LOGGER.info("Tokens was sorted in reverse order by number of mentioned symbols");

        return result;
    }

    private void repairLexeme(Lexeme lexeme){
        if(lexeme.getContent().equals("  "))
            lexeme.getContent().replace("  "," ");
        if(lexeme.getContent().contains(".")){
            lexeme.setContent(lexeme.getContent().replace(".",""));
        }
        if(lexeme.getContent().contains("\n")){
            lexeme.setContent(lexeme.getContent().replace("\n"," "));
        }
    }


}













