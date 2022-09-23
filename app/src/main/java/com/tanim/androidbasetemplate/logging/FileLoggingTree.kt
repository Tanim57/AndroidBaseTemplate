package com.tanim.androidbasetemplate.logging

import com.tanim.androidbasetemplate.logging.AppDebugTree
import com.tanim.androidbasetemplate.logging.FileLoggingTree
import android.text.TextUtils
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

/**
 * Edit the LOG_DIR for application specific directory
 * Verbose and Debug level logs won't be written in file
 */
class FileLoggingTree : AppDebugTree() {
    private var mFileLogComatible = false
    private var mLogFile = ""
    private fun initMembers() {
        val logFileName = SimpleDateFormat(
            LOG_FILE_NAME_TIME_STAMP_FORMAT,
            Locale.ENGLISH
        ).format(Date()) + ".html"
        if (!TextUtils.equals(logFileName, mLogFile)) {
            mLogFile = logFileName
        }
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        initMembers()
        val logTimeStamp = SimpleDateFormat(
            LOG_TIME_STAMP_FORMAT,
            Locale.ENGLISH
        ).format(Date())
        if (!mFileLogComatible) {
            super.log(priority, tag, message, t)
        } else {
            val directory = storageDirectory
            if (directory == null) {
                super.log(priority, tag, message, t)
            } else {
                if (priority == Log.DEBUG || priority == Log.VERBOSE) {
                    super.log(priority, tag, message, t)
                } else {
                    val logFile = getLogFileIn(directory)
                    if (logFile != null) {
                        try {
                            val outputStream: OutputStream = FileOutputStream(logFile, true)
                            if (message.length < MAX_LOG_LENGTH) {
                                if (priority == Log.ERROR) {
                                    outputStream.write("<p style=\"background:lightgray;\"><strong style=\"background:pink;\">&nbsp&nbsp$logTimeStamp$tag :&nbsp&nbsp</strong><font color=red>&nbsp&nbsp$message</font></p>".toByteArray())
                                } else {
                                    outputStream.write("<p style=\"background:lightgray;\"><strong style=\"background:lightblue;\">&nbsp&nbsp$logTimeStamp$tag :&nbsp&nbsp</strong>&nbsp&nbsp$message</p>".toByteArray())
                                }
                            } else {
                                var i = 0
                                val length = message.length
                                while (i < length) {
                                    var newline = message.indexOf('\n', i)
                                    newline = if (newline != -1) newline else length
                                    do {
                                        val end = Math.min(newline, i + MAX_LOG_LENGTH)
                                        val part = message.substring(i, end)
                                        if (priority == Log.ERROR) {
                                            outputStream.write("<p style=\"background:lightgray;\"><strong style=\"background:pink;\">&nbsp&nbsp$logTimeStamp$tag :&nbsp&nbsp</strong><font color=red>&nbsp&nbsp$message</font></p>".toByteArray())
                                        } else {
                                            outputStream.write("<p style=\"background:lightgray;\"><strong style=\"background:lightblue;\">&nbsp&nbsp$logTimeStamp$tag :&nbsp&nbsp</strong>&nbsp&nbsp$message</p>".toByteArray())
                                        }
                                        i = end
                                    } while (i < newline)
                                    i++
                                }
                            }
                            outputStream.close()
                        } catch (e: IOException) {
                            super.log(priority, tag, message, t)
                        }
                    } else {
                        super.log(priority, tag, message, t)
                    }
                }
            }
        }
    }

    private val storageDirectory: File?
        private get() {
            val dir = File(
                Environment.getExternalStorageDirectory()
                    .toString() + File.separator + LOG_DIR
            )
            if (!dir.exists()) {
                val created = dir.mkdir()
                if (!created) {
                    return null
                }
            }
            return dir
        }

    private fun getLogFileIn(dir: File): File? {
        val logFile = File(dir.path + File.separator + mLogFile)
        if (!logFile.exists()) {
            try {
                val created = logFile.createNewFile()
                if (!created) {
                    return null
                }
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            }
        }
        return logFile
    }

    private val isExternalStorageWritable: Boolean
        private get() {
            val state = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED == state
        }

    companion object {
        private const val MAX_LOG_LENGTH = 4000
        private const val LOG_DIR = "Log"
        private const val LOG_FILE_NAME_TIME_STAMP_FORMAT = "dd-MM-yyyy"
        private const val LOG_TIME_STAMP_FORMAT = "E MMM dd yyyy 'at' hh:mm:ss:SSS aaa"
    }

    init {
        if (isExternalStorageWritable) {
            mFileLogComatible = true
            initMembers()
        }
    }
}