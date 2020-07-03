package server.model.account;

import server.controller.ProgramManager;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ManagerTest {
    @Test
    public void managerTest(){
        ProgramManager.getProgramManagerInstance().createAllAccountHashMapForTest();
        Manager manager1 = new Manager("admin","admin123","ali","ali poor","admin@gmail.com","09122000100");
        Manager manager2 = new Manager("admin2","admin1234","hasan","hasan poor","admin2@gmail.com","09121000101");
        ArrayList<Manager> managerArrayList = new ArrayList<>();
        managerArrayList.add(manager1);
        managerArrayList.add(manager2);
        Assert.assertEquals(managerArrayList,Manager.sortManagers(0));
        Assert.assertEquals(managerArrayList,Manager.sortManagers(1));
        Assert.assertEquals(managerArrayList,Manager.sortManagers(2));
        Assert.assertNotEquals(managerArrayList,Manager.sortManagers(3));
        Assert.assertNotEquals(managerArrayList,Manager.sortManagers(4));
        Assert.assertEquals(managerArrayList,Manager.sortManagers(5));
    }
}
