package javadex;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.browser.Browser;


//NOTE: Any and all images are property of those who created them, I do not own
//any sprites or images used in this project

public class gui {

	protected Shell shell;
	private Text inputName;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			gui window = new gui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(566, 394);
		shell.setText("Javadex v1.0");
		
		Label lblPokemon = new Label(shell, SWT.NONE);
		lblPokemon.setBounds(10, 10, 55, 21);
		lblPokemon.setText("Pokemon:");
		
		inputName = new Text(shell, SWT.BORDER);
		inputName.setBounds(71, 10, 111, 21);
		
		Label pokeResultsField = new Label(shell, SWT.NONE);
		pokeResultsField.setBounds(10, 68, 208, 21);
		
		Label seperation = new Label(shell, SWT.NONE);
		seperation.setBounds(10, 79, 297, 15);
		
		Label pokeNum = new Label(shell, SWT.NONE);
		pokeNum.setBounds(10, 100, 137, 21);
		
		Label pokeType = new Label(shell, SWT.NONE);
		pokeType.setBounds(10, 127, 137, 21);
		
		Label strongAgainst = new Label(shell, SWT.NONE);
		strongAgainst.setBounds(10, 181, 414, 21);
		
		Label weakAgainst = new Label(shell, SWT.NONE);
		weakAgainst.setBounds(10, 154, 414, 21);
		
		Browser browser = new Browser(shell, SWT.NONE);
		browser.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
		browser.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		browser.setUrl("");
		browser.setBounds(429, 10, 111, 111);
		
		Label spriteBackground = new Label(shell, SWT.NONE);
		spriteBackground.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		spriteBackground.setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
		spriteBackground.setBounds(420, 0, 130, 132);
		
		//ADD BACKGROUND COLOR CHANGE DEPENDING ON TYPE
		Button submitButton = new Button(shell, SWT.NONE);
		submitButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String pokeName = inputName.getText();
				try {
					String path = "pokemon_list.csv"; //REMEMBER TO CHANGE BEFORE EXPORTING TO JUST pokemon_list.csv
					BufferedReader reader = new BufferedReader(new FileReader(path));
					String line = "";
										
					while(line != null){
						{
						line = reader.readLine();
						if(line == null) {
							break;
						}
						String[] values = line.split(",");
						if(values[1].toLowerCase().equals(pokeName.toLowerCase())) {
							pokeResultsField.setText("Results for: "+pokeName);
							seperation.setText("..................................................");
							System.out.println(line);
							pokeNum.setText("Dex No.: " + values[0]);
							try {
								pokeType.setText("Type(s): " + values[2] + " " + values[3]);
							}
							catch ( IndexOutOfBoundsException i ) {
								pokeType.setText("Type(s): " + values[2]);
							}

							browser.setUrl("www.pokestadium.com/sprites/xy/"+ pokeName.toLowerCase() +".gif");
							
							if(values[2].toLowerCase().equals("normal")) {
								strongAgainst.setText("Super-Effective Against: " + "None");
								weakAgainst.setText("Not Very Effective Against: " + "Rock" + " " + "Steel");
							}
							else if(values[2].toLowerCase().equals("fire")) {
								strongAgainst.setText("Super-Effective Against: " + "Grass" + " " + "Ice" + " " + "Bug" + " " + "Steel");
								weakAgainst.setText("Not Very Effective Against: " + "Fire" + " " + "Water" + " " + "Rock" + " " + "Dragon");
							}
							else if(values[2].toLowerCase().equals("water")) {
								strongAgainst.setText("Super-Effective Against: " + "Fire" + " " + "Ground" + " " + "Rock");
								weakAgainst.setText("Not Very Effective Against: " + "Water" + " " + "Grass" + " " + "Dragon");
							}
							else if(values[2].toLowerCase().equals("electric")) {
								strongAgainst.setText("Super-Effective Against: " + "Water" + " " + "Flying");
								weakAgainst.setText("Not Very Effective Against: " + "Electric" + " " + "Grass" + " " + "Dragon");
							}
							else if(values[2].toLowerCase().equals("grass")) {
								strongAgainst.setText("Super-Effective Against: " + "Water" + " " + "Ground" + " " + "Rock");
								weakAgainst.setText("Not Very Effective Against: " + "Fire" + " " + "Grass" + " " + "Poison" + " " + "Flying" + " " + "Bug" + " " + "Dragon" + " " + "Steel");
							}
							else if(values[2].toLowerCase().equals("ice")) {
								strongAgainst.setText("Super-Effective Against: " + "Grass" + " " + "Ground" + " " + "Flying" + " " + "Dragon");
								weakAgainst.setText("Not Very Effective Against: " + "Fire" + " " + "Water" + " " + "Ice" + " " + "Steel");
							}
							else if(values[2].toLowerCase().equals("fighting")) {
								strongAgainst.setText("Super-Effective Against: " + "Normal" + " " + "Ice" + " " + "Rock" + " " + "Dark" + " " + "Steel");
								weakAgainst.setText("Not Very Effective Against: " + "Fighting" + " " + "Flying" + " " + "Psychic" + " " + "Bug" + " " + "Fairy");
							}
							else if(values[2].toLowerCase().equals("poison")) {
								strongAgainst.setText("Super-Effective Against: " + "Grass" + " " + "Fairy");
								weakAgainst.setText("Not Very Effective Against: " + "Fighting" + " " + "Poison" + " " + "Rock" + " " + "Bug");
							}
							else if(values[2].toLowerCase().equals("ground")) {
								strongAgainst.setText("Super-Effective Against: " + "Fire" + " " + "Electric" + " " + "Poison" + " " + "Steel" + " " + "Rock");
								weakAgainst.setText("Not Very Effective Against: " + "Grass" + " " + "Bug");
							}
							else if(values[2].toLowerCase().equals("flying")) {
								strongAgainst.setText("Super-Effective Against: " + "Grass" + " " + "Fighting" + " " + "Bug");
								weakAgainst.setText("Not Very Effective Against: " + "Electric" + " " + "Rock" + " " + "Steel");
							}
							else if(values[2].toLowerCase().equals("psychic")) {
								strongAgainst.setText("Super-Effective Against: " + "Fighting" + " " + "Poison");
								weakAgainst.setText("Not Very Effective Against: " + "Psychic" + " " + "Steel");
							}
							else if(values[2].toLowerCase().equals("bug")) {
								strongAgainst.setText("Super-Effective Against: " + "Grass" + " " + "Flying" + " " + "Dragon");
								weakAgainst.setText("Not Very Effective Against: " + "Fire" + " " + "Fighting" + " " + "Poison" + " " + "Flying" + " " + "Ghost" + " " + "Steel" + " " + "Fairy");
							}
							else if(values[2].toLowerCase().equals("rock")) {
								strongAgainst.setText("Super-Effective Against: " + "Fire" + " " + "Ice" + " " + "Flying" + " " + "Bug");
								weakAgainst.setText("Not Very Effective Against: " + "Fighting" + " " + "Ground" + " " + "Steel");
							}
							else if(values[2].toLowerCase().equals("ghost")) {
								strongAgainst.setText("Super-Effective Against: " + "Psychic" + " " + "Ghost");
								weakAgainst.setText("Not Very Effective Against: " + "Dark");
							}
							else if(values[2].toLowerCase().equals("dragon")) {
								strongAgainst.setText("Super-Effective Against: " + "Dragon");
								weakAgainst.setText("Not Very Effective Against: " + "Steel");
							}
							else if(values[2].toLowerCase().equals("dark")) {
								strongAgainst.setText("Super-Effective Against: " + "Psychic" + " " + "Ghost");
								weakAgainst.setText("Not Very Effective Against: " + "Fighting" + " " + "Dragon" + " " + "Fairy");
							}
							else if(values[2].toLowerCase().equals("steel")) {
								strongAgainst.setText("Super-Effective Against: " + "Ice" + " " + "Rock" + " " + "Fairy");
								weakAgainst.setText("Not Very Effective Against: " + "Fire" + " " + "Water" + " " + "Electric" + " " + "Steel");
							}
							else if(values[2].toLowerCase().equals("fairy")) {
								strongAgainst.setText("Super-Effective Against: " + "Fighting" + " " + "Dark" + " " + "Dragon");
								weakAgainst.setText("Not Very Effective Against: " + "Fire" + " " + "Steel" + " " + "Poison");
							}
						}
					
						}
					
					}
					reader.close();
					
				} catch (IOException a) {
					// TODO Auto-generated catch block
					a.printStackTrace();
				}
			}
		});
		submitButton.setBounds(10, 37, 75, 25);
		submitButton.setText("Search");
		
	}
}
