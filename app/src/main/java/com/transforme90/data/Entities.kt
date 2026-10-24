package com.transforme90.data

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/** Progression d'une journée du programme (1 à 90). status : "done" ou "skipped". */
@Entity(tableName = "day_progress")
data class DayProgress(
    @PrimaryKey val day: Int,
    val status: String,
    val completedAt: Long? = null,
    val note: String = ""
)

@Entity(tableName = "goals")
data class Goal(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String = "",
    val category: String = "",
    val status: String = "todo",
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val frequency: String = "daily",
    val createdAt: Long = System.currentTimeMillis()
)

/** type : "income" ou "expense". amount en unités de la devise (XOF n'a pas de décimales). */
@Entity(tableName = "transactions")
data class FinanceTransaction(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: String,
    val amount: Long,
    val category: String,
    val description: String = "",
    val date: Long = System.currentTimeMillis()
)

@Dao
interface DayProgressDao {
    @Query("SELECT * FROM day_progress")
    fun observeAll(): Flow<List<DayProgress>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(progress: DayProgress)
}

@Dao
interface GoalDao {
    @Query("SELECT * FROM goals ORDER BY createdAt DESC")
    fun observeAll(): Flow<List<Goal>>

    @Insert
    suspend fun insert(goal: Goal): Long
}

@Dao
interface HabitDao {
    @Query("SELECT * FROM habits ORDER BY createdAt")
    fun observeAll(): Flow<List<Habit>>

    @Insert
    suspend fun insert(habit: Habit): Long
}

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun observeAll(): Flow<List<FinanceTransaction>>

    @Insert
    suspend fun insert(tx: FinanceTransaction): Long
}
