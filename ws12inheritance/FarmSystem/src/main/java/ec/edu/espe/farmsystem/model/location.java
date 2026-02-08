package ec.edu.espe.farmsystem.model;

/**
 *
 * @author Mikael Hidalgo, Object Masters, @ESPE
 */
public class Location {
    
   private int xCordinate;
   private int yCordinate;

    @Override
    public String toString() {
        return "Location{" + "xCordinate=" + xCordinate + ", yCordinate=" + yCordinate + '}';
    }

   
   
   public Location(int xCordinate, int yCordinate) {
        this.xCordinate = xCordinate;
        this.yCordinate = yCordinate;
    }

   
   
   
   /**
     * @return the xCordinate
     */
    public int getxCordinate() {
        return xCordinate;
    }

    /**
     * @param xCordinate the xCordinate to set
     */
    public void setxCordinate(int xCordinate) {
        this.xCordinate = xCordinate;
    }

    /**
     * @return the yCordinate
     */
    public int getyCordinate() {
        return yCordinate;
    }

    /**
     * @param yCordinate the yCordinate to set
     */
    public void setyCordinate(int yCordinate) {
        this.yCordinate = yCordinate;
    }
   
   
}