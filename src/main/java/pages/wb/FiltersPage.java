package pages.wb;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class FiltersPage {

    public SelenideElement filtersBlockContainer = $x(".//div[@class='filters-block__container']");

    public SelenideElement sortByRatingBtn = $x(".//button[contains(@class,'btn--sorter')]");

    public SelenideElement filterList = $x(".//ul[@class='filter__list']");
}
