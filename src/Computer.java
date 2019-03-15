
public class Computer {	
	private int choice;
	private final Board board;
	private char[]currentmap=new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' '}; // to keep track with Button array
	Computer(Board b){
		board=b;
	}
	
	public void setMap(int index,char value) {
		currentmap[index] = value;
	}

	public void choose() {		
		int notvariable= minimax('O',0);
		currentmap[choice]='O';
		board.setButton(choice,"O");
	}
	private int score(char turn,int depth) {
		if(turn=='X')
			return -10+depth;
		else if(turn=='O')
			return 10-depth;
		else
			return 0;
	}

	private int minimax(char turn,int depth) {
		char a=findWinner(currentmap);
		if(a!='N') {
			return score(a,depth);
		}
		int max=-1,max_Index=0,min=1;
		for(int i=0;i<9;i++) {
			if(currentmap[i]==' ') {
				currentmap[i]=turn;
				int score=minimax(changeTurn(turn),depth+1);
				//find Max
				if(turn=='O') {
					if(score>max){
						max=score;
						max_Index=i;
					}
				}
				//find Minimum
				else{
					if(score<min) {
						min=score;
					}
				}
				currentmap[i]=' ';
			}
		}
		if(turn=='O') {
			choice= max_Index;
			return max;
		}
		else {
			return min;
		}
	}
	private char findWinner(char[] map) {
		//horizontal
		for(int i=0;i<9;i+=3) 
			if(map[i]==map[i+1]&&map[i]==map[i+2]&&map[i]!=' ') {
				if(map[i]=='X')
					return 'X';
				else
					return 'O';
			}
		//vertical
		for(int x=0;x<3;x++) 
			if(map[x]==map[x+3]&&map[x]==map[x+6]&&map[x]!=' ') {
				if(map[x]=='X')
					return 'X';
				else
					return 'O';
			}
		//diagnose
		if(map[0]==map[4]&&map[0]==map[8]&&map[0]!=' ') {
			if(map[0]=='X')
				return 'X';
			else
				return 'O';
		}
		if(map[2]==map[4]&&map[2]==map[6]&&map[2]!=' ') {
			if(map[2]=='X')
				return 'X';
			else
				return 'O';
		}
		// check tie
		for(int i=0;i<9;i++) {
			if(map[i]==' ')
				return 'N';
		}
		return 'T';
	}
	private char changeTurn(char x) {
		if(x=='O')
			return 'X';
		else
			return 'O';
			
	}
}
