public class Talk {
    private String title;
    private int duration;

    public Talk(String title, int duration){
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration(){
        return duration;
    }

    @Override
    public String toString(){
        String durationString = (duration == 5) ? "lightning" : duration + "min";
        return title + " " + durationString;
    }
}
