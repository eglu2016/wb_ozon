package pages.wb;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    public SelenideElement searchInput = $x(".//input[@id='searchInput']");
    public SelenideElement applySearchBtn = $x("//button[@id='applySearchBtn']");
}
