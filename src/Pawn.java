import java.util.*;
public class Pawn extends Piece{
/* TODO: En Passant Method
 */

    public Pawn(char color, char letter){
        super(color, letter);
        super.setPointValue(1);
        switch (color) {
            case 'W' -> super.setImageFileName("src/Chessboard/whitePawn.png");
            case 'B' -> super.setImageFileName("src/Chessboard/blackPawn.png");
        }
    }

    public static boolean isValidMove(Square start, Square end, Square[][] boardMatrix, Board board){
        ArrayList<Coordinate> validMoveList = new ArrayList<>();
        validMoveList = getValidMoveList(start, boardMatrix, board);
        Coordinate coord = new Coordinate(end.getMatrixX(), end.getMatrixY());
        for(int i = 0; i < validMoveList.size(); i++) {
            if (coord.getX() == validMoveList.get(i).getX() && coord.getY() == validMoveList.get(i).getY()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasValidMove(){

        return false;
    }

    public static ArrayList<Coordinate> getValidMoveList(Square start, Square[][] boardMatrix, Board board){
        ArrayList<Coordinate> validMoveList = new ArrayList<>();
        Coordinate tempCoord = new Coordinate();

        if(start.getPiece().getColor() == 'W'){
            // Move forward twice or once as their first move. - DONE
            // Move forward once after their first move. - DONE
            // Take a piece diagonally one square. - DONE
            // Must promote when at the 8th rank.
            // En Passant: If there is an adjacent pawn that just made its first move, and it moved forward two spaces,
            //             can take diagonally past the adjacent enemy pawn "in passing".

            if(start.getMatrixY() < 7) {
                // Check for captures
                // up left capture check
                if (start.getMatrixX() > 0 && boardMatrix[start.getMatrixX() - 1][start.getMatrixY() + 1].hasPiece()) {
                    if (boardMatrix[start.getMatrixX() - 1][start.getMatrixY() + 1].getPiece().getColor() == 'B') {
                        tempCoord = new Coordinate(start.getMatrixX() - 1, start.getMatrixY() + 1);
//                    tempCoord.setX(start.getMatrixX() - 1);
//                    tempCoord.setY(start.getMatrixY() + 1);
                        validMoveList.add(tempCoord);

                        boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true); // Adding circles on possible moves
                    }
                }
                // up right capture check
                if (start.getMatrixX() < 7 && boardMatrix[start.getMatrixX() + 1][start.getMatrixY() + 1].hasPiece()) {
                    if (boardMatrix[start.getMatrixX() + 1][start.getMatrixY() + 1].getPiece().getColor() == 'B') {
                        tempCoord = new Coordinate(start.getMatrixX() + 1, start.getMatrixY() + 1);
//                    tempCoord.setX(start.getMatrixX() + 1);
//                    tempCoord.setY(start.getMatrixY() + 1);
                        validMoveList.add(tempCoord);

                        boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true); // Adding circles on possible moves
                    }
                }

                // Check for two-space move at start AND one-space move if has already moved.
                if (!boardMatrix[start.getMatrixX()][start.getMatrixY() + 1].hasPiece()) {
                    tempCoord = new Coordinate(start.getMatrixX(), start.getMatrixY() + 1);
//                tempCoord.setX(start.getMatrixX());
//                tempCoord.setY(start.getMatrixY() + 1);
                    validMoveList.add(tempCoord);
//                    System.out.println("Valid Move added (pawn): (" + tempCoord.getX() + ", " + tempCoord.getY() + ")"); // Testing

                    boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true); // Adding circles on possible moves
                }
                if (!start.getPiece().getHasMoved()) {
                    if (!boardMatrix[start.getMatrixX()][start.getMatrixY() + 2].hasPiece() && !boardMatrix[start.getMatrixX()][start.getMatrixY() + 1].hasPiece()) {
                        tempCoord = new Coordinate(start.getMatrixX(), start.getMatrixY() + 2);
//                    tempCoord.setX(start.getMatrixX());
//                    tempCoord.setY(start.getMatrixY() + 2);
                        validMoveList.add(tempCoord);
//                        System.out.println("Valid Move added (pawn): (" + tempCoord.getX() + ", " + tempCoord.getY() + ")"); // Testing
                        boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true); // Adding circles on possible moves
                    }
                }

                // En Passant Check
                if(start.getMatrixY() == 4) {
                    // En Passant left check
                    if (start.getMatrixX() > 0 && boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].hasPiece()) {
                        if (boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].getPiece().getMoveCount() == 1 && board.getLastMovedPiece() == boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].getPiece()
                                && boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].getPiece().getColor() == 'B' && boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].getPiece().getClass() == Pawn.class) {
                            tempCoord = new Coordinate(start.getMatrixX() - 1, start.getMatrixY() + 1);
                            validMoveList.add(tempCoord);

                            boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true); // Adding circles on possible moves
                            // Testing
                            boardMatrix[tempCoord.getX()][tempCoord.getY()].setEnPassantable(true); // does this work? Pass by reference? POSSIBLE ERROR
                            //System.out.println("Square [" + tempCoord.getX() + "] [" + tempCoord.getY() + "] is enPassantable");

//                        System.out.println("Valid Move added (pawn): (" + tempCoord.getX() + ", " + tempCoord.getY() + ")"); // Testing
                        }
                    }
                    //En Passant Right Check
                    if (start.getMatrixX() < 7 && boardMatrix[start.getMatrixX() + 1][start.getMatrixY()].hasPiece()) {
                        if (boardMatrix[start.getMatrixX() + 1][start.getMatrixY()].getPiece().getMoveCount() == 1 && board.getLastMovedPiece() == boardMatrix[start.getMatrixX() + 1][start.getMatrixY()].getPiece()
                                && boardMatrix[start.getMatrixX() + 1][start.getMatrixY()].getPiece().getColor() == 'B' && boardMatrix[start.getMatrixX() + 1][start.getMatrixY()].getPiece().getLetter() == 'P') {
                            tempCoord = new Coordinate(start.getMatrixX() + 1, start.getMatrixY() + 1);
                            validMoveList.add(tempCoord);

                            boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true); // Adding circles on possible moves
                            // Testing
                            //System.out.println("Square [" + tempCoord.getX() + "] [" + tempCoord.getY() + "] is enPassantable");
                            boardMatrix[tempCoord.getX()][tempCoord.getY()].setEnPassantable(true); // does this work? Pass by reference? POSSIBLE ERROR

//                        System.out.println("Valid Move added (pawn): (" + tempCoord.getX() + ", " + tempCoord.getY() + ")"); // Testing
                        }
                    }
                }
//            if(boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].hasPiece()){
//                if((boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].getPiece() instanceof Pawn &&) ){
//
//                }
//            }
            }

        }
        else {
            // Move forward twice or once as their first move.
            // Move forward once after their first move.
            // Take a piece diagonally one square.
            // Must promote when at the 1st rank.
            // En Passant: If there is an adjacent pawn that just made its first move, and it moved forward two spaces,
            //             can take diagonally past the adjacent enemy pawn "in passing".

            if (start.getMatrixY() > 0) {
                // Check for captures
                // down left capture check
                if (start.getMatrixX() > 0 && boardMatrix[start.getMatrixX() - 1][start.getMatrixY() - 1].hasPiece()) {
                    if (boardMatrix[start.getMatrixX() - 1][start.getMatrixY() - 1].getPiece().getColor() == 'W') {
                        tempCoord = new Coordinate(start.getMatrixX() - 1, start.getMatrixY() - 1);
//                        tempCoord.setX(start.getMatrixX() - 1);
//                        tempCoord.setY(start.getMatrixY() - 1);
                        validMoveList.add(tempCoord);

                        boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true); // Adding circles on possible moves
                    }
                }
                // down right capture check
                if (start.getMatrixX() < 7 && boardMatrix[start.getMatrixX() + 1][start.getMatrixY() - 1].hasPiece()) {
                    if (boardMatrix[start.getMatrixX() + 1][start.getMatrixY() - 1].getPiece().getColor() == 'W') {
                        tempCoord = new Coordinate(start.getMatrixX() + 1, start.getMatrixY() - 1);
//                 tempCoord.setX(start.getMatrixX() + 1);
//                 tempCoord.setY(start.getMatrixY() + 1);
                        validMoveList.add(tempCoord);

                        boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true); // Adding circles on possible moves
                    }
                }

                // Check for two-space move at start AND one-space move if has already moved.
                if (!boardMatrix[start.getMatrixX()][start.getMatrixY() - 1].hasPiece()) {
                    tempCoord = new Coordinate(start.getMatrixX(), start.getMatrixY() - 1);
//                tempCoord.setX(start.getMatrixX());
//                tempCoord.setY(start.getMatrixY() + 1);
                    validMoveList.add(tempCoord);
//                    System.out.println("Valid Move added (pawn): (" + tempCoord.getX() + ", " + tempCoord.getY() + ")"); // Testing
                    boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true); // Adding circles on possible moves
                }
                if (!start.getPiece().getHasMoved()) {
                    if (!boardMatrix[start.getMatrixX()][start.getMatrixY() - 2].hasPiece() && !boardMatrix[start.getMatrixX()][start.getMatrixY() - 1].hasPiece()) {
                        tempCoord = new Coordinate(start.getMatrixX(), start.getMatrixY() - 2);
//                    tempCoord.setX(start.getMatrixX());
//                    tempCoord.setY(start.getMatrixY() + 2);
                        validMoveList.add(tempCoord);
//                        System.out.println("Valid Move added (pawn): (" + tempCoord.getX() + ", " + tempCoord.getY() + ")"); // Testing
                        boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true); // Adding circles on possible moves
                    }
                }


                // En Passant Check

                if(start.getMatrixY() == 3) {
                    // En Passant left check
                    if (start.getMatrixX() > 0 && boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].hasPiece()) {
                        if (boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].getPiece().getMoveCount() == 1 && board.getLastMovedPiece() == boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].getPiece()
                                && boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].getPiece().getColor() == 'W' && boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].getPiece().getLetter() == 'P') {
                            tempCoord = new Coordinate(start.getMatrixX() - 1, start.getMatrixY() - 1);
                            validMoveList.add(tempCoord);

                            // Testing
                            //System.out.println("Square [" + tempCoord.getX() + "] [" + tempCoord.getY() + "] is enPassantable");
                            boardMatrix[tempCoord.getX()][tempCoord.getY()].setEnPassantable(true); // does this work? Pass by reference? POSSIBLE ERROR
//                        System.out.println("Valid Move added (pawn): (" + tempCoord.getX() + ", " + tempCoord.getY() + ")"); // Testing

                            boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true); // Adding circles on possible moves
                        }
                    }
                    // En Passant Right Check
                    if (start.getMatrixX() < 7 && boardMatrix[start.getMatrixX() + 1][start.getMatrixY()].hasPiece()) {
                        if (boardMatrix[start.getMatrixX() + 1][start.getMatrixY()].getPiece().getMoveCount() == 1 && board.getLastMovedPiece() == boardMatrix[start.getMatrixX() + 1][start.getMatrixY()].getPiece()
                                && boardMatrix[start.getMatrixX() + 1][start.getMatrixY()].getPiece().getColor() == 'W' && boardMatrix[start.getMatrixX() + 1][start.getMatrixY()].getPiece().getLetter() == 'P') {
                            tempCoord = new Coordinate(start.getMatrixX() + 1, start.getMatrixY() - 1);
                            validMoveList.add(tempCoord);

                            // Testing
                            //System.out.println("Square [" + tempCoord.getX() + "] [" + tempCoord.getY() + "] is enPassantable");
                            boardMatrix[tempCoord.getX()][tempCoord.getY()].setEnPassantable(true); // does this work? Pass by reference? POSSIBLE ERROR

//                        System.out.println("Valid Move added (pawn): (" + tempCoord.getX() + ", " + tempCoord.getY() + ")"); // Testing

                            boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true); // Adding circles on possible moves
                        }
                    }
                }
//            if(boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].hasPiece()){
//                if((boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].getPiece() instanceof Pawn &&) ){
//
//                }
//            }
            }
        }

        return validMoveList;
    }

    /**
     *
     * @param start
     * @param boardMatrix
     * @param board
     * @return
     */
    public static boolean isEnPassant(Square start, Square[][] boardMatrix, Board board){
//        ArrayList<Coordinate> validMoveList = new ArrayList<>();
//        Coordinate tempCoord;

        if(start.getPiece().getColor() == 'W') {

            if (start.getMatrixX() > 0 && boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].hasPiece()) {
                if (boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].getPiece().getMoveCount() == 1 && board.getLastMovedPiece() == boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].getPiece()
                        && boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].getPiece().getColor() == 'B' && boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].getPiece().getClass() == Pawn.class) {
//                    tempCoord = new Coordinate(start.getMatrixX() - 1, start.getMatrixY() + 1);
//                    validMoveList.add(tempCoord);
                    return true;
//                    System.out.println("Valid Move added (pawn): (" + tempCoord.getX() + ", " + tempCoord.getY() + ")"); // Testing
                }
            }
            //En Passant Right Check
            if (start.getMatrixX() < 7 && boardMatrix[start.getMatrixX() + 1][start.getMatrixY()].hasPiece()) {
                if (boardMatrix[start.getMatrixX() + 1][start.getMatrixY()].getPiece().getMoveCount() == 1 && board.getLastMovedPiece() == boardMatrix[start.getMatrixX() + 1][start.getMatrixY()].getPiece()
                        && boardMatrix[start.getMatrixX() + 1][start.getMatrixY()].getPiece().getColor() == 'B' && boardMatrix[start.getMatrixX() + 1][start.getMatrixY()].getPiece().getLetter() == 'P') {
//                    tempCoord = new Coordinate(start.getMatrixX() + 1, start.getMatrixY() + 1);
//                    validMoveList.add(tempCoord);
                    return true;
//                    System.out.println("Valid Move added (pawn): (" + tempCoord.getX() + ", " + tempCoord.getY() + ")"); // Testing
                }
            }

        }
        else {
            if(start.getMatrixX() > 0 && boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].hasPiece()){
                if(boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].getPiece().getMoveCount() == 1 && board.getLastMovedPiece() == boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].getPiece()
                        && boardMatrix[start.getMatrixX() + 1][start.getMatrixY()].getPiece().getColor() == 'W' && boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].getPiece().getLetter() == 'P'){
//                    tempCoord = new Coordinate(start.getMatrixX() - 1, start.getMatrixY() - 1);
//                    validMoveList.add(tempCoord);
                    return true;
//                    System.out.println("Valid Move added (pawn): (" + tempCoord.getX() + ", " + tempCoord.getY() + ")"); // Testing
                }
            }
            // En Passant Right Check
            if(start.getMatrixX() < 7 && boardMatrix[start.getMatrixX() + 1][start.getMatrixY()].hasPiece()) {
                if (boardMatrix[start.getMatrixX() + 1][start.getMatrixY()].getPiece().getMoveCount() == 1 && board.getLastMovedPiece() == boardMatrix[start.getMatrixX() + 1][start.getMatrixY()].getPiece()
                        && boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].getPiece().getColor() == 'W' && boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].getPiece().getLetter() == 'P') {
//                    tempCoord = new Coordinate(start.getMatrixX() + 1, start.getMatrixY() - 1);
//                    validMoveList.add(tempCoord);
                    return true;
//                    System.out.println("Valid Move added (pawn): (" + tempCoord.getX() + ", " + tempCoord.getY() + ")"); // Testing
                }
            }

        }
        return false;
    }

    public static boolean isPromotable(Piece piece, Square end){
        if(piece.getClass() == Pawn.class){
            switch (piece.getColor()){
                case 'W':
                    return end.getMatrixY() == 7;
                case 'B':
                    return end.getMatrixY() == 0;
            }
        }
        return false;
    }



}
