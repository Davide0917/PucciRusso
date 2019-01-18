package GameGraphic;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;

import javax.swing.JPanel;

enum STATE {
	HOME, GAME, PAUSE, GAMEOVER, EXIT
};

public class GameMenu extends JPanel {

	int ResolutionX, ResolutionY;
	STATE state;

	Image background;
	Toolkit tk;
	MediaTracker spriteTracker;

	Font customFont, titleFont;
	int index, maxOptions;

	public GameMenu() {
		this.setBackground(new Color(255, 255, 255));
		tk = Toolkit.getDefaultToolkit();
		spriteTracker = new MediaTracker(this);

		background = tk.getImage("resources/image/Sfondo_Menu.jpg");
		spriteTracker.addImage(background, 0);
		try {
			spriteTracker.waitForAll();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/Font/The_Bellovia_Sans.ttf"))
					.deriveFont(70f);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(customFont);
			titleFont = Font.createFont(Font.TRUETYPE_FONT, new File("resources/Font/The_Bellovia_Sans.ttf"))
					.deriveFont(100f);
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(titleFont);
		} catch (FontFormatException | IOException e) {
			System.out.println("Font non caricato! ");
		}

		index = maxOptions = 0;
		state = STATE.HOME;

		InitEH();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw background
		g.drawImage(background, 0, 0, ResolutionX, ResolutionY, null);

		if (state == STATE.HOME) {
			String title = "11 SEPTEMBER";
			String c1 = "SINGLEPLAYER";
			String c2 = "SETTINGS";
			String c3 = "EXIT";

			g.setFont(titleFont);
			g.setColor(new Color(0, 0, 0));
			g.drawString(title, ResolutionX / 2 - (g.getFontMetrics().stringWidth(title) / 2),
					ResolutionY / 2 - g.getFontMetrics().getHeight());

			g.setFont(customFont);
			g.setColor(new Color(0, 0, 0));

			if (index == 0)
				g.setColor(new Color(255, 255, 255, 200));
			else
				g.setColor(new Color(208, 174, 100, 200));
			g.drawString(c1, ResolutionX / 2 - (g.getFontMetrics().stringWidth(c1) / 2),
					ResolutionY / 2 + g.getFontMetrics().getHeight());

			if (index == 1)
				g.setColor(new Color(255, 255, 255, 200));
			else
				g.setColor(new Color(208, 174, 100, 200));
			g.drawString(c2, ResolutionX / 2 - (g.getFontMetrics().stringWidth(c2) / 2),
					ResolutionY / 2 + g.getFontMetrics().getHeight() * 2);

			if (index == 2)
				g.setColor(new Color(255, 255, 255, 200));
			else
				g.setColor(new Color(208, 174, 100, 200));
			g.drawString(c3, ResolutionX / 2 - (g.getFontMetrics().stringWidth(c3) / 2),
					ResolutionY / 2 + g.getFontMetrics().getHeight() * 4);
			maxOptions = 3;
		} else if (state == STATE.PAUSE) {
			String c1 = "CONTINUE";
			String c2 = "MAIN MENU";

			g.setFont(customFont);
			if (index == 0)
				g.setColor(new Color(255, 255, 255, 200));
			else
				g.setColor(new Color(208, 174, 100, 200));
			g.drawString(c1, ResolutionX / 2 - (g.getFontMetrics().stringWidth(c1) / 2),
					ResolutionY / 2 + g.getFontMetrics().getHeight());

			if (index == 1)
				g.setColor(new Color(255, 255, 255, 200));
			else
				g.setColor(new Color(208, 174, 100, 200));
			g.drawString(c2, ResolutionX / 2 - (g.getFontMetrics().stringWidth(c2) / 2),
					ResolutionY / 2 + g.getFontMetrics().getHeight() * 2);
			maxOptions = 2;
		} else if (state == STATE.GAMEOVER) {
			String title = "GAMEOVER";
			String c2 = "EXIT";

			g.setFont(titleFont);
			g.setColor(new Color(208, 174, 100, 200));
			g.drawString(title, ResolutionX / 2 - (g.getFontMetrics().stringWidth(title) / 2),
					ResolutionY / 2 - g.getFontMetrics().getHeight());

			g.setFont(customFont);
			g.setColor(new Color(208, 174, 100, 200));

			g.drawString(c2, ResolutionX / 2 - (g.getFontMetrics().stringWidth(c2) / 2),
					ResolutionY / 2 + g.getFontMetrics().getHeight() * 2);
			maxOptions = 1;
		}
	}

	private void InitEH() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == 'w')
					DecraseIndex();
				if (e.getKeyChar() == 's')
					IncreaseIndex();
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					state = modifyState();
				}

				repaint();
			}
		});
	}

	@Override
	public void setPreferredSize(Dimension preferredSize) {
		super.setPreferredSize(preferredSize);

		ResolutionY = (int) preferredSize.getHeight();
		ResolutionX = (int) preferredSize.getWidth();
	}

	public void IncreaseIndex() {
		index++;
		if (index >= maxOptions)
			index = 0;
	}

	public void DecraseIndex() {
		index--;
		if (index < 0)
			index = maxOptions - 1;
	}

	public STATE modifyState() {
		if (state == STATE.HOME && index == 0)
			return STATE.GAME;

		if (state == STATE.PAUSE && index == 0)
			return STATE.GAME;
		if (state == STATE.PAUSE && index == 1)
			return STATE.HOME;

		return STATE.EXIT;
	}

	public STATE getState() {
		return state;
	}

	public void setState(STATE state) {
		this.state = state;
	}
}
