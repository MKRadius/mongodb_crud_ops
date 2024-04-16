package com.example;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.scene.control.Alert.AlertType;
import org.bson.Document;
import org.bson.types.ObjectId;

public class Controller {
    private View view;
    private MongoCollection<Document> collection;

    public Controller(View view) {
        this.view = view;
        try {
            ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
            MongoClient mongoClient = MongoClients.create(connectionString);
            MongoDatabase database = mongoClient.getDatabase("crud_ops");
            collection = database.getCollection("data");
        } 
        catch (Exception e) {
            view.display(AlertType.ERROR, "Failed to connect to database");
            System.exit(0);
        }
    }

    public void add(String name, String age, String city) {
        if (name.isEmpty() || age.isEmpty() || city.isEmpty()) {
            view.display(AlertType.ERROR, "Please fill all fields");
            return;
        }

        Document document = new Document()
                .append("name", name)
                .append("age", age)
                .append("city", city);

        try {
            collection.insertOne(document);
            view.display(AlertType.INFORMATION, "Document added successfully");
        } 
        catch (Exception e) {
            view.display(AlertType.ERROR, "Failed to add document");
        }
    }

    public void read(String id) {
        try {
            Document document = collection.find(new Document("_id", new ObjectId(id))).first();
            
            if (document != null) {
                view.display(AlertType.INFORMATION, document.toJson());
            } else {
                view.display(AlertType.ERROR, "Document not found");
            }
        } 
        catch (Exception e) {
            view.display(AlertType.ERROR, "Error in reading document");
        }
    }

    public void update(String id, String name, String age, String city) {
        if (name.isEmpty() || age.isEmpty() || city.isEmpty()) {
            view.display(AlertType.ERROR, "Please fill all fields");
            return;
        }
        else if (id.isEmpty()) {
            view.display(AlertType.ERROR, "Please enter ID");
            return;
        }
        else if (collection.find(new Document("_id", new ObjectId(id))).first() == null) {
            view.display(AlertType.ERROR, "Document not found");
            return;
        }

        ObjectId objectId = new ObjectId(id);

        Document document = new Document()
                .append("_id", objectId)
                .append("name", name)
                .append("age", age)
                .append("city", city);

        try {
            collection.replaceOne(new Document("_id", objectId), document);
            view.display(AlertType.INFORMATION, "Document updated successfully");
        } 
        catch (Exception e) {
            view.display(AlertType.ERROR, "Error in updating document");
        }
    }

    public void delete(String id) {
        Document document = collection.find(new Document("_id", new ObjectId(id))).first();
        
        if (document == null) {
            view.display(AlertType.ERROR, "Document not found");
            return;
        }

        try {
            collection.deleteOne(document);
            view.display(AlertType.INFORMATION, "Document deleted successfully");
        } 
        catch (Exception e) {
            view.display(AlertType.ERROR, "Error in deleting document");
        }
    }
}
