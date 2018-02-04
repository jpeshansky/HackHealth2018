import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Pos;

/**
 *
 * @author Amy Yu
 *
 */

public class HealthGUI extends Application
{
    private static final int WIDTH = 1000; // constant for window width
    private static final int HEIGHT = 600; // constant for window height
    private Stage window;
    private User user;
    private Enemy currentEnemy;
    private VBox enemy;
    private ArrayList logList = new ArrayList();
    private ListView logScroll = new ListView();
    private VBox enemyView = new VBox(); 
        
    @Override // override the start method in the Application class
    public void start(Stage primaryStage)
    {
        window = primaryStage;
        user = new User();
        createEnemy();
        BorderPane screen = initializeScreen();
        Scene scene = new Scene(screen, WIDTH, HEIGHT);
        primaryStage.setTitle("Hack Health 2018");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public BorderPane initializeScreen()
    {
        BorderPane screen = new BorderPane();
        
        VBox stats = initializeStats();
        screen.setLeft(stats);
        
        VBox activity = initializeActivity();
        screen.setCenter(activity);
        
        VBox attacks = initializeAttacks();
        screen.setRight(attacks);
        
        HBox controlPanel = new HBox();
        Button quit = new Button("Quit");
        QuitHandlerClass quitHandler = new QuitHandlerClass();
        quit.setOnAction(quitHandler);
        quit.setFont(Font.font("Arial Black", FontWeight.BOLD, 15));
        controlPanel.setAlignment(Pos.CENTER);
        controlPanel.getChildren().add(quit);
        controlPanel.setStyle("-fx-padding: 10;");
        screen.setBottom(controlPanel);
        
        screen.setStyle("-fx-background-color: #efefef;");
        
        return screen;
    }
    
    public VBox initializeStats()
    {
        VBox stats = new VBox(20);
        Label statsLabel = new Label("Stats");
        statsLabel.setFont(Font.font("Arial Black", FontWeight.BOLD, 30));
        
        Label name = new Label("Name: " + user.getName());
        name.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        Label age = new Label("Age: " + user.getAge());
        age.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        Label level = new Label("Level: " + user.getLevel());
        level.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        
        Label enemiesToday = new Label("Today's Enemies: " + user.getEnemiesBeatToday());
        enemiesToday.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        Label totalEnemies = new Label("Total Enemies: " + user.getTotalEnemiesBeat());
        totalEnemies.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        
        
        stats.getChildren().addAll(statsLabel, name, age, level, enemiesToday, totalEnemies);
        stats.setStyle("-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black;" +
                "-fx-padding: 10;");
        stats.setPrefWidth(250);
        stats.setAlignment(Pos.CENTER);
        return stats;
    }
    
    public VBox initializeActivity()
    {
        VBox activity = new VBox();
        
        enemy = new VBox();
        Label enemyLabel = new Label("Enemy");
        enemyLabel.setFont(Font.font("Arial Black", FontWeight.BOLD, 30));
        /*
        Image enemyImage = new Image("File:"+currentEnemy.getAppearance());
        ImageView enemyImageView = new ImageView(enemyImage);
        enemyImageView.preserveRatioProperty();
        enemyImageView.setFitWidth(200);
        enemyImageView.setFitHeight(200);
        */
        enemyView.setAlignment(Pos.TOP_CENTER);
        enemy.getChildren().addAll(enemyLabel, enemyView);
        enemy.setStyle("-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black;");
        enemy.setAlignment(Pos.TOP_CENTER);
        VBox log = new VBox();
        Label logLabel = new Label("Log");
        logLabel.setFont(Font.font("Arial Black", FontWeight.BOLD, 30));
        //logList = new ArrayList<String>();
        //logScroll = new ListView();
        logScroll.getItems().setAll(logList);
        log.getChildren().addAll(logLabel, logScroll);
        log.setStyle("-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black;");
        log.setAlignment(Pos.CENTER);
        
        activity.getChildren().addAll(enemy, log);
        return activity;
    }
    
    public void writeToLog(String s) {
    	logList.add(0, s);
    	logScroll.getItems().setAll(logList);
    }
    
    public VBox initializeAttacks()
    {
        VBox attacks = new VBox(10);
        Label attacksLabel = new Label("Attacks");
        attacksLabel.setFont(Font.font("Arial Black", FontWeight.BOLD, 30));
        
        Button water = new Button("Drink water");
        water.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        
        Button fruit = new Button("Eat fruit");
        fruit.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        
        Button vegetables = new Button("Eat vegetables");
        vegetables.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        
        Button exercise = new Button("Exercise");
        exercise.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        
        water.setOnAction(e -> {
        	int previousHealth = currentEnemy.getHealth();
        	if (currentEnemy.getAttacked(0)	<= 0) {
        		writeToLog(currentEnemy.getName() + " has been defeated! It drowns in its tears.");
        		createEnemy();
        	}
        	else {
        		writeToLog("You did "+ (previousHealth-currentEnemy.getHealth())+ " damage. " + currentEnemy.getName() + " has " + currentEnemy.getHealth()+ " health left.");
        	}
        });
        fruit.setOnAction(e -> {
        	int previousHealth = currentEnemy.getHealth();
        	if (currentEnemy.getAttacked(1)	<= 0) {
        		writeToLog(currentEnemy.getName() + " has been defeated! It was always a bad seed.");
        		createEnemy();
        	}
        	else {
        		writeToLog("You did "+ (previousHealth-currentEnemy.getHealth())+ " damage. " + currentEnemy.getName() + " has " + currentEnemy.getHealth()+ " health left.");
        	}
        });        
        vegetables.setOnAction(e -> {
        	int previousHealth = currentEnemy.getHealth();
        	if (currentEnemy.getAttacked(2)	<= 0) {
        		writeToLog(currentEnemy.getName() + " has been defeated! It enters a vegetative state.");
        		createEnemy();
        	}
        	else {
        		writeToLog("You did "+ (previousHealth-currentEnemy.getHealth())+ " damage. " + currentEnemy.getName() + " has " + currentEnemy.getHealth()+ " health left.");
        	}
        });        
        exercise.setOnAction(e -> {
        	int previousHealth = currentEnemy.getHealth();
        	if (currentEnemy.getAttacked(3)	<= 0) {
        		writeToLog(currentEnemy.getName() + " has been defeated! It simply does not have the strength.");
        		createEnemy();
        	}
        	else {
        		writeToLog("You did "+ (previousHealth-currentEnemy.getHealth())+ " damage. " + currentEnemy.getName() + " has " + currentEnemy.getHealth()+ " health left.");
        	}
        });
        
        attacks.getChildren().addAll(attacksLabel, water, fruit, vegetables, exercise);
        attacks.setStyle("-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black;" +
                "-fx-padding: 10;");
        attacks.setPrefWidth(200);
        attacks.setAlignment(Pos.CENTER);
        return attacks;
    }
    
    public void createEnemy() {
    	currentEnemy = new Enemy();
    	writeToLog("A wild "+currentEnemy.getName() + " has appeared.");
    	Image enemyImage = new Image("File:"+currentEnemy.getAppearance());
        ImageView enemyImageView = new ImageView(enemyImage);
        enemyImageView.preserveRatioProperty();
        enemyImageView.setFitWidth(200);
        enemyImageView.setFitHeight(200);
        enemyView.getChildren().clear();
        enemyView.getChildren().addAll(enemyImageView);
    }
        
    public static void main(String[] args)
    {
        launch(args);
    }
    
    private boolean hasEnemy() {
		return currentEnemy.isAlive();
	}

	class QuitHandlerClass implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            window.close();
        }
    }
}
