package ru.meteor.myapplication.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.appbar_layout_with_title.*
import ru.meteor.myapplication.R
import ru.meteor.myapplication.interfaces.ItemClickInterface
import ru.meteor.myapplication.dialogs.ErrorDialog
import ru.meteor.myapplication.dialogs.LoadingDialog

abstract class CommonActivity: AppCompatActivity(), ItemClickInterface {

    private lateinit var loadingDialog: LoadingDialog
    protected var isLaunchSuccessful: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(!isNetworkConnected()) {
            showErrorDialog(getString(R.string.no_internet))
            isLaunchSuccessful = false
            return
        }

        loadingDialog = LoadingDialog()
    }

    // Метод инициализации контролов и что ещё добавится
    internal abstract fun initControls()


    // Общая для всех инициализация тулбара и контролов
    fun initialize(title: String) {
        initToolbar(title)
        initControls()
    }

    private fun initToolbar(title: String) {
        if(toolbar != null) {
            toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorAccent))
            toolbar.title = title
        }
    }

    override fun onClick() { finish() }

    override fun onClick(position: Int) { }

    // метод показывающий диалоговое окно
    fun showErrorDialog(error: String) {
        // Создаем экземпляр диалога
        val dialog = ErrorDialog(error)
        // Показываем диалог
        dialog.showDialog(supportFragmentManager, getString(R.string.tagLoadingDialog), false)
    }


    // Метод для получения доступа к Интернету
    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }

    fun showDialog() {
        loadingDialog.showDialog(supportFragmentManager, getString(R.string.tagLoadingDialog), true)
    }

    fun dismissDialog() {
        loadingDialog.dismiss()
    }
}