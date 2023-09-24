import java.util.ArrayList;

public class Queen extends Piece{

    public Queen(char color, char letter) {
        super(color, letter);
        super.setPointValue(9);
        switch (color) {
            case 'W' -> super.setImageFileName("src/Chessboard/whiteQueen.png");
            case 'B' -> super.setImageFileName("src/Chessboard/blackQueen.png");
        }
    }

    public static boolean isValidMove(Square start, Square end, Square[][] boardMatrix){
        ArrayList<Coordinate> validMoveList = new ArrayList<>();
        validMoveList = getValidMoveList(start, boardMatrix);
        Coordinate coord = new Coordinate(end.getMatrixX(), end.getMatrixY());
        for(int i = 0; i < validMoveList.size(); i++) {
            if (coord.getX() == validMoveList.get(i).getX() && coord.getY() == validMoveList.get(i).getY()) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Coordinate> getValidMoveList(Square start, Square[][] boardMatrix){
        ArrayList<Coordinate> validMoveList = new ArrayList<>();
        Coordinate tempCoord;
        int x = start.getMatrixX();
        int y = start.getMatrixY();

        // Set enemy color
        char enemyColor;
        if(start.getPiece().getColor() == 'W'){
            enemyColor = 'B';
        } else {
            enemyColor = 'W';
        }

        // Bishop portion
            // Up Left Check
        Bishop.bishopMoveCheck(start, boardMatrix, validMoveList, enemyColor, 0, 7);
            // Up Right Check
        Bishop.bishopMoveCheck(start, boardMatrix, validMoveList, enemyColor, 7, 7);
            // Down Left Check
        Bishop.bishopMoveCheck(start, boardMatrix, validMoveList, enemyColor, 0, 0);
            // Down Right Check
        Bishop.bishopMoveCheck(start, boardMatrix, validMoveList, enemyColor, 7, 0);

        // Rook portion
        if(x < 7){
            Rook.rookMoveCheckHorizontal(start, boardMatrix, validMoveList, enemyColor, 'R');
        }
        if(x > 0) {
            Rook.rookMoveCheckHorizontal(start, boardMatrix, validMoveList, enemyColor, 'L');
        }
        if(y < 7){
            Rook.rookMoveCheckVertical(start, boardMatrix, validMoveList, enemyColor, 'U');

        }
        if(y > 0){
            Rook.rookMoveCheckVertical(start, boardMatrix, validMoveList, enemyColor, 'D');
        }

        return validMoveList;
    }

}
