import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class testModel {

    @Test
    public void testStartGame() {
        TTTOperations model = new TTTModel(new ArrayList<ArrayList<TileStatus>>());
        model.startGame(3);
        assertEquals(3, model.getBoard().get(1).size());
    }

    @Test
    public void testPlaceMove() {
        TTTOperations model = new TTTModel(new ArrayList<ArrayList<TileStatus>>());
        model.startGame(3);
        model.placeMove(TileStatus.X, 1, 1);
        assertTrue(model.getBoard().get(0).get(0).equals(TileStatus.X));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalMove() {
        TTTOperations model = new TTTModel(new ArrayList<ArrayList<TileStatus>>());
        model.startGame(3);
        model.placeMove(TileStatus.X, 8, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalMoveTaken() {
        TTTOperations model = new TTTModel(new ArrayList<ArrayList<TileStatus>>());
        model.startGame(3);
        model.placeMove(TileStatus.X, 1, 1);
        model.placeMove(TileStatus.O, 1, 1);
    }

    @Test
    public void testIsGameOver() {
        TTTOperations model = new TTTModel(new ArrayList<ArrayList<TileStatus>>());
        model.startGame(3);
        model.placeMove(TileStatus.X, 1, 1);
        model.placeMove(TileStatus.X, 1, 2);
        model.placeMove(TileStatus.X, 1, 3);
        assertTrue(model.isGameOver());

        TTTOperations model2 = new TTTModel(new ArrayList<ArrayList<TileStatus>>());
        model2.startGame(3);
        model2.placeMove(TileStatus.X, 1, 1);
        model2.placeMove(TileStatus.X, 2, 1);
        model2.placeMove(TileStatus.X, 3, 1);
    }

    @Test
    public void testGetGameState() {
        TTTOperations model = new TTTModel(new ArrayList<ArrayList<TileStatus>>());
        model.startGame(3);
        model.placeMove(TileStatus.X, 1, 1);
        model.placeMove(TileStatus.O, 1, 2);
        model.placeMove(TileStatus.X, 3, 1);
        model.placeMove(TileStatus.O, 2, 2);
        System.out.print(model.getGameState());
    }

    @Test
    public void testAllTheSame() {
        TTTModel model = new TTTModel(new ArrayList<ArrayList<TileStatus>>());
        model.startGame(3);
        ArrayList<TileStatus> test = new ArrayList<>();
        test.add(TileStatus.O);
        test.add(TileStatus.O);
        test.add(TileStatus.O);
        assertTrue(model.allTheSame(test));
        test.add(TileStatus.X);
        assertFalse(model.allTheSame(test));


        TTTModel model2 = new TTTModel(new ArrayList<ArrayList<TileStatus>>());
        model2.startGame(3);
        ArrayList<TileStatus> test2 = new ArrayList<>();
        test.add(TileStatus.E);
        test.add(TileStatus.E);
        test.add(TileStatus.E);
        assertFalse(model.allTheSame(test));
        assertFalse(model.allTheSame(model.getBoard().get(1)));
    }

    @Test
    public void testRowComplete() {
        TTTModel model = new TTTModel(new ArrayList<ArrayList<TileStatus>>());
        model.startGame(3);
        //assertFalse(model.rowComplete());
        model.placeMove(TileStatus.X, 3, 1);
        model.placeMove(TileStatus.X, 3, 2);
        model.placeMove(TileStatus.X, 3, 3);
        assertTrue(model.rowComplete());
    }

    @Test
    public void testRowComplete2() {
        TTTModel model = new TTTModel(new ArrayList<ArrayList<TileStatus>>());
        model.startGame(3);
        assertFalse(model.rowComplete());
    }

    @Test
    public void testCComplete() {
        TTTModel model = new TTTModel(new ArrayList<ArrayList<TileStatus>>());
        model.startGame(3);
        model.placeMove(TileStatus.X, 1, 2);
        model.placeMove(TileStatus.X, 2, 2);
        model.placeMove(TileStatus.X, 3, 2);
        assertTrue(model.columnComplete());
    }
}

