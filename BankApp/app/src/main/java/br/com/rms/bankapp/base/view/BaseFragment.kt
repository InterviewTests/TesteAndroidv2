package br.com.rms.bankapp.base.view

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.rms.bankapp.base.mvp.BaseContract
import br.com.rms.bankapp.base.mvp.BaseView

abstract class BaseFragment<V: BaseContract.View, P: BaseContract.Presenter<V>>: Fragment(), BaseView<V,P> {

    val TAG = this.javaClass.simpleName
    private lateinit var root: View
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
        presenter = newPresenterInstance()
        presenter.attach(getViewInstance())
        initViews()
    }

    fun showToastShort(textRes: Int){
        showToast(textRes, Toast.LENGTH_SHORT)
    }

    fun showToast(textRes: Int, lenght: Int){
        Toast.makeText(context,textRes,lenght).show()
    }

















}