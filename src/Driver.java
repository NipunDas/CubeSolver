public class Driver {

    public static void main(String[] args) {
        Cube cube = new Cube();
        cube.scramble("U2 L2 B2 R' F B2 U' F R2 F D2 B' D2 L2 F L2 F D F2");
        cube.print();
    }
}
