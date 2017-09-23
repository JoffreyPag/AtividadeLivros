package br.ufrn.eaj.tads.meuslivros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class recyclerViewActivity extends AppCompatActivity {

    List<Livro> livroArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecView);

        //consulta do banco basicao
        carregaLivros();

        LivroRecyclerViewAdapter livroRecyclerViewAdapter = new LivroRecyclerViewAdapter(this, livroArrayList);
        recyclerView.setAdapter(livroRecyclerViewAdapter);

        //contexto, alinhamento, reverseLayout{decide se rola pra cima ou pra baixo}
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //outra forma soq  ue com gridLayout (OBS: ALTERAR O XML DE LINEAR PARA GRIDLAYOUT)
        //RecyclerView.LayoutManager layout = new RecyclerView.GridLayoutManager(this, int num_colunas)
        //OUTRO GRID LAYOUT [STAGGERED] DEFINE NUMERO DE COLUNAS E ALINHAMENTO
        //RecyclerView.LayoutManager layout = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layout);

        recyclerView.addOnItemTouchListener(new meuRecyclerViewClickListener(this, recyclerView, new meuRecyclerViewClickListener.OnItemClickListener(){

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(recyclerViewActivity.this, "clique simples", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(recyclerViewActivity.this, "clique longo", Toast.LENGTH_SHORT).show();

            }
        }));
    }

    private void carregaLivros() {
        BancoHelper bh = new BancoHelper(this);
        livroArrayList = bh.findAll();
    }
}
