import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Mouse_Adapter extends MouseAdapter {
	
	Board board;
	Camera camera;

	public Mouse_Adapter( Camera camera, Board board ) {
		this.board = board;
		this.camera = camera;
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
