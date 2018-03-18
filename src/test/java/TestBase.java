import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestBase {

  protected AndroidDriver driver;
  protected StartPage start;
  protected AllStationPage station;
  protected FavoritesPage favorite;

  @BeforeTest
  public void base() {

    DesiredCapabilities cap = new DesiredCapabilities();
    String udid = "0481e26dd1209760";
    // String udid="emulator-5554";
    // File app = new File(System.getProperty("user.dir")+"\\Resources\\com.vector.guru99.apk");

    cap.setJavascriptEnabled(true);
    cap.setCapability("deviceName", "Nexus 4");
    cap.setCapability("udid", udid);
    cap.setCapability("appPackage", "be.brunoparmentier.openbikesharing.app");
    cap.setCapability(
        "appActivity", "be.brunoparmentier.openbikesharing.app.activities.StationsListActivity");

    /* cap.setCapability(MobileCapabilityType.FULL_RESET,true);
    cap.setCapability(MobileCapabilityType.NO_RESET,false);*/

    try {
      driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), cap);
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  @Test(priority = 1)
  public void clickACityNetwrok() {
    start = new StartPage(driver);
    start.verifyWelcomeMessage();
    start.clickOkButton();
    start.verifyChooseNetwrokTextDisplayed();
    start.requestBikeDetails();
    start.getListOfNetworkAndClickBelfast();
  }

  @Test(priority = 2)
  public void verifyStationName() {
    station = new AllStationPage(driver);
    station.requestBikeDetails();
    station.getDetailsOfParticularStation();
    station.clickSearchButton();

  }

  @Test(priority = 3)
  public void verifyFavorites(){
    favorite=new FavoritesPage(driver);
    favorite.addFavouriteStation();

  }

  @AfterClass
  public void tearDown() {
    if (driver != null) {
      driver.closeApp();
      driver.quit();
    }
  }
}
