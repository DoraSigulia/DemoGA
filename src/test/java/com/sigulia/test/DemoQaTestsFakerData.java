package com.sigulia.test;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;


public class DemoQaTestsFakerData {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void openPage() {
        open("https://demoqa.com/automation-practice-form");
    }

    Faker faker = new Faker();

    String first_name = faker.name().firstName(),
            last_name = faker.name().lastName(),
            user_email = faker.internet().emailAddress(),
            mobile_number = faker.phoneNumber().subscriberNumber(10),
            address = faker.address().fullAddress(),
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
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").find(byText("1999")).click();
        $(".react-datepicker__month .react-datepicker__day").click();
        String date_input = $("#dateOfBirthInput").getValue();
        $("#subjectsInput").sendKeys(subjects);
        $(".subjects-auto-complete__option").click();
        $("#hobbiesWrapper .custom-control").click();
        $("#uploadPicture").uploadFromClasspath("cute_cat.jpg");
        $("#currentAddress").setValue(address);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        $("#submit").click();
        $(".modal-content").shouldBe(visible);
        $(byXpath("//td[contains(.,'Student Name')]/following-sibling::td")).shouldHave(text(expectedFullName));
        $(byXpath("//td[contains(.,'Student Name')]/following-sibling::td")).shouldHave(text(expectedFullName));
        $(byXpath("//td[contains(.,'Student Email')]/following-sibling::td")).shouldHave(text(user_email));
        $(byXpath("//td[contains(.,'Gender')]/following-sibling::td")).shouldHave(text(gender));
        $(byXpath("//td[contains(.,'Mobile')]/following-sibling::td")).shouldHave(text(mobile_number));
        $("#closeLargeModal").click();

    }
}
