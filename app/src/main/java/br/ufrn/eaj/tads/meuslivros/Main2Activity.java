package br.ufrn.eaj.tads.meuslivros;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private EditText ed1, ed2, ed3;
    private RatingBar rt;
    private Button b3, b4;
    private BancoHelper db;
    private int id;
    private String nomelivro, autorlivro, anolivro;
    private float notalivro;
    private boolean isUpdate;
    long identificao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i = getIntent();

        isUpdate = i.getBooleanExtra("update", false);
        nomelivro = i.getStringExtra("nomelivro");
        autorlivro = i.getStringExtra("autorlivro");
        anolivro = i.getStringExtra("anolivro");
        notalivro = i.getFloatExtra("notalivro", 0);
        identificao = i.getLongExtra("idlivro", 0);

        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);

        rt = (RatingBar) findViewById(R.id.ratingBar);

        //salvar
        b3 = (Button) findViewById(R.id.button3);
        //cancelar
        b4 = (Button) findViewById(R.id.button4);

        db = new BancoHelper(this);

        //um flag pra saber se essa activity veio do bota cadastro ou de uma atualização de um livro
        if (isUpdate) {
            ed1.setText(nomelivro);
            ed2.setText(autorlivro);
            ed3.setText(anolivro);
            rt.setRating(notalivro);
            Log.i("strelinha", "o que vem:"+notalivro+"\no que fica: "+rt.getRating());
            b3.setText("Atualizar");
            Toast.makeText(this, "Você entrou no modo atualizar!", Toast.LENGTH_SHORT).show();
        }

    }


    public void Salvar(View view) {
        Livro novoLivro = new Livro();
        novoLivro.setId(identificao);
        novoLivro.setNome(ed1.getText().toString());
        novoLivro.setAutor(ed2.getText().toString());
        novoLivro.setAno(ed3.getText().toString());
        novoLivro.setNota(rt.getRating());

        //novamento o flag pra caso seja uma atualização ou um cadastro
        if(isUpdate){
            db.atualizaLivro(novoLivro);
        }else{
            db.save(novoLivro);
        }
        setResult(RESULT_OK);
        finish();
    }

    public void Cancelar(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
