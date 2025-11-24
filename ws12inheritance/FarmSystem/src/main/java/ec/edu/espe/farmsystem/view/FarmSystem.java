package ec.edu.espe.farmsystem.view;

import ec.edu.espe.farmsystem.model.Cage;
import ec.edu.espe.farmsystem.model.Chicken;
import ec.edu.espe.farmsystem.model.FarmAnimal;
import javax.tools.JavaFileManager.Location;
import java.util.Date;

/**
 *
 * @author Mikael Hidalgo
 */
public  abstract class FarmSystem {
    public static void main (String[] args){
        
    int id = 0;
    String breed;
    Date bornOn;
    String gender;
    boolean isAbleToReproduce = false;
    float weight;
    Cage cage;
    Location Location;
    int xCoordinate;
    int yCoordinate;
    weight = 10.4F;
    gender ="male";
    
    
    
    xCoordinate = 20;
    yCoordinate = 10;
    
    bornOn = new Date(2025, 2, 1);
    breed = "Holstein";
    Location = new LocationImpl (xCoordinate, yCoordinate);
    cage = new Cage(1, "satble for cows",2, Location);
    
    
        FarmAnimal farmAnimal;  
        
        farmAnimal=new Chicken(true, 0,1, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        
        System.out.println("farmanimal -->"+farmAnimal);
    }

    private static class LocationImpl implements Location {

        public LocationImpl(int xCoordinate, int yCoordinate) {
        }

        @Override
        public String getName() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public boolean isOutputLocation() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}