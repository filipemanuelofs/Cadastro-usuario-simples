package br.com.examplo.cadastrousuario.core;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.examplo.cadastrousuario.dao.AlunoDAO;
import br.com.examplo.cadastrousuario.model.Aluno;

public class ListaAlunos extends Activity {

	private ListView lista;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_alunos);

		lista = (ListView) findViewById(R.id.lista);

		lista.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				Toast.makeText(ListaAlunos.this,
						"Clicou na posicao " + position, Toast.LENGTH_SHORT)
						.show();
			}
		});

		lista.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int position, long id) {
				Toast.makeText(ListaAlunos.this,
						"Clique longo na posicao " + position,
						Toast.LENGTH_SHORT).show();
				return true;
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		AlunoDAO dao = new AlunoDAO(this);
		List<Aluno> alunos = dao.getLista();
		dao.close();

		int layout = android.R.layout.simple_list_item_1;
		ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, layout,
				alunos);

		lista.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.listagem_alunos, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemClicado = item.getItemId();

		switch (itemClicado) {
		case R.id.novo:
			startActivity(new Intent(this, Formulario.class));
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
