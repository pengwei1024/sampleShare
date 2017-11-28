//
//  MainViewController.m
//  FirstApp
//
//  Created by pengwei on 2017/8/30.
//  Copyright © 2017 hiandroid. All rights reserved.
//

#import "MainViewController.h"

@interface MainViewController ()

@end

@implementation MainViewController {
    UIView *loadView;
    UIActivityIndicatorView *activityIndicator;
}

@synthesize url;

- (void)viewDidLoad {
    [super viewDidLoad];
    [self.navigationController setNavigationBarHidden:NO animated:YES];
    CGRect bounds = self.view.bounds;
    UIWebView *webView = [[UIWebView alloc] initWithFrame:CGRectMake(0, 0, bounds.size.width,
            bounds.size.height - 25)];
    [webView setOpaque:NO];
    [webView setBackgroundColor:[UIColor clearColor]];
    [self createLoadingView];
    [self.view addSubview:webView];
    if (!url) {
        url = @"http://www.apkfuns.com";
    }
    NSURLRequest *request = [NSURLRequest requestWithURL:[NSURL URLWithString:url]];
    [webView loadRequest:request];
    webView.delegate = self;
    [self.view bringSubviewToFront:loadView];
}

- (void)createLoadingView {
    loadView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 80, 80)];
    loadView.layer.cornerRadius = 8;
    [loadView setTag:103];
    [loadView setBackgroundColor:[UIColor blackColor]];
    [loadView setAlpha:0.8];
    [loadView setCenter:self.view.center];
    activityIndicator = [[UIActivityIndicatorView alloc] initWithFrame:CGRectMake(0.0f, 0.0f, 32.0f, 32.0f)];
//    activityIndicator.backgroundColor = [UIColor blueColor];
    [activityIndicator setActivityIndicatorViewStyle:UIActivityIndicatorViewStyleWhiteLarge];
    [loadView addSubview:activityIndicator];
    [self.view addSubview:loadView];

//    NSLog(@"%f, %f", loadView.center.x, loadView.center.y);
    // 中心点是相对于父类
    [activityIndicator setCenter:CGPointMake(loadView.bounds.size.width / 2, loadView.bounds.size.height / 2)];
    [activityIndicator startAnimating];
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)webViewDidStartLoad:(UIWebView *)webView {
    loadView.hidden = NO;
    if (![activityIndicator isAnimating]) {
        [activityIndicator startAnimating];
    }
}


- (void)webViewDidFinishLoad:(UIWebView *)webView {
    self.title = [webView stringByEvaluatingJavaScriptFromString:@"document.title"];
    [activityIndicator stopAnimating];
    loadView.hidden = YES;
    NSLog(@"网页加载完成, url= %@", webView.request.URL.absoluteString);
    NSString *inject = @"var imgs = document.getElementsByTagName('img');for(var i=0;i<imgs.length;i++){"
            "imgs[i].onclick=function(){location.href='bridge://' + this.src;}}";
    [webView stringByEvaluatingJavaScriptFromString:inject];
}

- (BOOL)webView:(UIWebView *)webView shouldStartLoadWithRequest:(NSURLRequest *)request navigationType:(UIWebViewNavigationType)navigationType {
    NSString *url = request.URL.absoluteString;
    if ([url hasPrefix:@"bridge:"]) {
        NSLog(@"点击图片链接:%@", url);
        return NO;
    }
    return YES;
}


@end
