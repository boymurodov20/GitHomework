import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepo implements Repository<Student>{
    private static String path = "src/student.txt";

    private final List<Student> students;
    private static StudentRepo singilton;

    public StudentRepo(List<Student> students) {
        this.students = students;
    }


    public static StudentRepo getInstance(){
        if (singilton == null) {
            singilton= new StudentRepo(loadDate());
        }
        return singilton;
    }

    private static List<Student> loadDate() {

        try (
                InputStream inputStream = new FileInputStream(path);
                ObjectInputStream object = new ObjectInputStream(inputStream)
        )
        {
            List<Student> usersTemp = (List<Student>)  object.readObject();
            return usersTemp;
        }
        catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }

    }
    private void uploadDate() throws IOException {

        try (
                OutputStream os = new FileOutputStream(path);
                ObjectOutputStream outputStream = new ObjectOutputStream(os)
        ) {
            outputStream.writeObject(students);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Student student) throws IOException {
             students.add(student);
             uploadDate();

    }

    @Override
    public void update(Student student) throws IOException {
        if (student != null) {
            student.setName(Input.inputString(" enter new name"));
            student.setAge(Input.inputNumber(" enter new age"));
            uploadDate();
        }

    }

    @Override
    public List<Student> findAll() {

        return students;
    }

    @Override
    public void delete(Student student) throws IOException {
        students.remove(student);
        uploadDate();
    }
}
