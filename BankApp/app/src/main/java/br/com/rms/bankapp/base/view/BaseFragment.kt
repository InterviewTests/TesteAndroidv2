package br.com.rms.bankapp.base.view

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import br.com.rms.bankapp.base.mvp.BaseContract
import br.com.rms.bankapp.base.mvp.BaseView
import br.com.rms.bankapp.utils.validations.CheckBoxValidationException
import br.com.rms.bankapp.utils.validations.EditTextValidationException
import br.com.rms.bankapp.utils.validations.MultipleValidationExceptions
import br.com.rms.bankapp.utils.validations.ValidationException
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<V: BaseContract.View, P: BaseContract.Presenter>: Fragment(), BaseView<V,P> {

    val TAG: String = this.javaClass.simpleName
    private lateinit var root: View

    @Inject
    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)

    }

    fun getWindowSize(): Point{
        val display = activity?.windowManager?.defaultDisplay
        val size = Point()
        display?.getSize(size)
        return size
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(getLayoutId(), container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ProcessLifecycleOwner.get().lifecycle.addObserver(presenter)
        initViews()
    }

    fun showToastShort(textRes: Int){
        showToast(textRes, Toast.LENGTH_SHORT)
    }

    fun showToastLong(textRes: Int){
        showToast(textRes, Toast.LENGTH_LONG)
    }

    fun showToastLong(text: String){
        showToast(text, Toast.LENGTH_LONG)
    }

    private fun showToast(textRes: Int, lenght: Int){
        Toast.makeText(context,textRes,lenght).show()
    }

    private fun showToast(text: String, lenght: Int){
        Toast.makeText(context,text,lenght).show()
    }


    fun validateError(throwable: ValidationException) {
        var validationExceptions = listOf(throwable)
        if (throwable is MultipleValidationExceptions) {
            validationExceptions = throwable.validationExceptions
        }
        validationExceptions.forEach {validationException ->
            if (validationException is EditTextValidationException) {
                val editTextId = validationException.editTextId
                val editText = root.findViewById<EditText>(editTextId)
                editText?.let {
                    editText.error = getString(validationException.errorStringRes)
                }
            } else if (validationException is CheckBoxValidationException) {

            }
            return@forEach
        }
    }




}