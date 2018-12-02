package com.epam.infohandling.parser;

import com.epam.infohandling.entity.Component;
import com.epam.infohandling.entity.TypeOfComponent;
import com.epam.infohandling.entity.Lexeme;
import com.epam.infohandling.entity.TextComponent;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

public class TestParagraphParser {
    private static final String INPUT_STRING = "I'am here? And where are you?";

    @Test
    public void shouldParseParagraphToSentece() {
        //given

        Component sentence = new TextComponent(TypeOfComponent.SENTENCE);
        sentence.addComponent(new Lexeme("I'am", TypeOfComponent.WORD));
        sentence.addComponent(new Lexeme("here",TypeOfComponent.WORD));

        Parser parser = Mockito.mock(Parser.class);
        Component paragraph = new TextComponent(TypeOfComponent.PARAGRAPH);
        paragraph.addComponent(sentence);
        Mockito.when(parser.parse(anyString())).thenReturn(sentence);
        ParagraphParser paragraphParser = new ParagraphParser(parser);
        //when
        Component sentences = paragraphParser.parse(INPUT_STRING);
        //then
        List<Component> actualSentences = sentences.getChildrenComponent();
        Assert.assertEquals(2, actualSentences.size());
        Assert.assertEquals(sentence, actualSentences.get(0));
    }
}
