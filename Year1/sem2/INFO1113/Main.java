class MyClass {
    void nonAbstractMethod() {
        System.out.println("Non-abstract method.");
    }
    void hi(){
        System.out.println("no");
    }
}

public class Main {
    public static void main(String[] args) {
        MyClass instance = new MyClass() {
            @Override
            void nonAbstractMethod() {
                System.out.println("Overridden method in anonymous class.");
            }
            void hi(){
                System.out.println("hi");
            }
        };
        instance.nonAbstractMethod();
        instance.hi();
    }
}