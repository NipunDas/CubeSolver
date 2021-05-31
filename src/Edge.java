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
}
