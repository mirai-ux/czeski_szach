package Figures;

public abstract class Figure {
    private
    int x;          // X coordinate
    int y;          // Y coordinate
    bool isActive;  // Wether piece is still in-game
    bool isWhite;   // Team White(1) or Black(0)
    int value;     // Worth of piece 
    char type;      // Type of figure
    public
    // Getters
    int getX(){ return x; }
    int getY(){ return y; }
    bool getTeam() { return isWhite; }
    bool getActivity(){ return isActive; }
    int getValue(){ return value; }
    char getType(){ return type; }
    // Setters
    void setX( int x_ ){ x = x_; }
    void setY( int y_ ){ y = y_; }
    void setActivity( bool status ){ isActive = status; }
    void setActivity( int x_, int y_, bool status ) {
        x = x_;
        y = y_;
        isActive = status;
    }

}
