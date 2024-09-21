// League: Wood 3

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Score points by scanning valuable fish faster than your opponent.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int creatureCount = in.nextInt();
        for (int i = 0; i < creatureCount; i++) {
            int creatureId = in.nextInt();
            int color = in.nextInt();
            int type = in.nextInt();
        }

        // game loop
        while (true) {
            int myScore = in.nextInt();
            int foeScore = in.nextInt();
            int myScanCount = in.nextInt();
            for (int i = 0; i < myScanCount; i++) {
                int creatureId = in.nextInt();
            }
            int foeScanCount = in.nextInt();
            for (int i = 0; i < foeScanCount; i++) {
                int creatureId = in.nextInt();
            }
            int myDroneCount = in.nextInt();
            var myDrones = new Drone[myDroneCount];
            for (int i = 0; i < myDroneCount; i++) {
                int droneId = in.nextInt();
                int droneX = in.nextInt();
                int droneY = in.nextInt();
                int emergency = in.nextInt();
                int battery = in.nextInt();
                myDrones[i] = new Drone(droneId, droneX, droneY, emergency, battery);
            }
            int foeDroneCount = in.nextInt();
            for (int i = 0; i < foeDroneCount; i++) {
                int droneId = in.nextInt();
                int droneX = in.nextInt();
                int droneY = in.nextInt();
                int emergency = in.nextInt();
                int battery = in.nextInt();
            }
            int droneScanCount = in.nextInt();
            for (int i = 0; i < droneScanCount; i++) {
                int droneId = in.nextInt();
                int creatureId = in.nextInt();
            }
            int visibleCreatureCount = in.nextInt();
            for (int i = 0; i < visibleCreatureCount; i++) {
                int creatureId = in.nextInt();
                int creatureX = in.nextInt();
                int creatureY = in.nextInt();
                int creatureVx = in.nextInt();
                int creatureVy = in.nextInt();
            }
            int radarBlipCount = in.nextInt();
            for (int i = 0; i < radarBlipCount; i++) {
                int droneId = in.nextInt();
                int creatureId = in.nextInt();
                String radar = in.next();
            }

            List<int[]> angles = new ArrayList<>(Arrays.asList(
                new int[]{2000, 2000},
                new int[]{8000, 8000},
                new int[]{2000, 8000},
                new int[]{8000, 2000}
            ));

            for (int i = 0; i < myDroneCount; i++) {
                if (angles.isEmpty()) break;

                var anglesDistances = new int[angles.size()];
                for (int j = 0; j < angles.size(); j++) {
                    anglesDistances[j] = distance(myDrones[i], angles.get(j)[0], angles.get(j)[1]);
                }
                var closestAngle = 0;
                for (int j = 0; j < angles.size(); j++) {
                    if (distance(myDrones[i], angles.get(j)[0], angles.get(j)[1]) < anglesDistances[closestAngle]) {
                        closestAngle = j;
                    }
                }
                var nextMoveX = angles.get(closestAngle)[0];
                var nextMoveY = angles.get(closestAngle)[1];
                
                var light = 0;
                if(distance(myDrones[i], nextMoveX, nextMoveY) < 600) {
                    light = 1;
                    angles.remove(closestAngle); // Remove the angle once the drone is close enough
                }

                // Write an action using System.out.println()
                // To debug: System.err.println("Debug messages...");

                System.out.printf("MOVE %d %d %d\n", nextMoveX, nextMoveY, light); // MOVE <x> <y> <light (1|0)> | WAIT <light (1|0)>
            }
        }
    }

    private static int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private static int distance(Drone drone, int x, int y) {
        return distance(drone.x, drone.y, x, y);
    }
}

class Drone {
    int id;
    int x;
    int y;
    int emergency;
    int battery;

    public Drone(int id, int x, int y, int emergency, int battery) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.emergency = emergency;
        this.battery = battery;
    }
}
