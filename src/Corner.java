public class Corner {

    private char c1, c2, c3;

    public Corner(char c1, char c2, char c3) {
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
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

    public char getC3() {
        return c3;
    }

    public void setC3(char c3) {
        this.c3 = c3;
    }

    public void twistCW() {
        char temp = c1;
        c1 = c3;
        c3 = c2;
        c2 = temp;
    }

    public void twistCCW() {
        char temp = c1;
        c1 = c2;
        c2 = c3;
        c3 = temp;
    }
}
