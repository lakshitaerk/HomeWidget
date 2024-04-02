package com.example.testing


import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import io.flutter.plugin.platform.PlatformView

internal class NativeView(context: Context, id: Int, creationParams: Map<String?, Any?>?) : PlatformView {

    private val textView: TextView
    private val rootView: View

    init {
        // Inflate the XML layout
        val inflater = LayoutInflater.from(context)
        rootView = inflater.inflate(R.layout.native_view_layout, null)

        // Find TextView within the inflated layout
        textView = rootView.findViewById(R.id.nativeTextView)

        // Set properties
//        textView.text = "Rendered on a native Android view (id: $id)"
//        textView.textSize = 72f
//        textView.setBackgroundColor(Color.rgb(255, 255, 255))
    }

    override fun getView(): View {
        return rootView
    }

    override fun dispose() {
        // Dispose resources if needed
    }
}