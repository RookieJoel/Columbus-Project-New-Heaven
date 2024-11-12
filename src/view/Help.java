package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Help {

    public void showHelpWindow() {
        // สร้าง Stage ใหม่สำหรับหน้าต่าง Help
        Stage helpStage = new Stage();
        helpStage.initModality(Modality.APPLICATION_MODAL); // ทำให้เป็น modal window
        helpStage.setTitle("How to Play");

        // สร้างข้อความวิธีการเล่น
        Text helpText = new Text("How to play:\n1. กดปุ่ม START เพื่อเริ่มเกม\n2. กดปุ่ม Roll เพื่อทอยลูกเต๋า\n3. ลูกเต๋าจะแสดงผลคะแนน\n4. คะแนนจะถูกคำนวณและแสดงผล\n\nเล่นให้สนุก!");
        helpText.setFont(Font.font("Arial", 18));
        helpText.setFill(Color.BLACK);

        // ปุ่มปิดหน้าต่าง Help
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> helpStage.close());

        // จัดเรียง layout ของข้อความและปุ่ม
        VBox layout = new VBox(20, helpText, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene helpScene = new Scene(layout, 400, 300);
        helpStage.setScene(helpScene);
        helpStage.showAndWait(); // ทำให้หน้าต่าง Help ต้องปิดก่อนถึงจะกลับไป MainMenu
    }
}
