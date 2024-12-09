package game;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.Node;
import javafx.util.Duration;
import javafx.scene.media.AudioClip;

public class AnimationUtils {

    public static void playDestructionAnimation(Node buildingNode, Runnable onComplete, String soundPath) {
        if (buildingNode == null) {
            System.err.println("Error: Building node is null.");
            return;
        }

        // Create a flashing effect
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(200), buildingNode);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.2);
        fadeTransition.setCycleCount(6); // Flash 3 times
        fadeTransition.setAutoReverse(true);

        // Create a shrinking effect
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300), buildingNode);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(0.0);
        scaleTransition.setToY(0.0);

        // Combine animations
        SequentialTransition sequentialTransition = new SequentialTransition(fadeTransition, scaleTransition);
        sequentialTransition.setOnFinished(event -> {
            // Run any additional logic after animation
            if (onComplete != null) {
                onComplete.run();
            }
        });

        // Play sound effect
        playSoundEffect(soundPath);

        // Start animation
        sequentialTransition.play();
    }

    private static void playSoundEffect(String soundPath) {
        try {
            AudioClip sound = new AudioClip(AnimationUtils.class.getResource(soundPath).toString());
            sound.play();
        } catch (Exception e) {
            System.err.println("Error playing sound effect: " + e.getMessage());
        }
    }
}
