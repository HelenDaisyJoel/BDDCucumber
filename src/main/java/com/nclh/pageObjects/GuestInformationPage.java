package com.nclh.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class GuestInformationPage {
    private WebDriver webDriver;
    public GuestInformationPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(xpath = "(//otalio-passenger-picture/../following-sibling::div/div)[1]")
    private WebElement crewName;

    @FindBy(xpath = "//otalio-passenger-picture/../following-sibling::div/div/div")
    private List<WebElement> countryAndLanguage;

    @FindBy(xpath = "//otalio-person-board-status/h2/span")
    private List<WebElement> status;

    public List<String> getStatus(){
        List<String> data = new ArrayList<>();
        for(int i=0; i<status.size(); i++){
            data.add(status.get(i).getText());
        }
        return data;
    }

    public List<String> getCountryAndLanguage(){
        List<String> data = new ArrayList<>();
        for(int i=0; i<countryAndLanguage.size(); i++){
            data.add(countryAndLanguage.get(i).getText());
        }
        return data;
    }
    public String getCrewName(){
        return crewName.getText().trim();
    }
}
