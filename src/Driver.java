public class Driver {

    public static void main(String[] args) {
        Cube cube = new Cube();
        cube.scramble("U L2 B2 D2 F U2 B' L2 F2 L2 D2 F L' U L2 R' B2 F U B'");
        cube.print();
        cube.solve();
        System.out.println();
        System.out.println(cube.getSolution());
    }
}
