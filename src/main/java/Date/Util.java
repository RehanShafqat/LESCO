package Date;

import java.util.Random;

/**
 *
 * @author rehan
 */
public class Util {

   
    public static boolean numsOnly(String input) {
        return input != null && input.chars().allMatch(Character::isDigit);
    }

   
    public static int getRandomNumber() {
        return new Random().nextInt(10000);  
    }
}
