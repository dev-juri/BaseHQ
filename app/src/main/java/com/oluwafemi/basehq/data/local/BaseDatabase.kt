package com.oluwafemi.basehq.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DbProduct::class, DbCart::class, DbCategories::class],
    version = 1,
    exportSchema = false
)
abstract class BaseDatabase : RoomDatabase() {
    abstract val baseDAO: BaseDAO
}