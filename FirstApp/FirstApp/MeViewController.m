//
// Created by pengwei on 2017/8/31.
// Copyright (c) 2017 hiandroid. All rights reserved.
//

#import "MeViewController.h"
#import "DSWebViewController.h"
#import <SVProgressHUD/SVProgressHUD.h>

NSString *const MeViewControllerIdentifier = @"STExploreCellIdentifier";

@interface MeViewController () <UITableViewDataSource, UITableViewDelegate>
@end

@implementation MeViewController {
    UITableView *_uiTableView;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    _uiTableView = [[UITableView alloc] initWithFrame:self.view.bounds style:UITableViewStyleGrouped];
    [_uiTableView registerClass:[UITableViewCell class] forCellReuseIdentifier:MeViewControllerIdentifier];
    _uiTableView.dataSource = self;
    _uiTableView.delegate = self;
    [self.view addSubview:_uiTableView];
    _uiTableView.center = self.view.center;
}

- (void)tableView:(UITableView *)tableView1 didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    if (indexPath.item == 0) {
        DSWebViewController *controller = [[DSWebViewController alloc] init];
        controller.hidesBottomBarWhenPushed = YES;
        [self.navigationController pushViewController:controller animated:YES];
    } else {
        [SVProgressHUD showSuccessWithStatus:[NSString stringWithFormat:@"选中:%i", indexPath.item] maskType:SVProgressHUDMaskTypeBlack];
    }
}


- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return 20;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:MeViewControllerIdentifier forIndexPath:indexPath];
    if (indexPath.item == 0) {
        cell.textLabel.text = @"DSBridge 测试";
    } else {
        cell.textLabel.text = [NSString stringWithFormat:@"Hi-%i", indexPath.item];
    }
    cell.layer.shouldRasterize = YES;
    cell.layer.rasterizationScale = [UIScreen mainScreen].scale;
    return cell;
}


@end