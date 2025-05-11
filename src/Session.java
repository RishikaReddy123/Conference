import java.util.List;
import java.util.ArrayList;

public class Session {
    private List<Talk> talks;
    private int totalDuration;
    private int maxDuration;

    public Session(int maxDuration){
        this.talks = new ArrayList<>();
        this.totalDuration = 0;
        this.maxDuration = maxDuration;
    }

    public boolean canAdd(Talk talk){
        return this.totalDuration + talk.getDuration() <= this.maxDuration;
    }

    public boolean addTalk(Talk talk){
        if (canAdd(talk)) {
            this.talks.add(talk);
            this.totalDuration += talk.getDuration();
            return true;
        }
        return false;
    }

    public List<Talk> getTalks(){
        return this.talks;
    }

    public int getTotalDuration(){
        return this.totalDuration;
    }

    public int getMaxDuration(){
        return this.maxDuration;
    }
}
