package pages.wb;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CatalogPage {

    public SelenideElement allCatalog = $x(".//*[@class='catalog-page']");

    // пример элемента: вся карточка (одна на стр-це)
    public ElementsCollection productCardList = $$x(".//article[contains(@class,'product-card')]");

    // пример элемент: Ariel / Капсулы для стирки белья Горный Pодник 15 шт
    public ElementsCollection productCardBrandList = $$x(".//h2[@class='product-card__brand-wrap']");

    // пример элемента: Цена выделенная фиолет. цветом при покупке с кошелка
    public ElementsCollection lowPriceList = $$x(".//ins[contains(@class,'lower-price')]");
}
