import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Sightings {
	private String name;
	private String location;
	private String rangerName;
	private int id;
    
    // Sightings constructors
	public Sightings(String name, String location, String rangerName) {
		this.name = name;
		this.location = location;
		this.rangerName = rangerName;
	}
    
    // Getter methods for animal names
	public String getName() {
		return name;
	}

	// Getter method for ids
	public int getId() {
		return id;
	}
    
    // Getter method for sightings location
	public String getLocation() {
		return location;
	}
    
    // Getter method for ranger's name
	public String getRangerName() {
		return rangerName;
	}
    
    // Overriding equals
	@Override
	public boolean equals(Object otherSighting) {
		if(!(otherSighting instanceof Sightings)) {
			return false;
		} else {
			Sightings newSightings = (Sightings) otherSighting;
			return this.getName().equals(newSightings.getName()) &&
			this.getRangerName().equals(newSightings.getRangerName());
		}
	}
    
    // Saving sightings into the database
	public void save() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO sightings (name, location, rangerName) VALUES (:name, :location, :rangerName)";
			this.id = (int) con.createQuery(sql, true)
			.addParameter("name", this.name)
			.addParameter("location", this.location)
			.addParameter("rangerName", this.rangerName)
			.executeUpdate()
			.getKey();
		}
	}
    
    // Returning all the entries from the database
	public static List<Sightings> all() {
		String sql = "SELECT * FROM sightings";
		try(Connection con = DB.sql2o.open()) {
			return con.createQuery(sql).executeAndFetch(Sightings.class);
		}
	}

		// Finding sightings based on thier ids
		public static Sightings find(int id) {
			try(Connection con = DB.sql2o.open()) {
				String sql = "SELECT * FROM sightings WHERE id=:id";
				Sightings sightings = con.createQuery(sql)
				.addParameter("id", id)
				.executeAndFetchFirst(Sightings.class);
				return sightings;
			}
		}
}