import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Ground {

	private String image_path;

	private BufferedImage ground_image = null;
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

	public Image get_image( ) {
		//Image ground_image = null;
		if ( ground_image == null ) {
			File _infile = new File( this.image_path );
			try {
				ground_image = ImageIO.read( _infile );
			} catch( IOException e ) { };
		}
		return ground_image;
	}


}
