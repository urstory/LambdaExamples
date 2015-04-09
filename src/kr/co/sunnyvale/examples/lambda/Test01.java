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
        // �޼ҵ� ���۷���
        print7(System.out::println);
        System.out.println("print8() ----------------------------------------------------");
        // �޼ҵ� ���۷���
        print8(Test01::print2param);

        // ������ ���۷��� 1.
        System.out.println("print9() ----------------------------------------------------");
        Bean09<String> bean09 = () -> { return new String("hello"); };
        Bean09<String> bean092 = String::new;

        // �ƹ��͵� ���� ���ڿ��� ��µ�.
        System.out.println(bean092.get());

        // ������ ���۷��� 2.
        System.out.println("print10() ----------------------------------------------------");
        Bean10 bean10 = Bean11::new;
        System.out.println(bean10.get());

        // bean12�� get�޼ҵ�� Bean11[5] �迭�� ��ȯ�Ѵ�. �迭�ȿ��� ���� �Ҵ����� �ʾұ� ������ null�� ��ȯ�Ѵ�.
        System.out.println("print12() ----------------------------------------------------");
        Bean12 bean12 =  () -> new Bean11[5];

        // �������̽��� �޼ҵ� ��ü�� �����ϵ��� �Ѵ�.
        System.out.println("print13() ----------------------------------------------------");
        Bean13 bean13 = () -> {
            System.out.println("run");
        };
        bean13.run();
        bean13.run2();
        // �������̽��� ������ �ִ� default�޼ҵ带 �������̵� �Ѵ�.
        System.out.println("print14() ----------------------------------------------------");
        Bean14 bean14 = () -> {
            System.out.println("run");
        };
        bean14.run2();

        System.out.println(bean14.getClass().getName()); // Test01�� �̸����� ���� Ŭ����?
        if(bean14 instanceof Bean14){ // Bean14�� �ν��Ͻ��� ���´�.
            System.out.println("bean14 instanceof Bean14");
        }
        System.out.println("print16() ----------------------------------------------------");
        Bean16 bean16 = new Bean16();
        bean16.run2();
        System.out.println("print17() ----------------------------------------------------");
        Bean17.run();


        // ������ ���۷���
        System.out.println("print18() ----------------------------------------------------");
        Bean18<Bean19> bean18 = ()->{ return new Bean19(); };
        Bean18<Bean19> bean18_2 = Bean19::new;

        // Bean21�� 2���� �Ķ���͸� �޴´�. (Integer, Integer)
        // bean20�� ������ �ִ� �޼ҵ�� Integer, Integer�� �Ķ���ͷ� ���޹޾� Bean21�� ��ȯ�Ѵ�.
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

// ���� �߻� �޼ҵ带 ���� �������̽��� ��ü�� ����� �� ���� ǥ������ ����� �� �ִ�. �̷��� �������̽��� �Լ��� �������̽���� �Ѵ�.
interface Exec04 {
    //    public void run(int i);
    public void run(int i, String str);
}

// �߻� Ŭ������ �ȵȴ�.
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

// ���� �������̽��� ���� �߰��Ǵ� �޼ҵ忡 ������ü�� ���� �� �ִ�.
// ���� �������̽��� ���ο� �޼ҵ尡 �߰��Ǹ� �ش� �������̽��� �����ϰ� �ִ� Ŭ�������� ���
// ������ �߻��ϰ� �ȴ�. �޼ҵ带 �������� �ʾ����ϱ�!!
// �̷� ������ �ذ��ϱ� ���Ͽ� �������̽��� ������ �޼ҵ带 ���� �� �ֵ��� �Ѵ�.
// �⺻ �޼ҵ�� Ŭ�������� �������� �ʾƵ� ��밡���ϴ�.
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

// ���������� ���߻���� �ȴ�. ���̾Ƹ�� ������ �߻���. ������ ������
class Bean16 implements Bean14, Bean15 {
    public void run() {
        System.out.println("run!");
    }

    @Override
    public void run2() {
        Bean14.super.run2();
    }
}

// �������̽� ���� �޼ҵ�
// �⺻ ������ �Բ� �������̽������� ���� �޼ҵ� ���� ����
// ���� �޼ҵ带 ���� �̸��������μ��� �������̽�

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