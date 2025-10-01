package com.example.ifunsoedmobile

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ifunsoedmobile.data.model.BookDoc
import com.example.ifunsoedmobile.databinding.ActivityDaftarBukuBinding
import com.example.ifunsoedmobile.ui.adapter.BookAdapter
import com.example.ifunsoedmobile.ui.adapter.onBookClickListener
import com.example.ifunsoedmobile.ui.fragment.BookDetailFragment
import com.example.ifunsoedmobile.viewmodel.MainViewModel

class DaftarBukuActivity : AppCompatActivity(), onBookClickListener {

    private lateinit var binding: ActivityDaftarBukuBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter = BookAdapter(emptyList(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.books.observe(this){
            adapter.setData(it)
        }

        viewModel.fetchBooks("kotlin programming")
    }

    override fun onBookClick(book: BookDoc) {
            book.let { b ->
                BookDetailFragment(
                     b.title ?: "-",
                    b.authorName?.joinToString(separator = ", ") ?: "Unknown Author",
                     "${b.firstPublishYear}",
                     b.coverId?: 0
                ).show(
                    supportFragmentManager,
                    BookDetailFragment::class.java.simpleName
                )
            }
        }

    }
