package com.example.testing
import HomeWidgetGlanceState
import HomeWidgetGlanceStateDefinition
import android.content.Context
import androidx.compose.runtime.Composable


import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.*
import androidx.glance.text.Text


/*
class HomeWidgetReceiver : GlanceAppWidget() {
    private val title: MutableState<String> = mutableStateOf("Default Title")
    private val message: MutableState<String> = mutableStateOf("Default Message")

    companion object {
        fun updateWidgetData(newTitle: String, newMessage: String) {
            title.value = newTitle
            message.value = newMessage
        }
    }
    companion object {
        private const val CHANNEL = "com.example.home_widget_channel"

        fun registerMethodChannel(flutterEngine: FlutterEngine) {
            MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
                .setMethodCallHandler { call, result ->
                    if (call.method == "updateWidgetData") {
                        val title = call.argument<String>("title")
                        val message = call.argument<String>("message")
                        if (title != null && message != null) {
                            updateWidgetData(title, message)
                            result.success(null)
                        } else {
                            result.error("ARGUMENT_ERROR", "Title or message is null", null)
                        }
                    } else {
                        result.notImplemented()
                    }
                }
        }
    }

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent { GlanceContent(context, title, message) }
    }

    @Composable
    private fun GlanceContent(
        context: Context,
        title: MutableState<String>,
        message: MutableState<String>
    ) {
        Box(
            modifier = GlanceModifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title.value,
                    modifier = GlanceModifier.padding(16.dp)
                )
                Text(
                    text = message.value,
                    modifier = GlanceModifier.padding(16.dp)
                )
            }
        }
    }
}
*/
import HomeWidgetGlanceWidgetReceiver
import android.content.SharedPreferences
import androidx.glance.currentState

class HomeWidgetReceiver : HomeWidgetGlanceWidgetReceiver<HomeWidgetGlanceAppWidget>() {
    override val glanceAppWidget = HomeWidgetGlanceAppWidget()
}
class HomeWidgetGlanceAppWidget : GlanceAppWidget() {

    /**
     * Needed for Updating
     */
    override val stateDefinition = HomeWidgetGlanceStateDefinition()

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceContent(context, currentState())
        }
    }


    @Composable
    private fun GlanceContent(context: Context, currentState: HomeWidgetGlanceState) {
        // Use data to access the data you save with
        val data = currentState.preferences


        // Access title and message from SharedPreferences
        val title = getPreference(context, "title", "Default Title")
        val message = getPreference(context, "message", "Default Message")

        // Build Your Composable Widget
        Column {
            Text(text = "Title: $title")
            Text(text = "Message: $message")
        }
    }

    private fun getPreference(context: Context, key: String, defaultValue: String): String {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("widget_data", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }
}