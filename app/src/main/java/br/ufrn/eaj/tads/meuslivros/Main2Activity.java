package br.ufrn.eaj.tads.meuslivros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class Main2Activity extends AppCompatActivity {

    EditText ed1, ed2, ed3;
    RatingBar rt;
    Button b3, b4;
    BancoHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);

        rt = (RatingBar) findViewById(R.id.ratingBar);

        //salvar
        b3 = (Button) findViewById(R.id.button3);
        //cancelar
        b4 = (Button) findViewById(R.id.button4);

        db = new BancoHelper(this);
    }


    public void Salvar(View view) {
        Livro novoLivro = new Livro();
        novoLivro.setNome(ed1.getText().toString());
        novoLivro.setAutor(ed2.getText().toString());
        novoLivro.setAno(ed3.getText().toString());
        novoLivro.setNota(rt.getRating());
        db.save(novoLivro);
        setResult(RESULT_OK);
        finish();
    }

    public void Cancelar(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
