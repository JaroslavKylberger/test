package cz.kylberger.test;

public class B extends A implements IA, IB {
    int y = 10;

    public B(int x) {
        super(x);
    }

    private int print(int x) {
        return 1;
    }

    ;

    public void p() {
        A a = new A(1);
        System.out.println(a.y + y);
    }

    public void iaAbstractM() {
    }

    public B m() {
        System.out.println(IA.IA_CONSTANT);
        return this;
    }
}

class HiddenC{}