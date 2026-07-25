import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}

enum Color {
    WHITE, BLACK
}

abstract class Piece {
    private Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public abstract char getSymbol();

    public abstract String getName();

    public abstract boolean isValidMove(Board board, int startRow, int startCol, int endRow, int endCol);

    protected boolean isOpponentPiece(Board board, int row, int col) {
        Piece piece = board.getPiece(row, col);
        return piece != null && piece.getColor() != color;
    }

    protected boolean isEmpty(Board board, int row, int col) {
        return board.getPiece(row, col) == null;
    }
}

class Pawn extends Piece {
    public Pawn(Color color) {
        super(color);
    }

    public char getSymbol() {
        return getColor() == Color.WHITE ? 'P' : 'p';
    }

    public String getName() {
        return "Pawn";
    }

    public boolean isValidMove(Board board, int startRow, int startCol, int endRow, int endCol) {
        int direction = getColor() == Color.WHITE ? -1 : 1;
        int startPosition = getColor() == Color.WHITE ? 6 : 1;

        if (startCol == endCol && isEmpty(board, endRow, endCol)) {
            if (endRow - startRow == direction) {
                return true;
            }

            if (startRow == startPosition && endRow - startRow == 2 * direction) {
                return isEmpty(board, startRow + direction, startCol);
            }
        }

        if (Math.abs(endCol - startCol) == 1 && endRow - startRow == direction) {
            return isOpponentPiece(board, endRow, endCol);
        }

        return false;
    }
}

class Rook extends Piece {
    public Rook(Color color) {
        super(color);
    }

    public char getSymbol() {
        return getColor() == Color.WHITE ? 'R' : 'r';
    }

    public String getName() {
        return "Rook";
    }

    public boolean isValidMove(Board board, int startRow, int startCol, int endRow, int endCol) {
        return (startRow == endRow || startCol == endCol)
                && board.isPathClear(startRow, startCol, endRow, endCol);
    }
}

class Knight extends Piece {
    public Knight(Color color) {
        super(color);
    }

    public char getSymbol() {
        return getColor() == Color.WHITE ? 'N' : 'n';
    }

    public String getName() {
        return "Knight";
    }

    public boolean isValidMove(Board board, int startRow, int startCol, int endRow, int endCol) {
        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }
}

class Bishop extends Piece {
    public Bishop(Color color) {
        super(color);
    }

    public char getSymbol() {
        return getColor() == Color.WHITE ? 'B' : 'b';
    }

    public String getName() {
        return "Bishop";
    }

    public boolean isValidMove(Board board, int startRow, int startCol, int endRow, int endCol) {
        return Math.abs(endRow - startRow) == Math.abs(endCol - startCol)
                && board.isPathClear(startRow, startCol, endRow, endCol);
    }
}

class Queen extends Piece {
    public Queen(Color color) {
        super(color);
    }

    public char getSymbol() {
        return getColor() == Color.WHITE ? 'Q' : 'q';
    }

    public String getName() {
        return "Queen";
    }

    public boolean isValidMove(Board board, int startRow, int startCol, int endRow, int endCol) {
        boolean straight = startRow == endRow || startCol == endCol;
        boolean diagonal = Math.abs(endRow - startRow) == Math.abs(endCol - startCol);
        return (straight || diagonal) && board.isPathClear(startRow, startCol, endRow, endCol);
    }
}

class King extends Piece {
    public King(Color color) {
        super(color);
    }

    public char getSymbol() {
        return getColor() == Color.WHITE ? 'K' : 'k';
    }

    public String getName() {
        return "King";
    }

    public boolean isValidMove(Board board, int startRow, int startCol, int endRow, int endCol) {
        return Math.abs(endRow - startRow) <= 1 && Math.abs(endCol - startCol) <= 1;
    }
}

class Board {
    private Piece[][] squares = new Piece[8][8];

    public Board() {
        setupInitialBoard();
    }

    public Piece getPiece(int row, int col) {
        return squares[row][col];
    }

    public void setupInitialBoard() {
        squares[0][0] = new Rook(Color.BLACK);
        squares[0][1] = new Knight(Color.BLACK);
        squares[0][2] = new Bishop(Color.BLACK);
        squares[0][3] = new Queen(Color.BLACK);
        squares[0][4] = new King(Color.BLACK);
        squares[0][5] = new Bishop(Color.BLACK);
        squares[0][6] = new Knight(Color.BLACK);
        squares[0][7] = new Rook(Color.BLACK);

        for (int col = 0; col < 8; col++) {
            squares[1][col] = new Pawn(Color.BLACK);
            squares[6][col] = new Pawn(Color.WHITE);
        }

        squares[7][0] = new Rook(Color.WHITE);
        squares[7][1] = new Knight(Color.WHITE);
        squares[7][2] = new Bishop(Color.WHITE);
        squares[7][3] = new Queen(Color.WHITE);
        squares[7][4] = new King(Color.WHITE);
        squares[7][5] = new Bishop(Color.WHITE);
        squares[7][6] = new Knight(Color.WHITE);
        squares[7][7] = new Rook(Color.WHITE);
    }

    public void printBoard() {
        for (int row = 0; row < 8; row++) {
            System.out.print((8 - row) + "  ");

            for (int col = 0; col < 8; col++) {
                if (squares[row][col] == null) {
                    System.out.print(".  ");
                } else {
                    System.out.print(squares[row][col].getSymbol() + "  ");
                }
            }

            System.out.println();
        }

        System.out.println("   a  b  c  d  e  f  g  h");
    }

    public String move(String from, String to, Color turn) {
        if (!isValidPosition(from) || !isValidPosition(to)) {
            return "Error: Invalid position. Use format like e2 e4.";
        }

        int startRow = 8 - Character.getNumericValue(from.charAt(1));
        int startCol = from.charAt(0) - 'a';
        int endRow = 8 - Character.getNumericValue(to.charAt(1));
        int endCol = to.charAt(0) - 'a';

        Piece piece = squares[startRow][startCol];

        if (piece == null) {
            return "Error: No piece found at " + from + ".";
        }

        if (piece.getColor() != turn) {
            return "Error: It is not your piece.";
        }

        Piece destination = squares[endRow][endCol];
        if (destination != null && destination.getColor() == turn) {
            return "Error: You cannot capture your own piece.";
        }

        if (!piece.isValidMove(this, startRow, startCol, endRow, endCol)) {
            return "Error: Invalid move for " + piece.getName() + ".";
        }

        squares[endRow][endCol] = piece;
        squares[startRow][startCol] = null;

        return piece.getName() + " moved from " + from + " to " + to + ".";
    }

    private boolean isValidPosition(String position) {
        if (position.length() != 2) {
            return false;
        }

        char col = position.charAt(0);
        char row = position.charAt(1);

        return col >= 'a' && col <= 'h' && row >= '1' && row <= '8';
    }

    public boolean isPathClear(int startRow, int startCol, int endRow, int endCol) {
        int rowStep = Integer.compare(endRow, startRow);
        int colStep = Integer.compare(endCol, startCol);

        int row = startRow + rowStep;
        int col = startCol + colStep;

        while (row != endRow || col != endCol) {
            if (squares[row][col] != null) {
                return false;
            }

            row += rowStep;
            col += colStep;
        }

        return true;
    }
}

class Game {
    private Board board = new Board();
    private Color turn = Color.WHITE;

    public void play() {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Chess Game (Console Version) ===");
        System.out.println("Initial Board Setup:");
        board.printBoard();

        while (true) {
            System.out.println(getTurnName() + "'s turn.");
            System.out.print("Enter move (e.g., e2 e4) or exit: ");

            String input = sc.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Game ended.");
                break;
            }

            String[] parts = input.split(" ");

            if (parts.length != 2) {
                System.out.println("Error: Please enter move like e2 e4.");
                continue;
            }

            String result = board.move(parts[0], parts[1], turn);
            System.out.println(result);

            if (!result.startsWith("Error")) {
                board.printBoard();
                changeTurn();
            }
        }

        sc.close();
    }

    private String getTurnName() {
        return turn == Color.WHITE ? "White" : "Black";
    }

    private void changeTurn() {
        turn = turn == Color.WHITE ? Color.BLACK : Color.WHITE;
    }
}
