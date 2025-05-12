import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        List<String> inputLines = InputReader.readInput();
        List<Talk> talks = new ArrayList<>();

        for (String line : inputLines) {
            if (line.trim().isEmpty()) continue;
            Talk talk = TalkParser.parseLine(line);
            if (talk != null) {
                talks.add(talk);
            } else {
                System.out.println("Invalid input format for: " + line);
            }
        }

        ConferencePlanner planner = new ConferencePlanner(talks);
        planner.planConference();
        ConferencePlanner.printTracks(planner.getTracks());
    }
}

class InputReader {
    public static List<String> readInput() {
        Scanner sc = new Scanner(System.in);
        List<String> inputLines = new ArrayList<>();
        System.out.println("Enter talks in format 'Title - Duration'");

        while (true) {
            String line = sc.nextLine();
            if (line.isEmpty()) {
                break;
            }
            inputLines.add(line);
        }
        sc.close();
        return inputLines;
    }
}
