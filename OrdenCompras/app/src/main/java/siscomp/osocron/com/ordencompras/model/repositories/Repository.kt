package siscomp.osocron.com.ordencompras.model.repositories

interface Repository<T, I> {

    fun getAll(): List<T>

    fun getById(id: I): T?

    fun insert(t: T): T?

    fun update(t: T): T?

    fun delete(id: I): T?

}