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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import javax.imageio.ImageIO;

public class InsertImage extends Application {
    BufferedImage img;
    BufferedImage secondImg;
    Image out;
    ImageView view;
    String store;

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        GridPane top = new GridPane();
        FileChooser input = new FileChooser();

        TextField fold = new TextField("C:\\Users\\{{username}}\\Documents");
        fold.selectAll();
        Label folder = new Label("Type the desired folder path and hit 'Enter'");
        folder.setFont(Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR, 14));
        folder.setTextFill(Color.WHITE);

        Label title = new Label("Image Modification Algorithms");
        title.setTextFill(Color.GREENYELLOW);
        title.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 32));
        title.setPadding(new Insets(50, 0, 0, 0));

        BorderPane.setAlignment(title, Pos.CENTER);
        pane.setTop(title);
        pane.setCenter(top);

        Button selectImage = new Button("Select image to modify");
        Button second = new Button("Select a second image (for image combiner)");
        Button combine = new Button("Combine");
        Button rotate = new Button("Rotate 90 degrees");
        Button reflectX = new Button("Reflect X");
        Button reflectY = new Button("Reflect Y");
        Button grayScale = new Button("Gray Scale");
        Button rotateX = new Button("Rotate about X");
        Button rotateY = new Button("Rotate about Y");

        top.setHgap(20);
        top.setVgap(30);
        BorderPane.setMargin(top, new Insets(0, 0, 0, 40));

        top.add(second, 0, 1);
        top.add(combine, 1, 1);
        top.add(rotate, 2, 1);

        top.add(selectImage, 3, 1);
        GridPane.setMargin(selectImage, new Insets(0, 50, 0, 50));
        GridPane.setHalignment(selectImage, HPos.CENTER);

        top.add(grayScale, 4, 1);
        top.add(rotateX, 5, 1);
        top.add(rotateY, 6, 1);
        top.add(reflectX, 7, 1);
        top.add(reflectY, 8, 1);

        top.add(folder, 3, 2);
        GridPane.setHalignment(folder, HPos.CENTER);
        top.add(fold, 3, 3);
        GridPane.setHalignment(fold, HPos.CENTER);

        String style = "-fx-background-color: rgba(0, 0, 0, 1.0);";
        pane.setStyle(style);
        pane.setBackground(Background.EMPTY);

        fold.setOnAction(e-> {
            store = fold.getText();
        });

        selectImage.setOnAction(e->
        {
            File choose = input.showOpenDialog(primaryStage);
            if(choose != null) {
                try{
                    img = ImageIO.read(choose);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                out = new Image(choose.toURI().toString());
                view = new ImageView(out);

                view.setFitWidth(400);
                view.setFitHeight(400);

                VBox box = new VBox(view);
                box.setAlignment(Pos.CENTER);
                box.setPadding(new Insets(20));
                pane.setBottom(box);
            }
        });

        second.setOnAction(e->{
            File choose = input.showOpenDialog(primaryStage);
            if(choose != null) {
                try {
                    secondImg = ImageIO.read(choose);
                    int type = secondImg.getType() == 0? BufferedImage.TYPE_INT_ARGB
                            : secondImg.getType();
                    secondImg = resizeImage(secondImg, type, img.getWidth(), img.getHeight());
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                out = new Image(choose.toURI().toString());
                view = new ImageView(out);

                view.setFitWidth(400);
                view.setFitHeight(400);

                VBox box = new VBox(view);
                box.setAlignment(Pos.CENTER);
                box.setPadding(new Insets(20));
                pane.setBottom(box);
            }
        });

        combine.setOnAction(e-> {
            try {
                BufferedImage result = combineImages(img, secondImg);
                img = result;
                File output = new File(store   + "\\newImage.png");
                ImageIO. write(result, "png", output);
                out = new Image(String.valueOf(output.toURI().toURL()));
                view = new ImageView(out);

                view.setFitWidth(400);
                view.setFitHeight(400);

                VBox box = new VBox(view);
                box.setAlignment(Pos.CENTER);
                box.setPadding(new Insets(20));
                pane.setBottom(box);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        rotate.setOnAction(e-> {
            try {
                    BufferedImage result = rotate90(img);
                    img = result;
                    File output = new File(store   + "\\newImage.png");
                    ImageIO.write(result, "png", output);
                    out = new Image(String.valueOf(output.toURI().toURL()));
                    view = new ImageView(out);

                    view.setFitWidth(400);
                    view.setFitHeight(400);

                    VBox box = new VBox(view);
                    box.setAlignment(Pos.CENTER);
                    box.setPadding(new Insets(20));
                    pane.setBottom(box);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        grayScale.setOnAction(e->{
            try {
                BufferedImage result = grayScale(img);
                img = result;
                File output = new File(store + "\\newImage.png");
                ImageIO.write(result, "png", output);
                out = new Image(String.valueOf(output.toURI().toURL()));
                view = new ImageView(out);

                view.setFitWidth(400);
                view.setFitHeight(400);

                VBox box = new VBox(view);
                box.setAlignment(Pos.CENTER);
                box.setPadding(new Insets(20));
                pane.setBottom(box);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        rotateX.setOnAction(e->{
            try {
                BufferedImage result = rotateAboutX(img);
                img = result;
                File output = new File( store + "\\newImage.png");
                ImageIO.write(result, "png", output);
                out = new Image(String.valueOf(output.toURI().toURL()));
                view = new ImageView(out);

                view.setFitWidth(400);
                view.setFitHeight(400);

                VBox box = new VBox(view);
                box.setAlignment(Pos.CENTER);
                box.setPadding(new Insets(20));
                pane.setBottom(box);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        rotateY.setOnAction(e->{
            try {
                BufferedImage result = rotateAboutY(img);
                img = result;
                File output = new File(store  + "\\newImage.png");
                ImageIO.write(result, "png", output);
                out = new Image(String.valueOf(output.toURI().toURL()));
                view = new ImageView(out);

                view.setFitWidth(400);
                view.setFitHeight(400);

                VBox box = new VBox(view);
                box.setAlignment(Pos.CENTER);
                box.setPadding(new Insets(20));
                pane.setBottom(box);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        reflectX.setOnAction(e->{
            try {
                BufferedImage result = reflectX(img);
                img = result;
                File output = new File(store  + "\\newImage.png");
                ImageIO.write(result, "png", output);
                out = new Image(String.valueOf(output.toURI().toURL()));
                view = new ImageView(out);

                view.setFitWidth(400);
                view.setFitHeight(400);

                VBox box = new VBox(view);
                box.setAlignment(Pos.CENTER);
                box.setPadding(new Insets(20));
                pane.setBottom(box);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        reflectY.setOnAction(e->{
            try {
                BufferedImage result = reflectY(img);
                img = result;
                File output = new File(store   + "\\newImage.png");
                ImageIO.write(result, "png", output);
                out = new Image(String.valueOf(output.toURI().toURL()));
                view = new ImageView(out);

                view.setFitWidth(400);
                view.setFitHeight(400);

                VBox box = new VBox(view);
                box.setAlignment(Pos.CENTER);
                box.setPadding(new Insets(20));
                pane.setBottom(box);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Scene scene = new Scene(pane);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setTitle("AlgGUI"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setMaximized(true);
        primaryStage.show(); // Display the stage
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type,
                                             Integer img_width, Integer img_height)
    {
        BufferedImage resizedImage = new BufferedImage(img_width, img_height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, img_width, img_height, null);
        g.dispose();

        return resizedImage;
    }

    private static BufferedImage grayScale(BufferedImage img) {
        BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {

                result.setRGB(i, j, img.getRGB(i, j));
            }
        }
        return result;
    }

    private static BufferedImage rotate90(BufferedImage img) {
        BufferedImage result = new BufferedImage(img.getHeight(), img.getWidth(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {

                result.setRGB(j, result.getHeight() - i - 1, img.getRGB(i, j));
            }
        }
        return result;
    }

    private static BufferedImage reflectX(BufferedImage img) {
        BufferedImage result = img.getSubimage(0, 0, img.getWidth(), img.getHeight());
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {

                result.setRGB(i, img.getHeight() - j - 1, img.getRGB(i, j));
            }
        }
        return result;
    }

    private static BufferedImage reflectY(BufferedImage img) {
        BufferedImage result = img.getSubimage(0, 0, img.getWidth(), img.getHeight());
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {

                result.setRGB(img.getWidth() - i - 1, j, img.getRGB(i, j));
            }
        }
        return result;
    }

    private static BufferedImage rotateAboutX(BufferedImage img) {
        BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {

                result.setRGB(i, img.getHeight() - j - 1, img.getRGB(i, j));
            }
        }
        return result;
    }

    private static BufferedImage rotateAboutY(BufferedImage img) {
        BufferedImage result = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {

                result.setRGB(img.getWidth() - i - 1, j, img.getRGB(i, j));
            }
        }
        return result;
    }

    public static BufferedImage combineImages(BufferedImage a, BufferedImage b) {
        if (a.getWidth() != b.getWidth() || a.getHeight() != b.getHeight()) {
            return null;
        }

        BufferedImage result = new BufferedImage(a.getWidth(), a.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < a.getHeight(); i++) {
            for (int j = 0; j < a.getWidth(); j++) {
                byte[] aPixel = new BigInteger("" + a.getRGB(j,i)).toByteArray();
                byte[] bPixel = new BigInteger("" + b.getRGB(j,i)).toByteArray();

                byte[] aFixed = new byte[4];
                byte[] bFixed = new byte[4];

                for (int k = 0; k < 4; k++) {
                    if (aPixel.length > k) {
                        aFixed[k] = aPixel[k];
                    } else {
                        aFixed[k] = 0;
                    }

                    if (bPixel.length > k) {
                        bFixed[k] = bPixel[k];
                    } else{
                        bFixed[k] = 0;
                    }
                }

                byte[] newPixel = new byte[4];
                newPixel[0] = -1;
                for (int k = 1; k < 4; k++) {
                    newPixel[k] = (byte)(int)(((int)aFixed[k] + (int)bFixed[k]) / 2);
                }

                result.setRGB(j, i, new BigInteger(newPixel).intValue());
            }
        }

        return result;
    }
}
