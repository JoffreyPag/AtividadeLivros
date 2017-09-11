package br.ufrn.eaj.tads.meuslivros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.util.List;

public class Main4Activity extends AppCompatActivity {

    ArrayAdapter<String> adaptador;
    List<Livro> livros;
    String[] titulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        BancoHelper bh = new BancoHelper(this);
        livros = bh.findAll();
        preencheTitulos();

        AutoCompleteTextView autocompletelivro = (AutoCompleteTextView)findViewById(R.id.campoAltoCompletar);
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titulos);
        autocompletelivro.setAdapter(adaptador);

        autocompletelivro.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    public void preencheTitulos(){
        int i=0;
        for(Livro li : livros){
            titulos[i] = li.getNome();
            i++;
        }

    }
}
