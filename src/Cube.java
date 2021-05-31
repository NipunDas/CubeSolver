public class Cube {

    private Corner[] corners;
    private Edge[] edges;
    private String solution;

    public Cube(){
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
    }

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

    public String getSolution() {
        return solution;
    }

    public void solve() {
        solution = "";
        solveEO();
    }

    public int numMisOriented() {
        int count = 0;
        for (int i = 0; i < edges.length; i++) {
            if (!edges[i].isOriented()) {
                count++;
            }
        }
        return count;
    }

    public int numMisOrientedSubset(int[] subset) {
        int count = 0;
        for (int i = 0; i < subset.length; i++) {
            if (!edges[subset[i]].isOriented()) {
                count++;
            }
        }
        return count;
    }

    //orients all edges
    public void solveEO() {
        //first loop does F, B, and if necessary other random moves to get the number of mis-oriented edges to be a
        //multiple of 4
        while (numMisOriented()%4 != 0) {
            if (numMisOrientedSubset(new int[]{2, 8, 9, 4}) % 2 == 1){
                F();
                solution += "F ";
            } else if (numMisOrientedSubset(new int[]{0, 10, 11, 6}) % 2 == 1) {
                B();
                solution += "B ";
            } else {
                int rand = (int) (Math.random() * 4);
                switch (rand) {
                    case 0:
                        R();
                        solution += "R ";
                        break;
                    case 1:
                        L();
                        solution += "L ";
                        break;
                    case 2:
                        U();
                        solution += "U ";
                        break;
                    case 3:
                        D();
                        solution += "D ";
                        break;
                }
            }
        }
        //second loop orients all edges
        while (numMisOriented() > 0) {
            if (numMisOrientedSubset(new int[]{0, 10, 11, 6}) == 4) {
                B();
                solution += "B ";
            }
            if (numMisOrientedSubset(new int[]{2, 8, 9, 4}) == 4) {
                F();
                solution += "F ";
            }
            if (numMisOriented() > 0) {
                if (numMisOrientedSubset(new int[]{0, 1, 2, 3}) > 1) {
                    if (!edges[1].isOriented()) {
                        Rprime();
                        solution += "R' ";
                    } else if (!edges[3].isOriented()) {
                        L();
                        solution += "L ";
                    }
                }
                if (numMisOrientedSubset(new int[]{4, 5, 6, 7}) > 1) {
                    if (!edges[5].isOriented()) {
                        R();
                        solution += "R ";
                    } else if (!edges[7].isOriented()) {
                        Lprime();
                        solution += "L' ";
                    }
                }
                if (numMisOrientedSubset(new int[]{1, 8, 5, 10}) > 1) {
                    if (!edges[1].isOriented()) {
                        U();
                        solution += "U ";
                    } else if (!edges[5].isOriented()) {
                        Dprime();
                        solution += "D' ";
                    }
                }
                if (numMisOrientedSubset(new int[]{3, 9, 7, 11}) > 1) {
                    if (!edges[3].isOriented()) {
                        Uprime();
                        solution += "U' ";
                    } else if (!edges[7].isOriented()) {
                        D();
                        solution += "D ";
                    }
                }
                if (edges[2].isOriented()) {
                    U();
                }
                if (edges[4].isOriented()) {
                    D();
                }
                if (edges[8].isOriented()) {
                    R();
                }
                if (edges[2].isOriented()) {
                    L();
                }
            }
        }
    }
}
