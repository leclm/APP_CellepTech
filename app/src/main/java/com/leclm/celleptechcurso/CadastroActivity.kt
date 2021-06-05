package com.leclm.celleptechcurso

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // Criar lista de opções para o Spinner
        val listaGenero = arrayListOf("Selecione o gênero", "Feminino", "Masculino", "Não binário", "Prefiro não declarar")

        // Criar um adaptador para o spinner: tela em que o adapter vai ser exibido (this), o tipo de adaptador e a lista
        val generoAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listaGenero
        )

        // Inserir o adapter no spinner
        spnCadastroGenero.adapter = generoAdapter

        // Criando o listener do btn Cadastrar
        btnCadastroCadastrar.setOnClickListener {
            // Obtendo os dados digitados pelo usuário
            val nome = edtCadastroNome.text.toString().trim()
            val sobrenome = edtCadastroSobrenome.text.toString().trim()
            val email = edtCadastroEmail.text.toString().trim()
            val senha = edtCadastroSenha.text.toString()
            val genero = spnCadastroGenero.selectedItem.toString()

            // Validação dos campos
            if (nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty() || genero == listaGenero[0]) {
                // Mostrando toast ao usuario
                Toast.makeText(this, "Dados inválidos!", Toast.LENGTH_LONG).show()
            } else { // Se todos os campos estiverem preenchidos corretamente
                // Criar/acessar o arquivo de sharedPreferences
                // Dois argumentos no get: nome do arquivo e o modo de acesso
                val sharedPref = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)

                // Editar arquivo
                val editPref = sharedPref.edit()

                // Adicionar modificações no edit
                editPref.putString("NOME", nome) // key: NOME, value: nome
                editPref.putString("SOBRENOME", sobrenome)
                editPref.putString("EMAIL", email)
                editPref.putString("SENHA", senha)
                editPref.putString("GENERO", genero)

                // Aplicando as alterações no Shared Preferences
                editPref.apply()

                // Abrir a proxima tela (main)
                val myIntent = Intent(this, MainActivity::class.java)

                // Passando um dado através da Intent
                myIntent.putExtra("INTENT_EMAIL", email)

                // Iniciar a próxima tela
                startActivity(myIntent)

                // Encerrando todas as tela empilhadas
                finishAffinity()
            }

            // Exibindo um Toast ao usuário
            Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_LONG).show()
        }
    }
}