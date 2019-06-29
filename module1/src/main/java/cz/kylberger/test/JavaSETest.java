package cz.kylberger.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class JavaSETest {

    public static void main(String[] args) {
        List<Integer> elements = new ArrayList<>();
        elements.add(10);
        int firstElmnt = elements.get(0);
        System.out.println(firstElmnt);
        Callable<String> callable = () -> {
            Thread.sleep(1000);
            return Thread.currentThread().getName();
        };
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(callable);
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
