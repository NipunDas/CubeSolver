import java.io.IOException;
import java.io.File;

public class Driver {

    public static void main(String[] args) throws IOException {
        Cube cube = new Cube();
        cube.setColors(args[0]);
        //System.out.println(cube.printCubeSolution());
        //System.out.println(cube.getMoveCount());
        cube.solve();
        String URL = "https://alg.cubing.net/?alg=" + cube.printCubeSolution() + "&setup=%2F%2FIgnore_Setup_%26%2345%3B_Solving_Instructions_in_-Moves-_Section%0A" + cube.invertSolution();
        //System.out.println(URL);
        openWebsite(URL);
    }

    public static void openWebsite(String url) throws IOException {
        Runtime rt = Runtime.getRuntime();
        File chromeDirectory = new File("C:/Program Files/Google/Chrome/Application");
        rt.exec("C://Program Files//Google//Chrome//Application//chrome.exe " + url,  null, chromeDirectory);
        //rt.exec("cd C:\Program Files\Google\Chrome\Application");
        //rt.exec("chrome.exe " + url);
    }
}
