package Michael.Penfield;

public class player {
    private boolean lost;
    private String Name;
    private Board board;
    public Board getBoard() {
        return this.board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean getLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


}
