package com.example.simplemvvm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplemvvm.adapter.CharacterPagedAdapter
import com.example.simplemvvm.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val rvCharacter: RecyclerView by lazy { findViewById(R.id.rv_character) }
    private lateinit var characterAdapter: CharacterPagedAdapter
    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        loadingData()
    }

    private fun loadingData() {
        lifecycleScope.launch {
            viewModel.listData.collect { pagingData ->
                characterAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupRecyclerView() {
        characterAdapter = CharacterPagedAdapter()

        rvCharacter.apply {
//            layoutManager = StaggeredGridLayoutManager(
//                2, StaggeredGridLayoutManager.VERTICAL
//            )
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = characterAdapter
            setHasFixedSize(true)
        }

    }
}