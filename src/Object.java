import java.awt.*;

/**
 * Created by nathan on 8/6/17.
 */
public class Object {
    /**
     * The base that just holds position and lets you set it.  It
     * Might get more complex later.
     *
     * 8/22/2017
     */
    private int pos_x;
    private int pos_y;


    public Object( int in_pos_x, int in_pos_y ) {
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
