import java.awt.*;
import java.lang.*;

/**
 * Created by nathan on 8/6/17.
 */
public class Base_Object {
    /**
     * The base that just holds position and lets you set it.  It
     * Might get more complex later.
     *
     * 8/22/2017
     */
    public int pos_x;
    public int pos_y;


    public Base_Object( int in_pos_x, int in_pos_y ) {
        pos_x = in_pos_x;
        pos_y = in_pos_y;
    }

    public Point get_position( ) {
        return  new Point( pos_x, pos_y );
    }

    public void set_position( int in_pos_x, int in_pos_y ) {
        pos_x = in_pos_x;
        pos_y = in_pos_y;
    }

}
