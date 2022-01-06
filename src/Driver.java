public class Driver {

    public static void main(String[] args) {
        Cube cube = new Cube();
        cube.scramble("U2 L2 R2 U' F2 U B2 F2 U2 R2 L' B2 D B2 L' B' D2 U F' L2");
        //cube.setColors(args[0]);
        cube.solve();
        //System.out.println(cube.printCubeSolution());
        //System.out.println(cube.getMoveCount());
        cube.printCubeSolution();
        String URL = "https://alg.cubing.net/?alg=" + cube.invertSolution() + "&setup=%2F%2FIgnore_Setup_%26%2345%3B_Solving_Instructions_in_-Moves-_Section%0A" + cube.printCubeSolution();
        System.out.println(URL);
    }
}
