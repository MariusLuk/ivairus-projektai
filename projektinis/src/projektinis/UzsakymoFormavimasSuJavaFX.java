package projektinis;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UzsakymoFormavimasSuJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Text text = new Text();
            text.setText("Įveskite savo duomenis");
            text.setFont(new Font(45));
            text.setStyle("-fx-font-weight: bold");
            text.setX(150);
            text.setY(150);

            Label labelPirkejoID = new Label("Pirkėjo ID:");
            labelPirkejoID.setLayoutX(150);
            labelPirkejoID.setLayoutY(230);
            labelPirkejoID.setStyle(" -fx-font-size: 20");

            TextField textFieldPirkejoID = new TextField();
            textFieldPirkejoID.setPromptText("Čia įveskite savo ID");
            textFieldPirkejoID.setLayoutX(150);
            textFieldPirkejoID.setLayoutY(260);
            textFieldPirkejoID.setPrefSize(490, 40);
            textFieldPirkejoID.setStyle(" -fx-font-size: 14");

            Label labelPirkejoPavadinimas = new Label("Pirkėjo vardas ir pavardė arba įmonės pavadinimas:");
            labelPirkejoPavadinimas.setLayoutX(150);
            labelPirkejoPavadinimas.setLayoutY(310);
            labelPirkejoPavadinimas.setStyle(" -fx-font-size: 20");

            TextField textFieldPirkejoPavadinimas = new TextField();
            textFieldPirkejoPavadinimas.setPromptText("Čia įveskite savo vardą ir pavardę arba įmonės pavadinimą");
            textFieldPirkejoPavadinimas.setLayoutX(150);
            textFieldPirkejoPavadinimas.setLayoutY(340);
            textFieldPirkejoPavadinimas.setPrefSize(490, 40);
            textFieldPirkejoPavadinimas.setStyle(" -fx-font-size: 14");

            Label labelPirkejoEmail = new Label("Pirkėjo e-mail:");
            labelPirkejoEmail.setLayoutX(150);
            labelPirkejoEmail.setLayoutY(390);
            labelPirkejoEmail.setStyle(" -fx-font-size: 20");

            TextField textFieldPirkejoEmail = new TextField();
            textFieldPirkejoEmail.setPromptText("Čia įveskite savo e-mail adresą");
            textFieldPirkejoEmail.setLayoutX(150);
            textFieldPirkejoEmail.setLayoutY(420);
            textFieldPirkejoEmail.setPrefSize(490, 40);
            textFieldPirkejoEmail.setStyle(" -fx-font-size: 14");

            Label labelPirkejoAdresas = new Label("Pirkėjo adresas:");
            labelPirkejoAdresas.setLayoutX(150);
            labelPirkejoAdresas.setLayoutY(470);
            labelPirkejoAdresas.setStyle(" -fx-font-size: 20");

            TextField textFieldPirkejoAdresas = new TextField();
            textFieldPirkejoAdresas.setPromptText("Čia įveskite užsakymo pristatymo adresą");
            textFieldPirkejoAdresas.setLayoutX(150);
            textFieldPirkejoAdresas.setLayoutY(500);
            textFieldPirkejoAdresas.setPrefSize(490, 40);
            textFieldPirkejoAdresas.setStyle(" -fx-font-size: 14");

            Button button = new Button("Spausti ir patvirtinti užsakymą!");
            button.setStyle(" -fx-font: 20 arial; -fx-font-weight: bold; -fx-background-color: lightgrey");
            button.setLayoutX(220);
            button.setLayoutY(620);
            button.setPrefWidth(350);
            button.setPrefHeight(40);

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    int pirkejoID = Integer.parseInt(textFieldPirkejoID.getText());
                    String pirkejoPavadinimas = textFieldPirkejoPavadinimas.getText();
                    String pirkejoEmail = textFieldPirkejoEmail.getText();
                    String pirkejoAdresas = textFieldPirkejoAdresas.getText();

                    Sandelis.krepselioPrekiuIsemimasIsSandelio();
                    UzsakymuAtaskaita.ikelkUzsakymaToAtaskaita(pirkejoID, pirkejoPavadinimas, pirkejoEmail, pirkejoAdresas, Krepselis.krepselioKaina);
                    PiniguLikutis.keiskPiniguLikuti(Krepselis.krepselioKaina);
                    Krepselis.istustinkKrepseli();

                    text.setText("Užsakymas patvirtintas!");
                    labelPirkejoID.setVisible(false);
                    textFieldPirkejoID.setVisible(false);
                    labelPirkejoPavadinimas.setText(pirkejoPavadinimas);
                    textFieldPirkejoPavadinimas.setVisible(false);
                    labelPirkejoEmail.setText(pirkejoEmail);
                    textFieldPirkejoEmail.setVisible(false);
                    labelPirkejoAdresas.setText(pirkejoAdresas);
                    textFieldPirkejoAdresas.setVisible(false);
                    button.setVisible(false);
                }
            });

            Group root = new Group(text, labelPirkejoID, textFieldPirkejoID,
                    labelPirkejoPavadinimas, textFieldPirkejoPavadinimas,
                    labelPirkejoEmail, textFieldPirkejoEmail,
                    labelPirkejoAdresas, textFieldPirkejoAdresas, button);

            Scene scene = new Scene(root, 800, 800);

            primaryStage.setScene(scene);
            primaryStage.setTitle("UŽSAKYMO PATVIRTINIMAS");
            primaryStage.show();

        } catch (
                Exception e)

        {
            e.printStackTrace();
        }
    }

    public static void launch() {
        UzsakymoFormavimasSuJavaFX.launch();
    }
}
