package com.tanim.androidbasetemplate.core.home

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import com.tanim.androidbasetemplate.BR
import com.tanim.androidbasetemplate.R
import com.tanim.androidbasetemplate.base.BaseFragment
import com.tanim.androidbasetemplate.databinding.FragmentHomeBinding
import com.tanim.androidbasetemplate.databinding.FragnentLoginBinding
import com.tanim.androidbasetemplate.di.component.FragmentComponent
import com.tanim.androidbasetemplate.extension.visible


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel


    override fun performDependencyInjection(buildComponent: FragmentComponent) {
        buildComponent.inject(this)
    }

    override val layout: Int
        get() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val worldMap = BitmapFactory.decodeResource(
            requireContext().resources,
            R.drawable.world_map
        )

        viewDataBinding.btnWorldMap.setOnClickListener {

            val byteArray:ByteArray? = mViewModel.encode(worldMap)
            val image = mViewModel.decode(byteArray)

            viewDataBinding.ivCompressWorldMap.setImageBitmap(image)
            viewDataBinding.tvCompressWorldMap.visible()
        }

    }
}