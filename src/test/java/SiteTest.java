import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;

public class SiteTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void update_updatesSitesName_true() {
        Site mySite = new Site("Oloitoktok", 1);
        mySite.save();
        mySite.update("Kawangware");
        assertEquals("Kawangware", Site.find(mySite.getId()).getName());
    }

    @Test
    public void Site_instantiatesCorrectly_true() {
        Site mySite = new Site("Kangemi", 1);
        assertEquals(true, mySite instanceof Site);
    }

    @Test
    public void Site_instantiatesWithDescription_String() {
        Site mySite = new Site("Kangemi", 1);
        assertEquals("Kangemi", mySite.getName());
    }

    @Test
    public void isDecommissioned_isFalseAfterInstantiation_false() {
        Site mySite = new Site("Kangemi", 1);
        assertEquals(false, mySite.isDecommissioned());
    }

    @Test
    public void getCreatedAt_instantiatesWithCurrentTime_today() {
        Site mySite = new Site("Kangemi", 1);
        assertEquals(LocalDateTime.now().getDayOfWeek(), mySite.getCreatedAt().getDayOfWeek());
    }

    @Test
    public void all_returnsAllInstancesOfSite_true() {
        Site firstSite = new Site("Kawangware", 1);
        firstSite.save();
        Site secondSite = new Site("Kangemi", 2);
        secondSite.save();
        assertEquals(true, Site.all().get(0).equals(firstSite));
        assertEquals(true, Site.all().get(1).equals(secondSite));
    }

    @Test
    public void getId_siteInstantiateWithAnID_1() {
        Site mySite = new Site("kangemi", 1);
        mySite.save();
        assertTrue(mySite.getId()>0);
    }

    @Test
    public void find_returnsSiteWithSameId_secondSite() {
        Site firstSite = new Site("Kawangware", 1);
        firstSite.save();
        Site secondSite = new Site("Kangemi", 1);
        secondSite.save();
        assertEquals(Site.find(secondSite.getId()), secondSite);
    }

    @Test
    public void equals_returnsTrueIfNamesAreTheSame(){
        Site firstSite = new Site("Kangemi", 1);
        Site secondSite = new Site("Langata", 1);
        assertTrue(firstSite.equals(secondSite));
    }

    @Test
    public void save_returnsTrueIfNamesAretheSame() {
        Site mySite = new Site("Langata", 1);
        mySite.save();
        assertTrue(Site.all().get(0).equals(mySite));
    }

    @Test
    public void save_assignsIdToObjects(){
        Site mySite = new Site("Loresho", 1);
        mySite.save();
        Site savedSite = Site.all().get(0);
        assertEquals(mySite.getId(), savedSite.getId());
    }

    @Test
    public void delete_deletesSite_true(){
        Site mySite = new Site("Loresho", 1);
        mySite.save();
        int mySiteId = mySite.getId();
        mySite.delete();
        assertEquals(null, Site.find(mySiteId));
    }
}


