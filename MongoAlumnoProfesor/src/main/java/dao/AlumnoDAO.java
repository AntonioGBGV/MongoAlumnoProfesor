package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import database.MongoDBConnection;
import model.Alumno;
import org.bson.conversions.Bson;

public class AlumnoDAO {

    MongoCollection coleccionAlumnos;

    public AlumnoDAO() {coleccionAlumnos = new MongoDBConnection().getAlumnoCollection();}

//    public void insertarAlumno(Alumno alumno) {
//        Document document = new Document().append("name", alumno.getName())
//                .append("gender", alumno.getGender())
//                .append("age", alumno.getAge())
//                .append("email", alumno.getEmail())
//                .append("phone", alumno.getPhone())
//                .append("calification", alumno.getCalificaation())
//                .append("higher_grade", alumno.getHigherGrade());
//        coleccionAlumnos.insertOne(document);
//    }
    public void insertarAlumnoPOJO(Alumno alumno) {
        MongoCollection collection = new MongoDBConnection().getAlumnoCollection();
        collection.insertOne(alumno);
    }

//    public void mostrarAlumnos() {
//        FindIterable<Document> iterable = coleccionAlumnos.find();
//        MongoCursor<Document> cursor = iterable.iterator();
//
//        while (cursor.hasNext()) {
//            Document document = cursor.next();
//            String nombre = document.getString("name");
//            int edad = document.getInteger("age");
//            String genero = document.getString("gender");
//            String email = document.getString("email");
//            String telefono = document.getString("phone");
//            double calificacion = document.getDouble("calification");
//            String ciclo = document.getString("higher_grade");
//            double clasificacion = document.getDouble("rating");
//            boolean fcts = document.getBoolean("fcts");
//
//            Alumno alumno = new Alumno(nombre, genero, edad, email, telefono, calificacion, ciclo, clasificacion, fcts);
//            alumno.mostrarDatos();
//        }
//    }
    public void mostrarAlumnosPOJO() {
        MongoCollection collection = new MongoDBConnection().getAlumnoCollection();
        FindIterable<Alumno> iterable1 = collection.find(Alumno.class);
        MongoCursor<Alumno> cursor = iterable1.cursor();
        while (cursor.hasNext()) {
            Alumno alumno = cursor.next();
            alumno.mostrarDatos();
        }
    }

    //    public void buscarAlumnoPorEmail(String email) {
//        Document busqueda = new Document().append("email", email);
//        FindIterable<Document> iterable = coleccionAlumnos.find(busqueda);
//        MongoCursor<Document> cursor = iterable.cursor();
//
//        while (cursor.hasNext()) {
//            Document document = cursor.next();
//            String nombre = document.getString("name");
//            int edad = document.getInteger("age");
//            String genero = document.getString("gender");
//            String telefono = document.getString("phone");
//            double calificacion = document.getDouble("calification");
//            String ciclo = document.getString("higher_grade");
//            double clasificacion = document.getDouble("rating");
//            boolean fcts = document.getBoolean("fcts");
//
//            Alumno alumno = new Alumno(nombre, genero, edad, email, telefono, calificacion, ciclo, clasificacion, fcts);
//            alumno.mostrarDatos();
//        }
//    }
    public void buscarAlumnoPorEmailPOJO(String email) {
        Bson filtrado = Filters.eq("email", email);
        FindIterable<Alumno> iterable = coleccionAlumnos.find(filtrado, Alumno.class);
        MongoCursor<Alumno> cursor = iterable.iterator();

        while (cursor.hasNext()) {
            Alumno alumno = cursor.next();
            alumno.mostrarDatos();
        }
    }

    public void eliminarAlumnosPorCalificacionPojo(int calificacionMinima) {
        Bson filtrado =
                Filters.gte("calificaation", calificacionMinima);
        MongoCollection coleccionAlumnos = new MongoDBConnection().getAlumnoCollection();
        coleccionAlumnos.deleteMany(filtrado);
    }
}

