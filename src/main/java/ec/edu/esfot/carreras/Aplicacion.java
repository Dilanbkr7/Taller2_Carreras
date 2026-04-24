package ec.edu.esfot.carreras;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

public class Aplicacion extends Application {

    private ImageView messi;
    private ImageView cristiano;
    private ImageView yamal;

    private Label lblGanador;
    private AtomicBoolean hayGanador = new AtomicBoolean(false);

    @Override
    public void start(Stage stage) {

        Pane root = new Pane();

        ImageView fondo = new ImageView(new Image(getClass().getResource("/campo.jpg").toExternalForm()));
        fondo.setFitWidth(900);
        fondo.setFitHeight(520);

        Label titulo = new Label("CARRERA DE PERSONAJES");
        titulo.setFont(new Font("Arial", 28));
        titulo.setTextFill(Color.WHITE);
        titulo.setLayoutX(260);
        titulo.setLayoutY(20);

        lblGanador = new Label("Presiona iniciar para comenzar");
        lblGanador.setFont(new Font("Arial", 22));
        lblGanador.setTextFill(Color.YELLOW);
        lblGanador.setLayoutX(285);
        lblGanador.setLayoutY(60);

        Label meta = new Label("META");
        meta.setFont(new Font("Arial", 24));
        meta.setTextFill(Color.WHITE);
        meta.setLayoutX(735);
        meta.setLayoutY(100);

        messi = crearPersonaje("/messi.png", 20, 120);
        cristiano = crearPersonaje("/cristiano.png", 20, 240);
        yamal = crearPersonaje("/yamal.png", 20, 360);

        Button btnIniciar = new Button("INICIAR CARRERA");
        btnIniciar.setLayoutX(350);
        btnIniciar.setLayoutY(465);
        btnIniciar.setPrefWidth(200);
        btnIniciar.setPrefHeight(35);
        btnIniciar.setStyle("-fx-background-color: #0b8f3a; -fx-text-fill: white; -fx-font-weight: bold;");

        btnIniciar.setOnAction(e -> iniciarCarrera());

        root.getChildren().addAll(
                fondo,
                titulo,
                lblGanador,
                meta,
                messi,
                cristiano,
                yamal,
                btnIniciar
        );

        Scene scene = new Scene(root, 900, 520);
        stage.setTitle("Carrera de Jugadores");
        stage.setScene(scene);
        stage.show();
    }

    private ImageView crearPersonaje(String ruta, int x, int y) {
        ImageView personaje = new ImageView(new Image(getClass().getResource(ruta).toExternalForm()));

        personaje.setFitWidth(90);
        personaje.setFitHeight(90);
        personaje.setPreserveRatio(true);

        personaje.setLayoutX(x);
        personaje.setLayoutY(y);

        return personaje;
    }

    private void iniciarCarrera() {
        messi.setLayoutX(20);
        cristiano.setLayoutX(20);
        yamal.setLayoutX(20);

        hayGanador.set(false);
        lblGanador.setText("Carrera en progreso...");

        new HiloPersonaje(messi, "Messi", lblGanador, hayGanador).start();
        new HiloPersonaje(cristiano, "Cristiano", lblGanador, hayGanador).start();
        new HiloPersonaje(yamal, "Yamal", lblGanador, hayGanador).start();
    }
}