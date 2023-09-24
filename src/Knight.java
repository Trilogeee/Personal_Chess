import java.lang.reflect.Array;
import java.util.ArrayList;

public class Knight extends Piece{

    public Knight(char color, char letter){
        super(color, letter);
        super.setPointValue(3);
        switch (color) {
            case 'W' -> super.setImageFileName("src/Chessboard/whiteKnight.png");
            case 'B' -> super.setImageFileName("src/Chessboard/blackKnight.png");
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

        // Check far left
        if(x-2 >= 0){
            if(y+1 <= 7){
                if(!boardMatrix[x-2][y+1].hasPiece()) {
                    addValidMove(x-2,y+1,boardMatrix,validMoveList);
                } else if(boardMatrix[x-2][y+1].getPiece().getColor() == enemyColor){
                    addValidMove(x-2,y+1,boardMatrix,validMoveList);
                }
            }
            if(y-1 >= 0){
                if(!boardMatrix[x-2][y-1].hasPiece()) {
                    addValidMove(x-2,y-1,boardMatrix,validMoveList);
                } else if(boardMatrix[x-2][y-1].getPiece().getColor() == enemyColor){
                    addValidMove(x-2,y-1,boardMatrix,validMoveList);
                }
            }
        }
        // Check middle left
        if(x-1 >= 0){
            if(y+2 <= 7){
                if(!boardMatrix[x-1][y+2].hasPiece()) {
                    addValidMove(x-1,y+2,boardMatrix,validMoveList);
                } else if(boardMatrix[x-1][y+2].getPiece().getColor() == enemyColor){
                    addValidMove(x-1,y+2,boardMatrix,validMoveList);
                }
            }
            if(y-2 >= 0){
                if(!boardMatrix[x-1][y-2].hasPiece()) {
                    addValidMove(x-1,y-2,boardMatrix,validMoveList);
                } else if(boardMatrix[x-1][y-2].getPiece().getColor() == enemyColor){
                    addValidMove(x-1,y-2,boardMatrix,validMoveList);
                }
            }
        }
        // Check middle right
        if(x+1 <= 7){
            if(y+2 <= 7){
                if(!boardMatrix[x+1][y+2].hasPiece()) {
                    addValidMove(x+1,y+2,boardMatrix,validMoveList);
                } else if(boardMatrix[x+1][y+2].getPiece().getColor() == enemyColor){
                    addValidMove(x+1,y+2,boardMatrix,validMoveList);
                }
            }
            if(y-2 >= 0){
                if(!boardMatrix[x+1][y-2].hasPiece()) {
                    addValidMove(x+1,y-2,boardMatrix,validMoveList);
                } else if(boardMatrix[x+1][y-2].getPiece().getColor() == enemyColor){
                    addValidMove(x+1,y-2,boardMatrix,validMoveList);
                }
            }
        }
        // Check far right
        if(x+2 <= 7){
            if(y+1 <= 7){
                if(!boardMatrix[x+2][y+1].hasPiece()) {
                    addValidMove(x+2,y+1,boardMatrix,validMoveList);
                } else if(boardMatrix[x+2][y+1].getPiece().getColor() == enemyColor){
                    addValidMove(x+2,y+1,boardMatrix,validMoveList);
                }
            }
            if(y-1 >= 0){
                if(!boardMatrix[x+2][y-1].hasPiece()) {
                    addValidMove(x+2,y-1,boardMatrix,validMoveList);
                } else if(boardMatrix[x+2][y-1].getPiece().getColor() == enemyColor){
                    addValidMove(x+2,y-1,boardMatrix,validMoveList);
                }
            }
        }

        return validMoveList;
    }

    public static void addValidMove(int x, int y, Square[][] boardMatrix, ArrayList<Coordinate> validMoveList){
        Coordinate tempCoord = new Coordinate(x, y);
        validMoveList.add(tempCoord);
        boardMatrix[tempCoord.getX()][tempCoord.getY()].setPossibleMove(true);
    }

}
