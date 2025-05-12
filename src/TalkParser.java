public class TalkParser {
    public static Talk parseLine(String line) {
        line = line.trim();

        if (line.toLowerCase().endsWith("lightning")) {
            String title = line.substring(0, line.toLowerCase().lastIndexOf("lightning")).trim();
            return new Talk(title, 5);
        }

        int lastHyphenIndex = line.lastIndexOf("-");
        if (lastHyphenIndex == -1 || lastHyphenIndex == line.length() - 1) {
            System.err.println("Invalid line format: " + line);
            return null;
        }

        String title = line.substring(0, lastHyphenIndex).trim();
        String durPart = line.substring(lastHyphenIndex + 1).trim();

        if (durPart.endsWith("min")) {
            try {
                int duration = Integer.parseInt(durPart.replace("min", "").trim());
                return new Talk(title, duration);
            } catch (NumberFormatException e) {
                System.err.println("Invalid duration format: " + durPart + " in line: " + line);
            }
        } else {
            System.err.println("Unknown duration format: " + durPart + " in line: " + line);
        }
        return null;
    }
}

