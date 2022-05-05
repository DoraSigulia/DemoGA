package com.sigulia.test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DragAndDropTest {

    String  nameSquareA = "A",
            nameSquareB = "B";
    SelenideElement squareA = $("#column-a"),
                    squareB = $("#column-b");
    ElementsCollection squareAll = $$("#columns div");


    @Test
    public void dragAndDrop(){
        open("https://the-internet.herokuapp.com/drag_and_drop");
        squareA.shouldHave(text(nameSquareA));
        squareB.shouldHave(text(nameSquareB));
        $(squareA).dragAndDropTo(squareB);
        squareA.shouldHave(text(nameSquareB));
        squareB.shouldHave(text(nameSquareA));
    }
}
