package cz.kylberger.test;

public class A {
    protected int y = 15;
    public A (int x) {}
    void print() {}
    public A m() {
        HiddenC hc = new HiddenC();
        return this;
    }
}
