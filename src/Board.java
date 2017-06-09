
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.lang.Long;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;


public class Board extends JPanel implements Runnable, ActionListener {
	long current_time;
	private int board_width = 800;
	private int board_height = 600;
	private ArrayList<Circle> circles;
	private ArrayList<Anchor> anchors;
	
	private Ground ground;

	private int current_zoom = 4;

	Dimension window_size = new Dimension( board_width, board_height );
	Thread animator;

	private Mouse_Adapter mouse_adapter;
	private Key_Adapter key_adapter;
	
	public void actionPerformed(ActionEvent e) { }
		

	public Board(java.awt.Color color ) {
		// TODO Auto-generated constructor stub
		setBackground( color );
		setOpaque( true );
		setBorder(BorderFactory.createLineBorder(Color.black));
		setPreferredSize( getMinimumSize( ) );

		this.ground = new Ground( 400, 400, "src/image/test_ground.jpg" );

		this.circles = new ArrayList<Circle>( );
		this.anchors = new ArrayList<Anchor>( );
		this.mouse_adapter = new Mouse_Adapter( this );
		this.key_adapter = new Key_Adapter( this );
		
		this.addMouseListener( this.mouse_adapter );
		this.addKeyListener( this.key_adapter );
	}

	@Override
	public void paintComponent( Graphics g ) {
		super.paintComponent( g );
		draw_ground( g );
		draw_time( g );
		draw_circles( g );
		draw_anchors( g );
	}
	
	public Dimension getMinimumSize( ) {
		return window_size;
	}
	
	public Dimension getPreferredSize( ) {
		return window_size;
	}

	public void addNotify( ) {
		super.addNotify();
		animator = new Thread( this );
		animator.start( );
	}

	public void draw_ground( Graphics g ) {
		Graphics2D g2d = ( Graphics2D ) g;
		g2d.drawImage( this.ground.get_image_scaled( this.current_zoom ), -100, -100, this );
	}

	public void draw_time( Graphics g ) {
		Graphics2D g2d = ( Graphics2D ) g;
		g2d.drawString( Long.toString( current_time ), 15, 15 );
	}
	
	public void draw_circles( Graphics g ) {
		for ( Circle c : this.circles ) {
			if( c.visible() ) {
				c.draw( g );
			}
		}
	}

	public void draw_anchors( Graphics g ) {

		for( int i = 0;  i < this.anchors.size( ); i++ ) {
			this.anchors.get( i ).draw_anchor( g, this.current_zoom );
		}

	}
	
	public void add_anchor( int anchor_pos_x, int anchor_pos_y ) {
		this.anchors.add( new Anchor( anchor_pos_x, anchor_pos_y, this ) );
	}

	public void add_circle( int circle_pos_x, int circle_pos_y ) {
				this.circles.add( new Circle( circle_pos_x, circle_pos_y ) );
	}

//	private Integer get_random_int( Integer min, Integer max ) {
//		Integer delta = max - min;		
//		return (int) Math.round( ( Math.random( ) * delta ) );
//	}

	public void zoom_in( ) {
		int _z = this.current_zoom++;
		_z = Math.max( 0, _z );
		_z = Math.min( 4, _z );
		this.current_zoom = _z;
	}

	public void zoom_out( ) {
		//
		int _z = this.current_zoom--;
		_z = Math.max( 0, _z );
		_z = Math.min( 4, _z );
		this.current_zoom = _z;
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

			// Random rain-drops
//			if( ( current_time  % mod_val ) == 0 ) {
////			if( 1 == 1 ) {
//				Integer circle_pos_x = get_random_int( 0, board_width );
//				Integer circle_pos_y = get_random_int( 0, board_height );
//				this.circles.add( new Circle( circle_pos_x, circle_pos_y ) );
//				mod_val = get_random_int( 0, 200 );
//				// never allow 0
//				while( mod_val == 0 ) {
//					mod_val = get_random_int( 0, 200 );
//				}
//			}

			if( this.circles != null  &&  this.circles.size( ) > 0 ) {
				for( Circle c : circles ) {
					if ( c.visible( ) ) {
						c.grow();
					}
				}
			}
			
			try{ 
				Thread.sleep( sleep );
			} catch ( InterruptedException e ) {
				System.out.println( "Interupted" );
			}
			before_time = System.currentTimeMillis( );

			// remove any non-visible circles
//			ArrayList<Integer> idx_to_delete = new ArrayList<Integer>( );
			for( int i = this.circles.size( ) -1; i == 0; i-- ) {
				Circle circle = this.circles.get( i );
				if( circle.visible( ) == false ) {
					this.circles.remove( i );
				}
			}
		}
	}
}
