package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    LocalDate today = LocalDate.now();
    LocalDate newDate = today.plusDays(3);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @BeforeEach
    void Setup() {
        open("http://localhost:9999");
    }

    @Test
    void SubmitRequest() {
        SelenideElement form = $(".form");
        form.$("[placeholder='Город']").setValue("Тамбов");
        form.$("[placeholder='Дата встречи']").doubleClick().sendKeys(formatter.format(newDate));
        form.$("[name=name]").setValue("Иван Иванов");
        form.$("[name=phone]").setValue("+79000000000");
        form.$(".checkbox__box").click();
        $$(".button__content").find(exactText("Забронировать")).click();
        $(withText("Успешно")).waitUntil(visible, 15000);
    }

    @Test
    void SubmitListedRequest() {
        SelenideElement form = $(".form");
        form.$("[placeholder='Город']").setValue("Тамб");
        $$(" .menu-item__control").last().click();
        form.$("[placeholder='Дата встречи']").doubleClick().sendKeys(formatter.format(newDate));
        form.$("[name=name]").setValue("Иван Иванов");
        form.$("[name=phone]").setValue("+79000000000");
        form.$(".checkbox__box").click();
        $$(".button__content").find(exactText("Забронировать")).click();
        $(withText("Успешно")).waitUntil(visible, 15000);
    }
}
