package ru.stqa.Anna.addressbook.appmanager;

import com.sun.javafx.binding.ExpressionHelperBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.Anna.addressbook.model.GroupDate;

import java.util.ArrayList;
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
  public void selectGroup(int index) {wd.findElements(By.name("selected[]")).get(index).click(); }
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

 public void modify(int index, GroupDate group) {
    selectGroup(index);
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    returnGroupPage();
  }
  public void delete(int index) {
   selectGroup(index);
   deleteSelectedGroups();
   returnGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<GroupDate> list() {
    List<GroupDate> groups = new ArrayList<GroupDate>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add( new GroupDate().withId(id).withName(name));
    }
      return groups;
    }
  }

