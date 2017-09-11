package br.ufrn.eaj.tads.meuslivros;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int RETORNO_CADASTRO = 11;

    Button bt1, bt2, busclivr;
    ConstraintLayout cons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = (Button) findViewById(R.id.button);
        bt2 = (Button) findViewById(R.id.button2);
        busclivr = (Button) findViewById(R.id.buscarLivrobotao);

        cons = (ConstraintLayout)findViewById(R.id.constraintao);
    }

    public void Cadastro(View view) {
        Intent i = new Intent(this, Main2Activity.class);
        startActivityForResult(i, RETORNO_CADASTRO);
    }

    public void Listar(View view) {
        Intent i = new Intent(this, Main3Activity.class);
        startActivity(i);

    }

    public void BuscarLivro(View view){
        Intent i = new Intent(this, Main4Activity.class);
        startActivity(i);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RETORNO_CADASTRO){
            if(resultCode == RESULT_OK){
                Snackbar snak = Snackbar.make(cons, "Livro Cadastrado", Snackbar.LENGTH_SHORT);
                snak.show();
            }else {
                Snackbar snak = Snackbar.make(cons, "Cadastro Cancelado", Snackbar.LENGTH_SHORT);
                snak.show();
            }
        }

    }
}
