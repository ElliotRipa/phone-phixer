import java.util.ArrayList;
import java.util.Scanner;

public class InputReader {

    public static ArrayList<String> readInput() {
        ArrayList<String> output = new ArrayList<>();
        
        // System.out.println("Print \"Done\" when you're done.\n");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.equalsIgnoreCase("done")) {
                break;
            }
            output.add(line);
        }
        scanner.close();
        return output;
    }

}
