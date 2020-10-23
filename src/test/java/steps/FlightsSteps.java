package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.FlightsPage;

import java.util.ArrayList;
import java.util.List;

public class FlightsSteps extends BaseSteps {

    private static FlightsPage flightsPage;

    public FlightsSteps(){
        super();
    }

    @Given("I navigate to Idealo Flights")
    public void navigateToFlights() {
        navigateToMainPage();
        flightsPage = new FlightsPage(driver);
    }

    @Then("I see first 2 states are open and the rest are closed")
    public void checkOpenedClosedClusters(){
        int clustersNumber = flightsPage.getClustersNumber();
        for(int i=0; i<clustersNumber; i++){
            if(i<=1){
                String state = flightsPage.getClusterDisplayValue(i);
                Assert.assertEquals ("block", state);
            }
            else {
                String state = flightsPage.getClusterDisplayValue(i);
                Assert.assertEquals ("none", state);}
        }
    }

    @Then("I see each cluster contain 1-3 offers")
    public void checkIsOffersAmountInRange(){
        int clustersAmount = flightsPage.getClustersNumber();
        for(int i =0; i<clustersAmount; i++){
            int a = flightsPage.getOffersNumberPerCluster(i);
            Assert.assertTrue(a>=1 & a<=3);
        }
    }

    @Then("I see total number of offers is <=30")
    public void checkOffersAmountPageLimit(){
        int offersAmount = flightsPage.getOffersNumberTotal();
        Assert.assertTrue(offersAmount<=30);
    }

    @Then("I click expand button for each element and check state of offers")
    public void checkClustersCanExpand(){
        List<WebElement> clustersList = flightsPage.getClustersList();

        for(int i=0; i<clustersList.size(); i++){

            int numberOfOffers = flightsPage.getOffersNumberPerCluster(i);
            Assert.assertTrue(numberOfOffers>0);

            String stateBeforeClick = flightsPage.getClusterDisplayValue(i);
            if (stateBeforeClick.equals("none")) {
                Assert.assertEquals ("none", stateBeforeClick);
                flightsPage.expandCluster(i);
                String stateAfterClick = flightsPage.getClusterDisplayValue(i);
                Assert.assertEquals ("block", stateAfterClick);
            } else{
                Assert.assertEquals ("block", stateBeforeClick);
                flightsPage.expandCluster(i);
                String stateAfterClick = flightsPage.getClusterDisplayValue(i);
                Assert.assertEquals ("none", stateAfterClick);
            }
        }
    }

    @After
    public void closeBrowser(){
        tearDown();
    }
}
