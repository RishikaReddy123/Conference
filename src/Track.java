public class Track {
    private int trackId;
    private Session morningSession;
    private Session afternoonSession;

    public Track(int trackId) {
        this.trackId = trackId;
        this.morningSession = new Session(180);
        this.afternoonSession = new Session(240);
    }

    public int getTrackId() {
        return this.trackId;
    }

    public boolean addTalk(Talk talk) {
        if (morningSession.canAdd(talk)) {
            return morningSession.addTalk(talk);
        } else if (afternoonSession.canAdd(talk)) {
            return afternoonSession.addTalk(talk);
        }
        return false;
    }

    public Session getMorningSession() {
        return morningSession;
    }

    public Session getAfternoonSession() {
        return afternoonSession;
    }

    public boolean hasSpaceFor(Talk talk) {
        return morningSession.canAdd(talk) || afternoonSession.canAdd(talk);
    }
}

