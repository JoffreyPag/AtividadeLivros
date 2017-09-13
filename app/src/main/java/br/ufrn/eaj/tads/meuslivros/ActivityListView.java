package br.ufrn.eaj.tads.meuslivros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActivityListView extends AppCompatActivity {

    ListView lista;
    List<Livro> listaLivros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        lista = (ListView)findViewById(R.id.minhalista);
        buscaBanco();

        lista.setAdapter(new LivroAdapter(this, listaLivros));
        /*lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Livro livro = listaLivros.get(i);
                Toast.makeText(ActivityListView.this, "Esse livro é baum!", Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    public void buscaBanco(){
        BancoHelper bh = new BancoHelper(this);
        listaLivros = bh.findAll();
    }
}
