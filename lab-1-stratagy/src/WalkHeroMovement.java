public class WalkHeroMovement implements HeroMovement {
    @Override
    public void move(String from, String to) {
        System.out.println("Waling from " + from + " to " + to);
    }

    @Override
    public String getName() {
        return "walk";
    }
}