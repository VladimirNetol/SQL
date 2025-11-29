package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

@Slf4j
public class LoginPage {
    private final SelenideElement heading = $(byText("Интернет Банк"));
    private final SelenideElement loginField = $("[data-test-id= 'login'] input");
    private final SelenideElement passField = $("[data-test-id= 'password'] input");
    private final SelenideElement loginButton = $("[data-test-id= 'action-login']");

    public LoginPage () {
        heading.should(Condition.visible);
    }

    public void login (DataHelper.AuthInfo authInfo) {
        loginField.setValue(authInfo.getLogin());
        passField.setValue(authInfo.getPassword());
        loginButton.click();
    }

    public VerificationPage validLogin (DataHelper.AuthInfo authInfo) {
        login(authInfo);
        return new VerificationPage();
    }
}
