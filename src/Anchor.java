import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Anchor  {

	private int x;
	private int y;
	private JPanel panel;
	private String base_icon_name = "src\\image\\VG_Circle.png";
	// create icons maybe for each other color
	private ImageIcon image_icon;
	private Image image;
	
	private int[ ] image_resized_index = { 0, 1, 2, 3, 4 };
	private float[ ] scale_factors = { (float) 0.25, (float) 0.5, (float) 1.0, (float) 2.0, (float) 4.0 };
	private Image[ ] image_resized = { null, null, null, null, null, null };
	
	private int owner_int;
	private ArrayList<Anchor> anchors;

	public Anchor( int pos_x, int pos_y, JPanel panel ) {
		this.panel = panel;
		setup_icon( );
		this.x = pos_x;
		this.y = pos_y;
	}
	
	public void setup_icon( ) {
		this.image_icon = new ImageIcon( base_icon_name );
		this.image = image_icon.getImage( );
	}
	
	public Image get_resize( Integer zoom_level ) {
		
		// the base anchor size is 4m
		float base_size = (float) 10.0;

		if( this.image_resized[ zoom_level ] != null ) {
			return this.image_resized[ zoom_level ];
		}
		float _x = base_size * this.scale_factors[ zoom_level ];
		int size_x = ( int ) _x;
		int size_y = ( int ) _x;
		
		Image resized_image = this.image.getScaledInstance( size_x,  size_y, java.awt.Image.SCALE_SMOOTH );
		this.image_resized[ zoom_level ] = resized_image;
		return resized_image;	
	}

	public void draw_anchor( Graphics g, int zoom ) {
		Graphics2D g2d = ( Graphics2D ) g;
		g2d.drawImage( get_resize( zoom ), this.x, this.y, this.panel );
	}
	

}
