package com.dullabs.notiga.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dullabs.notiga.MainActivity
import com.dullabs.notiga.R
import com.dullabs.notiga.data.Notification
import com.dullabs.notiga.databinding.FragmentInboxBinding
import com.dullabs.notiga.ui.SwipeCallback
import com.dullabs.notiga.ui.adapter.NotificationAdapter
import com.google.android.material.snackbar.Snackbar

class InboxFragment : Fragment() {
    private lateinit var mNotificationAdapter: NotificationAdapter
    private var _binding: FragmentInboxBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInboxBinding.inflate(inflater, container, false)
        val view = _binding!!.root
        setupRecyclerViewer()
//        (activity as MainActivity).setupNavigationIcon()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerViewer() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mNotificationAdapter = NotificationAdapter(ArrayList(), requireContext())
        binding.recyclerView.adapter = mNotificationAdapter
        ComposeView(requireContext()).apply {
            setContent {
                InitializeData()
                binding.coordinatorLayout.setBackgroundColor(getBackGroundColor())
            }
        }
        enableSwipeDeleteAndUndo()
    }


    private fun enableSwipeDeleteAndUndo() {
        val swipeCallback: SwipeCallback = object : SwipeCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position: Int = viewHolder.adapterPosition
                val notificationItem: Notification =
                    mNotificationAdapter.getItemAtPosition(position)
                if (direction == ItemTouchHelper.RIGHT) {
                    mNotificationAdapter.removeItem(position)
                    val snackbar: Snackbar = Snackbar.make(
                        binding.coordinatorLayout,
                        "Item was removed from the list.",
                        Snackbar.LENGTH_LONG
                    )
                    snackbar.setAction("Undo") {
                        mNotificationAdapter.restoreItem(notificationItem, position)
                        binding.recyclerView.scrollToPosition(position)
                    }
                    snackbar.setActionTextColor(Color.YELLOW)
                    snackbar.setBackgroundTint(Color.DKGRAY)
                    snackbar.show()
                } else {
                    mNotificationAdapter.notifyItemChanged(position)
                    val snackbar: Snackbar = Snackbar.make(
                        binding.coordinatorLayout,
                        "Item paused.",
                        Snackbar.LENGTH_LONG
                    )
                    snackbar.setBackgroundTint(Color.DKGRAY)
                    snackbar.show()
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    @Composable
    private fun InitializeData() {
        mNotificationAdapter.addItem(
            Notification(
                painterResource(id = R.drawable.ic_whatsapp),
                "Chrome",
                "We have some crap that you want to check out."
            )
        )
        mNotificationAdapter.addItem(
            Notification(
                painterResource(id = R.drawable.ic_whatsapp),
                "Chrome",
                "Hello"
            )
        )
        mNotificationAdapter.addItem(
            Notification(
                painterResource(id = R.drawable.ic_whatsapp),
                "Chrome",
                "Something hoopla"
            )
        )
        mNotificationAdapter.addItem(
            Notification(
                painterResource(id = R.drawable.ic_whatsapp),
                "Chrome",
                "Product hunt makes sense"
            )
        )
    }

    @Composable
    private fun getBackGroundColor(): Int {
        return Color.argb(
            MaterialTheme.colors.background.alpha,
            MaterialTheme.colors.background.red,
            MaterialTheme.colors.background.green,
            MaterialTheme.colors.background.blue
        )
    }

}