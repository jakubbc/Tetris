import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;




public class Tetris extends JFrame implements ActionListener
{	
	private static final int borderWidth=3;
	private static final int WIDTH= 700, HEIGHT= 622+2*borderWidth; //700, 622
	
	private static Board board;
	private static JLabel scoreLabel, highScoreLabel, gameOverLabel, pausedLabel, next;
	private JButton newGame;
	
	public int newGameCount=0;
	
	public Tetris()
	{
		setTitle("Tetris Game");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		
		board= new Board();
		scoreLabel = new JLabel ("Score: 0");
		highScoreLabel= new JLabel ("High Score: "+board.getHighScore());
		gameOverLabel= new JLabel (" ");
		pausedLabel= new JLabel (" ", JLabel.CENTER);
		next= new JLabel("Next Shape", JLabel.CENTER);
		newGame= new JButton ("New Game");
		
		
		// set the panel
		JPanel right= new JPanel();
		//right.setSize(200, 621);
		right.setLayout(new GridLayout(6,1));
		right.add(next);
		right.add(pausedLabel);
		right.add(newGame);
		right.add(gameOverLabel);
		right.add(highScoreLabel);
		right.add(scoreLabel);
		
		// set the whole layout
		setLayout(new GridLayout(1,2));
		
		add(board);
		
		add(right);
		
		newGame.addActionListener(this);
		board.addKeyListener(board);
		board.setFocusable(true);
		setVisible(true);
	}
	
	
	public void actionPerformed (ActionEvent e)
	{
		String action= e.getActionCommand();
		
		if (action.equals("New Game")) {
			board.setBoard();
			if (newGameCount!=0)
				board.nextShape();
			else 
				newGameCount++;
			board.setScore(0);
			scoreLabel.setText("Score: 0");
			pausedLabel.setText(" ");
			gameOverLabel.setText(" ");
			board.startTimer();
			//board.setFocusable(true);
			board.requestFocusInWindow();
			board.setGameOver(false);
			System.out.println("start the game");
		}
		
	}
	
	public static int getBorderWidth() {
		return borderWidth;
	}
	public static JLabel getPausedLabel() {
		return pausedLabel;
	}
	public static JLabel getScoreLabel() {
		return scoreLabel;
	}
	public static JLabel getHighScoreLabel() {
		return highScoreLabel;
	}
	public static JLabel getGameOverLabel() {
		return gameOverLabel;
	}
	
	public static void main(String[]args)
	{
		new Tetris();
		for (int i=0; i<board.getBoard().length; i++) {
			for (int j=0; j<board.getBoard()[i].length; j++) 
				System.out.print(board.getBoard()[i][j]+"\t");
			System.out.println("\n");
			}
		System.out.println(scoreLabel.getText());
	}
}
