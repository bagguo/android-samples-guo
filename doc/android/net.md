# 等待多个异步操作全部完成

```

val deferredList = arrayListOf<Deferred<Any>>()

async {
    apiRepository.getWalletBalance(currency)
}.let { 
    deferredList.add(it) 
}

async {
    apiRepository.getWalletBalance(mainCoinCurrency)
}.let { 
    deferredList.add(it) 
}

val r = deferredList.awaitAll()
                    
```