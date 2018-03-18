import static java.time.Duration.ofSeconds;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
  protected AndroidDriver driver;

  public static Dimension size;

  public BaseClass(AndroidDriver driver) {
    this.driver = driver;
    PageFactory.initElements(new AppiumFieldDecorator(driver, 10, TimeUnit.SECONDS), this);
  }

  public  void scrollTopToBottom() {
    size = driver.manage().window().getSize();
    int starty = (int) (size.height * 0.60);
    int endy = (int) (size.height * 0.20);
    int startx = size.width / 2;
    int timeduration = 3;
    new TouchAction(driver)
        .press(startx, starty)
        .waitAction(ofSeconds(1))
        .moveTo(startx, endy)
        .release()
        .perform();
  }

  public void swipeLeftToRight(){
     int startx=(int) (size.width*0.60);
     int endx=(int)(size.width*0.30);
     int starty=size.height/2;
    new TouchAction(driver)
        .press(startx, starty)
        .waitAction(ofSeconds(1))
        .moveTo(startx, endx)
        .release()
        .perform();
  }

  public void clickOnCenterOfScreen(){
    int xPoint=(int)(size.height*0.30);
    int click =size.width/2;
    new TouchAction(driver).press(xPoint,click);
  }

  public WebElement waitForPresentsOfElementLocated(By elementToBeLocated) {
    WebElement element =
        new WebDriverWait(driver, 100)
            .until(ExpectedConditions.visibilityOfElementLocated(elementToBeLocated));
    return element;
  }
}
