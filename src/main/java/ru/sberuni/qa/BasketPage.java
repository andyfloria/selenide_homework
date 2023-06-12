package ru.sberuni.qa;

import com.codeborne.selenide.SelenideElement;
import org.checkerframework.checker.index.qual.PolyUpperBound;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BasketPage {
    public BasketPage deleteItemButton() {
        $(By.xpath("//button[contains(@class, 'e2e4-uikit-presser_link') and text() = 'Очистить корзину']")).click();
        return this;
    }

    public SelenideElement getTextAboutEmptyBasket(String text) {
        return $(By.xpath("//div[contains(@class, 'cart-empty__header')]")).shouldHave(text(text));
    }

    public SelenideElement getAmountOrder(){
        return $(By.xpath("//span[contains(@class, 'calculator__big')]"));
    }

    public SelenideElement getCountItemFromOrder(){
        return $(By.xpath("//span[contains(@class, 'calculator__text')]"));
    }
}
