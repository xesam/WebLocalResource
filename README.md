## 远程网页访问 App 内置本地资源

WebView 打开网页的时候，如果网页里面包含较多的 CSS， JS，图片等资源，可能需要非常长的时间。
为了提高加载速度，我们可以将各个网页通用的资源预先内置到 App 中，在网页从远程服务器加载资源之前，先检查本地是否已经有对应的预置或者预下载资源。
如果根据规则命中本地资源，则让 WebView 直接加载本地资源，当没有找到本地

### 拦截规则讨论
比如，针对下列请求：

```html
    <link rel="stylesheet" href="http://xesam.github.io/css/app.css?v=6">
```

WebView 在加载 http://xesam.github.io//css/app.css?v=6 之前，先进行拦截：

1. 获取 url （http://xesam.github.io/css/app.css） 与版本号（5）
2. 依据规则查找对应的本地资源

#### 如何制定规则

制定规则的方式比较多

1. 可以在 assets 文件夹按照 url 地址方式对应的资源文件。比如 [https://github.com/xesam/WebLocalResource/blob/master/web-local-resource/src/main/java/dev/xesam/android/web/localresource/UrlAssetInterceptRule.java](https://github.com/xesam/WebLocalResource/blob/master/web-local-resource/src/main/java/dev/xesam/android/web/localresource/UrlAssetInterceptRule.java)
2. 预定义一份本地资源映射文件，根据 key 来查找资源 value。比如 [https://github.com/xesam/WebLocalResource/blob/master/web-local-resource/src/main/java/dev/xesam/android/web/localresource/MapAssetInterceptRule.java](https://github.com/xesam/WebLocalResource/blob/master/web-local-resource/src/main/java/dev/xesam/android/web/localresource/MapAssetInterceptRule.java)
3. 其他任何 io 都可以，甚至可以将一个远程资源替换为另一个远程资源

#### 版本号的作用

版本号租要是为了提高灵活性，假如远程网页想要更新版本，则可以修改资源连接的版本号，从而绕过本地资源。

### 拦截实现

系统提供的主要方法：

```java
    WebViewClient() {

        @TargetApi(Build.VERSION_CODES.HONEYCOMB)
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            return null;
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            return null;
        }
    }
```
    
只需要对 request 进行拦截，然后按照一定规则将 WebResourceResponse 的返回替换成本地的资源即可。

具体实现参考：[https://github.com/xesam/WebLocalResource](https://github.com/xesam/WebLocalResource)

## 动态下载预置资源

上面讨论了在发布版本的时候预置网页资源，这个方式局限比较大，因此，为了更好的灵活性，可以在app运行过程中动态下载拦截规则和资源。

具体内容待续...

有问题请联系[xesam](http://xesam.github.io/about/) 或者 Q群：315658668