package ru.netology.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static ru.netology.data.SQLHelper.clearAllFieldValues;
import static ru.netology.data.SQLHelper.clearValueAuthCodesField;

public class AuthTest {


    DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
    LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        loginPage = Selenide.open("http://localhost:9999", LoginPage.class);
    }

    @AfterAll
    public static void completeDBCleaning() {
        clearAllFieldValues();
    }

    @AfterEach
    public void onlyAuthCodeCleaning() {
        clearValueAuthCodesField();
    }

    @Test
    public void shouldBeAuthenticated() {
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQLHelper.getVerifyCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    public void shouldCatchLoginAndPasswordError() {
        var authInfo = DataHelper.getRandomUser();
        loginPage.login(authInfo);
        loginPage.checkErrorMessage("Ошибка! Неверно указан логин или пароль");
    }

    @Test
    public void shouldCatchLoginEmptyError() {
        var authInfo = DataHelper.generateRandomPassword();
        loginPage.loginEmpty(authInfo);
        loginPage.checkErrorMessageEmptyField();
    }

    @Test
    public void shouldCatchPassEmptyError() {
        var authInfo = DataHelper.generateRandomLogin();
        loginPage.passEmpty(authInfo);
        loginPage.checkErrorMessageEmptyField();
    }

    @Test
    public void shouldCatchErrorIfVerificationCodeIncorrect() {
        loginPage.login(authInfo);
        VerificationPage.randomVerifyCode();
        VerificationPage.checkErrorMessage("Ошибка! Неверно указан код! Попробуйте ещё раз.");
    }

    @Test
    public void shouldCatchErrorIfLogIncorrectlyThreeTimes() {

        var authInfo = DataHelper.getRandomUser();
        loginPage.clearAllFields();
        loginPage.login(authInfo);
        loginPage.checkErrorMessage("Ошибка! Неверно указан логин или пароль");


        authInfo = DataHelper.getRandomUser();
        loginPage.clearAllFields();
        loginPage.login(authInfo);
        loginPage.checkErrorMessage("Ошибка! Неверно указан логин или пароль");


        authInfo = DataHelper.getRandomUser();
        loginPage.clearAllFields();
        loginPage.login(authInfo);
        loginPage.checkErrorMessage("Ошибка! Неверно указан логин или пароль");

        loginPage.checkErrorMessage("Ошибка! Вы ввели неверный пароль три раза! Пользователь заблокирован");
    }
}
