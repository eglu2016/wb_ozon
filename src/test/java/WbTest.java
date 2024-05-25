import com.codeborne.selenide.Selenide;
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
        String outFileName = "wb_" + Utils.getFormatDateByPattern(currentDate, "yyyy_MM_dd_hh_mm_ss") + ".txt";
        String outFilePath = WriteReadFile.getAbsolutePath("out_files", outFileName);

        String inFilePath = WriteReadFile.getAbsolutePath("in_files", "data.txt");
        System.out.println("inFilePath = " + inFilePath);
        File inFile = new File(inFilePath);
        List<String> dataFromFile = WriteReadFile.readFromFile(inFile);

        webAction.openWebSite(BASE_URL);

        for (String lineFromFile : dataFromFile) {
            webAction.setValueElement(mainPage.searchInput, lineFromFile);
            System.out.println("------------------------------------------------------");
            System.out.println("-> Поисковая строка из файла = " + lineFromFile);
            webAction.clickElement(mainPage.applySearchBtn);
            Selenide.refresh();
            webAction.shouldBeVisibleElement(filtersPage.filtersBlockContainer);
            webAction.shouldBeVisibleElement(catalogPage.allCatalog);
            webAction.clickElement(filtersPage.sortByRatingBtn);
            Selenide.sleep(1000);
            webAction.shouldBeVisibleElement(filtersPage.filterList);
            webAction.clickElementByText("По возрастанию цены");
            Selenide.sleep(1000);
            webAction.shouldBeVisibleElement(catalogPage.allCatalog);

            System.out.println("-> Найдено товаров нв странице = " + catalogPage.productCardList.size());
            for (int i = 0; i < catalogPage.productCardList.size(); i++) {
                String productCardBrandList = catalogPage.productCardBrandList.get(i).getText();
                String line = catalogPage.productCardBrandList.get(i).getText() +
                        " = " + catalogPage.lowPriceList.get(i).getText();
                if (i <= 10) {
                    WriteReadFile.appendStrToFile(outFilePath, line + "\n");
                }
            }
        }
    }
}