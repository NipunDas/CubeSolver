public class Driver {

    public static void main(String[] args) {
        Cube cube = new Cube();
        cube.scramble("F L' R2 D' F2 R2 U' B2 F2 U' L2 U B2 L' D2 R B U L D' B'");
        cube.print();
        cube.solve();
        System.out.println();
        System.out.println(cube.getSolution());
    }
}
