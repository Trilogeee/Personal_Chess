import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.EventListener;
import java.util.Scanner;

public class Main {

    /**
     * TODO:
     *  - Pawn, Knight, Bishop, Rook, King, Queen classes
     *  - Players 1 and 2
     *  -
     *
     */

    public static void main(String[] args){
        // Create 'Game' object
        Game game = new Game();
        // call start new game function
        game.runGame();

        Scanner input = new Scanner(System.in);
//        while(true){
////            if(StdDraw.isMousePressed()){
////                System.out.println("X: " + StdDraw.mouseX() + " | Y: " + StdDraw.mouseY());
////            }
//            if(StdDraw.hasNextKeyTyped()){
//                System.out.print(StdDraw.nextKeyTyped());
//            }
//
//        }

    }

}