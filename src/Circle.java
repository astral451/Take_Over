import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;


public class Circle {
	
	private int x, y;
	private int radius;
	private int color_r, color_b, color_g;

	public Circle( int x, int y ) {
		this.x = x;
		this.y = y;
		radius = 0;
		color_r = 0;
		color_g = 0;
		color_b = 0;
	}
	
	public void grow( ) {
		radius += 1;
		color_r += 255 / 30;
		color_g += 255 / 30;
		color_b += 255 / 30; 
		this.color_r = Math.max(0, color_r );
		this.color_g = Math.max(0, color_g );
		this.color_b = Math.max(0, color_b );

	}
	
	public Boolean visible( ) {
		if( this.radius > 30 ) {
			return false;
		}
		return true;
	}
	
	public void draw( Graphics g ) {
		Graphics2D g2d = ( Graphics2D ) g;

		// get current color
		Color old_color = g2d.getColor( );
		Color c = new Color( this.color_r, this.color_g, this.color_b );
//		Color c = new Color( 255, 128, 128 );
		g2d.setColor( c );
		Integer new_x = this.x - this.radius / 2;
		Integer new_y = this.y - this.radius / 2;
		g2d.drawOval( new_x, new_y, this.radius, this.radius );
		
		// reset the color
		g2d.setColor( old_color );

	}


}
