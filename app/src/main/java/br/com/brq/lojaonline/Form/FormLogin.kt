package br.com.brq.lojaonline.Form

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import br.com.brq.lojaonline.R
import br.com.brq.lojaonline.TelaPrincipal
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_form_login.*

class FormLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_login)

        VerificarUsuarioLogado()

        supportActionBar!!.hide()

        text_tela_cadastro.setOnClickListener{
            var intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)

        }

        bt_entrar.setOnClickListener {
            AutenticarUsuario()
        }
    }

// AUTENTICAÇÃO DO USUARIO E SENHA
    private fun AutenticarUsuario(){

        val email = edit_email.text.toString()
        val senha = edit_senha.text.toString()

        if(email.isEmpty() || senha.isEmpty()){

//CASO NÃO SEJA PREENCHIDO OS CAMPOS DE EMAIL E SENHA

            var snackbar = Snackbar.make(layout_login, "Coloque o seu e-mail e senha!", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", {
                })
            snackbar.show()
        }else

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener {
            if (it.isSuccessful){
              frameL.visibility = View.VISIBLE
              AbrirTelaPrincipal()
            }



        }.addOnFailureListener {
//CASO COLOQUE O USUÁRIO E SENHA DE FORMA ERRADA, TERA UMA MSG DE ERRO
            var snackbar = Snackbar.make(layout_login, "Erro ao acessar usuário!", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", {
                })
            snackbar.show()

        }

    }

    private fun VerificarUsuarioLogado(){

        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if (usuarioAtual != null){
            AbrirTelaPrincipal()
        }
    }

    private fun AbrirTelaPrincipal(){
        var intent = Intent(this, TelaPrincipal::class.java)
        startActivity(intent)
        finish()
    }


}