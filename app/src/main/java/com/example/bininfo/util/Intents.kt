package com.example.bininfo.util

import android.content.Context
import android.content.Intent
import android.net.Uri

fun openUrl(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$url"))
    context.startActivity(intent)
}

fun dialPhone(context: Context, phone: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
    context.startActivity(intent)
}

fun openMap(context: Context, coordinates: String) {
    val uri = Uri.parse("geo:$coordinates?q=$coordinates")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    context.startActivity(intent)
}