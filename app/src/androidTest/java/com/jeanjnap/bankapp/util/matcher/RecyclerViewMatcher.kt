package com.jeanjnap.bankapp.util.matcher

import android.content.res.Resources
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class RecyclerViewMatcher constructor(private val recyclerViewId: Int) {

    companion object {
        private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
            return RecyclerViewMatcher(
                recyclerViewId
            )
        }

        fun withItemCount(matcher: Matcher<Int>): RecyclerViewItemCountAssertion {
            return RecyclerViewItemCountAssertion(
                matcher
            )
        }

        fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?>? {
            return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("has item at position $position: ")
                    itemMatcher.describeTo(description)
                }

                override fun matchesSafely(view: RecyclerView): Boolean {
                    val viewHolder =
                        view.findViewHolderForAdapterPosition(position) ?: return false
                    return itemMatcher.matches(viewHolder.itemView)
                }
            }
        }

        fun withRecyclerViewItemIdAtPosition(@IdRes recyclerView: Int, position: Int, @IdRes childViewId: Int) =
            withRecyclerView(
                recyclerView
            ).atPositionOnView(position, childViewId)

        fun withItemCount(expectedCount: Int): RecyclerViewItemCountAssertion {
            return withItemCount(
                `is`(expectedCount)
            )
        }
    }

    fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            var resources: Resources? = null
            var childView: View? = null

            override fun describeTo(description: Description) {
                var idDescription = "$recyclerViewId (resource name not found)"
                this.resources?.let {
                    idDescription = it.getResourceName(recyclerViewId)
                }

                description.appendText("with id: $idDescription")
            }

            public override fun matchesSafely(view: View): Boolean {
                this.resources = view.resources

                val recyclerView =
                    view.rootView.findViewById<View>(recyclerViewId) as RecyclerView
                if (recyclerView.id == recyclerViewId) {
                    val viewHolder =
                        recyclerView.findViewHolderForAdapterPosition(position)
                    if (viewHolder != null) {
                        childView = viewHolder.itemView
                    }
                } else {
                    return false
                }

                return if (targetViewId == -1) {
                    view === childView
                } else {
                    val targetView = childView?.findViewById<View>(targetViewId)
                    view === targetView
                }
            }
        }
    }
}