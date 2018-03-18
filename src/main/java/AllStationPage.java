import static io.restassured.RestAssured.get;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class AllStationPage extends StartPage {

  @FindBy(how = How.ID, id = "android:id/search_src_text")
  private WebElement getSearchTextBox;

  @AndroidFindBy(uiAutomator = "UiSelector().text(\"8\")")
  private MobileElement getFreebikeNumber;

  @FindBy(
    how = How.XPATH,
    xpath = "//android.widget.HorizontalScrollView[1]/android.widget.LinearLayout[0]/android.app.ActionBar$Tab[1]"
  )
  private WebElement getSearchBox;

  List<Integer> getValueOfFreeBikes;

  public AllStationPage(AndroidDriver driver) {
    super(driver);
  }

  public void getDetailsOfParticularStation() {
    String getResponseAsString =
        get("https://api.citybik.es/v2/networks/belfastbikes-belfast").asString();
    getValueOfFreeBikes =
        JsonPath.with(getResponseAsString)
            .param("name", "City Hall")
            .get("network.stations.findAll{stations->stations.name==name}.free_bikes");
    System.out.println(getValueOfFreeBikes.get(0));
  }

  public void clickSearchButton() {
    WebElement getSearchButton = driver.findElementByAccessibilityId("Search");
    getSearchButton.click();
    waitForPresentsOfElementLocated(By.id("android:id/search_src_text"));
    getSearchTextBox.sendKeys("City Hall");
    clickOnCenterOfScreen();
    Assert.assertEquals(
        getValueOfFreeBikes.get(0),
        getFreebikeNumber.getText(),
        "Number of free bike for City Hall Station is being verified");
  }
}
