package kr.co.prnd.sample

import android.content.Context
import android.net.Uri
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView

class PrndWebChromeClient(
    private val context: Context,
) : WebChromeClient() {
    private val fileChooser = FileChooser(context)

    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        fileChooser.show()
        return super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
    }
}
