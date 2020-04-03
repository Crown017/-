package concurrent.atomic;

public class Score {

    public volatile int scorew;

    public Score(Integer scorew) {
        this.scorew = scorew;
    }
}
