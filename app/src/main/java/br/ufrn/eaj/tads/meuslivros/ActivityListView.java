package br.ufrn.eaj.tads.meuslivros;

import android.app.Activity;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
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
    ConstraintLayout cons;

    private static final int RETORNO_ALTERACAO = 33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        cons = (ConstraintLayout)findViewById(R.id.contraintListView);
        lista = (ListView)findViewById(R.id.minhalista);
        buscaBanco();

        lista.setAdapter(new LivroAdapter(this, listaLivros));
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Livro livro = listaLivros.get(i);
                //Toast.makeText(ActivityListView.this, "id: "+livro.getId(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ActivityListView.this, Main2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("update", true);
                bundle.putString("nomelivro",livro.getNome());
                bundle.putString("autorlivro",livro.getAutor());
                bundle.putString("anolivro",livro.getAno());
                bundle.putFloat("notalivro", livro.getNota());
                bundle.putLong("idlivro", livro.getId());
                intent.putExtras(bundle);
                startActivityForResult(intent, RETORNO_ALTERACAO);

            }
        });

    }

    public void buscaBanco(){
        BancoHelper bh = new BancoHelper(this);
        listaLivros = bh.findAll();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RETORNO_ALTERACAO){
            if (resultCode == RESULT_OK){
                Snackbar snak = Snackbar.make(cons, "Livro atualizado!", Snackbar.LENGTH_SHORT);
                snak.show();
                //recreate(); see fizer isso o snack nao aparece :'-[
            }else if (resultCode == RESULT_CANCELED){
                Snackbar snak = Snackbar.make(cons, "Atualização cancelada", Snackbar.LENGTH_SHORT);
                snak.show();
            }
        }
    }
}
