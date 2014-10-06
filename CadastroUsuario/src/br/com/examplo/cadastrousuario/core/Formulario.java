package br.com.examplo.cadastrousuario.core;

import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.examplo.cadastrousuario.dao.AlunoDAO;
import br.com.examplo.cadastrousuario.helper.FormularioHelper;
import br.com.examplo.cadastrousuario.model.Aluno;

public class Formulario extends Activity {
	private FormularioHelper helper;
	private Aluno alunoParaAlterar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);
		
		Intent intent = getIntent();
		alunoParaAlterar = (Aluno) intent.getSerializableExtra("alunoSelecionado");
		
		helper = new FormularioHelper(this);
		
		Button btnGravar = (Button) findViewById(R.id.botao);
		
		if (alunoParaAlterar != null) {
			btnGravar.setText("Alterar");
			helper.setAlunoNoFormulario(alunoParaAlterar);
		}
		
		btnGravar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Aluno aluno = helper.getAluno();
				AlunoDAO dao = new AlunoDAO(Formulario.this);
				
				if (alunoParaAlterar == null) {
					dao.salvar(aluno);					
				} else {
					aluno.setId(alunoParaAlterar.getId());
					dao.alterar(aluno);
				}
				
				dao.close();
				
				finish();
			}
		});
		
	}
}
