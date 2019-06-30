package cz.kylberger.test;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class JavaSETest {

    public static void main(String[] args) {
        Collection<String> source = Arrays.asList("Cat","Dog","Rat");
        List<Integer> list = getLengthCollection(source, ArrayList::new);
        System.out.println(list);
        Set<Integer> set = getLengthCollection(source, HashSet::new);
        System.out.println(set);
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

    private static <C extends Collection<Integer>> C getLengthCollection(Collection<String> source, Supplier<C> supplier) {
        return source.stream().map(String::length).collect(Collectors.toCollection(supplier));
    }
}
