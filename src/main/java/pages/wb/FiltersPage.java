package pages.wb;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class FiltersPage {

    public SelenideElement filtersBlockContainer = $x(".//div[@class='filters-block__container']");

    // По популярности (строка фильтров)
    public SelenideElement sortByRatingBtn = $x(".//button[contains(@class,'btn--sorter')]");

    // Категория (строка фильтров)
    public SelenideElement sortCategoryBtn = $x(".//button[text()='Категория']");

    public SelenideElement readyBtn = $x(".//button[text()='Готово']");

    public SelenideElement filterAllBtn = $x(".//*[contains(@class, 'filter__btn--all')]");

    public SelenideElement filterList = $x(".//ul[@class='filter__list']");

    // -------------------------------------------------------------------------------------------------------------
    // Фильтры (Все фильтры)
    // -------------------------------------------------------------------------------------------------------------
    public ElementsCollection filterNameList = $$x(".//*[@class='checkbox-with-text__text']");

    public SelenideElement showButton = $x("//*[text()='Показать']");
}
