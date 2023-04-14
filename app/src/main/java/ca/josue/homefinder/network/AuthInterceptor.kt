package ca.josue.homefinder.network

import ca.josue.homefinder.domain.repository.DataStoreOperations
import ca.josue.homefinder.utils.Constants.HEADER_AUTHORIZATION
import ca.josue.homefinder.utils.Constants.HEADER_BEARER
import ca.josue.homefinder.utils.Endpoints
import okhttp3.Interceptor
import okhttp3.Response

/**
 * created by Josue Lubaki
 * date : 2023-04-14
 * version : 1.0.0
 */

class AuthInterceptor(private val dataStoreOperations: DataStoreOperations) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val whiteList = listOf(
            Endpoints.AUTH_LOGIN,
            Endpoints.REGISTRATION
        )

        val path = originalRequest.url.encodedPath
        if (whiteList.contains(path)) {
            return chain.proceed(originalRequest)
        }

        val requestBuilder = originalRequest.newBuilder()
            .header(HEADER_AUTHORIZATION, "$HEADER_BEARER ${getToken()}")

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

    private fun getToken(): String {
        return dataStoreOperations.readTokenAccess()
    }
}