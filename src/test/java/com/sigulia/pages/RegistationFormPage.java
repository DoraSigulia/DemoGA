package com.sigulia.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistationFormPage {

    // locators
    SelenideElement firstName = $("#firstName"),
                    lastName = $("#lastName"),
                    userEmail = $("#userEmail"),
                    mobileNumber = $("#userNumber"),
                    genter = $("#genterWrapper"),
                    subjects = $("#subjectsInput"),
                    address = $("#currentAddress"),
                    city = $("#city"),
                    state = $("#state"),
                    stateCityWrapper = $("#stateCity-wrapper");



    // actions
    public RegistationFormPage openPage () {
        open("/automation-practice-form");
        return this;
    }


    public RegistationFormPage setFirstName(String value) {
        firstName.setValue(value);
        return this;
    }

    public RegistationFormPage setLastName(String value) {
        lastName.setValue(value);
        return this;
    }

    public RegistationFormPage setUserEmail(String value) {
        userEmail.setValue(value);
        return this;
    }

    public RegistationFormPage setMobileNumber(String value) {
        mobileNumber.setValue(value);
        return this;
    }
    public RegistationFormPage setAddress(String value) {
        address.setValue(value);
        return this;
    }

    public RegistationFormPage setState(String value) {
        state.click();
        stateCityWrapper.$(byText(value)).click();
        return this;
    }

    public RegistationFormPage setCity(String value) {
        city.click();
        stateCityWrapper.$(byText(value)).click();
        return this;
    }

    public RegistationFormPage clickOnSubmitButton() {
        $("#submit").click();
        $(".modal-content").shouldBe(visible);
        return this;
    }

    public RegistationFormPage checkResult(String key, String value) {
        $(".table-responsive").$(byText(key))
                .parent().shouldHave(text(value));
        return this;
    }


}
