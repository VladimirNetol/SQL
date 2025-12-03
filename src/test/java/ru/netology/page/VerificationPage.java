package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private static final SelenideElement codeField = $("[data-test-id= 'code'] input");
    private static final SelenideElement verifyButton = $("[data-test-id= 'action-verify']");
    private static final SelenideElement errorMessage =
            $("[data-test-id= 'error-notification'] .notification__content");

    public VerificationPage() {
        codeField.should(Condition.visible);
    }

    public DashboardPage validVerify(String verificationCode) {
        codeField.setValue(verificationCode);
        verifyButton.click();
        return new DashboardPage();
    }

    public static void randomVerifyCode() {
        var code = DataHelper.generateVerifyCode();
        codeField.setValue(String.valueOf(code));
        verifyButton.click();
    }

    public static void checkErrorMessage(String expectedText) {
        errorMessage.should(Condition.visible).should(Condition.text(expectedText));
    }
}
