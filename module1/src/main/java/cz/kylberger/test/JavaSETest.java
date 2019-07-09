package cz.kylberger.test;

import cz.kylberger.test.anotation.ClassPreamble;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static java.lang.System.*;

@ClassPreamble(author = "")
public class JavaSETest {

    public static final Function<Byte, String> FORMAT_BYTE_TO_BINARY = b -> String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
    public static final Function<Byte, String> FORMAT_BYTE_TO_UNSIGNED_INT = b -> String.valueOf(b & 0xFF);
    public static final Function<Byte, String> FORMAT_BYTE_TO_HEX = b -> String.format("%02X", b);
    public static final Function<Byte, String> FORMAT_BYTE_TO_BYTE = b -> Byte.toString(b);

    private int pa;
    {
        int _6 = 6;
        out.print(true);
        pa = 10 * 30;
    }

    // nested classes

    // I. static class - accessible outside enclosing class using JavaSETest.StaticNestedClass
    public static class StaticNestedClass {
        public static String staticField = "static field";
        public StaticNestedClass() {}
        public void m(){}
    }

    // II. non-static (inner) classes - cannot have static members except constants
    // a) member inner class - defined as at a top level of a class, accessible outside enclosing class using new JavaSETest().new InnerClass()
    public class InnerClass {
        private static final String constant = "static constant";
        private int innerA;
        private void p(){}
    }
    public interface InnerInterface {
        public void m1();
        public void m2();
    }

    public Integer intM() {
        return 1;
    }

    public JavaSETest() {
        // b) local inner class - defined within the body of a method, declared and instantiated separately
        class LocalClass {private int i; public LocalClass() {i = pa;}}
        LocalClass lc = new LocalClass();
        int j = lc.i;
        // c) anonymous inner class - defined within the body of a method, declared and instantiated at the same time
        InnerInterface  fromInterface = new InnerInterface() {
            public void m1() {}
            public void m2() {}
        };
        InnerClass asSubclass = new InnerClass() {
            private int anonymA;
            private void p(){}
        };
    }

    public static void main(String[] args) {
        String[][] strings = {{"c","d"}, {"a","b"}};
        Arrays.sort(strings);
        System.out.println(Arrays.deepToString(strings));
        System.out.println("x + y = " + 9 + 3 + " ");
        byte[] bytes = new byte[] {'a','b','c'};
        String xxx = new String(bytes, StandardCharsets.US_ASCII);
        System.out.println("string = " + xxx);
        printBytes(xxx, StandardCharsets.US_ASCII, FORMAT_BYTE_TO_BINARY);
        printBytes(xxx, StandardCharsets.UTF_8, FORMAT_BYTE_TO_UNSIGNED_INT);
        printBytes(xxx, StandardCharsets.UTF_16, FORMAT_BYTE_TO_HEX);
        printBytes(xxx, StandardCharsets.UTF_16, FORMAT_BYTE_TO_BINARY);
        printBytes(xxx, StandardCharsets.UTF_16, FORMAT_BYTE_TO_BYTE);
        printBytes(xxx, StandardCharsets.UTF_16, FORMAT_BYTE_TO_UNSIGNED_INT);
        StringBuilder sb = new StringBuilder("abc\\ah.txt");
        String s = sb.toString();
        System.out.println("full path = "  + s.subSequence(0, s.length()));
        int lastSep = sb.lastIndexOf("\\");
        String path = lastSep == -1 ? "" : sb.substring(0, lastSep + 1);
        System.out.println("path = " + path);
        String fileName = sb.substring(lastSep == -1 ? 0 : lastSep + 1);
        System.out.println("filename = " + fileName);
        int []ary = {1,2,3};
        InnerClass ic = new JavaSETest().new InnerClass();
        int x = 3;
        int y = ++x * 4 / x-- + --x;
        System.out.println("y + x is " + (y + x));
        Integer arr[] = {1,2,3,4,0b1010};
        arr[1] = null;
        for (Integer a : arr) {
            System.out.print(a);
        }
        System.out.println();
        x = 8;
        for(;x > -1; x--){
            System.out.print(x);
        }
        System.out.println();
        new B(1).p();
        List<Integer> iList = new ArrayList<>();
        Random r = new Random();
        r.nextInt();
        iList.add(1);iList.add(11);iList.add(30);iList.add(9);
        for(int i : iList) {
            System.out.println(i);
            break;
        }
        iList.removeIf(e->e%2 !=0);
        System.out.println(iList);
        LocalDate date = LocalDate.now();
        date.atTime(10, 22);
        int i = 4 /4;
        Set<String> sety = new HashSet<>();
        System.out.println(i);
        List<String> source = Arrays.asList("Cat","Dog","Rat");
        Collections.sort(source);
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
        String strArr[] = new String[source.size()];
        Arrays.sort(source.toArray(strArr), String::compareToIgnoreCase);
        //new JavaSETest().p('a');
    }

    private static <T> void printBytes(String s, Charset charset, Function<Byte, String> formatFunction) {
        byte[] bytes = s.getBytes(charset);
        String result = IntStream.range(0, bytes.length).mapToObj(i -> formatFunction.apply(bytes[i])).collect(Collectors.toList()).toString();
        System.out.println(charset + ": bytes = " + result);
    }

    private static <C extends Collection<Integer>> C getLengthCollection(Collection<String> source, Supplier<C> supplier) {
        return source.stream().map(String::length).collect(Collectors.toCollection(supplier));
    }


    public void p(int i)throws ClassNotFoundException{}
    /*public void p(double d){}
    public void p(char c) {}*/

}
