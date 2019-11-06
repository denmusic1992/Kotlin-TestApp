package ru.meteor.myapplication.retrofit

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.meteor.myapplication.R
import ru.meteor.myapplication.ui.CommonActivity

abstract class CommonCallback<T> (val context: Context) : Callback<T> {

    override fun onFailure(call: Call<T>, t: Throwable) {
        if (context is CommonActivity) {
            context.dismissDialog()
            context.showErrorDialog(context.getString(R.string.error_connection))
        }
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (context is CommonActivity) {
            context.dismissDialog()
            val content = response.body()
            if (content != null)
                onSuccess(content)
            else
                context.showErrorDialog(context.getString(R.string.error_connection))
        }
    }

    abstract fun onSuccess(content: T)
}