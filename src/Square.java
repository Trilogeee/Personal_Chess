import java.awt.*;

public class Square {

    private int matrixX;
    private int matrixY;
    private String squareName;
    private Piece piece;
    private Color color;

    private Color defaultColor;

    private boolean isPossibleMove;

    private boolean isEnPassantable;

    public Square(String squareName, int matrixX, int matrixY, Color color, Piece piece){
        this.squareName = squareName;
        this.matrixX = matrixX;
        this.matrixY = matrixY;
        this.color = color;
        this.defaultColor = color;
        this.piece = piece;
        this.isPossibleMove = false;
    }
    public Square(String squareName, int matrixX, int matrixY, Color color){
        this.squareName = squareName;
        this.matrixX = matrixX;
        this.matrixY = matrixY;
        this.color = color;
        this.defaultColor = color;
        this.piece = null;
        this.isPossibleMove = false;
    }

    public int getMatrixX() {
        return matrixX;
    }

    public void setMatrixX(int matrixX) {
        this.matrixX = matrixX;
    }

    public int getMatrixY() {
        return matrixY;
    }

    public void setMatrixY(int matrixY) {
        this.matrixY = matrixY;
    }

    public String getSquareName() {
        return squareName;
    }

    public void setSquareName(String squareName) {
        this.squareName = squareName;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean hasPiece(){
        return this.piece != null;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(Color defaultColor) {
        this.defaultColor = defaultColor;
    }

    public boolean isEnPassantable() {
        return isEnPassantable;
    }

    public void setEnPassantable(boolean enPassantable) {
        isEnPassantable = enPassantable;
    }

    public boolean isPossibleMove() {
        return isPossibleMove;
    }

    public void setPossibleMove(boolean possibleMove) {
        isPossibleMove = possibleMove;
    }

    public void resetColor(){
        this.color = this.defaultColor;
    }

    @Override
    public String toString(){
        if(hasPiece()){
            return "Square: " + squareName + " | Location: [" + matrixX + "][" + matrixY + "] | Color: " + color.toString() + " | Piece: " + piece;
        }
        return "Square: " + squareName + " | Location: [" + matrixX + "][" + matrixY + "] | Color: " + color.toString() + " | Piece: null";
    }
}
