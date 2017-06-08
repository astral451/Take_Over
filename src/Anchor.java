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
	
	private int[ ] image_resized_index = { 0, 1, 2, 3, 4, 5 };
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
		
		if( zoom_level >= 0 && zoom_level - 1 < this.image_resized_index.length ) {
			if( this.image_resized[ zoom_level ] != null ) {
				return this.image_resized[ zoom_level ];
			}
		}
		Image resized_image = this.image.getScaledInstance( 20,  20, java.awt.Image.SCALE_SMOOTH );
		this.image_resized[ zoom_level ] = resized_image;
		return resized_image;	
	}

	public void draw_anchor( Graphics g ) {
		Graphics2D g2d = ( Graphics2D ) g;

		g2d.drawImage( get_resize( 1 ), this.x, this.y, this.panel );
	}
	

}
