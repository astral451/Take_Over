import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Key_Adapter extends KeyAdapter {

	private Board board;

	public Key_Adapter( Board board ) {
		this.board = board;
	}

	@Override
	public void keyReleased( KeyEvent e ) {
		//test
		int key_code = e.getKeyCode( );
		
		if ( key_code == KeyEvent.VK_UP ) {
			//
			this.board.zoom_in( );
		}
		if( key_code == java.awt.event.KeyEvent.VK_PLUS ) {
			//
			this.board.zoom_in( );
		}
		if( key_code == java.awt.event.KeyEvent.VK_MINUS ) {
			//
			this.board.zoom_out( );
		}
	}
}
