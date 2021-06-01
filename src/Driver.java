public class Driver {

    public static void main(String[] args) {
        Cube cube = new Cube();
        cube.scramble("U' B2 F2 D' B2 L2 B2 R2 F U B' U2 B2 U' F2 L' B");
        cube.print();
        cube.solve();
        System.out.println();
        System.out.println(cube.getSolution());
    }
}
