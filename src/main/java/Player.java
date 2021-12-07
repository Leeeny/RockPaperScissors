public class Player {
    private String name;
    private Move move;

    public Player(String name, Move move) {
        this.name = name;
        this.move = move;
    }

    public Player(Move move){
        this.move = move;
        name = "Unknown Player";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public String getName() {
        return name;
    }

    public Move getMove() {
        return move;
    }
}
