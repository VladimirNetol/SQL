package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class LoginPage {
    private final SelenideElement heading = $(byText("Интернет Банк"));
    private final SelenideElement loginField = $("[data-test-id= 'login'] input");
    private final SelenideElement passField = $("[data-test-id= 'password'] input");
    private final SelenideElement loginButton = $("[data-test-id= 'action-login']");
    private final SelenideElement errorMessage =
            $("[data-test-id='error-notification'] .notification__content");
    private final SelenideElement emptyLoginPassField = $(withText("Поле обязательно для заполнения"));



    public LoginPage() {
        heading.should(Condition.visible, Duration.ofSeconds(20));
    }

    public void login(DataHelper.AuthInfo authInfo) {
        loginField.setValue(authInfo.getLogin());
        passField.setValue(authInfo.getPassword());
        loginButton.click();
    }

    public void loginEmpty(String authInfo) {
        passField.setValue(authInfo);
        loginButton.click();
    }

    public void passEmpty(String authInfo) {
        loginField.setValue(authInfo);
        loginButton.click();
    }

    public VerificationPage validLogin(DataHelper.AuthInfo authInfo) {
        login(authInfo);
        return new VerificationPage();
    }

    public void checkErrorMessage(String expectedText) {
        errorMessage.should(Condition.visible).should(Condition.text(expectedText));
    }

    public void checkErrorMessageEmptyField () {
        emptyLoginPassField.should(Condition.visible);
    }

    public void clearAllFields () {
        loginField.press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        passField.press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }
}
