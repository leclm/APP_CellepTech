package com.leclm.celleptechcurso

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Criando o listener do btn Entrar
        btnLoginEntrar.setOnClickListener {
            val email = edtLoginEmail.text.toString().trim()
            val senha = edtLoginSenha.text.toString().trim()

            // Validação dos campos
                // verificando se os campos não estão vazios
            if (email.isEmpty()) {
                edtLoginEmail.error = "Campo obrigatório!"
                edtLoginEmail.requestFocus() // serve para que a mensagem "campo obrigatorio" apareça quando clicar em entrar. Sem essa linha só aparece um ! vermelho na linha do email e quanco clica na linha aparece a mensagem
            } else if (senha.isEmpty()) {
                edtLoginSenha.error = "Campo obrigatório!"
                edtLoginSenha.requestFocus()
            } else {
                // Acessando o arquivo de SharedPreferences
                val sharedPref = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)

                // Recuperando os dados do arquivos SharedPreferences
                val emailPref = sharedPref.getString("EMAIL", "") // segundo elemento é o que ele vai retornar se der algum erro na busca do EMAIL
                val senhaPref = sharedPref.getString("SENHA", "")

                // Verificando se o email e a senha estao corretos
                if (email == emailPref && senha == senhaPref) {

                    // Exibindo um alerta ao usuário
                    Toast.makeText(this, "Usuário Logado", Toast.LENGTH_LONG).show()

                    // Abrindo a próxima tela (Main) com intent
                    val myIntent = Intent(this, MainActivity::class.java)

                    // Passando os dados para a prox tela (main) atraves da intent
                    myIntent.putExtra("INTENT_EMAIL", email) // pode ser tbm o emailPrefs

                    //Iniciar a MainActivity através da Intent
                    startActivity(myIntent)

                    // Encerrando a tela de login e splash, o usuario só volta pra essa tela se ele deslogar
                    finishAffinity()
                } else {
                    // Exibindo um Toast ao usuário
                    Toast.makeText(this, "Email e/ou senha inválidos!", Toast.LENGTH_LONG).show()
                }
            }
        }

        // Criando o listener do btn Cadastrar
        btnLoginCadastrar.setOnClickListener {
            // Abrindo a próxima tela (Cadastro)
            val myIntent = Intent(this, CadastroActivity::class.java)
            startActivity(myIntent)
        }
    }
}