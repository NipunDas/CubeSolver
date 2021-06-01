public class Edge {

    private char c1, c2;

    public Edge(char c1, char c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    public char getC1() {
        return c1;
    }

    public void setC1(char c1) {
        this.c1 = c1;
    }

    public char getC2() {
        return c2;
    }

    public void setC2(char c2) {
        this.c2 = c2;
    }

    public void flip() {
        char temp = c2;
        c2 = c1;
        c1 = temp;
    }

    public boolean isOriented() {
        if (c1 == 'Y' || c1 == 'W') {
            return true;
        } else if (c1 == 'O' || c1 == 'R') {
            return false;
        } else {
            if (c2 == 'W' || c2 == 'Y') {
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean matches(String piece) {
        return (("" + c1 + c2).equals(piece));
    }
}
