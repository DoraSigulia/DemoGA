package com.sigulia.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;


public class GithubPages {

    SelenideElement searchInput = $(".header-search-input"),
                    pagesMenu = $(".wiki-rightbar #wiki-pages-box"),
                    headerPage = $("#repo-content-pjax-container h1"),
                    headerJunitExample = $("#user-content-3-using-junit5-extend-test-class"),
                    buttonMoreInPageMenu = pagesMenu.$(".wiki-more-pages-link button");
    ElementsCollection itemsInPageMenu = pagesMenu.$$(".flex-items-start");
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


    public GithubPages findTextWikiPage(String nameWikiPage){
        buttonMoreInPageMenu.click();
        itemsInPageMenu.findBy(text(nameWikiPage)).click();
        return this;
    }


    public GithubPages visibleJunitExample(String nameWikiPage, String textForCheck){
        headerPage.shouldHave(text(nameWikiPage));
        headerJunitExample.shouldBe(visible);
        headerJunitExample.parent().shouldHave(text(textForCheck));
        headerJunitExample.parent().sibling(0)
                .shouldHave(attribute("class", classJunitExample));
        return this;
    }

}

