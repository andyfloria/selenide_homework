package ru.sberuni.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.sberuni.qa.goods.Smartphone;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class StartPageE2E4 {
    public StartPageE2E4 openStartPage() {
        open("https://novosibirsk.e2e4online.ru/");
        return this;
    }

    public ServerPage openServerPage() {
        $(byXpath("//span[contains(@class, 'quick-links__link-text') and text() = 'Серверы']")).click();
        return page(ServerPage.class);
    }

    public BasketPage openBasketPage(){
        $(By.xpath("//div[contains(@class, 'the-header-navigation__counter-button-text') and text() = 'Корзина']")).click();
        return page(BasketPage.class);
    }

    public Smartphone openSmartphonePage(){
        $(byXpath("//div[contains(@class, 'main-popular-category-item__name') and text() = 'Смартфоны']")).shouldBe(Condition.visible).click();
        return page(Smartphone.class);
    }

 /*public SelenideElement findElementPromo() {
     $(byXpath("//a[contains(@class, 'header-user_tools__name') and text() = 'Избранное']"));
        return (SelenideElement) this; }*/
}
