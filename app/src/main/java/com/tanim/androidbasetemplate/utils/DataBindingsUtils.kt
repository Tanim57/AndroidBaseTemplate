@file:Suppress("UNCHECKED_CAST")

package com.tigerit.schoolattendance.utils

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.ViewUtils
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.tanim.androidbasetemplate.R
import com.tanim.androidbasetemplate.utils.ConverterUtils
import timber.log.Timber
import java.util.*


@BindingAdapter(value = ["text"])
fun text(textView: TextView, text: String?) {
    if (TextUtils.isEmpty(text)) {
        textView.setText("N/A")
    } else {
        textView.text = text
    }
}

@BindingAdapter(value = ["textDot"])
fun textDot(textView: TextView, text: Any?) {
    if (text == null) {
        textView.text = "---"
    } else {
        if (text is String) {
            if (TextUtils.isEmpty(text)) {
                textView.text = "---"
            } else textView.text = text
        } else if (text is Int) {
            textView.text = String.format(Locale.getDefault(), "%d", text)
        }
    }
}

@BindingAdapter("layoutMargin")
fun setBottomMargin(view: View, margin: Float) {
    val layoutParams = view.layoutParams as MarginLayoutParams
    layoutParams.setMargins(
        margin.toInt(), margin.toInt(),
        margin.toInt(), margin.toInt()
    )
    view.layoutParams = layoutParams
}

@BindingAdapter(value = ["recyclerView_adapter"])
fun recyclerView_adapter(
    recyclerView: RecyclerView,
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?
) {
    recyclerView.adapter = adapter
}


@BindingAdapter(value = ["startShimmer"])
fun visibleShimmer(shimmerFrameLayout: ShimmerFrameLayout, boolean: Boolean) {
    if (boolean) {
        shimmerFrameLayout.visibility = View.VISIBLE
        shimmerFrameLayout.startShimmer()
    } else {
        shimmerFrameLayout.visibility = View.GONE
        shimmerFrameLayout.stopShimmer()
    }
}

@BindingAdapter(value = ["goneUnless"])
fun goneUnless(view: View, boolean: Boolean) {
    when {
        boolean -> {
            view.visibility = View.VISIBLE
        }
        else -> {
            view.visibility = View.GONE
        }
    }
}

@BindingAdapter(value = ["refreshing"])
fun goneUnless(view: SwipeRefreshLayout, boolean: Boolean) {
    view.isRefreshing = boolean
}

@BindingAdapter(value = ["load_image"])
fun loadImage(view: ImageView, data: String?) {
    if(data==null)
        return

    if(!TextUtils.isEmpty(data)){

    }else{

    }

}


@BindingAdapter(value = ["invisibleUnless"])
fun invisibleUnless(view: View, boolean: Boolean) {
    when {
        boolean -> {
            view.visibility = View.VISIBLE
        }
        else -> {
            view.visibility = View.INVISIBLE
        }
    }
}


@BindingAdapter(value = ["animate_view"])
fun animateView(view: View, value: Animation?) {
    if (value == null)
        return

    try {
        view.startAnimation(value)
    } catch (e: Exception) {
        Timber.e(e)
    }

}


@BindingAdapter(value = ["load_image"])
fun loadImage(view: ImageView, photo: ByteArray?) {
    if (photo != null) {
        view.setImageBitmap(ConverterUtils.byteArrayToBitmap(photo))
        view.setOnClickListener(View.OnClickListener {

        })
    } else {
        //view.setImageBitmap(null)
    }
}

@BindingAdapter(value = ["load_resource"])
fun loadResource(imageView: ImageView, resource: Int?) {
    resource?.let { imageView.setImageResource(it) };
    imageView.setOnClickListener(View.OnClickListener {

    })
}


