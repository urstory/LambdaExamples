package kr.co.sunnyvale.examples.lambda;

public class Test01 {

    public static void main(String[] args) {
        System.out.println("print1() ----------------------------------------------------");
        print1(() -> {
            for (int i = 0; i < 10; i++) System.out.println("hello");
        });
        System.out.println("print2() ----------------------------------------------------");
        print2(() -> {
            int total = 0;
            for (int i = 0; i < 10; i++) {
                total += i;
            }
            ;
            return total + "";
        });
        System.out.println("print3() ----------------------------------------------------");
        print3((int i) -> {
            System.out.println(i);
        });
        print3(i -> {
            System.out.println(i);
        });

        System.out.println("print4() ----------------------------------------------------");
        print4((int i, String str) -> {
            for (int k = 0; k < i; k++) {
                System.out.println(str);
            }
        });

        Exec04 ex04 = (int i, String str) -> {
            for (int k = 0; k < i; k++) {
                System.out.println(str);
            }
        };
        print4(ex04);
        System.out.println("Thread start() ----------------------------------------------------");
        new Thread(() -> {
            System.out.println(5);
        }).start();

        new Thread(() -> {
            System.out.println(5);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }).start();
        System.out.println("print5() ----------------------------------------------------");
//        print5((int i) -> {
//            System.out.println(i);
//        });
        System.out.println("print6() ----------------------------------------------------");
        print6((int i) -> {
            System.out.println(i);
//            throw new Exec06Exception();
        });
        System.out.println("print7() ----------------------------------------------------");
        // 메소드 레퍼런스
        print7(System.out::println);
        System.out.println("print8() ----------------------------------------------------");
        // 메소드 레퍼런스
        print8(Test01::print2param);

        // 생성자 레퍼런스 1.
        System.out.println("print9() ----------------------------------------------------");
        Bean09<String> bean09 = () -> { return new String("hello"); };
        Bean09<String> bean092 = String::new;

        // 아무것도 없는 문자열이 출력됨.
        System.out.println(bean092.get());

        // 생성자 레퍼런스 2.
        System.out.println("print10() ----------------------------------------------------");
        Bean10 bean10 = Bean11::new;
        System.out.println(bean10.get());

        // bean12의 get메소드는 Bean11[5] 배열을 반환한다. 배열안에는 값을 할당하지 않았기 때문에 null을 반환한다.
        System.out.println("print12() ----------------------------------------------------");
        Bean12 bean12 =  () -> new Bean11[5];
    }

    public static Bean11[] get(Bean12 bean){
        return bean.get();
    }

    public static void print2param(int i, String str) {
        System.out.println(i);
        System.out.println(str);
    }

    public static void print1(Exec01 e) {
        e.run();
    }

    public static void print2(Exec02 e) {
        String str = e.run();
        System.out.println(str);
    }

    public static void print3(Exec03 e) {
        e.run(5);
    }

    public static void print4(Exec04 e) {
        e.run(5, "hello");
    }

    public static void print5(Exec05 e) {
        e.run(5);
    }

    public static void print6(Exec06 e) {
        try {
            e.run(5);
        } catch (Exec06Exception e1) {
            e1.printStackTrace();
        }
    }

    public static void print7(Exec07 k) {
        k.run(5);
    }

    public static void print8(Exec08 k) {
        k.run(5, "hello");
    }

}

interface Exec01 {
    public void run();
}

interface Exec02 {
    public String run();
}

interface Exec03 {
    public void run(int i);
}

// 단일 추상 메소드를 갖춘 인터페이스의 객체를 기대할 때 람다 표현식을 사용할 수 있다. 이러한 인터페이스를 함수형 인터페이스라고 한다.
interface Exec04 {
    //    public void run(int i);
    public void run(int i, String str);
}

// 추상 클래스는 안된다.
abstract class Exec05 {
    public abstract void run(int i);
}

interface Exec06 {
    public void run(int i) throws Exec06Exception;
}

class Exec06Exception extends Exception {

}

interface Exec07 {
    public void run(int i);
}

interface Exec08 {
    public void run(int i, String str);
}

interface Bean09<T> {
   public T get();
}

interface Bean10{
    public Bean11 get();
}

class Bean11{
    int k = 5;
    String str = "hello";

    @Override
    public String toString() {
        return "Bean11{" +
                "k=" + k +
                ", str='" + str + '\'' +
                '}';
    }
}

interface Bean12{
    public Bean11[] get();
}