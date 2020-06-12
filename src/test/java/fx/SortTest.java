package fx;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Callable;

public class SortTest {
    public void process(Runnable r) {
        r.run();
    }

    @Test
    public void test1() {
        process(() -> System.out.println("this is lambda"));
    }

    @Test
    public void test2() throws Exception {
        fetch().call();
    }

    @Test
    public Callable<String> fetch() {
        System.out.println("this is lambda ;-)");
        return () -> "this is lambda ;-)";
    }
}