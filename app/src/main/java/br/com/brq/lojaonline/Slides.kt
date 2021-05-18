package br.com.brq.lojaonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.brq.lojaonline.Form.FormLogin
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide

class Slides : IntroActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_slides)

        //BOTÃO PARA VOLTAR A TELA FOI RETIRADO
        isButtonBackVisible = false

    //TRANSIÇÃO DOS SLIDES
    addSlide(

        SimpleSlide.Builder()
            .background(R.color.Roxo)
            .image(R.drawable.drawer)
            .title("Loja Online de Tênis")
            .description("Entre e confira as promoções e preços que preparamos para você!")
            .build()

    )
        addSlide(

            SimpleSlide.Builder()
                .background(R.color.AzulV)
            // não permite voltar para o primeiro Slide    .canGoBackward(false)
                .title("Crie uma conta Grátis!")
                .description("Cadastre-se agora! E venha conhecer os nossos produtos!!")
                .build()
        )

        addSlide(
        SimpleSlide.Builder()
            .background(R.color.Roxo)
            .title("Aproveite nosso App!")
            .description("SEJA BEM-VINDO")
            .build()
        )

    }
//ENCERRA OS SLIDES E ABRE A TELA DE LOGIN
    override fun onDestroy() {
        super.onDestroy()

        var intent = Intent(this, FormLogin::class.java)
        startActivity(intent)
        finish()
    }

}