import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        innerJoin();
        //addStudent(8,"marah",20);
        //getAllStudents();
        //updateStudent("age","35",7);
        //getAllStudents();
        //updateStudent("name","marah alheeh",8);
        //getAllStudents();
        //deleteStudent(5);
        //getAllStudents();
    }

    public static void getAllStudents(){
        String sql = "select * from students";
        try (Connection connection = RDBMSConnectivity.connect();
        PreparedStatement psment = connection.prepareStatement(sql);
        ResultSet rs = psment.executeQuery();){
            while (rs.next()){
                int id = rs.getInt("student_id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println("student id: "+id+" student name: "+name+" student age: "+age);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addStudent(int student_id,String name,int age) throws SQLException {
        String sql = "INSERT INTO students(student_id,name,age) values (?,?,?)";

        try(Connection connection = RDBMSConnectivity.connect();
            PreparedStatement psment = connection.prepareStatement(sql)){
            psment.setInt(1,student_id);
            psment.setString(2,name);
            psment.setInt(3,age);
            psment.executeUpdate();
            System.out.println("the student "+name+" is added successfully!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteStudent(int student_id){
        String sql = "DELETE FROM students WHERE student_id = ?";
        try(Connection connection = RDBMSConnectivity.connect();
            PreparedStatement psment = connection.prepareStatement(sql)){
            psment.setInt(1,student_id);
            psment.executeUpdate();
            System.out.println("the student with id "+student_id+" is removed successfully!");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStudent(String whatToUpdate,String updatedValue,int student_id){
        String sql = "Update students Set "+whatToUpdate+" = ? WHERE student_id = ?";
        try(Connection conn = RDBMSConnectivity.connect();
        PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            if (!whatToUpdate.equalsIgnoreCase("name") || (!whatToUpdate.equalsIgnoreCase("age"))){
                System.out.println("Wrong input for the what to update parameter");
            }
            else if (whatToUpdate.equalsIgnoreCase("name")){
                preparedStatement.setString(1,updatedValue);
                preparedStatement.setInt(2,student_id);
                preparedStatement.executeUpdate();
                System.out.println("the student with id "+student_id+" is updated successfully!");
            }
            else {
                preparedStatement.setInt(1, Integer.parseInt(updatedValue));
                preparedStatement.setInt(2,student_id);
                preparedStatement.executeUpdate();
                System.out.println("the student with id "+student_id+" is updated successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void innerJoin() throws SQLException {
        String sql = "SELECT course_name, COUNT(*) AS student_count\n" +
                "FROM course\n" +
                "left JOIN enrollments ON course.course_id = enrollments.course_id\n" +
                "GROUP BY course_name;";
        try(Connection connection = RDBMSConnectivity.connect();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()){
            while (rs.next()){
                String course_name = rs.getString("course_name");
                int student_count = rs.getInt("student_count");
                System.out.println("course name: "+course_name+" student count: "+student_count);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}