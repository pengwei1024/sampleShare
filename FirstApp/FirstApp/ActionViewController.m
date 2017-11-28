//
// Created by pengwei on 2017/8/30.
// Copyright (c) 2017 hiandroid. All rights reserved.
//

#import "ActionViewController.h"
#import "View+MASAdditions.h"
#import "MainViewController.h"
#import <SVProgressHUD/SVProgressHUD.h>

@interface ActionViewController ()

@end

@implementation ActionViewController {

}

// http://www.cnblogs.com/wendingding/p/3761730.html

- (void)viewDidLoad {
    [super viewDidLoad];
    [self setupUI];
}

- (void)setupUI {
    UIBarButtonItem *buttonItem = [[UIBarButtonItem alloc] initWithTitle:@"Detail"
      style:UIBarButtonItemStylePlain target:self action:@selector(gotoPage)];
    self.navigationItem.rightBarButtonItem = buttonItem;

    for (int i = 0; i < 5; ++i) {
        UIButton *button = [UIButton buttonWithType:UIButtonTypeSystem];
        [button setTitle:@"Hello" forState:UIControlStateNormal];
        [button addTarget:self action:@selector(onHelloButtonClicked:) forControlEvents:UIControlEventTouchUpInside];
        [self.view addSubview:button];
        [button mas_makeConstraints:^(MASConstraintMaker *make){
            make.width.equalTo(@60.0);
            make.height.equalTo(@40.0);
            make.topMargin.equalTo(@10);
//            make.center.equalTo(self.view);
        }];
    }
}

-(void) gotoPage {
    MainViewController *mainViewController = [[MainViewController alloc] init];
    mainViewController.hidesBottomBarWhenPushed = YES;
    mainViewController.url = @"http://www.baidu.com";
    [self.navigationController pushViewController:mainViewController animated:YES];
}

-(void) onHelloButtonClicked:(id) sender {
    [SVProgressHUD showSuccessWithStatus:@"Hello, world!" maskType:SVProgressHUDMaskTypeBlack];
}

@end