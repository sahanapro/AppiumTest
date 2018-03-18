import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class FavoritesPage extends BaseClass {

  @AndroidFindBy(uiAutomator = "UiSelector().description(\"Favorite\")")
  private MobileElement getFavoriteButton;

  @AndroidFindBy(uiAutomator="UiSelector().text(\"City Hall\")")
  private MobileElement getTextFromFrvorites;

  public FavoritesPage(AndroidDriver driver){
    super(driver);
  }

  public void addFavouriteStation(){
    getFavoriteButton.click();
    driver.navigate().back();
    swipeLeftToRight();
    String getStationStringInFavorites=getTextFromFrvorites.getText();
    Assert.assertTrue(getStationStringInFavorites.contains("City Hall"));
  }

}
