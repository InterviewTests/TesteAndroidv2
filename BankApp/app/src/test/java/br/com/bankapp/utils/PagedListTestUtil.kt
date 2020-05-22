
import android.database.Cursor
import androidx.annotation.VisibleForTesting
import androidx.paging.Config
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.room.RoomDatabase
import androidx.room.RoomSQLiteQuery
import androidx.room.paging.LimitOffsetDataSource
import br.com.fortes.appcolaborador.getOrAwaitValue
import io.mockk.every
import io.mockk.mockk


@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> List<T>.asPagedList() = LivePagedListBuilder<Int, T>(createMockDataSourceFactory(this),
    Config(enablePlaceholders = false,
        prefetchDistance = 24,
        pageSize = if (size == 0) 1 else size)
)
    .build().getOrAwaitValue()

private fun <T> createMockDataSourceFactory(itemList: List<T>): DataSource.Factory<Int, T> =
    object : DataSource.Factory<Int, T>() {
        override fun create(): DataSource<Int, T> = MockLimitDataSource(itemList)
    }

private val mockQuery = mockk<RoomSQLiteQuery> {
    every { sql } returns ""
}

private val mockDb = mockk<RoomDatabase> {
    every { invalidationTracker } returns mockk(relaxUnitFun = true)
}

class MockLimitDataSource<T>(private val itemList: List<T>) : LimitOffsetDataSource<T>(mockDb, mockQuery, false, null) {
    override fun convertRows(cursor: Cursor?): MutableList<T> = itemList.toMutableList()
    override fun countItems(): Int = itemList.count()
    override fun isInvalid(): Boolean = false
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<T>) { /* Not implemented */ }

    override fun loadRange(startPosition: Int, loadCount: Int) =
        itemList.subList(startPosition, startPosition + loadCount).toMutableList()

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<T>) {
        callback.onResult(itemList, 0)
    }
}

