import java.util.*;
public class Piece {
    /**
     * TODO: Piece objects have:
     *      - point value (1,3,5,9,etc)
     *      - location (a1,b5,e8,etc)
     *      - color (white or black)
     *      - type (pawn, rook, queen, etc)
     *
     */

    private int pointValue;
    //private int[][] matrixLocation;       // Storing location of piece in Piece class seems redundant.
    //private double[][] screenLocation;    // I will give each square a location and a piece, then each piece has a location by proxy
    private char color;
    private char letter;
    private String imageFileName;

    private boolean hasMoved;

    private int moveCount = 0;

    public Piece(char color, char type){
        this.color = Character.toUpperCase(color);
        this.letter = Character.toUpperCase(type);
        //setupVariables(color, type);
        this.hasMoved = false;
    }

    //setupVariables Function - Deprecated

//    public void setupVariables(char color, char letter){
//        switch (letter){
//            case 'P':
//                this.pointValue = 1;
//                switch (color) {
//                    case 'W' -> this.imageFileName = "src/Chessboard/whitePawn.png";
//                    case 'B' -> this.imageFileName = "src/Chessboard/blackPawn.png";
//                }
//                break;
//            case 'B':
//                this.pointValue = 3;
//                switch (color) {
//                    case 'W' -> this.imageFileName = "src/Chessboard/whiteBishop.png";
//                    case 'B' -> this.imageFileName = "src/Chessboard/blackBishop.png";
//                }
//                break;
//            case 'N':
//                this.pointValue = 3;
//                switch (color) {
//                    case 'W' -> this.imageFileName = "src/Chessboard/whiteKnight.png";
//                    case 'B' -> this.imageFileName = "src/Chessboard/blackKnight.png";
//                }
//                break;
//            case 'R':
//                this.pointValue = 5;
//                switch (color) {
//                    case 'W' -> this.imageFileName = "src/Chessboard/whiteRook.png";
//                    case 'B' -> this.imageFileName = "src/Chessboard/blackRook.png";
//                }
//                break;
//            case 'Q':
//                this.pointValue = 9;
//                switch (color) {
//                    case 'W' -> this.imageFileName = "src/Chessboard/whiteQueen.png";
//                    case 'B' -> this.imageFileName = "src/Chessboard/blackQueen.png";
//                }
//                break;
//            case 'K':
//                this.pointValue = Integer.MAX_VALUE;
//                switch (color) {
//                    case 'W' -> this.imageFileName = "src/Chessboard/whiteKing.png";
//                    case 'B' -> this.imageFileName = "src/Chessboard/blackKing.png";
//                }
//                break;
//        }
//    }

    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    public void incMoveCount(){
        this.moveCount++;
    }

//    public ArrayList<Coordinate> getValidMoveList(){
//        ArrayList<Coordinate> validMoveList = new ArrayList<>();
//        return validMoveList;
//    }



    @Override
    public String toString(){
        return String.valueOf(color) + String.valueOf(letter);
    }
}
