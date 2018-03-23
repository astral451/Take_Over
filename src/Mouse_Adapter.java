import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;


public class Mouse_Adapter extends MouseAdapter  {
	
	private Board board;
	private Camera camera;
	private boolean button3 = false;

	// this is a captured starting position of the camera.  We need this
	// for our mouse dragging actions.
	int cam_x = 0;
	int cam_y = 0;
	int start_x = 0;
	int start_y = 0;
	int finish_x = 0;
	int finish_y = 0;

	public Mouse_Adapter( Camera camera, Board board ) {
		this.board = board;
		this.camera = camera;
	}


	@Override
	public void mouseWheelMoved( MouseWheelEvent e ) {
		if ( e.getWheelRotation( ) < 0 ) {
			this.camera.current_zoom -= 10;
		} else {
			this.camera.current_zoom += 10;
		}
	}

    @Override
	public void mousePressed( MouseEvent e ) {
        int x = e.getX( );
		int y = e.getY( );

        if ( e.getButton( ) == MouseEvent.BUTTON3 ) {
			button3 = true;
		}

		if( e.getButton( ) == MouseEvent.BUTTON1 ) {
	    int mod_x = x - this.camera.pos_x;
			int mod_y = y - this.camera.pos_y;
			this.board.add_anchor( mod_x, mod_y );
		}
	}


	@Override
	public void mouseDragged( MouseEvent e ) {
		if ( button3 ) {
            if ( ( start_x == 0 ) && ( start_y == 0 ) ) {
                cam_x = this.camera.pos_x;
                cam_y = this.camera.pos_y;
				start_x = e.getX( );
				start_y = e.getY( );
			}
			finish_x = e.getX( );
			finish_y = e.getY( );
			int dif_x = finish_x - start_x;
			int dif_y = finish_y - start_y;
			this.camera.set_drag_delta( dif_x, dif_y );
		}
	}


	@Override
	public void mouseReleased( MouseEvent e ) {
		if ( e.getButton() == MouseEvent.BUTTON3 ) {
			start_x = 0;
			start_y = 0;
			finish_x = 0;
			finish_y = 0;
			button3 = false;
			this.camera.reset_initial_pos( );
		}
	}

}
