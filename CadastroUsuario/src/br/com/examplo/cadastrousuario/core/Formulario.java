package br.com.examplo.cadastrousuario.core;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.examplo.cadastrousuario.dao.AlunoDAO;
import br.com.examplo.cadastrousuario.helper.FormularioHelper;
import br.com.examplo.cadastrousuario.model.Aluno;

public class Formulario extends Activity {
	private FormularioHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		helper = new FormularioHelper(this);
		
		
		Button btnGravar = (Button) findViewById(R.id.botao);
		btnGravar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Aluno aluno = helper.getAluno();
				AlunoDAO dao = new AlunoDAO(Formulario.this);
				
				dao.salvar(aluno);
				dao.close();
				
				finish();
			}
		});
		
	}
}
