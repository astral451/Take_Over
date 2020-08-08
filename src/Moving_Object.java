/**
 * Created by nathan on 7/1/18.
 */

//import .Base_Object;
import java.lang.Math;

class Vector {

    private float vec_x;
    private float vec_y;

    public Vector( float x, float y ) {
        this.vec_x = x;
        this.vec_y = y;
    }

    public Vector multiply( int velocity ) {
        float _vel = ( ( float ) velocity );
	    float x = this.vec_x *  _vel;
	    float y = this.vec_y *  _vel;
	    Vector vec = new Vector( x, y );
	    return vec;
    }

    public float get_x( ) {
        return vec_x;
    }

    public float get_y( ) {
        return vec_y;
    }
}


public class Moving_Object extends Base_Object {


    private int velocity; // Pixels per second

    private int dest_x;
    private int dest_y;

    public Moving_Object( int x, int y ) {
        super( x, y );
    }

    public void set_destination( int x, int y ) {
        dest_x = x;
        dest_y = y;
    }

    public Vector get_unit_vector(  ) {

	    int x_dif = dest_x - this.pos_x;
	    int x_sq = x_dif * x_dif;
	    int y_dif = dest_y - this.pos_y;
	    int y_sq = y_dif * y_dif;

        double x_plus_y = ( double )( x_sq + y_sq );
	    float abs_v = ( float )java.lang.Math.sqrt( x_plus_y );

	    float x_norm = (float)x_dif / abs_v;
	    float y_norm = (float)y_dif / abs_v;

	    Vector vector = new Vector( x_norm, y_norm );

	    return vector;

    }

    public void translate( float time ) {

	    Vector vect = get_unit_vector( );
	    Vector vel_vect = vect.multiply( velocity );
	
	    this.pos_x = this.pos_x + ( ( int ) vel_vect.get_x( ) );
	    this.pos_y = this.pos_y + ( ( int ) vel_vect.get_y( ) );

    }

}
