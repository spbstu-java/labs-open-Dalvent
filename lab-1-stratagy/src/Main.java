import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hero hero = new Hero("Alex", "home");

        Map<String, HeroMovement> movements = new LinkedHashMap<>();

        WalkHeroMovement walkHeroMovement = new WalkHeroMovement();
        movements.put(walkHeroMovement.getName(), walkHeroMovement);

        HorseHeroMovement horseHeroMovement = new HorseHeroMovement();
        movements.put(horseHeroMovement.getName(), horseHeroMovement);

        FlyHeroMovement flyHeroMovement = new FlyHeroMovement();
        movements.put(flyHeroMovement.getName(), flyHeroMovement);

        System.out.println("Hello!");
        System.out.println("For moving write: \"move {to} \"");
        System.out.println("For change mode of movement: \"mode {" + String.join("|", movements.keySet()) + "}\"");
        System.out.println("Type 'exit' for quit");

        Scanner sc = new Scanner(System.in);
        while (true) {
            hero.showInfo();

            String command = sc.nextLine().trim();
            String[] commandParts = command.split("\\s+");
            if (commandParts.length == 0)
                continue;

            if (commandParts.length == 1 && commandParts[0].equals("exit"))
                break;

            if (commandParts.length == 2 && commandParts[0].equals("move")) {
                hero.move(commandParts[1]);
                continue;
            }

            if (commandParts.length == 2 && commandParts[0].equals("mode")) {
                if (movements.containsKey(commandParts[1]))
                    hero.setMovement(movements.get(commandParts[1]));
                else
                    System.out.println("Unknown movement mode: " + commandParts[1]);

                continue;
            }

            System.out.println("Unknown command: " + command);
        }
    }
}