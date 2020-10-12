import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GameOfLife extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel contentPane;
	private JButton[][] net = new JButton[30][30]; //the cells.
	private int[][] net1 = new int[30][30]; // an array, that have to be checked for alive cells!
	private int[][] net2 = new int[30][30]; // an array for next generation.
	private int k = 0; // counter to count which cells are alive of the 8 neighbor cells!

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOfLife frame = new GameOfLife();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GameOfLife() {
		setTitle("Game Of Life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Panel, that contains the 30x30 cells.
		panel = new JPanel();
		panel.setBounds(0, 0, 423, 298);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(30, 30));

		//Adding the cells(as JButtons) to the panel!
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 30; j++) {
				net[i][j] = new JButton();
				net[i][j].setEnabled(false);
				panel.add(net[i][j]);
				
				//Initialized alive cells at the beginning of the game (first generation).
				net1[i][j] = (int)(0 + Math.random() * 2);
				if(net1[i][j] == 1) {
					net[i][j].setBackground(Color.BLACK);
				}
			}
		}

		JButton nextGeneration = new JButton("Next Generation");
		nextGeneration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//check which cells are alive around cells, which are not alive!
				for (int i = 0; i < 30; i++) {
					for (int j = 0; j < 30; j++) {
						if (i > 0 && j > 0 && i < 29 && j < 29) {
							if (net1[i][j] == 0) {
								if (net1[i - 1][j] == 1) {
									k = k + 1;
								}
								if (net1[i + 1][j] == 1) {
									k = k + 1;
								}
								if (net1[i][j - 1] == 1) {
									k = k + 1;
								}
								if (net1[i][j + 1] == 1) {
									k = k + 1;
								}

								if (net1[i - 1][j - 1] == 1) {
									k = k + 1;
								}
								if (net1[i - 1][j + 1] == 1) {
									k = k + 1;
								}
								if (net1[i + 1][j - 1] == 1) {
									k = k + 1;
								}
								if (net1[i + 1][j + 1] == 1) {
									k = k + 1;
								}
								
								if (k == 3) {
									net2[i][j] = 1;
								}
								k = 0;
								
								//check which cells are alive around cells, which are alive!
							} else if (net1[i][j] == 1) {

								if (net1[i - 1][j] == 1) {
									k = k + 1;
								}
								if (net1[i + 1][j] == 1) {
									k = k + 1;
								}
								if (net1[i][j - 1] == 1) {
									k = k + 1;
								}
								if (net1[i][j + 1] == 1) {
									k = k + 1;
								}

								if (net1[i - 1][j - 1] == 1) {
									k = k + 1;
								}
								if (net1[i - 1][j + 1] == 1) {
									k = k + 1;
								}
								if (net1[i + 1][j - 1] == 1) {
									k = k + 1;
								}
								if (net1[i + 1][j + 1] == 1) {
									k = k + 1;
								}

								if (k == 2 || k == 3) {
									net2[i][j] = 1;
								}
								
								k = 0;
							}
							
						}
						
						// condition for the upper left corner.
						if (i == 0 && j == 0) {
							if (net1[i][j] == 0) {
								if (net1[1][0] == 1 && net1[1][1] == 1 && net1[0][1] == 1)
									net2[i][j] = 1;
							}

							else if (net1[i][j] == 1) {
								if ((net1[1][0] == 1 && net1[1][1] == 1) || (net1[1][0] == 1 && net1[0][1] == 1)
										|| (net1[0][1] == 1 && net1[1][0] == 1))
									net2[i][j] = 1;
							}
						}

						// condition for the upper right corner.
						if (i == 0 && j == 29) {
							if (net1[i][j] == 0) {
								if (net1[0][28] == 1 && net1[1][28] == 1 && net1[1][29] == 1)
									net2[i][j] = 1;
							} else if (net1[i][j] == 1) {
								if ((net1[0][28] == 1 && net1[1][28] == 1) || (net1[0][28] == 1 && net1[1][29] == 1)
										|| (net1[1][28] == 1 && net1[1][29] == 1))
									net2[i][j] = 1;
							}

						}

						// condition for the lower left corner.
						if (i == 29 && j == 0) {
							if (net1[i][j] == 0) {
								if (net1[29][1] == 1 && net1[28][0] == 1 && net1[28][1] == 1)
									net2[i][j] = 1;
							} else if (net1[i][j] == 1) {
								if ((net1[28][0] == 1 && net1[28][1] == 1) || (net1[28][0] == 1 && net1[29][1] == 1)
										|| (net1[28][1] == 1 && net1[29][1] == 1))
									net2[i][j] = 1;
							}

						}

						// condition for the lower right corner, without the corners.
						if (i == 29 && j == 29) {
							if (net1[i][j] == 0) {
								if (net1[29][28] == 1 && net1[28][28] == 1 && net1[28][29] == 1)
									net2[i][j] = 1;
							}

							else if (net1[i][j] == 1) {
								if ((net1[28][29] == 1 && net1[28][28] == 1) || (net1[28][29] == 1 && net1[29][28] == 1)
										|| (net1[28][28] == 1 && net1[29][28] == 1))
									net2[i][j] = 1;
							}
						}

						// condition for the upper cells, without the corners.
						if (i == 0 && (j > 0 && j < 29)) {
							if (net1[i][j] == 0) {
								if (net1[i][j - 1] == 1) {
									k = k + 1;
								}
								if (net1[i][j + 1] == 1) {
									k = k + 1;
								}
								if (net1[i + 1][j] == 1) {
									k = k + 1;
								}
								if (net1[i + 1][j - 1] == 1) {
									k = k + 1;
								}
								if (net1[i + 1][j + 1] == 1) {
									k = k + 1;
								}

								if (k == 3) {
									net2[i][j] = 1;
								}
								k = 0;
							} else if (net1[i][j] == 1) {
								if (net1[i][j - 1] == 1) {
									k = k + 1;
								}
								if (net1[i][j + 1] == 1) {
									k = k + 1;
								}
								if (net1[i + 1][j] == 1) {
									k = k + 1;
								}
								if (net1[i + 1][j - 1] == 1) {
									k = k + 1;
								}
								if (net1[i + 1][j + 1] == 1) {
									k = k + 1;
								}

								if (k == 2 || k == 3) {
									net2[i][j] = 1;
								}
								k = 0;
							}
						}

						// condition for the left cells, without the corners.
						if ((i > 0 && i < 29) && j == 0) {
							if (net1[i][j] == 0) {
								if (net1[i - 1][j] == 1) {
									k = k + 1;
								}
								if (net1[i + 1][j] == 1) {
									k = k + 1;
								}
								if (net1[i + 1][j + 1] == 1) {
									k = k + 1;
								}
								if (net1[i - 1][j + 1] == 1) {
									k = k + 1;
								}
								if (net1[i][j + 1] == 1) {
									k = k + 1;
								}

								if (k == 3) {
									net2[i][j] = 1;
								}
								k = 0;
							}

							else if (net1[i][j] == 1) {
								if (net1[i - 1][j] == 1) {
									k = k + 1;
								}
								if (net1[i + 1][j] == 1) {
									k = k + 1;
								}
								if (net1[i + 1][j + 1] == 1) {
									k = k + 1;
								}
								if (net1[i - 1][j + 1] == 1) {
									k = k + 1;
								}
								if (net1[i][j + 1] == 1) {
									k = k + 1;
								}

								if (k == 2 || k == 3) {
									net2[i][j] = 1;
								}
								k = 0;
							}
						}

						// condition for the lower cells, without the corners.
						if (i == 29 && (j > 0 && j < 29)) {
							if (net1[i][j] == 0) {
								if (net1[i][j - 1] == 1) {
									k = k + 1;
								}
								if (net1[i][j + 1] == 1) {
									k = k + 1;
								}
								if (net1[i - 1][j] == 1) {
									k = k + 1;
								}
								if (net1[i - 1][j - 1] == 1) {
									k = k + 1;
								}
								if (net1[i - 1][j + 1] == 1) {
									k = k + 1;
								}

								if (k == 3) {
									net2[i][j] = 1;
								}
								k = 0;
							}

							else if (net1[i][j] == 1) {
								if (net1[i][j - 1] == 1) {
									k = k + 1;
								}
								if (net1[i][j + 1] == 1) {
									k = k + 1;
								}
								if (net1[i - 1][j] == 1) {
									k = k + 1;
								}
								if (net1[i - 1][j - 1] == 1) {
									k = k + 1;
								}
								if (net1[i - 1][j + 1] == 1) {
									k = k + 1;
								}

								if (k == 2 || k == 3) {
									net2[i][j] = 1;
								}
								k = 0;
							}
						}

						// condition for the right cells.
						if ((i > 0 && i < 29) && j == 29) {
							if (net1[i][j] == 0) {
								if (net1[i - 1][j] == 1) {
									k = k + 1;
								}
								if (net1[i + 1][j] == 1) {
									k = k + 1;
								}
								if (net1[i - 1][j - 1] == 1) {
									k = k + 1;
								}
								if (net1[i][j - 1] == 1) {
									k = k + 1;
								}
								if (net1[i + 1][j - 1] == 1) {
									k = k + 1;
								}

								if (k == 3) {
									net2[i][j] = 1;
								}
								k = 0;
							}

							else if (net1[i][j] == 1) {
								if (net1[i - 1][j] == 1) {
									k = k + 1;
								}
								if (net1[i + 1][j] == 1) {
									k = k + 1;
								}
								if (net1[i - 1][j - 1] == 1) {
									k = k + 1;
								}
								if (net1[i][j - 1] == 1) {
									k = k + 1;
								}
								if (net1[i + 1][j - 1] == 1) {
									k = k + 1;
								}

								if (k == 2 || k == 3) {
									net2[i][j] = 1;
								}
								k = 0;
							}
						}
					}
				}

				// alive and not alive cells in the next generation!
				for (int i = 0; i < 30; i++) {
					for (int j = 0; j < 30; j++) {
						if (net2[i][j] == 0) {
							net[i][j].setBackground(null);
						}
						if (net2[i][j] == 1) {
							net[i][j].setBackground(Color.BLACK);
						}

					}
				}

				// making the next generation as initialized generation for the next Button click!
				for (int i = 0; i < 30; i++) {
					for (int j = 0; j < 30; j++) {
						net1[i][j] = net2[i][j];
						net2[i][j] = 0;
					}
				}
			}

		});
		nextGeneration.setBounds(140, 309, 150, 23);
		nextGeneration.setFocusPainted(false);
		contentPane.add(nextGeneration);

	}

}

