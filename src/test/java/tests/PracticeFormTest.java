package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


// только success test
public class PracticeFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        // я просто скроллил до нужного элемента .scrollTo(), но сайт кривой и на мелком экране внизу баннер закрывает кнопку submit
        // поэтому scrollTo есть в коде
    }

    @Test
    void successFillTest() {
        open("/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));

        $("#firstName").setValue("Alex");
        $("#lastName").setValue("Solo");
        $("#userEmail").setValue("solo@awg.ru");
        $("[for='gender-radio-1']").click(); // я здесь пытался использовать selectRadio(String value), но не получилось. Ещё я не понял какое значение надо ставить
       // $(byText("Male")).click(); либо через текст
        $("#userNumber").setValue("9999999999");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOptionByValue("2000");
        $(".react-datepicker__month-select").selectOptionByValue("9");
        $(".react-datepicker__day.react-datepicker__day--006").click();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $(byText("Reading")).click();
        $(byText("Music")).click();
        File testFile = new File("src/test/resources/testFile.jpg");
        $("#uploadPicture").uploadFile(testFile);
        $("#currentAddress").setValue("City Street House");
        $("#state").scrollTo().click();
        $("#react-select-3-option-2").click(); //либо через текст $(byText("Haryana")).click(); но я хотел уйти от выбора по тексту
        $("#city").click();
        $("#react-select-4-option-0").click();
        $("#submit").scrollTo().click();
        $(".table-responsive").shouldHave(text("Alex"), text("Solo"),
                text("solo@awg.ru"), text("9999999999"), text("06 October,2000"), text("testFile.jpg"),
                text("City Street House"), text("Haryana Karnal"));
    }
}
