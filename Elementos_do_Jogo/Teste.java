public class Teste{
    public static void main(String[] args) {
        Tanque t1 = new Tanque("Tatchanca");
        Mago m1 = new Mago("Gandolf");
        Atirador a1 = new Atirador("Arrow");

        t1.atacar(m1);
        m1.atacar(t1);

    }
}