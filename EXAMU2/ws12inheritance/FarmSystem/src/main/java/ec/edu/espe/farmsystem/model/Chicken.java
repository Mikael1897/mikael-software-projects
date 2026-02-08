package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 *
 * @author Mikael Hidalgo, Object Masters, @ESPE
 */
public class Chicken extends FarmAnimal {
    private boolean isMolting;
    private int laidEggs;

    public class Chicken(boolean b, int i, int i0, String breed, Date bornOn, String gender, boolean ableToReproduce, float weight, Cage cage) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String toString() {
        return "Chicken{" + "isMolting=" + isMolting + ", laidEggs=" + laidEggs +","+ super.toString()+'}';
    }

    
    
    public Chicken(boolean isMolting, int laidEggs, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.isMolting = isMolting;
        this.laidEggs = laidEggs;
    }
    
    
    
    public void layAnEgg(){
        setLaidEggs(getLaidEggs() + 1);
    }

    /**
     * @return the isMolting
     */
    public boolean isIsMolting() {
        return isMolting;
    }

    /**
     * @param isMolting the isMolting to set
     */
    public void setIsMolting(boolean isMolting) {
        this.isMolting = isMolting;
    }

    /**
     * @return the laidEggs
     */
    public int getLaidEggs() {
        return laidEggs;
    }

    /**
     * @param laidEggs the laidEggs to set
     */
    public void setLaidEggs(int laidEggs) {
        this.laidEggs = laidEggs;
    }
}