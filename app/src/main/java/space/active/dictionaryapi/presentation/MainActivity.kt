package space.active.dictionaryapi.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import space.active.dictionaryapi.databinding.ActivityMainBinding


const val TAG = "Dictionary MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: WordViewModel
    private val adapter: AdapterRecyclerView = AdapterRecyclerView()
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e(TAG, "created")
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(WordViewModel::class.java)


        recyclerView = binding.recyclerView
        recyclerView.layoutManager
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.eventFlow.collectLatest { event ->
                when (event) {
                    is UIEvent.ShowSnackbar -> {
                        Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_SHORT).show()
//                        Snackbar.make(
//                            binding.root,
//                            event.message,
//                            Snackbar.LENGTH_LONG
//                        ).show()
                    }
                }

            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest { state ->
                when (state.isLoading) {
                    true -> {
                        binding.progressBar.isVisible = true
                    }
                    false -> {
                        binding.progressBar.isVisible = false
                        adapter.setList(state.wordItems.map { it.toRecycler() })
                    }
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.searchQuery.collectLatest {
                binding.textViewTitle.text = it
            }
        }

        binding.textInputEdit.addTextChangedListener { text ->
            text?.let {
                viewModel.onSearch(it.toString())
            }
        }

    }
}