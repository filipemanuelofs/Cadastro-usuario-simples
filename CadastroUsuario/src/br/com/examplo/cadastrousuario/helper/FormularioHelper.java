package br.com.examplo.cadastrousuario.helper;

import android.widget.EditText;
import android.widget.RatingBar;
import br.com.examplo.cadastrousuario.core.Formulario;
import br.com.examplo.cadastrousuario.core.R;
import br.com.examplo.cadastrousuario.model.Aluno;

public class FormularioHelper {

	private RatingBar nota;
	private EditText telefone;
	private EditText endereco;
	private EditText site;
	private EditText nome;

	public FormularioHelper(Formulario formulario) {
		nome = (EditText) formulario.findViewById(R.id.nome);
		site = (EditText) formulario.findViewById(R.id.site);
		endereco = (EditText) formulario.findViewById(R.id.endereco);
		telefone = (EditText) formulario.findViewById(R.id.telefone);
		nota = (RatingBar) formulario.findViewById(R.id.nota);
	}

	public Aluno getAluno() {
		Aluno aluno = new Aluno();
		aluno.setNome(nome.getText().toString());
		aluno.setSite(site.getText().toString());
		aluno.setTelefone(telefone.getText().toString());
		aluno.setEndereco(endereco.getText().toString());
		aluno.setNota(Double.valueOf(nota.getRating()));
		
		return aluno;
	}

}
