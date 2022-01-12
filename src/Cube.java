import java.util.ArrayList;

public class Cube {

    //arrays to represent corners, edges, and a string/arraylist to represent the solution
    private Corner[] corners;
    private Edge[] edges;
    private String solution;
    private ArrayList<String> cubeSolution = new ArrayList<>();

    //each OLL (Orientation of Last Layer) is represented by a number
    //0 = solved, 1 = sune, 2 = anti-sune, 3 = H, 4 = pi, 5 = L, 6 = T, 7 = U
    enum OLL {
        SOLVED, SUNE, ANTISUNE, H, PI, L, T, U;
    }

    public Cube() {
        corners = new Corner[8];
        edges = new Edge[12];

        //all colors in clockwise order starting on U/D face, corner order is UBL UBR UFR UFL DFL DFR DBR DBL
        corners[0] = new Corner('W', 'O', 'B');
        corners[1] = new Corner('W', 'B', 'R');
        corners[2] = new Corner('W', 'R', 'G');
        corners[3] = new Corner('W', 'G', 'O');
        corners[4] = new Corner('Y', 'O', 'G');
        corners[5] = new Corner('Y', 'G', 'R');
        corners[6] = new Corner('Y', 'R', 'B');
        corners[7] = new Corner('Y', 'B', 'O');

        //all edges in order of U/D first (or F/B first if U/D not available), edge order is UB UR UF UL DF DR DB DL FR FL BR BL
        edges[0] = new Edge('W', 'B');
        edges[1] = new Edge('W', 'R');
        edges[2] = new Edge('W', 'G');
        edges[3] = new Edge('W', 'O');
        edges[4] = new Edge('Y', 'G');
        edges[5] = new Edge('Y', 'R');
        edges[6] = new Edge('Y', 'B');
        edges[7] = new Edge('Y', 'O');
        edges[8] = new Edge('G', 'R');
        edges[9] = new Edge('G', 'O');
        edges[10] = new Edge('B', 'R');
        edges[11] = new Edge('B', 'O');

        //initializing OLL enums
        OLL ollEnums;
    }

    //methods to do move on virtual cube
    public void R() {
        Corner tempCorner = corners[2];
        corners[2] = corners[5];
        corners[2].twistCCW();
        corners[5] = corners[6];
        corners[5].twistCW();
        corners[6] = corners[1];
        corners[6].twistCCW();
        corners[1] = tempCorner;
        corners[1].twistCW();

        Edge tempEdge = edges[1];
        edges[1] = edges[8];
        edges[8] = edges[5];
        edges[5] = edges[10];
        edges[10] = tempEdge;
    }

    public void R2() {
        R();
        R();
    }

    public void Rprime() {
        R();
        R();
        R();
    }

    public void U() {
        Corner tempCorner = corners[0];
        corners[0] = corners[3];
        corners[3] = corners[2];
        corners[2] = corners[1];
        corners[1] = tempCorner;

        Edge tempEdge = edges[0];
        edges[0] = edges[3];
        edges[3] = edges[2];
        edges[2] = edges[1];
        edges[1] = tempEdge;
    }

    public void U2() {
        U();
        U();
    }

    public void Uprime() {
        U();
        U();
        U();
    }

    public void L() {
        Corner tempCorner = corners[0];
        corners[0] = corners[7];
        corners[0].twistCCW();
        corners[7] = corners[4];
        corners[7].twistCW();
        corners[4] = corners[3];
        corners[4].twistCCW();
        corners[3] = tempCorner;
        corners[3].twistCW();

        Edge tempEdge = edges[3];
        edges[3] = edges[11];
        edges[11] = edges[7];
        edges[7] = edges[9];
        edges[9] = tempEdge;
    }

    public void L2() {
        L();
        L();
    }

    public void Lprime() {
        L();
        L();
        L();
    }

    public void D() {
        Corner tempCorner = corners[4];
        corners[4] = corners[7];
        corners[7] = corners[6];
        corners[6] = corners[5];
        corners[5] = tempCorner;

        Edge tempEdge = edges[4];
        edges[4] = edges[7];
        edges[7] = edges[6];
        edges[6] = edges[5];
        edges[5] = tempEdge;
    }

    public void D2() {
        D();
        D();
    }

    public void Dprime() {
        D();
        D();
        D();
    }

    public void F() {
        Corner tempCorner = corners[2];
        corners[2] = corners[3];
        corners[2].twistCW();
        corners[3] = corners[4];
        corners[3].twistCCW();
        corners[4] = corners[5];
        corners[4].twistCW();
        corners[5] = tempCorner;
        corners[5].twistCCW();

        Edge tempEdge = edges[2];
        edges[2] = edges[9];
        edges[2].flip();
        edges[9] = edges[4];
        edges[9].flip();
        edges[4] = edges[8];
        edges[4].flip();
        edges[8] = tempEdge;
        edges[8].flip();
    }

    public void F2() {
        F();
        F();
    }

    public void Fprime() {
        F();
        F();
        F();
    }

    public void B() {
        Corner tempCorner = corners[0];
        corners[0] = corners[1];
        corners[0].twistCW();
        corners[1] = corners[6];
        corners[1].twistCCW();
        corners[6] = corners[7];
        corners[6].twistCW();
        corners[7] = tempCorner;
        corners[7].twistCCW();

        Edge tempEdge = edges[0];
        edges[0] = edges[10];
        edges[0].flip();
        edges[10] = edges[6];
        edges[10].flip();
        edges[6] = edges[11];
        edges[6].flip();
        edges[11] = tempEdge;
        edges[11].flip();
    }

    public void B2() {
        B();
        B();
    }

    public void Bprime() {
        B();
        B();
        B();
    }

    //methods to do moves specifically as a part of solution (cancels moves)
    public void solveU() {
        U();
        if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "U-") {
            cubeSolution.remove(cubeSolution.size()-1);
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "U") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("U2");
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "U2") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("U-");
        } else {
            cubeSolution.add("U");
        }
    }

    public void solveUprime() {
        Uprime();
        if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "U") {
            cubeSolution.remove(cubeSolution.size()-1);
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "U-") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("U2");
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "U2") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("U");
        } else {
            cubeSolution.add("U-");
        }
    }

    public void solveU2() {
        U2();
        if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "U2") {
            cubeSolution.remove(cubeSolution.size()-1);
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "U") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("U-");
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "U-") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("U");
        } else {
            cubeSolution.add("U2");
        }
    }

    public void solveR() {
        R();
        if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "R-") {
            cubeSolution.remove(cubeSolution.size()-1);
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "R") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("R2");
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "R2") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("R-");
        } else {
            cubeSolution.add("R");
        }
    }

    public void solveRprime() {
        Rprime();
        if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "R") {
            cubeSolution.remove(cubeSolution.size()-1);
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "R-") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("R2");
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "R2") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("R");
        } else {
            cubeSolution.add("R-");
        }
    }

    public void solveR2() {
        R2();
        if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "R2") {
            cubeSolution.remove(cubeSolution.size()-1);
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "R") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("R-");
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "R-") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("R");
        } else {
            cubeSolution.add("R2");
        }
    }

    public void solveL() {
        L();
        if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "L-") {
            cubeSolution.remove(cubeSolution.size()-1);
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "L") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("L2");
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "L2") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("L-");
        } else {
            cubeSolution.add("L");
        }
    }

    public void solveLprime() {
        Lprime();
        if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "L") {
            cubeSolution.remove(cubeSolution.size()-1);
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "L-") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("L2");
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "L2") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("L");
        } else {
            cubeSolution.add("L-");
        }
    }

    public void solveL2() {
        L2();
        if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "L2") {
            cubeSolution.remove(cubeSolution.size()-1);
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "L") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("L-");
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "L-") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("L");
        } else {
            cubeSolution.add("L2");
        }
    }

    public void solveF() {
        F();
        if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "F-") {
            cubeSolution.remove(cubeSolution.size()-1);
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "F") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("F2");
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "F2") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("F-");
        } else {
            cubeSolution.add("F");
        }
    }

    public void solveFprime() {
        Fprime();
        if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "F") {
            cubeSolution.remove(cubeSolution.size()-1);
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "F-") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("F2");
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "F2") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("F");
        } else {
            cubeSolution.add("F-");
        }
    }

    public void solveF2() {
        F2();
        if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "F2") {
            cubeSolution.remove(cubeSolution.size()-1);
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "F") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("F-");
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "F-") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("F");
        } else {
            cubeSolution.add("F2");
        }
    }

    public void solveB() {
        B();
        if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "B-") {
            cubeSolution.remove(cubeSolution.size()-1);
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "B") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("B2");
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "B2") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("B-");
        } else {
            cubeSolution.add("B");
        }
    }

    public void solveBprime() {
        Bprime();
        if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "B") {
            cubeSolution.remove(cubeSolution.size()-1);
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "B-") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("B2");
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "B2") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("B");
        } else {
            cubeSolution.add("B-");
        }
    }

    public void solveB2() {
        B2();
        if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "B2") {
            cubeSolution.remove(cubeSolution.size()-1);
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "B") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("B-");
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "B-") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("B");
        } else {
            cubeSolution.add("B2");
        }
    }

    public void solveD() {
        D();
        if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "D-") {
            cubeSolution.remove(cubeSolution.size()-1);
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "D") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("D2");
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "D2") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("D-");
        } else {
            cubeSolution.add("D");
        }
    }

    public void solveDprime() {
        Dprime();
        if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "D") {
            cubeSolution.remove(cubeSolution.size()-1);
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "D-") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("D2");
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "D2") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("D");
        } else {
            cubeSolution.add("D-");
        }
    }

    public void solveD2() {
        D2();
        if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "D2") {
            cubeSolution.remove(cubeSolution.size()-1);
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "D") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("D-");
        } else if (cubeSolution.size() > 0 && cubeSolution.get(cubeSolution.size()-1) == "D-") {
            cubeSolution.remove(cubeSolution.size()-1);
            cubeSolution.add("D");
        } else {
            cubeSolution.add("D2");
        }
    }

    public void print() {
        System.out.println("Top Face:");
        System.out.println(corners[0].getC1() + " " + edges[0].getC1() + " " + corners[1].getC1());
        System.out.println(edges[3].getC1() + " W " + edges[1].getC1());
        System.out.println(corners[3].getC1() + " " + edges[2].getC1() + " " + corners[2].getC1());
        System.out.println("Front Face:");
        System.out.println(corners[3].getC2() + " " + edges[2].getC2() + " " + corners[2].getC3());
        System.out.println(edges[9].getC1() + " G " + edges[8].getC1());
        System.out.println(corners[4].getC3() + " " + edges[4].getC2() + " " + corners[5].getC2());
        System.out.println("Right Face:");
        System.out.println(corners[2].getC2() + " " + edges[1].getC2() + " " + corners[1].getC3());
        System.out.println(edges[8].getC2() + " R " + edges[10].getC2());
        System.out.println(corners[5].getC3() + " " + edges[5].getC2() + " " + corners[6].getC2());
        System.out.println("Back Face:");
        System.out.println(corners[1].getC2() + " " + edges[0].getC2() + " " + corners[0].getC3());
        System.out.println(edges[10].getC1() + " B " + edges[11].getC1());
        System.out.println(corners[6].getC3() + " " + edges[6].getC2() + " " + corners[7].getC2());
        System.out.println("Left Face:");
        System.out.println(corners[0].getC2() + " " + edges[3].getC2() + " " + corners[3].getC3());
        System.out.println(edges[11].getC2() + " O " + edges[9].getC2());
        System.out.println(corners[7].getC3() + " " + edges[7].getC2() + " " + corners[4].getC2());
        System.out.println("Bottom Face:");
        System.out.println(corners[4].getC1() + " " + edges[4].getC1() + " " + corners[5].getC1());
        System.out.println(edges[7].getC1() + " Y " + edges[5].getC1());
        System.out.println(corners[7].getC1() + " " + edges[6].getC1() + " " + corners[6].getC1());
    }

    public void scramble(String scramble) {
        String temp = "";
        for (int i = 0; i < scramble.length(); i++) {
            if (scramble.charAt(i) != ' ') {
                temp += scramble.charAt(i);
            }
            if (scramble.charAt(i) == ' ' || i == scramble.length() - 1) {
                switch (temp) {
                    case "R" -> R();
                    case "R'" -> Rprime();
                    case "R2" -> R2();
                    case "L" -> L();
                    case "L'" -> Lprime();
                    case "L2" -> L2();
                    case "U" -> U();
                    case "U'" -> Uprime();
                    case "U2" -> U2();
                    case "D" -> D();
                    case "D'" -> Dprime();
                    case "D2" -> D2();
                    case "F" -> F();
                    case "F'" -> Fprime();
                    case "F2" -> F2();
                    case "B" -> B();
                    case "B'" -> Bprime();
                    case "B2" -> B2();
                    default -> System.out.println("Error: scramble is not valid");
                }
                temp = "";
            }
        }
    }

    //setting colors on cube from OpenCV scanning
    public void setColors(String colors) {
        //side 1 (white)
        corners[0].setC1(colors.charAt(0));
        edges[0].setC1(colors.charAt(1));
        corners[1].setC1(colors.charAt(2));
        edges[3].setC1(colors.charAt(3));
        edges[1].setC1(colors.charAt(5));
        corners[3].setC1(colors.charAt(6));
        edges[2].setC1(colors.charAt(7));
        corners[2].setC1(colors.charAt(8));
        //side 2 (green)
        corners[3].setC2(colors.charAt(9));
        edges[2].setC2(colors.charAt(10));
        corners[2].setC3(colors.charAt(11));
        edges[9].setC1(colors.charAt(12));
        edges[8].setC1(colors.charAt(14));
        corners[4].setC3(colors.charAt(15));
        edges[4].setC2(colors.charAt(16));
        corners[5].setC2(colors.charAt(17));
        //side 3 (red)
        corners[2].setC2(colors.charAt(18));
        edges[1].setC2(colors.charAt(19));
        corners[1].setC3(colors.charAt(20));
        edges[8].setC2(colors.charAt(21));
        edges[10].setC2(colors.charAt(23));
        corners[5].setC3(colors.charAt(24));
        edges[5].setC2(colors.charAt(25));
        corners[6].setC2(colors.charAt(26));
        //side 4 (blue)
        corners[1].setC2(colors.charAt(27));
        edges[0].setC2(colors.charAt(28));
        corners[0].setC3(colors.charAt(29));
        edges[10].setC1(colors.charAt(30));
        edges[11].setC1(colors.charAt(32));
        corners[6].setC3(colors.charAt(33));
        edges[6].setC2(colors.charAt(34));
        corners[7].setC2(colors.charAt(35));
        //side 5 (orange)
        corners[0].setC2(colors.charAt(36));
        edges[3].setC2(colors.charAt(37));
        corners[3].setC3(colors.charAt(38));
        edges[11].setC2(colors.charAt(39));
        edges[9].setC2(colors.charAt(41));
        corners[7].setC3(colors.charAt(42));
        edges[7].setC2(colors.charAt(43));
        corners[4].setC2(colors.charAt(44));
        //side 6 (yellow)
        corners[7].setC1(colors.charAt(45));
        edges[7].setC1(colors.charAt(46));
        corners[4].setC1(colors.charAt(47));
        edges[6].setC1(colors.charAt(48));
        edges[4].setC1(colors.charAt(50));
        corners[6].setC1(colors.charAt(51));
        edges[5].setC1(colors.charAt(52));
        corners[5].setC1(colors.charAt(53));
    }

    public String getSolution() {
        return solution;
    }

    public int getMoveCount() {
        return cubeSolution.size();
    }

    public String printCubeSolution() {
        solution = "";
        for (String move : cubeSolution) {
            solution += move;
            solution += "_";
        }
        solution = solution.substring(0, solution.length()-1);
        return solution;
    }

    public void solve() {
        cubeSolution.clear();
        solveEO();
        solveCross();
        solveBlueOrange();
        solveBlueRed();
        solveGreenOrange();
        solveGreenRed();
        solveOLL();
        solvePLL();
    }

    //orients all edges
    public void solveEO() {
        //first loop does F, B, and if necessary other random moves to get the number of mis-oriented edges to be a
        //multiple of 4
        while (numMisOriented()%4 != 0) {
            if (numMisOrientedSubset(new int[]{2, 8, 9, 4}) % 2 == 1){
                solveF();
            } else if (numMisOrientedSubset(new int[]{0, 10, 11, 6}) % 2 == 1) {
                solveB();
            } else {
                if (edges[2].isOriented() != edges[1].isOriented()) {
                    solveU();
                } else if (edges[2].isOriented() != edges[3].isOriented()) {
                    solveUprime();
                } else if (edges[2].isOriented() != edges[0].isOriented()) {
                    solveU2();
                } else if (edges[4].isOriented() != edges[5].isOriented()) {
                    solveDprime();
                } else if (edges[4].isOriented() != edges[7].isOriented()) {
                    solveD();
                } else if (edges[4].isOriented() != edges[6].isOriented()) {
                    solveD2();
                } else if (edges[8].isOriented() != edges[1].isOriented()) {
                    solveRprime();
                } else if (edges[8].isOriented() != edges[5].isOriented()) {
                    solveR();
                } else if (edges[8].isOriented() != edges[10].isOriented()) {
                    solveR2();
                } else if (edges[9].isOriented() != edges[3].isOriented()) {
                    solveL();
                } else if (edges[9].isOriented() != edges[7].isOriented()) {
                    solveLprime();
                } else if (edges[9].isOriented() != edges[11].isOriented()) {
                    solveL2();
                }
            }
        }
        //second loop orients all edges
        while (numMisOriented() > 0) {
            if (numMisOrientedSubset(new int[]{0, 10, 11, 6}) == 4) {
                solveB();
            }
            if (numMisOrientedSubset(new int[]{2, 8, 9, 4}) == 4) {
                solveF();
            }
            if (numMisOriented() > 0) {
                if (numMisOrientedSubset(new int[]{0, 1, 2, 3}) > 1) {
                    if (!edges[1].isOriented()) {
                        solveRprime();
                    } else if (!edges[3].isOriented()) {
                        solveL();
                    } else if (!edges[0].isOriented()) {
                        if (edges[4].isOriented()) {
                            solveB2();
                        } else if (edges[8].isOriented()) {
                            solveU();
                            solveRprime();
                        } else if (edges[9].isOriented()) {
                            solveUprime();
                            solveL();
                        }
                    }
                }
                if (numMisOrientedSubset(new int[]{4, 5, 6, 7}) > 1) {
                    if (!edges[5].isOriented()) {
                        solveR();
                    } else if (!edges[7].isOriented()) {
                        solveLprime();
                    } else if (!edges[6].isOriented()) {
                        if (edges[2].isOriented()) {
                            solveB2();
                        } else if (edges[8].isOriented()) {
                            solveD();
                            solveR();
                        } else if (edges[9].isOriented()) {
                            solveDprime();
                            solveLprime();
                        }
                    }
                }
                if (numMisOrientedSubset(new int[]{1, 8, 5, 10}) > 1) {
                    if (!edges[1].isOriented()) {
                        solveU();
                    } else if (!edges[5].isOriented()) {
                        solveDprime();
                    } else if (!edges[10].isOriented()) {
                        if (edges[9].isOriented()) {
                            solveB2();
                        } else {
                            solveRprime();
                        }
                    }
                }
                if (numMisOrientedSubset(new int[]{3, 9, 7, 11}) > 1) {
                    if (!edges[3].isOriented()) {
                        solveUprime();
                    } else if (!edges[7].isOriented()) {
                        solveD();
                    } else if (!edges[11].isOriented()) {
                        if (edges[8].isOriented()) {
                            solveB2();
                        } else {
                            solveL();
                        }
                    }
                }
                if (edges[2].isOriented()) {
                    if (!edges[1].isOriented()) {
                        solveU();
                    } else if (!edges[3].isOriented()) {
                        solveUprime();
                    } else if (!edges[0].isOriented()) {
                        solveU2();
                    }
                }
                if (edges[4].isOriented()) {
                    if (!edges[5].isOriented()) {
                        solveDprime();
                    } else if (!edges[7].isOriented()) {
                        solveD();
                    } else if (!edges[6].isOriented()) {
                        solveD2();
                    }
                }
                if (edges[8].isOriented()) {
                    if (!edges[1].isOriented()) {
                        solveRprime();
                    } else if (!edges[5].isOriented()) {
                        solveR();
                    } else if (!edges[10].isOriented()) {
                        solveR2();
                    }
                }
                if (edges[9].isOriented()) {
                    if (!edges[3].isOriented()) {
                        solveL();
                    } else if (!edges[7].isOriented()){
                        solveLprime();
                    } else if (!edges[11].isOriented()) {
                        solveL2();
                    }
                }
            }
        }
    }

    //solves the yellow cross
    public void solveCross() {
        //solves yellow-blue cross piece
        while (!edges[6].matches("YB")) {
            if (edges[0].matches("YB")) {
                solveB2();
            } else if (edges[1].matches("YB")) {
                solveR2();
            } else if (edges[2].matches("YB")) {
                solveF2();
            } else if (edges[3].matches("YB")) {
                solveL2();
            } else if (edges[4].matches("YB")) {
                solveD2();
            } else if (edges[5].matches("YB")) {
                solveD();
            } else if (edges[7].matches("YB")) {
                solveDprime();
            } else if (edges[8].matches("YB")) {
                solveRprime();
            } else if (edges[9].matches("YB")) {
                solveL();
            } else if (edges[10].matches("YB")) {
                solveR();
            } else if (edges[11].matches("YB")) {
                solveLprime();
            }
        }
        //solves yellow-orange cross piece
        while (!edges[7].matches("YO")) {
            if (edges[0].matches("YO")) {
                solveUprime();
            } else if (edges[1].matches("YO")) {
                solveU2();
            } else if (edges[2].matches("YO")) {
                solveU();
            } else if (edges[3].matches("YO")) {
                solveL2();
            } else if (edges[4].matches("YO")) {
                solveF2();
            } else if (edges[5].matches("YO")) {
                solveR2();
            } else if (edges[8].matches("YO")) {
                solveF2();
            } else if (edges[9].matches("YO")) {
                solveL();
            } else if (edges[10].matches("YO")) {
                solveRprime();
            } else if (edges[11].matches("YO")) {
                solveLprime();
            }
        }
        //solves yellow-red cross piece
        while (!edges[5].matches("YR")) {
            if (edges[0].matches("YR")) {
                solveU();
            } else if (edges[1].matches("YR")) {
                solveR2();
            } else if (edges[2].matches("YR")) {
                solveUprime();
            } else if (edges[3].matches("YR")) {
                solveU2();
            } else if (edges[4].matches("YR")) {
                solveF2();
            } else if (edges[8].matches("YR")) {
                solveRprime();
            } else if (edges[9].matches("YR")) {
                solveF2();
            } else if (edges[10].matches("YR")) {
                solveR();
            } else if (edges[11].matches("YR")) {
                solveD2();
                solveLprime();
                solveD2();
            }
        }
        //solves yellow-green cross edge
        while (!edges[4].matches("YG")) {
            if (edges[0].matches("YG")) {
                solveU2();
            } else if (edges[1].matches("YG")) {
                solveU();
            } else if (edges[2].matches("YG")) {
                solveF2();
            } else if (edges[3].matches("YG")) {
                solveUprime();
            } else if (edges[8].matches("YG")) {
                solveD();
                solveRprime();
                solveDprime();
            } else if (edges[9].matches("YG")) {
                solveDprime();
                solveL();
                solveD();
            } else if (edges[10].matches("YG")) {
                solveD();
                solveR();
                solveDprime();
            } else if (edges[11].matches("YG")) {
                solveDprime();
                solveLprime();
                solveD();
            }
        }
    }

    //solves blue-orange F2L pair into back left
    public void solveBlueOrange() {
        //moves orange-blue edge into UL position (edge 3)
        if (edges[0].matches("BO")) {
            solveUprime();
        } else if (edges[1].matches("BO")) {
            solveU2();
        } else if (edges[2].matches("BO")) {
            solveU();
        } else if (edges[8].matches("BO")) {
            solveR();
            solveU2();
            solveRprime();
        } else if (edges[9].matches("BO")) {
            solveLprime();
            solveUprime();
            solveL();
            solveU();
        } else if (edges[10].matches("BO")) {
            solveRprime();
            solveU2();
            solveR();
        } else if (edges[11].matches("BO")) {
            solveL();
            solveU();
            solveLprime();
            solveUprime();
        }

        //pairs and inserts corner with edge
        while (!(edges[11].matches("BO") && corners[7].matches("YBO"))) {
            if (corners[0].matches("YBO")) {
                solveL();
                solveU2();
                solveLprime();
            } else if (corners[0].matches("BOY")) {
                solveU();
                solveL();
                solveUprime();
                solveLprime();
            } else if (corners[0].matches("OYB")) {
                solveUprime();
                solveL();
                solveUprime();
                solveLprime();
                solveU2();
            } else if (corners[1].matches("YBO")) {
                solveL();
                solveU2();
                solveLprime();
            } else if (corners[1].matches("BOY")) {
                solveR();
                solveU();
                solveRprime();
                solveUprime();
            } else if (corners[1].matches("OYB")) {
                solveUprime();
                solveL();
                solveU();
                solveLprime();
            } else if (corners[2].matches("YBO")) {
                solveL();
                solveU();
                solveLprime();
            } else if (corners[2].matches("BOY")) {
                solveUprime();
                solveR();
                solveU2();
                solveRprime();
                solveUprime();
            } else if (corners[2].matches("OYB")) {
                solveL();
                solveUprime();
                solveLprime();
            } else if (corners[3].matches("YBO")) {
                solveR();
                solveUprime();
                solveRprime();
                solveUprime();
                solveR();
                solveUprime();
                solveRprime();
                solveU2();
            } else if (corners[3].matches("BOY")) {
                solveL();
                solveU();
                solveLprime();
            } else if (corners[3].matches("OYB")) {
                solveUprime();
                solveRprime();
                solveU2();
                solveR();
                solveUprime();
            } else if (corners[4].matches("YBO")) {
                solveU2();
                solveLprime();
                solveU();
                solveL();
                solveU();
            } else if (corners[4].matches("BOY")) {
                solveU();
                solveLprime();
                solveU();
                solveL();
                solveU2();
            } else if (corners[4].matches("OYB")) {
                solveUprime();
                solveLprime();
                solveU2();
                solveL();
                solveUprime();
            } else if (corners[5].matches("YBO")) {
                solveU();
                solveR();
                solveUprime();
                solveRprime();
            } else if (corners[5].matches("BOY")) {
                solveU();
                solveR();
                solveUprime();
                solveRprime();
            } else if (corners[5].matches("OYB")) {
                solveU2();
                solveR();
                solveUprime();
                solveRprime();
                solveU2();
            } else if (corners[6].matches("YBO")) {
                solveRprime();
                solveU();
                solveR();
                solveUprime();
            } else if (corners[6].matches("BOY")) {
                solveUprime();
                solveRprime();
                solveU();
                solveR();
            } else if (corners[6].matches("OYB")) {
                solveU();
                solveRprime();
                solveUprime();
                solveR();
            } else if (corners[7].matches("YBO")) {
                solveUprime();
                solveD();
                solveL();
                solveU();
                solveLprime();
                solveDprime();
            } else if (corners[7].matches("BOY")) {
                solveL();
                solveU();
                solveLprime();
            } else if (corners[7].matches("OYB")) {
                solveL();
                solveUprime();
                solveLprime();
            }
        }
    }

    //solves blue-red F2L pair into back right
    public void solveBlueRed() {
        //moves red-blue edge into UR position (edge 1)
        if (edges[0].matches("BR")) {
            solveU();
        } else if (edges[2].matches("BR")) {
            solveUprime();
        } else if (edges[3].matches("BR")) {
            solveU2();
        } else if (edges[8].matches("BR")) {
            solveR();
            solveU();
            solveRprime();
            solveUprime();
        } else if (edges[9].matches("BR")) {
            solveLprime();
            solveU2();
            solveL();
        } else if (edges[10].matches("BR")) {
            solveRprime();
            solveU();
            solveR();
            solveUprime();
        }

        //pairs and inserts corner with edge
        while (!(edges[10].matches("BR") && corners[6].matches("YRB"))) {
            if (corners[0].matches("YRB")) {
                solveRprime();
                solveU2();
                solveR();
            } else if (corners[0].matches("RBY")) {
                solveU();
                solveRprime();
                solveUprime();
                solveR();
            } else if (corners[0].matches("BYR")) {
                solveLprime();
                solveUprime();
                solveL();
                solveU();
            } else if (corners[1].matches("YRB")) {
                solveRprime();
                solveU2();
                solveR();
            } else if (corners[1].matches("RBY")) {
                solveU();
                solveRprime();
                solveU();
                solveR();
                solveU2();
            } else if (corners[1].matches("BYR")) {
                solveUprime();
                solveRprime();
                solveU();
                solveR();
            } else if (corners[2].matches("YRB")) {
                solveU2();
                solveRprime();
                solveU();
                solveR();
                solveU();
                solveRprime();
                solveU();
                solveR();
            } else if (corners[2].matches("RBY")) {
                solveUprime();
                solveR();
                solveU2();
                solveRprime();
                solveUprime();
            } else if (corners[2].matches("BYR")) {
                solveR();
                solveUprime();
                solveRprime();
            } else if (corners[3].matches("YRB")) {
                solveRprime();
                solveUprime();
                solveR();
            } else if (corners[3].matches("RBY")) {
                solveR();
                solveU();
                solveRprime();
            } else if (corners[3].matches("BYR")) {
                solveUprime();
                solveRprime();
                solveU2();
                solveR();
                solveUprime();
            } else if (corners[4].matches("YRB")) {
                solveUprime();
                solveLprime();
                solveU();
                solveL();
            } else if (corners[4].matches("RBY")) {
                solveU2();
                solveLprime();
                solveU();
                solveL();
                solveU2();
            } else if (corners[4].matches("BYR")) {
                solveUprime();
                solveLprime();
                solveU2();
                solveL();
                solveUprime();
            } else if (corners[5].matches("YRB")) {
                solveU2();
                solveR();
                solveUprime();
                solveRprime();
                solveUprime();
            } else if (corners[5].matches("RBY")) {
                solveU();
                solveR();
                solveU();
                solveRprime();
                solveU2();
            } else if (corners[5].matches("BYR")) {
                solveUprime();
                solveR();
                solveUprime();
                solveRprime();
                solveU2();
            } else if (corners[6].matches("YRB")) {
                solveUprime();
                solveD();
                solveRprime();
                solveU();
                solveR();
                solveDprime();
            } else if (corners[6].matches("RBY")) {
                solveRprime();
                solveU();
                solveR();
            } else if (corners[6].matches("BYR")) {
                solveRprime();
                solveUprime();
                solveR();
            }
        }
    }

    //solves green-orange F2L pair into front left
    public void solveGreenOrange() {
        //moves green-orange edge into UL position (edge 3)
        if (edges[0].matches("GO")) {
            solveUprime();
        } else if (edges[1].matches("GO")) {
            solveU2();
        } else if (edges[2].matches("GO")) {
            solveU();
        } else if (edges[8].matches("GO")) {
            solveR();
            solveU2();
            solveRprime();
        } else if (edges[9].matches("GO")) {
            solveLprime();
            solveU();
            solveL();
            solveUprime();
        }

        //pairs and inserts corner with edge
        while (!(edges[9].matches("GO") && corners[4].matches("YOG"))) {
            if (corners[0].matches("YOG")) {
                solveU2();
                solveLprime();
                solveU();
                solveL();
                solveU();
            } else if (corners[0].matches("OGY")) {
                solveU();
                solveR();
                solveU2();
                solveRprime();
                solveU();
            } else if (corners[0].matches("GYO")) {
                solveU2();
                solveR();
                solveUprime();
                solveRprime();
                solveU2();
            } else if (corners[1].matches("YOG")) {
                solveLprime();
                solveUprime();
                solveL();
            } else if (corners[1].matches("OGY")) {
                solveLprime();
                solveU();
                solveL();
            } else if (corners[1].matches("GYO")) {
                solveUprime();
                solveLprime();
                solveU2();
                solveL();
                solveUprime();
            } else if (corners[2].matches("YOG")) {
                solveLprime();
                solveU2();
                solveL();
            } else if (corners[2].matches("OGY")) {
                solveU();
                solveLprime();
                solveUprime();
                solveL();
            } else if (corners[2].matches("GYO")) {
                solveU2();
                solveLprime();
                solveUprime();
                solveL();
                solveUprime();
            } else if (corners[3].matches("YOG")) {
                solveLprime();
                solveU2();
                solveL();
            } else if (corners[3].matches("OGY")) {
                solveU();
                solveLprime();
                solveU();
                solveL();
                solveU2();
            } else if (corners[3].matches("GYO")) {
                solveUprime();
                solveLprime();
                solveU();
                solveL();
            } else if (corners[4].matches("YOG")) {
                solveU();
                solveDprime();
                solveLprime();
                solveUprime();
                solveL();
                solveD();
            } else if (corners[4].matches("OGY")) {
                solveLprime();
                solveU();
                solveL();
            } else if (corners[4].matches("GYO")) {
                solveLprime();
                solveUprime();
                solveL();
            } else if (corners[5].matches("YOG")) {
                solveR();
                solveU();
                solveRprime();
                solveUprime();
            } else if (corners[5].matches("OGY")) {
                solveUprime();
                solveR();
                solveU();
                solveRprime();
            } else if (corners[5].matches("GYO")) {
                solveU();
                solveR();
                solveUprime();
                solveRprime();
            }
        }
    }

    //solves green-red F2L pair into front right
    public void solveGreenRed() {
        //moves green-orange edge into UR position (edge 1)
        if (edges[0].matches("GR")) {
            solveU();
        } else if (edges[2].matches("GR")) {
            solveUprime();
        } else if (edges[3].matches("GR")) {
            solveU2();
        } else if (edges[8].matches("GR")) {
            solveR();
            solveU();
            solveRprime();
            solveUprime();
        }

        //pairs and inserts corner with edge
        while (!(edges[8].matches("GR") && corners[5].matches("YGR"))) {
            if (corners[0].matches("YGR")) {
                solveR();
                solveU();
                solveRprime();
            } else if (corners[0].matches("RYG")) {
                solveR();
                solveUprime();
                solveRprime();
            } else if (corners[0].matches("GRY")) {
                solveU();
                solveR();
                solveU2();
                solveRprime();
                solveU();
            } else if (corners[1].matches("YGR")) {
                solveU2();
                solveR();
                solveUprime();
                solveRprime();
                solveUprime();
            } else if (corners[1].matches("RYG")) {
                solveU();
                solveRprime();
                solveU2();
                solveR2();
                solveU();
                solveR2();
                solveU();
                solveR();
            } else if (corners[1].matches("GRY")) {
                solveU();
                solveRprime();
                solveDprime();
                solveR();
                solveUprime();
                solveRprime();
                solveD();
                solveR();
            } else if (corners[2].matches("YGR")) {
                solveR();
                solveU2();
                solveRprime();
            } else if (corners[2].matches("RYG")) {
                solveUprime();
                solveR();
                solveUprime();
                solveRprime();
                solveU2();
            } else if (corners[2].matches("GRY")) {
                solveU();
                solveR();
                solveUprime();
                solveRprime();
            } else if (corners[3].matches("YGR")) {
                solveR();
                solveU2();
                solveRprime();
            } else if (corners[3].matches("RYG")) {
                solveUprime();
                solveR();
                solveU();
                solveRprime();
            } else if (corners[3].matches("GRY")) {
                solveU2();
                solveR();
                solveU();
                solveRprime();
                solveU();
            } else if (corners[5].matches("YGR")) {
                solveRprime();
                solveUprime();
                solveRprime();
                solveUprime();
                solveRprime();
                solveU();
                solveR();
                solveU();
                solveR();
            } else if (corners[5].matches("RYG")) {
                solveR();
                solveUprime();
                solveRprime();
            } else if (corners[5].matches("GRY")) {
                solveR();
                solveU();
                solveRprime();
            }
        }
    }

    //solves OLL (makes all top yellow)
    public void solveOLL() {
        switch (getOLL()) {
            case SUNE -> {
                while (corners[3].getC1() != 'W') {
                    solveU();
                }
                solveR();
                solveU();
                solveRprime();
                solveU();
                solveR();
                solveU2();
                solveRprime();
            }
            case ANTISUNE -> {
                while (corners[1].getC1() != 'W') {
                    solveU();
                }
                solveR();
                solveU2();
                solveRprime();
                solveUprime();
                solveR();
                solveUprime();
                solveRprime();
            }
            case H -> {
                while (corners[2].getC3() != 'W') {
                    solveU();
                }
                solveR();
                solveU2();
                solveRprime();
                solveUprime();
                solveR();
                solveU();
                solveRprime();
                solveUprime();
                solveR();
                solveUprime();
                solveRprime();
            }
            case PI -> {
                while (corners[0].getC2() != 'W' || corners[3].getC3() != 'W') {
                    solveU();
                }
                solveR();
                solveU2();
                solveR2();
                solveUprime();
                solveR2();
                solveUprime();
                solveR2();
                solveU2();
                solveR();
            }
            case L -> {
                while (corners[0].getC1() != 'W' || corners[3].getC3() != 'W') {
                    solveU();
                }
                solveR2();
                solveDprime();
                solveR();
                solveUprime();
                solveRprime();
                solveD();
                solveR();
                solveU();
                solveR();
            }
            case T -> {
                while (corners[0].getC1() != 'W' || corners[1].getC1() != 'W') {
                    solveU();
                }
                solveRprime();
                solveUprime();
                solveRprime();
                solveDprime();
                solveR();
                solveU();
                solveRprime();
                solveD();
                solveR2();
            }
            case U -> {
                while (corners[2].getC1() != 'W' || corners[3].getC1() != 'W') {
                    solveU();
                }
                solveR2();
                solveDprime();
                solveR();
                solveU2();
                solveRprime();
                solveD();
                solveR();
                solveU2();
                solveR();
            }
        }
    }

    //solves PLL (Permutation of Last Layer) (rest of cube)
    public void solvePLL() {
        //solve corners
        while (!corners[0].matches("WOB") || !corners[1].matches("WBR") || !corners[2].matches("WRG") || !corners[3].matches("WGO")) {
            if (corners[0].getC2() == corners[3].getC3() && corners[2].getC3() == corners[3].getC2()) {
                solveU();
            } else if (corners[0].getC2() == corners[3].getC3()) {
                TPerm();
            } else if (corners[2].getC3() == corners[3].getC2()) {
                solveU();
                TPerm();
            } else if (corners[0].getC3() == corners[1].getC2()) {
                solveUprime();
                TPerm();
            } else if (corners[1].getC3() == corners[2].getC2()) {
                solveU2();
                TPerm();
            } else {
                YPerm();
            }
        }
        //solve edges
        if (edges[1].matches("WR")) {
            if (edges[2].getC2() == corners[3].getC3()) {
                solveUprime();
                BadUPerm();
            } else {
                solveUprime();
                GoodUPerm();
            }
        } else if (edges[2].matches("WG")) {
            if (edges[3].getC2() == corners[0].getC3()) {
                solveU2();
                BadUPerm();
            } else {
                solveU2();
                GoodUPerm();
            }
        } else if (edges[3].matches("WO")) {
            if (edges[0].getC2() == corners[1].getC3()) {
                solveU();
                BadUPerm();
            } else {
                solveU();
                GoodUPerm();
            }
        } else if (edges[0].matches("WB")) {
            if (edges[1].getC2() == corners[2].getC3()) {
                BadUPerm();
            } else {
                GoodUPerm();
            }
        } else {
            if (edges[2].getC2() == 'B') {
                HPerm();
            } else if (edges[2].getC2() == 'O') {
                solveUprime();
                ZPerm();
            } else if (edges[2].getC2() == 'R') {
                ZPerm();
            }
        }

        //AUF
        while (!corners[0].matches("WOB") || !corners[1].matches("WBR") || !corners[2].matches("WRG") || !corners[3].matches("WGO")) {
            solveU();
        }
    }

    //helper methods for solving process
    public int numMisOriented() {
        int count = 0;
        for (Edge edge : edges) {
            if (!edge.isOriented()) {
                count++;
            }
        }
        return count;
    }

    public int numMisOrientedSubset(int[] subset) {
        int count = 0;
        for (int j : subset) {
            if (!edges[j].isOriented()) {
                count++;
            }
        }
        return count;
    }

    public int returnNumCornersOriented() {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (corners[i].getC1() == 'W') {
                count++;
            }
        }
        return count;
    }

    //0 = solved, 1 = sune, 2 = anti-sune, 3 = H, 4 = pi, 5 = L, 6 = T, 7 = U
    public OLL getOLL() {
        int count = returnNumCornersOriented();
        if (count == 4) {
            return OLL.SOLVED;
        } else if (count == 1) {
            if (corners[3].getC1() == 'W') {
                if (corners[2].getC3() == 'W') {
                    return OLL.SUNE;
                } else {
                    return OLL.ANTISUNE;
                }
            } else {
                if (corners[3].getC3() == 'W') {
                    return OLL.SUNE;
                } else {
                    return OLL.ANTISUNE;
                }
            }
        } else if (count == 0) {
            if (corners[0].getC3() == 'W' || corners[2].getC3() == 'W' && !(corners[0].getC3() == 'W' && corners[2].getC3() == 'W')) {
                return OLL.PI;
            } else {
                return OLL.H;
            }
        } else {
            if (corners[0].getC1() == 'W' || corners[1].getC1() == 'W' && !(corners[0].getC1() == 'W' || corners[1].getC1() == 'W')) {
                return OLL.L;
            } else {
                if (corners[0].getC1() == 'W' && corners[1].getC1() == 'W') {
                    if (corners[2].getC2() == 'W') {
                        return OLL.T;
                    } else {
                        return OLL.U;
                    }
                } else if (corners[1].getC1() == 'W' && corners[2].getC1() == 'W') {
                    if (corners[3].getC2() == 'W') {
                        return OLL.T;
                    } else {
                        return OLL.U;
                    }
                } else if (corners[2].getC1() == 'W' && corners[3].getC1() == 'W') {
                    if (corners[0].getC2() == 'W') {
                        return OLL.T;
                    } else {
                        return OLL.U;
                    }
                } else {
                    if (corners[1].getC2() == 'W') {
                        return OLL.T;
                    } else {
                        return OLL.U;
                    }
                }
            }
        }
    }

    //PLL algorithms
    public void GoodUPerm() {
        solveR();
        solveUprime();
        solveR();
        solveU();
        solveR();
        solveU();
        solveR();
        solveUprime();
        solveRprime();
        solveUprime();
        solveR2();
    }

    public void BadUPerm() {
        solveR2();
        solveU();
        solveR();
        solveU();
        solveRprime();
        solveUprime();
        solveRprime();
        solveUprime();
        solveRprime();
        solveU();
        solveRprime();
    }

    public void ZPerm() {
        solveR();
        solveU();
        solveRprime();
        solveU();
        solveRprime();
        solveUprime();
        solveRprime();
        solveU();
        solveR();
        solveUprime();
        solveRprime();
        solveUprime();
        solveR2();
        solveU();
        solveR();
    }

    public void HPerm() {
        solveR2();
        solveU2();
        solveR();
        solveU2();
        solveR2();
        solveU2();
        solveR2();
        solveU2();
        solveR();
        solveU2();
        solveR2();
    }

    public void TPerm() {
        solveR();
        solveU();
        solveRprime();
        solveUprime();
        solveRprime();
        solveF();
        solveR2();
        solveUprime();
        solveRprime();
        solveUprime();
        solveR();
        solveU();
        solveRprime();
        solveFprime();
    }

    public void YPerm() {
        solveF();
        solveR();
        solveUprime();
        solveRprime();
        solveUprime();
        solveR();
        solveU();
        solveRprime();
        solveFprime();
        solveR();
        solveU();
        solveRprime();
        solveUprime();
        solveRprime();
        solveF();
        solveR();
        solveFprime();
    }

    public String invertSolution() {
        String inverse = "";
        for (int i = cubeSolution.size()-1; i >= 0; i--) {
            switch(cubeSolution.get(i)) {
                case "U":
                    inverse += "U-";
                    break;
                case "U-":
                    inverse += "U";
                    break;
                case "U2":
                    inverse += "U2";
                    break;
                case "D":
                    inverse += "D-";
                    break;
                case "D-":
                    inverse += "D";
                    break;
                case "D2":
                    inverse += "D2";
                    break;
                case "F":
                    inverse += "F-";
                    break;
                case "F-":
                    inverse += "F";
                    break;
                case "F2":
                    inverse += "F2";
                    break;
                case "B":
                    inverse += "B-";
                    break;
                case "B-":
                    inverse += "B";
                    break;
                case "B2":
                    inverse += "B2";
                    break;
                case "R":
                    inverse += "R-";
                    break;
                case "R-":
                    inverse += "R";
                    break;
                case "R2":
                    inverse += "R2";
                    break;
                case "L":
                    inverse += "L-";
                    break;
                case "L-":
                    inverse += "L";
                    break;
                case "L2":
                    inverse += "L2";
                    break;
            }
            inverse += "_";
        }
        inverse = inverse.substring(0, inverse.length()-1);
        return inverse;
    }
}
