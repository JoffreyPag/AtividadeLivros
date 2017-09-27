package br.ufrn.eaj.tads.meuslivros;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by joffr on 23/09/2017.
 */

public class LivroRecyclerViewAdapter extends RecyclerView.Adapter {

    Context contexto;
    List<Livro> listaLivros;

    public LivroRecyclerViewAdapter(Context contexto, List<Livro> listaLivros) {
        this.contexto = contexto;
        this.listaLivros = listaLivros;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //cria uma view e infla com um layout feito pra isso
        View view = LayoutInflater.from(contexto).inflate(R.layout.livro_inflater_frame, parent, false);
        //chama o holder que extende RecycleView.Holder (ainda nesse arquivo) e seta a view
        LivroViewHolder holder = new LivroViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        /*
        o holder que esse metodo recebe é um RecycleView.ViewHolder
        e por isso precisa-se fazer um cast para LivroViewHolder pois o mesmo extende
        os metodos de RecycleView.ViewHolder
         */
        LivroViewHolder livroholder = (LivroViewHolder)holder;
        Livro livroescolhido = listaLivros.get(position);
        //seta as informações do objeto nas views
        livroholder.titulo.setText(livroescolhido.getNome());
        /*livroholder.autor.setText(livroescolhido.getAutor());
        livroholder.ano.setText(livroescolhido.getAno());
        livroholder.nota.setText(String.valueOf(livroescolhido.getNota()));*/

    }

    @Override
    public int getItemCount() {
        return listaLivros == null ? 0:listaLivros.size();
    }

    //O HOLDER!!!
    public class LivroViewHolder extends RecyclerView.ViewHolder{
        final TextView titulo;
        //final TextView, autor, ano, nota;
        final LinearLayout layoutNormal, layoutGone;
        final Button undoButton;
        public LivroViewHolder(View v) {
            super(v);
            titulo = v.findViewById(R.id.nomeLivro);

            //autor = (TextView) v.findViewById(R.id.autorLista);
            //ano = (TextView) v.findViewById(R.id.anoLista);
            //nota = (TextView) v.findViewById(R.id.notaLista);


            layoutNormal = v.findViewById(R.id.layout_normal);
            layoutGone = v.findViewById(R.id.layout_gone);
            undoButton = v.findViewById(R.id.undo_button);
        }
    }

    public void remover(int posicao){
        Livro livro = listaLivros.get(posicao);
        if (listaLivros.contains(livro)){
            listaLivros.remove(posicao);
            notifyItemRemoved(posicao);
        }
    }
}






