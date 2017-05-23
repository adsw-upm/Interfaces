package es.upm.dit.adsw.interfaces;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DetalleActivity extends AppCompatActivity {

    private static final String TAG = DetalleActivity.class.getSimpleName();

    private TextView textoDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            Toast.makeText(this, "No hay texto para mostrar",
                    Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Error sin extras ");
            finish();
        }

        textoDetalle = (TextView) findViewById(R.id.textoDetalle);
        String matricula = extras.getString(MainActivity.DETALLE);
        textoDetalle.setText(matricula);
    }

    public void pulsadoCerrar(View v){
        setResult(RESULT_OK);
        finish();
    }
}
