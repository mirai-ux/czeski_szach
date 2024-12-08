package GameManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import org.checkerframework.checker.units.qual.A;

import Figures.*;

public class Helpers {
    public void testowa(){
        System.out.println("Testowy Helper");
    }
    private Figure interpretFromSave( String save ){
        char type = save.charAt(0);
        boolean team = save.charAt(3) == 't';
        int x = (int) save.charAt(1)-48;
        int y = (int) save.charAt(2)-48;
        Figure piece;
        switch ( type ){
            case 'r':
                piece = new Rook( x, y, team );
            break;
            case 'k':
                piece = new Knight( x, y, team );
            break;
            case 'b':
                piece = new Bishop( x, y, team );
            break;
            case 'l': // like lord. k is taken by knight
                piece = new King( x, y, team );
            break;
            case 'q':
                piece = new Queen( x, y, team );
            break;
            default:
                piece = new Pawn( x, y, team );
            break;
        }

        return piece;
    }

    public List<Figure> ReadFromFile( String fileName ){
        List<Figure> figures = new ArrayList<Figure>();
        
        try {
            Scanner scanner = new Scanner( new File( "src/main/java/GameManager/Saves/" + fileName ));
            while ( scanner.hasNextLine() ){
                String line = scanner.nextLine();
                figures.add( interpretFromSave(line) );
            }
            scanner.close();
            return figures;
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return figures;
    }

    public List< List< Integer > > InitializeArray8x8(){
        List< List< Integer > > Array8x8 = new ArrayList<>();
        for( int i=0; i<8; i++){
            List< Integer > row = new ArrayList<>();
            for( int j=0; j<8; j++ ){
                row.add(0);
            }
            Array8x8.add( row );
        }
        return Array8x8;
    }

    public List< List< Integer > > UpgradeArray8x8( List< Figure > figures, List< List< Integer > > Array8x8){
        Figure elem;
        int val, x, y;
	// System.out.println("Siema");
        for( int i=0; i<32; i++ ){
            elem = figures.get( i );
            x = elem.getX();
            y = elem.getY();
            // System.out.println("x: " + x + "|y: "+y);
            if( elem.getActivity() ) val = i;
            else val = 69;
            Array8x8.get(x).set(y,val);
        }

        return Array8x8;
    }
}
