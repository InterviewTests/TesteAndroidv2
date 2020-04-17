package br.com.rcp.bank.ui.fragments.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.rcp.bank.Application
import br.com.rcp.bank.repositories.base.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class AbstractVM<R: Repository>: ViewModel() {
	@Inject
	protected 	lateinit 	var		context			: Context

	@Inject
	protected 	lateinit 	var		repository		: R

	val		login		: MutableLiveData<Boolean>  = MutableLiveData(false)
	val		main		: MutableLiveData<Boolean>  = MutableLiveData(false)
	val     toast		: MutableLiveData<String>   = MutableLiveData("")
	val     progress    : MutableLiveData<Boolean> = MutableLiveData(false)

	protected	val	component					= Application.component.getServiceComponent()
	protected	fun setLoading()				= CoroutineScope(Dispatchers.Main).launch { progress.value	= true }
	protected	fun setNotLoading()				= CoroutineScope(Dispatchers.Main).launch { progress.value	= false }
	protected	fun setToast(message: String)	= CoroutineScope(Dispatchers.Main).launch { toast.value		= message }
	protected	fun callLoginScreen()			= CoroutineScope(Dispatchers.Main).launch { login.value		= true }
	protected	fun	callMainScreen()			= CoroutineScope(Dispatchers.Main).launch { main.value		= true }


}