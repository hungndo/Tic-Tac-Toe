import javax.swing.JFrame;

@SuppressWarnings("serial")
public class TicTacToe extends JFrame {

	TicTacToe(){
		setSize(500,500);
		setTitle("TicTacToe");
		add(new Board());
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		JFrame frame=new TicTacToe();
		frame.setVisible(true);

	}

}
