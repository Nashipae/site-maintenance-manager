import static org.junit.Assert.*;
import org.junit.*;

public class EngineerTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void engineer_instantiatesCorrectly_true() {
        Engineer testEngineer = new Engineer("Michael", emp_no);
        assertEquals(true, testEngineer instanceof Engineer);
    }

    @Test
    public void getName_engineerInstantiatesWithName_Home() {
        Engineer testEngineer = new Engineer("Michael", emp_no);
        assertEquals("Michael", testEngineer.getName());
    }

    @Test
    public void all_returnsAllInstancesOfEngineer_true() {
        Engineer firstEngineer = new Engineer("Michael", emp_no);
        firstEngineer.save();
        Engineer secondEngineer = new Engineer("Dan", emp_no);
        secondEngineer.save();
        assertEquals(true, Engineer.all().get(0).equals(firstEngineer));
        assertEquals(true, Engineer.all().get(1).equals(secondEngineer));
    }

    @Test
    public void getId_engineerInstantiateWithAnId_1() {
        Engineer testEngineer = new Engineer("Michael", emp_no);
        testEngineer.save();
        assertTrue(testEngineer.getId() > 0);
    }


    @Test
    public void equals_returnsTrueIfNamesAreTheSame(){
        Engineer firstEngineer = new Engineer("Michael", emp_no);
        Engineer secondEngineer = new Engineer("Michael", emp_no);
        assertTrue(firstEngineer.equals(secondEngineer));
    }

    @Test
    public void save_savesIntoDatabase_true(){
        Engineer myEngineer = new Engineer("Michael", emp_no);
        myEngineer.save();
        assertTrue(Engineer.all().get(0).equals(myEngineer));
    }

    @Test
    public void save_assignedToObject(){
        Engineer myEngineer = new Engineer("Michael", emp_no);
        myEngineer.save();
        Engineer savedEngineer = Engineer.all().get(0);
        assertEquals(myEngineer.getId(), savedEngineer.getId());
    }



}