import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConexionMySQL conexion = new ConexionMySQL();

        try {
            Connection connection = conexion.Conexion();

            while (true) {
                System.out.println("Seleccione una opción:");
                System.out.println("1. Consultar empleados");
                System.out.println("2. Insertar empleado");
                System.out.println("3. Actualizar empleado");
                System.out.println("4. Eliminar empleado");
                System.out.println("5. Salir");

                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        conexion.consultarEmpleados(connection);
                        break;
                    case 2:
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Apellido paterno: ");
                        String pa_surname = scanner.nextLine();
                        System.out.print("Apellido materno: ");
                        String ma_surname = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Salario: ");
                        double salario = scanner.nextDouble();
                        conexion.insertarEmpleado(connection, nombre, pa_surname, ma_surname, email, salario);
                        break;
                    case 3:
                        System.out.print("ID del empleado a actualizar: ");
                        int idActualizar = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Nuevo apellido paterno: ");
                        String nuevoPaSurname = scanner.nextLine();
                        System.out.print("Nuevo apellido materno: ");
                        String nuevoMaSurname = scanner.nextLine();
                        System.out.print("Nuevo email: ");
                        String nuevoEmail = scanner.nextLine();
                        System.out.print("Nuevo salario: ");
                        double nuevoSalario = scanner.nextDouble();
                        conexion.actualizarEmpleado(connection, idActualizar, nuevoNombre, nuevoPaSurname, nuevoMaSurname, nuevoEmail, nuevoSalario);
                        break;
                    case 4:
                        System.out.print("ID del empleado a eliminar: ");
                        int idEliminar = scanner.nextInt();
                        conexion.eliminarEmpleado(connection, idEliminar);
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        return;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
