package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id= 'code'] input");
    private final SelenideElement verifyButton = $("[data-test-id= 'action-verify']");
    private final SelenideElement errorMessage =
            $("[data-test-id= 'error-notification'] .notification__content");

    public VerificationPage() {
        codeField.should(Condition.visible);
    }

    public DashboardPage validVerify(String verificationCode) {
        codeField.setValue(verificationCode);
        verifyButton.click();
        return new DashboardPage();
    }

    public void checkErrorMessage(String expectedText) {
        errorMessage.should(Condition.visible, Duration.ofSeconds(5)).should(Condition.text(expectedText));
    }
}
