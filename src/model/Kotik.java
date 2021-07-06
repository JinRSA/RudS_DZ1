package model;

public class Kotik {
    //region Members.
    private int m_prettiness, m_weight;
    private String m_name, m_meow;
    protected Op[] methods = { this::play, this::sleep, this::chaseMouse, this::draw };
    protected int m_satiety = 100;
    private static int s_count;
    //endregion
//region Getters and setters.
    public int getPrettiness() { return m_prettiness; }

    public void setPrettiness(int prettiness) { m_prettiness = prettiness; }

    public int getWeight() { return m_weight; }

    public void setWeight(int weight) { m_weight = weight; }

    public String getName() { return m_name; }

    public void setName(String name) { m_name = name; }

    public String getMeow() { return m_meow; }

    public void setMeow(String meow) { m_meow = meow; }

    public int getSatiety() { return m_satiety; }

    public void setSatiety(int in_satiety) { m_satiety = in_satiety; }

    public static int getCount() { return s_count; }
    //endregion
    public Kotik() {
        ++s_count;
        int prettiness = (int)(Math.random() * 100);
        String name = "Gav";
        int weight = (int)(Math.random() * 10);
        String meow = "Meow";
        setKotik(prettiness, name, weight, meow);
        System.out.println("Unknown cat was born. It weighs " + m_weight + " kg..");
    }

    public Kotik(int prettiness, String name, int weight, String meow) {
        ++s_count;
        setKotik(prettiness, name, weight, meow);
        System.out.println(m_name + " (cat) was born. It weighs " + m_weight + " kg..");
    }

    public void setKotik(int prettiness, String name, int weight, String meow) {
        m_prettiness = prettiness;
        m_name = name;
        m_weight = weight;
        m_meow = meow;
    }

    @Override
    protected void finalize() throws Throwable {
        --s_count;
        System.out.println(m_name + ":\t\"Memento mori!\"");
        super.finalize();
    }
//region Methods.
    public boolean eat() { return eat((int)(100 / 1.5), "Whiskas"); }

    public boolean eat(int satiety) { return eat(satiety, "unknown suspicious food"); }

    public boolean eat(int satiety, String EatName) {
        m_satiety += Math.abs(satiety);
        if (m_satiety > 100)
            m_satiety = 100;
        System.out.println(m_name + " ate " + EatName + ". Cat have now " + m_satiety + " satiety.");
        return true;
    }

    public boolean play() {
        if (isHungry())
            return false;
        spendEnergy();
        System.out.println(m_name + " is playing.");
        return true;
    }

    public boolean sleep() {
        if (isHungry())
            return false;
        spendEnergy();
        System.out.println(m_name + " is sleeping.");
        return true;
    }

    public boolean chaseMouse() {
        if (isHungry())
            return false;
        spendEnergy(16);
        System.out.println(m_name + " is chasing a mouse.");
        return true;
    }

    public boolean draw() {
        if (isHungry())
            return false;
        spendEnergy(5);
        System.out.println("(◉ܫ◉) " + m_meow + '.');
        return true;
    }

    protected boolean isHungry() {
        if (m_satiety < 25) {
            System.out.println(m_name + " wants to eat! Cat have now " + m_satiety + " satiety.");
            return true;
        }
        return false;
    }

    protected void spendEnergy() {
        spendEnergy(15);
    }

    protected void spendEnergy(int coefficient) {
        int value = (int)(Math.random() * Math.abs(coefficient));
        m_satiety = (int)(m_satiety - value < 0 ? 0 : m_satiety - value);
//        System.out.println("Cat have now " + m_satiety + " satiety.");
    }

    public void liveAnotherDay() {
        boolean isStarving = false;
        for (int i = 0; i < 24; ++i) {
            if (isStarving) {
                eat(100 - m_satiety);
                isStarving = !isStarving;
            }
            else {
                int id = (int)(Math.random() * methods.length);
                isStarving = !methods[id].doing();
            }
        }
    }
//endregion
}

interface Op {
    boolean doing();
}
