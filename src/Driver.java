public class Driver {

    public static void main(String[] args) {
        Cube cube = new Cube();
        cube.scramble("U2 F D2 B2 R2 F R2 F2 R2 B2 U F' L2 R2 D2 U L B' R' F'");
        cube.print();
        cube.solve();
        System.out.println();
        System.out.println(cube.getSolution());
    }
}
