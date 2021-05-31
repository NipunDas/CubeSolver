public class Driver {

    public static void main(String[] args) {
        Cube cube = new Cube();
        cube.scramble("D2 B' L2 D2 L2 U2 F R2 F' U2 F U R' B2 F2 R2 F' D2 F' R D");
        cube.print();
        cube.solve();
        System.out.println();
        System.out.println(cube.getSolution());
    }
}
