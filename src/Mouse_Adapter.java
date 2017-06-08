import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;


public class Mouse_Adapter extends MouseAdapter {
	
	private Board board;

	public Mouse_Adapter( Board board ) {

		this.board = board;

	}


	@Override
	public void mousePressed( MouseEvent e ) {
		int x = e.getX( );
		int y = e.getY( );
		
		if( e.getButton( ) == MouseEvent.BUTTON1 ) {
			this.board.add_circle( x, y );
		}
		
		if( e.getButton( ) == MouseEvent.BUTTON3 ) {
			this.board.add_anchor( x, y );
		}
		
	}
}
