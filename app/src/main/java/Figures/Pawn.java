package Figures;

public class Pawn extends Figure {
    public
    Pawn( int x_, int y_, bool team ){
        x = x_;
        y = y_;
        isWhite = team;
        type = 'P';
        value = 1;
    }
}
