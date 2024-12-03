package GameManager;

public class GM {
    private
    int selectedPieceId;
    boolean isTurnWhite;
    int[][] board;
    int[] AllFigures;
    public
    GM(){
        System.out.println("GM");
        Helpers helper = new Helpers();
        helper.testowa();
    }
}
