package com.example.projetobank.ui.home

import com.example.projetobank.data.source.DadosBancarioDataSource
import com.example.projetobank.data.source.UsuarioDataSource

class HomePresenter

    (val repositorio: DadosBancarioDataSource,
     val repositorioUsuario: UsuarioDataSource,
     val fragment: HomeContrato.View)

    : HomeContrato.Presenter {

    override fun logout() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

   private  fun listarDadosBancarioDoUsuario () {
       
   }
}