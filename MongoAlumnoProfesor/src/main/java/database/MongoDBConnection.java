package database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Alumno;
import model.Profesor;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class MongoDBConnection {
    private String connectionString = "mongodb+srv://%s:%s@cluster0.jq9dq.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
    private MongoClient mongoClient;
    private CodecProvider codecProvider;
    CodecRegistry codecRegistry;

    public MongoDBConnection() {
        try {
            codecProvider = PojoCodecProvider.builder().automatic(true).build();
            codecRegistry = CodecRegistries.fromRegistries(
                    MongoClients.create().getCodecRegistry(),
                    CodecRegistries.fromProviders(codecProvider)
            );
            mongoClient = MongoClients.create(String.format(connectionString, DBScheme.USER, DBScheme.PASS));

        } catch (Exception e){
            System.out.println("Error al establecer la conexión con MongoDB: " + e.getMessage());
        }
    }

    public MongoCollection<Alumno> getAlumnoCollection() {
        MongoDatabase database = mongoClient.getDatabase("centro_estudios").withCodecRegistry(codecRegistry);
        return database.getCollection("alumnos", Alumno.class);
    }

    public MongoCollection<Profesor> getProfesorCollection() {
        MongoDatabase database = mongoClient.getDatabase("centro_estudios").withCodecRegistry(codecRegistry);
        return database.getCollection("profesores", Profesor.class);
    }
}
