import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class EndangeredAnimals {
	private String name;
	private String health;
	private int age;
	private int id;
    
    // Animals constructors
	public EndangeredAnimals(String name, String health, int age) {
		this.name = name;
		this.health = health;
		this.age = age;

	}
    
    // Getter methods for animal names
	public String getName() {
		return name;
	}

	// Getter method for ids
	public int getId() {
		return id;
	}

	public String getHealth() {
		return health;
	}

	public int getAge() {
		return age;
	}

    // Overriding equals
	@Override
  public boolean equals(Object otherEndangeredAnimal){
    if (!(otherEndangeredAnimal instanceof EndangeredAnimals)) {
      return false;
    } else {
      EndangeredAnimals newEndangeredAnimal = (EndangeredAnimals) otherEndangeredAnimal;
      return this.getName().equals(newEndangeredAnimal.getName()) &&
             this.getHealth().equals(newEndangeredAnimal.getHealth());
    }
  }
    
    // Saving animals into the database
	public void save() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO endangered_animals (name, health, age) VALUES (:name, :health, :age)";
			this.id = (int) con.createQuery(sql, true)
			.addParameter("name", this.name)
			.addParameter("health", this.health)
			.addParameter("age", this.age)
			.executeUpdate()
			.getKey();
		}
	}
    
    // Returning all the entries from the database
	public static List<EndangeredAnimals> all() {
		String sql = "SELECT * FROM endangered_animals";
		try(Connection con = DB.sql2o.open()) {
			return con.createQuery(sql).executeAndFetch(EndangeredAnimals.class);
		}
	}

		// Finding animals based on thier ids
		public static EndangeredAnimals find(int id) {
			try(Connection con = DB.sql2o.open()) {
				String sql = "SELECT * FROM endangered_animals WHERE id=:id";
				EndangeredAnimals endangeredAnimals = con.createQuery(sql)
				.addParameter("id", id)
				.executeAndFetchFirst(EndangeredAnimals.class);
				return endangeredAnimals;
			}
		}
}