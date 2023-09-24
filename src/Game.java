import java.util.Scanner;
import java.awt.event.*;

import static java.awt.Event.LEFT;

public class Game {

    /**
     * TODO:
     *  - When game instance is created, a board with certain properties is created
     *  - Game rules should be contained within this class
     *  - Rule checks can be done within this class
     *  Functions:
     *      - isMoveValid()
     *      - createBoard()
     *      - resetBoard()
     *
     */

    private Board board;

    private static char activeColor;
    public Game(){
        this.board = new Board();
        activeColor = 'W'; // White always starts
    }

    public static char getActiveColor() {
        return activeColor;
    }

    public static void setActiveColor(char activeColor) {
        Game.activeColor = activeColor;
    }

    public void runGame(){
        //Scanner input = new Scanner(System.in);
        createBoard();

        StdDraw.enableDoubleBuffering();
        while (true) {
            if (StdDraw.isMousePressed() && StdDraw.mouseX() < 8 && StdDraw.mouseX() > 0 && StdDraw.mouseY() < 8 && StdDraw.mouseY() > 0) {
                Square currentSquare = board.getBoardMatrix()[(int)StdDraw.mouseX()][(int)StdDraw.mouseY()];

                board.updateSquares(StdDraw.mouseX(), StdDraw.mouseY());
//                if(!this.board.getBoardMatrix()[(int)StdDraw.mouseX()][(int)StdDraw.mouseY()].hasPiece()){
//                    System.out.print("null | ");
//                }
                //System.out.println("Mouse was pressed");
                if(board.isValidMove(board.getLastClickedSquare(), currentSquare)){
                    board.movePiece(board.getLastClickedSquare(), currentSquare);

                }
            }


            //System.out.println(StdDraw.mouseX() + " " + StdDraw.mouseY());

            board.drawBoard();
            StdDraw.show();
            //System.out.println("--------------------------");
        }

    }
    public void createBoard(){
        board.setupCanvas();
        board.drawBoard();
    }

}
