import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import java.util.List;
import org.sql2o.*;

public class EngineerTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void engineer_instantiatesCorrectly_true() {
        Engineer testEngineer = new Engineer("Michael");
        assertEquals(true, testEngineer instanceof Engineer);
    }

    @Test
    public void getName_engineerInstantiatesWithName_Home() {
        Engineer testEngineer = new Engineer("Michael");
        assertEquals("Michael", testEngineer.getName());
    }

    @Test
    public void all_returnsAllInstancesOfEngineer_true() {
        Engineer firstEngineer = new Engineer("Michael");
        firstEngineer.save();
        Engineer secondEngineer = new Engineer("Dan");
        secondEngineer.save();
        assertEquals(true, Engineer.all().get(0).equals(firstEngineer));
        assertEquals(true, Engineer.all().get(1).equals(secondEngineer));
    }

    @Test
    public void getId_engineerInstantiateWithAnId_1() {
        Engineer testEngineer = new Engineer("Michael");
        testEngineer.save();
        assertTrue(testEngineer.getId() > 0);
    }


    @Test
    public void equals_returnsTrueIfNamesAreTheSame(){
        Engineer firstEngineer = new Engineer("Michael");
        Engineer secondEngineer = new Engineer("Michael");
        assertTrue(firstEngineer.equals(secondEngineer));
    }

    @Test
    public void save_savesIntoDatabase_true(){
        Engineer myEngineer = new Engineer("Michael");
        myEngineer.save();
        assertTrue(Engineer.all().get(0).equals(myEngineer));
    }

    @Test
    public void save_assignedToObject(){
        Engineer myEngineer = new Engineer("Michael");
        myEngineer.save();
        Engineer savedEngineer = Engineer.all().get(0);
        assertEquals(myEngineer.getId(), savedEngineer.getId());
    }



}