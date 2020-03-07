import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import java.io.File;

public class InsertImage extends Application {
    Image img;
    Image out;
    ImageView view;
    ImageView viewOut;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        GridPane center = new GridPane();
        FileChooser input = new FileChooser();

        Label title = new Label("Image Modification Algorithms");
        title.setTextFill(Color.GREENYELLOW);
        title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 32));
        title.setPadding(new Insets(50, 0, 0, 0));

        BorderPane.setAlignment(title, Pos.CENTER);
        pane.setTop(title);

        Button selectImage = new Button("Select image to modify");
        Button encrypt = new Button("Encrypt image");
        Button decrypt = new Button("Decrypt image");
        Button matrix = new Button("Perform matrix operations");

        center.setHgap(20);
        center.setVgap(30);

        GridPane.setHalignment(selectImage, HPos.CENTER);
        GridPane.setHalignment(encrypt, HPos.CENTER);
        GridPane.setHalignment(decrypt, HPos.CENTER);
        GridPane.setHalignment(matrix, HPos.CENTER);

        center.setAlignment(Pos.CENTER);
        pane.setCenter(center);

        center.add(selectImage, 0,0);
        center.add(encrypt, 0, 1);
        center.add(decrypt, 0, 2);
        center.add(matrix, 0, 3);

        String style = "-fx-background-color: rgba(0, 0, 0, 1.0);";
        pane.setStyle(style);
        pane.setBackground(Background.EMPTY);

        Rectangle rec1 = new Rectangle(500, 500);
        rec1.setFill(Color.WHITE);
        rec1.setStroke(Color.BLACK);

        Rectangle rec2 = new Rectangle(500, 500);
        rec2.setFill(Color.WHITE);
        rec2.setStroke(Color.BLACK);

        VBox box1 = new VBox(rec1);
        VBox box2 = new VBox(rec2);

        box1.setAlignment(Pos.CENTER);
        box1.setPadding(new Insets(0, 0, 0, 60));
        box2.setAlignment(Pos.CENTER);
        box2.setPadding(new Insets(0, 60, 0, 0));

        pane.setLeft(box1);
        pane.setRight(box2);

        selectImage.setOnAction(e->
        {
            final File choose = input.showOpenDialog(primaryStage);
            if(choose != null) {
                img = new Image(choose.toURI().toString());
                view = new ImageView(img);

                view.setFitWidth(500);
                view.setFitHeight(500);
//                viewOut.setPreserveRatio(true);   //do we want to preserve the ratio or force it to be 500x500?

                VBox box = new VBox(view);
                box.setAlignment(Pos.CENTER);
                box.setPadding(new Insets(0, 0, 0, 60));
                pane.setLeft(box);
            }
        });

        encrypt.setOnAction(e-> {
            //call encrypt function instead of input
            File encrypted = input.showOpenDialog(primaryStage);
            if(encrypted != null) {
                out = new Image(encrypted.toURI().toString());
                viewOut = new ImageView(out);

                viewOut.setFitWidth(500);
                viewOut.setFitHeight(500);

                VBox box = new VBox(viewOut);
                box.setAlignment(Pos.CENTER);
                box.setPadding(new Insets(0, 60, 0, 0));
                pane.setRight(box);
            }
        });

        decrypt.setOnAction(e-> {
            //call decrypt function instead of input
            File decrypted = input.showOpenDialog(primaryStage);
            if(decrypted != null) {
                out = new Image(decrypted.toURI().toString());
                viewOut = new ImageView(out);

                viewOut.setFitWidth(500);
                viewOut.setFitHeight(500);

                VBox box = new VBox(viewOut);
                box.setAlignment(Pos.CENTER);
                box.setPadding(new Insets(0, 60, 0, 0));
                pane.setRight(box);
            }
        });

        matrix.setOnAction(e-> {
            //call matrix function instead of input
            //we can make multiple matrix buttons if we want to
            File result = input.showOpenDialog(primaryStage);
            if(result != null) {
                out = new Image(result.toURI().toString());
                viewOut = new ImageView(out);

                viewOut.setFitWidth(500);
                viewOut.setFitHeight(500);

                VBox box = new VBox(viewOut);
                box.setAlignment(Pos.CENTER);
                box.setPadding(new Insets(0, 60, 0, 0));
                pane.setRight(box);
            }
        });

        //insert algorithm functions here

        Scene scene = new Scene(pane);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setTitle("AlgGUI"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setMaximized(true);
        primaryStage.show(); // Display the stage
    }
}
