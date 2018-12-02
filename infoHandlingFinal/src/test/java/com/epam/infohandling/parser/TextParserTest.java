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

public class TextParserTest {
    private static final String TEXT = "Paragraph1!\n\tParagraph2.\n\tParagraph3...";
    private static final String TEST_STRING = "Paragraph1!";
    @Test
    public void shouldParserTextToParagraphs() {
        //given
        Component lexeme = new Lexeme(TEST_STRING, TypeOfComponent.WORD);
        Component sentence = new TextComponent(TypeOfComponent.SENTENCE);
        sentence.addComponent(lexeme);
        Component paragraph = new TextComponent(TypeOfComponent.PARAGRAPH);
        paragraph.addComponent(lexeme);

        Parser paragraphParser = Mockito.mock(Parser.class);
        Mockito.when(paragraphParser.parse(anyString())).thenReturn(paragraph);

        TextParser textParser = new TextParser(paragraphParser);
        //when
        Component text = textParser.parse(TEXT);
        List<Component> paragraphs = text.getChildrenComponent();
        //then
        Assert.assertEquals(3, paragraphs.size());
        Assert.assertEquals(paragraph, paragraphs.get(0));
    }
}
