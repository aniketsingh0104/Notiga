package com.dullabs.notiga

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dullabs.notiga.data.Notification
import com.dullabs.notiga.ui.SwipeCallback
import com.dullabs.notiga.ui.adapter.NotificationAdapter
import com.dullabs.notiga.ui.components.NotificationComponent
import com.dullabs.notiga.ui.theme.NotigaTheme
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mCoordinatorLayout: CoordinatorLayout
    private lateinit var mNotificationAdapter: NotificationAdapter
//    private lateinit var mNotificationsData: ArrayList<Notification>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotigaTheme {
                setContentView(R.layout.activity_main)
                mRecyclerView = findViewById(R.id.recyclerView)
                mCoordinatorLayout = findViewById(R.id.coordinatorLayout)
                mRecyclerView.layoutManager = LinearLayoutManager(this)
                mNotificationAdapter = NotificationAdapter(ArrayList(), this)
                InitializeData()
                mRecyclerView.adapter = mNotificationAdapter
                enableSwipeDeleteAndUndo()
//                mNotificationsData = ArrayList()
//                Surface(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colors.background) {
//                    val notification: Notification = Notification(vectorResource(id = R.drawable.ic_whatsapp), "Chrome", "We have some crap that you want to check out.")
//                    NotificationComponent(notification)
//                }
            }
        }
    }

    @Composable
    private fun InitializeData() {
        mNotificationAdapter.addItem(
            Notification(
                vectorResource(id = R.drawable.ic_whatsapp),
                "Chrome",
                "We have some crap that you want to check out."
            )
        )
        mNotificationAdapter.addItem(
            Notification(
                vectorResource(id = R.drawable.ic_whatsapp),
                "Chrome",
                "Hello"
            )
        )
        mNotificationAdapter.addItem(
            Notification(
                vectorResource(id = R.drawable.ic_whatsapp),
                "Chrome",
                "Hello"
            )
        )
        mNotificationAdapter.addItem(
            Notification(
                vectorResource(id = R.drawable.ic_whatsapp),
                "Chrome",
                "Hello"
            )
        )
    }

    private fun enableSwipeDeleteAndUndo() {
        val swipeCallback: SwipeCallback = object : SwipeCallback(this) {
            //            @Composable
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position: Int = viewHolder.adapterPosition
                val notificationItem: Notification =
                    mNotificationAdapter.getItemAtPosition(position)
                mNotificationAdapter.removeItem(position)
                val snackbar: Snackbar = Snackbar.make(
                    mCoordinatorLayout,
                    "Item was removed from the list.",
                    Snackbar.LENGTH_LONG
                )
                snackbar.setAction("Undo") {
                    mNotificationAdapter.restoreItem(notificationItem, position)
                    mRecyclerView.scrollToPosition(position)
                }
                snackbar.setActionTextColor(android.graphics.Color.YELLOW)
                snackbar.show()
//                Snackbar(modifier = Modifier.padding(8.dp), action = {
//                    Button(onClick = {
//                        mNotificationAdapter.restoreItem(notificationItem, position)
//                        mRecyclerView.scrollToPosition(position)
//                    }) {
//                        Text("Undo", color = Color.Yellow)
//                    }
//                } ) {
//                    Text("Item was removed from the list.", color = Color.Cyan)
//                }
            }
        }

        val itemTouchHelper: ItemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(mRecyclerView)
    }
}