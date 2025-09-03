package lab1;

public class Hero {
    private final String name;
    private HeroMovement movement;
    private String currentLocation;

    public Hero(String name, String startLocation) {
        this.name = name;
        this.currentLocation = startLocation;
    }

    public void setMovement(HeroMovement movement) {
        this.movement = movement;
    }

    public void move(String to) {
        System.out.print(name + ": ");
        if (movement == null) {
            System.out.println("I don't know how move :(");
            return;
        }

        movement.move(currentLocation, to);
        currentLocation = to;
    }

    public void showInfo() {
        StringBuilder infoBuilder = new StringBuilder();

        infoBuilder.append(name)
                   .append(" located at ")
                   .append(currentLocation);

        if (movement != null) {
            infoBuilder.append(" and he used for movement: ")
                       .append(movement.getName());
        } else {
            infoBuilder.append(", but he don't know how move");
        }

        System.out.println(infoBuilder);
    }
}
