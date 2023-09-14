package com.example.app_provider_server.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log

val TAG = "hanami"

class UserInfoContentProvider : ContentProvider() {

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        Log.i(TAG, "delete: uri $uri, selection $selection, args $selectionArgs")
        return 1
    }

    override fun getType(uri: Uri): String? {
        Log.i(TAG, "getType: uri $uri")
        return "getType"
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        Log.i(TAG, "insert: $uri, values $values")
        return uri
    }

    override fun onCreate(): Boolean {
        Log.i(TAG, "onCreate:")
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        TODO("Implement this to handle query requests from clients.")
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}