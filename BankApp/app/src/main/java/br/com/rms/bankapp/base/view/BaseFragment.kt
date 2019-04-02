package br.com.rms.bankapp.base.view

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import br.com.rms.bankapp.R
import br.com.rms.bankapp.base.mvp.BaseContract
import br.com.rms.bankapp.base.mvp.BaseView
import br.com.rms.bankapp.utils.validations.CheckBoxValidationException
import br.com.rms.bankapp.utils.validations.EditTextValidationException
import br.com.rms.bankapp.utils.validations.MultipleValidationExceptions
import br.com.rms.bankapp.utils.validations.ValidationException
import dagger.android.AndroidInjection
import java.util.concurrent.TimeUnit
import javax.inject.Inject

abstract class BaseFragment<V: BaseContract.View, P: BaseContract.Presenter<V>>: Fragment(), BaseView<V,P> {

    val TAG = this.javaClass.simpleName
    private lateinit var root: View

    @Inject
    lateinit var presenter: P

    fun getWindowSize(): Point{
        val display = activity?.windowManager?.defaultDisplay
        val size = Point()
        display?.getSize(size)
        return size
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(getLayoutId(), container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //presenter = newPresenterInstance()
        presenter.attach(getViewInstance())
        initViews()
    }

    fun showToastShort(textRes: Int){
        showToast(textRes, Toast.LENGTH_SHORT)
    }

    fun showToast(textRes: Int, lenght: Int){
        Toast.makeText(context,textRes,lenght).show()
    }

    fun onValidationException(throwable: ValidationException) {
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