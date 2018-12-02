package com.epam.infohandling.parser;

import com.epam.infohandling.entity.Component;
import com.epam.infohandling.entity.TypeOfComponent;
import com.epam.infohandling.entity.Lexeme;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestSentenceParser {
    private static final String SENTENCE = "This is the test sentence";
    private static final Component FIRST_LEXEME = new Lexeme("This", TypeOfComponent.WORD);
    private static final Component SECOND_LEXEME = new Lexeme("is", TypeOfComponent.WORD);
    private static final Component THIRD_LEXEME = new Lexeme("the", TypeOfComponent.WORD);
    private static final Component FOURH_LEXEME = new Lexeme("test", TypeOfComponent.WORD);
    private static final Component FIFTH_LEXEME = new Lexeme("sentence", TypeOfComponent.WORD);

    @Test
    public void shouldParseSentencesToWord() {
        //given
        Parser parser = new SentenceParser();
        //when
        Component component = parser.parse(SENTENCE);
        //then
        List<Component> words = component.getChildrenComponent();
        Assert.assertEquals(5, words.size());
        Assert.assertEquals(FIRST_LEXEME, words.get(0));
        Assert.assertEquals(SECOND_LEXEME, words.get(1));
        Assert.assertEquals(THIRD_LEXEME, words.get(2));
        Assert.assertEquals(FOURH_LEXEME, words.get(3));
        Assert.assertEquals(FIFTH_LEXEME, words.get(4));
    }
}
