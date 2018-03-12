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

  String getListOfStation;

  public AllStationPage(AndroidDriver driver) {
    super(driver);
  }

  public void getDetailsOfParticularStation() {
    JsonPath jsonPathEvalutor = response.jsonPath();
    getListOfStation = jsonPathEvalutor.get("$..Station[?(@.name=='City Hall')]");
    System.out.println(getListOfStation);
  }

  public void clickSearchButton() {
    WebElement getSearchButton = driver.findElementByAccessibilityId("Search");
    getSearchButton.click();
    waitForPresentsOfElementLocated(By.id("android:id/search_src_text"));
    getSearchTextBox.sendKeys("City Hall");
  }
}
