import java.util.Random;

public class Main {

    public static int[] health = {1300, 250, 250, 250, 250, 250, 120};
    public static int[] hits = {50, 20, 20, 20, 10, 21, 15};
    public static String[] hitTipes = {"Physical", "Physical", "Magical",
            "Mental", "Medical", "Tor", "Trickster"};/* "Tank" "Berserk"  */

    public static void main(String[] args) {
        while (!isFinished()) {
           // for (int i = 1; i <= 20; i++) {   //переделать цикл чтобы показывал номер раунда
                System.out.println("___***___New round___***___"/* + i*/);
                changeBossDefence();
                round();
                printStatistics();
            }
        }
    

    public static void round() {
        for (int i = 1; i <= 6; i++) {
            if (health[0] > 0) {
                int damagedHealth = playerHit(i);
                if (damagedHealth < 0) {
                    health[0] = 0;
                } else {
                    health[0] = damagedHealth;
                }
            }
        }
        torHit();
        if (health[0] > 0) {
            for (int i = 1; i <= 5; i++) {
                health[i] = bossHit(i);
            }

            if (health[4] <= 0) {   //Medic[4] (как можно сделать цикл лечения медика чтобы он лечил всех кроме себя?)
                health[4] = 0;
            } else if (health[4] > 0) {
                for (int i = 1; i <= 6; i++) {
                    health[i] = hits[4] + health[i];
                }
            }

        }
        tricksterHit();


    }

    public static int playerHit(int playerIndex) {
        Random r = new Random();
        int randomNumber = r.nextInt(2) + 2;
        //сделать чтобы супер удар работал только пока живы войны
        if (hitTipes[0].equals(hitTipes[playerIndex])) {
            System.out.println(hitTipes[playerIndex] + " hits: " + hits[playerIndex]
                    * randomNumber);
            return health[0] - hits[playerIndex] * randomNumber;

        } else {
            return health[0] - hits[playerIndex];

        }

    }

    public static int bossHit(int playerIndex) {
        return health[playerIndex] - hits[0];
    }

    public static boolean isFinished() {

        if (health[0] <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (health[1] <= 0 && health[2] <= 0 && health[3] <= 0 &&
                health[4] <= 0 && health[5] <= 0 && health[6] <= 0) {
            System.out.println("Boss won!");
            return true;
        }
        return false;
    }


    public static void printStatistics() {
        for (int i = 0; i <= 6; i++) {
            if (health[i] <= 0) {
                health[i] = 0;
            }
        }

        System.out.println("_________________________");
        System.out.println("Boss health: " + health[0]);
        System.out.println("Warrior health: " + health[1]);
        System.out.println("Magic health: " + health[2]);
        System.out.println("Kinetic health: " + health[3]);
        System.out.println("Medical health: " + health[4]);
        System.out.println("Tor health: " + health[5]);
        System.out.println("Trickster health: " + health[6]);
        System.out.println("_________________________");
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomNum = r.nextInt(3) + 1;
        hitTipes[0] = hitTipes[randomNum];
    }


    public static void tricksterHit() {
        Random r = new Random();
        int randomNum = r.nextInt(2);

        switch (randomNum) {
            case 0:
                if (health[6] > 0) {
                    health[6] = health[6];
                    System.out.println("Trickster avoided damage");
                } else if (health[6] < 0) {
                    health[6] = 0;
                }
                break;
            case 1:
                health[6] = health[6] - hits[0];
                break;
        }

    }

    public static void torHit() {
        Random r = new Random();
        int randomNum = r.nextInt(4);

        switch (randomNum) {
            case 0:
                if (health[5] > 0) {
                    hits[0] = 0;
                    System.out.println("Tor stunned boss");
                } else if (health[5] < 0) {
                    health[5] = 0;
                }
                break;
            case 1:                   //
                hits[0] = 50;
            case 2:
                hits[0] = 50;
            case 3:
                hits[0] = 50;
                break;

            //health[5] = health[5] - hits[0];

                /*if (randomNum == 0) {
            System.out.println("Tor stunned boss");
            hits[0] = 0;
        } else {
            hits[0] = hits[0];
        }*/
        }

    }
}
