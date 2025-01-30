package GameManager;

public class Move {
    private String start;
    private String stop;

    public Move( String pos, String dest ){
        start = pos;
        stop = dest;
    }

    public Move(){
        start = null;
        stop = null;
    }

    public String getStart(){ return start; }
    public String getStop(){ return stop; }
    public String getMove(){ return start+stop; }

    public void setStart( String s ){ start = s; }
    public void setStop( String s ){ stop = s; }

    @Override
    public String toString(){
        return start+stop;
    }
}
