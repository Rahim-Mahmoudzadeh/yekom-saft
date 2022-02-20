package ir.rahimmahmuodzadeh.yekomsaft.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import ir.rahimmahmuodzadeh.yekomsaft.R

abstract class ContactFragment : Fragment(), ContentView {
    override val rootView: ConstraintLayout
        get() = view as ConstraintLayout
    override val viewContext: Context?
        get() = context

    fun showErrorResponse(textError: String) {
        Toast.makeText(activity, textError, Toast.LENGTH_SHORT).show()
    }

    fun showSuccessResponse(textError: String) {
        Toast.makeText(activity, textError, Toast.LENGTH_SHORT).show()
    }
}

interface ContentView {
    val rootView: ConstraintLayout?
    val viewContext: Context?

    fun showProgressBar(show: Boolean) {
        rootView?.let { rootView ->
            viewContext?.let { context ->
                var loadingView = rootView.findViewById<View>(R.id.loadingView)
                if (loadingView == null && show) {
                    loadingView =
                        LayoutInflater.from(context).inflate(R.layout.view_loading, rootView, false)
                    rootView.addView(loadingView)
                }
                loadingView?.visibility = if (show) View.VISIBLE else View.GONE
            }
        }
    }
}