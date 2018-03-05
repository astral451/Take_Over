import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;


public class Camera extends JPanel implements  Runnable {

	long current_time;


	private int frame_width = 800;
	private int frame_height = 600;
	
	private Mouse_Adapter mouse_adapter;
	private Board board;

	int current_zoom = 0;
	int initial_pos_x = 0;
	int initial_pos_y = 0;
	int pos_x;
	int pos_y;

	Dimension window_size = new Dimension( this.frame_width, this.frame_height );

	public Camera( java.awt.Color color  ){
		setBackground( color );
		setOpaque( true );
		setBorder( BorderFactory.createLineBorder( Color.blue ) );
		setPreferredSize( getMinimumSize( ) );
	

		board = new Board( color, this ) ;
		mouse_adapter = new Mouse_Adapter( this, this.board );
		addMouseListener( this.mouse_adapter );
		addMouseWheelListener( this.mouse_adapter );
		addMouseMotionListener( this.mouse_adapter );

	}

	public void set_drag_delta( int x, int y ) {
	    if( initial_pos_x == 0 ) {
	    	initial_pos_x = pos_x;
	    	initial_pos_y = pos_y;
		}
		pos_x = initial_pos_x + x;
		pos_y = initial_pos_y + y;
	}

	public void reset_initial_pos( ) {
		initial_pos_x = 0;
		initial_pos_y = 0;
	}

    public void setup_key_bindings( ) {
        this.getInputMap( ).put( KeyStroke.getKeyStroke( KeyEvent.VK_ADD, 0 ), "zoom_in" );
        this.getActionMap( ).put( "zoom_in", new AbstractAction( ) {
			public void actionPerformed( ActionEvent e ) {
				zoom_in();
            }
        });

        this.getInputMap( ).put( KeyStroke.getKeyStroke( KeyEvent.VK_SUBTRACT, 0 ), "zoom_out" );
        this.getActionMap( ).put( "zoom_out", new AbstractAction( ) {
            public void actionPerformed( ActionEvent e ) {
				zoom_out();
            }
        });
    }

    public void zoom_in( ) {
		int _z = this.current_zoom + 1;
		_z = Math.max( 0, _z );
		_z = Math.min( 4, _z );
		this.current_zoom = _z;
	}

	public void zoom_out( ) {
		int _z = this.current_zoom - 1;
		_z = Math.max( 0, _z );
		_z = Math.min( 4, _z );
		this.current_zoom = _z;
	}

	@Override
	public void paintComponent( Graphics g ) {
		super.paintComponent( g );
		int _x = this.pos_x;
		int _y = this.pos_y;
		board.draw_board( g, this.current_zoom, _x, _y );
//		draw_time( g );
//		board.draw_ground( g, current_zoom );
//		board.draw_circles( g );
//		board.draw_anchors( g );
	}

    public void draw_time( Graphics g ) {
		Graphics2D g2d = ( Graphics2D ) g;
		g2d.drawString( Long.toString( current_time ), 15, 15 );
	}

	public Dimension getMinimumSize( ) {
		return window_size;
	}
	
	public Dimension getPreferredSize( ) {
		return window_size;
	}

	public void addNotify( ) {
		super.addNotify();
		Thread animator = new Thread( this );
		animator.start( );
	}

    public void run( ) {
		long before_time;
		long time_diff;
		long sleep;
		current_time = System.currentTimeMillis( );
		before_time = System.currentTimeMillis( );

//		Integer mod_val = 200;

		while( true ) {
			current_time += 1;
			repaint( );
			time_diff = System.currentTimeMillis( ) - before_time;
			sleep = 10 - time_diff;
			if ( sleep < 0 ) {
				sleep = 2;
			}

			board.draw_circles( );

			try{
				Thread.sleep( sleep );
			} catch ( InterruptedException e ) {
				System.out.println( "Interupted" );
			}
			before_time = System.currentTimeMillis( );

		}
	}

}
