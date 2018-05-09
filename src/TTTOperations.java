import java.util.ArrayList;

public interface TTTOperations {

    /**
     * places the player's move on the board.
     *
     * @param move X or O to place on board
     * @throws IllegalArgumentException for an illegal move
     */
    void placeMove(TileStatus move, int columnValue, int rowValue) throws IllegalArgumentException;

    /**
     * checks if game is over by a full board or a winner.
     *
     * @return boolean if game is over
     */
    boolean isGameOver();

    /**
     * initializes the board and starts a game.
     */
    void startGame(int boardSize);

    /**
     * returns the current state of the game with a string, a mock view.
     *
     * @return String representing the board
     */
    String getGameState();

    boolean diagonalComplete();

    boolean rowComplete();

    boolean columnComplete();

    public ArrayList<ArrayList<TileStatus>> getBoard();
}
