public class Player {
    private final int id;
    private boolean hasBall;

    public Player(int id) {
        this.id = id;
        this.hasBall = false;
    }

    public int getPlayerId() {
        return this.id;
    }

    public boolean getDoesHaveBall() {
        return this.hasBall;
    }

    public void setBall(boolean hasBall) {
        this.hasBall = hasBall;
    }


    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", hasBall=" + hasBall +
                '}';
    }
}
