package ohtu;

public class Player {
    String name;
    int points;

    public Player(String name) {
        this.name=name;
        this.points=0;
    }

    public int getPoints() {
        return this.points;
    }

    public void addPoints() {
        this.points++;
    }
}
