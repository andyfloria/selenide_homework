package ru.sberuni.qa.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.sberuni.qa.StartPageE2E4;
import ru.sberuni.qa.goods.Smartphone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class Tests extends Settings {
    @Test
    public void findH1() {

        final String SERVER_FIND_TEXT = "Серверное оборудование под ключ";

        open("https://novosibirsk.e2e4online.ru/", StartPageE2E4.class).openServerPage().getHeader(SERVER_FIND_TEXT);
    }

    @Test
    public void checkItemCount() throws InterruptedException {

        String countItem = "1";

        open("https://novosibirsk.e2e4online.ru/", StartPageE2E4.class).openSmartphonePage().putOnBasket(0).getCountGood(countItem);
    }

    @Test
    public void deleteItem() throws InterruptedException {

        String searchEmptyBasketString = "Ваша корзина пуста";

        open("https://novosibirsk.e2e4online.ru/", StartPageE2E4.class).openSmartphonePage().putOnBasket(0).putOnBasket(1).openBasketPage().deleteItemButton().getTextAboutEmptyBasket(searchEmptyBasketString);
    }

    @Test
    public void sumCountPrice() throws InterruptedException {

        List<String> itemPrice = new ArrayList<>();
        String newItemPriceFromSmartphonePhoneZero;
        String newItemPriceFromSmartphonePhoneOne;

        //Получаем общую сумму добавленных в корзину товаров со страницы корзины
        SelenideElement itemAmout = open("https://novosibirsk.e2e4online.ru/", StartPageE2E4.class).openSmartphonePage().putOnBasket(0).putOnBasket(1).openBasketPage().getAmountOrder();
        String newsumFromitem = itemAmout.getText().replaceAll(" ", "").replaceAll("₽", "");

        back();
        //Получаем суммы товаров со страницы товаров
        SelenideElement itemPriceFromSmartphonePhoneZero = open("https://novosibirsk.e2e4online.ru/catalog/smartfony-4279/", Smartphone.class).getPriceItem(0);
        SelenideElement itemPriceFromSmartphonePhoneOne = open("https://novosibirsk.e2e4online.ru/catalog/smartfony-4279/", Smartphone.class).getPriceItem(1);

        itemPrice.add(newItemPriceFromSmartphonePhoneZero = itemPriceFromSmartphonePhoneZero.getText().replaceAll(" ", "").replaceAll("₽", ""));
        itemPrice.add(newItemPriceFromSmartphonePhoneOne = itemPriceFromSmartphonePhoneOne.getText().replaceAll(" ", "").replaceAll("₽", ""));

        List<Double> itemPriceDouble = itemPrice.stream().map(Double::parseDouble).collect(Collectors.toList());

        double sumFromPageItem = itemPriceDouble.stream().mapToDouble(Double::doubleValue).sum();
        String sumFromPageItemToString = Double.toString(sumFromPageItem);

        Assertions.assertThat(sumFromPageItemToString).contains(newsumFromitem);
    }

    @Test
    public void sumCountGain() throws InterruptedException {

        final int COUNT_ITEM = 2;
        int countFromPage;

        ElementsCollection result = $$(By.xpath("//div[contains(@class, 'subcategory-new-offers__item-block')]"));

        SelenideElement countItemOfOrder = open("https://novosibirsk.e2e4online.ru/", StartPageE2E4.class).openSmartphonePage().putOnBasket(0).putOnBasket(1).openBasketPage().getCountItemFromOrder();
        String newCountItemOfOrder = countItemOfOrder.getText().substring(0, 1);

        countFromPage = Integer.parseInt(newCountItemOfOrder);
        System.out.println((countFromPage));

        Assertions.assertThat(countFromPage).isEqualTo(COUNT_ITEM);
    }

}
