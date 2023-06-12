package ru.sberuni.qa.goods;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.sberuni.qa.BasketPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Smartphone {
    private ElementsCollection getListGoods() throws InterruptedException {
        Thread.sleep(5000);
        return $$(By.xpath("//div[contains(@class, 'subcategory-new-offers__item-block')]"));
    }

    public Smartphone putOnBasket(int index) throws InterruptedException {
        getListGoods().get(index).find(By.xpath(".//span[contains(@class, 'cart-button__text') and text() = 'В корзину']")).click();
        return this;
    }

    public Smartphone getCountGood(String count) {
        $(By.xpath("//span[contains(@class, 'counter-button__counter')]")).shouldHave(text(count));
        return this;
    }

    public BasketPage openBasketPage() {
        $(By.xpath("//div[contains(@class, 'the-header-navigation__counter-button-text') and text() = 'Корзина']")).click();
        return page(BasketPage.class);
    }

    public SelenideElement getPriceItem(int index) throws InterruptedException {
        return getListGoods().get(index).find(By.xpath(".//div[contains(@class, 'price-block__price')]/span"));
    }
}

