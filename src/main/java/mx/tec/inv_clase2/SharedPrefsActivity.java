package mx.tec.inv_clase2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SharedPrefsActivity extends AppCompatActivity {
    // shared prefs - archivo que guarda información en espacio local
    private static final String ARCHIVO_PREFS = "misPrefs";
    private static final String KEY_NOMBRE = "nombre";
    private static final String KEY_APELLIDO = "apellido";

    private SharedPreferences prefs;
    private EditText nombre, apellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefs);

        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);

        // cargar desde archivo a objeto
        prefs = getSharedPreferences(ARCHIVO_PREFS, MODE_PRIVATE);
    }


    public void imprimir(View v){

        String key_nombre = prefs.getString(KEY_NOMBRE, "no hubo nombre");
        String key_apellido = prefs.getString(KEY_APELLIDO, "no hubo apellido");
        Toast.makeText(this, "Valores: " + key_nombre + " " + key_apellido, Toast.LENGTH_SHORT).show();

    }

    public void guardar(View v){

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_NOMBRE, nombre.getText().toString());
        editor.putString(KEY_APELLIDO, apellido.getText().toString());
        editor.commit();

        Toast.makeText(this, "info guarda", Toast.LENGTH_SHORT).show();

    }

    public void borrarNombre(View v){

        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(KEY_NOMBRE);
        editor.commit();


        Toast.makeText(this, "NOMBRE BORRADO", Toast.LENGTH_SHORT).show();


    }

    public void borrarTodo(View v){

        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();

        Toast.makeText(this, "TODO BORRADO", Toast.LENGTH_SHORT).show();

    }


}