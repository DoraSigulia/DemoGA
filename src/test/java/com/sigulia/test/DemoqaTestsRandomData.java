package com.sigulia.test;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.sigulia.utils.GenerateRandomData.generatedString;
import static com.sigulia.utils.GenerateRandomData.generatedInt;
import static java.lang.String.format;


public class DemoqaTestsRandomData {


    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void openPage() {
        open("https://demoqa.com/automation-practice-form");
    }


    String first_name = generatedString(),
            last_name = generatedString(),
            user_email = generatedString() + "@gmail.com",
            mobile_number = generatedInt(),
            gender = "Other",
            subjects = "English",
            state = "NCR",
            city = "Delhi";
    String expectedFullName = format("" + first_name + " " + last_name + "");

    @Test
    void submitWithoutValue() {
        $("#submit").click();
        $(".modal-content").shouldNotBe(visible);
    }

    @Test
    void fillFormsWithObligatoryFields() {
        $("#firstName").setValue(first_name);
        $("#lastName").setValue(last_name);
        $("#userEmail").setValue(user_email);
        $("#userNumber").setValue(mobile_number);
        $("#genterWrapper").$(byText(gender)).click();
        //String gender = $("#genterWrapper .custom-control-label").getText();
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").find(byText("1999")).click();
        $(".react-datepicker__month .react-datepicker__day").click();
        String date_input = $("#dateOfBirthInput").getValue();
        $("#subjectsInput").sendKeys(subjects);
        $(".subjects-auto-complete__option").click();
        $("#hobbiesWrapper .custom-control").click();
        $("#uploadPicture").uploadFromClasspath("cute_cat.jpg");
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        $("#submit").click();
        $(".modal-content").shouldBe(visible);
        $(byXpath("//td[contains(.,'Student Name')]/following-sibling::td")).shouldHave(text(expectedFullName));
        $(byXpath("//td[contains(.,'Student Email')]/following-sibling::td")).shouldHave(text(user_email));
        $(byXpath("//td[contains(.,'Gender')]/following-sibling::td")).shouldHave(text(gender));
        $(byXpath("//td[contains(.,'Mobile')]/following-sibling::td")).shouldHave(text(mobile_number));
        $("#closeLargeModal").click();

    }


}
