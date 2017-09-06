package br.ufrn.eaj.tads.meuslivros;

import android.provider.BaseColumns;

/**
 * Created by Aluno on 06/09/2017.
 */

public class LivroContrato {

    private LivroContrato() {

    }

    public static class LivroEntry implements BaseColumns {
        public static final String TABLE_NAME = "Livro";
        public static final String NOME = "nome";
        public static final String AUTOR = "autor";
        public static final String ANO = "ano";
        public static final String NOTA = "nota";
    }
}
