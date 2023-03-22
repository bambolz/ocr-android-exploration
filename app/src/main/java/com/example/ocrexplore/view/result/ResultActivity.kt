package com.example.ocrexplore.view.result

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.ocrexplore.databinding.ActivityResultBinding
import com.example.ocrexplore.view.main.MainUIModel

class ResultActivity : AppCompatActivity() {
    private val binding by lazy { ActivityResultBinding.inflate(layoutInflater) }
    private val result by lazy { intent.getParcelableExtra<MainUIModel>("result") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    private fun setupView() = with(binding) {
        setContentView(root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        etDistance.setText(result?.distance ?: "")
        etEstTime.setText(result?.duration ?: "")
        etScanResult.setText(result?.text ?: "")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                super.onBackPressed()
        }
        return true
    }
}
