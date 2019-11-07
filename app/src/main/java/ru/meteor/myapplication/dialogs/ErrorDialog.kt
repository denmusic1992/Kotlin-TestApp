package ru.meteor.myapplication.dialogs

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.dialog_common.view.*
import ru.meteor.myapplication.R
import ru.meteor.myapplication.interfaces.ItemClickInterface

class ErrorDialog(private val error: String): CommonDialog(), View.OnClickListener {

    // биндим лэйаут к классу и инициализируем сообщение
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.dialog_common, container, false)
        initDialogContent(view)
        return view
    }


    override fun onClick(v: View) {
        // Можно заменить на простой вызов dismiss(), но мало ли, если ещё добавится функционал обработки нажатия
        if (v.id == R.id.btnOK) {
            dialog?.dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        // Здесь получаем последнее активити из стека
        if (activity is ItemClickInterface)
            (activity as ItemClickInterface).onClick()
    }

    private fun initDialogContent(v: View) {
        // обработчик события нажатия
        v.btnOK.setOnClickListener(this)
        // Здесь указываем причину ошибки, если есть
        v.textNoConnection.text = error
    }
}