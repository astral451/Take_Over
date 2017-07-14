
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.lang.Long;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;


public class Board  {
	long current_time;
	private int board_width = 1024;
	private int board_height = 1024;
	private ArrayList<Circle> circles;
	private ArrayList<Anchor> anchors;
	
	private Ground ground;
	private JPanel top_panel;

	private int current_zoom = 4;

//	Dimension window_size = new Dimension( board_width, board_height );
	Thread animator;


	public void actionPerformed(ActionEvent e) { }
		

	public Board( java.awt.Color color, JPanel top_panel ) {

		this.ground = new Ground( 400, 400, "src/image/test_ground.jpg" );
		this.top_panel = top_panel;

		this.circles = new ArrayList<Circle>( );
		this.anchors = new ArrayList<Anchor>( );
		
//		this.setup_key_bindings( );
	}
	
//	public void setup_key_bindings( ) {
//		this.getinputmap( ).put( keystroke.getkeystroke( keyevent.vk_add, 0 ), "zoom_in" );
//		this.getactionmap( ).put( "zoom_in", new abstractaction( ) {
//			public void actionperformed( actionevent e ) {
//				zoom_in();
//			}
//		});
//
//		this.getinputmap( ).put( keystroke.getkeystroke( keyevent.vk_subtract, 0 ), "zoom_out" );
//		this.getactionmap( ).put( "zoom_out", new abstractaction( ) {
//			public void actionperformed( actionevent e ) {
//				zoom_out();
//			}
//		});
//	}


	public void draw_ground( Graphics g ) {
		Graphics2D g2d = ( Graphics2D ) g;
		g2d.drawImage( this.ground.get_image_scaled( this.current_zoom ), 0, 0, this.top_panel );
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
		this.anchors.add( new Anchor( anchor_pos_x, anchor_pos_y, this.top_panel ) );
	}

	public void add_circle( int circle_pos_x, int circle_pos_y ) {
    	this.circles.add( new Circle( circle_pos_x, circle_pos_y ) );
	}

//	private Integer get_random_int( Integer min, Integer max ) {
//		Integer delta = max - min;		
//		return (int) Math.round( ( Math.random( ) * delta ) );
//	}

	public void zoom_in( ) {
		int _z = this.current_zoom + 1;
		_z = Math.max( 0, _z );
		_z = Math.min( 4, _z );
		this.current_zoom = _z;
	}

	public void zoom_out( ) {
		//
		int _z = this.current_zoom - 1;
		_z = Math.max( 0, _z );
		_z = Math.min( 4, _z );
		this.current_zoom = _z;
	}


	public void draw_circles( ) {

//         Random rain-drops
//			if( ( current_time  % mod_val ) == 0 ) {
//    			if( 1 == 1 ) {
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


        // remove any non-visible circles
//			ArrayList<Integer> idx_to_delete = new ArrayList<Integer>( );
        for( int i = this.circles.size( ) -1; i == 0; i-- ) {
            Circle circle = this.circles.get( i );
            if( !circle.visible( ) ) {
                this.circles.remove( i );
            }
        }
	}
}
