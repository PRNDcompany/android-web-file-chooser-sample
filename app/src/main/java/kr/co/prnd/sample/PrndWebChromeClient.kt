package kr.co.prnd.sample

import android.content.Context
import android.net.Uri
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Toast

class PrndWebChromeClient(
    private val context: Context,
) : WebChromeClient() {
    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        Toast.makeText(context, "파일 선택 클릭", Toast.LENGTH_SHORT).show()
        return super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
    }
}
