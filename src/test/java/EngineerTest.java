//import static org.junit.Assert.*;
//
//import models.Engineer;
//import org.junit.*;
//
//public class EngineerTest {
//    @Rule
//    public DatabaseRule database = new DatabaseRule();
//
//    @Test
//    public void engineer_instantiatesCorrectly_true() {
//        Engineer testEngineer = new Engineer("Michael", 1);
//        assertEquals(true, testEngineer instanceof Engineer);
//    }
//
//    @Test
//    public void getName_engineerInstantiatesWithName_Home() {
//        Engineer testEngineer = new Engineer("Michael", 1);
//        assertEquals("Michael", testEngineer.getName());
//    }
//
//    @Test
//    public void all_returnsAllInstancesOfEngineer_true() {
//        Engineer firstEngineer = new Engineer("Michael", 1);
//        firstEngineer.save();
//        Engineer secondEngineer = new Engineer("Dan", 1);
//        secondEngineer.save();
//        assertEquals(true, Engineer.all().get(0).equals(firstEngineer));
//        assertEquals(true, Engineer.all().get(1).equals(secondEngineer));
//    }
//
//    @Test
//    public void getId_engineerInstantiateWithAnId_1() {
//        Engineer testEngineer = new Engineer("Michael", 1);
//        testEngineer.save();
//        assertTrue(testEngineer.getId() > 0);
//    }
//
//
//    @Test
//    public void equals_returnsTrueIfNamesAreTheSame(){
//        Engineer firstEngineer = new Engineer("Michael", 1);
//        Engineer secondEngineer = new Engineer("Michael", 1);
//        assertTrue(firstEngineer.equals(secondEngineer));
//    }
//
//    @Test
//    public void save_savesIntoDatabase_true(){
//        Engineer myEngineer = new Engineer("Michael", 1);
//        myEngineer.save();
//        assertTrue(Engineer.all().get(0).equals(myEngineer));
//    }
//
//    @Test
//    public void save_assignedToObject(){
//        Engineer myEngineer = new Engineer("Michael", 1);
//        myEngineer.save();
//        Engineer savedEngineer = Engineer.all().get(0);
//        assertEquals(myEngineer.getId(), savedEngineer.getId());
//    }
//
//
//
//}