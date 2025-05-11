public class Track {
    private int trackId;
    private Session morningSession;
    private Session afternoonSession;

    public Track(int trackId){
        this.trackId = trackId;
        this.morningSession = new Session(180);
        this.afternoonSession = new Session(180);
    }

    public int getTrackId(){
        return this.trackId;
    }

    public boolean addTalkToMorningSession(Talk talk){
        return morningSession.addTalk(talk);
    }

    public boolean addTalkToAfternoonSession(Talk talk){
        return afternoonSession.addTalk(talk);
    }

    public Session getMorningSession(){
        return morningSession;
    }

    public Session getAfternoonSession(){
        return afternoonSession;
    }
}
