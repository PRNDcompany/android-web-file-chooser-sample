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

    companion object {
        fun parse(
            resultCode: Int,
            data: Intent?,
        ): FileChooserResult {
            if (data == null) return Empty

            val singleFileChooserData = data.data
            return when {
                singleFileChooserData != null -> File(resultCode, data, singleFileChooserData)
                else -> Empty
            }
        }
    }
}
