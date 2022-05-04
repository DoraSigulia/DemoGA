package com.sigulia.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class GithubPages {

    SelenideElement searchInput = $(".header-search-input"),
                    headerPage = $("#repo-content-pjax-container h1"),
                    headerJunitExample = $("#user-content-3-using-junit5-extend-test-class");
    ElementsCollection listWikiPages = $$("#wiki-body .internal.present");
    String classJunitExample = "highlight highlight-source-java position-relative overflow-auto";




    public GithubPages openPage(String nameWebsite){
        open(nameWebsite);
        return this;
    }

    public GithubPages searchNameRepository(String nameRepository){
        searchInput.click();
        searchInput.sendKeys(nameRepository);
        searchInput.submit();
        $(linkText(nameRepository)).click();
        return this;
    }


    public GithubPages clickOnPageInRepository(String namePage){
        $(partialLinkText(namePage)).click();
        return this;
    }

    public GithubPages visibleTextWikiPage(String nameWikiPage){
        listWikiPages.filterBy(text(nameWikiPage)).shouldHave(size(1));
        return this;
    }

    public GithubPages clickOnVisibleTextWikiPage(String nameWikiPage){
        listWikiPages.findBy(Condition.text(nameWikiPage)).click();
        return this;
    }

    public GithubPages visibleJunitExample(){
        headerPage.shouldHave(text("SoftAssertions"));
        headerJunitExample.shouldBe(visible);
        headerJunitExample.parent().sibling(0)
                .shouldHave(attribute("class", classJunitExample));
        return this;
    }


}

