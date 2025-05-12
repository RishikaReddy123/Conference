import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ConferencePlanner {
    private List<Talk> allTalks;
    private List<Track> tracks;

    public ConferencePlanner(List<Talk> talks) {
        this.allTalks = talks;
        this.tracks = new ArrayList<>();
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void planConference() {
        tracks = new ArrayList<>();
        int trackId = 1;
        List<Talk> unscheduledTalks = new ArrayList<>(allTalks);

        while (!unscheduledTalks.isEmpty()) {
            Track currentTrack = new Track(trackId++);
            boolean trackFilled = false;

            List<Talk> remainingTalks = new ArrayList<>();

            for (Talk talk : unscheduledTalks) {
                boolean scheduled = false;

                if (currentTrack.getMorningSession().canAdd(talk)) {
                    currentTrack.getMorningSession().addTalk(talk);
                    scheduled = true;
                }

                else if (currentTrack.getAfternoonSession().canAdd(talk)) {
                    currentTrack.getAfternoonSession().addTalk(talk);
                    scheduled = true;
                }

                if (!scheduled) {
                    remainingTalks.add(talk);
                }
            }

            if (currentTrack.getMorningSession().getTalks().size() > 0 || currentTrack.getAfternoonSession().getTalks().size() > 0) {
                tracks.add(currentTrack);
            }

            unscheduledTalks = remainingTalks;
        }
    }



    public static void printTracks(List<Track> tracks) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");

        for (Track track : tracks) {
            System.out.println("Track " + track.getTrackId() + ":");
            LocalTime time = LocalTime.of(9, 0);

            time = printSession(track.getMorningSession().getTalks(), time, timeFormatter);
            time = printSession(track.getAfternoonSession().getTalks(), time, timeFormatter);

            LocalTime networkingTime = time.isBefore(LocalTime.of(16, 0)) ?
                    LocalTime.of(16, 0) :
                    time.isAfter(LocalTime.of(17, 0)) ?
                            LocalTime.of(17, 0) : time;

            System.out.println(networkingTime.format(timeFormatter) + " Networking Event");
        }
    }

    private static LocalTime printSession(List<Talk> talks, LocalTime startTime, DateTimeFormatter formatter) {
        for (Talk talk : talks) {
            System.out.println(startTime.format(formatter) + " " + talk);
            startTime = startTime.plusMinutes(talk.getDuration());
        }
        return startTime;
    }
}
