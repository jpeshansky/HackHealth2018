import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* Jennifer Peshansky
 * 111049923
 * CSE 260
 * Feb 3, 2018 - HackHealth 2018 
 */

public class User {
	public static final int NUMBER_OF_POSSIBLE_ATTACKS = 4;
	//Stats
	private String name; //what the user chooses to go by
	private int age; //time since starting the app
	private int level;
	private int enemiesBeatToday;
	private int totalEnemiesBeat;

	public User() {
		this("src/DefaultNewUser");
	}

	public User(String fileName) {
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader reader = new BufferedReader(fileReader);
			
			name = reader.readLine();
			age = Integer.parseInt(reader.readLine());
			level = Integer.parseInt(reader.readLine());
			enemiesBeatToday = Integer.parseInt(reader.readLine());
			totalEnemiesBeat = Integer.parseInt(reader.readLine());

		} catch(FileNotFoundException e) {
			System.err.println("Unable to open file '" + fileName + "'");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public User(boolean load) {
		this("src/saveFile");
	}

	public void save() {
		String saveFile = "src/saveFile";
		try {
			FileWriter fileWriter = new FileWriter(saveFile);
			BufferedWriter writer = new BufferedWriter(fileWriter);
			
			writer.write(name);
			writer.newLine();
			writer.write(""+age);
			writer.newLine();
			writer.write(""+level);
			writer.newLine();
			writer.write(""+enemiesBeatToday);
			writer.newLine();
			writer.write(""+totalEnemiesBeat);
			writer.newLine();
			
			writer.close();
		}catch(FileNotFoundException e) {
			System.err.println("Unable to open file '" + saveFile + "'");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	//basic getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void getOlder() {
		age++; //age is in days
	}
	public int getLevel() {
		return level;
	}
	public void levelUp() {
		level++;
	}
	public int getEnemiesBeatToday() {
		return enemiesBeatToday;
	}
	public int getTotalEnemiesBeat() {
		return totalEnemiesBeat;
	}
	public void defeatEnemy() {
		defeatEnemy(1);
	}
	public void defeatEnemy(int enemies) {
		enemiesBeatToday+= enemies;
		totalEnemiesBeat+= enemies;
	}



}
