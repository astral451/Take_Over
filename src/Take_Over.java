
import java.awt.EventQueue;
import java.awt.Color;
import javax.swing.JFrame;


public class Take_Over extends JFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Take_Over( ) {
		initUI( );
	}

	public void initUI( ){
		// TODO Auto-generated method stub
		add ( new Camera( Color.BLACK ) );
		setResizable( false );
		pack( );
		setTitle( "Take Over" );
		setLocationRelativeTo( null );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}


	public static void main(String[] args) {
		EventQueue.invokeLater( new Runnable( ) {
            public void run( ) {
                JFrame ex = new Take_Over( );
                ex.setVisible( true );
            }
        } );

    }
}
