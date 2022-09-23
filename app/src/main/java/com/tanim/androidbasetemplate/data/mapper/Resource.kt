import androidx.annotation.Keep
import com.tanim.androidbasetemplate.data.mapper.Status

@Keep
data class Resource<out T,out V>(val status: Status, val data: T?, val message: V?) {

    companion object {
        fun <T,V> success(data: T): Resource<T, V> {
            return Resource(Status.SUCCESS, data, null)
        }
        fun <T,V> success(data: T,msg: V): Resource<T, V> {
            return Resource(Status.SUCCESS, data, msg)
        }
        fun <T,V> error(msg: V): Resource<T, V> {
            return Resource(Status.ERROR, null, msg)
        }
        fun <T,V> error(msg: V, data: T?): Resource<T, V> {
            return Resource(Status.ERROR, data, msg)
        }
        fun <T,V> loading(data: T?,msg: V?): Resource<T, V> {
            return Resource(Status.LOADING, data, msg)
        }
        fun <T,V> loading(data: T?): Resource<T, V> {
            return Resource(Status.LOADING, data, null)
        }
        fun <T,V> loading(): Resource<T, V> {
            return Resource(Status.LOADING, null, null)
        }
    }

}
