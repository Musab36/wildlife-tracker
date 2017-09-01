import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class SightingsTest {

	@Rule
    public DatabaseRule database = new DatabaseRule();
    
    // Instantiation Test
	@Test
	public void sightins_instantiateCorrectly_true() {
		Sightings testSightings = new Sightings("Kangaroo", "Location", "Ranger name");
		assertEquals(true, testSightings instanceof Sightings);
	}
    
    // Name attribute Test
	@Test
	public void sightings_instantiatesWithName_String() {
		Sightings testSightings = new Sightings("Kangaroo", "Location", "Ranger name");
		assertEquals("Kangaroo", testSightings.getName());
	}

	// Location attribute
	@Test
	public void getLocation_sightingsInstantiateWithLocation_String() {
		Sightings testSightings = new Sightings("Simba", "Simba", "Ranger name");
		assertEquals("Simba", testSightings.getLocation());
	}

	// Ranger's name attribute
	@Test
	public void getRangerName_sightingsInstantiateWithRangerName_String() {
		Sightings testSightings = new Sightings("Lion", "Location", "Lion");
		assertEquals("Lion", testSightings.getRangerName());
	}

	// Overriding equals Test
	@Test
	public void equals_returnsTrueIfSightingsHaveSameNames_true() {
		Sightings firstSighting = new Sightings("Kangaroo", "Location", "Ranger name");
		Sightings anotherSighting = new Sightings("Kangaroo", "Location", "Ranger name");
		assertTrue(firstSighting.equals(anotherSighting));
	}

	// Saving sightings into the database Test
	@Test
	public void save_savesSightingsIntoTheDatabase_Sightings() {
		Sightings testSightings = new Sightings("Kangaroo", "Location", "Ranger name");
		testSightings.save();
		assertTrue(Sightings.all().get(0).equals(testSightings));
	}

	// Returning all database entries Test
	@Test
	public void all_returnsAllInstancesOfSightins_true() {
		Sightings firstSighting = new Sightings("Kangaroo", "Location", "Ranger name");
		firstSighting.save();
		Sightings secondSighting = new Sightings("Rabbit", "Location", "Ranger name");
		secondSighting.save();
		assertEquals(true, Sightings.all().get(0).equals(firstSighting));
		assertEquals(true, Sightings.all().get(1).equals(secondSighting));
	}

	// Assigning ids Test
	@Test
	public void save_assignsIdsToSightings() {
		Sightings testSightings = new Sightings("Kangaroo", "Location", "Ranger name");
		testSightings.save();
		Sightings savedSightings = Sightings.all().get(0);
		assertEquals(testSightings.getId(), savedSightings.getId());
	}

	// Finding sightings based on thier ids
	@Test
	public void find_returnsSightingsWithId_secondSighting() {
		Sightings firstSighting = new Sightings("Kangaroo", "Location", "Ranger name");
		firstSighting.save();
		Sightings secondSighting = new Sightings("Rabbit", "Location", "Ranger name");
		secondSighting.save();
		assertEquals(Sightings.find(secondSighting.getId()), secondSighting);
	}
}