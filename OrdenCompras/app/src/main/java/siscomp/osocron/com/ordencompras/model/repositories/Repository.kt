package siscomp.osocron.com.ordencompras.model.repositories

interface Repository<T, I> {

    fun getAll(): List<T>

    fun getById(id: I): T?

    fun queryAllByPredicate(p: (T) -> Boolean): List<T>

    fun querySingleByPredicate(p: (T) -> Boolean): T?

    fun queryIfExists(p: (T) -> Boolean): Boolean

    fun insert(t: T): T?

    fun update(t: T): T?

    fun delete(id: I): T?

}