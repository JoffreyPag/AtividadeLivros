package br.ufrn.eaj.tads.meuslivros;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by joffr on 13/09/2017.
 */

public class LivroAdapter extends BaseAdapter{
    Context contexto;
    List<Livro> listaLivros;

    public LivroAdapter(Context contexto, List<Livro> listaLivros) {
        this.contexto = contexto;
        this.listaLivros = listaLivros;
    }

    @Override
    public int getCount() {
        return listaLivros != null ? listaLivros.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return listaLivros.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        View view;
        ListViewLivroHolder holder;

        if(convertView == null){
            view = LayoutInflater.from(contexto).inflate(R.layout.livro_item_inflater, viewGroup, false);
            holder = new ListViewLivroHolder(view);
            view.setTag(holder);
        }else{
            view = convertView;
            holder = (ListViewLivroHolder) view.getTag();

        }

        Livro livroescolhido = listaLivros.get(i);
        holder.titulo.setText(livroescolhido.getNome());
        holder.autor.setText(livroescolhido.getAutor());
        holder.ano.setText(livroescolhido.getAno());
        holder.nota.setText(toString().valueOf(livroescolhido.getNota())); //dessa vez sem aquela gambiarra que voce respeita
        return view;
    }
}
