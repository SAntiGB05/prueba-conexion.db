import java.sql.*;

public class ConexionMySQL {

    public Connection Conexion() {
        Connection myConn = null;
        try {
            myConn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/project",
                    "root",
                    ""
            );
            System.out.println("Genial, nos conectamos");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Algo salió mal :(");
        }
        return myConn;
    }

    // Método para insertar un empleado
    public void insertarEmpleado(Connection conexion, String first_name, String pa_surname, String ma_surname, String email, double salary)
            throws SQLException {
        String sql = "INSERT INTO employees (first_name, pa_surname, ma_surname, email, salary) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, first_name);
            pstmt.setString(2, pa_surname);
            pstmt.setString(3, ma_surname);
            pstmt.setString(4, email);
            pstmt.setDouble(5, salary);
            pstmt.executeUpdate();
            System.out.println("Empleado insertado correctamente!");
        }
    }

    // Método para consultar empleados
    public void consultarEmpleados(Connection conexion) throws SQLException {
        String sql = "SELECT * FROM employees";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("ID: %d, Nombre: %s, Apellido Paterno: %s, Apellido Materno: %s, Email: %s, Salario: %.2f%n",
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("pa_surname"),
                        rs.getString("ma_surname"),
                        rs.getString("email"),
                        rs.getDouble("salary"));
            }
        }
    }

    // Método para actualizar un empleado
    public void actualizarEmpleado(Connection conexion, int id, String first_name, String pa_surname, String ma_surname, String email, double salary)
            throws SQLException {
        String sql = "UPDATE employees SET first_name = ?, pa_surname = ?, ma_surname = ?, email = ?, salary = ? WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, first_name);
            pstmt.setString(2, pa_surname);
            pstmt.setString(3, ma_surname);
            pstmt.setString(4, email);
            pstmt.setDouble(5, salary);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
            System.out.println("Empleado actualizado correctamente!");
        }
    }

    // Método para eliminar un empleado
    public void eliminarEmpleado(Connection conexion, int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Empleado eliminado correctamente!");
        }
    }
}
