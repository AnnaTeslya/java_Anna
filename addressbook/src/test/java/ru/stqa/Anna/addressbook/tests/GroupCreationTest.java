package ru.stqa.Anna.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.Anna.addressbook.model.GroupDate;
import ru.stqa.Anna.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {

        app.goTo().GroupPage();
        Groups before = app.group().all();
        GroupDate group = new GroupDate().withName("test101");
        app.group().create(group);
        Groups after = app.group().all();
        assertThat(after.size(),equalTo(before.size()+1));
        assertThat(after, equalTo(before
                .withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }


    @Test
    public void testBadGroupCreation() {

        app.goTo().GroupPage();
        Groups before = app.group().all();
        GroupDate group = new GroupDate().withName("test'");
        app.group().create(group);
        assertThat(app.group().count(),equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before));
    }

}
