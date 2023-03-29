package kr.co.prnd.sample

import android.content.Intent
import android.net.Uri

sealed interface FileChooserResult {
    object Empty : FileChooserResult

    data class File(
        val resultCode: Int,
        val data: Intent,
        val uri: Uri,
    ) : FileChooserResult

    data class Files(
        val uris: List<Uri>,
    ) : FileChooserResult

    companion object {
        fun parse(
            resultCode: Int,
            data: Intent?,
        ): FileChooserResult {
            if (data == null) return Empty

            val singleFileChooserData = data.data
            val multipleFileChooserData = data.getMultipleFileChooserData()
            return when {
                singleFileChooserData != null -> File(resultCode, data, singleFileChooserData)
                multipleFileChooserData != null -> Files(multipleFileChooserData)
                else -> Empty
            }
        }

        private fun Intent.getMultipleFileChooserData(): List<Uri>? {
            val clipData = clipData ?: return null
            return (0 until clipData.itemCount).map { clipData.getItemAt(it).uri }
        }
    }
}
