import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Ground {

	private String image_path;
	// Expermienting with fixed zoom factors
	// 0, 	1, 	2, 3, 4 
	// .25, .5, 1, 2, 4 
	private float[ ] scale_factors = { (float) 0.25, (float) 0.5, (float) 1.0, (float) 2.0, (float) 4.0 };
	private Image[ ] ground_images = { null, null, null, null, null };

	// These are in meters and are 'real world' scale
	// and it relates directly to the image.  Even if
	// the image is not exactly the right size, it will
	// be scaled to these dimensions.
	private int dimension_x;
	private int dimension_y;
	
	public Ground( int dim_x, int dim_y, String image_path ) {
		this.image_path = image_path;
		this.dimension_x = dim_x;
		this.dimension_y = dim_y;
	}
	
	public Image get_image_scaled( int zoom ) {
		ImageIcon image_icon = new ImageIcon( this.image_path );
		float zoom_conversion = scale_factors[ zoom ];
		float _x = ( float ) this.dimension_x * zoom_conversion;
		float _y = ( float ) this.dimension_y * zoom_conversion;

		int size_x = ( int ) _x;
		int size_y = ( int ) _y;

		if( this.ground_images[ zoom ] == null ){
			this.ground_images[ zoom ] = image_icon.getImage( ).getScaledInstance( size_x, size_y, java.awt.Image.SCALE_SMOOTH );
		}
		return this.ground_images[ zoom ];
	}
	
}
