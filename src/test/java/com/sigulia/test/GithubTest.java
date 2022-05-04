package com.sigulia.test;
import com.sigulia.pages.GithubPages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class GithubTest {

    String  nameWebsite = "https://github.com/",
            nameRepository = "selenide/selenide",
            namePage = "Wiki",
            nameWikiPage = "SoftAssertions",
            textForCheck = "3. Using JUnit5 extend test class:";

    GithubPages githubTest = new GithubPages();


    @Test
    @DisplayName("Поиск примера для Junit5")
    public void findExampleJunit(){
        githubTest.openPage(nameWebsite)
                .searchNameRepository(nameRepository)
                .clickOnPageInRepository(namePage)
                .findTextWikiPage(nameWikiPage)
                .visibleJunitExample(nameWikiPage, textForCheck);
    }


}
