package ec.edu.espe.farmsystem.model;

import java.util.Date;

/**
 * @author Mikael Hidalgo, Object Masters, @ESPE
 */
public class Sheep extends FarmAnimal {
    
    private Date lastShearing;

    public Sheep(Date lastShearing, int id, String breed, Date bornOn, String gender, boolean isAbleToReproduce, float weight, Cage cage) {
        super(id, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        this.lastShearing = lastShearing;
    }

    public void cutWool() {
        // Lógica para cortar lana
        System.out.println("Cutting wool for sheep " + getId());
        this.lastShearing = new Date(); // Actualizamos la fecha a 'hoy'
    }

    public void shear() {
        // Lógica de esquilar (similar a cutWool, según tu diagrama tienes ambos)
        cutWool();
    }

    @Override
    public String toString() {
        return "Sheep{" + "lastShearing=" + lastShearing + ", " + super.toString() + '}';
    }

    // Getters y Setters
    public Date getLastShearing() {
        return lastShearing;
    }

    public void setLastShearing(Date lastShearing) {
        this.lastShearing = lastShearing;
    }
}