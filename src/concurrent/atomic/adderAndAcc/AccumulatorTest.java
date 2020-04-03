package concurrent.atomic.adderAndAcc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

public class AccumulatorTest {
    public static void main(String[] args) {
        LongAccumulator accumulator = new LongAccumulator((x,y) -> x + y,1);

        ExecutorService service = Executors.newFixedThreadPool(2);


        IntStream.range(0,10).forEach(i -> service.submit(() -> accumulator.accumulate(i)));




    }
}
