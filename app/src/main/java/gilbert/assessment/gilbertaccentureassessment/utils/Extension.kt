package gilbert.assessment.gilbertaccentureassessment.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.showView() {
    visibility = View.VISIBLE
}

fun View.hideView() {
    visibility = View.GONE
}

fun String.toCapitalized() = replaceFirstChar { it.uppercase() }
fun checkStringNullOrNot(text: String?): Boolean =
    text != null && text.trim { it <= ' ' }.isNotBlank() && text != "null"
fun setImageView(context: Context, imageView: ImageView, drawable: Int) {
    Glide.with(context)
        .load(drawable)
        .into(imageView)
}
fun <T> MutableList<T>.toArrayList(): ArrayList<T> = ArrayList(this)