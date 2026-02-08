package ec.edu.espe.farmsystem.view;

import ec.edu.espe.farmsystem.model.Cage;
import ec.edu.espe.farmsystem.model.Chicken;
import ec.edu.espe.farmsystem.model.FarmAnimal;
import ec.edu.espe.farmsystem.model.Location;
import ec.edu.espe.farmsystem.model.Pig;
import ec.edu.espe.farmsystem.model.Sheep;
import java.util.Date;

/**
 *
 * @author Mikael Hidalgo, Object Masters, @ESPE
 */
public class FarmSystem {
    
    public static void main(String[] args) {
        
        int id; 
        String breed;
        Date bornOn;
        String gender;
        boolean isAbleToReproduce;
        float weight;
        Cage cage;
        Location location;
        int xCoordinate;
        int yCoordinate;
        
        xCoordinate=10;
        yCoordinate=20;
        weight = 10.4F;
        bornOn= new Date(2025,2,1);
        breed="Holsterin";
        gender="male";
        isAbleToReproduce=false;
        
        location= new Location (xCoordinate, yCoordinate);
        cage= new Cage(1, "for cows", 2, location);
        
        FarmAnimal farmAnimal;
       
        farmAnimal= new Chicken(false, 1, 1, breed, bornOn, gender, isAbleToReproduce, weight, cage);
        System.out.println("farmAnimal Chicken-----> " + farmAnimal);
        
        farmAnimal = new Cow(true, 12.5F, 2, "Holstein", bornOn, "female", true, 500.0F, cage);
        System.out.println("farmAnimal (Cow) -> " + farmAnimal);
        
        farmAnimal = new Pig(3, "Landrace", bornOn, "male", true, 150.0F, cage);
        System.out.println("farmAnimal (Pig) -> " + farmAnimal);
        
        Date shearingDate = new Date(2025 - 1900, 4, 15); 
        
        farmAnimal = new Sheep(shearingDate, 4, "Merino", bornOn, "female", true, 70.5F, cage);
        System.out.println("farmAnimal (Sheep) -> " + farmAnimal);
        
    }
    
    
}