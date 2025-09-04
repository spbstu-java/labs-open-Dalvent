package lab2;

public class ExampleClass {

    @Repeat(2)
    public void helloPublic() {
        System.out.println("Hello, Public!");
    }

    public void sum(int a, int b) {
        System.out.println("sum=" + (a + b));
    }

    public void showYesNo(boolean isYes) {
        System.out.println(isYes ? "yes" : "no");
    }

    protected void helloProtected() {
        System.out.println("Hello, Protected!");
    }

    protected void mul(int a, int b) {
        System.out.println("mul=" + (a * b));
    }

    @Repeat(5)
    protected void showOkCancel(boolean isOk) {
        System.out.println(isOk ? "ok" : "cancel");
    }

    @Repeat(3)
    private void helloPrivate() {
        System.out.println("Hello, Private!");
    }

    @Repeat(1)
    private void sub(double a, double b) {
        System.out.println("sub=" + (a - b));
    }

    protected void showTF(boolean isT) {
        System.out.println(isT ? "T" : "F");
    }

    @Repeat(3)
    private boolean hello(String name) {
        System.out.println("Hello, " + name + "!");
        return true;
    }
}
