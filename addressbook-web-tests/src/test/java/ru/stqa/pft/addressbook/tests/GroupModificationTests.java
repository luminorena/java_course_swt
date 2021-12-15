package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase{
    @Test

    public void testGroupModification(){
        app.getNavigationHelper().gotoGroupPage();
        if (app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("1", null, "1"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("1", "1", "1"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
    }


}
