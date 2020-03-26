package id.lacakcepat.covidnineteen.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import id.lacakcepat.covidnineteen.R
import kotlinx.android.synthetic.main.fragment_on_boarding_page.*

class OnBoardingPageFragment : Fragment() {
    private var title: String? = null
    private var overview: String? = null
    private var image: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            overview = it.getString(ARG_OVERVIEW)
            image = it.getInt(ARG_IMAGE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_on_boarding_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Glide.with(this)
            .load(image)
            .into(img_league)

        textView.text = title
        textView2.text = overview
    }

    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_OVERVIEW = "overview"
        private const val ARG_IMAGE = "images"

        fun newInstance(title: String, overview: String, image: Int) =
            OnBoardingPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_OVERVIEW, overview)
                    putInt(ARG_IMAGE, image)
                }
            }
    }
}
