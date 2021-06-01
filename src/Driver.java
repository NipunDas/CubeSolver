public class Driver {

    public static void main(String[] args) {
        Cube cube = new Cube();
        cube.scramble("D2 R' B U2 L' F' U' B D2 R' F2 U2 L2 F2 L' F2 D2 F2 L2 B2");
        cube.print();
        cube.solve();
        System.out.println();
        System.out.println(cube.getSolution());
    }
}
