package projektinis;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PirkejoDuomenuNuskaitymas extends Application {



    boolean counter = false;
    boolean counter2 = false;
    String text1 = "Hello world1";
    String text2 = "Hello world2";

    @Override
    public void start(Stage primaryStage) {
        try {
            Text text = new Text();
            text.setText(text1);
            text.setFont(new Font(45));
            text.setStyle("-fx-font-weight: bold");
            text.setX(80);
            text.setY(200);

            Line line = new Line();
            line.setStartX(80);
            line.setEndX(330);
            line.setVisible(false);

            Button button1 = new Button("Button1");
            button1.setStyle(" -fx-font: 24 arial; -fx-font-weight: bold; -fx-background-color: grey");
            button1.setTextFill(Paint.valueOf("red"));
            button1.setLayoutX(20);
            button1.setLayoutY(20);
            button1.setMinWidth(10);
            button1.setMinHeight(5);
            button1.setMaxWidth(1000);
            button1.setMaxHeight(200);
            button1.setPrefWidth(200);
            button1.setPrefHeight(40);

            Button button2 = new Button("Button2");
            button2.setStyle(" -fx-font: 24 arial; -fx-font-weight: bold; -fx-background-color: grey");
            button2.setTextFill(Paint.valueOf("red"));
            button2.setLayoutX(20);
            button2.setLayoutY(80);
            button2.setMinWidth(10);
            button2.setMinHeight(5);
            button2.setMaxWidth(1000);
            button2.setMaxHeight(200);
            button2.setPrefWidth(200);
            button2.setPrefHeight(40);

            Button button3 = new Button("Button3");
            button3.setStyle(" -fx-font: 24 arial; -fx-font-weight: bold; -fx-background-color: grey");
            button3.setTextFill(Paint.valueOf("red"));
            button3.setLayoutX(20);
            button3.setLayoutY(250);
            button3.setMinWidth(10);
            button3.setMinHeight(5);
            button3.setMaxWidth(1000);
            button3.setMaxHeight(200);
            button3.setPrefWidth(200);
            button3.setPrefHeight(40);

            //      System.out.println("Iveskite savo ID");
//    int pirkejoID = Nuskaitymai.teigIntSkaiciausNuskaitymas();
//        System.out.println("Iveskite savo varda ir pavarde arba imones pavadinima:");
//    String pirkejoPavadinimas = ProjektinisMain.scanner.nextLine();
//        System.out.println("Iveskite savo e-mail adresa:");
//    String pirkejoEmail = ProjektinisMain.scanner.nextLine();
//        System.out.println("Iveskite pristatymo adresa:");
//    String pirkejoAdresas = ProjektinisMain.scanner.nextLine();


            TextField textFieldPirkejoID = new TextField();
            textFieldPirkejoID.setPromptText("Cia iveskite savo ID");
            textFieldPirkejoID.setLayoutX(20);
            textFieldPirkejoID.setLayoutY(300);
            textFieldPirkejoID.setPrefSize(500, 40);

            TextField textFieldPirkejoPavadinimas = new TextField();
            textFieldPirkejoPavadinimas.setPromptText("Cia iveskite savo varda ir pavarde arba imones pavadinima");
            textFieldPirkejoPavadinimas.setLayoutX(20);
            textFieldPirkejoPavadinimas.setLayoutY(380);
            textFieldPirkejoPavadinimas.setPrefSize(500, 40);

            TextField textFieldPirkejoEmail = new TextField();
            textFieldPirkejoEmail.setPromptText("Cia iveskite savo e-mail adresa");
            textFieldPirkejoEmail.setLayoutX(20);
            textFieldPirkejoEmail.setLayoutY(460);
            textFieldPirkejoEmail.setPrefSize(500, 40);

            TextField textFieldPirkejoAdresas = new TextField();
            textFieldPirkejoAdresas.setPromptText("Cia iveskite pristatymo adresa");
            textFieldPirkejoAdresas.setLayoutX(20);
            textFieldPirkejoAdresas.setLayoutY(540);
            textFieldPirkejoAdresas.setPrefSize(500, 40);

            Button button4 = new Button("Patvirtinti uzsakyma");
            button4.setStyle(" -fx-font: 24 arial; -fx-font-weight: bold; -fx-background-color: grey");
            button4.setTextFill(Paint.valueOf("red"));
            button4.setLayoutX(20);
            button4.setLayoutY(620);
            button4.setMinWidth(10);
            button4.setMinHeight(5);
            button4.setMaxWidth(1000);
            button4.setMaxHeight(200);
            button4.setPrefWidth(500);
            button4.setPrefHeight(40);




            button1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if (counter) {
                        text.setText(text1);
                    } else {
                        text.setText(text2);
                    }
                    counter = !counter;
                }
            });


            button2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if (counter2) {
                        line.setStartY(185);
                        line.setEndY(185);
                    } else {
                        line.setStartY(200);
                        line.setEndY(200);
                    }
                    line.setVisible(true);
                    counter2 = !counter2;
                }
            });

            button3.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if (!counter) {
                        text1 = textFieldPirkejoPavadinimas.getText();
                        text.setText(text1);
                        textFieldPirkejoPavadinimas.setPromptText(text1);
                    } else {
                        text2 = textFieldPirkejoPavadinimas.getText();
                        text.setText(text2);
                        textFieldPirkejoPavadinimas.setPromptText(text2);
                    }
                }
            });


            Group root = new Group(text, line, button1, button2, button3, textFieldPirkejoID, textFieldPirkejoPavadinimas, textFieldPirkejoEmail, textFieldPirkejoAdresas, button4);

            Scene scene = new Scene(root, 800, 800);

            primaryStage.setScene(scene);
            primaryStage.setTitle("My Application");
            primaryStage.show();
        } catch (
                Exception e)

        {
            e.printStackTrace();
        }
    }

    public static void launch() {
        PirkejoDuomenuNuskaitymas.launch();
    }
}
