import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class InsertImage extends Application {
    Image img;
    Image out;
    ImageView view;
    ImageView viewOut;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        GridPane top = new GridPane();
        FileChooser input = new FileChooser();

        Label title = new Label("Image Modification Algorithms");
        title.setTextFill(Color.GREENYELLOW);
        title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 32));
        title.setPadding(new Insets(50, 0, 0, 0));

        BorderPane.setAlignment(title, Pos.CENTER);
        pane.setTop(title);
        pane.setCenter(top);

        Button selectImage = new Button("Select image to modify");
        Button pub = new Button("Select public key (decrypt only)");
        Button encrypt = new Button("Encrypt");
        Button decrypt = new Button("Decrypt");
        Button rotate = new Button("Rotate 90 degrees");
        Button reflectX = new Button("Reflect X");
        Button reflectY = new Button("Reflect Y");
        Button rotateX = new Button("Rotate about X axis");
        Button rotateY = new Button("Rotate about Y axis");
        Button pubKey = new Button("Download public key");
        Button privateKey = new Button("Download private key");

        top.setHgap(20);
        top.setVgap(30);

        top.add(encrypt, 2, 1);
        top.add(decrypt, 3, 1);
        top.add(rotate, 4, 1);
        top.add(selectImage, 0, 1);
        top.add(reflectX, 7, 1);
        top.add(reflectY, 8, 1);
        top.add(rotateX, 5, 1);
        top.add(rotateY, 6, 1);
        top.add(pub, 1, 1);
        top.add(pubKey, 9, 1);
        top.add(privateKey, 10, 1);

        String style = "-fx-background-color: rgba(0, 0, 0, 1.0);";
        pane.setStyle(style);
        pane.setBackground(Background.EMPTY);

        final String[] imagePath = {""};
        final Image[] image = new Image[1];

        final String[] imagePathPriv = {""};
        final Image[] imagePriv = new Image[1];

        selectImage.setOnAction(e->
        {
            final File choose = input.showOpenDialog(primaryStage);
            if(choose != null) {
                img = new Image(choose.toURI().toString());
                view = new ImageView(img);

                view.setFitWidth(500);
                view.setFitHeight(500);

                VBox box = new VBox(view);
                box.setAlignment(Pos.CENTER);
                box.setPadding(new Insets(50));
                pane.setBottom(box);
            }
        });

        pub.setOnAction(e->{
            final File choose = input.showOpenDialog(primaryStage);
            if(choose != null) {
                img = new Image(choose.toURI().toString());
                view = new ImageView(img);
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
                box.setPadding(new Insets(50));
                pane.setBottom(box);

//                imagePath[0] = "Downloads/protocols.png";
//                image[0] = new Image(imagePath[0]);
//
//                imagePathPriv[0] = "Downloads/protocols.png";
//                imagePriv[0] = new Image(imagePath[0]);
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
                box.setPadding(new Insets(50));
                pane.setBottom(box);
            }
        });

        rotate.setOnAction(e-> {
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
                box.setPadding(new Insets(50));
                pane.setBottom(box);
            }
        });

        rotateX.setOnAction(e->{
            final File choose = input.showOpenDialog(primaryStage);
            if(choose != null) {
                img = new Image(choose.toURI().toString());
                view = new ImageView(img);
            }
        });

        rotateY.setOnAction(e->{
            final File choose = input.showOpenDialog(primaryStage);
            if(choose != null) {
                img = new Image(choose.toURI().toString());
                view = new ImageView(img);
            }
        });

        reflectX.setOnAction(e->{
            final File choose = input.showOpenDialog(primaryStage);
            if(choose != null) {
                img = new Image(choose.toURI().toString());
                view = new ImageView(img);
            }
        });

        reflectY.setOnAction(e->{
            final File choose = input.showOpenDialog(primaryStage);
            if(choose != null) {
                img = new Image(choose.toURI().toString());
                view = new ImageView(img);
            }
        });

        pubKey.setOnAction(e->{
//            saveToFile(image[0]);
        });

        privateKey.setOnAction(e->{
//            saveToFile(imagePriv[0]);
        });

        Scene scene = new Scene(pane);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setTitle("AlgGUI"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setMaximized(true);
        primaryStage.show(); // Display the stage
    }

//    public static void saveToFile(Image image) {
//        File outputFile = new File("C:/JavaFX/");
//        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
//        try {
//            ImageIO.write(bImage, "png", outputFile);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
