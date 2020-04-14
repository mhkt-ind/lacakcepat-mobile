package id.lacakcepat.covidnineteen.ui.adapter

import android.content.Context
import android.content.res.TypedArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import id.lacakcepat.covidnineteen.R
import id.lacakcepat.covidnineteen.databinding.AdapterOnBoardingBinding
import kotlinx.android.synthetic.main.adapter_on_boarding.view.*

class OnBoardingAdapter(private val context: Context) : PagerAdapter() {

    private companion object {
        const val ANIMATE_SHOW = 1F
        const val ANIMATE_HIDE = 0F
        const val ANIMATE_TRANSITION = 100F
        const val ANIMATE_DEFAULT_TRANSITION = 0F
        const val ANIMATE_DURATION = 600L
    }

    private var images: TypedArray
    private var titles: Array<String>
    private var overviews: Array<String>

    private val res = context.resources

    init {
        images = res.obtainTypedArray(R.array.on_boarding_img)
        titles = res.getStringArray(R.array.on_boarding_title)
        overviews = res.getStringArray(R.array.on_boarding_ov)
    }

    override fun isViewFromObject(view: View, `object`: Any) = view == `object` as ConstraintLayout

    override fun getCount() = titles.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val viewBinding = DataBindingUtil.inflate<AdapterOnBoardingBinding>(
            LayoutInflater.from(context),
            R.layout.adapter_on_boarding,
            container,
            false
        )

        val view = viewBinding.root
        container.addView(view)

        view.iv_image.setImageResource(images.getResourceId(position, 0))
        viewBinding.let {
            it.title = titles[position]
            it.overview = overviews[position]
        }

        val animBounce = AnimationUtils.loadAnimation(context, R.anim.bounce)
        view.iv_image.startAnimation(animBounce)

        view.tv_title.let {
            it.alpha = ANIMATE_HIDE
            it.translationY = ANIMATE_TRANSITION
            it.animate()
                .alpha(ANIMATE_SHOW)
                .translationY(ANIMATE_DEFAULT_TRANSITION)
                .setDuration(ANIMATE_DURATION)
                .start()
        }

        view.tv_overview.let {
            it.alpha = ANIMATE_HIDE
            it.translationY = ANIMATE_TRANSITION
            it.animate()
                .alpha(ANIMATE_SHOW)
                .translationY(ANIMATE_DEFAULT_TRANSITION)
                .setStartDelay(300)
                .setDuration(ANIMATE_DURATION)
                .start()
        }

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

}