package br.com.talita.estacaohack

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        //Criando meu Shared Preference
        val minhaPreferencia = getSharedPreferences("minha-preferencia", Context.MODE_PRIVATE)
        //Criando o editor
        val meuEditor = minhaPreferencia.edit()
        //Criando uma lista para o Spinner
        val listaSexo = arrayListOf("Selecione o sexo", "Masculino", "Feminino", "Outro")
        //Criando o adapter

        val adapterSexo = ArrayAdapter(this@CadastroActivity, android.R.layout.simple_spinner_dropdown_item, listaSexo)
        spnSexo.adapter =adapterSexo
        //
        btnRegister.setOnClickListener {
            val nome = edtName.text .toString ().trim()
            val sobrenome = edtLastName.text.toString().trim()
            val email = edtEmail.text.toString().trim().toLowerCase()
            val senha = edtPass.text.toString().trim()
            //estrutura de decisão
            if (nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(
                    this@CadastroActivity,
                    "Por favor Preencha todos os campos",
                    Toast.LENGTH_LONG
                ).show()
            } else if (spnSexo.selectedItem == "Selecione o sexo") {
                Toast.makeText(this@CadastroActivity, "Selecione um sexo", Toast.LENGTH_LONG).show()
            } else {
                //gravando as informações dentro do Shared Preference
                meuEditor.putString("nome", nome).apply()
                meuEditor.putString("sobrenome", sobrenome).apply()
                meuEditor.putString("email", spnSexo.selectedItem.toString()).apply()
                meuEditor.putString("senha", spnSexo.selectedItem.toString()).apply()
                meuEditor.putString("sexo", spnSexo.selectedItem.toString()).apply()

                //Criando um Alert para o Shared Preference
                AlertDialog.Builder(this@CadastroActivity)
                    .setTitle("Sucesso")
                    .setMessage("usuário cadastro com sucesso")
                    .setPositiveButton("OK") { _, _ ->

                        onBackPressed()
                    }

                    .create()
                    .show()

        }
    }




}

}
