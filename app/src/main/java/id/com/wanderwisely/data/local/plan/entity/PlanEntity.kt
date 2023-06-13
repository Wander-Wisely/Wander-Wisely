package id.com.wanderwisely.data.local.plan.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Plan_Wisata")
data class PlanEntity(
    @field:ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int?,

    @field:ColumnInfo(name = "name")
    val name: String?,

    @field:ColumnInfo(name = "city")
    val city: String?,

    @field:ColumnInfo(name = "costFrom")
    val costFrom: Int,

    @field:ColumnInfo(name = "costTo")
    val costTo: Int,

    @field:ColumnInfo(name = "path")
    val path: String? = null,
): Serializable