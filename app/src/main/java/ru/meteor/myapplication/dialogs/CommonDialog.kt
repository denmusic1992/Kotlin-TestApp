package ru.meteor.myapplication.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import ru.meteor.myapplication.R

open class CommonDialog: DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    public fun showDialog(
        fragmentManager: FragmentManager,
        tag: String,
        cancelable: Boolean) {

        // Проверяем, нет ли в стеке фрагментов уже нашего dialog
        val fragmentTransaction = fragmentManager.beginTransaction()
        val currentFragment = fragmentManager.findFragmentByTag(tag)

        // Если уже есть такой фрагмент, удаляем его
        if (currentFragment != null) {
            fragmentTransaction.remove(currentFragment)
        } else {
            fragmentTransaction.addToBackStack(null)
        }
        super.show(fragmentTransaction, tag)
        isCancelable = cancelable
    }
}