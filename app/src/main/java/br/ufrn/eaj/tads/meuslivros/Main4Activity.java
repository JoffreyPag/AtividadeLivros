package br.ufrn.eaj.tads.meuslivros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Main4Activity extends AppCompatActivity {

    TextView nome, autor, ano, nota;
    ArrayAdapter<String> adaptador;
    List<Livro> livros;
    String[] titulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        nome = (TextView)findViewById(R.id.textViewNome);
        autor = (TextView)findViewById(R.id.textViewAutor);
        ano = (TextView)findViewById(R.id.textViewAno);
        nota = (TextView)findViewById(R.id.textViewNota);

        BancoHelper bh = new BancoHelper(this);
        livros = bh.findAll();
        preencheTitulos();

        AutoCompleteTextView autocompletelivro = (AutoCompleteTextView)findViewById(R.id.campoAltoCompletar);
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titulos);
        autocompletelivro.setAdapter(adaptador);

        autocompletelivro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                for(Livro li: livros){
                    if(adaptador.getItem(i).toString().equals(li.getNome())){
                        nome.setText(li.getNome());
                        autor.setText(li.getAutor());
                        ano.setText(li.getAno());
                        nota.setText(""+li.getNota());
                    }
                }
            }
        });
    }

    public void preencheTitulos(){

        titulos = new String[livros.size()];

        int i=0;
        for(Livro li : livros){
            titulos[i] = li.getNome();
            i++;
        }

    }
}
