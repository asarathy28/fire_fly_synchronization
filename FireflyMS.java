/* Based off the Mirollo and Strogatz Model. This program  */

public class FireflyMS extends Unit {

    private boolean alive;//is this a live firefly or an empty space
    private double timePeriod = 100;//number of steps between each flash
    private double internalTime;//number of "steps" since the last flash
    private double epsilon = 0.05; //constant < 1, the bigger epsilon is, the faster it syncs

    public FireflyMS(boolean a, double startTime) {
        super(0);
        alive = a;
        internalTime = startTime;
        if (alive && internalTime < 1) {
            setState(-1);
        }
    }

    public void incrementInternalTime() {
        if (alive) {
            internalTime++;
            if (internalTime < timePeriod) {
                setState(0);
            } else {
                //System.out.println("**FLASH**"); //for debugging
                internalTime = 0;
                setState(-1);
            }
        } else {
            setState(0);
        }
    }

    public void jumpFunction(boolean isFlash) {
        if (isFlash) {
            double tDoublePrime;
            double fireFunction = Math.log(internalTime);
            tDoublePrime = Math.exp(fireFunction + epsilon);
            //System.out.println("FUNC: "+tDoublePrime); //for debugging
            internalTime = tDoublePrime;
        }
    }

    public FireflyMS clone() {
        FireflyMS clone = new FireflyMS(alive, internalTime);
        return clone;
    }

    public boolean isAlive() {
        return alive;
    }

    //for debugging
    public void print() {
        System.out.println
        ("STATE: " + getState() +
         " timePeriod: " + timePeriod +
         " internalTime: " + internalTime);
    }
}
