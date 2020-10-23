package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.ArrayList;
import java.util.List;

public class FlightsPage extends BasePage {

    public FlightsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "route-cluster")
    private List<WebElement> clustersList;

    private final String innerClasterLocator = "route-cluster__container";

    private final String offerLocator = "result-list-flight-route";

    private final String buttonExpandClusterLocator = "route-cluster__headline-icon";

    public int getClustersNumber() {
        return clustersList.size();
    }

    public List<WebElement> getClustersList(){
        return clustersList;
    }

    public void expandCluster(int i){
        WebElement buttonArrow = clustersList.get(i).findElement(By.className(buttonExpandClusterLocator));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", buttonArrow);
    }

    public String getClusterDisplayValue(int i){
        return clustersList.get(i).findElement(By.className(innerClasterLocator))
                .getCssValue("display");
    }

    public int getOffersNumberPerCluster(int i) {
        List<WebElement> listOfOffers = clustersList.get(i).findElements(By.className(offerLocator));
        return listOfOffers.size();
    }

    public int getOffersNumberTotal(){
        int offersNumber = 0;
        for (WebElement webElement : clustersList) {
            List<WebElement> offersListPerCluster = webElement.findElements(By.className(offerLocator));
            offersNumber = +offersListPerCluster.size();
        }
        return offersNumber;
    }
}
