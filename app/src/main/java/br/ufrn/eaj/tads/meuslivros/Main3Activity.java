package br.ufrn.eaj.tads.meuslivros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Main3Activity extends AppCompatActivity {

    Button proximo, anterior;
    TextView titulo, autor, ano, nota;
    List<Livro> Livros;
    int indice = 0, tamLista = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        anterior = (Button) findViewById(R.id.button5);
        proximo = (Button) findViewById(R.id.button6);

        titulo = (TextView) findViewById(R.id.textView9);
        autor = (TextView) findViewById(R.id.textView10);
        ano = (TextView) findViewById(R.id.textView11);
        nota = (TextView) findViewById(R.id.textView12);

        BancoHelper db = new BancoHelper(this);

        Livros = db.findAll(); // PEGA A LISTA DE LIVROS
        Lista();

    }

    public void Lista() {
         //se a lista nao for vazia
        if (!Livros.isEmpty()) {
            //pega o tamanho da lista
            tamLista = Livros.size();

            //habilitando o clicavel dos botoes de acordo quando anda na lista
            //se indice for 0 entao ta no começo da lista, logo voltar nao é possivel
            if (indice == 0) {
                anterior.setClickable(false);
            } else {
                //ja aqui estamos em outro lugar que nao é o inicio da lista
                anterior.setClickable(true);
            }
            //se nao estiver no fim da lista
            if (indice < tamLista-1) {
                proximo.setClickable(true);
            } else {
                //se estiver no fim da lista
                proximo.setClickable(false);
            }

            //pega o objeto livro da lista de acordo com o indice
            Livro l = Livros.get(indice);

            //seta os texts Views
            titulo.setText(l.getNome());
            autor.setText(l.getAutor());
            ano.setText(l.getAno());
            nota.setText("" + l.getNota()); //MELHOR GAMBIARRA QUE VC RESPEITA :)

        } else {
            //SE NAO EXISTIR LISTA DEIXA OS BOTOES NAO CLICAVEIS PRA NAO DAR BODE
            anterior.setClickable(false);
            proximo.setClickable(false);
        }
    }

    public void Proximo(View view) {
        //algoritmo obvio pt. 1
        indice++;
        Lista();
    }

    public void Anterior(View view) {
        //algoritmo obvio pt. 2
        indice--;
        Lista();
    }
}
