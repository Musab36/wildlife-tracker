import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class EndangeredAnimalsTest {

	@Rule
    public DatabaseRule database = new DatabaseRule();
    
    // Instantiation Test
	@Test
	public void EndangeredAnimals_instantiateCorrectly_true() {
		EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("Elephants", "Rahino", 10);
		assertEquals(true, testEndangeredAnimals instanceof EndangeredAnimals);
	}
    
    // Name attribute Test
	@Test
	public void EndangeredAnimals_instantiatesWithName_String() {
		EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("Elephants", "Rahino", 10);
		assertEquals("Elephants", testEndangeredAnimals.getName());
	}

	// health attribute test
	@Test
	public void getHealth_endangeredAnimalsInstantiateWithHealth_String() {
		EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("Elephants", "Elephants", 10);
		assertEquals("Elephants", testEndangeredAnimals.getHealth());
	}

	// age attribute
	@Test
	public void getAge_endangeredAnimalsInstantiateWithAge_int() {
		EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("Elephants", "Rahino", 10);
		assertEquals(10, testEndangeredAnimals.getAge());
	}

    
	// Overriding equals Test
	@Test
	public void equals_returnsTrueIfEndangeredAnimalsHaveSameNames_true() {
		EndangeredAnimals firstEndangeredAnimal = new EndangeredAnimals("Elephants", "Elephants", 10);
		EndangeredAnimals anotherEndangeredAnimal = new EndangeredAnimals("Elephants", "Elephants", 10);
		assertTrue(firstEndangeredAnimal.equals(anotherEndangeredAnimal));
	}


	// Saving animals into the database Test
	@Test
	public void save_savesEndangeredAnimalsIntoTheDatabase_Animals() {
		EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("Elephants", "Rahino", 10);
		testEndangeredAnimals.save();
		assertTrue(EndangeredAnimals.all().get(0).equals(testEndangeredAnimals));
	}

	// Returning all database entries Test
	@Test
	public void all_returnsAllInstancesOfEndangeredAnimals_true() {
		EndangeredAnimals firstEndangeredAnimal = new EndangeredAnimals("Elephants", "Rahino", 10);
		firstEndangeredAnimal.save();
		EndangeredAnimals secondEndangeredAnimal = new EndangeredAnimals("Rahino", "Crocks", 10);
		secondEndangeredAnimal.save();
		assertEquals(true, EndangeredAnimals.all().get(0).equals(firstEndangeredAnimal));
		assertEquals(true, EndangeredAnimals.all().get(1).equals(secondEndangeredAnimal));
	}

	// Assigning ids Test
	@Test
	public void save_assignsIdsToEndangeredAnimals() {
		EndangeredAnimals testEndangeredAnimals = new EndangeredAnimals("Elephants", "Rahino", 10);
		testEndangeredAnimals.save();
		EndangeredAnimals savedEndangeredAnimal = EndangeredAnimals.all().get(0);
		assertEquals(testEndangeredAnimals.getId(), savedEndangeredAnimal.getId());
	}

	// Finding animals based on thier ids
	@Test
	public void find_returnsEndangereAnimalsWithId_secondEndangeredAnimal() {
		EndangeredAnimals firstEndangeredAnimal = new EndangeredAnimals("Elephants", "Lions", 10);
		firstEndangeredAnimal.save();
		EndangeredAnimals secondEndangeredAnimal = new EndangeredAnimals("Rahino", "Tiger", 10);
		secondEndangeredAnimal.save();
		assertEquals(EndangeredAnimals.find(secondEndangeredAnimal.getId()), secondEndangeredAnimal);
	}
}