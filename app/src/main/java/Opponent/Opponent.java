package Opponent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Opponent {
    private Process stockfish;
    private BufferedReader input;
    private PrintWriter output;

    public boolean startEngine( String path ){
        try {
            stockfish = new ProcessBuilder( path ).start();
            input = new BufferedReader( new InputStreamReader( stockfish.getInputStream() ));
            output = new PrintWriter( new OutputStreamWriter( stockfish.getOutputStream() ));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public void stopEngine(){
        output.println( "quit" );
        try {
            stockfish.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String sendCommand( String command ){
        output.println( command) ;
        try {
            StringBuilder response = new StringBuilder();
            String line;
            while( (line = input.readLine()) != null && !line.equals( "readyok" ) )
                response.append( line ).append( "\n" );
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getBestMove( String fen ){
        sendCommand( "uci" ); // Initialization
        sendCommand( "isready" );
        sendCommand( "position fen " + fen );
        sendCommand( " go movetime 2000" ); // 2s for calculating the move
        try {
            String line;
            while( (line = input.readLine()) != null )
                if( line.startsWith( "bestmove" ) )
                    return line.split( " " )[1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
/*

    public static void main(String[] args) {
        StockfishAI stockfishAI = new StockfishAI();
        if (stockfishAI.startEngine("/path/to/stockfish")) {
            String fen = "rnbqkb1r/pppppppp/8/8/8/8/PPPPPPPP/RNBQKB1R w KQkq - 0 1"; // Pozycja startowa
            String bestMove = stockfishAI.getBestMove(fen);
            System.out.println("Best move: " + bestMove);
            stockfishAI.stopEngine();
        }
    }
}
*/