package br.ufrn.eaj.tads.meuslivros;


/**
 * Created by Aluno on 06/09/2017.
 */

public class Livro {
    private long id;
    private String nome, autor, ano;
    private float nota;

    public Livro(String nome, String autor, String ano, float nota) {
        this.nome = nome;
        this.autor = autor;
        this.ano = ano;
        this.nota = nota;
        this.id = 0;
    }
    public Livro(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", autor='" + autor + '\'' +
                ", ano='" + ano + '\'' +
                ", nota=" + nota +
                '}';
    }
}
