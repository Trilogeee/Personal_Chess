import java.awt.*;
import java.util.ArrayList;

public class King extends Piece{

    public King(char color, char letter){
        super(color, letter);
        super.setPointValue(Integer.MAX_VALUE);
        int r = (int) (Math.random() * (100));
        switch (color) {
            case 'W' -> super.setImageFileName("src/Chessboard/whiteKing.png");
            case 'B' -> super.setImageFileName("src/Chessboard/blackKing.png");
        }
        if(r == 1){
            super.setImageFileName("src/Chessboard/whiteKingRandom.png");
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
        int[] xArr = {x - 1, x, x + 1};
        int[] yArr = {y - 1, y, y + 1};

        // Set enemy color
        char enemyColor;
        if(start.getPiece().getColor() == 'W'){
            enemyColor = 'B';
        } else {
            enemyColor = 'W';
        }

        //Check for normal one-space king move
        for(int i = 0; i < xArr.length; i++){
            for(int j = 0; j < yArr.length; j++){

                tempCoord = new Coordinate(xArr[i], yArr[j]);
                if(!isOutOfBounds(tempCoord)) {
                    if (boardMatrix[xArr[i]][yArr[j]].hasPiece()) {
                        if ((xArr[i] == x && yArr[j] == y) || (boardMatrix[xArr[i]][yArr[j]].getPiece().getColor() != enemyColor)) {
                            continue;
                        }
                    }
                    validMoveList.add(tempCoord);
                    boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true);
                }
            }
        }

        //Check for castle option
//        if(!start.getPiece().getHasMoved()) {
//            System.out.println("Checking for castle option.");
//            // left castle
//            if (isCastleable(start, boardMatrix[start.getMatrixX() - 4][start.getMatrixY()], boardMatrix)) {
//                tempCoord = new Coordinate(start.getMatrixX() - 4, start.getMatrixY());
//                validMoveList.add(tempCoord);
//                boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true);
//            }
//            // right castle
//            if (isCastleableRight(start, boardMatrix[start.getMatrixX() + 3][start.getMatrixY()], boardMatrix)) {
//                System.out.println("you can castle right");
//                tempCoord = new Coordinate(start.getMatrixX() + 3, start.getMatrixY());
//                validMoveList.add(tempCoord);
//                boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true);
//            }
//        }

        return validMoveList;
    }

    public static boolean isOutOfBounds(Coordinate coord){
        if(coord.getX() <= 7 && coord.getX() >= 0 && coord.getY() <= 7 && coord.getY() >= 0){
            return false;
        }
        return true;
    }

    /**
     * isCastleable - Returns true if the start piece is a king, end piece is a rook, they are both the same color,
     *                neither of them have moved, and there are no pieces between them
     * @param start
     * @param end
     * @param boardMatrix
     * @return boolean
     */
//    public static boolean isCastleable(Square start, Square end, Square[][] boardMatrix){
//        boolean isCastleable = false;
//        if(end.hasPiece()){
//            if(start.getPiece().getClass() == King.class && !start.getPiece().getHasMoved() && end.getPiece().getClass() == Rook.class && !end.getPiece().getHasMoved()
//                    && start.getPiece().getColor() == end.getPiece().getColor()){
//
//                //Checks if the rook to right-side
//                if(end.getMatrixX() > start.getMatrixX()){
//                    for(int i = start.getMatrixX(); i < end.getMatrixX() - 1; i++){
//                        if(boardMatrix[i][start.getMatrixY()].hasPiece()){
//                            isCastleable = false;
//                            break;
//                        }
//                        isCastleable = true;
//                        System.out.println("You are able to castle right-side.");
//                    }
//                } else { // Otherwise, it is the left-side rook
//                    for(int i = start.getMatrixX(); i > end.getMatrixX() + 1; i--){
//                        if(boardMatrix[i][start.getMatrixY()].hasPiece()){
//                            isCastleable = false;
//                            break;
//                        }
//                        isCastleable = true;
//                        System.out.println("You are able to castle left-side.");
//                    }
//                }
//                System.out.println("Returning true");
//                return isCastleable;
//
//
//            }
//        }
//
//        return isCastleable;
//    }

//    public static boolean isCastleableLeft(){
//        for(int i = start.getMatrixX(); i > end.getMatrixX() + 1; i--){
//            if(boardMatrix[i][start.getMatrixY()].hasPiece()){
//                isCastleable = false;
//                break;
//            }
//            isCastleable = true;
//            System.out.println("You are able to castle left-side.");
//        }
//    }

//    public static boolean isCastleableRight(Square start, Square end, Square[][] boardMatrix){
//        boolean isCastleable = false;
//
//        if(end.getMatrixX() > start.getMatrixX()){
//            for(int i = start.getMatrixX(); i <= end.getMatrixX(); i++){
//                if(boardMatrix[i][start.getMatrixY()].hasPiece()){
//                    isCastleable = false;
//                    break;
//                }
//                isCastleable = true;
//                System.out.println("You are able to castle right-side.2");
//            }
//        }
//
//        return isCastleable;
//
//    }


}
