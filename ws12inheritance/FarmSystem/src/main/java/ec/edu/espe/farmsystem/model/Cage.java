package ec.edu.espe.farmsystem.model;

/**
 *
 * @author Mikael Hidalgo, Object Masters, @ESPE
 */
public class Cage {
    private int id ;
    private String description;
    private int type; // 1 coop 2 stable 3 pens 

    private Location location; 

    public Cage(int i, String for_cows, int i0, Location location) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
        return "Cage{" + "id=" + id + ", description=" + description + ", type=" + type + ", location=" + location + '}';
    }

    
    
    
    public class Cage(int id, String description, int type, Location location) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.location = location;
    }

    
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }
   



}