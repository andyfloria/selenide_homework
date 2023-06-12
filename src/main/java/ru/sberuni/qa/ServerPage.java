package ru.sberuni.qa;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class ServerPage {

    public SelenideElement getHeader(String text){
        switchTo().window(1);
        return $(byXpath(".//div[contains(@class, 'infoContent')]/h1")).shouldHave(text(text));
    }
}
