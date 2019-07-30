package org.firefly.provider.lombok;

public class LombokDemo {
    public static void main(String[] args) {
        People people = People.builder().id(1L).name("Kobe").build();
        System.out.println(people.toString());
    }
}
