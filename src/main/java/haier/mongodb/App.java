package haier.mongodb;

import java.util.List;
import java.util.Set;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import static com.mongodb.client.model.Filters.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        MongoClient client = new MongoClient("10.10.10.130");
        
        @SuppressWarnings("deprecation")
		//DB db = client.getDB("words");
        MongoDatabase db=client.getDatabase("words");
        //Set<String> names = db.getCollectionNames();
        MongoIterable<String> names = db.listCollectionNames();
        
        for(String s : names)
        {
        	System.out.println(s);
        }
        
        //DBCollection collection=db.getCollection("word_stats");
        MongoCollection<Document> collection = db.getCollection("word_stats");
        System.out.println(collection.count());
        
        MongoCursor<Document> cursor = collection.find(gt("size", 12)).iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
        
        System.out.println(cursor.toString());
        
        BasicDBObject query=new BasicDBObject("size", 5);
        query.append("$gt", 5);
        //DBCursor cursor=collection.find(query).limit(10);
        
        client.close();
    }
}
