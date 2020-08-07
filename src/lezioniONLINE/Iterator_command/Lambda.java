package Iterator_command;

import java.nio.charset.Charset;
import java.util.function.BiFunction;

public class Lambda {

    public interface Function<T, S>{
        S apply(T t);
    }

    public interface Supplier<T>{
        T get();
    }

    public interface Consumer<T>{
        void accept(T t);
    }

    public interface Runnable{
        void run();
    }

    public static void main(String[] args) {
        Function<String, Integer>  f1_1 = s -> s.hashCode();
        Function<String, Integer>  f1_2 = String::hashCode;

        Supplier<Long> f2_1 = () -> System.currentTimeMillis();
        Supplier<Long> f2_2 = System::currentTimeMillis;

        Consumer<Integer> f3 =
                (Integer x) -> {
                    for (int i = 0; i < x; i++)
                        System.out.println(i);
                };
        f3.accept(199);
        Runnable r = () -> System.out.println(" ciao ");

        BiFunction<String, Character, Integer> f = String::indexOf;

        System.out.println(f.apply("electron",'c'));
        BiFunction<CharSequence,Iterable< ? extends CharSequence>,String> luca = String::join;
    }
}
