
import java.awt.EventQueue;
import java.awt.Color;
import javax.swing.JFrame;

//import Camera.Camera;


public class Take_Over extends JFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Take_Over( ) {
		initUI( );
	}

	public void initUI( ){
		add ( new Camera( Color.BLACK ) );
		setResizable( false );
		pack( );
		setTitle( "Take Over" );
		setLocationRelativeTo( null );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}


	public static void main( String[ ] args ) {
		// I assume this starts up like a CallAfter
		EventQueue.invokeLater( new Runnable( ) {
		    public void run( ) {
			JFrame ex = new Take_Over( );
			ex.setVisible( true );
		    }
        } );

    }
}
