import java.awt.*;
import java.util.ArrayList;

public class Board {

    private int width;
    private int height;
    private final int MAX_GRID_X = 8;
    private final int MAX_GRID_Y = 8;
    private final int BLOCK_SIZE = 75;

    private Square boardMatrix[][] = new Square[8][8];

    private Square lastClickedSquare;


    //private Square lastMovedSquare;
    private Piece lastMovedPiece;

    public Board(){
        this.width = MAX_GRID_X * BLOCK_SIZE;
        this.height = MAX_GRID_Y * BLOCK_SIZE;
    }

    public Square[][] getBoardMatrix(){
        return this.boardMatrix;
    }

    public Square getLastClickedSquare() {
        return lastClickedSquare;
    }

    public void setLastClickedSquare(Square lastClickedSquare) {
        //System.out.println("previous square: " + lastClickedSquare.getSquareName()); // Testing
        this.lastClickedSquare = lastClickedSquare;
        //System.out.println("current square: " + lastClickedSquare.getSquareName()); // Testing
    }

    public Piece getLastMovedPiece() {
        return lastMovedPiece;
    }

    public void setLastMovedPiece(Piece lastMovedPiece) {
        this.lastMovedPiece = lastMovedPiece;
    }

    public void setupCanvas(){
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setCanvasSize(width, height);

        StdDraw.setXscale(0,MAX_GRID_X);
        StdDraw.setYscale(0,MAX_GRID_Y);

        setupBoardMatrix();
    }

    // Draw Board V1
//    public void drawBoard(){
//        int n = 8;
//        Color darkGreen = new Color(118,150,86);    // Setting constants color variables
//        Color cream = new Color(238,238,210);       // from Chess.com
//
//
//        // Draw from lower left, up and across.
//        // i is the index for the x value; j is the index for y.
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                if (((i + j) % 2) != 0) {
//                    StdDraw.setPenColor(cream);
//                } else {
//                    StdDraw.setPenColor(darkGreen);
//                }
//
//                // draw filled square centered at ___, ___ with radius 0.5
//                StdDraw.filledSquare(i + 0.5, j + 0.5, 0.5);
//            }
//        }
//    }

    // Draw Board V2: filename is null error, happened when i removed the setupVariables function in the Piece class
    public void drawBoard(){
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                StdDraw.setPenColor(boardMatrix[x][y].getColor());
                StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
                if(boardMatrix[x][y].getPiece() != null){
                    StdDraw.picture(x + 0.5, y + 0.5, boardMatrix[x][y].getPiece().getImageFileName());
                }
                if(boardMatrix[x][y].isPossibleMove()){
                    StdDraw.setPenColor(Color.DARK_GRAY);
                    StdDraw.filledCircle((double)x + 0.5,(double)y + 0.5, 0.1); //TESTING NEW DISPLAY_VALID_MOVES METHOD
                    //boardMatrix[x][y].setPossibleMove(false);
                }
                // Writes each square's name at the center of each square
//                StdDraw.setPenColor(StdDraw.BLACK);
//                StdDraw.text(x + 0.5, y + 0.5, boardMatrix[x][y].getSquareName());
            }
        }
        //StdDraw.filledCircle(0,0, 1);
    }
    // setupBoardMatrix V1: has pieces on the sides, doesnt print queen
//    public void setupBoardMatrix(){
//        //temp variables
//        char[] charArr = {'a','b','c','d','e','f','g','h'};
//        char[] pieceSequence = {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'};
//        Color darkGreen = new Color(118,150,86);    // Setting color variables
//        Color cream = new Color(238,238,210);
//        Color tempColor;
//        String squareName = "";
//        Piece tempPiece;
//
//        //loops from a1-h8, bottom left to top right
//        for(int i = 0; i < MAX_GRID_X; i++){
//            for(int k = 0; k < MAX_GRID_Y; k++){
//                tempPiece = null;
//
//                // Assign Color
//                if((i + k) % 2 == 0){
//                    tempColor = cream;
//                } else {
//                    tempColor = darkGreen;
//                }
//
//                // Way to set first rank pieces easily
//                if(i == 0){
//                    tempPiece = new Piece('W', pieceSequence[k]);
//                }
//                //Way to set last rank pieces easily
//                if(i == 7){
//                    tempPiece = new Piece('B', pieceSequence[k]);
//                }
//
//                //Way to set pawns easily
//                if(i+1 == 2){
//                    tempPiece = new Piece('W', 'P');
//                }
//                if(i+1 == 7){
//                    tempPiece = new Piece('B', 'P');
//                }
//
//                squareName = charArr[k] + String.valueOf(i+1);
//
//                boardMatrix[i][k] = new Square(squareName, i, k, tempColor, tempPiece);
//                System.out.println(boardMatrix[i][k]); // Testing
//            }
//        }
//
//    }

    public void setupBoardMatrix(){
        //temp variables
        char[] charArr = {'a','b','c','d','e','f','g','h'};
        char[] pieceSequence = {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'};
        Color darkGreen = new Color(118,150,86);    // Setting color variables
        Color cream = new Color(238,238,210);
        Color tempColor;
        String squareName = "";
        Piece tempPiece;

        //loops from a1-h8, bottom left to top right
        for(int x = 0; x < MAX_GRID_X; x++){
            for(int y = 0; y < 8; y++){
                tempPiece = null;

                // Assign Color
                if((x + y) % 2 == 0){
                    tempColor = darkGreen;
                } else {
                    tempColor = cream;
                }

                // Way to set first rank pieces easily
//                if(k== 0){
//                    tempPiece = new Piece('W', pieceSequence[i]);
//                }
//                //Way to set last rank pieces easily
//                if(k == 7){
//                    tempPiece = new Piece('B', pieceSequence[i]);
//                }

                // Sets rank 1 and 8 pieces
                switch (x){
                    case 0:
                    case 7:
                        switch (y) {
                            case 0 -> tempPiece = new Rook('W', 'R');
                            case 7 -> tempPiece = new Rook('B', 'R');
                        }
                        break;
                    case 1:
                    case 6:
                        switch (y) {
                            case 0 -> tempPiece = new Knight('W', 'N');
                            case 7 -> tempPiece = new Knight('B', 'N');
                        }
                        break;
                    case 2:
                    case 5:
                        switch (y) {
                            case 0 -> tempPiece = new Bishop('W', 'B');
                            case 7 -> tempPiece = new Bishop('B', 'B');
                        }
                        break;
                    case 3:
                        switch (y) {
                            case 0 -> tempPiece = new Queen('W', 'Q');
                            case 7 -> tempPiece = new Queen('B', 'Q');
                        }
                        break;
                    case 4:
                        switch (y) {
                            case 0 -> tempPiece = new King('W', 'K');
                            case 7 -> tempPiece = new King('B', 'K');
                        }
                        break;
                }

                //Way to set pawns easily
                if(y+1 == 2){
                    tempPiece = new Pawn('W', 'P');
                }
                if(y+1 == 7){
                    tempPiece = new Pawn('B', 'P');
                }

                squareName = charArr[x] + String.valueOf(y+1);

                boardMatrix[x][y] = new Square(squareName, x, y, tempColor, tempPiece);
                System.out.println(boardMatrix[x][y]); // Testing
            }
        }
    }


    public void lightUpSquare(double x, double y){
        Color lightUpColor = new Color(217, 146,15);
        if(boardMatrix[(int)x][(int)y].hasPiece()){
            boardMatrix[(int)x][(int)y].setColor(lightUpColor);
        }
        //System.out.println("lightUpSquare: " + (int)x + ", " + (int)y + " | Color: " + boardMatrix[(int)x][(int)y].getColor()); // Testing
//        if(boardMatrix[(int)x][(int)y].getPiece() != null){
//            System.out.println(boardMatrix[(int)x][(int)y].getPiece().getClass() == class);
//        }

    }

    public void displayValidMoves(double x, double y){

        ArrayList<Coordinate> validMoveList = new ArrayList<>();

        if(boardMatrix[(int)x][(int)y].getPiece() != null){
            //System.out.println(boardMatrix[(int)x][(int)y].getPiece().getClass() == Pawn.class);

            if(boardMatrix[(int)x][(int)y].getPiece().getClass() == Pawn.class){
                validMoveList = Pawn.getValidMoveList(boardMatrix[(int)x][(int)y],boardMatrix, this);
            }
            if(boardMatrix[(int)x][(int)y].getPiece().getClass() == Knight.class){
                validMoveList = Knight.getValidMoveList(boardMatrix[(int)x][(int)y],boardMatrix);
            }
            if(boardMatrix[(int)x][(int)y].getPiece().getClass() == Bishop.class){
                validMoveList = Bishop.getValidMoveList(boardMatrix[(int)x][(int)y],boardMatrix);
            }
            if(boardMatrix[(int)x][(int)y].getPiece().getClass() == Rook.class){
                validMoveList = Rook.getValidMoveList(boardMatrix[(int)x][(int)y],boardMatrix);
            }
            if(boardMatrix[(int)x][(int)y].getPiece().getClass() == Queen.class){
                validMoveList = Queen.getValidMoveList(boardMatrix[(int)x][(int)y],boardMatrix);
            }
            if(boardMatrix[(int)x][(int)y].getPiece().getClass() == King.class){
                validMoveList = King.getValidMoveList(boardMatrix[(int)x][(int)y],boardMatrix);
            }

//            System.out.println(validMoveList);
//            //System.out.print("Valid Moves: ");
//            for(Coordinate coord : validMoveList){
//                //System.out.print("(" + coord.getX() + ", " + coord.getY() + ") ");
//                boardMatrix[coord.getX()][coord.getY()].setColor(Color.PINK);
//            }

//         Makes valid move squares PINK
//            for(int i = 0; i < validMoveList.size(); i++){
//                boardMatrix[validMoveList.get(i).getX()][validMoveList.get(i).getY()].setColor(Color.PINK);
//            }

        }
    }

    public void resetIsPossibleMove(){
        for(int i = 0; i < 8; i++){
            for(int k = 0; k < 8; k++){
                    boardMatrix[i][k].setPossibleMove(false);
            }
        }
    }

    public void resetSquareColors(){
        for(int i = 0; i < 8; i++){
            for(int k = 0; k < 8; k++){
                if(boardMatrix[i][k].getColor() != boardMatrix[i][k].getDefaultColor()){
                    boardMatrix[i][k].resetColor();
                }
            }
        }
    }

    public void updateSquares(double x, double y){
        if(getLastClickedSquare() == boardMatrix[(int)x][(int)y] || !boardMatrix[(int)x][(int)y].hasPiece()){
            resetSquareColors();
            resetIsPossibleMove();
        }
        if(boardMatrix[(int)x][(int)y].hasPiece()) {
            if(boardMatrix[(int)x][(int)y].getPiece().getColor() == Game.getActiveColor()) {
                lightUpSquare(x, y);
                displayValidMoves(x, y);
            }
        }

        if(isValidMove(getLastClickedSquare(), boardMatrix[(int)StdDraw.mouseX()][(int)StdDraw.mouseY()])){
            movePiece(getLastClickedSquare(), boardMatrix[(int)StdDraw.mouseX()][(int)StdDraw.mouseY()]);

        }
        //lastClickedSquare = boardMatrix[(int)x][(int)y];
        setLastClickedSquare(boardMatrix[(int)x][(int)y]);
    }

//    public void drawBoard(){
//        StdDraw.clear(StdDraw.BLACK);
//
//        // StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
//
//        int[][] tempLocation;
//
//        int count = 1;
//        //boolean isDarkSquare = true;
//        //Setting up grid
//        for(int i = 15; i > 0; i-=2){
//            for(int j = 1; j < 16; j+=2){
//                if(count % 2 == 0){ //if count is even
//                    StdDraw.setPenColor(Color.lightGray);
//                }
//                else if(count % 2 == 1) {
//                    StdDraw.setPenColor(Color.darkGray);
//                }
////                if(isDarkSquare){ //if count is even
////                    StdDraw.setPenColor(Color.darkGray);
////                    isDarkSquare = false;
////                } else {
////                    StdDraw.setPenColor(Color.lightGray);
////                    isDarkSquare = true;
////                }
//
//                System.out.println("Count: " + count + " | i = " + i + " | j = " + j + " | count % 2 = " + count % 2 + " | penColor = " + StdDraw.getPenColor());
//                //StdDraw.filledSquare((double)height * j/16.0, (double)width * i/16.0, );
//                StdDraw.filledRectangle((double)height * j/16.0, (double)width * i/16.0, BLOCK_SIZE / 2.0, BLOCK_SIZE / 2.0);
//
//                count++;
//            }
//
//        }
//
////        for(int i = 0; i < 8; i++){
////            for(int j = 0; j < 8; j++){
////                String squareName = count.toString();
////                Square square = new Square(squareName, i, j);
////                boardMatrix[i][j] = square;
////                count++;
////                //StdDraw.square(1/16);
////            }
////        }
//
////        //draw vertical lines
////        for(int i = 0; i < MAX_GRID_X + 1; i++){
////            int x;
////            StdDraw.line((width * i) / MAX_GRID_X,0,(width * i) / MAX_GRID_X,height);
////        }
////
////        //draw horizontal lines
////        for(int i = 0; i < MAX_GRID_Y + 1; i++){
////            int y;
////            StdDraw.line(0,(height * i) / MAX_GRID_Y,width,(height * i) / MAX_GRID_Y);
////        }
//
//    }

    public void movePiece(Square start, Square end){
        start.getPiece().incMoveCount(); // increments move count of moving piece

        // En Passant Check
        if(end.isEnPassantable()){
            System.out.println("You for real just en passanted that fool!");

            if(end.getMatrixX() < start.getMatrixX()){
                boardMatrix[start.getMatrixX() - 1][start.getMatrixY()].setPiece(null);
            } else{
                boardMatrix[start.getMatrixX() + 1][start.getMatrixY()].setPiece(null);
            }
            end.setEnPassantable(false);
        }

        end.setPiece(start.getPiece());

        // Promotion Check
        if(Pawn.isPromotable(start.getPiece(), end)){
            System.out.println("Your pawn can promote! Press \"N\" for Knight, \"B\" for Bishop, \"R\" for Rook, or \"Q\" for Queen.");
            Piece tempPiece;
            while(true) {
                if(StdDraw.isKeyPressed('N')){
                    tempPiece = new Knight(start.getPiece().getColor(), 'N');
                    end.setPiece(tempPiece);
                    break;
                }
                if(StdDraw.isKeyPressed('B')){
                    tempPiece = new Bishop(start.getPiece().getColor(), 'B');
                    end.setPiece(tempPiece);
                    break;
                }
                if(StdDraw.isKeyPressed('R')){
                    tempPiece = new Rook(start.getPiece().getColor(), 'R');
                    end.setPiece(tempPiece);
                    break;
                }
                if(StdDraw.isKeyPressed('Q')){
                    tempPiece = new Queen(start.getPiece().getColor(), 'Q');
                    end.setPiece(tempPiece);
                    break;
                }


//                switch (Character.toUpperCase(StdDraw.isKeyPressed())) {
//                    case 'N':
//                        Piece Knight = new Knight(start.getPiece().getColor(), 'N');
//                        end.setPiece(Knight);
//                    case 'B':
//                        Piece Bishop = new Bishop(start.getPiece().getColor(), 'B');
//                        end.setPiece(Bishop);
//                    case 'R':
//                        Piece Rook = new Rook(start.getPiece().getColor(), 'R');
//                        end.setPiece(Rook);
//                    case 'Q':
//                        Piece Queen = new Queen(start.getPiece().getColor(), 'Q');
//                        end.setPiece(Queen);
//                }
            }
        }

        resetSquareColors();
        start.getPiece().setHasMoved(true);
        if(start.getPiece().getColor() == Game.getActiveColor()){
            // Flip colors.
            if(start.getPiece().getColor() == 'W'){
                Game.setActiveColor('B');
            } else {
                Game.setActiveColor('W');
            }
        }

        start.setPiece(null);
        lastMovedPiece = end.getPiece();
        //lastClickedSquare = boardMatrix[end.getMatrixX()][end.getMatrixY()];
        //setLastClickedSquare(boardMatrix[end.getMatrixX()][end.getMatrixY()]);
    }

    public boolean isValidMove(Square start, Square end){
        if(start != null && start.hasPiece()) {
            if (isValidColor(start)) {

                int startX = start.getMatrixX();
                int startY = start.getMatrixY();

                if (boardMatrix[startX][startY].getPiece().getClass() == Pawn.class) {
                    return Pawn.isValidMove(start, end, boardMatrix, this);
                }
                if (boardMatrix[startX][startY].getPiece().getClass() == Knight.class) {
                    return Knight.isValidMove(start, end, boardMatrix);
                }
                if (boardMatrix[startX][startY].getPiece().getClass() == Bishop.class) {
                    return Bishop.isValidMove(start, end, boardMatrix);
                }
                if (boardMatrix[startX][startY].getPiece().getClass() == Rook.class) {
                    return Rook.isValidMove(start, end, boardMatrix);
                }
                if (boardMatrix[startX][startY].getPiece().getClass() == Queen.class) {
                    return Queen.isValidMove(start, end, boardMatrix);
                }
                if (boardMatrix[startX][startY].getPiece().getClass() == King.class) {
                    return King.isValidMove(start, end, boardMatrix);
                }

            }
            return false;
        }
        return false;

    }

    public boolean isValidColor(Square start){
        if(start.getPiece().getColor() == Game.getActiveColor()){
            return true;
        }
        return false;
    }

}
