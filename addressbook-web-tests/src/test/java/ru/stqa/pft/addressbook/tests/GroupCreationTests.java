package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation()  {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test2");
        app.group().create(group);
        app.goTo().groupPage();
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        group.withId(after.stream().max((Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        assertThat(after, equalTo(before.withAdded
                (group.withId(after.stream().mapToInt((g)-> g.getId()).max().getAsInt()))));
    }

}
