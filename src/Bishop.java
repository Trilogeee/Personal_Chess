import java.util.ArrayList;

public class Bishop extends Piece{

    public Bishop(char color, char letter){
        super(color, letter);
        super.setPointValue(3);
        switch (color) {
            case 'W' -> super.setImageFileName("src/Chessboard/whiteBishop.png");
            case 'B' -> super.setImageFileName("src/Chessboard/blackBishop.png");
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

        // Up Left Check
        bishopMoveCheck(start, boardMatrix, validMoveList, enemyColor, 0, 7);
        // Up Right Check
        bishopMoveCheck(start, boardMatrix, validMoveList, enemyColor, 7, 7);
        // Down Left Check
        bishopMoveCheck(start, boardMatrix, validMoveList, enemyColor, 0, 0);
        // Down Right Check
        bishopMoveCheck(start, boardMatrix, validMoveList, enemyColor, 7, 0);

//        // Up Left Check
//        while(x > 0 && y < 7){
//            x--;
//            y++;
//            if(!boardMatrix[x][y].hasPiece()){
//                tempCoord = new Coordinate(x,y);
//                validMoveList.add(tempCoord);
//            }
//            else {
//                if(boardMatrix[x][y].getPiece().getColor() == enemyColor){
//                    tempCoord = new Coordinate(x,y);
//                    validMoveList.add(tempCoord);
//                }
//                break;
//            }
//        }
//
//        x = start.getMatrixX();
//        y = start.getMatrixY();
//        // Up Right Check
//        while(x < 7 && y < 7){
//            x++;
//            y++;
//            if(!boardMatrix[x][y].hasPiece()){
//                tempCoord = new Coordinate(x,y);
//                validMoveList.add(tempCoord);
//            }
//            else {
//                if(boardMatrix[x][y].getPiece().getColor() == enemyColor){
//                    tempCoord = new Coordinate(x,y);
//                    validMoveList.add(tempCoord);
//                }
//                break;
//            }
//        }
//
//
//        x = start.getMatrixX();
//        y = start.getMatrixY();
//        // Down Left Check
//        while(x > 0 && y > 0) {
//            x--;
//            y--;
//            if (!boardMatrix[x][y].hasPiece()) {
//                tempCoord = new Coordinate(x, y);
//                validMoveList.add(tempCoord);
//            } else {
//                if (boardMatrix[x][y].getPiece().getColor() == enemyColor) {
//                    tempCoord = new Coordinate(x, y);
//                    validMoveList.add(tempCoord);
//                }
//                break;
//            }
//        }
//
//        x = start.getMatrixX();
//        y = start.getMatrixY();
//        // Down Right Check
//        while(x < 7 && y > 0) {
//            x++;
//            y--;
//            if (!boardMatrix[x][y].hasPiece()) {
//                tempCoord = new Coordinate(x, y);
//                validMoveList.add(tempCoord);
//            } else {
//                if (boardMatrix[x][y].getPiece().getColor() == enemyColor) {
//                    tempCoord = new Coordinate(x, y);
//                    validMoveList.add(tempCoord);
//                }
//                break;
//            }
//        }

        return validMoveList;
    }

    // Attempt at simplifying the loops for the bishop moveCheck.
//    public void loopThroughLine(Square[][] boardMatrix, int x, int y, ArrayList<Coordinate> validMoveList, char enemyColor){
//
//        Coordinate tempCoord;
//        if (!boardMatrix[x][y].hasPiece()) {
//                tempCoord = new Coordinate(x, y);
//                validMoveList.add(tempCoord);
//            } else {
//                if (boardMatrix[x][y].getPiece().getColor() == enemyColor) {
//                    tempCoord = new Coordinate(x, y);
//                    validMoveList.add(tempCoord);
//                }
//                break;
//            }
//    }

    public static void bishopMoveCheck(Square start, Square[][] boardMatrix, ArrayList<Coordinate> validMoveList, char enemyColor, int endX, int endY){
        Coordinate tempCoord;
        int xInc = 1;
        int yInc = 1;
        int x = start.getMatrixX();
        int y = start.getMatrixY();
        boolean xBool = x < 7;
        boolean yBool = y < 7;
        if(endX == 0){
            xInc = -1;
            xBool = x > 0;
        }
        if(endY == 0){
            yInc = -1;
            yBool = y > 0;
        }

        while(xBool && yBool) {
            x += xInc;
            y += yInc;
            if (!boardMatrix[x][y].hasPiece()) {
                tempCoord = new Coordinate(x, y);
                validMoveList.add(tempCoord);

                boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true); // Adding circles on possible moves
            } else {
                if (boardMatrix[x][y].getPiece().getColor() == enemyColor) {
                    tempCoord = new Coordinate(x, y);
                    validMoveList.add(tempCoord);

                    boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true); // Adding circles on possible moves
                }
                break;
            }
            // Update xBool value
            if(xInc == 1){
                xBool = x < 7;
            } else {
                xBool = x > 0;
            }
            //Update yBool value
            if(yInc == 1){
                yBool = y < 7;
            } else {
                yBool = y > 0;
            }
        }

    }

}
