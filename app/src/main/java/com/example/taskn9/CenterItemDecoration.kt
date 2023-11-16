import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CenterItemDecoration(private val spanCount: Int, private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount
        val hSpacing = spacing * (spanCount + 1) / spanCount
        val vSpacing = if (position < spanCount) spacing else 0

        outRect.left = column * hSpacing / spanCount
        outRect.right = hSpacing - (column + 1) * hSpacing / spanCount
        outRect.top = vSpacing
        outRect.bottom = spacing
    }
}