package br.ufrn.eaj.tads.meuslivros;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by joffr on 13/09/2017.
 */

public class ListViewLivroHolder {
    final TextView titulo, autor, ano, nota;
    final ImageView img;

    public ListViewLivroHolder(View v) {
        titulo = (TextView) v.findViewById(R.id.tituloLista);
        autor = (TextView) v.findViewById(R.id.autorLista);
        ano = (TextView) v.findViewById(R.id.anoLista);
        nota = (TextView) v.findViewById(R.id.notaLista);
        img = (ImageView) v.findViewById(R.id.img);
    }
}
