import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import helpers.Utils;
import helpers.WebAction;
import helpers.WriteReadFile;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.wb.CatalogPage;
import pages.wb.FiltersPage;
import pages.wb.MainPage;

import java.io.File;
import java.util.Date;
import java.util.List;

import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.fail;

public class WbTest extends BaseTest {
    private final static String BASE_URL = "https://www.wildberries.ru";
    WebAction webAction = new WebAction();
    MainPage mainPage = new MainPage();
    CatalogPage catalogPage = new CatalogPage();
    FiltersPage filtersPage = new FiltersPage();

    @Tag("wb")
    @Test
    public void checkOne() {

        Date currentDate = Utils.getShiftedDate(0, 0, 0);
        String outFileName = "wb_" + Utils.getFormatDateByPattern(currentDate, "yyyy_MM_dd_HH_mm_ss") + ".txt";
        String outFilePath = WriteReadFile.getAbsolutePath("out_files", outFileName);

        step("Формируем имя файла для отчета", ()-> {
            attachment("out_file_name", outFileName);
        });

        String inFilePath = WriteReadFile.getAbsolutePath("in_files", "data.txt");
        File inFile = new File(inFilePath);
        List<String> dataFromFile = WriteReadFile.readFromFile(inFile);

        step("Перейдем по ссылке", ()-> {
            attachment("url", BASE_URL);
            webAction.openWebSite(BASE_URL);
        });

        for (String lineFromFile : dataFromFile) {

            // поисковую строку заполним текстом
            webAction.setValueElement(mainPage.searchInput, lineFromFile);
            System.out.println("------------------------------------------------------");
            System.out.println("-> Поисковая строка из файла = " + lineFromFile);

            WriteReadFile.appendStrToFile(outFilePath, "---> Поисковая строка = " + lineFromFile + "\n");

            webAction.clickElement(mainPage.applySearchBtn);
            Selenide.refresh();
            webAction.shouldBeVisibleElement(filtersPage.filtersBlockContainer);
            webAction.shouldBeVisibleElement(catalogPage.allCatalog);

            // Нажать в строке с фильтрами 'Категория'
            webAction.clickElement(filtersPage.sortCategoryBtn);
            webAction.shouldBeVisibleElement(filtersPage.filterNameList.get(0));

            for (SelenideElement element : filtersPage.filterNameList) {
                lineFromFile = lineFromFile.toLowerCase();
                String fullName = element.getText();
                String name = fullName.replaceAll("[0-9]+", "").toLowerCase();
                name = name.substring(0, name.length() - 1);
                if (lineFromFile.contains(name)) {
                    webAction.clickElement(element);
                    break;
                }
            }
            webAction.clickElement(filtersPage.readyBtn);

            step("Выберим из фильтра: 'По возрастанию цены'", ()-> {
                webAction.clickElement(filtersPage.sortByRatingBtn);
                Selenide.sleep(1000);
                webAction.shouldBeVisibleElement(filtersPage.filterList);
                webAction.clickElementByText("По возрастанию цены");
                Selenide.sleep(1000);
                webAction.shouldBeVisibleElement(catalogPage.allCatalog);
            });

            System.out.println("-> Найдено товаров нв странице = " + catalogPage.productCardList.size());
            for (int i = 0; i < catalogPage.productCardList.size(); i++) {
                String productCardBrandList = catalogPage.productCardBrandList.get(i).getText();
                String line = productCardBrandList + " = " + catalogPage.lowPriceList.get(i).getText();
                line = line.toLowerCase();
                if (i <= 10) {
                    WriteReadFile.appendStrToFile(outFilePath, line + "\n");
                }
            }
        }
    }
}