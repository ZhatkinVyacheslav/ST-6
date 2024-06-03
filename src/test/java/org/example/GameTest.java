package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest 
{
    @Test
    public void testCheckState() {
        Game game = new Game();
        char[] board = {'X', 'X', 'X', ' ', ' ', ' ', ' ', ' ', ' '};
        assertEquals(State.XWIN, game.checkState(board));

        board = new char[]{'O', 'O', 'O', ' ', ' ', ' ', ' ', ' ', ' '};
        assertEquals(State.OWIN, game.checkState(board));

        board = new char[]{'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X'};
        assertEquals(State.DRAW, game.checkState(board));

        board = new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        assertEquals(State.PLAYING, game.checkState(board));
    }

    @Test
    public void testGenerateMoves() {
        Game game = new Game();
        ArrayList<Integer> moveList = new ArrayList<>();
        game.generateMoves(game.board, moveList);
        assertEquals(9, moveList.size());

        game.board[0] = 'X';
        moveList.clear();
        game.generateMoves(game.board, moveList);
        assertEquals(8, moveList.size());
    }

    @Test
    public void testEvaluatePosition() {
        Game game = new Game();
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        assertEquals(Game.INF, game.evaluatePosition(game.board, game.player1));

        game.board[0] = 'O';
        game.board[1] = 'O';
        game.board[2] = 'O';
        assertEquals(-Game.INF, game.evaluatePosition(game.board, game.player2));

        game.board[0] = 'X';
        game.board[1] = 'O';
        game.board[2] = 'X';
        assertEquals(0, game.evaluatePosition(game.board, game.player1));
    }

    @Test
    public void testMiniMax() {
        Game game = new Game();
        game.board[0] = 'X';
        game.board[1] = 'X';
        assertEquals(2, game.MiniMax(game.board, game.player2));
    }

    @Test
    public void testGetRow() {
        TicTacToeCell cell = new TicTacToeCell(0, 1, 2);
        assertEquals(2, cell.getRow());
    }

    @Test
    public void testGetCol() {
        TicTacToeCell cell = new TicTacToeCell(0, 1, 2);
        assertEquals(1, cell.getCol());
    }

    @Test
    public void testGetNum() {
        TicTacToeCell cell = new TicTacToeCell(5, 1, 2);
        assertEquals(5, cell.getNum());
    }

    @Test
    public void testInitialValues() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        assertEquals(' ', cell.getMarker());
        assertTrue(cell.isEnabled());
    }

    @Test
    public void testSetMarker() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        cell.setMarker("O");
        assertEquals('O', cell.getMarker());
        assertFalse(cell.isEnabled());
    }

    @Test
    public void testSetMarkerTwice() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        cell.setMarker("X");
        cell.setMarker("O");
        assertEquals('X', cell.getMarker());
        assertFalse(cell.isEnabled());
    }

    @Test
    public void testSetMarkerAfterReset() {
        TicTacToeCell cell = new TicTacToeCell(0, 0, 0);
        cell.setMarker("X");
        cell.setText(" ");
        cell.setEnabled(true);
        cell.setMarker("O");
        assertEquals('O', cell.getMarker());
        assertFalse(cell.isEnabled());
    }

}
