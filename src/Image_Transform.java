/**
 * This class is a struct for outputting position and size of a
 * particular image.  All data is public so set and get back in
 * the same way.
 *
 * Created by nathan on 3/22/18.
 */
public class Image_Transform {

    public int pos_x;
    public int pos_y;
    public int size_x;
    public int size_y;

    public Image_Transform( int pos_x, int pos_y, int size_x, int size_y ) {

        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.size_x = size_x;
        this.size_y = size_y;

    }

}
