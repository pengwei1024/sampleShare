//
// Created by pengwei on 2017/9/1.
// Copyright (c) 2017 hiandroid. All rights reserved.
//

#import "DSWebViewController.h"
#import "dsbridge.h"

@interface DSWebViewController ()<JSBWebEventDelegateProtocol> {

}
@end

@implementation DSWebViewController {
    DUIwebview * webview;
}

- (void)onpageFinished:(NSString *)url {
    self.title = [webview stringByEvaluatingJavaScriptFromString:@"document.title"];
}


- (void)viewDidLoad {
    [super viewDidLoad];
    CGRect bounds=self.view.bounds;
    webview=[[DUIwebview alloc] initWithFrame:CGRectMake(0, 0, bounds.size.width, bounds.size.height-25)];
    jsApi=[[JsObject alloc] init];
    webview.WebEventDelegate = self;
    webview.JavascriptInterfaceObject = jsApi;
    [self.view addSubview:webview];
    __weak DUIwebview *_webview = webview;
    [webview setJavascriptContextInitedListener:^() {
        [_webview callHandler:@"addValue"
                    arguments:[[NSArray alloc] initWithObjects:@1,@"hello", nil]
            completionHandler:^(NSString * value){
                NSLog(@"%@",value);
            }];
    }];
    NSString *filePath = [[NSBundle mainBundle] pathForResource:@"dsbridge" ofType:@"html"];
    NSString *content = [NSString stringWithContentsOfFile:filePath encoding:NSUTF8StringEncoding error:nil];
//    [webview loadUrl:@"http://www.qq.com"];
    [webview loadHTMLString:content baseURL:[NSURL fileURLWithPath:filePath]];
}

@end