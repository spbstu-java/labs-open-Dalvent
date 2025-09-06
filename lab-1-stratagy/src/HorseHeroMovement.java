public class HorseHeroMovement implements HeroMovement {
    @Override
    public void move(String from, String to) {
        System.out.println("Riding a horse from " + from + " to " + to);
    }

    @Override
    public String getName() {
        return "horse";
    }
}