package net.ivanvega.clientecontentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        findViewById(R.id.btnQuery).setOnClickListener(
            v -> consultarCP()
        );
        findViewById(R.id.btnQueryUno).setOnClickListener(
                v -> {
                    consultarUnCP();
                }
        );
        findViewById(R.id.btnQueryPorID).setOnClickListener(
                v -> {
                    consultarPorIDCP();
                }
        );

        findViewById(R.id.btnInsert).setOnClickListener(
                v -> {
                    insertCP();
                }
        );

        findViewById(R.id.btnUpdate).setOnClickListener(
                view -> {
                    updateCP();
                }

                );

        findViewById(R.id.btnDelete).setOnClickListener(
                view -> {
                    delateCP();
                }
        );

    }

    private void consultarCP() {

        Cursor c =  getContentResolver().query(
                UsuarioProviderContract.CONTENT_URI,
                UsuarioProviderContract.COLUMNS,
                null,null,null
        );



        if(c!=null){
            while(c.moveToNext()){
                Log.d("providerusuario", "Usuario: "+ c.getInt(0)
                        + " - " + c.getString(1));
            }
        }else{
            Log.d("providerusuario", "Sin Usuario: ");
        }

    }

    private void consultarUnCP() {
        String id = "1";
        String nombre = "Juan";
        String apellido = "Peres";
        Cursor c =  getContentResolver().query(Uri.withAppendedPath
                (UsuarioProviderContract.CONTENT_URI, "1"),
                UsuarioProviderContract.COLUMNS,
                null,new String[]{id, nombre, apellido},null
        );

        if(c!=null){
            while(c.moveToNext()){
                Log.d("providerusuario", "Usuario: "+ c.getInt(0)
                        + " - " + c.getString(1));
            }
        }else{
            Log.d("providerusuario", "Sin Usuario: ");
        }

    }

    private void consultarPorIDCP() {
        String id = "1";
        String nombre = "Juan";
        String apellido = "Peres";
        Cursor c =  getContentResolver().query(Uri.withAppendedPath
                        (UsuarioProviderContract.CONTENT_URI, "1"),
                UsuarioProviderContract.COLUMNS,
                null,new String[]{id, nombre, apellido},null
        );

        if(c!=null){
            while(c.moveToNext()){
                Log.d("providerusuario", "Usuario: "+ c.getInt(0)
                        + " - " + c.getString(1));
            }
        }else{
            Log.d("providerusuario", "Sin Usuario: ");
        }

    }


    private void insertCP () {

        ContentValues cv = new ContentValues();

        cv.put(UsuarioProviderContract.FIRSTNAME_COLUMN, "Juan");
        cv.put(UsuarioProviderContract.LASTNAME_COLUMN, "Peres");


        Uri uriinsert =
                getContentResolver()
                        .insert(UsuarioProviderContract.CONTENT_URI,
                cv);


        Log.d("providerusuario", uriinsert.toString());
    }

    private void  updateCP(){
        ContentValues cv = new ContentValues();
        cv.put(UsuarioProviderContract.FIRSTNAME_COLUMN, "David");
        cv.put(UsuarioProviderContract.LASTNAME_COLUMN, "Vega");

        Uri uriUpdate = Uri.withAppendedPath(UsuarioProviderContract.CONTENT_URI,
                "4");

        int filasAfectas = getContentResolver().update(uriUpdate, cv, null, null );

        Log.d("Update", "Filas afectadas: "+ filasAfectas);
}

    private void delateCP(){
        Uri uriDelete = Uri.withAppendedPath(UsuarioProviderContract.CONTENT_URI, "19");
        int filasAfectadas = getContentResolver().delete(uriDelete, null, null);
        Log.d("providerusuario", "Filas borradas" + filasAfectadas);
        Log.d("nota", "ruta"+uriDelete);

        //content://net.ivanvega.basededatosconroomb.provider/usuario/18
        //content://net.ivanvega.basededatosconroomb.provider/usuario/19
    }
}