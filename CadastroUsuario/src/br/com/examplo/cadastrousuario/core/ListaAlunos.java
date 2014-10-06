package br.com.examplo.cadastrousuario.core;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
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

	private Aluno aluno;
	private ListView lista;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_alunos);

		lista = (ListView) findViewById(R.id.lista);
		registerForContextMenu(lista);

		lista.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {
				Aluno alunoClicado = (Aluno) adapter.getItemAtPosition(position);
				
				Intent intent = new Intent(ListaAlunos.this, Formulario.class);
				intent.putExtra("alunoSelecionado", alunoClicado);
				startActivity(intent);
			}
		});

		lista.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int position, long id) {
				aluno = (Aluno) adapter.getItemAtPosition(position);
				return false;
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.listagem_alunos, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		menu.add("Ligar");
		menu.add("Enviar SMS");
		menu.add("Navegar no site");
		MenuItem deletar = menu.add("Deletar");
		deletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlunoDAO dao = new AlunoDAO(ListaAlunos.this);
				dao.deletar(aluno);
				dao.close();
				
				carregarLista();
				
				return false;
			}
		});
		menu.add("Ver no mapa");
	}

	@Override
	protected void onResume() {
		super.onResume();
		carregarLista();
	}

	private void carregarLista() {
		AlunoDAO dao = new AlunoDAO(this);
		List<Aluno> alunos = dao.getLista();
		dao.close();

		int layout = android.R.layout.simple_list_item_1;
		ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, layout,
				alunos);

		lista.setAdapter(adapter);
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
