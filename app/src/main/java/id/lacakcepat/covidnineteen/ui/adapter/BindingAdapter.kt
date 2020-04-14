package id.lacakcepat.covidnineteen.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.api.load
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.extension.gone
import id.lacakcepat.covidnineteen.extension.invisible
import id.lacakcepat.covidnineteen.extension.visible
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

@BindingAdapter("isVisibleOrGone")
fun View.isVisibleOrGone(value: Boolean) {
    if (value) visible() else gone()
}

@BindingAdapter("isVisibleOrInvisible")
fun View.isVisibleOfInvisible(value: Boolean) {
    if (value) visible() else invisible()
}

@BindingAdapter("displayImageWithCoil")
fun ImageView.displayImageWithCoil(image: String?) {
    load(image)
}

@SuppressLint("SetTextI18n")
@BindingAdapter("displayDateTimeAgo")
fun TextView.displayDateTimeAgo(date: String?) {

    try {

        if (date != null) {

            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val parse = format.parse(date)
            val now = Date()

            val seconds = TimeUnit.MILLISECONDS.toSeconds(now.time - parse.time)
            val minutes = TimeUnit.MILLISECONDS.toMinutes(now.time - parse.time)
            val hours = TimeUnit.MILLISECONDS.toHours(now.time - parse.time)
            val days = TimeUnit.MILLISECONDS.toDays(now.time - parse.time)

            text = when {

                seconds < 60 -> "$seconds ${resources.getString(R.string.seconds_ago)}"

                minutes < 60 -> "$minutes ${resources.getString(R.string.minutes_ago)}"

                hours < 24 -> "$hours ${resources.getString(R.string.hours_ago)}"

                days > 24 -> "$days ${resources.getString(R.string.days_ago)}"

                else -> resources.getString(R.string.unknown)

            }

        }

    } catch (error: Exception) {
        error.printStackTrace()
    }

}