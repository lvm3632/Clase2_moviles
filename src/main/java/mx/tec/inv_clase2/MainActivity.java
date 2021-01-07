package mx.tec.inv_clase2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText id, nombre, matricula;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.dbId);
        nombre = findViewById(R.id.dbNombre);
        matricula = findViewById(R.id.dbMatricula);

        db = new DBHelper(this);
    }


    public void agregar(View v){

        db.guardar(nombre.getText().toString(), matricula.getText().toString());
        Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();
    }

    public void buscar(View v){
            int idQuery = db.buscar(nombre.getText().toString());
            id.setText("" + idQuery);

    }

    public void borrar(View v){

        int afectados = db.borrar(nombre.getText().toString());
        Toast.makeText(this, afectados + " REGISTROS BORRADOS", Toast.LENGTH_SHORT).show();
    }


}