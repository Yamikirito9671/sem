package com.napier.devops;

// Import the correct classes for the newer mongodb-driver-sync
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class App
{
    public static void main(String[] args)
    {
        // Connect to MongoDB on local system - we're using port 27000
        // Use MongoClients.create() for the new driver.
        // Connection string format is "mongodb://host:port"
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27000");

        // Get a database - will create when we use it
        MongoDatabase database = mongoClient.getDatabase("mydb");
        // Get a collection from the database
        MongoCollection<Document> collection = database.getCollection("test");
        // Create a document to store
        Document doc = new Document("name", "Kevin Sim")
                .append("class", "DevOps")
                .append("year", "2024")
                .append("result", new Document("CW", 95).append("EX", 85));
        // Add document to collection
        collection.insertOne(doc);

        // Check document in collection
        Document myDoc = collection.find().first();
        if (myDoc != null) { // Added a null check for safety
            System.out.println(myDoc.toJson());
        } else {
            System.out.println("No document found in collection.");
        }

        // Close the connection when finished
        mongoClient.close();
    }
}