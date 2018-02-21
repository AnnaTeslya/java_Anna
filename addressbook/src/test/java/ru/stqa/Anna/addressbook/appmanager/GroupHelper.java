package ru.stqa.Anna.addressbook.appmanager;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ru.stqa.Anna.addressbook.model.GroupDate;
import ru.stqa.Anna.addressbook.model.Groups;



import java.util.List;


public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returnGroupPage() {
    click(By.linkText("group page"));
  }
  public void submitGroupCreation() {
    click(By.name("submit"));
  }
  public void initGroupCreation() {
    click(By.name("new"));
  }
  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }


  public void selectGroupById(int id) {wd.findElement(By.cssSelector("input[value ='" + id + "']")).click(); }
  public void initGroupModification() {
    click(By.name("edit"));
  }
  public void submitGroupModification() {
    click(By.name("update"));
  }
  public void fillGroupForm(GroupDate groupDate) {
    type(By.name("group_name"), groupDate.getName());
    type(By.name("group_header"), groupDate.getHeader());
    type(By.name("group_footer"), groupDate.getFooter());

  }

  public void create(GroupDate group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnGroupPage();
  }

 public void modify(GroupDate group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    returnGroupPage();
  }


  public void delete(GroupDate group) {
    selectGroupById(group.getId());
    deleteSelectedGroups();
    returnGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }


  public Groups all() {
    Groups groups = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add( new GroupDate().withId(id).withName(name));
    }
    return groups;
  }


}

