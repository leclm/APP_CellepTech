package com.leclm.celleptechcurso

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_web.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Recuperando o email passado por meio da intent
        val email = intent.getStringExtra("INTENT_EMAIL")

        // Acessando o arquivo shared preferences
        val sharedPref = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)

        // Recuperando os dados do arquivo de shared preferences
        val nome = sharedPref.getString("NOME", "")
        val sobrenome = sharedPref.getString("SOBRENOME", "")
        val genero = sharedPref.getString("GENERO", "")

        // Exibindo os dados nos componentes TextViews da tela main
        txvMainNome.text = "$nome $sobrenome"
        txvMainEmail.text = "$email"
        txvMainGenero.text = "$genero"

        // Listener do botão btnMainSite - cellep
        btnMainSite.setOnClickListener {
            val myIntent = Intent(this, WebActivity::class.java)
            startActivity(myIntent)
        }

        // Listener do botão de sair e voltar para a tela de login
        btnMainSair.setOnClickListener {
            // Criando alert dialog
            val alert = AlertDialog.Builder(this)

            // Configurando o título do alert dialog
            alert.setTitle("Atenção")

            // Configurando a mensagem do alert dialog
            alert.setMessage("Deseja mesmo sair?")

            // Configurando as opções de resposta
            alert.setPositiveButton("Sair") { _, _ ->
                val myIntent = Intent(this, LoginActivity::class.java)
                startActivity(myIntent)
                finishAffinity()
            }

            alert.setNeutralButton("Cancelar") { _, _ -> }

            // Impedindo que o alert seja cancelado clicando fora do box
            alert.setCancelable(false)

            alert.show()
        }
    }
}