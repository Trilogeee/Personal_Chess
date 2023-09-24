import java.util.ArrayList;

public class Rook extends Piece{

    public Rook(char color, char letter){
        super(color, letter);
        super.setPointValue(5);
        switch (color) {
            case 'W' -> super.setImageFileName("src/Chessboard/whiteRook.png");
            case 'B' -> super.setImageFileName("src/Chessboard/blackRook.png");
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

        if(x < 7){
            rookMoveCheckHorizontal(start, boardMatrix, validMoveList, enemyColor, 'R');
        }
        if(x > 0) {
            rookMoveCheckHorizontal(start, boardMatrix, validMoveList, enemyColor, 'L');
        }
        if(y < 7){
            rookMoveCheckVertical(start, boardMatrix, validMoveList, enemyColor, 'U');

        }
        if(y > 0){
            rookMoveCheckVertical(start, boardMatrix, validMoveList, enemyColor, 'D');
        }



        return validMoveList;
    }

    public static void rookMoveCheckHorizontal(Square start, Square[][] boardMatrix, ArrayList<Coordinate> validMoveList, char enemyColor, char direction){
        Coordinate tempCoord;
        int xInc = 1;
        int x = start.getMatrixX();
        int y = start.getMatrixY();
        boolean xBool = x < 7;
        if(direction == 'L'){
            xInc = -1;
            xBool = x > 0;
        }

        while(xBool) {
            x += xInc;
            if (!boardMatrix[x][y].hasPiece()) {
                tempCoord = new Coordinate(x, y);
                validMoveList.add(tempCoord);
                boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true);
            } else {
                if (boardMatrix[x][y].getPiece().getColor() == enemyColor) {
                    tempCoord = new Coordinate(x, y);
                    validMoveList.add(tempCoord);
                    boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true);
                }
                break;
            }
            // Update xBool value
            if(xInc == 1){
                xBool = x < 7;
            } else {
                xBool = x > 0;
            }
        }
    }

    public static void rookMoveCheckVertical(Square start, Square[][] boardMatrix, ArrayList<Coordinate> validMoveList, char enemyColor, char direction){
        Coordinate tempCoord;
        int yInc = 1;
        int x = start.getMatrixX();
        int y = start.getMatrixY();
        boolean yBool = y < 7;
        if(direction == 'D'){
            yInc = -1;
            yBool = y > 0;
        }


        while(yBool) {
            y += yInc;
            if (!boardMatrix[x][y].hasPiece()) {
                tempCoord = new Coordinate(x, y);
                validMoveList.add(tempCoord);
                boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true);
            } else {
                if (boardMatrix[x][y].getPiece().getColor() == enemyColor) {
                    tempCoord = new Coordinate(x, y);
                    validMoveList.add(tempCoord);
                    boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true);
                }
                break;
            }
            // Update xBool value
            if(yInc == 1){
                yBool = y < 7;
            } else {
                yBool = y > 0;
            }
        }
    }

}
