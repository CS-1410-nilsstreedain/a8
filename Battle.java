package a8;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

/**
 * This class creates an organized JFrame where a human player can "battle"
 * against the computer in the game Rock, Paper, Scissors. The player can select
 * their hand and also choose how the computer comes up with it's move, either
 * randomly, or based of the players last move.
 * 
 * @author Nils Streedain
 *
 */
@SuppressWarnings("serial")
public class Battle extends JFrame implements ActionListener {

	private JButton rock;
	private JButton paper;
	private JButton scissors;

	private JRadioButton random;
	private JRadioButton lastChoice;

	private String playerMove;
	private String playerLastMove;
	private String computerMoveType = "random";
	private String computerMove;
	private String winner;

	private JLabel playerHand;
	private JLabel winnerLabel;
	private JLabel computerHand;

	/**
	 * Constructs an organized JFrame User Interface with two main grid sections
	 * (2x1), an upper section for determining player and computer moves, and a
	 * lower section for showing game results and a bit of ASCII art each section
	 * contains a JPanel. Within the upper section there is another grid section
	 * (1x2), each again containing a JPanel, on the top left there is the player
	 * move selections, and on the top right there is the computer move calculation
	 * options.
	 */
	public Battle() {
		super("Rock Paper Scissors");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		rock = new JButton("Rock");
		paper = new JButton("Paper");
		scissors = new JButton("Scissors");

		random = new JRadioButton("Random Choice", true);
		lastChoice = new JRadioButton("Player's Last Choice");

		JPanel mainPanel = new JPanel();
		JPanel topPanel = new JPanel();
		JPanel topLeftPanel = new JPanel();
		JPanel topRightPanel = new JPanel();
		JPanel bottomPanel = new JPanel();

		BoxLayout box = new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS);
		mainPanel.setLayout(box);
		mainPanel.add(topPanel);
		mainPanel.add(bottomPanel);

		topPanel.setLayout(new GridLayout(1, 2));
		topPanel.setSize(topPanel.getWidth(), 0);
		topPanel.add(topLeftPanel);
		topPanel.add(topRightPanel);

		topLeftPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Your Move",
				TitledBorder.CENTER, TitledBorder.TOP));
		topLeftPanel.add(rock);
		topLeftPanel.add(paper);
		topLeftPanel.add(scissors);

		topRightPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Computer's Move",
				TitledBorder.CENTER, TitledBorder.TOP));
		topRightPanel.add(random);
		topRightPanel.add(lastChoice);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(random);
		buttonGroup.add(lastChoice);

		bottomPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Game Results",
				TitledBorder.LEFT, TitledBorder.TOP));
		bottomPanel.setLayout(new GridLayout(1, 3));
		playerHand = new JLabel("<html><br><br><br><br><br><br><br><br><br></html>");
		winnerLabel = new JLabel("");
		computerHand = new JLabel("<html><br><br><br><br><br><br><br><br><br></html>");
		bottomPanel.add(playerHand);
		playerHand.setHorizontalAlignment(JLabel.CENTER);
		bottomPanel.add(winnerLabel);
		winnerLabel.setHorizontalAlignment(JLabel.CENTER);
		bottomPanel.add(computerHand);
		computerHand.setHorizontalAlignment(JLabel.CENTER);

		setContentPane(mainPanel);
		pack();
		rock.addActionListener(this);
		paper.addActionListener(this);
		scissors.addActionListener(this);
		random.addActionListener(this);
		lastChoice.addActionListener(this);
	}

	// Getters and Setters for private variables
	public String getPlayerMove() {
		return playerMove;
	}

	public void setPlayerMove(String playerMove) {
		this.playerMove = playerMove;
	}

	public String getComputerMoveType() {
		return computerMoveType;
	}

	public void setComputerMoveType(String computerMoveType) {
		this.computerMoveType = computerMoveType;
	}

	public String getComputerMove() {
		return computerMove;
	}

	public void setComputerMove(String computerMove) {
		this.computerMove = computerMove;
	}

	public String getPlayerLastMove() {
		return playerLastMove;
	}

	public void setPlayerLastMove(String playerLastMove) {
		this.playerLastMove = playerLastMove;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	/**
	 * This method calculates what move the computer will make by first determining
	 * the move type (random or player's last move), then if random, it will pick a
	 * random int between 1 and 3, using that number to set the computerMove for
	 * "rock", "paper", or "scissors". If the lastChoice moveType is selected, the
	 * method will instead set the computerMove to "rock" if it's the first game, or
	 * to the player's last move if it's any other.
	 * 
	 * @param moveType the name of the file to read
	 */
	private void computerMove(String moveType) {
		if (moveType == "random") {
			int rand = (int) (Math.random() * 3) + 1;
			if (rand == 1) {
				setComputerMove("rock");
			} else if (rand == 2) {
				setComputerMove("paper");
			} else if (rand == 3) {
				setComputerMove("scissors");
			}
		} else if (moveType == "lastChoice") {
			if (getPlayerLastMove() != null) {
				setComputerMove(getPlayerLastMove());
			} else {
				setComputerMove("rock");
			}
		}
	}

	/**
	 * This method will take both the player's selected move and the computer's
	 * selected move and calculate who won that match based of the rules of Rock,
	 * Paper, Scissors. It will the run updatePanel();
	 */
	private void pickWinner() {
		if (getPlayerMove() == "rock") {
			if (getComputerMove() == "rock") {
				setWinner("Everyone loses.");
			} else if (getComputerMove() == "paper") {
				setWinner("Computer wins.");
			} else if (getComputerMove() == "scissors") {
				setWinner("You Win!");
			}
		}
		if (getPlayerMove() == "paper") {
			if (getComputerMove() == "rock") {
				setWinner("You Win!");
			} else if (getComputerMove() == "paper") {
				setWinner("Everyone loses.");
			} else if (getComputerMove() == "scissors") {
				setWinner("Computer wins.");
			}
		}
		if (getPlayerMove() == "scissors") {
			if (getComputerMove() == "rock") {
				setWinner("Computer wins.");
			} else if (getComputerMove() == "paper") {
				setWinner("You Win!");
			} else if (getComputerMove() == "scissors") {
				setWinner("Everyone loses.");
			}
		}
		updatePanel();
	}

	/**
	 * This method will output both who the winner is and what moves each player
	 * made, as well as some ASCII art representing their move.
	 */
	private void updatePanel() {
		if (playerMove == "rock") {
			playerHand.setText(
					"<html>Player: Rock<br><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;_______<br>---'&nbsp;&nbsp;&nbsp;&nbsp;____)<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(_____)<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(_____)<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(____)<br>---.__(___)</html>");
		} else if (playerMove == "paper") {
			playerHand.setText(
					"<html>Player: Paper<br><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;_______<br>---'&nbsp;&nbsp;&nbsp;&nbsp;____)____<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;______)<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;_______)<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;_______)<br>---._________)</html>");
		} else if (playerMove == "scissors") {
			playerHand.setText(
					"<html>Player: Scissors<br>&nbsp;&nbsp;&nbsp;    .-.  &nbsp;_<br>&nbsp;&nbsp;&nbsp;     | | / )<br>&nbsp;&nbsp;&nbsp;     | |/ /<br>&nbsp;   _|__ /_ <br>  /&nbsp; __)-' ) <br>  \\&nbsp;  `(.-') <br>   / ._>-'<br>/ \\/ </html>");
		}
		if (computerMove == "rock") {
			computerHand.setText(
					"<html>Computer: Rock<br><br><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.——_<br>&nbsp;&nbsp;.:(`&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;)<br>:(&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;))<br>`(&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;)<br>&nbsp;&nbsp;&nbsp;`&nbsp;___-:’</html>");
		} else if (computerMove == "paper") {
			computerHand.setText(
					"<html>Computer: Paper<br><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;_________<br>&nbsp;&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/<br>&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<i>Paper</i>&nbsp;&nbsp;&nbsp;/<br>&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/<br>&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;/<br>/________/</html>");
		} else if (computerMove == "scissors") {
			computerHand.setText(
					"<html>Computer: Scissors<br><br>&nbsp;&nbsp;&nbsp;&nbsp;_&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;_<br&nbsp;>&nbsp;&nbsp;&nbsp;(_)&nbsp;&nbsp;/&nbsp;&nbsp;)<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;(_/&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;_+/&nbsp;&nbsp;<br>&nbsp;&nbsp;&nbsp;//|\\<br>&nbsp;&nbsp;//&nbsp;|&nbsp;|<br>&nbsp;(/&nbsp;&nbsp;&nbsp;|/&nbsp;</html>");
		}
		winnerLabel.setText(winner);
	}

	/**
	 * The main method creates a new battle UI and sets it to visible.
	 */
	public static void main(String[] args) {
		Battle app = new Battle();
		app.setVisible(true);
	}

	/**
	 * This method overrides the actionPerformed method from ActionListener. The
	 * goal of this method it to detect when a button is pressed and decide what to
	 * do based of the button pressed. If it's a player move, it will set the
	 * players move, calculate the computer's move, and pick and display the winner.
	 * It will also log the player's move for the next round. If the computer
	 * moveType is changed, instead it will just set the computer move type.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == rock) {
			setPlayerMove("rock");
			computerMove(getComputerMoveType());
			pickWinner();
		}
		if (e.getSource() == paper) {
			setPlayerMove("paper");
			computerMove(getComputerMoveType());
			pickWinner();
		}
		if (e.getSource() == scissors) {
			setPlayerMove("scissors");
			computerMove(getComputerMoveType());
			pickWinner();
		}
		if (e.getSource() == random) {
			setComputerMoveType("random");
		}
		if (e.getSource() == lastChoice) {
			setComputerMoveType("lastChoice");
		}
		setPlayerLastMove(getPlayerMove());

	}

}
