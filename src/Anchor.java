/*
 *
 * The Anchor object goes here
 *
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Anchor extends Base_Object {

	private JPanel panel;
	private String base_icon_name = "image\\node.png";

	// create icons maybe for each other color
	private ImageIcon image_icon;
	private Image image;
	
//	private int owner_int;
	private ArrayList<Anchor> anchors;

	public Anchor( int pos_x, int pos_y, JPanel panel ) {
		// call to the Base_Object
		super( pos_x, pos_y );
		setup_icon( );

		this.panel = panel;
	}
	
	public void setup_icon( ) {
		this.image_icon = new ImageIcon( base_icon_name );
		this.image = image_icon.getImage( ).getScaledInstance( 16, 16, Image.SCALE_SMOOTH );
	}
	

	public void draw_anchor( Graphics g, Camera cam ) { //int zoom, int offset_x, int offset_y ) {
		Graphics2D g2d = ( Graphics2D ) g;
		Point point = this.get_position( );
		int w = this.image.getWidth( this.panel );
		int h = this.image.getHeight( this.panel );

		Image_Transform im_trans = cam.transform_image( point.x, point.y, w, h );


//		w += zoom;
//		h += zoom;
		g2d.drawImage(
				this.image,
				//point.x + offset_x - ( ( w + zoom ) / 2 ),
				//point.y + offset_y - ( ( h + zoom ) / 2 ),
				im_trans.pos_x,
				im_trans.pos_y,
				im_trans.size_x,
				im_trans.size_y,
				this.panel
		);

		g2d.drawString( String.format( "x %d, y %d",  point.x, point.y ), im_trans.pos_x, im_trans.pos_y );
		Image_Transform sc_trans = cam.world_to_screen( point.x, point.y );
		g2d.drawString( String.format( "sx %d, y %d", sc_trans.pos_x, sc_trans.pos_y  ), sc_trans.pos_x, sc_trans.pos_y + 30 );
	}
	

}
