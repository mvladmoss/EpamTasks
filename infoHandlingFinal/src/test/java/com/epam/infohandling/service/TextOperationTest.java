package com.epam.infohandling.service;

import com.epam.infohandling.entity.Component;
import com.epam.infohandling.entity.TextComponent;
import com.epam.infohandling.representation.ComponentRepresentation;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TextOperationTest {

    private final static String TEXT = "It has survived - not only (five) centuries, but also the leap into [50&2&+] electronic\n" +
            " typesetting, remaining unchanged. It was popularised in the [8&2&7&4&+&*&-] with the release of Letraset sheets containing\n" +
            " Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of lorem Ipsum.\n" +
            "\tIt is a long established fact that a reader will be distracted by the readable\n" +
            " content of a page when looking at its layout. The point of using\n" +
            " 8 Ipsum is that it has a more-or-less normal\n" +
            " distribution of letters, as opposed to using (Content here), content here', making it look\n" +
            " like readable English.\n" +
            "\tIt is a [2 3 +] established fact that a reader will be of a\n" +
            " page when looking at its layout.\n" +
            "\tBye.";

    private final static String SENTECE = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout";

    private TextComponentCreator creator = new TextComponentCreator();
    private Component componentText = creator.create(TEXT);
    private Component componentSentence = creator.create(SENTECE);
    private TextOperation textOperation = new TextOperation();
    private ComponentRepresentation representation = new ComponentRepresentation();

    @Test(dataProvider = "dataForSortingByNumOfSentences")
    public void sortParagraphsByNumOFSentencesTest(int position, int expected){
        TextComponent sortedText = textOperation.sortParagraphsByNumOFSentences(componentText);
        int actual = sortedText.getComponent(position).getComponentsSize();

        assertEquals(actual, expected);
    }
    @DataProvider(name = "dataForSortingByNumOfSentences")
    public Object[][] dataForSortingByNumOfSentences(){
        return new Object[][]{
                {0, 1},
                {1, 1},
                {2, 2},
                {3, 2}
        };
    }
    @Test
    public void sortSentenceByLengthOfWordsTest(){
        Component sortedComponent = textOperation.sortByLengthOfWordsText(componentSentence);
        String expected = representation.represent(sortedComponent);
        String actual = "\n" +
                "\ta a a It is be by of at the its long fact that will page when reader layout content looking readable distracted established. ";

        assertEquals(actual, expected);
    }

    @Test
    public void reverseSortSentenceLexemesByOrderSymbolTest(){
        TextComponent sortedComponent = textOperation.reverseSortLexemesByOrderSymbol(componentSentence, 'a');
        String expected = representation.represent(sortedComponent);
        String actual = "\n" +
                "\treadable a a a at distracted established fact layout page reader that be by content is It its long looking of the when will. ";

        assertEquals(actual, expected);
    }

}
