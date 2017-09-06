package br.ufrn.eaj.tads.meuslivros;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aluno on 06/09/2017.
 */

public class BancoHelper extends SQLiteOpenHelper {

    //String auxiliares
    private static final String TAG = "sql";
    private static final String TEXT_TYPE = " TEXT";
    private static final String NUMBER_TYPE = " INTEGER";
    private static final String FLOAT_TYPE = " FLOAT";
    private static final String VIRGULA = ",";

    private static final String DATABASE_NAME = "bancoLivraria.sqlite";

    //versão do banco
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE =
            ("CREATE TABLE " + LivroContrato.LivroEntry.TABLE_NAME +
                    "(" +
                    LivroContrato.LivroEntry._ID + NUMBER_TYPE + " PRIMARY KEY" +
                    LivroContrato.LivroEntry.NOME + TEXT_TYPE + VIRGULA +
                    LivroContrato.LivroEntry.AUTOR + TEXT_TYPE + VIRGULA +
                    LivroContrato.LivroEntry.ANO + TEXT_TYPE + VIRGULA +
                    LivroContrato.LivroEntry.NOTA + FLOAT_TYPE +
                    ");");

    private static final String SQL_DROP_TABLE =
            ("DROP TABLE " + LivroContrato.LivroEntry.TABLE_NAME + ";"
            );

    public BancoHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int VERSAOANTIGA, int NOVAVERSAO) {

    }

    //INSERE UM NOVO LIVRO, OU ATUALIZA SE JA EXISTE
    public long save(Livro livro) {
        long id = livro.getId();
        SQLiteDatabase db = getWritableDatabase();

        try {
            ContentValues valores = new ContentValues();
            valores.put(LivroContrato.LivroEntry.NOME, livro.getNome());
            valores.put(LivroContrato.LivroEntry.AUTOR, livro.getAutor());
            valores.put(LivroContrato.LivroEntry.ANO, livro.getAno());
            valores.put(LivroContrato.LivroEntry.NOTA, livro.getNota());

            if (id != 0) {

                String selection = LivroContrato.LivroEntry._ID + "= ?";
                String[] whereArgs = new String[]{String.valueOf(id)};

                // update carro set values = ... where _id=?
                int count = db.update(LivroContrato.LivroEntry.TABLE_NAME, valores, selection, whereArgs);

                return count;

            } else {
                // insert into carro values (...)-------------------alterei de "" para null
                id = db.insert(LivroContrato.LivroEntry.TABLE_NAME, null, valores);

                return id;
            }

        } finally {
            db.close();
        }
    }

    //Consulta a lista com todos os livros
    public List<Livro> findAll() {
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor c = db.query(LivroContrato.LivroEntry.TABLE_NAME, null, null, null, null, null, null, null);
            return toList(c);
        } finally {

        }
    }

    //Le o cursor e cria a lista de livros
    private List<Livro> toList(Cursor c) {
        List<Livro> livros = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                Livro livro = new Livro();
                livros.add(livro);

                //recupera os atributos de livro
                livro.setId(c.getInt(c.getColumnIndex(LivroContrato.LivroEntry._ID)));
                livro.setNome(c.getString(c.getColumnIndex(LivroContrato.LivroEntry.NOME)));
                livro.setAutor(c.getString(c.getColumnIndex(LivroContrato.LivroEntry.AUTOR)));
                livro.setAno(c.getString(c.getColumnIndex(LivroContrato.LivroEntry.ANO)));
                livro.setNota(c.getFloat(c.getColumnIndex(LivroContrato.LivroEntry.NOTA)));
            } while (c.moveToNext());
        }
        return livros;
    }

    //executa um sql
    public void execSQL(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.execSQL(sql);
        } finally {
            db.close();
        }
    }

    //executa um sql
    public void execSQL(String sql, Object[] args) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.execSQL(sql, args);
        } finally {
            db.close();
        }

    }


}
