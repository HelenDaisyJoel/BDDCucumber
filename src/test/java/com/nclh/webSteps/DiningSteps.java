package com.nclh.webSteps;

import com.nclh.managers.AllDriverManager;
import com.nclh.utilities.Context;
import com.nclh.pageObjects.*;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class DiningSteps extends AllDriverManager {

    Context context;
    GuestServicePage guestServicePage;
    FAndBDiningPage fAndBDiningPage;
    HotelMaintenancePage hotelMaintenancePage;
    CommonPage commonPage;

    public DiningSteps(Context context) {
        this.context = context;
        guestServicePage = new GuestServicePage(getDriver());
        fAndBDiningPage = new FAndBDiningPage(getDriver());
        hotelMaintenancePage = new HotelMaintenancePage(getDriver());
        commonPage = new CommonPage(getDriver());
    }

    @Then("User select on table {string}")
    public void user_select_on_table(String number) {
        System.out.println(number);
        context.setContext("tableNumber", number);
        fAndBDiningPage.selectionOfTable(number);
    }

    @And("User click on map Tab")
    public void userClickOnMapTab() {
        fAndBDiningPage.clickOnMapTab();
    }

    @And("User select dining location {string}")
    public void userSelectDiningLocation(String location) {
        fAndBDiningPage.selectLocation(location);
    }

    @And("User type the amount of guests {string}")
    public void userTypeTheAmountOfGuests(String number) {
        fAndBDiningPage.typeAmountOfGuests(number);
    }

    @And("User select the guests")
    public void userSelectTheGuests() {
        fAndBDiningPage.searchGuestUsingAlphabetic();
        fAndBDiningPage.selectGuests();
    }

    @And("User click on save")
    public void userClickOnSave() {
        fAndBDiningPage.clickOnSelect();
    }



    @And("user click on inventory slots")
    public void userClickOnInventorySlots() {
        fAndBDiningPage.clickOnInventorySlot();
    }

    @And("User click on {string} in select table")
    public void userClickOnInSelectTable(String save) {
        fAndBDiningPage.clicOnSaveInSelectTable(save);
    }

    @And("User select table {string}")
    public void userSelectTable(String table) {
        fAndBDiningPage.selectTable(table);
    }

    @Then("User select time from dropdown")
    public void userSelectTimeFromDropdown() {
        fAndBDiningPage.selectTime();

    }

    @And("User click on {string} reservation")
    public void userClickOnReservation(String add) {
        fAndBDiningPage.clickOnAdd(add);
    }

    @And("User click on reservation Tab")
    public void userClickOnReservationTab() {
        fAndBDiningPage.clickOnReservationTab();
    }

    @And("User confirm if guest is already reserved")
    public void userConfirmIfGuestIsAlreadyReserved() {
        fAndBDiningPage.userConfirmPopup();
    }

    @And("User save the guest names")
    public void userSaveTheGuestNames() {
        List<String> names =fAndBDiningPage.getGuestNames();
        Set<String> guestNameHavingOrangeCrown = new TreeSet<>();
        for (int i=0; i<names.size(); i++){
            try{
            guestNameHavingOrangeCrown.add(fAndBDiningPage.getGuestHavingColorCrown(names.get(i)));}
            catch (NullPointerException nse){
                System.out.println(nse.toString());
            }
        }
        System.out.println(guestNameHavingOrangeCrown);
        String guestName = guestNameHavingOrangeCrown.isEmpty() ? null : guestNameHavingOrangeCrown.iterator().next();
        System.out.println(guestName);

        context.setContext("crownGuest",guestName);
    }

    @And("User select location {string} in reservations")
    public void userSelectLocationInReservations(String name) {
        fAndBDiningPage.selectLocationInList(name);
    }

    @And("verify the Reservation")
    public void verifyTheReservation() {
        String name = context.getContext("crownGuest");
        String reservedName= fAndBDiningPage.getGuestName(name);
        Assert.assertEquals(name,reservedName);
    }

    @And("User click on guest")
    public void userClickOnGuest() {
        String name = context.getContext("crownGuest");
        fAndBDiningPage.clickOnGuest(name);
    }

    @Then("verify the reservation is canceled")
    public void verifyTheReservationIsCanceled() {
        String name = context.getContext("crownGuest");
        Assert.assertEquals(fAndBDiningPage.getNumberOfGuests(name), 0);

    }

    @Then("User click on edit icon")
    public void userClickOnEditIcon() {
        fAndBDiningPage.clickOnEdit();
    }


    @And("User get the selected table number")
    public void userGetTheSelectedTableNumber() {
        String tableNumber = fAndBDiningPage.getTableNumber();
        System.out.println(tableNumber);
        context.setContext("selectedTable",tableNumber);
    }

    @And("User get the {string} number from table")
    public void userGetTheNumberFromTable(String columnName) {
        String text = fAndBDiningPage.getTableNumberText(columnName);
        System.out.println(text);
        context.setContext("tableNumber",text);
    }

    @Then("Verify the table number")
    public void verifyTheTableNumber() {
        String selectedTable = context.getContext("selectedTable");
        String reservedTable = context.getContext("tableNumber");
        Assert.assertEquals(selectedTable,reservedTable);
    }

    @Then("User  select the reserved table")
    public void userSelectTheReservedTable() {
        String reserved = context.getContext("selectedTable");
        fAndBDiningPage.selectionOfTable(reserved);
    }

    @And("User click on reservation card")
    public void userClickOnReservationCard() {
        fAndBDiningPage.clickOnReservationCard();
    }

    @And("Verify the status of the table is {string}")
    public void verifyTheStatusOfTheTableIs(String status) {
        String actualStatus = fAndBDiningPage.getReservationStatus();
        Assert.assertEquals(status,actualStatus);
    }

    @And("User select the product dropdown as {string}")
    public void userSelectTheProductDropdownAs(String product) {
        fAndBDiningPage.selectProductDropDown(product);
    }

    @And("User unselect the filters and select {string}")
    public void userUnselectTheFiltersAndSelect(String filterName) {
        fAndBDiningPage.applyFilter(filterName);
    }

    @And("User click on plus button to add guest")
    public void userClickOnPlusButtonToAddGuest() {
        fAndBDiningPage.clickOnAddButton();
    }

    @And("User click the first available slot")
    public void userClickTheFirstAvailableSlot() {
        fAndBDiningPage.clickOnFirstInventorySlot();
    }

    @And("User save the restaurants name")
    public void userSaveTheRestaurantsName() {
//        String hotel = fAndBDiningPage.getRestaurantsName();
//        System.out.println(hotel);
    }

    @And("User click on confirm")
    public void userClickOnConfirm() {
        fAndBDiningPage.clickOnConfirmButton();
    }

    @And("User select the dining  location in reservation as {string}")
    public void userSelectTheDiningLocationInReservationAs(String location) {
        fAndBDiningPage.selectLocationInReservation(location);
    }


    @Then("User select {string} option from  filter")
    public void userSelectOptionFromFilter(String option) {
        fAndBDiningPage.checkOptionInTheFilter(option);

    }

    @And("User Verify that reservation is done for the guest")
    public void userVerifyThatReservationIsDoneForTheGuest() {
        String actualName = fAndBDiningPage.getGuestNameInTable();
        String expectedName = context.getContext("guestName");
        Assert.assertEquals(actualName,expectedName);

    }

    @And("User click on dining")
    public void userClickOnDining() {
        fAndBDiningPage.clickOnDining();
    }

    @And("User type cabin number in filter")
    public void userTypeCabinNumberInFilter() {
        String cabin = context.getContext("CabinNumber");
        fAndBDiningPage.searchLocation(cabin);
    }

    @And("User click On name in the table")
    public void userClickOnNameInTheTable() {
        fAndBDiningPage.clickOnGuestNameInTable();
    }

    @And("User again select the cagney's")
    public void userAgainSelectTheCagneyS() {
        fAndBDiningPage.clickOnLocationSelect();
    }

    @And("User type the last name in search")
    public void userTypeTheLastNameInSearch() {
        String guestFullName = context.getContext("guestName");
        String[] nameParts = guestFullName.split(" ");
        String firstName = nameParts[0];
        String lastName = nameParts[nameParts.length - 1];
        context.setContext("guestLastName", lastName);
        fAndBDiningPage.searchLastName(lastName);
    }

    @And("User navigated to guest profile page")
    public void userNavigatedToGuestProfilePage() {
        String guestName = context.getContext("guestName");
        guestServicePage.navigateToCrewInformation(guestName);

    }

    @And("User open the calender icon on the right panel")
    public void userOpenTheCalenderIconOnTheRightPanel() {
        commonPage.openLinkUsingHref("calendar");
    }

    @And("User get the dining reservation history")
    public void userGetTheDiningReservationHistory() {
        List<String>  DiningList = fAndBDiningPage.getDiningList();
        System.out.println(DiningList);
    }

    @And("User verify that the list has {string}")
    public void userVerifyThatTheListHas(String name) {
        List<String>  DiningList = fAndBDiningPage.getDiningList();
        boolean containsCagneys = DiningList.stream().anyMatch(s -> s.contains("Cagney's"));
        Assert.assertTrue(containsCagneys);

    }

    @And("User click on Cagney hyper link")
    public void userClickOnCagneyHyperLink() {
        fAndBDiningPage.clickOnCagneyLink();
    }

    @And("User click on single file icon on the right pane")
    public void userClickOnSingleFileIconOnTheRightPane() {
        commonPage.openLinkUsingHref("single-file");
    }

    @Then("User click on waiting list")
    public void userClickOnWaitingList() {
        commonPage.openLinkUsingHref("waiting-list");
    }

    @And("User select {string} filter  select option as {string}")
    public void userSelectFilterSelectOptionAs(String filterName, String status) {
        fAndBDiningPage.changeStatusAndFilterDining(filterName,status);
    }

    @And("User close the right panel")
    public void userCloseTheRightPanel() {
        commonPage.openLinkUsingHref("close-12");
    }

    @And("User return the pager")
    public void userReturnThePager() {
        fAndBDiningPage.openPagerSystem();
        commonPage.openLinkUsingHref("menu-dots");
        fAndBDiningPage.clickOnReturnPagers();
    }

    @And("User select the pager")
    public void userSelectThePager() {
        fAndBDiningPage.selectPager();
    }

    @And("User click on table {string} for assign")
    public void userClickOnTableForAssign(String number) {
        fAndBDiningPage.assignTable(number);
    }

    @And("User verify that same table is showing blocked Status")
    public void userVerifyThatSameTableIsShowingBlockedStatus() {
        String table = context.getContext("tableNumber");
        Assert.assertTrue(fAndBDiningPage.checkStatusAsBlocked(table));
        System.out.println("STATUS IS BLOCKED");
    }

    @And("User clicks on Table Join and joins table {string}")
    public void userClicksOnTableJoinAndJoinsTable(String number) {
        fAndBDiningPage.tableJoin(number);
        System.out.println(number);
        context.setContext("JoinedTable", number);
    }

    @And("User Verify that table is joined")
    public void userVerifyThatTableIsJoined() {
        String join = context.getContext("JoinedTable");
        fAndBDiningPage.checkTableIsJoined(join);
        System.out.println("TABLE IS JOINED TO  :"+ join);
    }

    @And("User click on UnJoin")
    public void userClickOnUnJoin() {
        fAndBDiningPage.clickOnUnJoin();
    }

    @And("User type the first name in search")
    public void userTypeTheFirstNameInSearch() {
        String guestFullName = context.getContext("guestName");
        String[] nameParts = guestFullName.split(" ");
        String firstName = nameParts[0];
        String lastName = nameParts[nameParts.length - 1];
        context.setContext("guestFirstName", firstName);
        fAndBDiningPage.searchLastName(firstName);
    }

    @And("User select a guest in dining")
    public void userSelectAGuestInDining() {
        hotelMaintenancePage.searchGuestUsingAlphabeticInDining();
        int number = hotelMaintenancePage.getRandomNumber();
        System.out.println(number);
        hotelMaintenancePage.selectGuests(number);
    }

    @And("User clear reservation status filter of the guest")
    public void userClearReservationStatusFilterOfTheGuest() {
        fAndBDiningPage.clearStatus();
    }

    @And("User double tap on the reservation")
    public void userDoubleTapOnTheReservation() {
        String name = context.getContext("guestFirstName");
        fAndBDiningPage.clickOnReservation(name);
    }

    @And("Verify the status of the table  {string}")
    public void verifyTheStatusOfTheTable(String status) {
        String actualStatus = fAndBDiningPage.getReservationStatusInHouse();
        System.out.println(actualStatus);
        Assert.assertEquals(status,actualStatus);
    }

    @And("User click on {string} if there is previous reservation")
    public void userClickOnIfThereIsPreviousReservation(String text) {
        fAndBDiningPage.clickOnOverRide(text);
    }

    @And("Verify the status of the table is changed to  {string}")
    public void verifyTheStatusOfTheTableIsChangedTo(String status ) {
        String actualStatus = fAndBDiningPage.getReservationStatusOpen();
        System.out.println(actualStatus);
        Assert.assertEquals(status,actualStatus);

    }

    @And("User select the reason for closing table as {string}")
    public void userSelectTheReasonForClosingTableAs(String reason) {
        fAndBDiningPage.selectReasonDropdown(reason);

    }

    @And("User enter the additional comment as {string}")
    public void userEnterTheAdditionalCommentAs(String comment) {
       fAndBDiningPage.typeReasonComment(comment);
    }

    @And("User enter the comment filed for guest reservation as {string}")
    public void userEnterTheCommentFiledForGuestReservationAs(String comment) {
        fAndBDiningPage.typeReservationComment(comment);
    }

    @And("User Verify the comment is {string}")
    public void userVerifyTheCommentIs(String actual) {
        String comment  = fAndBDiningPage.getCommentText();
        Assert.assertEquals(comment,actual);

    }

    @And("Verify the table colour is updated to green")
    public void verifyTheTableColourIsUpdatedToGreen() {
        Assert.assertTrue(fAndBDiningPage.checkForGreen());
    }
}




