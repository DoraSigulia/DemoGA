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
        squareAll.get(0).shouldHave(text(nameSquareA));
        squareAll.get(1).shouldHave(text(nameSquareB));
        $(squareA).dragAndDropTo(squareB);
        squareAll.get(1).shouldHave(text(nameSquareA));
        squareAll.get(0).shouldHave(text(nameSquareB));
    }
}
