package com.sigulia.test;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import com.sigulia.pages.RegistationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.String.format;


public class DemoQaTestsFakerData {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
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

    RegistationFormPage registationFormPage = new RegistationFormPage();


    @Test
    void fillFormsWithObligatoryFields() {
        registationFormPage.openPage()
                .setFirstName(first_name)
                .setLastName(last_name)
                .setUserEmail(user_email)
                .setMobileNumber(mobile_number)
                .setAddress(address);

        $("#genterWrapper").$(byText(gender)).click();
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").find(byText("1999")).click();
        $(".react-datepicker__month .react-datepicker__day").click();
        String date_input = $("#dateOfBirthInput").getValue();
        $("#subjectsInput").sendKeys(subjects);
        $(".subjects-auto-complete__option").click();
        $("#hobbiesWrapper .custom-control").click();
        $("#uploadPicture").uploadFromClasspath("cute_cat.jpg");

        registationFormPage.setState(state)
                .setCity(city)
                .clickOnSubmitButton()
                .checkResult("Student Name", expectedFullName)
                .checkResult("Student Email", user_email)
                .checkResult("Mobile", mobile_number)
                .checkResult("Address", address)
                .checkResult("Gender", gender);
    }
}
