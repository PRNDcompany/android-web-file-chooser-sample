package kr.co.prnd.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.prnd.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webView.loadUrl("file:///android_asset/sample.html")
    }
}
