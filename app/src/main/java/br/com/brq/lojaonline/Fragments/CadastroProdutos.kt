package br.com.brq.lojaonline.Fragments

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import br.com.brq.lojaonline.Model.Dados
import br.com.brq.lojaonline.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_cadastro_produtos.*
import kotlinx.android.synthetic.main.activity_cadastro_produtos.view.*
import java.util.*

class CadastroProdutos : AppCompatActivity() {


    private var SelecionarUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_produtos)

        bt_selecionar_foto.setOnClickListener {

            SelecionarFoto()
        }
        //SALVAR DADOS NO FIREBASE
        bt_cadastrar_produto.setOnClickListener {

            SalvarDadosFirebase()

        }


    }

    //METODO PARA PEGAR A IMAGEM DO CELULAR E JOGAR NO CELULAR
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0){
         SelecionarUri = data?.data
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, SelecionarUri)
        foto_produto.setImageBitmap(bitmap)
        bt_selecionar_foto.alpha = 0f

        }
    }

    //ESCOLHER IMAGENS DO CELULAR PARA PUXAR E ADICIONAR NO APP
    private fun SelecionarFoto(){

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 0)

    }

    //METODO/REFERENCIA QUE PEGA COMO REFERENCIA O STOREGA DO FIREBASE DA NUVEM
    private fun SalvarDadosFirebase(){

        val nomeArquivo = UUID.randomUUID().toString()
        val referencia = FirebaseStorage.getInstance().getReference(
            "/imagens/${nomeArquivo}")
       //GARANTIR QUE UMA IMAGEM FOI SELECIONADA
        SelecionarUri?.let {
            referencia.putFile(it)
                .addOnSuccessListener {
                    referencia.downloadUrl.addOnSuccessListener {

                        //CADASTRANDO OS PRODUTOS NO FIREBASE COM AS VARIAVEIS/DADOS

                        val url = it.toString()
                        val nome = edit_nome.text.toString()
                        val preco = edit_preco.text.toString()
                        val uid = FirebaseAuth.getInstance().uid

                        val Produtos = Dados(url, nome, preco)
                        FirebaseFirestore.getInstance().collection("Produtos")
                            .add(Produtos)
                            .addOnSuccessListener {

                                Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_SHORT).show()

                            }.addOnFailureListener {

                            }

                    }
                }
        }

    }

}