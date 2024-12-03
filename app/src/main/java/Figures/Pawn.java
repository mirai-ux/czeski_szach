package Figures;

public class Pawn extends Figure {
    public Pawn( int x_, int y_, boolean team ){
        super( x_, y_, team );
        type = 'P';
        value = 1;
    }
}
