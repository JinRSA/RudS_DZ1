import model.Kotik;

public class Application {
    public static void main(String[] args) {
        Kotik[] cats = {new Kotik(100, "Дымок", 12, "I'm grey cat"), new Kotik()};
        cats[0].liveAnotherDay();
        System.out.println("Cat name is: " + cats[0].getName() + ";\tweight = " + cats[0].getSatiety());
        if (cats[0].getMeow().equals(cats[1].getMeow()))
            System.out.println("Cat's meow are equals!");
        else
            System.out.println("Cat's meow aren't equals!");
        System.out.println("Cats count: " + Kotik.getCount());
    }
}