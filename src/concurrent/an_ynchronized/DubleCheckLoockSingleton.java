package concurrent.an_ynchronized;

public class DubleCheckLoockSingleton {

    private static DubleCheckLoockSingleton dubleCheckLoockSingleton ;

    private DubleCheckLoockSingleton() {
    }

    public static DubleCheckLoockSingleton newInstance(){
        if (dubleCheckLoockSingleton == null ){
            synchronized (DubleCheckLoockSingleton.class){
                if (dubleCheckLoockSingleton == null) {
                     dubleCheckLoockSingleton = new DubleCheckLoockSingleton();
                    return dubleCheckLoockSingleton;
                }
            }
        }
        return dubleCheckLoockSingleton;
    }
}
