import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Animals {
	private String name;
	private int id;
    
    // Animals constructors
	public Animals(String name) {
		this.name = name;

	}
    
    // Getter methods for animal names
	public String getName() {
		return name;
	}

	// Getter method for ids
	public int getId() {
		return id;
	}
    
    // Overriding equals
	@Override
	public boolean equals(Object otherAnimal) {
		if(!(otherAnimal instanceof Animals)) {
			return false;
		} else {
			Animals newAnimals = (Animals) otherAnimal;
			return this.getName().equals(newAnimals.getName());
		}
	}
    
    // Saving animals into the database
	public void save() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO animals (name) VALUES (:name)";
			this.id = (int) con.createQuery(sql, true)
			.addParameter("name", this.name)
			.executeUpdate()
			.getKey();
		}
	}
    
    // Returning all the entries from the database
	public static List<Animals> all() {
		String sql = "SELECT * FROM animals";
		try(Connection con = DB.sql2o.open()) {
			return con.createQuery(sql).executeAndFetch(Animals.class);
		}
	}

		// Finding animals based on thier ids
		public static Animals find(int id) {
			try(Connection con = DB.sql2o.open()) {
				String sql = "SELECT * FROM animals WHERE id=:id";
				Animals animals = con.createQuery(sql)
				.addParameter("id", id)
				.executeAndFetchFirst(Animals.class);
				return animals;
			}
		}
}