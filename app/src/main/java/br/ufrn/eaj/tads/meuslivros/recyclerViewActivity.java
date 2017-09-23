package br.ufrn.eaj.tads.meuslivros;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class recyclerViewActivity extends AppCompatActivity {

    List<Livro> livroArrayList = new ArrayList<>();
    private static final int RETORNO_ALTERACAO = 50;
    boolean primeira = true;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = (RecyclerView) findViewById(R.id.RecView);

        //consulta do banco basicao
        carregaLivros();

        confDoRecycle();

        recyclerView.addOnItemTouchListener(new meuRecyclerViewClickListener(this, recyclerView, new meuRecyclerViewClickListener.OnItemClickListener(){

            @Override
            public void onItemClick(View view, int position) {
                //Toast.makeText(recyclerViewActivity.this, "clique simples no card: "+position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemLongClick(View view, int position) {
                //Toast.makeText(recyclerViewActivity.this, "clique longo", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(recyclerViewActivity.this, Main2Activity.class);
                Livro livro = livroArrayList.get(position);

                Intent intent = new Intent(recyclerViewActivity.this, Main2Activity.class);
                Bundle bundle = new Bundle();
                //jogo todas as info do objeto livro pq nao tem como jogar o objeto em simno bundle, uma pena...
                bundle.putBoolean("update", true);
                bundle.putString("nomelivro",livro.getNome());
                bundle.putString("autorlivro",livro.getAutor());
                bundle.putString("anolivro",livro.getAno());
                bundle.putFloat("notalivro", livro.getNota());
                bundle.putLong("idlivro", livro.getId());
                intent.putExtras(bundle);
                startActivityForResult(intent, RETORNO_ALTERACAO);
            }
        }));
    }

    private void confDoRecycle() {
        LivroRecyclerViewAdapter livroRecyclerViewAdapter = new LivroRecyclerViewAdapter(this, livroArrayList);
        recyclerView.setAdapter(livroRecyclerViewAdapter);

        //contexto, alinhamento, reverseLayout{decide se rola pra cima ou pra baixo}
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //outra forma soq  ue com gridLayout (OBS: ALTERAR O XML DE LINEAR PARA GRIDLAYOUT)
        //RecyclerView.LayoutManager layout = new RecyclerView.GridLayoutManager(this, int num_colunas)
        //OUTRO GRID LAYOUT [STAGGERED] DEFINE NUMERO DE COLUNAS E ALINHAMENTO
        //RecyclerView.LayoutManager layout = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layout);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RETORNO_ALTERACAO){
            if(resultCode == RESULT_OK){
                Snackbar snak = Snackbar.make((LinearLayout)findViewById(R.id.layoutPaidoRecycle), "Livro atualizado!", Snackbar.LENGTH_SHORT);
                snak.show();
            }else if (resultCode == RESULT_CANCELED){
                Snackbar snak = Snackbar.make((LinearLayout)findViewById(R.id.layoutPaidoRecycle), "Atualização Cancelada!", Snackbar.LENGTH_SHORT);
                snak.show();
            }
        }

    }

    private void carregaLivros() {
        BancoHelper bh = new BancoHelper(this);
        livroArrayList = bh.findAll();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!primeira) {
            carregaLivros();
            confDoRecycle();
            Log.i("onReume", "atualizou!");
        } else{
            primeira = false;
            Log.i("onResume", "não atualizou");
        }
    }
}
