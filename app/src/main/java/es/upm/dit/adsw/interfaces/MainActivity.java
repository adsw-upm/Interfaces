package es.upm.dit.adsw.interfaces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int MUESTRA_DETALLE = 0;
    public static final String DETALLE = "Texto";

    private ListView listView;
    private RadioGroup turnoGroup;
    private List<String> lista;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boton = (Button) findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsadoBoton(v);
            }
        });

        turnoGroup = (RadioGroup) findViewById(R.id.radioGroupTurno);

        lista = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.row, lista);

        listView = (ListView) findViewById(R.id.itemsListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("LISTA", "preremove");
                lista.remove(position);
                adapter.notifyDataSetChanged();
                Log.i("LISTA", "postremove");
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), DetalleActivity.class);
                intent.putExtra(DETALLE, lista.get(position));
                startActivityForResult(intent, MUESTRA_DETALLE);
                return true;
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            Log.i("RESULTADO", "Cerrado por el usuario");
            Toast.makeText(this, "Cerrado por el usuario.", Toast.LENGTH_SHORT).show();
            return;
        }
    }


    public void pulsadoBoton (View v){
        EditText editor = (EditText) findViewById(R.id.editText);
        TextView texto  = (TextView) findViewById(R.id.textView);
        Spinner selector = (Spinner) findViewById(R.id.spinner);
        String[] nombres = getResources().getStringArray(R.array.nombres);
        String nombre = nombres[selector.getSelectedItemPosition()];

        int turnoSeleccionado = turnoGroup.getCheckedRadioButtonId();
        switch (turnoSeleccionado){
            case R.id.radioMañana:
                texto.setText(editor.getText() + " matriculado en " + nombre);
                Toast.makeText(this, "Matriculado en turno de mañana", Toast.LENGTH_SHORT).show();
                lista.add(editor.getText() + " matriculado en " + nombre + ".\nTurno de Mañana.");
                adapter.notifyDataSetChanged();
                break;
            case R.id.radioTarde:
                texto.setText(editor.getText() + " matriculado en " + nombre);
                Toast.makeText(this, "Matriculado en turno de tarde", Toast.LENGTH_SHORT).show();
                lista.add(editor.getText() + " matriculado en " + nombre + ".\nTurno de Tarde.");
                adapter.notifyDataSetChanged();
                break;
            default:
                Toast.makeText(this, "Seleeccione turno de mañana o tarde", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item1:
                EditText editor = (EditText) findViewById(R.id.editText);
                editor.setText("");
                return true;
            case R.id.item2:
                TextView texto  = (TextView) findViewById(R.id.textView);
                texto.setText("Inserte texto y pulse boton");
                return true;
            default:
                 return super.onOptionsItemSelected(item);
        }
    }
}
