package br.com.examplo.cadastrousuario.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.examplo.cadastrousuario.model.Aluno;

public class AlunoDAO extends SQLiteOpenHelper {

	private static final String DATABASE = "cadastro.db";
	private static final int VERSION = 1;

	private static final String TABELA = "Aluno";

	private static final String COL_ID = "_id";
	private static final String COL_NOME = "nome";
	private static final String COL_SITE = "site";
	private static final String COL_ENDERECO = "endereco";
	private static final String COL_TELEFONE = "telefone";
	private static final String COL_NOTA = "nota";
	private static final String COL_FOTO = "foto";

	public AlunoDAO(Context context) {
		super(context, DATABASE, null, VERSION);
	}

	public void salvar(Aluno aluno) {
		ContentValues values = new ContentValues();

		values.put(COL_NOME, aluno.getNome());
		values.put(COL_SITE, aluno.getSite());
		values.put(COL_ENDERECO, aluno.getEndereco());
		values.put(COL_TELEFONE, aluno.getTelefone());
		values.put(COL_NOTA, aluno.getNota());
		values.put(COL_FOTO, aluno.getFoto());

		getWritableDatabase().insert(TABELA, null, values);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String SQL = "CREATE TABLE " + TABELA + " (" + COL_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NOME
				+ " TEXT NOT NULL, " + COL_SITE + " TEXT NOT NULL, "
				+ COL_ENDERECO + " TEXT NOT NULL, " + COL_TELEFONE
				+ " TEXT NOT NULL, " + COL_FOTO + " TEXT, " + COL_NOTA
				+ " REAL);";
		db.execSQL(SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String SQL = "DROP DATABASE IF EXISTS " + TABELA;
		db.execSQL(SQL);
		this.onCreate(db);
	}

	public List<Aluno> getLista() {
		String[] columns = { COL_ID, COL_NOME, COL_SITE, COL_ENDERECO,
				COL_TELEFONE, COL_FOTO, COL_NOTA };
		Cursor query = getWritableDatabase().query(TABELA, columns, null, null,
				null, null, null);

		ArrayList<Aluno> alunos = new ArrayList<Aluno>();

		while (query.moveToNext()) {
			Aluno aluno = new Aluno();
			aluno.setId(query.getLong(0));
			aluno.setNome(query.getString(1));
			aluno.setSite(query.getString(2));
			aluno.setEndereco(query.getString(3));
			aluno.setTelefone(query.getString(4));
			aluno.setFoto(query.getString(5));
			aluno.setNota(query.getDouble(6));
			
			alunos.add(aluno);

		}
		return alunos;
	}
}
