package com.example.video

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.FileReader
import java.io.FileWriter
import java.lang.StringBuilder

object FileUtil {

    fun saveText(path: String, text: String) {
        var os: BufferedWriter? = null

        try {
            os = BufferedWriter(FileWriter(path))
            os.write(text)
            os.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun openText(path: String): String {
        var br: BufferedReader? = null
        var sb = StringBuilder()
        try {
            br = BufferedReader(FileReader(path))
            var line: String? = null
            while ((br.readLine().also { line = it }) != null) {
                sb.append(line)
            }

            br.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return sb.toString()
    }

    fun saveImage(path: String, bitmap: Bitmap) {
        try {
            val fos = FileOutputStream(path)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
        }
    }

    fun readImage(path: String): Bitmap? {
        val fis = FileInputStream(path)
        val bitmap = BitmapFactory.decodeStream(fis)

        return bitmap
    }
}