import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener{
	private char turn;
	private Computer computer=new Computer(this);
	private boolean gameIsGoingOn;
	private JButton[] button= new JButton[9];
	Board() {
		for(int y=0;y<9;y++)
		{
				setLayout(new GridLayout(3,3));
				button[y]=new MyButton(y);
				add(button[y]);
		}
		setBackground(Color.BLACK);
		setBorder(BorderFactory.createLineBorder(Color.gray));
		setFocusable(true);
		initialize();
	}
	public void setButton(int x,String value) {
		button[x].setText(value);
		}
	
	public JButton getButton(int x) {
		return button[x];
	}
	
	public char getTurn() {
		return turn;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(gameIsGoingOn)
		{	
			if(turn=='O') {
				computer.choose();
				turn='X';
			};
			char a=findWinner();
			if(a!='N') {
				gameIsGoingOn=false;
				popUpDialog(a);
			}
		}		
	}
	public void initialize() {
		for(int y=0;y<9;y++)
		 {
				button[y].setText(" ");
			}
		turn='X';
		gameIsGoingOn=true;
		Timer t=new Timer(100,this);
		t.start();
	}
	public char findWinner() {
		//horizontal
		for(int i=0;i<9;i+=3) 
			if(button[i].getText()==button[i+1].getText()&&button[i].getText()==button[i+2].getText()&&button[i].getText()!=" ")
				if(button[i].getText()=="X")
					return 'X';
				else
					return 'O';
		//vertical
		for(int x=0;x<3;x++) 
			if(button[x].getText()==button[x+3].getText()&&button[x].getText()==button[x+6].getText()&&button[x].getText()!=" ")
				if(button[x].getText()=="X")
					return 'X';
				else
					return 'O';
		//diagnose
		if(button[0].getText()==button[4].getText()&&button[0].getText()==button[8].getText()&&button[0].getText()!=" ")
			if(button[0].getText()=="X")
				return 'X';
			else
				return 'O';
		if(button[2].getText()==button[4].getText()&&button[2].getText()==button[6].getText()&&button[2].getText()!=" ")
			if(button[2].getText()=="X")
				return 'X';
			else
				return 'O';
		for(int i=0;i<9;i++) {
			if(button[i].getText()==" ")
				return 'N';
		}
		return 'T';
	}
	public class MyButton extends JButton implements ActionListener{
		private int index;
		MyButton(int i){
			index=i;
			addActionListener(this);
			setFont(new Font("Dialog",5,100));
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(turn=='X'&&getText().equals(" ")) {
				setText("X"); turn='O';
				computer.setMap(index, 'X');
			}
		}
	
		
	}
	private void popUpDialog(char winner){
		int playagain;
		if(winner=='X')
			playagain=JOptionPane.showConfirmDialog(null, "Dang! Do you want to play again?","WON",JOptionPane.YES_NO_OPTION);
		else if(winner=='O')
			playagain=JOptionPane.showConfirmDialog(null, "You stupid! Do you want to play again?","LOST",JOptionPane.YES_NO_OPTION);
		else
			playagain=JOptionPane.showConfirmDialog(null, " Tie! Do you want to play again?","TIE",JOptionPane.YES_NO_OPTION);
		///
		if(playagain==JOptionPane.YES_OPTION) {
			for(int i=0;i<9;i++) {
				button[i].setText(" ");
				computer.setMap(i,' ');
			}
			turn='X';
			gameIsGoingOn=true;
		}
		else{
			System.exit(0);
		}
	}
	
}
