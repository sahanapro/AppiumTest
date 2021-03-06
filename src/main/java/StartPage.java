import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class StartPage extends BaseClass {
  String getListOfStation;
  @FindBy(how = How.ID, id = "android:id/alertTitle")
  private WebElement getWelcomeMessage;

  @FindBy(how = How.ID, id = "android:id/button1")
  private WebElement getOkButton;

  @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Choose a network\")")
  private MobileElement getChooseNetweorkText;

  protected static Response response;

  public StartPage(AndroidDriver driver) {
    super(driver);
  }

  public void verifyWelcomeMessage() {
    Assert.assertTrue(getWelcomeMessage.getText().contains("Welcome"));
  }

  public void clickOkButton() {
    Assert.assertTrue(getOkButton.isDisplayed());
    getOkButton.click();
  }

  public void verifyChooseNetwrokTextDisplayed() {
    Assert.assertTrue(getChooseNetweorkText.isDisplayed());
  }

  public void getListOfNetworkAndClickBelfast() {
    Boolean isFound = false;
    List<WebElement> getNetworkList =
        driver.findElements(By.className("android.widget.TwoLineListItem"));
    do {
      for (int i = 1; i < getNetworkList.size(); i++) {
        String getCurrentNetworkText =
            getNetworkList.get(i).findElement(By.className("android.widget.TextView")).getText();
        if (getCurrentNetworkText.contains("Belfast (GB)")) {
          getNetworkList.get(i).click();
          isFound = true;
          break;
        }
      }
      if (!isFound) {
        scrollTopToBottom();
        getNetworkList = driver.findElements(By.className("android.widget.TwoLineListItem"));
      }
    } while (!isFound);
  }

  public void requestBikeDetails() {
    given().when().get("https://api.citybik.es/v2/networks/belfastbikes-belfast").then().assertThat().statusLine("HTTP/1.1 200 OK");
  }

}
