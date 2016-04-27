## 远程网页访问 App 内置本地资源

系统提供的主要方法：

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
    
只需要对 request 进行拦截，然后按照一定规则将 WebResourceResponse 的返回替换成本地的资源即可。

## App 内置网页访问远程资源

待补充
