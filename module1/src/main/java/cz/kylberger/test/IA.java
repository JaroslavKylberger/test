package cz.kylberger.test;

public interface IA extends IB, IC {

    int IA_CONSTANT = 1;

    void iaAbstractM();

    default void iaDefaultM() {
    }

    static void iaStaticM() {
    }

}
