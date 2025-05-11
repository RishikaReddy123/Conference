public class TalkParser {
    public static Talk parseLine(String line){
        line = line.trim();

        if (line.toLowerCase().contains("lightning")) {
            String title = line.replace("lightning", "").trim();
            return new Talk(title, 5);
        }


        int lasthyphenindex = line.lastIndexOf("-");

        if((lasthyphenindex == -1) || lasthyphenindex == line.length()-1){
            System.err.println("Invalid line format: " + line);
            return null;
        }

        String title = line.substring(0, lasthyphenindex).trim();
        String durPart = line.substring(lasthyphenindex + 1).trim();

        if(durPart.equalsIgnoreCase("lightning")){
            return new Talk(title, 5);
        }else if(durPart.endsWith("min")){
            try{String numStr = durPart.substring(0, durPart.length()-3).trim();
                int duration = Integer.parseInt(numStr);
                return new Talk(title, duration);
            }catch(NumberFormatException e){
                System.err.println("Invalid duration format: " + durPart + "in line: " + line);
                return null;
            }
        }else {
            System.err.println("Unknown duration format: " + durPart + "in line: " + line);
            return null;
        }
    }
}
