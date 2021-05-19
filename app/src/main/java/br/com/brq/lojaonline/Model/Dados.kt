package br.com.brq.lojaonline.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//CLASSE PARA ARMAZENAR OS DADOS

@Parcelize

data class Dados(

    val uid: String = " ",
    val nome: String = " ",
    val preco: String = " ",
    val url: String = " "):Parcelable