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

        // 인터페이스가 메소드 자체를 구현하도록 한다.
        System.out.println("print13() ----------------------------------------------------");
        Bean13 bean13 = () -> {
            System.out.println("run");
        };
        bean13.run();
        bean13.run2();
        // 인터페이스가 가지고 있는 default메소드를 오버라이딩 한다.
        System.out.println("print14() ----------------------------------------------------");
        Bean14 bean14 = () -> {
            System.out.println("run");
        };
        bean14.run2();

        System.out.println(bean14.getClass().getName()); // Test01의 이름없는 람다 클래스?
        if(bean14 instanceof Bean14){ // Bean14의 인스턴스로 나온다.
            System.out.println("bean14 instanceof Bean14");
        }
        System.out.println("print16() ----------------------------------------------------");
        Bean16 bean16 = new Bean16();
        bean16.run2();
        System.out.println("print17() ----------------------------------------------------");
        Bean17.run();


        // 생성자 레퍼런스
        System.out.println("print18() ----------------------------------------------------");
        Bean18<Bean19> bean18 = ()->{ return new Bean19(); };
        Bean18<Bean19> bean18_2 = Bean19::new;

        // Bean21은 2개의 파라미터를 받는다. (Integer, Integer)
        // bean20이 가지고 있는 메소드는 Integer, Integer를 파라미터로 전달받아 Bean21을 반환한다.
        System.out.println("print20() ----------------------------------------------------");
        Bean20<Integer, Integer, Bean21> bean20 = Bean21::new;
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

// 기존 인터페이스에 새로 추가되는 메소드에 구현자체를 넣을 수 있다.
// 기존 인터페이스에 새로운 메소드가 추가되면 해당 인터페이스를 구현하고 있는 클래스들은 모두
// 에러가 발생하게 된다. 메소드를 구현하지 않았으니깐!!
// 이런 문제를 해결하기 위하여 인터페이스에 구현된 메소드를 가질 수 있도록 한다.
// 기본 메소드는 클래스에서 구현하지 않아도 사용가능하다.
interface Bean13{
    public void run();
    public default void run2(){
        System.out.println("run2!");
    }
}

interface Bean14 extends Bean13{
    @Override
    public default void run2(){
        System.out.println("Bean14.run2");
    }
}

interface Bean15 extends Bean13{
    @Override
    public default void run2(){
        System.out.println("Bean15.run2");
    }
}

// 실질적으로 다중상속이 된다. 다이아몬드 문제가 발생함. 컴파일 오류류
class Bean16 implements Bean14, Bean15 {
    public void run() {
        System.out.println("run!");
    }

    @Override
    public void run2() {
        Bean14.super.run2();
    }
}

// 인터페이스 정적 메소드
// 기본 구현과 함께 인터페이스에서도 정적 메소드 정의 가능
// 편의 메소드를 위한 이름공간으로서의 인터페이스

interface Bean17{
    public static void run(){
        System.out.println("run!!");
    }
}


interface Bean18<T>{
    public T get();
}

class Bean19{

}

interface Bean20<T,U,R>{
    public R get(T t, U u);
}

class Bean21<T,U>{
    T t;
    U u;
    public Bean21(T t, U u){
        this.t = t;
        this.u = u;
    }

    @Override
    public String toString() {
        return "Bean21{" +
                "t=" + t +
                ", u=" + u +
                '}';
    }
}