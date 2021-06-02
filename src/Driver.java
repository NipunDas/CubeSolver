public class Driver {

    public static void main(String[] args) {
        Cube cube = new Cube();
        cube.scramble("U' F D R2 U2 R U R B2 D2 L' F2 L' U2 L2 F2 R D2 R2 F' R");
        cube.print();
        cube.solve();
        System.out.println();
        System.out.println(cube.getSolution());
    }
}
