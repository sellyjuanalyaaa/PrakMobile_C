package com.example.ifunsoedmobile.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import android.widget.Toast
import com.example.ifunsoedmobile.R
import com.example.ifunsoedmobile.data.model.BookDoc
import com.example.ifunsoedmobile.databinding.FragmentBookDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 * A simple [Fragment] subclass.
 * Use the [BookDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
    class BookDetailFragment(
        private val title: String,
        private val author: String,
        private val year: String,
        private val coverId: Int) : BottomSheetDialogFragment() {
            private var _binding : FragmentBookDetailBinding? = null
        private val binding get() = _binding!!

        override fun getTheme(): Int = R.style.ThemeOverlay_App_BottomSheetDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LoadData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

private fun LoadData() {
    // Menampilkan ID cover dengan Toast
    Toast.makeText(
        context,
        "Cover ID: $coverId",
        Toast.LENGTH_SHORT
    ).show()

    // Set data ke TextView
    binding.textViewTitle.text = title
    binding.textViewAuthor.text = author
    binding.textViewYear.text = year

    // Load gambar cover
    if (coverId != 0) {
        val url = "https://covers.openlibrary.org/b/id/$coverId-M.jpg"
        Glide.with(this)
            .load(url)
            .into(binding.imageViewCover)
    } else {
        // Jika coverId kosong, gunakan gambar default
        binding.imageViewCover.setImageResource(
            R.drawable.book_not_found
        )
    }



}
}