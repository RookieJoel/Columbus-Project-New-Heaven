package game;

import pane.BuildActionPane;
import pane.ConfirmationDialog;
import pane.EndGamePane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import pane.ActionPane;
import pane.HexagonPane;
import pane.StatusPane;
import player.Player;
import view.MainMenu;

public class GameController {
    private static GameController instance; // Singleton instance
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private HexagonPane hexagonPane;

    private BuildActionPane buildPane1;
    private BuildActionPane buildPane2;
    private ActionPane actionPane1;
    private ActionPane actionPane2;

    private Build build1; // Add Build for Player 1
    private Build build2; // Add Build for Player 2
    
    private StatusPane player1StatusPane;
    private StatusPane player2StatusPane;
    
    private boolean turnActionCompleted; // Flag to track if an action is completed in the current turn
    
 // MediaPlayers for different music
    private static MediaPlayer mainMenuMusicPlayer;
    private static MediaPlayer gameMusicPlayer;
    private static MediaPlayer endGameMusicPlayer;
    
    private GameController(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1; // Default to Player 1
        
        
    }

    public static GameController getInstance(Player player1, Player player2) {
        if (instance == null) {
            instance = new GameController(player1, player2);
        }
        return instance;
    }

    public static GameController getInstance() {
        if (instance == null) {
            throw new IllegalStateException("GameController is not initialized. Call getInstance(Player, Player) first.");
        }
        return instance;
    }

    public void setBuilds(Build build1, Build build2) {
        this.build1 = build1;
        this.build2 = build2;
    }

    public void setBuildActionPanes(BuildActionPane pane1, BuildActionPane pane2) {
        this.buildPane1 = pane1;
        this.buildPane2 = pane2;
    }

    public void setActionPanes(ActionPane pane1, ActionPane pane2) {
        this.actionPane1 = pane1;
        this.actionPane2 = pane2;
    }
    public void setStatusPanes(StatusPane player1StatusPane, StatusPane player2StatusPane) {
        this.player1StatusPane = player1StatusPane;
        this.player2StatusPane = player2StatusPane;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    

    public void switchTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        
        turnActionCompleted = false; // Reset the flag for the new turn
        // Reset build status for the new turn
        if (currentPlayer == player1 && build1 != null) {
            build1.resetBuildStatus(); // Reset for Player 1
            actionPane1.enableBuildButton(); // Enable build button for Player 1
        } else if (currentPlayer == player2 && build2 != null) {
            build2.resetBuildStatus(); // Reset for Player 2
            actionPane2.enableBuildButton(); // Enable build button for Player 2
        }

        getCurrentActionPane().enableAllButtons();

        updatePaneVisibility();
        currentPlayer.printInventory(); // Debug inventory at the start of the turn
    }
    
    public boolean isTurnActionCompleted() {
        return turnActionCompleted;
    }

    public void markActionCompleted() {
        this.turnActionCompleted = true;
        getCurrentActionPane().disableAllButtons();
    }
    
    
    public static void reset() {
        instance = null; // Clear the existing instance
    }

    
    public ActionPane getCurrentActionPane() {
        return (currentPlayer == player1) ? actionPane1 : actionPane2;
    }
    
    public StatusPane getCurrentStatusPane() {
        return (currentPlayer == player1) ? player1StatusPane : player2StatusPane;
    }

    private void updatePaneVisibility() {
        // Hide all panes
        if (buildPane1 != null) buildPane1.setVisible(false);
        if (buildPane2 != null) buildPane2.setVisible(false);
        if (actionPane1 != null) actionPane1.setVisible(false);
        if (actionPane2 != null) actionPane2.setVisible(false);

        // Show only the current player's panes
        if (currentPlayer == player1) {
            if (actionPane1 != null) actionPane1.setVisible(true);
            if (buildPane1 != null) buildPane1.updateButtonStates(); // Update button states
        } else {
            if (actionPane2 != null) actionPane2.setVisible(true);
            if (buildPane2 != null) buildPane2.updateButtonStates(); // Update button states
        }
    }
    
    public StatusPane getStatusPaneForPlayer(Player player) {
        if (player == player1) {
            return player1StatusPane;
        } else if (player == player2) {
            return player2StatusPane;
        }
        return null; // Default to null if the player is not recognized
    }

    public boolean isGameEnd() {
    	if(player1.getHp() <= 0 || player2.getHp() <=0) {
    		return true; 		
    	}
    	return false;
    }
    
    public void endGame(StackPane rootPane,Stage stage) {
    	EndGamePane endGamePane = new EndGamePane(rootPane);
		Runnable openMainMenuAction = () -> {
			MainMenu mainMenu = new MainMenu();
            Stage mainStage = new Stage();
            try {
                mainMenu.start(mainStage);
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
        if(player1.getHp() <= 0) {
        	endGamePane.show(
                    player2.getName() + " Win!!",
                    openMainMenuAction // Action to execute when confiSrmed
                );
        	return;
        }
        endGamePane.show(
        		player1.getName() + " Win!!",
                openMainMenuAction // Action to execute when confirmed
            );
    }
    
    // Centralized method to stop all music
    public static void stopAllMusic() {
        if (mainMenuMusicPlayer != null) {
            mainMenuMusicPlayer.stop();
        }
        if (gameMusicPlayer != null) {
            gameMusicPlayer.stop();
        }
        if (endGameMusicPlayer != null) {
            endGameMusicPlayer.stop();
        }
    }

    // Setters for MediaPlayer instances
    public static void setMainMenuMusicPlayer(MediaPlayer player) {
        mainMenuMusicPlayer = player;
    }

    public static void setGameMusicPlayer(MediaPlayer player) {
        gameMusicPlayer = player;
    }

    public static void setEndGameMusicPlayer(MediaPlayer player) {
        endGameMusicPlayer = player;
    }
    
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
    
    public HexagonPane getHexagonPane() {
        return hexagonPane;
    }

	public void setHexagonPane(HexagonPane hexagonPane) {
		this.hexagonPane = hexagonPane;
		
	}
}