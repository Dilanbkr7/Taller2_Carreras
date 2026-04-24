package ec.edu.esfot.carreras;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class HiloPersonaje extends Thread {

    private ImageView personaje;
    private String nombre;
    private Label lblGanador;
    private AtomicBoolean hayGanador;
    private Random random = new Random();

    public HiloPersonaje(ImageView personaje, String nombre, Label lblGanador, AtomicBoolean hayGanador) {
        this.personaje = personaje;
        this.nombre = nombre;
        this.lblGanador = lblGanador;
        this.hayGanador = hayGanador;
    }

    @Override
    public void run() {
        try {
            while (personaje.getLayoutX() < 670 && !hayGanador.get()) {

                Thread.sleep(random.nextInt(120) + 40);

                Platform.runLater(() -> {
                    personaje.setLayoutX(personaje.getLayoutX() + random.nextInt(15) + 5);
                });
            }

            if (personaje.getLayoutX() >= 670 && hayGanador.compareAndSet(false, true)) {
                Platform.runLater(() -> {
                    lblGanador.setText("🏆 Ganador: " + nombre);
                });

                System.out.println("🏆 Ganador final: " + nombre);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}