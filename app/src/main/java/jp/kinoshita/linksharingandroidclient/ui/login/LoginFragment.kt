package jp.kinoshita.linksharingandroidclient.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import jp.kinoshita.linksharingandroidclient.R
import jp.kinoshita.linksharingandroidclient.application.LoginViewModel
import jp.kinoshita.linksharingandroidclient.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {


    private lateinit var viewModel: LoginViewModel

    private lateinit var mBinding: FragmentLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container,false)
        mBinding.loginViewModel = viewModel

        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        mBinding.loginCancel.setOnClickListener {
            val controller = findNavController()
            controller.navigateUp()
        }
    }

}
