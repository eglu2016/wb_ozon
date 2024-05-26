package helpers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByText;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class WebAction {

    public void openWebSite(String url) {
        Selenide.open(url);
    }

    public void setValueElement(SelenideElement element, String value) {
        element.shouldBe(Condition.appear, Duration.ofSeconds(30));
        element.setValue(value);
    }

    public void clickElement(SelenideElement element) {
        element.shouldBe(Condition.appear, Duration.ofSeconds(30));
        element.click();
    }

    public void clickElementByText(String text) {
        $(new ByText(text)).click();
    }

    public void shouldBeVisibleElement(SelenideElement element) {
        element.shouldBe(Condition.visible, Duration.ofSeconds(30));
    }
}
