package me.tatocaster.core_android_testing

import me.tatocaster.core.source.remote.RetrofitService

class MockRetrofitService(val retrofitService: RetrofitService) :
    RetrofitService by retrofitService