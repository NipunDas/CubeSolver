import java.util.ArrayList;

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
        solveCross();
        solveBlueOrange();
        solveBlueRed();
        solveGreenOrange();
        //simplifying the solution using cancellations
        ArrayList<String> moves = new ArrayList<>();
        String temp = "";
        for (int i = 0; i < solution.length(); i++) {

        }
    }

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
                if (edges[2].isOriented() != edges[1].isOriented()) {
                    U();
                    solution += "U ";
                } else if (edges[2].isOriented() != edges[3].isOriented()) {
                    Uprime();
                    solution += "U' ";
                } else if (edges[2].isOriented() != edges[0].isOriented()) {
                    U2();
                    solution += "U2 ";
                } else if (edges[4].isOriented() != edges[5].isOriented()) {
                    Dprime();
                    solution += "D' ";
                } else if (edges[4].isOriented() != edges[7].isOriented()) {
                    D();
                    solution += "D ";
                } else if (edges[4].isOriented() != edges[6].isOriented()) {
                    D2();
                    solution += "D2 ";
                } else if (edges[8].isOriented() != edges[1].isOriented()) {
                    Rprime();
                    solution += "R' ";
                } else if (edges[8].isOriented() != edges[5].isOriented()) {
                    R();
                    solution += "R ";
                } else if (edges[8].isOriented() != edges[10].isOriented()) {
                    R2();
                    solution += "R2 ";
                } else if (edges[9].isOriented() != edges[3].isOriented()) {
                    L();
                    solution += "L ";
                } else if (edges[9].isOriented() != edges[7].isOriented()) {
                    Lprime();
                    solution += "L' ";
                } else if (edges[9].isOriented() != edges[11].isOriented()) {
                    L2();
                    solution += "L2 ";
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
                    } else if (!edges[0].isOriented()) {
                        if (edges[4].isOriented()) {
                            B2();
                            solution += "B2 ";
                        } else if (edges[8].isOriented()) {
                            U();
                            Rprime();
                            solution += "U R' ";
                        } else if (edges[9].isOriented()) {
                            Uprime();
                            L();
                            solution += "U' L ";
                        }
                    }
                }
                if (numMisOrientedSubset(new int[]{4, 5, 6, 7}) > 1) {
                    if (!edges[5].isOriented()) {
                        R();
                        solution += "R ";
                    } else if (!edges[7].isOriented()) {
                        Lprime();
                        solution += "L' ";
                    } else if (!edges[6].isOriented()) {
                        if (edges[2].isOriented()) {
                            B2();
                            solution += "B2 ";
                        } else if (edges[8].isOriented()) {
                            D();
                            R();
                            solution += "D R ";
                        } else if (edges[9].isOriented()) {
                            Dprime();
                            Lprime();
                            solution += "D' L' ";
                        }
                    }
                }
                if (numMisOrientedSubset(new int[]{1, 8, 5, 10}) > 1) {
                    if (!edges[1].isOriented()) {
                        U();
                        solution += "U ";
                    } else if (!edges[5].isOriented()) {
                        Dprime();
                        solution += "D' ";
                    } else if (!edges[10].isOriented()) {
                        if (edges[9].isOriented()) {
                            B2();
                            solution += "B2 ";
                        } else {
                            Rprime();
                            solution += "R' ";
                        }
                    }
                }
                if (numMisOrientedSubset(new int[]{3, 9, 7, 11}) > 1) {
                    if (!edges[3].isOriented()) {
                        Uprime();
                        solution += "U' ";
                    } else if (!edges[7].isOriented()) {
                        D();
                        solution += "D ";
                    } else if (!edges[11].isOriented()) {
                        if (edges[8].isOriented()) {
                            B2();
                            solution += "B2 ";
                        } else {
                            L();
                            solution += "L ";
                        }
                    }
                }
                if (edges[2].isOriented()) {
                    if (!edges[1].isOriented()) {
                        U();
                        solution += "U ";
                    } else if (!edges[3].isOriented()) {
                        Uprime();
                        solution += "U' ";
                    } else if (!edges[0].isOriented()) {
                        U2();
                        solution += "U2 ";
                    }
                }
                if (edges[4].isOriented()) {
                    if (!edges[5].isOriented()) {
                        Dprime();
                        solution += "D' ";
                    } else if (!edges[7].isOriented()) {
                        D();
                        solution += "D ";
                    } else if (!edges[6].isOriented()) {
                        D2();
                        solution += "D2 ";
                    }
                }
                if (edges[8].isOriented()) {
                    if (!edges[1].isOriented()) {
                        Rprime();
                        solution += "R' ";
                    } else if (!edges[5].isOriented()) {
                        R();
                        solution += "R ";
                    } else if (!edges[10].isOriented()) {
                        R2();
                        solution += "R2 ";
                    }
                }
                if (edges[9].isOriented()) {
                    if (!edges[3].isOriented()) {
                        L();
                        solution += "L ";
                    } else if (!edges[7].isOriented()){
                        Lprime();
                        solution += "L' ";
                    } else if (!edges[11].isOriented()) {
                        L2();
                        solution += "L2 ";
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
                B2();
                solution += "B2 ";
            } else if (edges[1].matches("YB")) {
                R2();
                solution += "R2 ";
            } else if (edges[2].matches("YB")) {
                F2();
                solution += "F2 ";
            } else if (edges[3].matches("YB")) {
                L2();
                solution += "L2 ";
            } else if (edges[4].matches("YB")) {
                D2();
                solution += "D2 ";
            } else if (edges[5].matches("YB")) {
                D();
                solution += "D ";
            } else if (edges[7].matches("YB")) {
                Dprime();
                solution += "D' ";
            } else if (edges[8].matches("YB")) {
                Rprime();
                solution += "R' ";
            } else if (edges[9].matches("YB")) {
                L();
                solution += "L ";
            } else if (edges[10].matches("YB")) {
                R();
                solution += "R ";
            } else if (edges[11].matches("YB")) {
                Lprime();
                solution += "L' ";
            }
        }
        //solves yellow-orange cross piece
        while (!edges[7].matches("YO")) {
            if (edges[0].matches("YO")) {
                Uprime();
                solution += "U' ";
            } else if (edges[1].matches("YO")) {
                U2();
                solution += "U2 ";
            } else if (edges[2].matches("YO")) {
                U();
                solution += "U ";
            } else if (edges[3].matches("YO")) {
                L2();
                solution += "L2 ";
            } else if (edges[4].matches("YO")) {
                F2();
                solution += "F2 ";
            } else if (edges[5].matches("YO")) {
                R2();
                solution += "R2 ";
            } else if (edges[8].matches("YO")) {
                F2();
                solution += "F2 ";
            } else if (edges[9].matches("YO")) {
                L();
                solution += "L ";
            } else if (edges[10].matches("YO")) {
                Rprime();
                solution += "R' ";
            } else if (edges[11].matches("YO")) {
                Lprime();
                solution += "L' ";
            }
        }
        //solves yellow-red cross piece
        while (!edges[5].matches("YR")) {
            if (edges[0].matches("YR")) {
                U();
                solution += "U ";
            } else if (edges[1].matches("YR")) {
                R2();
                solution += "R2 ";
            } else if (edges[2].matches("YR")) {
                Uprime();
                solution += "U' ";
            } else if (edges[3].matches("YR")) {
                U2();
                solution += "U2 ";
            } else if (edges[4].matches("YR")) {
                F2();
                solution += "F2 ";
            } else if (edges[8].matches("YR")) {
                Rprime();
                solution += "R' ";
            } else if (edges[9].matches("YR")) {
                F2();
                solution += "F2 ";
            } else if (edges[10].matches("YR")) {
                R();
                solution += "R ";
            } else if (edges[11].matches("YR")) {
                D2();
                Lprime();
                D2();
                solution += "D2 L' D2 ";
            }
        }
        //solves yellow-green cross edge
        while (!edges[4].matches("YG")) {
            if (edges[0].matches("YG")) {
                U2();
                solution += "U2 ";
            } else if (edges[1].matches("YG")) {
                U();
                solution += "U ";
            } else if (edges[2].matches("YG")) {
                F2();
                solution += "F2 ";
            } else if (edges[3].matches("YG")) {
                Uprime();
                solution += "U' ";
            } else if (edges[8].matches("YG")) {
                D();
                Rprime();
                Dprime();
                solution += "D R' D' ";
            } else if (edges[9].matches("YG")) {
                Dprime();
                L();
                D();
                solution += "D' L D ";
            } else if (edges[10].matches("YG")) {
                D();
                R();
                Dprime();
                solution += "D R D' ";
            } else if (edges[11].matches("YG")) {
                Dprime();
                Lprime();
                D();
                solution += "D' L' D ";
            }
        }
    }

    //solves blue-orange F2L pair into back left
    public void solveBlueOrange() {
        //moves orange-blue edge into UL position (edge 3)
        if (edges[0].matches("BO")) {
            Uprime();
            solution += "U' ";
        } else if (edges[1].matches("BO")) {
            U2();
            solution += "U2 ";
        } else if (edges[2].matches("BO")) {
            U();
            solution += "U ";
        } else if (edges[8].matches("BO")) {
            R();
            U2();
            Rprime();
            solution += "R U2 R' ";
        } else if (edges[9].matches("BO")) {
            Lprime();
            Uprime();
            L();
            U();
            solution += "L' U' L U ";
        } else if (edges[10].matches("BO")) {
            Rprime();
            U2();
            R();
            solution += "R' U2 R ";
        } else if (edges[11].matches("BO")) {
            L();
            U();
            Lprime();
            Uprime();
            solution += "L U L' U' ";
        }

        //pairs and inserts corner with edge
        while (!(edges[11].matches("BO") && corners[7].matches("YBO"))) {
            if (corners[0].matches("YBO")) {
                L();
                U2();
                Lprime();
                solution += "L U2 L' ";
            } else if (corners[0].matches("BOY")) {
                U();
                L();
                Uprime();
                Lprime();
                solution += "U L U' L' ";
            } else if (corners[0].matches("OYB")) {
                Uprime();
                L();
                Uprime();
                Lprime();
                U2();
                solution += "U' L U' L' U2 ";
            } else if (corners[1].matches("YBO")) {
                L();
                U2();
                Lprime();
                solution += "L U2 L' ";
            } else if (corners[1].matches("BOY")) {
                R();
                U();
                Rprime();
                Uprime();
                solution += "R U R' U' ";
            } else if (corners[1].matches("OYB")) {
                Uprime();
                L();
                U();
                Lprime();
                solution += "U' L U L' ";
            } else if (corners[2].matches("YBO")) {
                L();
                U();
                Lprime();
                solution += "L U L' ";
            } else if (corners[2].matches("BOY")) {
                Uprime();
                R();
                U2();
                Rprime();
                Uprime();
                solution += "U' R U2 R' U' ";
            } else if (corners[2].matches("OYB")) {
                L();
                Uprime();
                Lprime();
                solution += "L U' L' ";
            } else if (corners[3].matches("YBO")) {
                R();
                Uprime();
                Rprime();
                Uprime();
                R();
                Uprime();
                Rprime();
                U2();
                solution += "R U' R' U' R U' R' U2 ";
            } else if (corners[3].matches("BOY")) {
                L();
                U();
                Lprime();
                solution += "L U L' ";
            } else if (corners[3].matches("OYB")) {
                Uprime();
                Rprime();
                U2();
                R();
                Uprime();
                solution += "U' R' U2 R U' ";
            } else if (corners[4].matches("YBO")) {
                U2();
                Lprime();
                U();
                L();
                U();
                solution += "U2 L' U L U";
            } else if (corners[4].matches("BOY")) {
                U();
                Lprime();
                U();
                L();
                U2();
                solution += "U L' U L U2 ";
            } else if (corners[4].matches("OYB")) {
                Uprime();
                Lprime();
                U2();
                L();
                Uprime();
                solution += "U' L' U2 L U' ";
            } else if (corners[5].matches("YBO")) {
                U();
                R();
                Uprime();
                Rprime();
                solution += "U R U' R' ";
            } else if (corners[5].matches("BOY")) {
                U();
                R();
                Uprime();
                Rprime();
                solution += "U R U' R' ";
            } else if (corners[5].matches("OYB")) {
                U2();
                R();
                Uprime();
                Rprime();
                U2();
                solution += "U2 R U' R' U2 ";
            } else if (corners[6].matches("YBO")) {
                Rprime();
                U();
                R();
                Uprime();
                solution += "R' U R U' ";
            } else if (corners[6].matches("BOY")) {
                Uprime();
                Rprime();
                U();
                R();
                solution += "U' R' U R ";
            } else if (corners[6].matches("OYB")) {
                U();
                Rprime();
                Uprime();
                R();
                solution += "U R' U' R ";
            } else if (corners[7].matches("YBO")) {
                Uprime();
                D();
                L();
                U();
                Lprime();
                Dprime();
                solution += "U' D L U L' D' ";
            } else if (corners[7].matches("BOY")) {
                L();
                U();
                Lprime();
                solution += "L U L' ";
            } else if (corners[7].matches("OYB")) {
                L();
                Uprime();
                Lprime();
                solution += "L U' L' ";
            }
        }
    }

    //solves blue-red F2L pair into back right
    public void solveBlueRed() {
        //moves red-blue edge into UL position (edge 1)
        if (edges[0].matches("BR")) {
            U();
            solution += "U ";
        } else if (edges[2].matches("BR")) {
            Uprime();
            solution += "U' ";
        } else if (edges[3].matches("BR")) {
            U2();
            solution += "U2 ";
        } else if (edges[8].matches("BR")) {
            R();
            U();
            Rprime();
            Uprime();
            solution += "R U R' U' ";
        } else if (edges[9].matches("BR")) {
            Lprime();
            U2();
            L();
            solution += "L' U2 L ";
        } else if (edges[10].matches("BR")) {
            Rprime();
            U();
            R();
            Uprime();
            solution += "R' U R U' ";
        }

        //pairs and inserts corner with edge
        while (!(edges[10].matches("BR") && corners[6].matches("YRB"))) {
            if (corners[0].matches("YRB")) {
                Rprime();
                U2();
                R();
                solution += "R' U2 R ";
            } else if (corners[0].matches("RBY")) {
                U();
                Rprime();
                Uprime();
                R();
                solution += "U R' U' R ";
            } else if (corners[0].matches("BYR")) {
                Lprime();
                Uprime();
                L();
                U();
                solution += "L' U' L U ";
            } else if (corners[1].matches("YRB")) {
                Rprime();
                U2();
                R();
                solution += "R' U2 R ";
            } else if (corners[1].matches("RBY")) {
                U();
                Rprime();
                U();
                R();
                U2();
                solution += "U R' U R U2 ";
            } else if (corners[1].matches("BYR")) {
                Uprime();
                Rprime();
                U();
                R();
                solution += "U' R' U R ";
            } else if (corners[2].matches("YRB")) {
                U2();
                Rprime();
                U();
                R();
                U();
                Rprime();
                U();
                R();
                solution += "U2 R' U R U R' U R ";
            } else if (corners[2].matches("RBY")) {
                Uprime();
                R();
                U2();
                Rprime();
                Uprime();
                solution += "U' R U2 R' U' ";
            } else if (corners[2].matches("BYR")) {
                R();
                Uprime();
                Rprime();
                solution += "R U' R' ";
            } else if (corners[3].matches("YRB")) {
                Rprime();
                Uprime();
                R();
                solution += "R U' R' ";
            } else if (corners[3].matches("RBY")) {
                R();
                U();
                Rprime();
                solution += "R U R' ";
            } else if (corners[3].matches("BYR")) {
                Uprime();
                Rprime();
                U2();
                R();
                Uprime();
                solution += "U' R' U2 R U' ";
            } else if (corners[4].matches("YRB")) {
                Uprime();
                Lprime();
                U();
                L();
                solution += "U' L' U L ";
            } else if (corners[4].matches("RBY")) {
                U2();
                Lprime();
                U();
                L();
                U2();
                solution += "U2 L' U L U2 ";
            } else if (corners[4].matches("BYR")) {
                Uprime();
                Lprime();
                U2();
                L();
                Uprime();
                solution += "U' L' U2 L U' ";
            } else if (corners[5].matches("YRB")) {
                U2();
                R();
                Uprime();
                Rprime();
                Uprime();
                solution += "U2 R U' R' U' ";
            } else if (corners[5].matches("RBY")) {
                U();
                R();
                U();
                Rprime();
                U2();
                solution += "U R U R' U2 ";
            } else if (corners[5].matches("BYR")) {
                Uprime();
                R();
                Uprime();
                Rprime();
                U2();
                solution += "U' R U' R' U2 ";
            } else if (corners[6].matches("YRB")) {
                Uprime();
                D();
                Rprime();
                U();
                R();
                Dprime();
                solution += "U' D R' U R D' ";
            } else if (corners[6].matches("RBY")) {
                Rprime();
                U();
                R();
                solution += "R' U R ";
            } else if (corners[6].matches("BYR")) {
                Rprime();
                Uprime();
                R();
                solution += "R' U' R' ";
            }
        }
    }

    //solves green-orange F2L pair into front left
    public void solveGreenOrange() {
        //moves green-orange edge into UL position (edge 1)
        if (edges[0].matches("GO")) {
            Uprime();
            solution += "U' ";
        } else if (edges[1].matches("GO")) {
            U2();
            solution += "U2 ";
        } else if (edges[2].matches("GO")) {
            U();
            solution += "U ";
        } else if (edges[8].matches("GO")) {
            R();
            U2();
            Rprime();
            solution += "R U2 R' ";
        } else if (edges[9].matches("GO")) {
            Lprime();
            U();
            L();
            Uprime();
            solution += "L' U L U' ";
        }

        //pairs and inserts corner with edge
        while (!(edges[9].matches("GO") && corners[4].matches("YOG"))) {
            if (corners[0].matches("YOG")) {
                U2();
                Lprime();
                Uprime();
                L();
                Uprime();
                solution += "U2 L' U' L U' ";
            } else if (corners[0].matches("OGY")) {
                U();
                R();
                U2();
                Rprime();
                U();
                solution += "U R U2 R' U ";
            } else if (corners[0].matches("GYO")) {
                U2();
                R();
                Uprime();
                Rprime();
                U2();
                solution += "U2 R U' R' U2 ";
            } else if (corners[1].matches("YOG")) {
                Lprime();
                Uprime();
                L();
                solution += "L' U' L ";
            } else if (corners[1].matches("OGY")) {
                Lprime();
                U();
                L();
                solution += "L' U L ";
            } else if (corners[1].matches("GYO")) {
                Uprime();
                Lprime();
                U2();
                L();
                Uprime();
                solution += "U' L' U2 L U' ";
            } else if (corners[2].matches("YOG")) {
                Lprime();
                U2();
                L();
                solution += "L' U2 L ";
            } else if (corners[2].matches("OGY")) {
                U();
                Lprime();
                Uprime();
                L();
                solution += "U L' U' L ";
            } else if (corners[2].matches("GYO")) {
                U2();
                Lprime();
                Uprime();
                L();
                Uprime();
                solution += "U2 L' U' L U' ";
            } else if (corners[3].matches("YOG")) {
                Lprime();
                U2();
                L();
                solution += "L' U2 L ";
            } else if (corners[3].matches("OGY")) {
                U();
                Lprime();
                U();
                L();
                U2();
                solution += "U L' U L U2 ";
            } else if (corners[3].matches("GYO")) {
                Uprime();
                Lprime();
                U();
                L();
                solution += "U' L' U L ";
            } else if (corners[4].matches("YOG")) {
                U();
                Dprime();
                Lprime();
                Uprime();
                L();
                D();
                solution += "U D' L' U' L D ";
            } else if (corners[4].matches("OGY")) {
                Lprime();
                U();
                L();
                solution += "L' U L ";
            } else if (corners[4].matches("GYO")) {
                Lprime();
                Uprime();
                L();
                solution += "L' U' L ";
            } else if (corners[5].matches("YOG")) {
                R();
                U();
                Rprime();
                Uprime();
                solution += "R U R' U' ";
            } else if (corners[5].matches("OGY")) {
                Uprime();
                R();
                U();
                Rprime();
                solution += "U' R U R' ";
            } else if (corners[5].matches("GYO")) {
                U();
                R();
                Uprime();
                Rprime();
                solution += "U R U' R' ";
            }
        }
    }
}
