package dao;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import database.MongoDBConnection;
import model.Profesor;
import org.bson.conversions.Bson;


public class ProfesorDAO {
    MongoCollection coleccionProfesores;

    public ProfesorDAO() {
        coleccionProfesores = new MongoDBConnection().getProfesorCollection();
    }

//    public void insertarProfesor(Profesor profesor) {
//        Document document = new Document()
//                .append("name", profesor.getName())
//                .append("gender", profesor.getGender())
//                .append("age", profesor.getAge())
//                .append("email", profesor.getEmail())
//                .append("phone", profesor.getPhone())
//                .append("subjects", profesor.getSubjects())
//                .append("title", profesor.getTitle());
//        coleccionProfesores.insertOne(document);
//    }
    public void insertarProfesorPOJO(Profesor profesor) {
        MongoCollection collection = new MongoDBConnection().getProfesorCollection();
        collection.insertOne(profesor);
    }

//    public void mostrarProfesores() {
//        Document busqueda = new Document();
//        FindIterable iterable = coleccionProfesores.find(busqueda);
//        MongoCursor<Document> cursor = iterable.cursor();
//
//        while (cursor.hasNext()) {
//            Document document = cursor.next();
//            String nombre = document.getString("name");
//            String genero = document.getString("gender");
//            int edad = document.getInteger("age");
//            String email = document.getString("email");
//            String telefono = document.getString("phone");
//            String titulo = document.getString("title");
//            List<String> asignaturas = document.getList("subjects", String.class);
//
//            Profesor profesor = new Profesor();
//            profesor.mostrarDatos();
//        }
//    }
    public void mostrarProfesoresPOJO() {
        MongoCollection collection = new MongoDBConnection().getProfesorCollection();
        FindIterable<Profesor> iterable1 = collection.find(Profesor.class);
        MongoCursor<Profesor> cursor = iterable1.cursor();
        while (cursor.hasNext()) {
            Profesor profesor = cursor.next();
            profesor.mostrarDatos();
        }
    }

//    public void buscarProfesorPorRangoEdad(int edadMin, int edadMax) {
//        Bson filtro = Filters.and(Filters.gte("age", edadMin), Filters.lte("age", edadMax));
//        FindIterable<Document> iterable = coleccionProfesores.find(filtro);
//        MongoCursor<Document> cursor = iterable.iterator();
//
//        while (cursor.hasNext()) {
//            Document document = cursor.next();
//            String nombre = document.getString("name");
//            String genero = document.getString("gender");
//            int edad = document.getInteger("age");
//            String email = document.getString("email");
//            String telefono = document.getString("phone");
//            String titulo = document.getString("title");
//            List<String> asignaturas = document.getList("subjects", String.class);
//
//            Profesor profesor = new Profesor(nombre, genero, edad, email, telefono, titulo, asignaturas);
//            profesor.mostrarDatos();
//        }
//    }
public void buscarProfesorPorRangoEdadPOJO(int edadMin, int edadMax) {
    Bson filtrado = Filters.and(Filters.gte("age", edadMin), Filters.lte("age", edadMax));
    FindIterable<Profesor> iterable = coleccionProfesores.find(filtrado, Profesor.class);
    MongoCursor<Profesor> cursor = iterable.iterator();

    while (cursor.hasNext()) {
        Profesor profesor = cursor.next();
        profesor.mostrarDatos();
    }
}

    public void actualizarCalificacionProfesorPOJO(String email, double nuevaClasificacion) {
        Bson filtrado = Filters.eq("email", email);
        Bson actualizacion = Updates.set("rating", nuevaClasificacion);
        MongoCollection coleccionProfesores = new MongoDBConnection().getProfesorCollection();
        coleccionProfesores.updateOne(filtrado, actualizacion);
    }
}
