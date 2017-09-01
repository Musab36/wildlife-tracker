import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class AnimalsTest {

	@Rule
    public DatabaseRule database = new DatabaseRule();
    
    // Instantiation Test
	@Test
	public void animals_instantiateCorrectly_true() {
		Animals testAnimals = new Animals("Kangaroo");
		assertEquals(true, testAnimals instanceof Animals);
	}
    
    // Name attribute Test
	@Test
	public void animals_instantiatesWithName_String() {
		Animals testAnimals = new Animals("Kangaroo");
		assertEquals("Kangaroo", testAnimals.getName());
	}

	// Overriding equals Test
	@Test
	public void equals_returnsTrueIfAnimalsHaveSameNames_true() {
		Animals firstAnimal = new Animals("Kangaroo");
		Animals anotherAnimal = new Animals("Kangaroo");
		assertTrue(firstAnimal.equals(anotherAnimal));
	}

	// Saving animals into the database Test
	@Test
	public void save_savesAnimalsIntoTheDatabase_Animals() {
		Animals testAnimals = new Animals("Kangaroo");
		testAnimals.save();
		assertTrue(Animals.all().get(0).equals(testAnimals));
	}

	// Returning all database entries Test
	@Test
	public void all_returnsAllInstancesOfAnimals_true() {
		Animals firstAnimal = new Animals("Kangaroo");
		firstAnimal.save();
		Animals secondAnimal = new Animals("Rabbit");
		secondAnimal.save();
		assertEquals(true, Animals.all().get(0).equals(firstAnimal));
		assertEquals(true, Animals.all().get(1).equals(secondAnimal));
	}

	// Assigning ids Test
	@Test
	public void save_assignsIdsToAnimals() {
		Animals testAnimals = new Animals("Kangaroo");
		testAnimals.save();
		Animals savedAnimals = Animals.all().get(0);
		assertEquals(testAnimals.getId(), savedAnimals.getId());
	}

	// Finding animals based on thier ids
	@Test
	public void find_returnsAnimalsWithId_secondAnimal() {
		Animals firstAnimal = new Animals("Kangaroo");
		firstAnimal.save();
		Animals secondAnimal = new Animals("Rabbit");
		secondAnimal.save();
		assertEquals(Animals.find(secondAnimal.getId()), secondAnimal);
	}
}