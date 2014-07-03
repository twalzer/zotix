package com.zotix.riak;

import com.basho.riak.client.IRiakClient;
import com.basho.riak.client.IRiakObject;
import com.basho.riak.client.RiakFactory;
import com.basho.riak.client.bucket.Bucket;

import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try{
            // Wrap our generics so we can use .class
            class StringIntMap extends HashMap<String,Integer> {}

            // Creating Objects In Riak
            System.out.println("Creating Objects In Riak...");

            IRiakClient client = RiakFactory.pbcClient();

            // Note: Use this line instead of the former if using a local devrel cluster
            // IRiakClient client = RiakFactory.pbcClient("127.0.0.1", 10017);

            Bucket myBucket = client.fetchBucket("test").execute();

            int val1 = 1;
            myBucket.store("one", val1).execute();

            String val2 = "two";
            myBucket.store("two", val2).execute();

            StringIntMap val3 = new StringIntMap();
            val3.put("myValue", 3);
            myBucket.store("three", val3).execute();


            // Reading Objects From Riak
            System.out.println("Reading Objects From Riak...");

            Integer fetched1 = myBucket.fetch("one", Integer.class).execute();
            IRiakObject fetched2 = myBucket.fetch("two").execute();
            StringIntMap fetched3 = myBucket.fetch("three", StringIntMap.class).execute();

            assert(fetched1 == val1);
            assert(fetched2.getValueAsString().compareTo(val2) == 0);
            assert(fetched3.equals(val3));


            // Updating Objects In Riak
            System.out.println("Updating Objects In Riak");

            fetched3.put("myValue", 42);
            myBucket.store("three", fetched3).execute();


            // Deleting Objects From Riak
            System.out.println("Deleting Objects From Riak...");

            myBucket.delete("one").execute();
            myBucket.delete("two").execute();
            myBucket.delete("three").execute();


            // Working With Complex Objects
            System.out.println("Working With Complex Objects...");



            Book book = new Book();
            book.setISBN("1111979723");
            book.setTitle("Moby Dick");
            book.setAuthor("Herman Melville");
            book.setBody("Call me Ishmael. Some years ago...");
            book.setCopiesOwned(3);

            Bucket booksBucket = client.fetchBucket("books").execute();
            booksBucket.store(book.getISBN(), book).execute();

            IRiakObject riakObject = booksBucket.fetch(book.getISBN()).execute();
            System.out.println("Serialized Object:");
            System.out.println("\t" + riakObject.getValueAsString());

            booksBucket.delete(book.getISBN()).execute();

            client.shutdown();

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
