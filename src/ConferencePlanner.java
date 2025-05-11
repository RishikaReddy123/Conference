import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ConferencePlanner {
    private List<Talk> allTalks;
    private List<Track> tracks;

    public ConferencePlanner(List<Talk> talks){
        this.allTalks = talks;
        this.tracks = new ArrayList<>();
    }

    public List<Track> getTracks(){
        return tracks;
    }

    public void planConference(){
        tracks = new ArrayList<>();
        int trackId = 1;
        Track currentTrack = new Track(trackId);
        tracks.add(currentTrack);

        for(Talk talk: allTalks){
            if(!currentTrack.addTalkToMorningSession(talk)){
                if(!currentTrack.addTalkToAfternoonSession(talk)){
                    trackId++;
                    currentTrack = new Track(trackId);
                    tracks.add(currentTrack);
                    if(!currentTrack.addTalkToMorningSession(talk)){
                        currentTrack.addTalkToAfternoonSession(talk);
                    }
                }
            }
        }
    }
    public void printTracks(){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
        for(Track track : tracks){
            System.out.println("Track " + track.getTrackId() + ":");

            LocalTime time = LocalTime.of(9, 0);
            for(Talk talk : track.getMorningSession().getTalks()){
                System.out.print(time.format(timeFormatter));
                System.out.print(" ");
                System.out.print(talk);
                System.out.println();
                time = time.plusMinutes(talk.getDuration());
            }

            for(Talk talk : track.getAfternoonSession().getTalks()){
                System.out.print(time.format(timeFormatter));
                System.out.print(" ");
                System.out.print(talk);
                System.out.println();
                time = time.plusMinutes(talk.getDuration());
            }

            LocalTime networkingTime = time;
            if(networkingTime.isBefore(LocalTime.of(16, 0))){
                networkingTime = LocalTime.of(16, 0);
            } else if (networkingTime.isAfter(LocalTime.of(17, 0))) {
                networkingTime = LocalTime.of(17, 0);
            }
            System.out.println(networkingTime.format(timeFormatter) + " Networking Event");
        }
    }
}
