package ru.stqa.Anna.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase{
  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().closeAlert();





  }
}