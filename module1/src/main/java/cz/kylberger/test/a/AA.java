package cz.kylberger.test.a;

import cz.kylberger.test.JavaSETest;

public class AA {
    protected static int a;
    public static void main(String args[]) {
        JavaSETest.InnerClass ic = new JavaSETest().new InnerClass();
    }
}
