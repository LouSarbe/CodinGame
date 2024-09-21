// Score: 41240
// 100% de réussite

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Save humans, destroy zombies!
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // game loop
        while (true) {
            int x = in.nextInt();
            int y = in.nextInt();
            int humanCount = in.nextInt();
            var humans = new Human[humanCount];
            for (int i = 0; i < humanCount; i++) {
                humans[i] = new Human(in.nextInt(), in.nextInt(), in.nextInt());
            }
            int zombieCount = in.nextInt();
            var zombies = new Zombie[zombieCount];
            for (int i = 0; i < zombieCount; i++) {
                zombies[i] = new Zombie(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
            }

            var savableHumans = SavableHumans(humans, zombies, x, y);
            var humanInDanger = NextHumanToDie(savableHumans, zombies, x, y);
            var humansInDanger = HumansInDanger(savableHumans, zombies);
            if (humansInDanger.length > 0) {
                for (Human h : humansInDanger) {
                    if (distance(h, x, y) < distance(humanInDanger, x, y)) {
                        humanInDanger = h;
                    }
                }
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.out.printf("%d %d\n", humanInDanger.x, humanInDanger.y); // Your destination coordinates
        }
    }

    // Calculateur de distance
    private static int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private static int distance(Human human, Zombie zombie) {
        return distance(human.x, human.y, zombie.x, zombie.y);
    }

    private static int distance(Human human, int x, int y) {
        return distance(human.x, human.y, x, y);
    }

    private static int distance(Zombie zombie, int x, int y) {
        return distance(zombie.x, zombie.y, x, y);
    }

    private static Human NextHumanToDie(Human[] humans, Zombie[] zombies, int x, int y) {
        var nextHumanToDie = humans[0];
        var smallestDistanceToZombie = distance(nextHumanToDie, zombies[0]);
        for (Human h : humans) {
            for (Zombie z : zombies) {
                if (distance(h, z) < smallestDistanceToZombie) {
                    smallestDistanceToZombie = distance(h, z);
                    nextHumanToDie = h;
                }
            }
        }
        return nextHumanToDie;
    }

    private static Human[] HumansInDanger(Human[] humans, Zombie[] zombies) {
        List<Human> humansInDanger = new ArrayList<>();
        for (Human h : humans) {
            for (Zombie z : zombies) {
                if (distance(h, z) <= 1200) {
                    humansInDanger.add(h);
                    break;
                }
            }
        }
        return humansInDanger.toArray(new Human[humansInDanger.size()]);
    }

    private static boolean isHumanSavable(Human human, Zombie[] zombies, int x, int y) {
        for (Zombie zombie : zombies) {
            if (distance(human, zombie) < (distance(zombie, x, y) - 2000) / 2.5) {
                return false;
            }
        }
        return true;
    }

    private static Human[] SavableHumans(Human[] humans, Zombie[] zombies, int x, int y) {
        List<Human> savableHumans = new ArrayList<>();
        for (Human h : humans) {
            if (isHumanSavable(h, zombies, x, y)) {
                savableHumans.add(h);
            }
        }
        return savableHumans.toArray(new Human[savableHumans.size()]);
    }
}

class Zombie {
    int id;
    int x;
    int y;
    int xNext;
    int yNext;

    Zombie(int id, int x, int y, int xNext, int yNext) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.xNext = xNext;
        this.yNext = yNext;
    }
}

class Human {
    int id;
    int x;
    int y;

    Human(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
}