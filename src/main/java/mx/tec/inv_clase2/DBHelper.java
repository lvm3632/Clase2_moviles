package mx.tec.inv_clase2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_FILE = "StudentDatabase.db";
    private static final String TABLE = "Students";
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_STUDENT_ID = "student";



    public DBHelper(Context context){
        super(context, DB_FILE, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // se llama al crear la base de datos
        String query = "CREATE TABLE " + TABLE + "(" +
                FIELD_ID + " INTEGER PRIMARY KEY, " +
                FIELD_NAME + " TEXT, " +
                FIELD_STUDENT_ID + " TEXT)";

        db.execSQL(query);
        //db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Se llama al actualizar la version de la bd

        // prepared statements!
        String query = "DROP TABLE IF EXISTS ?";
        // Va por orden en el array con los ?
        String[] params = {TABLE};

        db.execSQL(query, params);

        onCreate(db);

    }


    public void guardar(String nombre, String matricula){

        // obtener referencia a base de datos
        SQLiteDatabase db = getWritableDatabase();

        // content values - especie de diccionario donde guardamos los valores a utilizarse en un método
        // que va a afectar la db
        ContentValues valores = new ContentValues();

        valores.put(FIELD_NAME, nombre);
        valores.put(FIELD_STUDENT_ID, matricula);

        db.insert(TABLE, null, valores);

    }


    public int borrar(String nombre){

        SQLiteDatabase db = getWritableDatabase();

        String clause = FIELD_NAME + " = ?";
        String[] params = {nombre};

        //int rowsDeleted = db.delete(TABLE, clause, params);

        return db.delete(TABLE, clause, params);
    }

    public int buscar(String nombre)
    {
        SQLiteDatabase db = getReadableDatabase();

        String clause = FIELD_NAME + " = ?";
        String[] params = {nombre};

        Cursor c = db.query(TABLE, null, clause, params,null,null,null);

        int resultado = -1;

        if(c.moveToFirst()){
            resultado = c.getInt(0);
            String matricula  = c.getString(2);
        }


        // Recorrer todos los resultados
        // Regresa un false cuando llego al ultimo
        /*while(c.moveToNext()){

             Recorrer todos los resultados si es necesario
            do{

            }while(c.moveToNext());


        }*/

        // O tambien se puede hacer un do while
        return resultado;

        //return c.moveToFirst()==true?resultado=c.getInt(0):-1;
    }

    public static void main(String[] args){

    }




}
