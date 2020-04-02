package id.lacakcepat.covidnineteen.utilities

import android.view.View

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.setVisible(state:Boolean){
    if(state){
        visible()
    }else
        gone()
}