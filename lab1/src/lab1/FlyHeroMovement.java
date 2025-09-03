package lab1;

public class FlyHeroMovement implements HeroMovement {
    @Override
    public void move(String from, String to) {
        System.out.println("Flying from " + from + " to " + to);
    }

    @Override
    public String getName() {
        return "fly";
    }
}
