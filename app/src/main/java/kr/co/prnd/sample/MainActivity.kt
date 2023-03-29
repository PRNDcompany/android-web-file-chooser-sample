package kr.co.prnd.sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kr.co.prnd.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("file:///android_asset/sample.html")
        binding.webView.setupFileChooser()
    }

    private fun WebView.setupFileChooser() {
        webChromeClient = PrndWebChromeClient(
            context = this@MainActivity,
            coroutineScope = lifecycleScope,
        )
    }
}
