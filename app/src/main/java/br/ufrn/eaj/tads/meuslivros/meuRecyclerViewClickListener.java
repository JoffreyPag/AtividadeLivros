package br.ufrn.eaj.tads.meuslivros;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by joffr on 23/09/2017.
 */

//classe que implementa uma interace para tratar o click do recycler view
public class meuRecyclerViewClickListener implements RecyclerView.OnItemTouchListener {

    GestureDetector myGestureDetector;
    OnItemClickListener myListener;

    //interface para os metodos que queremos disponibilizar ao RecyclerView
    //ela é chamada no contrutor dessa classe
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    /*
    ordem de chamada: 1- onItercept... , 2- onTouch... , 3- onClick da view
     */
    //intercepta um evento de toque na tela
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        myGestureDetector.onTouchEvent(e);
        return false;
    }

    //chamado quando um toque na view é detectado
    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    //chamado quando uma view filha nao quer os eventos sejam interceptados
    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    //contrutor de RecyclerItemClickListener
    public meuRecyclerViewClickListener(Context context, final RecyclerView view, OnItemClickListener listener) {
        myListener = listener;
        /*GestureDetector.SimpleOnGestureListener() usada quando nao queremos implementar todos os gestos
        caso seja preciso usar outros gestos usar OnGestureListener*/
        myGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                super.onSingleTapUp(e);
                //essa view recebe a view filha que sera encontrada atraves das posicoes x e y
                /*
                o metodo findChieldViewUnder [implementada em todas as views] pode ser usada
                em conjunto com getX e getY do MotionEvent para detectar qual View o usuario clicou
                */
                View childView = view.findChildViewUnder(e.getX(),e.getY());
                if (childView != null && myListener != null) {
                    myListener.onItemClick(childView, view.getChildAdapterPosition(childView));
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
                View childView = view.findChildViewUnder(e.getX(), e.getY());
                if (childView != null && myListener != null){
                    myListener.onItemLongClick(childView, view.getChildAdapterPosition(childView));
                }
            }
        });
    }
}
