import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Pedir datos al usuario
        System.out.println("Ingrese el DNI del Paciente:");
        String dni = scanner.nextLine();
        System.out.println("Ingrese el nombre del Paciente:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el código de la obra social:");
        String codigo = scanner.nextLine();

        // Crear una nueva instancia de ObraSocial
        ObraSocial obraSocial = new ObraSocial(dni, nombre, codigo);
        
        // Guardar los datos en un archivo
        try (FileWriter writer = new FileWriter("obra_social.txt", true)) {
            writer.write(obraSocial.toString() + "\n");
            System.out.println("Obra social guardada correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar la obra social.");
        }
    }
}
