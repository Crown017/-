package concurrent.java8;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestFunction {

    public static void main(String[] args) {
        //Function
        BiFunction<Integer,Integer,Integer> function = (a , b) ->   a + b ;
        BiFunction<Integer,Integer,Integer> function2 = (a , b) -> {
             return a + b ;
        };
        int bi = function.apply(11,12);
        System.out.println(bi);

        //Consumer
        BiConsumer<Integer,Integer> biConsumer = (a, b) -> {
            // 不返回值
            System.out.println(a + b);
        };

        //Predicate
        Predicate<String> predicate =  (info) -> {
            return info.equals("hello");
        };


        //Supplier 返回指定的类型
        Supplier<String> stringSupplier = () -> {
          return "welcome";
        };

    }
}
