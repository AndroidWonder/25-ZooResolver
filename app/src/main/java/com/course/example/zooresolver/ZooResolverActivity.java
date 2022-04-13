//Note in the Manifest that this Resolver has permissions for both reads and writes

package com.course.example.zooresolver;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.widget.TextView;

public class ZooResolverActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TextView text = (TextView)findViewById(R.id.Text);
        ContentValues values;
        
        long id = 0L;
        String name = null; 
        int quantity = 0;
        
        //the database is located through the uri
        Uri uri = Animal.CONTENT_URI;

        text.append("\n Contents initially: \n\n");
        //query animals to see what's in database already
        Cursor cur = getContentResolver().query(uri, null, null, null, null);
        if (cur != null) {
            while (cur.moveToNext()) {

                id = cur.getLong(cur.getColumnIndex(BaseColumns._ID));
                name = cur.getString(cur.getColumnIndex(Animal.NAME));
                quantity = cur.getInt(cur.getColumnIndex(Animal.QUANTITY));
                text.append(name + " " + quantity + " " + "\n");
            }
        }

        cur.close();

        //make some changes
        text.append("\n Contents after updates: \n\n");

        //delete animals table content
        getContentResolver().delete(uri, null, null);
        
      //insert values
        values = new ContentValues();
        values.put("name", "tiger");
        values.put("quantity",4);
        getContentResolver().insert(uri, values);
        
        values = new ContentValues();
        values.put("name", "zebra");
        values.put("quantity",23);
        getContentResolver().insert(uri, values);
        
        values = new ContentValues();
        values.put("name", "owls");
        values.put("quantity",3);
        getContentResolver().insert(uri, values);
        
        values = new ContentValues();
        values.put("name", "buffalo");
        values.put("quantity",13);
        getContentResolver().insert(uri, values);
        
        values = new ContentValues();
        values.put("name", "lion");
        values.put("quantity",37);
        getContentResolver().insert(uri, values);
        
        //update buffalo to gorilla
        values = new ContentValues();
    	values.put("name", "gorilla");
    	getContentResolver().update(uri, values, "name=?", new String[] {"buffalo"});
    	
    	//delete tiger
    	getContentResolver().delete(uri, "name=?", new String[] {"tiger"});

        //query animals
        cur = getContentResolver().query(uri, null, null, null, null);
        if (cur != null) {
            while (cur.moveToNext()) {
                name = cur.getString(cur.getColumnIndex(Animal.NAME));
                quantity = cur.getInt(cur.getColumnIndex(Animal.QUANTITY));
                text.append(name + " " + quantity + " " + "\n");
            }
        }

        cur.close();
        
    }
}