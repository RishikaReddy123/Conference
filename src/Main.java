import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        List<String> inputLines = new ArrayList<>();

        System.out.println("Enter talks in format 'Title - Duration'");

        while(true){
            String line = sc.nextLine();

            if (line.isEmpty()) {
                break;
            }
            inputLines.add(line);

        }
        sc.close();

        List<Talk> talks = new ArrayList<>();
        for(String line : inputLines){
            if (line.trim().isEmpty()) continue;
            Talk talk = TalkParser.parseLine(line);
            if(talk != null){
                talks.add(talk);
            }else {
                System.out.println("Invalid input format for: " + line);
            }
        }

        ConferencePlanner planner = new ConferencePlanner(talks);
        planner.planConference();
        planner.printTracks();
    }

}





