package hello.core.singleton;

public class StatefulService {

    private int price; // 상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price =  " + price);
        this.price = price; // 여기가문제
    }

    public int getPrice() {
        return price;
    }

    // 무상태로 설계 ( 상태를 유지하는 필드를 굳이 둘필요가없다

    public int orderStateless(String name, int price) {
        System.out.println("name = " + name + " price =  " + price);
//        this.price = price; // 여기가문제
        return price;
    }
}
