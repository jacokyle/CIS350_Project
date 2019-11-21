package gamePackage;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.security.Key;

/***********************************************************************
 * The primaryGUI class creates the components and general functions
 * that can be displayed when the GUI is executed.
 *
 * @author (Jeremiah Cerriteno, Kyle Jacobson, Austin Jarema)
 * @version (10/25/18)
 ***********************************************************************/
class primaryGUI extends JFrame {

	/* Buttons for the main menu */
	private JButton startBtn = new JButton("");
	private JButton loadBtn = new JButton("");
	private JButton optionBtn = new JButton("");
	private JButton secretBtn = new JButton("");

	/* Buttons for the game screen */
	private JButton exitBtn = new JButton("");
	private JButton openObjBtn = new JButton("");
	private JButton closeObjBtn = new JButton("");
	private JButton inspectObjBtn = new JButton("");
	private JButton useObjBtn = new JButton("");
	private JButton takeItemBtn = new JButton("");
	private JButton beginDialogueBtn = new JButton("");

	/* Buttons for the exiting */
	private JButton confirmBtn = new JButton("");
	private JButton cancelBtn = new JButton("");

	private static final long serialVersionUID = 1L;

	/******************************************************************
	 * Main method that runs the primaryGUI class.
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 * @throws LineUnavailableException
	 ******************************************************************/
	primaryGUI() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		super("Soul Survivor");

		// Allows an audio clip to play in the background on the main menu.
		AudioInputStream music = AudioSystem.getAudioInputStream(new File("resources\\music\\Atmospheric Lab.wav"));
		AudioFormat format = music.getFormat();
		DataLine.Info info = new DataLine.Info(Clip.class, format);
		Clip audioClip = (Clip) AudioSystem.getLine(info);

		// Opens and plays the assigned main menu audio clip.
		audioClip.open(music);
		audioClip.start();
		audioClip.loop(Clip.LOOP_CONTINUOUSLY);

		// Instantiates panels for the mainMenu.
		JLayeredPane backgroundPane = new JLayeredPane();
		JLayeredPane gameButtonsPane = new JLayeredPane();

		// Creates a layout manager to handle the organization.
		OverlayLayout overlayBackgroundPanel = new OverlayLayout(backgroundPane);

		// Sets the layouts for the panels.
		backgroundPane.setLayout(overlayBackgroundPanel);
		gameButtonsPane.setLayout(null);

		// Creates a background for the GUI using the assigned photo.
		JLabel mainMenu = new JLabel("");
		mainMenu.setMaximumSize(new Dimension(1920, 1080));
		mainMenu.setIcon(new ImageIcon("resources\\ui\\Main Menu.jpg"));
		backgroundPane.add(mainMenu);

		// ---------- Start Button ------------ //

		// Creates and aligns the start button for the main menu.
		startBtn.setOpaque(true);
		startBtn.setContentAreaFilled(false);
		startBtn.setBorderPainted(false);

		gameButtonsPane.add(startBtn);
		startBtn.setBounds(10, 905, 620, 75);

		// The functionality of the start button, changes all resources to the primary game screen.
		startBtn.addActionListener(event -> {
			if(event.getSource() == startBtn){
				// Hide the main menu buttons.
				startBtn.setVisible(false);
				loadBtn.setVisible(false);
				optionBtn.setVisible(false);
				secretBtn.setVisible(false);
				
				// Reveal the overlay buttons.
				exitBtn.setVisible(true);
				openObjBtn.setVisible(true);
				closeObjBtn.setVisible(true);
				inspectObjBtn.setVisible(true);
				useObjBtn.setVisible(true);
				takeItemBtn.setVisible(true);
				beginDialogueBtn.setVisible(true);

				// Changes the background image to the game screen.
				mainMenu.setIcon(new ImageIcon("resources\\ui\\introduction\\Intro Sequence 1.jpg"));

				// Ends the main menu music.
				audioClip.stop();

				// Assigns an audio file to an AudioInputStream.
				AudioInputStream music1 = null;
				try {
					music1 = AudioSystem.getAudioInputStream(new File("resources\\music\\Soul Survivor.wav"));
				} catch (UnsupportedAudioFileException | IOException e) {
					e.printStackTrace();
				}

				// Gets information about the music track that has been assigned.
				AudioFormat format1 = music1.getFormat();
				DataLine.Info info1 = new DataLine.Info(Clip.class, format1);
				Clip audioClip1 = null;
				try {
					audioClip1 = (Clip) AudioSystem.getLine(info1);
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}

				// Opens the updated music track for the game screen.
				try {
					audioClip1.open(music1);
				} catch (LineUnavailableException | IOException e) {
					e.printStackTrace();
				}

				// Starts and loops the updated music track for the game screen.
				audioClip1.start();
				audioClip1.loop(Clip.LOOP_CONTINUOUSLY);

				// Performs an exit action when the escape key is pressed on the keyboard.
				KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
				Action enterAction = new AbstractAction() {
					// Initializes the intro sequence frame count.
					int counter = 1;

					public void actionPerformed(ActionEvent event) {
						// Increments through each intro sequence frame.
						counter++;
						mainMenu.setIcon(new ImageIcon("resources\\ui\\introduction\\Intro Sequence " + counter + ".jpg"));

						// Stops incrementing and begins actual game with first location.
						if(counter > 5){
							mainMenu.setIcon(new ImageIcon("resources\\ui\\locations\\Passenger Quarters.jpg"));
						}
					}
				};

				// Searches for the enter key input that is pressed by the player.
				getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(enterKey, "ENTER");
				getRootPane().getActionMap().put("ENTER", enterAction);
			}
		});

		// ---------- Load Button ------------ //

		// Creates and aligns the load button for the main menu.
		loadBtn.setOpaque(true);
		loadBtn.setContentAreaFilled(false);
		loadBtn.setBorderPainted(false);

		gameButtonsPane.add(loadBtn);
		loadBtn.setBounds(10, 1003, 570, 75);

		// ---------- Options Button ------------ //

		// Creates and aligns the options button for the main menu.
		optionBtn.setOpaque(true);
		optionBtn.setContentAreaFilled(false);
		optionBtn.setBorderPainted(false);

		gameButtonsPane.add(optionBtn);
		optionBtn.setBounds(1500, 1003, 410, 75);

		// ---------- Credits Button ------------ //

		// Creates and aligns the credits button for the main menu.
		secretBtn.setOpaque(true);
		secretBtn.setContentAreaFilled(false);
		secretBtn.setBorderPainted(false);

		gameButtonsPane.add(secretBtn);
		secretBtn.setBounds(10, 10, 970, 75);

		// ---------- Exit Button ------------ //

		// Creates and aligns the exit button for the game screen.
		exitBtn.setOpaque(true);
		exitBtn.setContentAreaFilled(false);
		exitBtn.setBorderPainted(false);
		exitBtn.setVisible(false);

		gameButtonsPane.add(exitBtn);
		exitBtn.setBounds(28,950,80,80);

		// The functionality of the exit button, closes the game.
		exitBtn.addActionListener(event -> {
			System.exit(0);
		});

		// ---------- Open Object Button ------------ //

		// Creates and aligns the open object button for the game screen.
		openObjBtn.setOpaque(true);
		openObjBtn.setContentAreaFilled(false);
		openObjBtn.setBorderPainted(true);
		openObjBtn.setVisible(false);
		
		gameButtonsPane.add(openObjBtn);
		openObjBtn.setBounds(130,925,176,130);
		
		// ---------- Close Object Button ------------ //

		// Creates and aligns the close object button for the game screen.
		closeObjBtn.setOpaque(true);
		closeObjBtn.setContentAreaFilled(false);
		closeObjBtn.setBorderPainted(true);
		closeObjBtn.setVisible(false);
		
		gameButtonsPane.add(closeObjBtn);
		closeObjBtn.setBounds(332,925,175,130);
		
		// ---------- Inspect Object Button ------------ //

		// Creates and aligns the inspect object button for the game screen.
		inspectObjBtn.setOpaque(true);
		inspectObjBtn.setContentAreaFilled(false);
		inspectObjBtn.setBorderPainted(true);
		inspectObjBtn.setVisible(false);
		
		gameButtonsPane.add(inspectObjBtn);
		inspectObjBtn.setBounds(532,924,175,130);
		
		// ---------- Use Item Button ------------ //

		// Creates and aligns the use item button for the game screen.
		useObjBtn.setOpaque(true);
		useObjBtn.setContentAreaFilled(false);
		useObjBtn.setBorderPainted(true);
		useObjBtn.setVisible(false);
		
		gameButtonsPane.add(useObjBtn);
		useObjBtn.setBounds(730,924,175,130);
		
		// ---------- Take Item Button ------------ //

		// Creates and aligns the take item button for the game screen.
		takeItemBtn.setOpaque(true);
		takeItemBtn.setContentAreaFilled(false);
		takeItemBtn.setBorderPainted(true);
		takeItemBtn.setVisible(false);
		
		gameButtonsPane.add(takeItemBtn);
		takeItemBtn.setBounds(927,923,175,130);
		
		// ---------- Begin Dialogue Button ------------ //

		// Creates and aligns the begin dialogue button for the game screen.
		beginDialogueBtn.setOpaque(true);
		beginDialogueBtn.setContentAreaFilled(false);
		beginDialogueBtn.setBorderPainted(true);
		beginDialogueBtn.setVisible(false);
		
		gameButtonsPane.add(beginDialogueBtn);
		beginDialogueBtn.setBounds(1124,923,175,130);

		// Adds the primary JPanels of the GUI.
		add(gameButtonsPane);
		add(backgroundPane);

		// Defines the size and general appearance of the frame.
		setSize(1920, 1080);
		gameButtonsPane.setSize(1920, 1080);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		setVisible(true);

		// Performs an exit action when the escape key is pressed on the keyboard.
		KeyStroke escapeKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		Action escapeAction = new AbstractAction() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		};

		// Searches for the escape key input that is pressed by the player.
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKey, "ESCAPE");
		getRootPane().getActionMap().put("ESCAPE", escapeAction);
	}
}
