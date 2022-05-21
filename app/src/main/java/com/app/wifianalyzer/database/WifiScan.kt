import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wifi_scan_results")
data class WifiScan(
    @PrimaryKey(autoGenerate = true)
    var scanId: Long = 0L,

    @ColumnInfo(name = "scan_time")
    val timeMili: Long = System.currentTimeMillis()
)